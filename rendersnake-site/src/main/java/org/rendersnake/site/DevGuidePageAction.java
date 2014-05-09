package org.rendersnake.site;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;
import org.rendersnake.site.components.GotoTop;
import org.rendersnake.site.components.SourceLink;

/**
 * 
 * @author ernestmicklei
 */
@Singleton @Named("/devguide.html")
public class DevGuidePageAction implements HttpGetHandler {

    public void get(HtmlCanvas html, HandlerResult result) throws IOException {

        html.getPageContext().withString("title", "renderSnake - Developer's Guide");

        // Use an anonymous defined component to render the examples content
        SiteLayoutWrapper layout = new SiteLayoutWrapper(new SyntaxHighlightingWrapper(this.renderContentOn(html)));
        layout.showSideBar = false;
        html.render(layout);        
    }

    /**
     * Return a component that renders the content of the dev guide page.
     * 
     * @param html
     * @return
     * @throws IOException
     */
    private Renderable renderContentOn(final HtmlCanvas html) throws IOException {
        return new Renderable() {

            public void renderOn(HtmlCanvas html) throws IOException {
                html
                .render(StringResource.valueOf("content/DevGuide.html"))
                .render(StringResource.valueOf("content/guides/canvas.html"))
                .render(new GotoTop())
                .render(StringResource.valueOf("content/guides/attributes.html"))
                .render(new GotoTop())
                .render(StringResource.valueOf("content/guides/renderable.html"))
                .render(SourceLink.folder("org.rendersnake.site.components","Browse the components for this site"))
                .br()
                .render(new GotoTop())
                .render(StringResource.valueOf("content/guides/wrapper.html"))
                .render(SourceLink.site("SiteLayoutWrapper", "Browse the layout wrapper for this site"))
                .br()
                .render(new GotoTop())
                .render(StringResource.valueOf("content/guides/pagecontext.html"))
                .render(StringResource.valueOf("content/guides/specialcontext.html"))
                .render(new GotoTop())
                .render(StringResource.valueOf("content/guides/stringresource.html"))
                .render(new GotoTop())            

                .render(StringResource.valueOf("content/guides/canvasonly.html"))
                .render(new GotoTop())
                .render(StringResource.valueOf("content/guides/jtidy.html"))
                .render(new GotoTop())
                .render(StringResource.valueOf("content/guides/componentonly.html"))

                .render(new GotoTop())            
                .render(StringResource.valueOf("content/guides/jsp.html"))
                .render(new GotoTop()) 
                .render(StringResource.valueOf("content/guides/spring.html"))
                .render(new GotoTop()) 
                .render(StringResource.valueOf("content/guides/guice.html"))
                .render(new GotoTop())                       
                ;                
            }
        };
    }
}
