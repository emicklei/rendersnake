package org.rendersnake.site.example;

import static org.rendersnake.HtmlAttributesFactory.id;
import static org.rendersnake.HtmlAttributesFactory.onClick;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;
import org.rendersnake.site.components.SourceLink;

public class JQDialog implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        
        html
        .h1().write("jQuery - Dialog")._h1()
        .write(StringResource.get("content/Example-jquery-dialog.html"),false);
        
        html
              .div(id("dialog")).content("Welcome")              
              .a(onClick("$('#dialog').dialog()"))
                  .content("Open Dialog");
        
        html.render(SourceLink.example("JQDialog"));
    }
}
