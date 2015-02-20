package org.rendersnake.ext.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rendersnake.HtmlCanvas;
/**
 * ServletUtils is a helper class for accessing specific state of a HtmlServletCanvas.
 * 
 * @author ernestmicklei
 */
public class ServletUtils {
    /**
     * @param canvas
     * @return
     */
    public static HttpServletRequest requestFor(HtmlCanvas canvas){
        if (canvas instanceof HtmlServletCanvas) {
            return ((HtmlServletCanvas)canvas).request;
        } else {
            return null;
        }
    }
    /**
     * @param canvas
     * @return
     */
    public static HttpServletResponse responseFor(HtmlCanvas canvas){
        if (canvas instanceof HtmlServletCanvas) {
            return ((HtmlServletCanvas)canvas).response;
        } else {
            return null;
        }        
    }
    /**
     * @param canvas
     * @return
     */
    public static HttpSession sessionFor(HtmlCanvas canvas){
        if (canvas instanceof HtmlServletCanvas) {
            HttpServletRequest request = ((HtmlServletCanvas)canvas).request;
            HttpServletResponse response = ((HtmlServletCanvas)canvas).response;
            // do not create a session if response is commited (would raise exception)
            return request.getSession(!response.isCommitted());
        } else {
            return null;
        }        
    }
    /**
     * @param canvas
     * @return
     */
    public static boolean hasAjaxRequest(HtmlCanvas canvas){
        if (canvas instanceof HtmlServletCanvas) {
            return ((HtmlServletCanvas)canvas).hasAjaxRequest();
        } else {
            return false; // we don't know actually
        }   
    }
}
