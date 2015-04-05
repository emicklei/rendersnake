package com.company.ui.template;

import static org.rendersnake.HtmlAttributesFactory.*;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.ext.spring.template.TemplateDescriptor;

/**
 * Default template of the page with Twitter Bootstrap and Font-awsome
 * 
 * @author Thibaut Mottet
 *
 */
public class DefaultTemplate implements TemplateDescriptor {
	
	@Override
	public String getDefaultTitle() {
		return "RenderSnake";
	}
	
	@Override
	public void renderHeaderOn(HtmlCanvas html) throws IOException {
		html
			.meta(charset("utf-8"))
	        .meta(http_equiv("X-UA-Compatible").content("IE=edge"))
	        .meta(name("viewport").content("width=device-width, initial-scale=1"))
	         
	        .macros().stylesheet("/bower_components/bootstrap/dist/css/bootstrap.min.css")
	        .macros().stylesheet("/bower_components/fontawesome/css/font-awesome.min.css")
	        .macros().javascript("/bower_components/jquery/dist/jquery.min.js")
     		.macros().javascript("/bower_components/bootstrap/dist/js/bootstrap.min.js");
	}
	
	@Override
	public void renderBodyStartOn(HtmlCanvas html) throws IOException {
		html.div(class_("container"));
	}
	
	@Override
	public void renderBodyEndOn(HtmlCanvas html) throws IOException {
		html._div(); // end container
	}
}
