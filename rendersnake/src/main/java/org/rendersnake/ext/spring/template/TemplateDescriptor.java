package org.rendersnake.ext.spring.template;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;

/**
 * 
 * @see quickstart-rendersnake-spring-boot project for example
 * 
 * @author Thibaut Mottet
 */
public interface TemplateDescriptor {
	String getDefaultTitle();

	void renderHeaderOn(HtmlCanvas html) throws IOException;

	void renderBodyStartOn(HtmlCanvas html) throws IOException;
	void renderBodyEndOn(HtmlCanvas html) throws IOException;
}