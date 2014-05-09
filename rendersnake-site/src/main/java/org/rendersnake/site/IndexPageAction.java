package org.rendersnake.site;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.site.components.IndexContent;

/**
 * 
 * 
 * @author ernestmicklei
 */
@Singleton @Named("/index.html")
public class IndexPageAction implements HttpGetHandler {

	public void get(HtmlCanvas html, HandlerResult result) throws IOException {
        html.getPageContext().withString("title","renderSnake - Home");
        
        // Use a separate component for rendering the index content
        html.render(new SiteLayoutWrapper(new IndexContent()));		
	}
}
