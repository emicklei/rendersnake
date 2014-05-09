package org.rendersnake.ext.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * PostHandler indicates that the component can handle data send by a Http POST.
 * 
 * @author ernestmicklei
 */
public interface PostHandler {
    /**
     * Handle the incoming request sent e.g. from a HTML Form.
     * @param request
     * @param response
     * @throws IOException
     */    
	void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
