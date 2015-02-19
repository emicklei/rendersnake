package org.rendersnake.internal;

import java.io.IOException;
import java.io.Writer;
/**
 * HtmlEscapeHandler can apply standard HTML4 escaping for writing a String on an io.Writer.
 * 
 * @author emicklei
 */
public interface HtmlEscapeHandler {
    
    /**
     * @param out
     * @param text
     * @throws IOException
     */
    void escapeHtml(Writer out, String text) throws IOException;
}
