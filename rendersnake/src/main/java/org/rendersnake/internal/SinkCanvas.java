package org.rendersnake.internal;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.PageContext;
import org.rendersnake.Renderable;
/**
 * SinkCanvas is a canvas that ignores all render operations.
 * 
 * @author ernest micklei
 */
public class SinkCanvas extends HtmlCanvas {
    private HtmlCanvas parentCanvas;
    public SinkCanvas(HtmlCanvas parent) {
        super();
        this.parentCanvas = parent;
        // create new context for local changes to ignore
        this.getPageContext().attributes.push();
    }
    public HtmlCanvas _if() throws IOException {
        // forget all changes made to the context
        this.getPageContext().attributes.pop();
        return parentCanvas;
    }
    public PageContext getPageContext() { return parentCanvas.getPageContext(); }
    public String nextId() { return parentCanvas.nextId(); }
    public HtmlCanvas render(Renderable component) { return this; }
    public HtmlCanvas _render_inside() { return this; }
}
