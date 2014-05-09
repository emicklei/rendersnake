package org.rendersnake.site;

import static org.rendersnake.HtmlAttributesFactory.href;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.site.ipad.AnatomyView;
/**
 * 
 * @author ernestmicklei
 */
@Singleton @Named("/jquery.html")
public class ExamplesJQueryPageAction implements HttpGetHandler {

    public void get(HtmlCanvas html, HandlerResult result) throws IOException {
        
        html.getPageContext().withString("title","renderSnake - Examples JQuery");
        
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
                
                html
                    .h3().content("JQuery")
                    .p().content("The classes HtmlAttributes, HtmlAttributesFactory and JQueryLibrary " +
                    		"provide convenience methods for attributes to build pages that use JQuery and JQuery Mobile construts.");                    
                          
                html.render(new AnatomyView());
                         
                html
                    .h1().content("jQuery Mobile - Minimal")
                    .a(href("mobile.html")).content("minimal mobile page");
                
                html
                    .h1().content("jQuery Mobile - Widgets")
                    .a(href("ipad.html")).content("jQuery Mobile widgets");
            }
        };
    }
}
