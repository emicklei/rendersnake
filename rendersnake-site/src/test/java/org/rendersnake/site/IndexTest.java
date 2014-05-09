package org.rendersnake.site;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.tools.PrettyWriter;

public class IndexTest extends TestCase {
    public void testRender() throws IOException {
        
        IndexPageAction index = new IndexPageAction();
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter(new StringWriter()));
        index.get(html, null);
        
        System.out.println(html.toHtml());
    }
}
