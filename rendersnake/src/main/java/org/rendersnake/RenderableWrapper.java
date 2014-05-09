package org.rendersnake;

import java.io.IOException;

/**
 * RenderableWrapper is an abstract class to create decorator components such as
 * a site layout. Because RenderableWrapper itself implements Renderable,
 * decoration can be nested.
 * 
 * <p/>
 * Example usage:
 * 
 * <pre>
 * class RedBorderWrapper extends RenderableWrapper {
 *      ...
 *      public void renderOn(HtmlCanvas html) throws IOException {
 *              canvas
 *                  .div(class_("red-border"))
 *                      .render(this.component)
 *                  ._div();
 *      }
 * }
 * </pre>
 * 
 * 
 * @author ernestmicklei
 */
public abstract class RenderableWrapper implements Renderable {
    /**
     * The wrapped component. Cannot be null.
     */
    protected Renderable component; 

    public RenderableWrapper(Renderable component) {
        if (null == component)
            throw new IllegalArgumentException("Attempt to create a wrapper on a null component");
        this.component = component;
    }

    /**
     * see {@link Renderable}
     */
    abstract public void renderOn(HtmlCanvas html) throws IOException;
}
