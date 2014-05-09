package com.company.ui.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpPostHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.RequestUtils;

import com.company.service.UserService;

@Singleton @Named("/login")
public class LoginAction implements HttpPostHandler {

    @Inject
    UserService userService;

	@Override
	public void post(HtmlCanvas html, HandlerResult result) throws IOException {
		String enteredUser = RequestUtils.getParameter(html,"name");
        String enteredPassword = RequestUtils.getParameter(html,"password");
        
        if (this.userService.authenticate(enteredUser, enteredPassword)) {
            RequestUtils.getSession(html)
            	.withBoolean("authenticated", true)
            	.withObject("user", enteredUser);
        }
		result.redirectTo("/");
	}
}
