package org.rendersnake.test;

import java.io.StringWriter;

import junit.framework.TestCase;

import org.rendersnake.ext.servlet.HtmlServletCanvas;

public class HtmlServletCanvasTest extends TestCase {
    public void testCreateWithReqResp() {
        HtmlServletCanvas html = new HtmlServletCanvas(new MockHttpRequest(), new MockHttpResponse(), new StringWriter());
        assertNotNull(html.request);
        assertNotNull(html.response);
        assertNotNull(html.toHtml());
        assertTrue(html.toHtml().length() == 0);
    }
}
