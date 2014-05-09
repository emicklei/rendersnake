package org.rendersnake.site.components;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;

public class IndexContent implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {

        html.write(StringResource.get("content/Introduction.html"),false);
        html.write(StringResource.get("content/Promobox.html"),false);
    }
}
