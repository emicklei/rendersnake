package com.company.ui;

import java.io.IOException;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton @Named("/")
public class IndexAction implements Renderable {
    
    public void renderOn(HtmlCanvas html) throws IOException  {
        html.render(new SiteLayoutWrapper(new HomePage()));
    }
}
