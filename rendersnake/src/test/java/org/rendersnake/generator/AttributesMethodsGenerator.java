package org.rendersnake.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.rendersnake.StringResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AttributesMethodsGenerator extends DefaultHandler {

    class Attribute {
        public String name;
        public String arg;
        public String selector;
        public String description;
        public String since = "@since HTML4.01";

        public void since5(){
            since = "@since HTML5";
        }
        
        public boolean takesScript() {
        	return name.startsWith("on");
        }
    }

    protected Map<String, Attribute> attributesMap = new HashMap<String, Attribute>();

    protected boolean isFactory = false;
    protected boolean isTest = false;
    
    public static void main(String[] args) throws Exception {
        AttributesMethodsGenerator gen = new AttributesMethodsGenerator();
        gen.parse("../html-codegen/html4-doc.xml");
        if (args.length > 0) {
            gen.isFactory = args[0].equals("f");
            gen.isTest = args[1].equals("t");
        }
        gen.exportMethods();
    }
    
    protected void parse(String inputFilename) throws Exception {
        SAXParser p = SAXParserFactory.newInstance().newSAXParser();
        p.parse(new File(inputFilename), this);
    }

    @Override
    public void startElement(String uri, String localName, String qname, Attributes attrs) throws SAXException {
        if (!"attribute".equals(qname))
            return;
        Attribute attribute = new Attribute();
        attribute.name = attrs.getValue("name");
        attribute.selector = this.selectorFor(attribute.name);
        attribute.arg = attribute.selector;
        attribute.description = attrs.getValue("description");
        attributesMap.put(attribute.name, attribute);
    }

    protected void exportMethods() {
        for (Attribute each : attributesMap.values())
            this.exportMethodFor(each);
    }
    
    protected String selectorFor(String name) {
        // apply exceptions because of reserved words in the Java language
        if ("class".equals(name)) {
            return "class_";
        } else if ("for".equals(name)) {
            return "for_";
        } else if ("char".equals(name)) {
            return "char_";
        } else if (name.indexOf("-") > 0) {
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

    protected void exportMethodFor(Attribute attribute) {
        String template = "content/AttributeMethod.txt";
        if (isFactory) {
            template = "content/AttributeFactoryMethod.txt";
            if (isTest) {
                template = "content/AttributeFactoryTestMethod.txt";
            }
        } else {
            if (isTest) template = "content/AttributeTestMethod.txt";
        }
        if (attribute.arg == null) attribute.arg = attribute.name;
        System.out.print(String.format(StringResource.get(template)
                ,attribute.name
                ,attribute.selector
                ,attribute.description
                ,attribute.arg
                ,attribute.since
                ,attribute.takesScript() ? "addScript" : "add"));
    }
}
