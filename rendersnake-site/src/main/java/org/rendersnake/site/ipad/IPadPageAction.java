package org.rendersnake.site.ipad;

import static org.rendersnake.HtmlAttributesFactory.dataIcon;
import static org.rendersnake.HtmlAttributesFactory.dataInline;
import static org.rendersnake.HtmlAttributesFactory.dataRole;
import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.DocType;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.ext.jquery.JQueryLibrary;

@Singleton @Named("/ipad.html")
public class IPadPageAction implements HttpGetHandler {

    public void get(HtmlCanvas html, HandlerResult result) throws IOException {// @formatter:off
        
        html
        .render(DocType.HTML5)
        .html()
            .head()
                .title().write("renderSnake - Mobile")._title()
                .render(JQueryLibrary.mobileTheme("1.0"))
                .render(JQueryLibrary.core("1.6.4"))
                .render(JQueryLibrary.mobile("1.0"))
            ._head()
        .body()
            .div(dataRole("page"))
                .div(dataRole("header").dataTheme("a").dataPosition("inline"))
                    //.a(dataIcon("back").dataTheme("b").href("index.html"))
                    //    .write("Back")
                    //._a()
                    .h1().write("renderSnake - JQuery Mobile support")._h1()
                    .a(dataIcon("check").dataTheme("b").href("http://rendersnake.org"))
                        .write("Back to Home")
                    ._a()
                    ._div()
                .div(dataRole("content").dataTheme("a"))
                    .render(this.renderContentOn(html))
                    ._div()
                .div(dataRole("footer").dataTheme("a"))
                    .h4().write("(c) 2012, renderSnake.org")._h4()
                    ._div()
            ._div()
        ._body()
        ._html();
    }
    private Renderable renderContentOn(HtmlCanvas html) {
        return new Renderable() {
            
            public void renderOn(HtmlCanvas html) throws IOException {
                
                html
                    .div()
                        .a(dataInline(true).dataRole("button").onClick("$('#replace').load('org.rendersnake.site.ipad.AnatomyView') "))
                        .write("Minimal Page")
                        ._a()                    
                        .a(dataInline(true).dataRole("button").onClick("$('#replace').load('org.rendersnake.site.ipad.ListView') "))
                        .write("Basic List View")
                        ._a()                    
                        .a(dataInline(true).dataRole("button").onClick("$('#replace').load('org.rendersnake.site.ipad.ButtonsView') "))
                        .write("Themed Buttons")
                        ._a()                    
                        .a(dataInline(true).dataRole("button").onClick("$('#replace').load('org.rendersnake.site.ipad.WidgetsView') "))
                        .write("Widgets")
                        ._a()                    
                    ._div()
                ;
                html.hr();
                // place holder
                html.div(id("replace"))._div();                                
            }
        };
    }
}
