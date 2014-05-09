package org.rendersnake.element;

import static org.rendersnake.HtmlAttributesFactory.src;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

/**
 * Script is a HTML element.
 * 
 * <p>
 * The placing of JavaScript in the above location differs in the timing of
 * their execution. JavaScript placed in the HEAD section of HTML will be
 * executed when called whereas, JavaScript placed in the BODY section of HTML
 * will be executed only when the page is loaded.
 * </p>
 * 
 * @author ernest
 **/
public class Script implements Renderable {

    public String src;
    public String type = "text/javascript";
    public String javascript;

    public Script(String src) {
        this.src = src;
    }

    public static Script external(String src) {
        return new Script(src);
    }

    public static Script source(String source) {
        Script spt = new Script(null);
        spt.javascript = source;
        return spt;
    }

    public void renderOn(HtmlCanvas canvas) throws IOException {
        canvas.script(src(src).type(this.type))._script();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Script))
            return false;
        Script otherScript = (Script) other;
        if (src != null)
            return src.equals(otherScript.src);
        return false;
    }

    @Override
    public String toString() {
        try {
            return new HtmlCanvas().render(this).toHtml();
        } catch (Exception ex) {
            return "*** error in Script>>toString() ***";
        }
    }
}
