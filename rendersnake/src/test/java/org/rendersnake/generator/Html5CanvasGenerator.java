package org.rendersnake.generator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Html5CanvasGenerator extends CanvasMethodsGenerator {
    Element rootElement;

    public static void main(String[] args) throws Exception {
        Html5CanvasGenerator gen = new Html5CanvasGenerator();
        gen.parse("../html-codegen/html5/tags.xml");
        if (args.length > 0) {
            gen.isTest = args[0].equals("t");
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
            Method m = this.buildMethod(row);
            this.exportMethodFor(m);
        }
    }

    // quick and dirty..
    private Method buildMethod(Node row) {
        Method m = new Method();
        NodeList td = row.getChildNodes();
        int i = 0;
        for (int c = 0; c < td.getLength(); c++) {
            Node column = td.item(c);
            String value = column.getTextContent();
            if (!StringUtils.isBlank(value)) { // skip the empty nodes
                if (0 == i) {
                    m.name = value;
                } else if (1 == i) {
                    m.description = value;
                }
                i++;
            }
        }
        m.selector = this.selectorFor(m.name);
        return m;
    }
}
