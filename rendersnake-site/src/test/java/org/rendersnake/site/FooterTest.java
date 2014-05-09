package org.rendersnake.site;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.site.components.Footer;
import org.rendersnake.tools.PrettyWriter;

public class FooterTest extends TestCase {
    public void testRender() throws IOException {
        Footer ui = new Footer();
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter());
        html.render(ui);
        System.out.println(html.toHtml());
    }
}
