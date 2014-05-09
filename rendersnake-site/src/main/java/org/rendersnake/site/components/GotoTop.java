package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.href;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class GotoTop implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        html.a(href("#top").class_("toplink")).write("top")._a();
    }
}
