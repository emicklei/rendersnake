package org.rendersnake.site;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendershark.core.HttpPostHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.RequestUtils;
import org.rendersnake.internal.ContextMap;
import org.rendersnake.site.components.TranslatorForm;
/**
 * 
 * @author ernestmicklei
 */
@Singleton @Named("/translator.html")
public class TranslatorPageAction implements HttpGetHandler, HttpPostHandler {

    @Inject StatsManager statsManager;
    
    public void get(HtmlCanvas html, HandlerResult result) throws IOException {
        
        // secret hook to force stats export
        if (RequestUtils.getParameter(html, "save") != null) {
            statsManager.contextDestroyed();
        }
        
        html.getPageContext().withString("title","renderSnake - Translator");
        html.render(new SiteLayoutWrapper(new TranslatorForm()));
    }

    public void post(HtmlCanvas html, HandlerResult result) throws IOException {
        
        ContextMap session = RequestUtils.getSession(html);
        boolean useTidy = "on".equals(RequestUtils.getParameter(html,"tidy"));
        String handle = RequestUtils.getParameter(html,"handle");
        if ("translate".equals(handle)) {
            ByteArrayInputStream input = new ByteArrayInputStream(RequestUtils.getParameter(html,"html").getBytes());
            HtmlToRenderSnakeTranslator translator = new HtmlToRenderSnakeTranslator();
            try {
                translator.translate(input,useTidy);
            } catch (Exception ex) {
                translator.errorMessage = ex.getMessage();
            }
            session.withString("java", translator.isSuccess() ? translator.toJavaSource() : translator.errorMessage);
            session.withString("html", RequestUtils.getParameter(html,"html"));
        }
        if ("back".equals(handle)) {
            session.withString("java", null);
        }
        if (useTidy) {
            session.withString("tidy", "on");
        } else {
            session.withString("tidy", null);
        }
        result.redirectTo("translator.html");
    }
}
