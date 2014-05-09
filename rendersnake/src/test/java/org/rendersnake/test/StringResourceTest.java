package org.rendersnake.test;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.StringResource;

import junit.framework.TestCase;

public class StringResourceTest extends TestCase {

    public void testSample() {
        assertNotNull(StringResource.get("content/Sample.html"));
    }
    public void testRenderSample() throws Exception {
        HtmlCanvas html = new HtmlCanvas();
        html.render(new StringResource("content/Sample.html",false));
    }
    public void testTextSample() throws Exception {
        HtmlCanvas html = new HtmlCanvas();
        html.write(StringResource.get("content/Sample.html"),false);
    }
    public void testTextSampleNoCache() throws Exception {
        HtmlCanvas html = new HtmlCanvas();
        html.write(StringResource.get("content/Sample.html",false),false);
    }    
    public void testMissingSample() throws Exception {
        assertTrue(StringResource.get("contents/Sample.html").startsWith("[StringResource]"));
    }
    public void test404() throws Exception {
        assertTrue(StringResource.get("http://ernestmicklei.com/missing").startsWith("[StringResource]"));
    }    
}
