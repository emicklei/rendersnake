package org.rendersnake.site;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;

public class ContentTest extends TestCase {

    private HtmlCanvas html;
    
    public void setUp() { html = new HtmlCanvas(); }
    
    public void testNestedContent() throws Exception{
        html.td().td().content("one").content("two");
        System.out.println(html.toHtml());
        
    }
}
