package org.rendersnake.test;

import junit.framework.TestCase;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;

public class CrossSiteScriptingTest extends TestCase {
    public void testContent() throws Exception {
        HtmlCanvas html = new HtmlCanvas();
        html.write("<&>");
        assertEquals("&lt;&amp;&gt;",html.toHtml());
    }
    public void testAttributeContent() throws Exception {
        HtmlAttributes attrs = new HtmlAttributes();
        attrs.content("<&>");
        assertEquals(" content=\"&lt;&amp;&gt;\"",attrs.toHtml());
    }
}
