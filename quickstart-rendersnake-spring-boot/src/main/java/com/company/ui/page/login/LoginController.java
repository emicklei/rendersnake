package com.company.ui.page.login;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.service.LoginService;

@Controller
@RequestMapping(value = { "/login", "/", } )
public class LoginController {
	@Inject LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String displayLoginPage() {
		return "loginPage";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String checkLoginAndDisplayPage(
			@Valid LoginBinder loginBinder,
			BindingResult result) 
	{
		if (result.hasErrors()) {
			return "redirect:/login?fieldError";
		}
		
		if (!loginService.isAuthenticated(loginBinder.getUsername(), 
										  loginBinder.getPassword()))
		{
			return "redirect:/login?error";
		}
		
		return "redirect:/welcome";
	}

}
