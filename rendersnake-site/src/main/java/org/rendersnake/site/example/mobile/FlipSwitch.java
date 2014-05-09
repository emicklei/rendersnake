package org.rendersnake.site.example.mobile;

import java.io.IOException;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlAttributesFactory;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class FlipSwitch extends HtmlAttributes implements Renderable {

    String selectId;
    String name;
    public String textOn = "On";
    public String textOff = "Off";
    public String textLabel = "Select";
    
    public FlipSwitch(String selectId, String selectName) {
        super();
        this.selectId = selectId;
        this.name = selectName;
    }
    
    public void renderOn(HtmlCanvas html) throws IOException {
        this.dataRole("fieldcontain");
        html
            .div(this)
            .label(HtmlAttributesFactory.for_(selectId))
                .content(textLabel)
            .select(HtmlAttributesFactory.dataRole("slider").name(name).id(selectId))
                .option(HtmlAttributesFactory.value("off")).content(textOff)
                .option(HtmlAttributesFactory.value("on")).content(textOn)
            ._select()
            ._div();
    }
}
