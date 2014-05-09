package org.rendersnake.site.example;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;

public class IncludeExternalJavascript implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        
        html
            .h1().write("Include static content")._h1()
            
            .render(StringResource.valueOf("content/Example-javascript.html"));
    }
}
