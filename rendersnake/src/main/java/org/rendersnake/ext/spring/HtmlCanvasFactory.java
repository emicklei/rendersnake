package org.rendersnake.ext.spring;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.ext.servlet.HtmlServletCanvas;
import org.rendersnake.tools.DebugHtmlCanvas;
/**
 * HtmlCanvasFactory is responsible for creating HtmlCanvas objects.
 * 
 * @author e.micklei
 */
public class HtmlCanvasFactory {
    public static String INSPECT_QUERY_PARAM = "inspect";
    public static boolean DEBUG_ENABLED = true;

    public static HtmlCanvas createCanvas(HttpServletRequest request, HttpServletResponse response, Writer out) {

        HtmlCanvas html = null;
        // detect debug request
        if (DEBUG_ENABLED && (request.getParameter(INSPECT_QUERY_PARAM) != null)) {
            html = new DebugHtmlCanvas(request, response, out);
        } else {
            html = new HtmlServletCanvas(request, response, out);
        }
        return html;
    }
}
