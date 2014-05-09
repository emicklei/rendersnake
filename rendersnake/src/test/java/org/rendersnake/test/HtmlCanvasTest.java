package org.rendersnake.test;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.error.RenderException;

public class HtmlCanvasTest extends TestCase {

    private HtmlCanvas html;

    public void setUp() {
        html = new HtmlCanvas();
    }
    
    public void testIfTrue() throws Exception {
        html.if_(true).br()._if().p()._p();
        Assert.assertEquals("<br/><p></p>",html.toHtml());
    }
    public void testIfFalse() throws Exception {
        html.if_(false).br()._if().p()._p();
        Assert.assertEquals("<p></p>",html.toHtml());
    }

    public void testCdata() throws Exception {
        html.macros().cdata("function onLoad(){};");
        assertEquals("/*<![CDATA[*/function onLoad(){};/*]]>*/",html.toHtml());
    }
    
    public void testNextId() {
        assertEquals("id1", html.nextId());
        assertEquals("id2", html.nextId());
    }
    
    public void testSelfClosingTag_input() throws Exception {
        html.input();
        assertEquals("<input/>", html.toHtml());
    }    
    public void testSelfClosingTag_input_attrs() throws Exception {
        html.input(html.attributes().type("text"));
        assertEquals("<input type=\"text\"/>", html.toHtml());
    }    

    public void testHasPageContext(){
        html.getPageContext();
        assertTrue(html.hasPageContext());
    }

    public void testMacros() {
        assertNotNull(html.macros());
    }
    
    public void testHasNoPageContext(){
        assertFalse(html.hasPageContext());
    }
    
    
    public void testSelfClosingTag_br() throws Exception {
        html.br();
        assertEquals("<br/>", html.toHtml());
    }    
    
    
    public void testTagEmpty() throws Exception {
        html.tag("tag");
        assertEquals("<tag></tag>", html.close().toHtml());
    }

    public void testTagAttr1() throws Exception {
        html.tag("tag", html.attributes().id("ident"));
        assertEquals("<tag id=\"ident\"></tag>", html.close().toHtml());
    }

    // Failures
    public void testError() throws Exception {
        try {
            html.tag(null);
            fail("should detect NP");
        } catch (RenderException ex) {
            // gotit
        }
    }

    public void testError2() throws Exception {
        try {
            html.tag(null, null);
            fail("should detect NP");
        } catch (RenderException ex) {
            // gotit
        }
    }
    
    public void testTag() throws IOException {
        html.tag("some");
        html.tag_close("some");
        assertEquals("<some></some>", html.toHtml());
    }

    public void testTextNoEscapeNeeded() throws IOException {
        html.write("music");
        assertEquals("music", html.toHtml());
    }

    public void testTextEscape() throws IOException {
        html.write("<music>");
        assertEquals("&lt;music&gt;", html.toHtml());
    }
    public void testTextEscapeExplicit() throws IOException {
        html.write("<music>",true);
        assertEquals("&lt;music&gt;", html.toHtml());
    }    
    public void testTextNoEscape() throws IOException {
        html.write("<music>",false);
        assertEquals("<music>", html.toHtml());
    }    

    public void testCloseWrongTag() throws IOException {
        html.div();
        try {
            html.close("</body>");
            fail("Expected to see an exception because of wrong close tag");
        } catch (RenderException ex) {
            // got it
        }
    }

    public void testCloseOnEmpty() throws IOException {
        try {
            html.close();
            fail("Expected to see an exception because of wrong close tag");
        } catch (RenderException ex) {
            // got it
        }
    }    
    
    public void testCloseOnEmptyWithTag() throws IOException {
        try {
            html.close("</stop>");
            fail("Expected to see an exception because of wrong close tag");
        } catch (RenderException ex) {
            // got it
        }
    }     
    


    public void testCreateWithWriter() throws Exception {
        StringWriter out = new StringWriter(1024);
        HtmlCanvas html = new HtmlCanvas(out);
        html.write("hello");
        assertEquals(out.toString(), "hello");
    }
}