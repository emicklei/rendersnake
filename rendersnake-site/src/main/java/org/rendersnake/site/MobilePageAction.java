package org.rendersnake.site;

import static org.rendersnake.HtmlAttributesFactory.dataRole;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.DocType;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.StringResource;
import org.rendersnake.ext.jquery.JQueryLibrary;

@Singleton @Named("/mobile.html")
public class MobilePageAction implements HttpGetHandler {

    public void get(HtmlCanvas html, HandlerResult result) throws IOException {// @formatter:off
        
        html
            .render(DocType.HTML5)
            .html()
                .head()
                    .title().write("renderSnake - Mobile")._title()
                    .render(JQueryLibrary.mobileTheme("1.0a2"))
                    .render(JQueryLibrary.core("1.4.4"))
                    .render(JQueryLibrary.mobile("1.0a2"))
                ._head()
            .body()
                .div(dataRole("page"))
                    .div(dataRole("header").dataTheme("b"))
                        .h1().write("renderSnake - JQuery Mobile support")._h1()
                        ._div()
                    .div(dataRole("content").dataTheme("b"))
                        .h1().write("Header 1")._h1()
                        .h2().write("Header 2")._h2()
                        .h3().write("Header 3")._h3()
                        .h4().write("Header 4")._h4()
                        .hr()
                        .pre().render(new StringResource("content/Example-jquery-mobile.html"))._pre()
                        ._div()
                    .div(dataRole("footer").dataTheme("b"))
                        .h4().write("(c) 2012, renderSnake.org")._h4()
                        ._div()
                ._div()
            ._body()
            ._html();
    }
}
