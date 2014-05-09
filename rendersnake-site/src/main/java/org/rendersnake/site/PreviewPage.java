package org.rendersnake.site;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.site.components.GotoTop;
import org.rendersnake.site.components.Head;
import org.rendersnake.site.components.InspectThis;
import org.rendersnake.site.components.Logo;
import org.rendersnake.site.components.MenuBar;
import org.rendersnake.site.components.Sidebar;
import org.rendersnake.site.components.SourceLink;
import org.rendersnake.site.components.TranslatorForm;
import org.rendersnake.site.example.CityTable;
import org.rendersnake.tools.Inspector;

public class PreviewPage implements Renderable{

    public void renderOn(HtmlCanvas html) throws IOException {
        
        html
        .html()
        .render(new Head())
        .body()
            .render(new Content())
        ._body()
        ._html();

    }
    private class Content implements Renderable {
    	public void renderOn(HtmlCanvas html) throws IOException {
        
            html.render(new Inspector(new Logo()));
            html.hr();
            html.render(new Inspector(new InspectThis()));
            html.hr();
            html.render(new Inspector(new MenuBar("here")));
            html.hr();
            html.render(new Inspector(new Sidebar()));
            html.hr();
            html.render(new Inspector(new GotoTop()));
            html.hr();
            html.render(new Inspector(new SourceLink()));
            html.hr();
            html.render(new Inspector(new TranslatorForm()));
            html.hr();
            html.render(new Inspector(new CityTable()));
            html.hr();
    }
    }
}
