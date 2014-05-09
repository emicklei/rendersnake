package org.rendersnake.site;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.site.components.MenuBar;
import org.rendersnake.tools.PrettyWriter;

public class MenuBarTest extends TestCase {
    public void testRender() throws IOException {
        MenuBar mb = new MenuBar("examples.html");
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter());
        html.render(mb);
        System.out.println(html.toHtml());
    }
}
