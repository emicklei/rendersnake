package org.rendersnake.test;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import static org.rendersnake.HtmlAttributesFactory.*;

public class Title implements Renderable {

    public static final Title INSTANCE = new Title();
    
    @Override
    public void renderOn(HtmlCanvas html) throws IOException {
            html
                .div(id("help").class_("class").abbr("abbr").background("background"))
                    .write(html.getPageContext().getString("title"))
                ._div();
    }
}
