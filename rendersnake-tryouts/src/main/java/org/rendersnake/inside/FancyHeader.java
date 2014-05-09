package org.rendersnake.inside;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import static org.rendersnake.HtmlAttributesFactory.*;

public class FancyHeader {
    
    public static class Before implements Renderable {
        public void renderOn(HtmlCanvas html) throws IOException {
            html.h1(style("font-style:italic"));
        }        
    }
    public static class After implements Renderable {
        public void renderOn(HtmlCanvas html) throws IOException {
            html._h1();
        }
    }    
}
