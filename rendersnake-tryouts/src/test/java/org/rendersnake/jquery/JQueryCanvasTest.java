package org.rendersnake.jquery;

import static org.rendersnake.jquery.JQueryCanvasFactory.*;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.Hash;

public class JQueryCanvasTest extends TestCase {

    public void testDollar() {
        JQueryCanvas jq = $("row").addClass("hidden");
        assertEquals("$('row').addClass('hidden');", jq.toJavascript());
    }

    public void testThis() throws IOException {
        JQueryCanvas jq = new JQueryCanvas();
        jq.jQuery("span", "this");
        assertEquals("$(\'span\',this);", jq.toJavascript());
    }
    
    // public JQueryCanvas $() { return new JQueryCanvas(); }
    
    public void testSetup() {
        JQueryCanvas jc = $().ajaxSetup(new Hash("cache",false));
        System.out.println(jc.toJavascript());
    }
}
