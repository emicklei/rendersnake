package org.rendersnake.site;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.tidy.Tidy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HtmlToRenderSnakeTranslator extends DefaultHandler {

    // http://www.elharo.com/blog/software-development/web-development/2007/01/29/all-empty-tags-in-html/
    public static String[] EMPTY_ELEMENTS = new String[]{"meta","br","hr","link","base","img","embed","param","area","col","input","frame","base","basefront","isindex"};
    
    // stats
    public static int TRANSLATIONS_OK = 0;
    public static int TRANSLATIONS_FAILED = 0;    
    public static int TRANSLATIONS_LINECOUNT = 0;    
    
    public String errorMessage = "";
    public int spaces = 4;
    private int indent = 0;
    public StringWriter out = new StringWriter();
    public String lastElementContent, lastElementName;
    
    public static void main(String[] args) throws Exception {
        HtmlToRenderSnakeTranslator translator = new HtmlToRenderSnakeTranslator();
        translator.translate(HtmlToRenderSnakeTranslator.class.getResourceAsStream("test.html"),true);
        System.out.println(translator.toJavaSource());
        System.out.println(translator.errorMessage);
    }

    public boolean isSuccess() {
        return errorMessage.length() == 0;
    }

    public void translate(InputStream rawInput, boolean useTidy) throws Exception {
        if (rawInput.available() == 0) {
            errorMessage = "Nothing to translate";
            return;
        }
        InputStream is = useTidy ? this.tidy(rawInput) : rawInput;
        StringWriter sw = new StringWriter();
        sw.write('<');
        sw.write("rs");
        sw.write(">\n");
        this.writeContentsOn(is, sw);
        sw.write("\n</");
        sw.write("rs");
        sw.write('>');
        SAXParser p = SAXParserFactory.newInstance().newSAXParser();
        try {
            p.parse(new ByteArrayInputStream(sw.toString().getBytes()), this);
            TRANSLATIONS_OK++;
            TRANSLATIONS_LINECOUNT += this.countLinesIn(out.toString());
        } catch (org.xml.sax.SAXParseException sax) {
            TRANSLATIONS_FAILED++;
            int column = sax.getColumnNumber();
            int line = sax.getLineNumber();
            errorMessage = String.format("At line=%s,column=%s an error was detected: %s\n\n%s", line, column, sax.getMessage(), this.getHintFromTo(line, column, sw.toString()));
        }
        is.close();
    }
    
    private int countLinesIn(String content) {
        int sum = 0;
        for (int i = 0; i < content.length(); i++) {
            sum += content.charAt(i) == '\n' ? 1 : 0;            
        }
        return sum;
    }

    private String getHintFromTo(int line, int column, String content) {
        String[] lines = content.split("\n");
        String hint = lines[line - 1];
        return hint.substring(0, column - 1) + "==>" + hint.substring(column, hint.length());
    }

    private void writeContentsOn(InputStream is, StringWriter sw) throws Exception {
        while (is.available() > 0) {
            sw.write(is.read());
        }
    }

    public String toJavaSource() {
        return out.toString();
    }

    @Override
    public void startElement(String uri, String localName, String qname, Attributes attrs) throws SAXException {
        if ("rs".equals(qname))
            return;
        // see if the lastElementContent must be written
        if (lastElementName != null && !lastElementName.equals(qname.toLowerCase())) {
            this.writeLastElementContent();
        }        
        this.doIndent();
        out.append('.');
        out.append(this.dashToUpper(qname));
        String attrExpr = this.buildAttributeInvocations(attrs);
        if (attrExpr.length() > 0)
            out.append('(').append(attrExpr).append(')');
        else
            out.append("()");
        out.append("\n");
        indent++;
        // remember for endElement
        lastElementName = qname.toLowerCase();
    }

    @Override
    public void endElement(String uri, String localName, String qname) throws SAXException {
        if ("rs".equals(qname))
            return;
        indent--;
        if (isEmptyElement(qname.toLowerCase())) return;
        
        // see if we can use the content() method here
        if (lastElementName.equals(qname.toLowerCase())) {
            out.append(".content(\"");
            out.append(lastElementContent);
            out.append("\")");
        } else {
            this.writeLastElementContent();                       
            this.doIndent();
            out.append("._");
            out.append(this.dashToUpper(qname));
            out.append("()");
        }
        out.append("\n");
        lastElementContent = null; // we used it        
    }

    private void writeLastElementContent() {
        if (null == lastElementContent) return;
        out.append(".write(\"");
        out.append(lastElementContent);
        out.append("\")\n");
    }

    private void doIndent() {
        for (int i = 0; i < indent * spaces; i++)
            out.append(' ');
    }

    public String buildAttributeInvocations(Attributes attrs) {
        StringWriter expression = new StringWriter();
        for (int i = 0; i < attrs.getLength(); i++) {
            String aname = attrs.getLocalName(i);
            String avalue = attrs.getValue(i);
            if (i > 0)
                expression.append('.');

            // reserved words
            if ("class".equals(aname))
                aname = "class_";
            else if ("for".equals(aname))
                aname = "for_";
            aname = this.dashToUpper(aname);

            expression.append(aname).append("(\"").append(avalue).append("\")");
        }
        return expression.toString();
    }

    private String dashToUpper(String in) {
        String out = in;
        if (in.indexOf('-') != -1) {
            int dash = in.indexOf('-');
            out = in.substring(0, dash) + Character.toUpperCase(in.charAt(dash+1)) + in.substring(dash+2);
        }
        return out;
    }
    
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (length == 0)
            return;
        String content = new String(ch, start, length).trim();
        if (content.length() == 0)
            return;
        this.doIndent();
        // escape quotes
        content = content.replaceAll("\"", "\\\\\"");
        content = content.replace("\n", " ");
        lastElementContent = content;
    }

    @Override
    public void endDocument() throws SAXException {
        out.append(";");
    }

    @Override
    public void startDocument() throws SAXException {
        out.append("html\n");
        indent++;
    }
    
    public boolean isEmptyElement(String elm) {
        for(String each : EMPTY_ELEMENTS) {
            if (each.equals(elm)) return true;
        }
        return false;
    }
    
    public InputStream tidy(InputStream is) {
        Tidy tidy = new Tidy();
        tidy.setXHTML(false); 
        tidy.setPrintBodyOnly(true);
        tidy.setDocType("loose");
        tidy.setXHTML(true);
        tidy.setForceOutput(true);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream(1024);
        tidy.parse(is,out);
        return new ByteArrayInputStream(out.toByteArray());
    }
}
