package org.rendersnake.element;

import static org.rendersnake.HtmlAttributesFactory.href;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class Link implements Renderable {

    public String href;
    public String rel = "stylesheet";
    public String type = "text/css";
    
    public Link(String href) {
        this.href = href;
    }
    
    public void renderOn(HtmlCanvas canvas) throws IOException {
        canvas.link(href(href).type(this.type).rel(rel));
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Link)) return false;
        Link otherLink = (Link)other;
        if (href != null) return href.equals(otherLink.href);
        return false;
    }
    @Override
    public String toString() {
        try {
            return new HtmlCanvas().render(this).toHtml();
        } catch (Exception ex) {
            return "*** error in Link>>toString() ***";
        }
    }    
}
