package org.rendersnake;

import java.io.IOException;

public class ConditionalWrapper extends RenderableWrapper {
    private boolean mustRender;
    
    public ConditionalWrapper(Renderable component,boolean condition) {
        super(component);
        this.mustRender = condition;
    }
    @Override
    public void renderOn(HtmlCanvas html) throws IOException {
        if (mustRender) html.render(this.component);
    }
}
