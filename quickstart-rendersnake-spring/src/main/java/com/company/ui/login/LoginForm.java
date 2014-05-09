package com.company.ui.login;

import static org.rendersnake.HtmlAttributesFactory.action;
import static org.rendersnake.HtmlAttributesFactory.for_;
import static org.rendersnake.HtmlAttributesFactory.type;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

public class LoginForm implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {
        
        html
            .form(action("login").method("post").id("login-form"))
            .fieldset()
                .div()
                    .label(for_("name")).content("Username")                    
                    .input(type("text").name("name").id("name"))                    
                ._div()
                .div()
                    .label(for_("password")).content("Password")
                    .input(type("password").name("password").id("password"))                    
                ._div()
                .input(type("submit").value("Login"))
            ._fieldset()
            ._form(); 
    }
}
