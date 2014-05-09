package org.rendersnake.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;

import org.rendersnake.HtmlCanvas;

public class GoogleWikiMarkupProcessor {
    boolean inList = false;
    boolean inPre = false;
    
    public void process(String markup, Writer out) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(markup));
        while (reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("{{{")) {
                out.append("<pre>");
                inPre = true;
            } else if (line.startsWith("}}}")) {
                out.append("</pre>");
            } else if (line.startsWith("#summary")) {
                // skip
            } else {
                if (inPre) {
                    HtmlCanvas.HTML_ESCAPE_HANDLER.escapeHtml(out,line);
                } else {
                    out.append(line);
                }
                out.append('\n');
            }
        }
    }
}
