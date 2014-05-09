package org.rendersnake.site.example;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.for_;
import static org.rendersnake.HtmlAttributesFactory.id;
import static org.rendersnake.HtmlAttributesFactory.type;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.rendershark.core.HandlerResult;
import org.rendershark.core.HttpPostHandler;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RequestUtils;
import org.rendersnake.StringResource;
import org.rendersnake.internal.ContextMap;
import org.rendersnake.site.components.SourceLink;

@Singleton @Named("/login.html")
public class LoginForm implements Renderable, HttpPostHandler {
	
    private static final String ERROR_LOGIN_PASSWORD = "error.LoginForm.password";
    private static final String ERROR_LOGIN_USERNAME = "error.LoginForm.username";
    static final String ID_USERNAME = "username";
    static final String ID_PASSWORD = "password";
    
    static final String VAR_USERNAME = "username";
    static final String VAR_PASSWORD = "password";

    public String username;
    public String password;
    
	public void renderOn(HtmlCanvas html) throws IOException {
        html
            .h1().write("Login Form")._h1()
            .p().write("This component implements Renderable for displaying the Form, FormValidator to validate incoming data and FormHandler to process the validated data.")._p()
            .write(StringResource.get("content/Example-login.html"),false);
        
        Object user = RequestUtils.getSession(html).getObject("user");
        if (null != user) {
            html.h4().write("Welcome " + user)._h4();
            return;
        }                
        this.renderErrorsOn(html);
        html.style().write("#sample label,input { padding: 4px; margin:4px;}")._style();
        html
            .form(id("sample").action("/login.html").method("post"))
                .label(for_(ID_USERNAME)).write("Username:")._label()
                .input(
                    id(ID_USERNAME)
                    .name(VAR_USERNAME).value(username))
            .br()
                .label(for_(ID_PASSWORD)).write("Password:")._label()
                .input(
                    type("password")
                    .id(ID_PASSWORD)
                    .name(VAR_PASSWORD).value(password))
            .br()
                .input(type("submit").value("Log me in"))
        ._form();
        
        html.render(SourceLink.example("LoginForm"));
    }

    private void renderErrorsOn(HtmlCanvas html) throws IOException {
        ContextMap session = html.getSession();
        if (session == null)
            return;
        Object usernameMsg = session.getObject(ERROR_LOGIN_USERNAME);
        Object passwordMsg = session.getObject(ERROR_LOGIN_PASSWORD);
        if (usernameMsg == null && passwordMsg == null)
            return;
        html
            .div(class_("errors"))
                .write((String)usernameMsg)
                .br()
                .write((String)passwordMsg)
            ._div();
    }
    
	public void post(HtmlCanvas html, HandlerResult result) throws IOException {
		html.getSession().withString("user", 
				html.getRequestParameters().getString(VAR_USERNAME));
		result.redirectTo("/examples.html");
	}    

    
//    public boolean validate(HttpServletRequest request) {
//        
//        boolean isValid = true;
//        if (StringUtils.isBlank(username)) {
//            request.getSession().setAttribute(ERROR_LOGIN_USERNAME, "Username is missing");
//            isValid = false;
//        }
//        if (StringUtils.isBlank(password)) {
//            request.getSession().setAttribute(ERROR_LOGIN_PASSWORD, "Password is missing");
//            isValid = false;
//        }
//        return isValid;
//    }



}
