package org.rendersnake.jquery;

import java.io.StringWriter;

import org.rendersnake.Hash;
import org.rendersnake.ToJavascript;
import org.rendersnake.jquery.mobile.JQMobileCanvas;

/**
 * JQueryCanvas can write jQuery expressions.
 * Use {@link toJavascript} to pass the expression String to an Html attribute. 
 * 
 * @author ernestmicklei
 */
public class JQueryCanvas implements ToJavascript {

	private StringWriter out = new StringWriter();

	public JQueryCanvas jQuery(String selector) {
	    out.write("$('");
	    out.write(selector);
	    out.write("')");
	    return this;
	}
	public JQueryCanvas jQuery(String selector,String context) {
	    out.write("$('");
	    out.write(selector);
	    out.write("',");
	    out.write(context);
	    out.write(')');
	    return this;
	}			
    public JQueryCanvas jQuery() {
        out.write("$");
        return this;
    }
	public String toJavascript() {
	    out.write(';');
		return out.toString();
	}
    
    public JQueryCanvas ajaxSetup(Hash hash) {
        out.write(".ajaxSetup(");        
        out.write(hash.toJavascript());
        out.write(')');
        return this;
    }
	
    public JQueryCanvas addClass(String cssClass) {
        out.write(".addClass('");
        out.write(cssClass);
        out.write("')");
        return this;
    }
    public JQueryCanvas dialog() {
        out.write(".dialog()");
        return this;
    }
    /**
     * 
     * @param url A string containing the URL to which the request is sent.
     * @param args  
     * <ul>
     * <li>A map or string that is sent to the server with the request.
     * <li>complete(responseText, textStatus, XMLHttpRequest) A callback function that is executed when the request completes.
     * </ul>
     * 
     * @return
     */
    public JQueryCanvas load(String url,Object ... args) {
        out.write(".load('");
        out.write(url);
        out.write("')");
        return this;
    }
    public JQMobileCanvas mobile() {
        return new JQMobileCanvas();
    }
}
