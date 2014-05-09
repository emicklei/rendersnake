package org.rendersnake.site.example;

import static org.rendersnake.HtmlAttributesFactory.class_;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.StringResource;
import org.rendersnake.site.components.SourceLink;

public class CityTable implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        html
            .h1().write("Table")._h1()
            .write(StringResource.get("content/Example-table.html"),false);
        
        html
            .table(class_("city-table"))
              .tr()
                .th().write("City")._th()
                .th().write("Country")._th()
              ._tr()
              .tr()
                .td().write("Amsterdam")._td()
                .td().write("The Netherlands")._td()
              ._tr()
            ._table();
        
        html.render(SourceLink.example("CityTable"));        
    }
}
