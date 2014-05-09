package org.rendersnake.test;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.error.RenderException;

import junit.framework.TestCase;

public class RenderExceptionTest extends TestCase {

    public class CloseOnEmpty implements Renderable {

        public void renderOn(HtmlCanvas html) throws IOException {
            html.close();            
        }        
    }
    
    public class ErrorInRender implements Renderable {

        public void renderOn(HtmlCanvas html) throws IOException {
            throw RenderException.caught(new RuntimeException("test"));     
        }        
    }    
    
    public class WriteNullTag implements Renderable {

        public void renderOn(HtmlCanvas html) throws IOException {
            html.tag(null);        
        }        
    }     
    
    public void testEmpty() throws IOException {
                
        HtmlCanvas html = new HtmlCanvas();
        try { 
            html.render(new CloseOnEmpty());
            fail("should raise render exception");
        } catch (RenderException rex) {
            assertTrue(rex.isEmptyStack);
            html.render(rex);
            assertTrue(html.toHtml().length() > 0);
            System.out.println(rex.toString());
        }
    } 
    public void testNull() throws IOException {
        
        HtmlCanvas html = new HtmlCanvas();
        try { 
            html.render(new WriteNullTag());
            fail("should raise render exception");
        } catch (RenderException rex) {
            assertTrue(rex.isNullTag);
            html.render(rex);
            assertTrue(html.toHtml().length() > 0);
            System.out.println(rex.toString());
        }
    }
    public void testCaught() throws IOException {
        
        HtmlCanvas html = new HtmlCanvas();
        try { 
            html.render(new ErrorInRender());
            fail("should raise render exception");
        } catch (RenderException rex) {
            assertFalse(rex.isEmptyStack);
            html.render(rex);
            assertTrue(html.toHtml().length() > 0);
            System.out.println(rex.toString());
        }
    }    
}
