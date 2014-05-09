package org.rendersnake.test.debug;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.test.Person;
import org.rendersnake.test.components.PersonUI;
import org.rendersnake.tools.Inspector;

public class InspectorTest extends TestCase {

    public void testInpsectPersonUI() throws IOException {
        PersonUI ui = new PersonUI(new Person());
        Inspector inspector = new Inspector(ui);
        HtmlCanvas html = new HtmlCanvas();
        html.render(inspector);
        assertNotNull("inspected html", html.toHtml());
        System.out.println(html.toHtml());
        assertTrue(html.toHtml().indexOf("<div class=\"rendersnake-inspector\">") == 0);
    }
    public void testInpsectNull() throws IOException {
        try {
            new Inspector(null);
            fail("new Inspector(null) must raise illegal argument exception");
        } catch (IllegalArgumentException iae) {
            // got it
        }
    }    
}
