package com.company.ui.login;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.rendersnake.ext.servlet.PostHandler;

import com.company.service.UserService;

@Singleton @Named("/login")
public class LoginAction implements PostHandler {

    @Inject
    UserService userService;
    
	public void handlePost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        
        String enteredUser = request.getParameter("name");
        String enteredPassword = request.getParameter("password");
        
        if (this.userService.authenticate(enteredUser, enteredPassword)) {
            request.getSession().setAttribute("authenticated", true);
            request.getSession().setAttribute("user", enteredUser);
        }
        
        response.sendRedirect("./");
    }
}
