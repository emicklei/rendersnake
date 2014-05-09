package org.rendersnake.site.components;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RequestUtils;
import org.rendersnake.StringResource;

public class GoogleAnalyticsTracker implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {

        String host = RequestUtils.getHeaderValue(html, "HOST");
        if(null != host && host.startsWith("localhost"))
            return;
        html.write(StringResource.get("content/googletracker.js"),false);      
    }
}
