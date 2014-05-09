package com.company.ui;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpGetHandler;
import org.rendersnake.HtmlCanvas;

@Singleton @Named("/")
public class IndexAction implements HttpGetHandler {
    
	@Override
	public void get(HtmlCanvas html, HandlerResult result) throws IOException {
		html.render(new SiteLayoutWrapper(new HomePage()));		
	}
}
