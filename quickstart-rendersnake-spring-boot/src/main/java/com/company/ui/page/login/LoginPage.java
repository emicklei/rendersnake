package com.company.ui.page.login;

import static org.rendersnake.HtmlAttributesFactory.*;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RequestUtils;
import org.rendersnake.ext.spring.template.Template;
import org.springframework.stereotype.Component;

import com.company.ui.template.LoginTemplate;

@Component
@Template(LoginTemplate.class)
public class LoginPage implements Renderable {
	

	@Override
	public void renderOn(HtmlCanvas html) throws IOException {
		final String fieldError = RequestUtils.getParameters(html).getString("fieldError");
		final String error = RequestUtils.getParameters(html).getString("error");
		final String logout = RequestUtils.getParameters(html).getString("logout");
		
		html.form(method("POST").class_("form-signin"))
				.h2(class_("form-signin-heading text-center")).content("Rendersnake + Spring Boot")
				
				.if_(fieldError != null).p(class_("text-center text-danger")).content("Username or password cannot be empty")._if()
				.if_(error != null).p(class_("text-center text-danger")).content("Invalid username or password")._if()
				.if_(logout != null).p(class_("text-center text-success")).content("Your are now disconnected")._if()
				.p(class_("text-center")).content("Login: admin / admin")
				
				.br()
				
				.input(name("username").type("text").placeholder("Username").class_("form-control"))
				.input(name("password").type("password").placeholder("Password").class_("form-control"))
				.button(type("submit").class_("btn btn-lg btn-primary btn-block")).content("Login")
			._form();
	}
}
