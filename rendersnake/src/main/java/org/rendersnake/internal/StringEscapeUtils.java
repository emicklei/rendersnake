package org.rendersnake.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rendersnake.HtmlCanvas;


/**
 * StringEscapeUtils is a helper class that can write a String with XML escaping.
 * 
 * @author ernest
 */
public class StringEscapeUtils implements HtmlEscapeHandler {
    private static final Logger LOG = Logger.getLogger("org.rendersnake.internal");
    
    private StringEscapeUtils() {}

    public static void init() {
        // Try to setup the HtmlEscapeHandler for HtmlCanvas
        // Log a warning if the apache commons handler is not available.
        // In that case, you must provide your own implementation to the HtmlCanvas.HTML_ESCAPE_HANDLER static field.
        try {
            Class.forName("org.apache.commons.lang3.StringEscapeUtils");
            HtmlEscapeHandler soleInstance = (HtmlEscapeHandler) Class.forName("org.rendersnake.ext.apache.CommonsHtmlEscapeHandler").newInstance();
            HtmlCanvas.HTML_ESCAPE_HANDLER = soleInstance;
        } catch (ClassNotFoundException e) {
            LOG.warning("Unable to use org.apache.commons.lang.StringEscapeUtils, make sure to initialize the HtmlCanvas.HTML_ESCAPE_HANDLER static field.");
        } catch (InstantiationException e) {
            LOG.log(Level.SEVERE,"Unable to install org.rendersnake.ext.apache.CommonsHtmlEscapeHandler",e);
        } catch (IllegalAccessException e) {
            LOG.log(Level.SEVERE,"Unable to install org.rendersnake.ext.apache.CommonsHtmlEscapeHandler",e);
        }
    }

    public void escapeHtml(Writer out, String text) throws IOException {
        // Probably insufficient ; use Apache Commons Lang or other library.
        this.escapeXml(out, text);        
    }

    public void escapeXml(Writer out, String text) throws IOException {
        /**
         * See google groups
         * http://groups.google.com/groups?q=java+escape+xml&start=10&hl=en&lr=&ie=UTF-8&selm=JPyaOQm2Fo7O2PeLfDykYBAsjKGw%404ax.com&rnum=12
         */
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int v = (int) c;
            if (v < 32 || v > 127 || v == 38 || v == 39 || v == 60 || v == 62 || v == 34) {
                // we must escape a character in format &#22;
                // 38 is ampersand &
                // 60 is smaller <
                // 62 is larger >
                // 34 is quote "
                // 39 is apos '
                out.append('&').append('#').append(Integer.toString(v,10)).append(';');
            } else {
                out.append(c);
            }
        }           
    }

    public void escapeEcmascript(Writer out, String text) throws IOException {
        /**
         * http://www.squarefree.com/securitytips/web-developers.html
         */
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int v = (int) c;
            if (v == 92 || v == 47 || v == 34 || v == 39) {
                // we must escape a character in format &#22;
                // 92 is \
                // 47 is /
                // 34 is quote "
                // 39 is apos '
                out.append('&').append('#').append(Integer.toString(v,10)).append(';');
            } else {
                out.append(c);
            }
        }       
    }   
    // http://www.fiveanddime.net/HTMLescapeCodes.html
    public static void escapeISOCharacters(Writer out, String input) throws IOException {
        for (int i=0;i<input.length();i++) {
            char each = input.charAt(i);
            int v = (int) each;
            if (v < 32 || v > 127) {
                out.append('&').append('#').append(Integer.toString(v,10)).append(';');
            } else {
                out.append(each);
            }
        }
    }
} 
