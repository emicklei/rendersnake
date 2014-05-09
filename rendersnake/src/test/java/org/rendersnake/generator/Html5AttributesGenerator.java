package org.rendersnake.generator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Html5AttributesGenerator extends AttributesMethodsGenerator {

    Element rootElement;

    public static void main(String[] args) throws Exception {
        Html5AttributesGenerator gen = new Html5AttributesGenerator();
        gen.parse("../html-codegen/html5/attributes.xml");
        if (args.length > 0) {
            gen.isFactory = args[0].equals("f");
            gen.isTest = args[1].equals("t");
        }
        gen.exportMethods();
    }

    protected void parse(String inputFilename) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFilename);
        rootElement = doc.getDocumentElement();
    }

    protected void exportMethods() {
        NodeList tr = rootElement.getElementsByTagName("tr");
        for (int r = 0; r < tr.getLength(); r++) {
            Node row = tr.item(r);
            Attribute attr = this.buildAttribute(row);
            // for HTML5 we only want the NEW
            if(attr.name.endsWith("New")) {            
                attr.name = attr.name.substring(0,attr.name.length()-4);
                attr.selector = this.selectorFor(attr.name);
                this.exportMethodFor(attr);
            }
        }
    }
    // quick and dirty..
    private Attribute buildAttribute(Node row) {
        Attribute attr = new Attribute();
        attr.since5();
        NodeList td = row.getChildNodes();
        int i = 0;
        for (int c=0;c<td.getLength();c++){
             Node column = td.item(c);
            String value = column.getTextContent();
            if (!StringUtils.isBlank(value)) {  // skip the empty nodes
                if (0 == i) {
                    attr.name = value;
                } else if (1 == i) {
                    attr.arg = value.replaceAll("\\W", "");  // replace everything that is not a word char
                } else if (2 == i) {
                    attr.description = value;
                }
                i++;
            }            
        }
        return attr;
    }
    
}
