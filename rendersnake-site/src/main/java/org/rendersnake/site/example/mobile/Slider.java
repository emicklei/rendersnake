package org.rendersnake.site.example.mobile;

import java.io.IOException;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

/**
 * <pre>
 * new Slider(0,50,100).id("slider").name("slider");
 * 
 * &lt;input type="range" name="slider" selectId="slider" value="0" min="0" max="100" /&gt;
 * </pre>
 * 
 * @author emicklei
 */
public class Slider extends HtmlAttributes implements Renderable {

    public Slider(int min, int value, int max) throws IOException {
       super();
       this.min(min).value(value).max(max).type("range");
    }

    public void renderOn(HtmlCanvas html) throws IOException {
        html.input(this);
    }
}
