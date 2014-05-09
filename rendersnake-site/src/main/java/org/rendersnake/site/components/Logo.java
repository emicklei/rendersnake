package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.href;
import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class Logo implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        html
            .div(id("logo"))
                .div(id("logo_text"))
                    .h1()
                        .a(href("index.html"))
                            .write("render")
                            .style().write(".snake { color:yellow; }")._style()
                            .span(class_("snake")).write("S")._span()
                            .write("nake")
                        ._a()
                    ._h1()
                    .h2().write("lean and mean HTML page writing machine")._h2()._div()
                ._div();
    }
}
