package org.rendersnake.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.ext.tidy.TidyMessageCheck;
import org.rendersnake.test.components.PersonUI;
import org.w3c.tidy.Tidy;

public class PersonUITest extends TestCase {

    public void testRender() throws IOException {
        PersonUI ui = new PersonUI(new Person());
        HtmlCanvas html = new HtmlCanvas();
        ui.renderOn(html);
        System.out.println(html.toHtml());
    }
    public void testPageRender() throws IOException {
        HtmlCanvas html = new HtmlCanvas();
        html.render(new PersonalPage());
        
        Tidy tidy = new Tidy();
        tidy.setMessageListener(new TidyMessageCheck());
        tidy.setXHTML(true); 
        tidy.setDocType("loose");
        tidy.parse(new ByteArrayInputStream(html.toHtml().getBytes()), System.out);        
    }
    public void testPageRenderWithError() throws IOException {
        HtmlCanvas html = new HtmlCanvas();
        html.tag("bogus");
        html.render(new PersonalPage());
        
        Tidy tidy = new Tidy();
        tidy.setMessageListener(new TidyMessageCheck());
        tidy.setXHTML(true); 
        tidy.setDocType("loose");
        try {
            tidy.parse(new ByteArrayInputStream(html.toHtml().getBytes()), System.out);        
        } catch (AssertionFailedError err) {
            System.out.println(err);
            // good thing
        }
    } 
}
