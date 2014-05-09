package org.rendersnake.site;

import static org.rendersnake.HtmlAttributesFactory.type;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RenderableWrapper;
import org.rendersnake.site.components.SyntaxHighlighterLibary;

public class SyntaxHighlightingWrapper extends RenderableWrapper {

    public SyntaxHighlightingWrapper(Renderable component) {
        super(component);
    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException {
        html.render(new SyntaxHighlighterLibary());
        html.render(this.component);
        // highlight all examples
        html.script(type("text/javascript")).write("SyntaxHighlighter.all();",false)._script();
    }
}
