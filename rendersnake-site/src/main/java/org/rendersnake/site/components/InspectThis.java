package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.href;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class InspectThis implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        html
            .div(class_("inspect"))
                .a(href("?inspect")).write("&nbsp;inspect this page",false)._a()
            ._div();
    }
}
