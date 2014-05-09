package org.rendersnake.test;

import java.io.IOException;

import org.rendersnake.DocType;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.test.components.PersonUI;

public class PersonalPage implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        html
            .render(DocType.HTML_4_01_Strict)
            .html()
            .body()
                .render(new PersonUI(new Person()))
            ._body()
            ._html();
    }

}
