package org.rendersnake.tools;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class PrettyWriter extends Writer {

    public Writer writer;
    public int indentLevel = 0;
    private boolean inString = false;
    private boolean lastCharWasSlash = false;    

    public PrettyWriter() {
        super();
        this.writer = new StringWriter(1024);
    }    
    public PrettyWriter(Writer wrapped) {
        super();
        this.writer = wrapped;
    }
    /**
     * Answer a pretty formatted HTML output for a Renderable component.
     * @param component
     * @return
     */
    public static String toString(Renderable component) {
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter());
        try {
            component.renderOn(html);
        } catch (Exception ex){
            throw new RuntimeException("Error writing HTML for component:" + component);
        }
        return html.toHtml();
    }
    
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if (len < 2) {
            if (lastCharWasSlash && cbuf[0]=='>') indentLevel--;
            lastCharWasSlash = cbuf[0]=='/';
            writer.write(cbuf, off, len);
            return;
        }
        if (cbuf[off] == '<') {
            // open or close
            if (cbuf[off + 1] == '/') {
                // close
                indentLevel--;
                this.doIndent(indentLevel);
                writer.write(cbuf, off, len);
                writer.write('\n');
                return;
            } else if (cbuf[off + len - 2] == '/') {
                // empty
                this.doIndent(indentLevel);
                writer.write(cbuf, off, len);
                writer.write('\n');
                return;
            } else if (cbuf[off + len - 1] == '>') {
                // end open 
                this.doIndent(indentLevel);
                indentLevel++;
                writer.write(cbuf, off, len);
                this.checkQuotes(cbuf,off, len);
                this.doNewLine();
                return;                
            } else {
                // open open
                this.doIndent(indentLevel);
                indentLevel++;
                writer.write(cbuf, off, len);
                return;
            }
        } else if (cbuf[off + len - 2] == '/') {
            // empty            
            writer.write(cbuf, off, len);
            indentLevel--;
            this.doIndent(indentLevel);
            return;
        }
        this.checkQuotes(cbuf, off, len);
        writer.write(cbuf, off, len);        
    }

    private void doIndent(int level) throws IOException {
        if (inString || level == 0)
            return;
        this.doNewLine();
        for (int i = 0; i < level; i++) {
            writer.write('\t');
        }
    }

    private void doNewLine() throws IOException {
        if (!inString)
            writer.write('\n');
    }

    private void checkQuotes(char[] cbuf, int off, int len) {
        // count number of quotes
        int quotes = 0;
        for (int i = 0; i < len; i++) {
            quotes += cbuf[i+off] == '"' ? 1 : 0;
        }
        inString = inString ? quotes % 2 == 0 : quotes % 2 == 1;
    }

    @Override
    public void flush() throws IOException {
        writer.flush();

    }

    @Override
    public void close() throws IOException {
        writer.close();

    }

    public String toString() {
        return writer.toString();
    }
}
