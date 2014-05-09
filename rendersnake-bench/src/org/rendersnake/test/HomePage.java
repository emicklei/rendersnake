package org.rendersnake.test;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class HomePage implements Renderable {

    public static final HomePage INSTANCE = new HomePage();
    
    @Override
    public void renderOn(HtmlCanvas html) throws IOException {
        html.getPageContext().withObject("title", "Bench");
        html
            .html()
                .body()
                    .render(Contents.INSTANCE)
                ._body()
            ._html();
    }
}
