package org.rendersnake;

import static org.rendersnake.HtmlAttributesFactory.*;
import java.io.IOException;
/**
 * CanvasMacros is a helper class that provides convenience methods for common HTML constructions.
 * 
 * @author ernest.micklei
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class CanvasMacros<T extends HtmlCanvas> {
    private T canvas;
    
    public CanvasMacros(T canvas) {
        super();
        this.canvas = canvas;
    } 
    /**
     * Write the script tag with code content. No HTML escaping applied.
     * W3C has recommended that all scripts within an XHTML document be escaped using CDATA sections.
     * @param code
     * @return the canvas
     * @throws IOException
     */
    public T script(String code) throws IOException {
        return (T)canvas.script(type("text/javascript"))
                .cdata()
                .write(code,NO_ESCAPE)
                ._cdata()
                ._script();
    }
    /**
     * Write the link tag with a reference to the external resource.
     * @param cssHref
     * @return the canvas
     * @throws IOException
     */    
    public T stylesheet(String cssHref) throws IOException {
        return (T)canvas
            .link(type("text/css")
            .href(cssHref)
            .rel("stylesheet")); 
    }
    /**
     * Write the script tag with a reference to the external resource.
     * @param jsHref
     * @return the canvas
     * @throws IOException
     */    
    public T javascript(String jsHref) throws IOException {
        return (T)canvas
            .script(type("text/javascript").src(jsHref))
            ._script(); 
    } 
    /**
     * Write some character data inside a CDATA section.
     * @param cdata
     * @return the canvas
     * @throws IOException
     */
    public T cdata(String cdata) throws IOException {
        canvas.cdata();
        canvas.write(cdata,NO_ESCAPE);
        return (T)canvas._cdata();
    }

    /**
     * Write the link tag with a reference to the favicon.
     * @param iconHref
     * @return the canvas
     * @throws IOException
     */
    public T favicon(String iconHref) throws IOException {
    	return (T)canvas
            .link(href(iconHref)
            .rel("shortcut icon"));
    }
}