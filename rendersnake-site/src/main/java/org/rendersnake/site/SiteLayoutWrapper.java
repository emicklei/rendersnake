package org.rendersnake.site;

import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RenderableWrapper;
import org.rendersnake.RequestUtils;
import org.rendersnake.site.components.Footer;
import org.rendersnake.site.components.Head;
import org.rendersnake.site.components.InspectThis;
import org.rendersnake.site.components.Logo;
import org.rendersnake.site.components.MenuBar;
import org.rendersnake.site.components.Sidebar;


public class SiteLayoutWrapper extends RenderableWrapper {

    public boolean showSideBar = true;
    
    public SiteLayoutWrapper(Renderable component){
        super(component);
    }
    
    public void renderOn(HtmlCanvas html) throws IOException {
        
        html
            .html()
            .render(new Head());
             renderBodyOn(html)
            ._html();
    }

    public HtmlCanvas renderBodyOn(HtmlCanvas html) throws IOException {
        
        String uri = RequestUtils.getHeaderValue(html, "Path");
        html
            .body()
            .div(id("main"))    
                .div(id("header"))
                .render(new Logo())
                .render(new MenuBar(uri))
                .render(new InspectThis())
                ._div() // header
            .div(id("content_header"))._div()
            
            .div(id("site_content"))
                .render(showSideBar ? new Sidebar() : null)
                .div(id(showSideBar ? "content" : "contentwide"))
                    .render(component)
                ._div()
            ._div()
            
            .div(id("content_footer"))._div()
            .render(new Footer())
            ._div() // main
            ._body();
           
       return html;
    }
}
