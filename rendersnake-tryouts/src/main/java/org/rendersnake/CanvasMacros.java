package org.rendersnake;

import java.io.IOException;
import java.io.StringWriter;

@SuppressWarnings("unchecked")
public class CanvasMacros<T extends HtmlCanvas> {
    
    public static void main(String[] args) throws IOException {
        HtmlBufferingCanvas c = new HtmlBufferingCanvas(null,null,new StringWriter());
        
        c.macros().script("Help").macros().li("item");
        
        System.out.println(c.getOutputWriter().toString());
    }
    
    private T canvas;
    
    public CanvasMacros(T canvas) {
        super();
        this.canvas = canvas;
    }        
    public T script(String code) throws IOException {
        return (T)canvas.script().write(code)._script();
    }
    public T li(String item) throws IOException {
        return (T)canvas.li().write(item)._li();
    }
}
