package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.name;
import static org.rendersnake.HtmlAttributesFactory.type;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.ext.jquery.JQueryLibrary;

public class Head implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {// formatter:off
        
        html
            .head()
                .title().write(html.getPageContext().getString("title"))._title()
                .meta(name("description").add("content","renderSnake documentation and examples",false))
                .link(type("text/css").rel("stylesheet").href("/htdocs/style-01.css"))
                .render(JQueryLibrary.core("1.7.1"))
                .render(JQueryLibrary.ui("1.8.18"))
                .render(JQueryLibrary.baseTheme("1.8.18"))
            ._head();            
    }
}
