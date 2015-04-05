package com.company.ui.page.welcome;

import static org.rendersnake.HtmlAttributesFactory.*;

import java.io.IOException;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.ext.spring.template.Template;
import org.springframework.stereotype.Component;

import com.company.ui.template.DefaultTemplate;

@Component
@Template(DefaultTemplate.class)
public class WelcomePage implements Renderable {

	@Override
	public void renderOn(HtmlCanvas html) throws IOException {
		html.h1(class_("text-center")).content("Quickstart Rendersnake with Spring Boot and Bootstrap")
			.hr()
			.div(class_("text-right"))
				.button(class_("btn btn-success")).i(class_("fa fa-fw fa-user"))._i().write(" admin")._button()
				.write(" ")
				.a(href("/login?logout").class_("btn btn-warning")).i(class_("fa fa-fw fa-sign-out"))._i().write(" Logout")._a()
			._div();
	}

}
