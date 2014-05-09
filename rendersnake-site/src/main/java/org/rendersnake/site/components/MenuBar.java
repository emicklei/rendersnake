package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.href;
import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class MenuBar implements Renderable {

    private String uri;
    
    public MenuBar(String currentUri) {
        super();
        this.uri = currentUri;
    }
    
    public void renderOn(HtmlCanvas html) throws IOException {//@formatter:off
        
        html
            .div(id("menubar"))
              .ul(id("menu"));
              
                for(String[] pair : this.links()) {
                    html
                        .li(class_(pair[0].equals(uri) ? "selected" : null))
                            .a(href(pair[0])).write(pair[1])._a()
                        ._li();
                }
                
                html
              ._ul()
            ._div();            
    }
    public String[][] links() {
        return new String[][]{
                {"index.html","Home"},
                {"examples.html","Examples"},
                {"devguide.html","Developer's Guide"},
                {"translator.html","Translator"},
                {"jquery.html","jQuery"}                
        };
    }
}
