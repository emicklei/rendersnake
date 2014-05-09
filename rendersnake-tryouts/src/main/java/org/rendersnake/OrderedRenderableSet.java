package org.rendersnake;

import java.util.ArrayList;
import java.util.List;
/**
 * OrderedRenderableSet is an ordered collection of unique Renderable objects.
 * @author ernest
 */
public class OrderedRenderableSet {
    public List<Renderable> renderables = new ArrayList<Renderable>();
    
    public OrderedRenderableSet add(Renderable renderable) {
        if (renderables.indexOf(renderable) != -1) return this;
        renderables.add(renderable);
        return this;
    }
    
    public List<Renderable> getRenderables() { return renderables; }
}
