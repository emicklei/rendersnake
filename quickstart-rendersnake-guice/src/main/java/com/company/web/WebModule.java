package com.company.web;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import com.company.ui.IndexAction;
import com.company.ui.login.LoginAction;
import com.company.service.UserService;
import com.company.service.UserServiceImpl;

public class WebModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ServletModule() {
			  @Override
			  protected void configureServlets() {
			     serve("/*").with(org.rendersnake.ext.guice.GuiceComponentServlet.class);
			     bind(IndexAction.class);
			     bind(LoginAction.class);
				 bind(UserService.class).to(UserServiceImpl.class);
			  }
			});
	}
}
