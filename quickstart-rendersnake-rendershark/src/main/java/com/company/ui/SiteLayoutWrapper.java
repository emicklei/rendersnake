package com.company.ui;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RenderableWrapper;

public class SiteLayoutWrapper extends RenderableWrapper {

    public SiteLayoutWrapper(Renderable component) {
        super(component);
    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException {
        html
            .html()
                .head()
                ._head()
            .body()
            .render(this.component)
            ._body()
            ._html();
    }
}
