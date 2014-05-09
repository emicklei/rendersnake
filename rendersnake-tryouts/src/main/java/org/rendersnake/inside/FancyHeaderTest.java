package org.rendersnake.inside;

import org.junit.Test;
import org.rendersnake.HtmlCanvas;

public class FancyHeaderTest {
    @Test
    public void test() throws Exception {
        
        HtmlCanvas html = new HtmlCanvas();
        html
            .render(new FancyHeader.Before())
            .write("Hello")
            .render(new FancyHeader.After());
        
        System.out.println(html.toHtml());
    }
}
