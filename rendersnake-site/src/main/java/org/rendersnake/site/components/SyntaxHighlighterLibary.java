package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.type;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.site.SiteConstants;

public class SyntaxHighlighterLibary implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        html.script(type("text/javascript").src(SiteConstants.RESOURCE_BASE_URL + "htdocs/js/shCore.js"))._script();
        html.script(type("text/javascript").src(SiteConstants.RESOURCE_BASE_URL + "htdocs/js/shBrushJScript.js"))._script();
        html.script(type("text/javascript").src(SiteConstants.RESOURCE_BASE_URL + "htdocs/js/shBrushJava.js"))._script();
        html.link(type("text/css").rel("stylesheet").href(SiteConstants.RESOURCE_BASE_URL + "htdocs/shCore.css"));
        html.link(type("text/css").rel("stylesheet").href(SiteConstants.RESOURCE_BASE_URL + "htdocs/shThemeDefault.css"));
    }
}
