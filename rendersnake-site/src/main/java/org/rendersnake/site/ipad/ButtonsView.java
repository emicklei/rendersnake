package org.rendersnake.site.ipad;

import static org.rendersnake.HtmlAttributesFactory.dataInline;
import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.StringResource;
import org.rendersnake.ext.servlet.ServletUtils;

@Singleton @Named("/org.rendersnake.site.ipad.ButtonsView")
public class ButtonsView implements HttpGetHandler {

    
    public void get(HtmlCanvas html, HandlerResult result) throws IOException {
        
        html.div(id("bar"));
        for (char each : "abcde".toCharArray()) {
          html
            .a(dataInline(true).dataRole("button").dataTheme(String.valueOf(each)).href("#"))
            .write("Theme "+each)
                ._a();
        }
       html._div();            
       if (ServletUtils.hasAjaxRequest(html)) {
           // need to apply modification on client side
           html.script().write("$('div#bar').page()")._script();
       }
       html
            .hr()
            .render(StringResource.valueOf("content/ipad/ButtonsView.html"));
    }
}
