package org.rendersnake.test;

import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlAttributesFactory;

public class AttributesTest extends TestCase {

    private HtmlAttributes attrs;

    public void setUp() {
        attrs = new HtmlAttributes();
    }

    public void testEntities() {
        HtmlAttributes a = new HtmlAttributes();
        a.value("\"'<>&");
        Assert.assertEquals(" value=\"&quot;&#39;&lt;&gt;&amp;\"", a.toHtml());
    }
    
    public void testConstructor() {
        HtmlAttributes one = new HtmlAttributes();
        one.id("id");
        HtmlAttributes withClass = new HtmlAttributes(one);
        withClass.class_("class");
        Assert.assertEquals(" id=\"id\"", one.toHtml());
        Assert.assertEquals(" id=\"id\" class=\"class\"", withClass.toHtml());
    }
    
    public void testtestdata() throws IOException {
        attrs.dataTest("yes");
        Assert.assertEquals(" data-test=\"yes\"", attrs.toHtml());
    }
    
    public void testtestdata_skip() throws IOException {
        HtmlAttributes.RENDER_DATA_TEST_ATTRIBUTE = false;
        try {
            attrs.dataTest("yes");        
            Assert.assertEquals("", attrs.toHtml());
        } finally { HtmlAttributes.RENDER_DATA_TEST_ATTRIBUTE = !false; }        
    }    
    
    public void testCanBeOnlyOne() throws IOException {
        attrs.id("id1");
        HtmlAttributes other = new HtmlAttributes();
        other.id("id2");
        System.out.println(attrs);
        System.out.println(other);
    }
    
    
    public void testEmpty() {
        assertEquals("", attrs.toHtml());
    }

    public void testCreateOne() throws IOException {
        assertEquals(" one=\"een\"", new HtmlAttributes("one", "een").toHtml());
    }

    public void testAddNull() throws IOException {
        assertEquals("", attrs.add("null", (String)null).toHtml());
    }

    public void testAddNullKey() throws IOException {
        try { attrs.add(null, "null").toHtml(); fail("should throw up"); } catch (NullPointerException nup) {}
    }

    public void testAddOne() throws IOException {
        assertEquals(" one=\"one\"", attrs.add("one", "one").toHtml());
    }

    public void testAddNullEscape() throws IOException {
        assertEquals("", attrs.add("null", null, true).toHtml());
    }

    public void testAddNullNoEscape() throws IOException {
        assertEquals("", attrs.add("null", null, false).toHtml());
    }

    public void testToString() {
        assertNotNull(attrs.toString());
    }    
    
    public void testXmlLang() throws IOException {
        assertNotNull(" xml:lang=\"lang\"",attrs.xml_lang("lang").toHtml());
    }
    public void test_data_extension() throws IOException {
        assertEquals(" data-extension=\"value\"", HtmlAttributesFactory.data("extension","value").toHtml());
    }       
}
