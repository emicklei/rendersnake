package org.rendersnake.site.components;

import static org.rendersnake.HtmlAttributesFactory.align;
import static org.rendersnake.HtmlAttributesFactory.id;
import static org.rendersnake.HtmlAttributesFactory.type;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.RequestUtils;
import org.rendersnake.internal.ContextMap;
import org.rendersnake.site.HtmlToRenderSnakeTranslator;

public class TranslatorForm implements Renderable {

    public void renderOn(HtmlCanvas html) throws IOException {

        html.h2().write("From HTML to Java")._h2();
        html.p().write("Developers who wish to create Renderable components from existing HTML pages or fragments," 
                + " can use the translator below to generate the corresponding Java source.")._p();
        html.p().write("Copy and paste your XHTML into the top textarea and hit the translate button." 
                + " Make sure the XHTML is well-formed ; an error will be displayed otherwise.")._p();
        html.table().tr().td();
        this.renderFormOn(html);
        html._td()._tr()._table();
        
        this.renderStatisticsOn(html);
    }

    public void renderFormOn(HtmlCanvas html) throws IOException {// @formatter:off
        // either is empty, showing java or showing html
        ContextMap session = RequestUtils.getSession(html);
        String tidy = session.getString("tidy");
        String checked = "on".equals(tidy) ? "checked" : null;
        String java = session.getString("java");        
        String xhtml = session.getString("html");
        boolean hasJava = !StringUtils.isEmpty(java);
        boolean hasHtml = !StringUtils.isEmpty(xhtml);
        if (!hasJava && !hasHtml) {
            hasHtml = true;
            xhtml = "<table id=\"sample\" class=\"title\">\n" +
            		"    <tr>\n" +
            		"       <td>renderSnake.org</td>\n" +
            		"    </tr>\n" +
            		"</table>";
        }        
        html.form(
                    id("translator")
                    .action("/translator.html")
                    .method("post"))
                .input(type("submit").value(!hasJava ? "Translate XHTML to Java" : "Back to XHTML"))
                .div(align("right")).content("rev.916")
                .input(type("checkbox").name("tidy").checked(checked))
                .write("use tidy").br()
                .input(type("hidden").name("handle").value(!hasJava ? "translate" : "back"))
                .textarea(
                     type("text")
                     .name("html")
                     .add("rows", "60", false)
                     .add("cols", "60", false))
                     .write(hasJava ? java : xhtml)
                ._textarea()._form();
    }

    private void renderStatisticsOn(HtmlCanvas html) throws IOException {
        html
        .h4().write("Statistics")._h4()
        .ul()
            .li().write(""+HtmlToRenderSnakeTranslator.TRANSLATIONS_OK+" succesful")._li()
            .li().write(""+HtmlToRenderSnakeTranslator.TRANSLATIONS_LINECOUNT+" source lines generated")._li()
            .li().write(""+HtmlToRenderSnakeTranslator.TRANSLATIONS_FAILED+" failed")._li()
        ._ul();        
    }

}
