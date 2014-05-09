package org.rendersnake;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.rendersnake.element.Script;
import org.rendersnake.tools.PrettyWriter;

public class HtmlBufferingCanvasTest extends TestCase {

    public void test() throws Exception {
        HtmlBufferingCanvas buf = new HtmlBufferingCanvas(null,null,new StringWriter(1024));
        buf.out = new PrettyWriter(buf.out);
        
        // add something to the head
        buf.headAttributes().add("some", "hot");
        buf.headElements().add(Script.external("loaded-in-top-head.js"));

        buf.body();
        buf.h1().write("Buffer test")._h1();
        // add something to the body
        buf.bodyElements().add(Script.external("called-in-bottom-body.js"));
        // add a load script
        buf.onLoadScripts().add("alert('hi');");
        buf._body();

        System.out.println(buf.toHtml());
    }
    
    public void testUnbuffered() throws IOException {
        HtmlBufferingCanvas buf = new HtmlBufferingCanvas(null,null,new StringWriter(1024));
        
        buf.html().body().h2().write("Unbuffered use")._h2()._body()._html();

        System.out.println(buf.toHtml());
    }    
}
