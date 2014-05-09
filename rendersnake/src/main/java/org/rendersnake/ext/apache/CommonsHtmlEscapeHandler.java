package org.rendersnake.ext.apache;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang3.StringEscapeUtils;
import org.rendersnake.internal.HtmlEscapeHandler;
/**
 * CommonsHtmlEscapeHandler is an HtmlEscapHandler that uses the apache commons lang library.
 * 
 * @author emicklei
 */
public class CommonsHtmlEscapeHandler implements HtmlEscapeHandler{

    public void escapeHtml(Writer out, String text) throws IOException {
        out.append(StringEscapeUtils.escapeHtml4(text));        
    }
}
