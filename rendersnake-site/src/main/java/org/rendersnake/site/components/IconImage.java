package org.rendersnake.site.components;

import java.io.IOException;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class IconImage implements Renderable {

    public HtmlAttributes attributes = new HtmlAttributes();
    
    public IconImage(String name) throws IOException {
        super();
        this.attributes.src("/static/images/icons/" + name);
        this.attributes.width("16");
        this.attributes.height("16");
    }
    public void renderOn(HtmlCanvas html) throws IOException {
        html.img(attributes);        
    }
    public IconImage alt(String alt) throws IOException {
        this.attributes.alt(alt);
        return this;
    }
}