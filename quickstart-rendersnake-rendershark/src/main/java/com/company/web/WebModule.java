package com.company.web;

import org.rendershark.core.RendersharkModule;

import com.company.service.UserService;
import com.company.service.UserServiceImpl;
import com.company.ui.IndexAction;
import com.company.ui.login.LoginAction;
import com.google.inject.AbstractModule;

public class WebModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new RendersharkModule.HTML());
		bind(IndexAction.class);
		bind(LoginAction.class);
		bind(UserService.class).to(UserServiceImpl.class);
	}
}
