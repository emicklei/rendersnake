package org.rendersnake.site;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.site.components.Sidebar;
import org.rendersnake.tools.PrettyWriter;

public class SidebarTest extends TestCase {
    public void testRender() throws IOException {
        Sidebar sb = new Sidebar();
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter());
        html.render(sb);
        System.out.println(html.toHtml());
    }
}
