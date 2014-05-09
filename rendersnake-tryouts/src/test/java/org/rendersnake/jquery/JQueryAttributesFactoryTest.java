package org.rendersnake.jquery;

import static org.rendersnake.HtmlAttributesFactory.xmlns;
import static org.rendersnake.jquery.JQueryCanvasFactory.$;

import java.io.IOException;

import junit.framework.TestCase;

public class JQueryAttributesFactoryTest extends TestCase {

    public void testDollar() {
        JQueryCanvas jqc = $("this");
        assertEquals(JQueryCanvas.class, jqc.getClass());
        assertEquals("$('this');", jqc.toJavascript());
    }

    public void testDollarContext() {
        JQueryCanvas jqc = $("this", "ctx");
        assertEquals(JQueryCanvas.class, jqc.getClass());
        assertEquals("$('this',ctx);", jqc.toJavascript());
    }    
    
    public void testXmlns() throws IOException {
        assertEquals(" xmlns=\"ns\"", xmlns("ns").toHtml());
    }
       
}
