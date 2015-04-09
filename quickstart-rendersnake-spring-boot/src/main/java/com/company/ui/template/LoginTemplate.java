package com.company.ui.template;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;

/**
 * Login template
 * 
 * @author Thibaut Mottet
 *
 */
public class LoginTemplate extends DefaultTemplate {
	
	@Override
	public String getDefaultTitle() {
		return "Login";
	}
	
	@Override
	public void renderHeaderOn(HtmlCanvas html) throws IOException {
		super.renderHeaderOn(html);
		html.macros().stylesheet("/css/signin.css");
	}
}
