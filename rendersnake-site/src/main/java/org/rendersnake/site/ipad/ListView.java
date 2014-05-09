package org.rendersnake.site.ipad;

import static org.rendersnake.HtmlAttributesFactory.dataRole;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.StringResource;

@Singleton @Named("/org.rendersnake.site.ipad.ListView")
public class ListView implements HttpGetHandler {

    
    public void get(HtmlCanvas html, HandlerResult result) throws IOException {

            html
            .ul(dataRole("listview").dataTheme("b").id("lv"))
                .li().write("Basic")._li()
                .li().write("Normal")._li()
                .li().write("Advanced")._li()
            ._ul()
            .script().write("$('ul#lv').page()")._script()
            .hr()
            .render(StringResource.valueOf("content/ipad/ListView.html"));
    }
}
