package org.rendersnake.test;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.rendersnake.internal.WriteBuffer;

public class WriteBufferTest extends TestCase {
    private WriteBuffer wb;
    
    public void setUp(){
        wb = new WriteBuffer();
    }
    public void testEmpty(){
        assertEquals("", wb.toString());
        assertEquals(0,wb.length());
    }
    public void testReset(){
        wb.append("some");
        wb.reset();
        assertEquals("", wb.toString());
        assertEquals(0,wb.length());
    }    
    public void testChar(){
        wb.append('?');
        assertEquals("?", wb.toString());
        assertEquals(1,wb.length());
    }
    public void testString(){
        wb.append("42");
        assertEquals("42", wb.toString());
        assertEquals(2,wb.length());
    }
    public void testString_2(){
        wb.append("42");
        wb.append("=it");
        assertEquals("42=it", wb.toString());
    }     
    public void testString_char(){
        wb.append("42");
        wb.append('!');
        assertEquals("42!", wb.toString());
    }   
    public void testGrow(){
        for (int c=0;c<256;c++){
            wb.append("!");
        }
        wb.append('!');        
    } 
    public void testGrowTwice(){
        for (int c=0;c<256*2;c++){
            wb.append("!");
        }
        wb.append('!');     
        assertEquals(256*2+1,wb.length());
    }   
    public void testFlushOn() throws IOException {
        StringWriter sw = new StringWriter();
        wb.append("hello world");
        wb.writeCharsOn(sw);
        assertEquals(wb.toString(), sw.toString());
    }
}
