package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.NO_ESCAPE;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RequestUtils;
import org.rendersnake.StringResource;

public class Sidebar implements Renderable {

    private static final String URL_SIDEBAR_CONTENT = "http://rendersnake.googlecode.com/svn/trunk/docs/sidebar.html";

    public void renderOn(HtmlCanvas html) throws IOException {
        
        // If the page request contains ?flush then do just that for my content
        String flush = RequestUtils.getParameter(html, "flush");
        if (flush != null) {
            StringResource.flush(URL_SIDEBAR_CONTENT);
        }
        html.write(StringResource.get(URL_SIDEBAR_CONTENT),NO_ESCAPE);
    }
}
