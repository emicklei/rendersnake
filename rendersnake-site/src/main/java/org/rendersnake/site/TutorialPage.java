package org.rendersnake.site;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;
/**
 * 
 * @author ernestmicklei
 */
public class TutorialPage implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        
        html.getPageContext().withString("title","renderSnake - Tutorial");

        // Use an anonymous defined component to render the examples content
        html.render(new SiteLayoutWrapper(this.renderContentOn(html)));
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
                html.write(StringResource.get("content/Tutorial.html"),false);
            }
            
        };
    }
}
