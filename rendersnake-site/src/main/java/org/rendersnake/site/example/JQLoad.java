package org.rendersnake.site.example;

import static org.rendersnake.HtmlAttributesFactory.id;
import static org.rendersnake.HtmlAttributesFactory.onClick;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;
import org.rendersnake.site.components.SourceLink;

public class JQLoad implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        
        html
        .h1().write("jQuery - Load")._h1()
        .write(StringResource.get("content/Example-jquery-load.html"),false);
        
        html
              .div(id("replace"))
                  .content("Click below and watch me being replaced")
              .a(onClick("$('#replace').load('org.rendersnake.site.example.JQLoad$Fragment')"))
                  .content("Asynchronously load a HTMl fragment");

        html.render(SourceLink.example("JQLoad"));
    }
    
    public static class Fragment implements Renderable {

        public void renderOn(HtmlCanvas html) throws IOException {
            
            html.h1().write("This fragment was loaded using Ajax")._h1();            
        }        
    }
}
