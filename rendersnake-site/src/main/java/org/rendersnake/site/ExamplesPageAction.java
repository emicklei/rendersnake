package org.rendersnake.site;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;
import org.rendersnake.site.components.SourceLink;
import org.rendersnake.site.example.CityTable;
import org.rendersnake.site.example.IconImage;
import org.rendersnake.site.example.IncludeExternalJavascript;
import org.rendersnake.site.example.InsideFooter;
import org.rendersnake.site.example.InsideHead;
import org.rendersnake.site.example.LoginForm;
/**
 * 
 * @author ernestmicklei
 */
@Singleton @Named("/examples.html")
public class ExamplesPageAction implements HttpGetHandler {

	public void get(HtmlCanvas html, HandlerResult result) throws IOException {
        
        html.getPageContext().withString("title","renderSnake - Examples");
        
        // Use an anonymous defined component to render the examples content
        SiteLayoutWrapper layout = new SiteLayoutWrapper(new SyntaxHighlightingWrapper(this.renderContentOn(html)));
        layout.showSideBar = false;
        html.render(layout);
    }
    /**
     * Return a component that renders the content of the examples page.
     * @param html
     * @return
     * @throws IOException
     */
    private Renderable renderContentOn(final HtmlCanvas html) throws IOException {
        return new Renderable() {

            public void renderOn(HtmlCanvas html) throws IOException {
                
                html.h1().write("Minimal")._h1();
                html.write(StringResource.get("content/Example-minimal.html"),false);
                html.pre().write("<html><body><h1>renderSnake it</h1></body></html>")._pre();
                
                html.render(new CityTable());
                
                html.write(StringResource.get("/content/Example-Goto.html"), false);
                
                html.write(StringResource.get("/content/Example-Script.html"), false);
                
                html.write(StringResource.get("/content/Example-Library.html"), false);

                html.render(new InsideHead());

                html.render(new InsideFooter());
                
                html.render(new IconImage());
                
                html.render(new LoginForm());
                
                html.render(new IncludeExternalJavascript());
                
                html.render(SourceLink.site("ExamplesPage", "Full source of this page"));
            }
        };
    }
}
