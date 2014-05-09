package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.href;
import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class Footer implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        html
            .div(id("footer"))
                .write("Copyright &copy; renderSnake.org |",false)
                .a(href("http://www.html5webtemplates.co.uk"))
                        .write(" design from HTML5webtemplates.co.uk | ")
                ._a()
                .a(href("http://rendershark.googlecode.com"))
                        .write("powered by rendershark")
                ._a()
            ._div()
            .render(new GoogleAnalyticsTracker());
    }
}
