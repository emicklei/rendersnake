package com.company.ui.login;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.company.service.UserService;

@Controller
public class LoginAction {

    @Autowired
    UserService userService;
    
    @RequestMapping("/login")
    public ModelAndView login(HtmlCanvas html) throws IOException {
        
        String enteredUser = RequestUtils.getParameters(html).getString("name", "");
        String enteredPassword = RequestUtils.getParameters(html).getString("password", "");
        
        if (this.userService.authenticate(enteredUser, enteredPassword)) {
            html.getSession()
                .withBoolean("authenticated", true)
                .withString("user", enteredUser);
        }
        
        return new ModelAndView(new RedirectView("index.html"));
    }
}
