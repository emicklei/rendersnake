package org.rendersnake.site;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.site.components.IconImage;
import org.rendersnake.tools.PrettyWriter;

public class IconImageTest extends TestCase {
    public void testRender() throws IOException {
        IconImage icon = new IconImage("tooltip").alt("happy");
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter());
        html.render(icon);
        System.out.println(html.toHtml());
    }    

}
