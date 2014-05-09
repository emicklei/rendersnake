package org.rendersnake.site;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.PageContext;
import org.rendersnake.internal.SimpleContextMap;
import org.rendersnake.site.example.LoginForm;
import org.rendersnake.tools.PrettyWriter;

public class LoginFormTest extends TestCase {
    public void testRender() throws IOException {
        
        HtmlCanvas html = new HtmlCanvas(new PrettyWriter());
        html.getPageContext().withObject(PageContext.SESSION, new SimpleContextMap());
        html.render(new LoginForm());
        
        System.out.println(html.toHtml());
    }
}
