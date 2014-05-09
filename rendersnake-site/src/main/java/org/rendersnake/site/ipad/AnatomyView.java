package org.rendersnake.site.ipad;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;

@Singleton
@Named("/org.rendersnake.site.ipad.AnatomyView")
public class AnatomyView implements Renderable, HttpGetHandler {

    public void renderOn(HtmlCanvas html) throws IOException {
        html.render(StringResource.valueOf("content/Example-jquery-mobile.html"));

    }

    public void get(HtmlCanvas arg0, HandlerResult arg1) throws IOException {
        this.renderOn(arg0);
    }
}
