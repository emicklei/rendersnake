package org.rendersnake.test.debug;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.rendersnake.tools.PrettyWriter;

public class PrettyWriterTest extends TestCase {

    public void testIndent() throws IOException {
        PrettyWriter pw = new PrettyWriter();
        StringWriter sw = new StringWriter();
        pw.writer = sw;
        pw.write("<tag>");
            pw.write("<kid>");
                pw.write("<empty/>");
                pw.write("<leaf");
                pw.write(" level=\"");
                pw.write("1\" class=\"green\">");
                pw.write("hello");
                pw.write("</leaf>");
            pw.write("</kid>");        
            pw.write("<empty/>");
        pw.write("</tag>");
        pw.write(".");
        pw.flush();
        pw.close();
        System.out.println(sw);        
    }
    
    public void testString() throws IOException {
        PrettyWriter pw = new PrettyWriter();
        StringWriter sw = new StringWriter();
        pw.writer = sw;
        pw.write("<tag>");
        pw.write("content");
        pw.write("</tag>");
        assertEquals("<tag>\ncontent</tag>\n", sw.toString());
    }
    public void testString1() throws IOException {
        PrettyWriter pw = new PrettyWriter(new StringWriter());
        pw.write("<tag");
        pw.write(" a=\"");
        pw.write("b\"/>");
        assertEquals("<tag a=\"b\"/>", pw.toString());
    }    
}
