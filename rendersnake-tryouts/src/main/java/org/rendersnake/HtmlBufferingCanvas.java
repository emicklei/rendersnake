package org.rendersnake;

import static org.rendersnake.HtmlAttributesFactory.type;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rendersnake.error.RenderException;
import org.rendersnake.internal.CharactersWriteable;
import org.rendersnake.ext.servlet.HtmlServletCanvas;

/**
 * HtmlBufferingCanvas separates the Head and Body section of a HTML page.
 * Information for the Head section must be added by HTML element objects.
 * Information for the Body section can be added using the HtmlCanvas API. In
 * addition, HTML elements that need to be on the bottom of the page must be
 * added to the bodyElements set. Finally, Javascript statements that need to be
 * executed AFTER loading the page can be added to the onLoadScripts list.
 * 
 * The actual output is written when the _body() is sent.
 * 
 * @author ernest
 */
public class HtmlBufferingCanvas extends HtmlServletCanvas {

    // configurable
    public static int CANVAS_BODY_BUFFER_LENGTH = 4096;

    private Writer output;
    private DocType docType = DocType.HTML_4_01_Strict;
    private List<String> onLoadScripts;
    private HtmlAttributes htmlAttributes;
    private HtmlAttributes headAttributes;
    private OrderedRenderableSet headElements;
    private OrderedRenderableSet bodyElements;

    public HtmlBufferingCanvas(HttpServletRequest request, HttpServletResponse response, Writer out) {
        super(request, response, out);
        this.init();
    }

    public void init() {
        // swap output with new buffer
        output = out;
        out = new StringWriter(CANVAS_BODY_BUFFER_LENGTH);
    }

    public HtmlAttributes headAttributes() {
        if (headAttributes == null) {
            headAttributes = new HtmlAttributes();
        }
        return headAttributes;
    }

    public List<String> onLoadScripts() {
        if (onLoadScripts == null) {
            onLoadScripts = new ArrayList<String>();
        }
        return onLoadScripts;
    }

    public OrderedRenderableSet headElements() {
        if (headElements == null) {
            headElements = new OrderedRenderableSet();
        }
        return headElements;
    }

    @Override
    public HtmlCanvas _body() throws IOException {
        this.renderBodyElements();
        this.renderOnLoadScripts();
        super._body();
        this.copyBufferContentsToOutput();
        return this;
    }

    private void renderBodyElements() throws IOException {
        if (bodyElements != null) {
            for (Renderable each : bodyElements.getRenderables()) {
                this.render(each);
            }
        }
    }

    private void renderOnLoadScripts() throws IOException {
        if (onLoadScripts != null) {
            this.script(type("text/javascript")).cdata();
            this.write("function onLoad(){\n", false);
            for (String each : onLoadScripts) {
                this.write(each, false);
            }
            this.write("\n};", false);
            this._cdata()._script();
        }
    }

    @Override
    public HtmlCanvas html() throws IOException {
        // no effect
        return this;
    }

    @Override
    public HtmlCanvas html(CharactersWriteable attrs) throws IOException {
        // no effect
        htmlAttributes = (HtmlAttributes)attrs;
        return this;
    }

    @Override
    public HtmlCanvas head() throws IOException {
        // no effect
        return this;
    }

    @Override
    public HtmlCanvas head(CharactersWriteable attrs) throws IOException {
        // no effect
        headAttributes = (HtmlAttributes)attrs;
        return this;
    }

    @Override
    public HtmlCanvas _html() throws IOException {
        // no effect
        this.checkForClose("</html>");
        return this;
    }

    @Override
    public HtmlCanvas _head() throws IOException {
        // no effect
        this.checkForClose("</head>");
        return this;
    }

    private void checkForClose(String endElement) {
        // only check if end tags are on the stack
        if (openTagStack.isEmpty())
            return;
        if (!openTagStack.get(openTagStack.size()-1).equals(endElement))
            throw RenderException.unexpectedTag(openTagStack.get(openTagStack.size()-1), endElement);
    }

    public OrderedRenderableSet bodyElements() {
        if (bodyElements == null) {
            bodyElements = new OrderedRenderableSet();
        }
        return bodyElements;
    }

    /**
     * It allows you to send your partially ready HTML response to the browser
     * so that the browser can start fetching components while your backend is
     * busy with the rest of the HTML page. [source:yahoo]
     * 
     * @return
     */
    public HtmlBufferingCanvas flush() throws IOException {
        out.flush();
        return this;
    }

    private void copyBufferContentsToOutput() {
        try {
            HtmlCanvas outCanvas = new HtmlServletCanvas(request, response, output);
            outCanvas.render(docType);
            outCanvas.html(htmlAttributes);
            // add any head elements
            outCanvas.head(headAttributes);
            if (headElements != null) {
                for (Renderable each : headElements.getRenderables()) {
                    outCanvas.render(each);
                }
            }
            outCanvas._head();
            // copy out contents to output
            outCanvas.out.write(out.toString());
            outCanvas._html();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toHtml() {
        return output.toString();
    }
    
    // Tryout
    public CanvasMacros<HtmlBufferingCanvas> macros() {
        return new CanvasMacros<HtmlBufferingCanvas>(this);
    }
}
