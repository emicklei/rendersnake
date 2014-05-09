package org.rendersnake.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.rendersnake.StringResource;
import org.rendersnake.generator.AttributesMethodsGenerator.Attribute;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CanvasMethodsGenerator extends DefaultHandler {

    class Method {
        public String name;
        public String selector;
        public String description;
        public boolean deprecated = false;
        public boolean endTagForbidden = false;
    }

    private Map<String, Method> attributesMap = new HashMap<String, Method>();
    
    boolean isTest = false;
    
    public static void main(String[] args) throws Exception {
        SAXParser p = SAXParserFactory.newInstance().newSAXParser();
        String input = "../html-codegen/html4-doc.xml";
        CanvasMethodsGenerator gen = new CanvasMethodsGenerator();
        if (args.length > 0) {
            gen.isTest = args[0].equals("t");
        }
        p.parse(new File(input), gen);
        gen.exportMethods();
    }

    @Override
    public void startElement(String uri, String localName, String qname, Attributes attrs) throws SAXException {
        if (!"tag".equals(qname))
            return;
        Method method = new Method();
        method.name = attrs.getValue("name");
        method.selector = this.selectorFor(method.name);
        method.description = attrs.getValue("description");
        method.deprecated = "true".equals(attrs.getValue("deprecated"));
        method.endTagForbidden = "forbidden".equals(attrs.getValue("endTag"));
        attributesMap.put(method.name, method);
    }

    protected void exportMethods() {
        for (Method each : attributesMap.values())
            this.exportMethodFor(each);
    }
    
    protected String selectorFor(String name) {
        // apply exceptions because of reserved words in the Java language
        if (name.indexOf("-") > 0) {
            return name.replace("-", "_");
        } else if (name.indexOf(":") > 0) {
            return name.replace(":", "_");
        } else if (name.startsWith("on")) {
            // capitalize char after on
            return "on" + (Character.toUpperCase(name.charAt(2))) + name.substring(3);
        }
        // use as is
        return name;
    }

    protected void exportMethodFor(Method method) {
        if (method.deprecated) return;
        String template = "content/CanvasMethod.txt";
        if (isTest) {
            template = "content/CanvasTestMethod.txt";        
            if (method.endTagForbidden) template = "content/CanvasTestSelfClosingMethod.txt";
        }
        
        System.out.print(String.format(StringResource.get(template)
                ,method.name
                ,method.selector
                ,method.description));
    }
}
