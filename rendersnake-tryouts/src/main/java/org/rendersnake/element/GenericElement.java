package org.rendersnake.element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class GenericElement implements Renderable {

    public String name;
    public HtmlAttributes attributes = new HtmlAttributes();
    public List<Renderable> children = new ArrayList<Renderable>();
    
    public GenericElement(String tagName) {
        this.name = tagName;
    }
    
    public void renderOn(HtmlCanvas canvas) throws IOException {
        canvas.tag(this.name,this.attributes);
        for (Renderable each : children) canvas.render(each);
        canvas.close(this.name);
    }
}
