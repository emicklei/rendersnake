package org.rendersnake.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.PageContext;
import org.rendersnake.internal.ContextMap;
import org.rendersnake.internal.SimpleContextMap;
import org.rendersnake.tools.Inspector;

public class PageContextTest extends TestCase {

    private PageContext ctx;
    
    public void setUp() {
        ctx = new PageContext();
    }
    public void testClearKey() {
        ctx.withBoolean("bool", true);
        assertEquals(ctx.getBoolean("bool"),Boolean.TRUE);
        ctx.clear("bool");
        assertEquals(ctx.getBoolean("bool"),(Boolean)null);        
    }
    public void testOptionalBoolean() {
        assertTrue(ctx.getBoolean("missing", true));
    }
    public void testMissingBoolean() {
        assertNull(ctx.getBoolean("missing"));
    }    
    public void testBoolean(){
        ctx.withBoolean("boolean", true);
        assertEquals(new Boolean(true), ctx.getBoolean("boolean"));
    }
    public void testInt(){
        ctx.withInteger("int", 42);
        assertEquals(new Integer(42), ctx.getInteger("int"));
    }
    public void testString(){
        ctx.withString("hello", "world");
        assertEquals("world", ctx.getString("hello"));
    }
    public void testObject(){
        Date now = Calendar.getInstance().getTime();
        ctx.withObject("now",now);
        assertEquals(now, ctx.getObject("now"));
    }
    public void testGetStringWithOptionalNull() {
        assertNull(ctx.getString("missing", null));
    }
    public void testGetIntWithOptionalNull() {
        assertNull(ctx.getInteger("missing", null));
    }
    public void testGetLonWithOptionalNull() {
        assertNull(ctx.getLong("missing", null));
    }
    public void testGetBooleanWithOptionalNull() {
        assertNull(ctx.getBoolean("missing", null));
    }
    public void testGetFloatWithOptionalNull() {
        assertNull(ctx.getFloat("missing", null));
    }
    public void testGetWithOptionalNull() {
        assertNull(ctx.getObject("missing", null));
    }    
    public void testNullKeyString(){
        try {
            ctx.withObject(null,"null");
            fail("should raise exception");            
        } catch (IllegalArgumentException ex) {
            // got it
        }
    }
    public void testNullKeyInt(){
        try {
            ctx.withInteger(null,42);
            fail("should raise exception");            
        } catch (IllegalArgumentException ex) {
            // got it
        }
    }
    public void testNullKeyBoolean(){
        try {
            ctx.withBoolean(null,true);
            fail("should raise exception");            
        } catch (IllegalArgumentException ex) {
            // got it
        }
    }
    public void testNullKeyObject(){
        try {
            ctx.withObject(null,new ClassNotFoundException());
            fail("should raise exception");            
        } catch (IllegalArgumentException ex) {
            // got it
        }
    }  
    public void testRender() throws Exception {
        HtmlCanvas html = new HtmlCanvas();
        ctx.renderForErrorOn(html);
        ctx.renderForInpectorOn(new Inspector(new PersonalPage()), html);
        assertFalse(html.toHtml().length() == 0);
    }
    public void testPutInteger() {
        ctx.withString("size", "");
        Integer integer = ctx.getInteger("size");
        System.out.println(integer);
    }
    public void testAccess(){
        ctx.withObject("map", new SimpleContextMap());
        ContextMap map = ctx.getContextMap("map");
        map.withString("s","s");
        // TODO cannot use cascading anymore, fix it
        map.withBoolean("b", true);
    }
}
