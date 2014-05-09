package org.rendersnake.ext.servlet;

import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rendersnake.HtmlCanvas;

public class HtmlServletCanvas extends HtmlCanvas {

    public HttpServletRequest request;
    public HttpServletResponse response;    
    
    public HtmlServletCanvas(HttpServletRequest request, HttpServletResponse response, Writer out) {
        this.request = request;
        this.response = response;
        this.out = out;
    }    
    /**
     * Answer whether this html was created for an Ajax Http Request
     */
    public boolean hasAjaxRequest() {
        return request != null && request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }
    /* (non-Javadoc)
     * @see org.rendersnake.HtmlCanvas#createLocalCanvas()
     */
    @Override
    public HtmlCanvas createLocalCanvas(){
        return new HtmlServletCanvas(this.request,this.response,new StringWriter(1024));
    }    
}
