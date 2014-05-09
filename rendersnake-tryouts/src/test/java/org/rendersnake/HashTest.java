package org.rendersnake;

import junit.framework.TestCase;

import org.rendersnake.Hash;

public class HashTest extends TestCase {

    
    public void testEscape(){
        Hash h = new Hash("key","<&>");
        assertEquals(" key=\"&lt;&amp;&gt;\"", h.asCharSequence());
    }
    public void testFail(){
        try {
            new Hash("key");
            fail("should raise ex");
        } catch (IllegalArgumentException iex) {
            assertTrue(true);
        }
        
    }
    public void testJavascript(){
        Hash h = new Hash("key","value");
        assertEquals("{key:'value'}", h.toJavascript());
    }
    public void testMultiKey(){
        Hash h = new Hash("key","value","key2","value2");
        assertTrue(h.toJavascript().indexOf("value2") != -1);
    }
    public void testJavascriptBoolean(){
        Hash h = new Hash("bool",true);
        assertEquals("{bool:true}", h.toJavascript());
    }    
    public void testJavascriptInt(){
        Hash h = new Hash("int",42);
        assertEquals("{int:42}", h.toJavascript());
    }    
}
