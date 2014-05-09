package org.rendersnake;

import java.io.IOException;
/**
 * Implementors can be rendered using a HtmlCanvas.
 * <p/>
 * An IOException is thrown when the html cannot write its tags.
 * 
 * @author ernestmicklei
 */
public interface Renderable {
    /**
     * Render the receiver using the HTML html.
     * @param html
     * @throws IOException
     */
    void renderOn(HtmlCanvas html) throws IOException;
}
