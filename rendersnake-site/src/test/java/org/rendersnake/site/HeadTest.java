package org.rendersnake.site;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.site.components.Head;
import org.rendersnake.tools.PrettyWriter;

public class HeadTest extends TestCase {
    public void testRender() throws IOException {
        Head head = new Head();
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter());
        html.render(head);
        System.out.println(html.toHtml());
    }
}
