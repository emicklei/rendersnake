package org.rendersnake.site.ipad;

import static org.rendersnake.HtmlAttributesFactory.id;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.StringResource;
import org.rendersnake.ext.servlet.ServletUtils;
import org.rendersnake.site.example.mobile.FlipSwitch;
import org.rendersnake.site.example.mobile.Slider;

@Singleton @Named("/org.rendersnake.site.ipad.WidgetsView")
public class WidgetsView implements HttpGetHandler {

    
    public void get(HtmlCanvas html, HandlerResult result) throws IOException {
            html.div(id("widgets"));

            html.h1().write("Slider")._h1();
            Slider s = new Slider(0,50,100);
            s.id("slider");
            html.render(s);                    
            html.render(StringResource.valueOf("content/ipad/Slider.html"));                    

            html.h1().write("FlipSwitch")._h1();            
            FlipSwitch f = new FlipSwitch("switch-select", "happy");
            f.textLabel = "Bluetooth";
            f.id("switch");            
            html.render(f);    
            html.render(StringResource.valueOf("content/ipad/FlipSwitch.html"));                                           
            
            html._div();
            
            if (ServletUtils.hasAjaxRequest(html)) {
                // need to apply modification on client side
                html.script().content("$('#widgets').page()");
            }
    }
}
