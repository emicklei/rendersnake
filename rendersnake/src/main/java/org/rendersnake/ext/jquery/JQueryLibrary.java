package org.rendersnake.ext.jquery;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

/**
 * JQueryLibrary is a convenience component to add HTML instructions to load
 * libraries from http://code.jquery.com/ (or your own domain in BASE_RESOURCE_URL).
 * 
 * @author emicklei
 */
public class JQueryLibrary implements Renderable {
    /**
     * The base url where to find jQuery assets (js,css).
     */
    public static String BASE_RESOURCE_URL = "http://code.jquery.com";
    
    private String url;

    public JQueryLibrary(String url) {
        super();
        this.url = url;
    }
    /**
     * Return a library that loads the core from code.jquery.com
     * @param version
     * @return a JQueryLibrary
     */
    public static JQueryLibrary core(String version) {
        return new JQueryLibrary(BASE_RESOURCE_URL + "/jquery-" + version + ".min.js");
    }
    /**
     * Return a library that loads the ui framework from code.jquery.com
     * @param version
     * @return a JQueryLibrary
     */
    public static JQueryLibrary ui(String version) {
        return new JQueryLibrary(BASE_RESOURCE_URL + "/ui/" + version + "/jquery-ui.min.js");
    }

    public static JQueryLibrary baseTheme(String version) {
        return theme(version,"base");
    }
    /**
     * Return a library that loads a theme css from code.jquery.com
     * @param version
     * @return a JQueryLibrary
     */    
    public static JQueryLibrary theme(String version, String theme) {
        return new JQueryLibrary(BASE_RESOURCE_URL + "/ui/" + version + "/themes/"+theme+"/jquery-ui.css");
    }    
    /**
     * Return a library that loads the css from code.jquery.com
     * @param version
     * @return a JQueryLibrary
     */
    public static JQueryLibrary mobileTheme(String version) {
        return new JQueryLibrary(BASE_RESOURCE_URL + "/mobile/" + version + "/jquery.mobile-" + version + ".min.css");
    }
    /**
     * Return a library that loads the structure-only from code.jquery.com
     * @param version
     * @return a JQueryLibrary
     */
    public static JQueryLibrary mobileStructure(String version) {
        return new JQueryLibrary(BASE_RESOURCE_URL + "/mobile/" + version + "/jquery.mobile.structure-" + version + ".min.css");
    }    
    /**
     * Return a library that loads the javascript from code.jquery.com
     * @param version
     * @return a JQueryLibrary
     */    
    public static JQueryLibrary mobile(String version) {
        return new JQueryLibrary(BASE_RESOURCE_URL + "/mobile/" + version + "/jquery.mobile-" + version + ".min.js");
    }

    /**
     * Write a link reference to load the external Javascript library for
     * JQuery. This should be part of the HEAD section of an HTML page.
     * 
     * @param html
     */
    public void renderOn(HtmlCanvas html) throws IOException {
        if (url.endsWith(".js")) {
            html.script(html.attributes().type("text/javascript").src(url))._script();
            return;
        }
        if (url.endsWith(".css")) {
            html.link(html.attributes().type("text/css").href(url).rel("stylesheet"));
            return;
        }
        throw new IllegalStateException("Unknown resource type:" + url);
    }
}
