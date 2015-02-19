package org.rendersnake;

import java.io.IOException;

/**
 * DocType is used to set the doctype of markup language based document such as HTML and XML.
 * 
 * @see "http://www.w3schools.com/tags/tag_DOCTYPE.asp"
 * @author e.micklei
 */
public enum DocType implements Renderable {
    XHTML_1_0_Strict("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
    ,HTML_4_01_Strict("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">")
    ,HTML_4_01_Transitional("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
    ,HTML5("<!DOCTYPE html>")
    ,XML_1_0("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

    private final String declaration;

    DocType(String declaration) {
        this.declaration = declaration;
    }

    public void renderOn(HtmlCanvas html) throws IOException {
        html.write(this.declaration,false);
    }
}
