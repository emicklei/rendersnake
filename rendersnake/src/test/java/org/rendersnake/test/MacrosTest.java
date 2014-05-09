package org.rendersnake.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Assert;
import org.rendersnake.HtmlCanvas;

public class MacrosTest extends TestCase {
    private HtmlCanvas html;

    public void setUp() {
        html = new HtmlCanvas();
    }
    
    public void test_script() throws IOException {
        html.macros().script("code");
        Assert.assertEquals("<script type=\"text/javascript\">/*<![CDATA[*/code/*]]>*/</script>",html.toHtml());
    }
    public void test_stylesheet() throws IOException {
        html.macros().stylesheet("cssHref");
        Assert.assertEquals("<link type=\"text/css\" href=\"cssHref\" rel=\"stylesheet\"/>",html.toHtml());
    }
    public void test_javascript() throws IOException {
        html.macros().javascript("jsHref");
        Assert.assertEquals("<script type=\"text/javascript\" src=\"jsHref\"></script>",html.toHtml());
    }
    public void test_cdata() throws IOException {
        html.macros().cdata("<&>");
        Assert.assertEquals("/*<![CDATA[*/<&>/*]]>*/",html.toHtml());
    }
}
