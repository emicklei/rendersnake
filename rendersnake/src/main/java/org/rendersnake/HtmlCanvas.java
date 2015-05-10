package org.rendersnake;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.rendersnake.error.RenderException;
import org.rendersnake.internal.CharactersWriteable;
import org.rendersnake.internal.ContextMap;
import org.rendersnake.internal.SinkCanvas;
import org.rendersnake.internal.HtmlEscapeHandler;
import org.rendersnake.internal.StringEscapeUtils;

/**
 * HtmlCanvas provide the api to write HTML elements on a io.Writer.
 * It holds a PageContext for passing Objects to other Renderable instances.
 * 
 * @author e.micklei
 */
public class HtmlCanvas {
    /**
     * The handler that can escape characters of a String for proper HTML writing.
     */
    public static HtmlEscapeHandler HTML_ESCAPE_HANDLER;    
    static { StringEscapeUtils.init(); }
    /**
     * Intention revealing constant to emphasize that escaping for a text item is not needed
     */
    public final static boolean NO_ESCAPE = false;
    /**
     * The writer that will collect all HTML code.
     */
    protected Writer out;
    /**
     * Collection of written open elements to be closed.
     */
    protected ArrayList<String> openTagStack = new ArrayList<String>(INITIAL_STACK_CAPACITY);
    /**
     * Configurable parameter to nested element.
     */
    public static int INITIAL_STACK_CAPACITY = 32;
    /**
     * Id generator for generated elements and Javascript.
     */
    private int lastId = 0;
    /**
     * Map of key-value pairs to store domain data used in rendering.
     */
    private PageContext pageContext;
    /**
     * The helper class that provides methods for common HTML constructions.
     */
    private CanvasMacros<? extends HtmlCanvas> canvasMacros;
    /**
     * Create a new HtmlCanvas that writes its output on Writer.
     * @param output
     */    
    public HtmlCanvas(Writer output) {
        super();
        this.out = output;
    }
    /**
     * Create a HtmlCanvas that writes its output on a StringWriter.
     * This is for testing purposes.
     */
    public HtmlCanvas() {
        super();
        this.out = new StringWriter(1024);
    }
    /**
     * Return a helper object to write elements in a macro-like style.
     * @return
     */
    public CanvasMacros<?> macros() { 
        if (canvasMacros == null)
            this.canvasMacros = createMacros();
        return canvasMacros;
    }
    /**
     * Convenience method that calls write(aString) and closes the last opened tag.
     * The content (aString) will be HTML escaped.
     * @param aString
     * @return
     * @throws IOException
     */        
    public HtmlCanvas content(String aString) throws IOException {
        this.write(aString);
        return this.close();        
    }
    /**
     * Convenience method that calls write(aString) and closes the last opened tag.
     * The content (aString) will be HTML escaped if needed.
     * @param aString
     * @param escapeNeeded
     * @return
     * @throws IOException
     */
    public HtmlCanvas content(String aString, boolean escapeNeeded) throws IOException {
        this.write(aString,escapeNeeded);
        return this.close();        
    }
    /**
     * Create a new empty canvas based on the receiver.
     * NOTE: Id generation will no longer work for the local canvas.
     */
    public HtmlCanvas createLocalCanvas(){
        return new HtmlCanvas();
    }
    /**
     * Create a new CanvasMacros that provides convenience methods.
     * @return
     */
    public CanvasMacros<? extends HtmlCanvas> createMacros() {
        return new CanvasMacros<HtmlCanvas>(this);
    }
    /**
     * Answer the writer that is used to produce output.
     * @return
     */
    public Writer getOutputWriter(){
        return out;
    }    
    /**
     * Answer a String for use as a unique identifier for elements written using this html.
     *    
     * @return
     */
    public String nextId() {
        lastId++;
        return new StringBuilder()
            .append('i')
            .append('d')
            .append(lastId)
            .toString();
    }        
    /**
     * Answer a String with tracing information about the receiver.
     * 
     * @return String
     */
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("[depth=");
        buffer.append(openTagStack.size());
        buffer.append(']');
        buffer.append(openTagStack);
        return buffer.toString();
    }
    /**
     * Return the current contents of the output writer.
     * 
     * @return String
     */
    public String toHtml() {
        return out.toString();
    }  
    /**
     * Raises an illegal state exception if initialization by StringEscapeUtils has failed.
     * @return an actual implementation of the HtmlEscapeHandler
     */
    private static HtmlEscapeHandler getHtmlEscapeHandler() {
        if (HTML_ESCAPE_HANDLER == null)
            throw new IllegalStateException("HTML Escape Handler not set." +
            		"Either the Apache commons lang3 library is missing or" +
            		"you need to provide an implementation of HtmlEscapeHandler yourself.");
    	   return HTML_ESCAPE_HANDLER;
    	}    
    /**
     * Write text after HTML escaping it. No need to close().
     * 
     * @param unescapedString String , HTML or plain text or null
     * @return HTMLCanvas , the receiver
     * @throws IOException
     */
    public HtmlCanvas write(String unescapedString) throws IOException {
        if (unescapedString == null) return this;
        getHtmlEscapeHandler().escapeHtml(out, unescapedString);
        return this;
    }  
    
    /**
     * Write a character. No need to close().
     * 
     * @param character
     * @return HTMLCanvas , the receiver
     * @throws IOException
     */
    public HtmlCanvas write(char ch) throws IOException { out.append(ch); return this; }    
    
    /**
     * Write some text. If escapedNeeded then HTML escape the characters while writing it.
     * 
     * @param text
     *            String , HTML or plain text
     * @param escapeNeeded
     *            , boolean (e.g. NO_ESCAPE)
     * @return HTMLCanvas , the receiver
     * @throws IOException
     */
    public HtmlCanvas write(String text, boolean escapeNeeded) throws IOException {
        if (text == null) return this;
        if (escapeNeeded) {
            getHtmlEscapeHandler().escapeHtml(out, text);
        } else {
            this.out.write(text);
        }
        return this;
    }    
    /**
     * Write the opening of a CDATA section (not really a HTML element).
     * @return HTMLCanvas , the receiver
     * @throws IOException
     */
    public HtmlCanvas cdata() throws IOException {
        this.out.write("/*<![CDATA[*/");
        openTagStack.add("/*]]>*/");
        return this;
    }    
    /**
     * Write the closing of a CDATA section (not really a HTML element).
     * @return HTMLCanvas , the receiver
     * @throws IOException
     */    
    public HtmlCanvas _cdata() throws IOException {
        return this.close("/*]]>*/");
    }    
    /**
     * Close the most recent opened tag.
     * 
     * @return the receiver, a HtmlCanvas
     * @throws IOException, RenderException
     */
    public HtmlCanvas close() throws IOException {
        if (openTagStack.isEmpty())
            throw RenderException.emptyStack();
        out.write(openTagStack.remove(openTagStack.size()-1));
        return this;
    }

    /**
     * Close the most recent opened tag and expect it to be #expectedTag
     * 
     * @return the receiver, a HtmlCanvas
     * @throws IOException, RenderException
     */
    public HtmlCanvas close(String expectedTag) throws IOException {
        if (openTagStack.isEmpty())
            throw RenderException.emptyStack();
    	String popped = openTagStack.remove(openTagStack.size()-1);
        if (!popped.equals(expectedTag))
            throw RenderException.unexpectedTag(popped,expectedTag);
        out.write(popped);
        return this;
    }
    
    /**
     * Render the component using the receiver. If available, prepare the
     * pageContext for accessing variables by the components.
     * 
     * @param component a Renderable instance | null
     * @return the receiver this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */  
    public HtmlCanvas render(Renderable component) throws IOException {
        // Allow undefined component as argument. This is a noop.
        if (component == null) return this;
        
        if (pageContext != null) {
            pageContext.beginRender();
            component.renderOn(this);
            pageContext.endRender();
        } else {
            component.renderOn(this);
        }
        return this;
    }
    /**
     * Conditionally render a component based on the condition.
     * @param component
     * @param condition
     * @return
     * @throws IOException
     */
    public HtmlCanvas render_if(Renderable component, boolean condition) throws IOException {
        return condition ? this.render(component) : this;
    }
    /**
     * Factory method for an HtmlAttributes instance.
     * <p/>
     * This allows for subclass specialization. Also conforms to the fluent
     * interface compared to "new HtmlAttributes()"
     * 
     * @return a new ToAttributesString
     */
    public HtmlAttributes attributes() {
        return new HtmlAttributes();
    }

    /**
     * Write the open tag &lt;{tagName}>. Requires close().
     * 
     * @param tagName
     *            String, cannot be null
     * @return the receiver, a HtmlCanvas
     * @throws IOException
     * 
     * @see #close()
     */
    public HtmlCanvas tag(String tagName) throws IOException {
        if (tagName == null)
            throw RenderException.nullTag();
        out.write('<');
        out.write(tagName);
        out.write('>');
        StringBuilder buffer = new StringBuilder(tagName.length()+3);
        buffer.append('<').append('/').append(tagName).append('>');
        openTagStack.add(buffer.toString());
        return this;
    }

    public HtmlCanvas tag_close(String tagName) throws IOException {
        if (tagName == null)
            throw RenderException.nullTag();
        StringBuilder buffer = new StringBuilder(tagName.length()+3);
        buffer.append('<').append('/').append(tagName).append('>');
        return this.close(buffer.toString());        
    }
    
    /**
     * Write the open tag with attributes {attrs}. Requires close().
     * 
     * @param tagName
     *            String, cannot be null
     * @param attrs
     *            ToAttributesString, cannot be null
     * @return the receiver, aHtmlCanvas
     * @throws IOException
     * 
     * @see #close()
     */
    public HtmlCanvas tag(String tagName, CharactersWriteable attrs) throws IOException {
        if (tagName == null)
            throw RenderException.nullTag();
        out.write('<');
        out.append(tagName);
        attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</" + tagName + '>');
        return this;
    }
    
    /**
     * Answer whether the receiver has instantiated a PageContext.
     * @return
     */
    public boolean hasPageContext() { return pageContext != null; }
    
    /**
     * Answer the pageContext. Create one if absent.
     * 
     * @return the context
     */
    public PageContext getPageContext() {
        if (null == pageContext)
            pageContext = this.createPageContext();
        return pageContext;
    }
    /**
     * use RequestUtils.getSession(html) instead.
     * @return
     */
    @Deprecated     
    public ContextMap getSession() {
        return RequestUtils.getSession(this);
    }
    /**
     * use RequestUtils.getParameters(html) instead.
     * @return
     */
    @Deprecated 
    public ContextMap getRequestParameters() {
        return RequestUtils.getParameters(this);
    }
    /**
     * use RequestUtils.getPathParameters(html) instead.
     * @return
     */
    @Deprecated 
    public ContextMap getPathParameters() {
        return this.getPageContext().getContextMap(PageContext.REQUEST_PATH);
    }    
    /**
     * If the condition is true then return the receiver
     * otherwise return a SinkCanvas that consumes all tags until the last is closed.
     * @param condition
     * @param component
     * @return
     * @throws IOException
     */
    public HtmlCanvas if_(boolean condition) throws IOException {
        if (condition)
            return this;
        else
            return new SinkCanvas(this);            
    }
    public HtmlCanvas _if() throws IOException {
        // NOOP
        return this;
    }
    
    /**
     * Return a new PageContext for storing component-scoped variables.
     * @return a new PageContext
     */
    protected PageContext createPageContext() { return new PageContext(); }

    // ////////////////////////////////////////////////////////////////
    //
    // Methods below are generated using XSLT (see /html-codegen). DO NOT EDIT
    //
    // ////////////////////////////////////////////////////////////////

    /**
     * Opens the <em>a</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_a()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas a() throws IOException {
        out.write("<a>");
        openTagStack.add("</a>");
        return this;
    }

    /**
     * Opens the <em>a</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_a()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>named link end
     * <dt>href<dd>URI for linked resource
     * <dt>hreflang<dd>language code
     * <dt>type<dd>advisory content type
     * <dt>rel<dd>forward link types
     * <dt>rev<dd>reverse link types
     * <dt>charset<dd>char encoding of linked resource
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>shape<dd>for use with client-side image maps
     * <dt>coords<dd>for use with client-side image maps
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>the element got the focus
     * <dt>onblur<dd>the element lost the focus
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>target<dd>render in this frame
     * <dt>tabindex<dd>position in tabbing order
     * <dt>accesskey<dd>accessibility key character
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas a(CharactersWriteable attrs) throws IOException {
        out.write("<a");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</a>");
        return this;
    }

	/**
     * Closes the <em>a</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _a() throws IOException {
        return this.close("</a>");
    }

    /**
     * Opens the <em>abbr</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_abbr()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas abbr() throws IOException {
        out.write("<abbr>");
        openTagStack.add("</abbr>");
        return this;
    }

    /**
     * Opens the <em>abbr</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_abbr()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas abbr(CharactersWriteable attrs) throws IOException {
        out.write("<abbr");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</abbr>");
        return this;
    }

	/**
     * Closes the <em>abbr</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _abbr() throws IOException {
        return this.close("</abbr>");
    }

    /**
     * Opens the <em>acronym</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_acronym()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas acronym() throws IOException {
        out.write("<acronym>");
        openTagStack.add("</acronym>");
        return this;
    }

    /**
     * Opens the <em>acronym</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_acronym()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas acronym(CharactersWriteable attrs) throws IOException {
        out.write("<acronym");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</acronym>");
        return this;
    }

	/**
     * Closes the <em>acronym</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _acronym() throws IOException {
        return this.close("</acronym>");
    }

    /**
     * Opens the <em>em</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_em()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas em() throws IOException {
        out.write("<em>");
        openTagStack.add("</em>");
        return this;
    }

    /**
     * Opens the <em>em</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_em()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas em(CharactersWriteable attrs) throws IOException {
        out.write("<em");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</em>");
        return this;
    }

	/**
     * Closes the <em>em</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _em() throws IOException {
        return this.close("</em>");
    }

    /**
     * Opens the <em>strong</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_strong()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas strong() throws IOException {
        out.write("<strong>");
        openTagStack.add("</strong>");
        return this;
    }

    /**
     * Opens the <em>strong</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_strong()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas strong(CharactersWriteable attrs) throws IOException {
        out.write("<strong");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</strong>");
        return this;
    }

	/**
     * Closes the <em>strong</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _strong() throws IOException {
        return this.close("</strong>");
    }

    /**
     * Opens the <em>cite</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_cite()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas cite() throws IOException {
        out.write("<cite>");
        openTagStack.add("</cite>");
        return this;
    }

    /**
     * Opens the <em>cite</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_cite()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas cite(CharactersWriteable attrs) throws IOException {
        out.write("<cite");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</cite>");
        return this;
    }

	/**
     * Closes the <em>cite</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _cite() throws IOException {
        return this.close("</cite>");
    }

    /**
     * Opens the <em>dfn</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_dfn()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dfn() throws IOException {
        out.write("<dfn>");
        openTagStack.add("</dfn>");
        return this;
    }

    /**
     * Opens the <em>dfn</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_dfn()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dfn(CharactersWriteable attrs) throws IOException {
        out.write("<dfn");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</dfn>");
        return this;
    }

	/**
     * Closes the <em>dfn</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _dfn() throws IOException {
        return this.close("</dfn>");
    }

    /**
     * Opens the <em>code</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_code()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas code() throws IOException {
        out.write("<code>");
        openTagStack.add("</code>");
        return this;
    }

    /**
     * Opens the <em>code</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_code()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas code(CharactersWriteable attrs) throws IOException {
        out.write("<code");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</code>");
        return this;
    }

	/**
     * Closes the <em>code</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _code() throws IOException {
        return this.close("</code>");
    }

    /**
     * Opens the <em>samp</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_samp()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas samp() throws IOException {
        out.write("<samp>");
        openTagStack.add("</samp>");
        return this;
    }

    /**
     * Opens the <em>samp</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_samp()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas samp(CharactersWriteable attrs) throws IOException {
        out.write("<samp");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</samp>");
        return this;
    }

	/**
     * Closes the <em>samp</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _samp() throws IOException {
        return this.close("</samp>");
    }

    /**
     * Opens the <em>kbd</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_kbd()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas kbd() throws IOException {
        out.write("<kbd>");
        openTagStack.add("</kbd>");
        return this;
    }

    /**
     * Opens the <em>kbd</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_kbd()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas kbd(CharactersWriteable attrs) throws IOException {
        out.write("<kbd");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</kbd>");
        return this;
    }

	/**
     * Closes the <em>kbd</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _kbd() throws IOException {
        return this.close("</kbd>");
    }

    /**
     * Opens the <em>var</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_var()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas var() throws IOException {
        out.write("<var>");
        openTagStack.add("</var>");
        return this;
    }

    /**
     * Opens the <em>var</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_var()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas var(CharactersWriteable attrs) throws IOException {
        out.write("<var");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</var>");
        return this;
    }

	/**
     * Closes the <em>var</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _var() throws IOException {
        return this.close("</var>");
    }

    /**
     * Opens the <em>address</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_address()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas address() throws IOException {
        out.write("<address>");
        openTagStack.add("</address>");
        return this;
    }

    /**
     * Opens the <em>address</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_address()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas address(CharactersWriteable attrs) throws IOException {
        out.write("<address");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</address>");
        return this;
    }

	/**
     * Closes the <em>address</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _address() throws IOException {
        return this.close("</address>");
    }

    /**
     * Opens the (deprecated) <em>applet</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_applet()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas applet() throws IOException {
        out.write("<applet>");
        openTagStack.add("</applet>");
        return this;
    }

    /**
     * Opens the <em>applet</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_applet()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>codebase<dd>optional base URI for applet
     * <dt>code<dd>applet class file
     * <dt>name<dd>allows applets to find each other
     * <dt>archive<dd>comma separated archive list
     * <dt>object<dd>serialized applet file
     * <dt>width<dd>initial width
     * <dt>height<dd>initial height
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>alt<dd>short description
     * <dt>align<dd>vertical or horizontal alignment
     * <dt>hspace<dd>horizontal gutter
     * <dt>vspace<dd>vertical gutter
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas applet(CharactersWriteable attrs) throws IOException {
        out.write("<applet");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</applet>");
        return this;
    }

	/**
     * Closes the <em>applet</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _applet() throws IOException {
        return this.close("</applet>");
    }

    /**
     * Opens the <em>area</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>coords<dd>comma separated list of lengths
     * <dt>shape<dd>controls interpretation of coords
     * <dt>nohref<dd>this region has no action
     * <dt>name<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>shape<dd>controls interpretation of coords
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>the element got the focus
     * <dt>onblur<dd>the element lost the focus
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>target<dd>render in this frame
     * <dt>tabindex<dd>position in tabbing order
     * <dt>accesskey<dd>accessibility key character
     * <dt>href<dd>URI for linked resource
     * <dt>alt<dd>short description
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas area(CharactersWriteable attrs) throws IOException {
        out.write("<area");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');
        return this;
    }

    /**
     * Opens the <em>map</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_map()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas map() throws IOException {
        out.write("<map>");
        openTagStack.add("</map>");
        return this;
    }

    /**
     * Opens the <em>map</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_map()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>for reference by usemap
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas map(CharactersWriteable attrs) throws IOException {
        out.write("<map");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</map>");
        return this;
    }

	/**
     * Closes the <em>map</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _map() throws IOException {
        return this.close("</map>");
    }

    /**
     * Opens the <em>b</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_b()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas b() throws IOException {
        out.write("<b>");
        openTagStack.add("</b>");
        return this;
    }

    /**
     * Opens the <em>b</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_b()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas b(CharactersWriteable attrs) throws IOException {
        out.write("<b");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</b>");
        return this;
    }

	/**
     * Closes the <em>b</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _b() throws IOException {
        return this.close("</b>");
    }

    /**
     * Opens the <em>tt</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_tt()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tt() throws IOException {
        out.write("<tt>");
        openTagStack.add("</tt>");
        return this;
    }

    /**
     * Opens the <em>tt</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_tt()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tt(CharactersWriteable attrs) throws IOException {
        out.write("<tt");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</tt>");
        return this;
    }

	/**
     * Closes the <em>tt</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _tt() throws IOException {
        return this.close("</tt>");
    }

    /**
     * Opens the <em>i</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_i()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas i() throws IOException {
        out.write("<i>");
        openTagStack.add("</i>");
        return this;
    }

    /**
     * Opens the <em>i</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_i()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas i(CharactersWriteable attrs) throws IOException {
        out.write("<i");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</i>");
        return this;
    }

	/**
     * Closes the <em>i</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _i() throws IOException {
        return this.close("</i>");
    }

    /**
     * Opens the <em>big</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_big()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas big() throws IOException {
        out.write("<big>");
        openTagStack.add("</big>");
        return this;
    }

    /**
     * Opens the <em>big</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_big()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas big(CharactersWriteable attrs) throws IOException {
        out.write("<big");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</big>");
        return this;
    }

	/**
     * Closes the <em>big</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _big() throws IOException {
        return this.close("</big>");
    }

    /**
     * Opens the <em>small</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_small()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas small() throws IOException {
        out.write("<small>");
        openTagStack.add("</small>");
        return this;
    }

    /**
     * Opens the <em>small</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_small()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas small(CharactersWriteable attrs) throws IOException {
        out.write("<small");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</small>");
        return this;
    }

	/**
     * Closes the <em>small</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _small() throws IOException {
        return this.close("</small>");
    }

    /**
     * Opens the (deprecated) <em>strike</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_strike()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas strike() throws IOException {
        out.write("<strike>");
        openTagStack.add("</strike>");
        return this;
    }

    /**
     * Opens the <em>strike</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_strike()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas strike(CharactersWriteable attrs) throws IOException {
        out.write("<strike");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</strike>");
        return this;
    }

	/**
     * Closes the <em>strike</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _strike() throws IOException {
        return this.close("</strike>");
    }

    /**
     * Opens the (deprecated) <em>s</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_s()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas s() throws IOException {
        out.write("<s>");
        openTagStack.add("</s>");
        return this;
    }

    /**
     * Opens the <em>s</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_s()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas s(CharactersWriteable attrs) throws IOException {
        out.write("<s");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</s>");
        return this;
    }

	/**
     * Closes the <em>s</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _s() throws IOException {
        return this.close("</s>");
    }

    /**
     * Opens the (deprecated) <em>u</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_u()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas u() throws IOException {
        out.write("<u>");
        openTagStack.add("</u>");
        return this;
    }

    /**
     * Opens the <em>u</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_u()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas u(CharactersWriteable attrs) throws IOException {
        out.write("<u");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</u>");
        return this;
    }

	/**
     * Closes the <em>u</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _u() throws IOException {
        return this.close("</u>");
    }

    /**
     * Opens the <em>base</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>href<dd>URI that acts as base URI
     * <dt>target<dd>render in this frame
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas base(CharactersWriteable attrs) throws IOException {
        out.write("<base");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');
        return this;
    }

    /**
     * Opens the (deprecated) <em>basefont</em> tag, without any attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas basefont() throws IOException {
        out.write("<basefont>");
        openTagStack.add("</basefont>");
        return this;
    }

    /**
     * Opens the <em>basefont</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>size<dd>base font size for FONT elements
     * <dt>color<dd>text color
     * <dt>face<dd>comma separated list of font names
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>
     * <dt>lang<dd>
     * <dt>dir<dd>
     * <dt>title<dd>
     * <dt>style<dd>
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas basefont(CharactersWriteable attrs) throws IOException {
        out.write("<basefont");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</basefont>");
        return this;
    }

    /**
     * Opens the (deprecated) <em>font</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_font()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas font() throws IOException {
        out.write("<font>");
        openTagStack.add("</font>");
        return this;
    }

    /**
     * Opens the <em>font</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_font()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>size<dd>[+|-]nn e.g. size=+1, size=4
     * <dt>color<dd>text color
     * <dt>face<dd>comma separated list of font names
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas font(CharactersWriteable attrs) throws IOException {
        out.write("<font");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</font>");
        return this;
    }

	/**
     * Closes the <em>font</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _font() throws IOException {
        return this.close("</font>");
    }

    /**
     * Opens the <em>bdo</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_bdo()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas bdo() throws IOException {
        out.write("<bdo>");
        openTagStack.add("</bdo>");
        return this;
    }

    /**
     * Opens the <em>bdo</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_bdo()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>dir<dd>directionality
     * <dt>lang<dd>language code
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas bdo(CharactersWriteable attrs) throws IOException {
        out.write("<bdo");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</bdo>");
        return this;
    }

	/**
     * Closes the <em>bdo</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _bdo() throws IOException {
        return this.close("</bdo>");
    }

    /**
     * Opens the <em>body</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_body()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas body() throws IOException {
        out.write("<body>");
        openTagStack.add("</body>");
        return this;
    }

    /**
     * Opens the <em>body</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_body()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>background<dd>texture tile for document background
     * <dt>text<dd>document text color
     * <dt>link<dd>color of links
     * <dt>vlink<dd>color of visited links
     * <dt>alink<dd>color of selected links
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>bgcolor<dd>document background color
     * <dt>onload<dd>the document has been loaded
     * <dt>onunload<dd>the document has been removed
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas body(CharactersWriteable attrs) throws IOException {
        out.write("<body");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</body>");
        return this;
    }

	/**
     * Closes the <em>body</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _body() throws IOException {
        return this.close("</body>");
    }

    /**
     * Opens the <em>br</em> tag, without any attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas br() throws IOException {
        out.write("<br/>");
        return this;
    }

    /**
     * Opens the <em>br</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>clear<dd>control of text flow
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas br(CharactersWriteable attrs) throws IOException {
        out.write("<br");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write("/>");
        return this;
    }

    /**
     * Opens the <em>button</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_button()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas button() throws IOException {
        out.write("<button>");
        openTagStack.add("</button>");
        return this;
    }

    /**
     * Opens the <em>button</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_button()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>
     * <dt>value<dd>sent to server when submitted
     * <dt>type<dd>for use as form button
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>disabled<dd>unavailable in this context
     * <dt>accesskey<dd>accessibility key character
     * <dt>tabindex<dd>position in tabbing order
     * <dt>onblure<dd>
     * <dt>onfocus<dd>the element got the focus
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas button(CharactersWriteable attrs) throws IOException {
        out.write("<button");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</button>");
        return this;
    }

	/**
     * Closes the <em>button</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _button() throws IOException {
        return this.close("</button>");
    }

    /**
     * Opens the <em>caption</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_caption()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas caption() throws IOException {
        out.write("<caption>");
        openTagStack.add("</caption>");
        return this;
    }

    /**
     * Opens the <em>caption</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_caption()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>align<dd>relative to table
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>lang<dd>language code
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas caption(CharactersWriteable attrs) throws IOException {
        out.write("<caption");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</caption>");
        return this;
    }

	/**
     * Closes the <em>caption</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _caption() throws IOException {
        return this.close("</caption>");
    }

    /**
     * Opens the (deprecated) <em>center</em> tag, without any attributes.
     * This tag does not support any attributes.
     *
     * <p>Close this tag by calling {@link #_center()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas center() throws IOException {
        out.write("<center>");
        openTagStack.add("</center>");
        return this;
    }

	/**
     * Closes the (deprecated) <em>center</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    @Deprecated
    public HtmlCanvas _center() throws IOException {
    	return this.close("</center>");
    }
    /**
     * Opens the <em>col</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>span<dd>COL attributes affect N columns
     * <dt>width<dd>column width specification
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * <dt>valign<dd>vertical alignment in cells
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas col(CharactersWriteable attrs) throws IOException {
        out.write("<col");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');        
        return this;
    }

    /**
     * Opens the <em>colgroup</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_colgroup()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas colgroup() throws IOException {
        out.write("<colgroup>");
        openTagStack.add("</colgroup>");
        return this;
    }

    /**
     * Opens the <em>colgroup</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_colgroup()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>span<dd>default number of columns in group
     * <dt>width<dd>default width for enclosed COLs
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * <dt>valign<dd>vertical alignment in cells
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas colgroup(CharactersWriteable attrs) throws IOException {
        out.write("<colgroup");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</colgroup>");
        return this;
    }

	/**
     * Closes the <em>colgroup</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _colgroup() throws IOException {
        return this.close("</colgroup>");
    }

    /**
     * Opens the <em>dd</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_dd()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dd() throws IOException {
        out.write("<dd>");
        openTagStack.add("</dd>");
        return this;
    }

    /**
     * Opens the <em>dd</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_dd()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>char<dd>
     * <dt>charoff<dd>
     * <dt>valign<dd>
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dd(CharactersWriteable attrs) throws IOException {
        out.write("<dd");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</dd>");
        return this;
    }

	/**
     * Closes the <em>dd</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _dd() throws IOException {
        return this.close("</dd>");
    }

    /**
     * Opens the <em>dl</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_dl()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dl() throws IOException {
        out.write("<dl>");
        openTagStack.add("</dl>");
        return this;
    }

    /**
     * Opens the <em>dl</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_dl()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>char<dd>
     * <dt>charoff<dd>
     * <dt>valign<dd>
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dl(CharactersWriteable attrs) throws IOException {
        out.write("<dl");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</dl>");
        return this;
    }

	/**
     * Closes the <em>dl</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _dl() throws IOException {
        return this.close("</dl>");
    }

    /**
     * Opens the <em>dt</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_dt()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dt() throws IOException {
        out.write("<dt>");
        openTagStack.add("</dt>");
        return this;
    }

    /**
     * Opens the <em>dt</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_dt()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>char<dd>
     * <dt>charoff<dd>
     * <dt>valign<dd>
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas dt(CharactersWriteable attrs) throws IOException {
        out.write("<dt");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</dt>");
        return this;
    }

	/**
     * Closes the <em>dt</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _dt() throws IOException {
        return this.close("</dt>");
    }

    /**
     * Opens the <em>del</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_del()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas del() throws IOException {
        out.write("<del>");
        openTagStack.add("</del>");
        return this;
    }

    /**
     * Opens the <em>del</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_del()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>cite<dd>info on reason for change
     * <dt>datetime<dd>date and time of change
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas del(CharactersWriteable attrs) throws IOException {
        out.write("<del");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</del>");
        return this;
    }

	/**
     * Closes the <em>del</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _del() throws IOException {
        return this.close("</del>");
    }

    /**
     * Opens the <em>ins</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_ins()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas ins() throws IOException {
        out.write("<ins>");
        openTagStack.add("</ins>");
        return this;
    }

    /**
     * Opens the <em>ins</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_ins()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>cite<dd>info on reason for change
     * <dt>datetime<dd>date and time of change
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas ins(CharactersWriteable attrs) throws IOException {
        out.write("<ins");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</ins>");
        return this;
    }

	/**
     * Closes the <em>ins</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _ins() throws IOException {
        return this.close("</ins>");
    }

    /**
     * Opens the (deprecated) <em>dir</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_dir()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas dir() throws IOException {
        out.write("<dir>");
        openTagStack.add("</dir>");
        return this;
    }

    /**
     * Opens the <em>dir</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_dir()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas dir(CharactersWriteable attrs) throws IOException {
        out.write("<dir");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</dir>");
        return this;
    }

	/**
     * Closes the <em>dir</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _dir() throws IOException {
        return this.close("</dir>");
    }

    /**
     * Opens the (deprecated) <em>menu</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_menu()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas menu() throws IOException {
        out.write("<menu>");
        openTagStack.add("</menu>");
        return this;
    }

    /**
     * Opens the <em>menu</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_menu()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas menu(CharactersWriteable attrs) throws IOException {
        out.write("<menu");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</menu>");
        return this;
    }

	/**
     * Closes the <em>menu</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _menu() throws IOException {
        return this.close("</menu>");
    }

    /**
     * Opens the <em>div</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_div()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas div() throws IOException {
        out.write("<div>");
        openTagStack.add("</div>");
        return this;
    }

    /**
     * Opens the <em>div</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_div()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas div(CharactersWriteable attrs) throws IOException {
        out.write("<div");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</div>");
        return this;
    }

	/**
     * Closes the <em>div</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _div() throws IOException {
        return this.close("</div>");
    }

    /**
     * Opens the <em>span</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_span()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas span() throws IOException {
        out.write("<span>");
        openTagStack.add("</span>");
        return this;
    }

    /**
     * Opens the <em>span</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_span()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas span(CharactersWriteable attrs) throws IOException {
        out.write("<span");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</span>");
        return this;
    }

	/**
     * Closes the <em>span</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _span() throws IOException {
        return this.close("</span>");
    }

    /**
     * Opens the <em>fieldset</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_fieldset()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas fieldset() throws IOException {
        out.write("<fieldset>");
        openTagStack.add("</fieldset>");
        return this;
    }

    /**
     * Opens the <em>fieldset</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_fieldset()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>align<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>accesskey<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas fieldset(CharactersWriteable attrs) throws IOException {
        out.write("<fieldset");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</fieldset>");
        return this;
    }

	/**
     * Closes the <em>fieldset</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _fieldset() throws IOException {
        return this.close("</fieldset>");
    }

    /**
     * Opens the <em>form</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_form()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas form() throws IOException {
        out.write("<form>");
        openTagStack.add("</form>");
        return this;
    }

    /**
     * Opens the <em>form</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_form()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>action<dd>server-side form handler
     * <dt>method<dd>HTTP method used to submit the form
     * <dt>enctype<dd>
     * <dt>accept-charset<dd>list of supported charsets
     * <dt>accept<dd>
     * <dt>name<dd>name of form for scripting
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>target<dd>render in this frame
     * <dt>onsubmit<dd>the form was submitted
     * <dt>onreset<dd>the form was reset
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas form(CharactersWriteable attrs) throws IOException {
        out.write("<form");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</form>");
        return this;
    }

	/**
     * Closes the <em>form</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _form() throws IOException {
        return this.close("</form>");
    }
    /**
     * Opens the <em>frame</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>name of frame for targetting
     * <dt>longdesc<dd>link to long description (complements title)
     * <dt>src<dd>source of frame content
     * <dt>noresize<dd>allow users to resize frames?
     * <dt>scrolling<dd>scrollbar or none
     * <dt>frameborder<dd>request frame borders?
     * <dt>margin-width<dd>
     * <dt>margin-height<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas frame(CharactersWriteable attrs) throws IOException {
        out.write("<frame");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');
        return this;
    }

    /**
     * Opens the <em>frameset</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_frameset()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas frameset() throws IOException {
        out.write("<frameset>");
        openTagStack.add("</frameset>");
        return this;
    }

    /**
     * Opens the <em>frameset</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_frameset()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>rows<dd>list of lengths, default: 100% (1 row)
     * <dt>cols<dd>list of lengths, default: 100% (1 col)
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onload<dd>all the frames have been loaded
     * <dt>onunload<dd>all the frames have been removed
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas frameset(CharactersWriteable attrs) throws IOException {
        out.write("<frameset");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</frameset>");
        return this;
    }

	/**
     * Closes the <em>frameset</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _frameset() throws IOException {
        return this.close("</frameset>");
    }

    /**
     * Opens the <em>h1</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_h1()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h1() throws IOException {
        out.write("<h1>");
        openTagStack.add("</h1>");
        return this;
    }

    /**
     * Opens the <em>h1</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_h1()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h1(CharactersWriteable attrs) throws IOException {
        out.write("<h1");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</h1>");
        return this;
    }

	/**
     * Closes the <em>h1</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _h1() throws IOException {
        return this.close("</h1>");
    }

    /**
     * Opens the <em>h2</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_h2()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h2() throws IOException {
        out.write("<h2>");
        openTagStack.add("</h2>");
        return this;
    }

    /**
     * Opens the <em>h2</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_h2()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h2(CharactersWriteable attrs) throws IOException {
        out.write("<h2");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</h2>");
        return this;
    }

	/**
     * Closes the <em>h2</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _h2() throws IOException {
        return this.close("</h2>");
    }

    /**
     * Opens the <em>h3</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_h3()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h3() throws IOException {
        out.write("<h3>");
        openTagStack.add("</h3>");
        return this;
    }

    /**
     * Opens the <em>h3</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_h3()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h3(CharactersWriteable attrs) throws IOException {
        out.write("<h3");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</h3>");
        return this;
    }

	/**
     * Closes the <em>h3</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _h3() throws IOException {
        return this.close("</h3>");
    }

    /**
     * Opens the <em>h4</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_h4()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h4() throws IOException {
        out.write("<h4>");
        openTagStack.add("</h4>");
        return this;
    }

    /**
     * Opens the <em>h4</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_h4()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h4(CharactersWriteable attrs) throws IOException {
        out.write("<h4");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</h4>");
        return this;
    }

	/**
     * Closes the <em>h4</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _h4() throws IOException {
        return this.close("</h4>");
    }

    /**
     * Opens the <em>h5</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_h5()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h5() throws IOException {
        out.write("<h5>");
        openTagStack.add("</h5>");
        return this;
    }

    /**
     * Opens the <em>h5</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_h5()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h5(CharactersWriteable attrs) throws IOException {
        out.write("<h5");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</h5>");
        return this;
    }

	/**
     * Closes the <em>h5</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _h5() throws IOException {
        return this.close("</h5>");
    }

    /**
     * Opens the <em>h6</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_h6()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h6() throws IOException {
        out.write("<h6>");
        openTagStack.add("</h6>");
        return this;
    }

    /**
     * Opens the <em>h6</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_h6()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas h6(CharactersWriteable attrs) throws IOException {
        out.write("<h6");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</h6>");
        return this;
    }

	/**
     * Closes the <em>h6</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _h6() throws IOException {
        return this.close("</h6>");
    }

    /**
     * Opens the <em>head</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_head()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas head() throws IOException {
        out.write("<head>");
        openTagStack.add("</head>");
        return this;
    }

    /**
     * Opens the <em>head</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_head()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>profile<dd>named dictionary of meta info
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas head(CharactersWriteable attrs) throws IOException {
        out.write("<head");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</head>");
        return this;
    }

	/**
     * Closes the <em>head</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _head() throws IOException {
        return this.close("</head>");
    }

    /**
     * Opens the <em>hr</em> tag, without any attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas hr() throws IOException {
        out.write("<hr/>");
        return this;
    }

    /**
     * Opens the <em>hr</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>align<dd>
     * <dt>noshade<dd>
     * <dt>size<dd>
     * <dt>width<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas hr(CharactersWriteable attrs) throws IOException {
        out.write("<hr");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write("/>");
        return this;
    }

    /**
     * Opens the <em>html</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_html()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas html() throws IOException {
        out.write("<html>");
        openTagStack.add("</html>");
        return this;
    }

    /**
     * Opens the <em>html</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_html()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>version<dd>Constant
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas html(CharactersWriteable attrs) throws IOException {
        out.write("<html");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</html>");
        return this;
    }

	/**
     * Closes the <em>html</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _html() throws IOException {
        return this.close("</html>");
    }

    /**
     * Opens the <em>iframe</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_iframe()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas iframe() throws IOException {
        out.write("<iframe>");
        openTagStack.add("</iframe>");
        return this;
    }

    /**
     * Opens the <em>iframe</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_iframe()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>longdesc<dd>link to long description (complements title)
     * <dt>name<dd>name of frame for targetting
     * <dt>width<dd>frame width
     * <dt>height<dd>frame height
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>src<dd>source of frame content
     * <dt>frameborder<dd>request frame borders?
     * <dt>marginwidth<dd>margin widths in pixels
     * <dt>marginheight<dd>margin height in pixels
     * <dt>scrolling<dd>scrollbar or none
     * <dt>align<dd>vertical or horizontal alignment
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas iframe(CharactersWriteable attrs) throws IOException {
        out.write("<iframe");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</iframe>");
        return this;
    }

	/**
     * Closes the <em>iframe</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _iframe() throws IOException {
        return this.close("</iframe>");
    }

    /**
     * Opens the <em>img</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>src<dd>URI of image to embed
     * <dt>longdesc<dd>link to long description (complements alt)
     * <dt>name<dd>name of image for scripting
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>alt<dd>short description
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>ismap<dd>use server-side image map
     * <dt>usemap<dd>use client-side image map
     * <dt>align<dd>vertical or horizontal alignment
     * <dt>width<dd>override width
     * <dt>height<dd>override height
     * <dt>border<dd>link border width
     * <dt>hspace<dd>horizontal gutter
     * <dt>vspace<dd>vertical gutter
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas img(CharactersWriteable attrs) throws IOException {
        out.write("<img");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');
        return this;
    }
    /**
     * Opens the <em>input</em> tag, without any attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas input() throws IOException {
        out.write("<input/>");
        return this;
    }

    /**
     * Opens the <em>input</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>type<dd>what kind of widget is needed
     * <dt>name<dd>submit as part of form
     * <dt>value<dd>required for radio and checkboxes
     * <dt>size<dd>specific to each type of field
     * <dt>maxlength<dd>max chars for text fields
     * <dt>checked<dd>for radio buttons and check boxes
     * <dt>src<dd>for fields with images
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>alt<dd>short description
     * <dt>align<dd>vertical or horizontal alignment
     * <dt>accept<dd>list of MIME types for file upload
     * <dt>disabled<dd>unavailable in this context
     * <dt>tabindex<dd>position in tabbing order
     * <dt>accesskey<dd>accessibility key character
     * <dt>usemap<dd>use client-side image map
     * <dt>ismap<dd>use server-side image map
     * <dt>readonly<dd>for text and passwd
     * <dt>onfocus<dd>the element got the focus
     * <dt>onblur<dd>the element lost the focus
     * <dt>onselect<dd>some text was selected
     * <dt>onchange<dd>the element value was changed
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas input(CharactersWriteable attrs) throws IOException {
        out.write("<input");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write("/>");
        return this;
    }

    /**
     * Opens the (deprecated) <em>isindex</em> tag, without any attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas isindex() throws IOException {
        out.write("<isindex>");
        openTagStack.add("</isindex>");
        return this;
    }

    /**
     * Opens the <em>isindex</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>prompt<dd>prompt message
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @deprecated this tag is deprecated in HTML 4.0.
     */
    @Deprecated
    public HtmlCanvas isindex(CharactersWriteable attrs) throws IOException {
        out.write("<isindex");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</isindex>");
        return this;
    }

    /**
     * Opens the <em>label</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_label()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas label() throws IOException {
        out.write("<label>");
        openTagStack.add("</label>");
        return this;
    }

    /**
     * Opens the <em>label</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_label()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>for<dd>matches field ID value
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>accesskey<dd>accessibility key character
     * <dt>onfocus<dd>the element got the focus
     * <dt>onblur<dd>the element lost the focus
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas label(CharactersWriteable attrs) throws IOException {
        out.write("<label");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</label>");
        return this;
    }

	/**
     * Closes the <em>label</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _label() throws IOException {
        return this.close("</label>");
    }

    /**
     * Opens the <em>legend</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_legend()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas legend() throws IOException {
        out.write("<legend>");
        openTagStack.add("</legend>");
        return this;
    }

    /**
     * Opens the <em>legend</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_legend()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>for<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>accesskey<dd>accessibility key character
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas legend(CharactersWriteable attrs) throws IOException {
        out.write("<legend");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</legend>");
        return this;
    }

	/**
     * Closes the <em>legend</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _legend() throws IOException {
        return this.close("</legend>");
    }

    /**
     * Opens the <em>li</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_li()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas li() throws IOException {
        out.write("<li>");
        openTagStack.add("</li>");
        return this;
    }

    /**
     * Opens the <em>li</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_li()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>type<dd>list item style
     * <dt>start<dd>
     * <dt>value<dd>reset sequence number
     * <dt>compact<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas li(CharactersWriteable attrs) throws IOException {
        out.write("<li");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</li>");
        return this;
    }

	/**
     * Closes the <em>li</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _li() throws IOException {
        return this.close("</li>");
    }

    /**
     * Opens the <em>ol</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_ol()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas ol() throws IOException {
        out.write("<ol>");
        openTagStack.add("</ol>");
        return this;
    }

    /**
     * Opens the <em>ol</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_ol()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>type<dd>numbering style
     * <dt>start<dd>starting sequence number
     * <dt>value<dd>
     * <dt>compact<dd>reduced interitem spacing
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas ol(CharactersWriteable attrs) throws IOException {
        out.write("<ol");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</ol>");
        return this;
    }

	/**
     * Closes the <em>ol</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _ol() throws IOException {
        return this.close("</ol>");
    }

    /**
     * Opens the <em>ul</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_ul()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas ul() throws IOException {
        out.write("<ul>");
        openTagStack.add("</ul>");
        return this;
    }

    /**
     * Opens the <em>ul</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_ul()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>type<dd>bullet style
     * <dt>start<dd>
     * <dt>value<dd>
     * <dt>compact<dd>reduced interitem spacing
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas ul(CharactersWriteable attrs) throws IOException {
        out.write("<ul");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</ul>");
        return this;
    }

	/**
     * Closes the <em>ul</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _ul() throws IOException {
        return this.close("</ul>");
    }

    /**
     * Opens the <em>link</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onfocus<dd>
     * <dt>onblur<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>href<dd>URI for linked resource
     * <dt>hreflang<dd>language code
     * <dt>rel<dd>forward link types
     * <dt>rev<dd>reverse link types
     * <dt>target<dd>render in this frame
     * <dt>charset<dd>char encoding of linked resource
     * <dt>media<dd>for rendering on these media
     * <dt>type<dd>advisory content type
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas link(CharactersWriteable attrs) throws IOException {
        out.write("<link");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');
        return this;
    }

    /**
     * Opens the <em>meta</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>metainformation name
     * <dt>content<dd>associated information
     * <dt>scheme<dd>select form of content
     * <dt>http-equiv<dd>HTTP response header name
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas meta(CharactersWriteable attrs) throws IOException {
        out.write("<meta");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');
        return this;
    }

    /**
     * Opens the <em>noframes</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_noframes()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas noframes() throws IOException {
        out.write("<noframes>");
        openTagStack.add("</noframes>");
        return this;
    }

    /**
     * Opens the <em>noframes</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_noframes()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas noframes(CharactersWriteable attrs) throws IOException {
        out.write("<noframes");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</noframes>");
        return this;
    }

	/**
     * Closes the <em>noframes</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _noframes() throws IOException {
        return this.close("</noframes>");
    }

    /**
     * Opens the <em>noscript</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_noscript()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas noscript() throws IOException {
        out.write("<noscript>");
        openTagStack.add("</noscript>");
        return this;
    }

    /**
     * Opens the <em>noscript</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_noscript()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas noscript(CharactersWriteable attrs) throws IOException {
        out.write("<noscript");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</noscript>");
        return this;
    }

	/**
     * Closes the <em>noscript</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _noscript() throws IOException {
        return this.close("</noscript>");
    }

    /**
     * Opens the <em>object</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_object()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas object() throws IOException {
        out.write("<object>");
        openTagStack.add("</object>");
        return this;
    }

    /**
     * Opens the <em>object</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_object()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>classid<dd>identifies an implementation
     * <dt>codebase<dd>base URI for classid, data, archive
     * <dt>codetype<dd>content type for code
     * <dt>data<dd>reference to object's data
     * <dt>type<dd>content type for data
     * <dt>archive<dd>space separated archive list
     * <dt>declare<dd>declare but don't instantiate flag
     * <dt>standby<dd>message to show while loading
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>tabindex<dd>position in tabbing order
     * <dt>usemap<dd>use client-side image map
     * <dt>name<dd>submit as part of form
     * <dt>align<dd>vertical or horizontal alignment
     * <dt>width<dd>override width
     * <dt>height<dd>override height
     * <dt>border<dd>link border width
     * <dt>hspace<dd>horizontal gutter
     * <dt>vspace<dd>vertical gutter
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas object(CharactersWriteable attrs) throws IOException {
        out.write("<object");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</object>");
        return this;
    }

	/**
     * Closes the <em>object</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _object() throws IOException {
        return this.close("</object>");
    }

    /**
     * Opens the <em>optgroup</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_optgroup()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas optgroup() throws IOException {
        out.write("<optgroup>");
        openTagStack.add("</optgroup>");
        return this;
    }

    /**
     * Opens the <em>optgroup</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_optgroup()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>
     * <dt>label<dd>for use in hierarchical menus
     * <dt>size<dd>
     * <dt>multiple<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>disabled<dd>unavailable in this context
     * <dt>tabindex<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas optgroup(CharactersWriteable attrs) throws IOException {
        out.write("<optgroup");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</optgroup>");
        return this;
    }

	/**
     * Closes the <em>optgroup</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _optgroup() throws IOException {
        return this.close("</optgroup>");
    }

    /**
     * Opens the <em>option</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_option()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas option() throws IOException {
        out.write("<option>");
        openTagStack.add("</option>");
        return this;
    }

    /**
     * Opens the <em>option</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_option()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>selected<dd>
     * <dt>value<dd>defaults to element content
     * <dt>label<dd>for use in hierarchical menus
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>disabled<dd>unavailable in this context
     * <dt>tabindex<dd>
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas option(CharactersWriteable attrs) throws IOException {
        out.write("<option");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</option>");
        return this;
    }

	/**
     * Closes the <em>option</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _option() throws IOException {
        return this.close("</option>");
    }

    /**
     * Opens the <em>select</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_select()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas select() throws IOException {
        out.write("<select>");
        openTagStack.add("</select>");
        return this;
    }

    /**
     * Opens the <em>select</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_select()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>field name
     * <dt>size<dd>rows visible
     * <dt>multiple<dd>default is single selection
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>disabled<dd>unavailable in this context
     * <dt>tabindex<dd>position in tabbing order
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>onblur<dd>the element lost the focus
     * <dt>onchange<dd>the element value was changed
     * <dt>onfocus<dd>the element got the focus
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas select(CharactersWriteable attrs) throws IOException {
        out.write("<select");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</select>");
        return this;
    }

	/**
     * Closes the <em>select</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _select() throws IOException {
        return this.close("</select>");
    }

    /**
     * Opens the <em>p</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_p()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas p() throws IOException {
        out.write("<p>");
        openTagStack.add("</p>");
        return this;
    }

    /**
     * Opens the <em>p</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_p()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>alt<dd>
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>align<dd>align, text alignment
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas p(CharactersWriteable attrs) throws IOException {
        out.write("<p");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</p>");
        return this;
    }

	/**
     * Closes the <em>p</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _p() throws IOException {
        return this.close("</p>");
    }

    /**
     * Opens the <em>param</em> tag, with the specified attributes.
     *
     * <p>This tag must not be closed, an end tag is forbidden.
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>property name
     * <dt>value<dd>property value
     * <dt>valuetype<dd>How to interpret value
     * <dt>type<dd>content type for value when valuetype=ref
     * <dt>id<dd>document-wide unique id
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas param(CharactersWriteable attrs) throws IOException {
        out.write("<param");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('/');
        out.write('>');
        return this;
    }

    /**
     * Opens the <em>pre</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_pre()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas pre() throws IOException {
        out.write("<pre>");
        openTagStack.add("</pre>");
        return this;
    }

    /**
     * Opens the <em>pre</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_pre()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>width<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas pre(CharactersWriteable attrs) throws IOException {
        out.write("<pre");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</pre>");
        return this;
    }

	/**
     * Closes the <em>pre</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _pre() throws IOException {
        return this.close("</pre>");
    }

    /**
     * Opens the <em>blockquote</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_blockquote()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas blockquote() throws IOException {
        out.write("<blockquote>");
        openTagStack.add("</blockquote>");
        return this;
    }

    /**
     * Opens the <em>blockquote</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_blockquote()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>cite<dd>URI for source document or msg
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas blockquote(CharactersWriteable attrs) throws IOException {
        out.write("<blockquote");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</blockquote>");
        return this;
    }

	/**
     * Closes the <em>blockquote</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _blockquote() throws IOException {
        return this.close("</blockquote>");
    }

    /**
     * Opens the <em>q</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_q()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas q() throws IOException {
        out.write("<q>");
        openTagStack.add("</q>");
        return this;
    }

    /**
     * Opens the <em>q</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_q()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>cite<dd>URI for source document or msg
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas q(CharactersWriteable attrs) throws IOException {
        out.write("<q");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</q>");
        return this;
    }

	/**
     * Closes the <em>q</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _q() throws IOException {
        return this.close("</q>");
    }

    /**
     * Opens the <em>script</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_script()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas script() throws IOException {
        out.write("<script>");
        openTagStack.add("</script>");
        return this;
    }

    /**
     * Opens the <em>script</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_script()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>src<dd>URI for an external script
     * <dt>type<dd>content type of script language
     * <dt>language<dd>predefined script language name
     * <dt>defer<dd>UA may defer execution of script
     * <dt>charset<dd>char encoding of linked resource
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas script(CharactersWriteable attrs) throws IOException {
        out.write("<script");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</script>");
        return this;
    }

	/**
     * Closes the <em>script</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _script() throws IOException {
        return this.close("</script>");
    }

    /**
     * Opens the <em>style</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_style()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas style() throws IOException {
        out.write("<style>");
        openTagStack.add("</style>");
        return this;
    }

    /**
     * Opens the <em>style</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_style()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>type<dd>content type of style language
     * <dt>media<dd>designed for use with these media
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas style(CharactersWriteable attrs) throws IOException {
        out.write("<style");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</style>");
        return this;
    }

	/**
     * Closes the <em>style</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _style() throws IOException {
        return this.close("</style>");
    }

    /**
     * Opens the <em>sub</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_sub()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas sub() throws IOException {
        out.write("<sub>");
        openTagStack.add("</sub>");
        return this;
    }

    /**
     * Opens the <em>sub</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_sub()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas sub(CharactersWriteable attrs) throws IOException {
        out.write("<sub");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</sub>");
        return this;
    }

	/**
     * Closes the <em>sub</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _sub() throws IOException {
        return this.close("</sub>");
    }

    /**
     * Opens the <em>sup</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_sup()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas sup() throws IOException {
        out.write("<sup>");
        openTagStack.add("</sup>");
        return this;
    }

    /**
     * Opens the <em>sup</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_sup()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas sup(CharactersWriteable attrs) throws IOException {
        out.write("<sup");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</sup>");
        return this;
    }

	/**
     * Closes the <em>sup</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _sup() throws IOException {
        return this.close("</sup>");
    }

    /**
     * Opens the <em>textarea</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_textarea()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas textarea() throws IOException {
        out.write("<textarea>");
        openTagStack.add("</textarea>");
        return this;
    }

    /**
     * Opens the <em>textarea</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_textarea()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>name<dd>
     * <dt>rows<dd>
     * <dt>cols<dd>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>readonly<dd>
     * <dt>disabled<dd>unavailable in this context
     * <dt>tabindex<dd>position in tabbing order
     * <dt>accesskey<dd>accessibility key character
     * <dt>onfocus<dd>the element got the focus
     * <dt>onblur<dd>the element lost the focus
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>onchange<dd>the element value was changed
     * <dt>onselect<dd>some text was selected
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas textarea(CharactersWriteable attrs) throws IOException {
        out.write("<textarea");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</textarea>");
        return this;
    }

	/**
     * Closes the <em>textarea</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _textarea() throws IOException {
        return this.close("</textarea>");
    }

    /**
     * Opens the <em>title</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_title()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas title() throws IOException {
        out.write("<title>");
        openTagStack.add("</title>");
        return this;
    }

    /**
     * Opens the <em>title</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_title()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas title(CharactersWriteable attrs) throws IOException {
        out.write("<title");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</title>");
        return this;
    }

	/**
     * Closes the <em>title</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _title() throws IOException {
        return this.close("</title>");
    }

    /**
     * Opens the <em>table</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_table()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas table() throws IOException {
        out.write("<table>");
        openTagStack.add("</table>");
        return this;
    }

    /**
     * Opens the <em>table</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_table()} (the end tag is required).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>summary<dd>purpose/structure for speech output
     * <dt>align<dd>table position relative to window
     * <dt>width<dd>table width
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>bgcolor<dd>background color for cells
     * <dt>frame<dd>which parts of frame to render
     * <dt>rules<dd>rulings between rows and cols
     * <dt>border<dd>controls frame width around table
     * <dt>cellspacing<dd>spacing between cells
     * <dt>cellpadding<dd>spacing within cells
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas table(CharactersWriteable attrs) throws IOException {
        out.write("<table");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</table>");
        return this;
    }

	/**
     * Closes the <em>table</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _table() throws IOException {
        return this.close("</table>");
    }

    /**
     * Opens the <em>tbody</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_tbody()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tbody() throws IOException {
        out.write("<tbody>");
        openTagStack.add("</tbody>");
        return this;
    }

    /**
     * Opens the <em>tbody</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_tbody()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>valign<dd>vertical alignment in cells
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tbody(CharactersWriteable attrs) throws IOException {
        out.write("<tbody");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</tbody>");
        return this;
    }

	/**
     * Closes the <em>tbody</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _tbody() throws IOException {
        return this.close("</tbody>");
    }

    /**
     * Opens the <em>tfoot</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_tfoot()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tfoot() throws IOException {
        out.write("<tfoot>");
        openTagStack.add("</tfoot>");
        return this;
    }

    /**
     * Opens the <em>tfoot</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_tfoot()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>valign<dd>vertical alignment in cells
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tfoot(CharactersWriteable attrs) throws IOException {
        out.write("<tfoot");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</tfoot>");
        return this;
    }

	/**
     * Closes the <em>tfoot</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _tfoot() throws IOException {
        return this.close("</tfoot>");
    }

    /**
     * Opens the <em>thead</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_thead()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas thead() throws IOException {
        out.write("<thead>");
        openTagStack.add("</thead>");
        return this;
    }

    /**
     * Opens the <em>thead</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_thead()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>valign<dd>vertical alignment in cells
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas thead(CharactersWriteable attrs) throws IOException {
        out.write("<thead");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</thead>");
        return this;
    }

	/**
     * Closes the <em>thead</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _thead() throws IOException {
        return this.close("</thead>");
    }

    /**
     * Opens the <em>td</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_td()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas td() throws IOException {
        out.write("<td>");
        openTagStack.add("</td>");
        return this;
    }

    /**
     * Opens the <em>td</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_td()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>headers<dd>list of id's for header cells
     * <dt>scope<dd>scope covered by header cells
     * <dt>abbr<dd>abbreviation for header cell
     * <dt>axis<dd>names groups of related headers
     * <dt>rowspan<dd>number of rows spanned by cell
     * <dt>colspan<dd>number of cols spanned by cell
     * <dt>nowrap<dd>suppress word wrap
     * <dt>width<dd>width for cell
     * <dt>height<dd>height for cell
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>valign<dd>vertical alignment in cells
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * <dt>bgcolor<dd>cell background color
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas td(CharactersWriteable attrs) throws IOException {
        out.write("<td");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</td>");
        return this;
    }

	/**
     * Closes the <em>td</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _td() throws IOException {
        return this.close("</td>");
    }

    /**
     * Opens the <em>th</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_th()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas th() throws IOException {
        out.write("<th>");
        openTagStack.add("</th>");
        return this;
    }

    /**
     * Opens the <em>th</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_th()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>headers<dd>list of id's for header cells
     * <dt>scope<dd>scope covered by header cells
     * <dt>abbr<dd>abbreviation for header cell
     * <dt>axis<dd>names groups of related headers
     * <dt>rowspan<dd>number of rows spanned by cell
     * <dt>colspan<dd>number of cols spanned by cell
     * <dt>nowrap<dd>suppress word wrap
     * <dt>width<dd>width for cell
     * <dt>height<dd>height for cell
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>valign<dd>vertical alignment in cells
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * <dt>bgcolor<dd>cell background color
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas th(CharactersWriteable attrs) throws IOException {
        out.write("<th");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</th>");
        return this;
    }

	/**
     * Closes the <em>th</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _th() throws IOException {
        return this.close("</th>");
    }

    /**
     * Opens the <em>tr</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_tr()} (the end tag is optional).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tr() throws IOException {
        out.write("<tr>");
        openTagStack.add("</tr>");
        return this;
    }

    /**
     * Opens the <em>tr</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_tr()} (the end tag is optional).
     *
     * <p>This tag supports the following attributes:
     * <dl>
     * <dt>id<dd>document-wide unique id
     * <dt>class<dd>space separated list of classes
     * <dt>lang<dd>language code
     * <dt>dir<dd>direction for weak/neutral text
     * <dt>title<dd>advisory title
     * <dt>style<dd>associated style info
     * <dt>onclick<dd>a pointer button was clicked
     * <dt>ondblclick<dd>a pointer button was double clicked
     * <dt>onmousedown<dd>a pointer button was pressed down
     * <dt>onmouseup<dd>a pointer button was released
     * <dt>onmouseover<dd>a pointer was moved onto
     * <dt>onmousemove<dd>a pointer was moved within
     * <dt>onmouseout<dd>a pointer was moved away
     * <dt>onkeypress<dd>a key was pressed and released
     * <dt>onkeydown<dd>a key was pressed down
     * <dt>onkeyup<dd>a key was released
     * <dt>align<dd>
     * <dt>valign<dd>vertical alignment in cells
     * <dt>char<dd>alignment char, e.g. char=':'
     * <dt>charoff<dd>offset for alignment char
     * <dt>bgcolor<dd>background color for row
     * </dl>
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     */
    public HtmlCanvas tr(CharactersWriteable attrs) throws IOException {
        out.write("<tr");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</tr>");
        return this;
    }

	/**
     * Closes the <em>tr</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _tr() throws IOException {
        return this.close("</tr>");
    }
    
    /**
     * Defines an article.
     *
     * Opens the <em>article</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_article()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas article() throws IOException {
        out.write("<article>");
        openTagStack.add("</article>");
        return this;
    }

    /**
     * Defines an article.
     *
     * Opens the <em>article</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_article()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas article(CharactersWriteable attrs) throws IOException {
        out.write("<article");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</article>");
        return this;
    }
    
    /**
     * Closes the <em>article</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _article() throws IOException {
        return this.close("</article>");
    }        /**
     * Defines content aside from the page content.
     *
     * Opens the <em>aside</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_aside()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas aside() throws IOException {
        out.write("<aside>");
        openTagStack.add("</aside>");
        return this;
    }

    /**
     * Defines content aside from the page content.
     *
     * Opens the <em>aside</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_aside()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas aside(CharactersWriteable attrs) throws IOException {
        out.write("<aside");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</aside>");
        return this;
    }
    
    /**
     * Closes the <em>aside</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _aside() throws IOException {
        return this.close("</aside>");
    }        /**
     * Defines sound content.
     *
     * Opens the <em>audio</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_audio()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas audio() throws IOException {
        out.write("<audio>");
        openTagStack.add("</audio>");
        return this;
    }

    /**
     * Defines sound content.
     *
     * Opens the <em>audio</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_audio()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas audio(CharactersWriteable attrs) throws IOException {
        out.write("<audio");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</audio>");
        return this;
    }
    
    /**
     * Closes the <em>audio</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _audio() throws IOException {
        return this.close("</audio>");
    }        /**
     * Defines graphics.
     *
     * Opens the <em>canvas</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_canvas()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas canvas() throws IOException {
        out.write("<canvas>");
        openTagStack.add("</canvas>");
        return this;
    }

    /**
     * Defines graphics.
     *
     * Opens the <em>canvas</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_canvas()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas canvas(CharactersWriteable attrs) throws IOException {
        out.write("<canvas");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</canvas>");
        return this;
    }
    
    /**
     * Closes the <em>canvas</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _canvas() throws IOException {
        return this.close("</canvas>");
    }        /**
     * Defines a command button.
     *
     * Opens the <em>command</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_command()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas command() throws IOException {
        out.write("<command>");
        openTagStack.add("</command>");
        return this;
    }

    /**
     * Defines a command button.
     *
     * Opens the <em>command</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_command()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas command(CharactersWriteable attrs) throws IOException {
        out.write("<command");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</command>");
        return this;
    }
    
    /**
     * Closes the <em>command</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _command() throws IOException {
        return this.close("</command>");
    }        /**
     * Defines a dropdown list.
     *
     * Opens the <em>datalist</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_datalist()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas datalist() throws IOException {
        out.write("<datalist>");
        openTagStack.add("</datalist>");
        return this;
    }

    /**
     * Defines a dropdown list.
     *
     * Opens the <em>datalist</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_datalist()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas datalist(CharactersWriteable attrs) throws IOException {
        out.write("<datalist");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</datalist>");
        return this;
    }
    
    /**
     * Closes the <em>datalist</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _datalist() throws IOException {
        return this.close("</datalist>");
    }        /**
     * Defines details of an element.
     *
     * Opens the <em>details</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_details()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas details() throws IOException {
        out.write("<details>");
        openTagStack.add("</details>");
        return this;
    }

    /**
     * Defines details of an element.
     *
     * Opens the <em>details</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_details()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas details(CharactersWriteable attrs) throws IOException {
        out.write("<details");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</details>");
        return this;
    }
    
    /**
     * Closes the <em>details</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _details() throws IOException {
        return this.close("</details>");
    }        /**
     * Defines external interactive content or plugin.
     *
     * Opens the <em>embed</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_embed()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas embed() throws IOException {
        out.write("<embed>");
        openTagStack.add("</embed>");
        return this;
    }

    /**
     * Defines external interactive content or plugin.
     *
     * Opens the <em>embed</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_embed()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas embed(CharactersWriteable attrs) throws IOException {
        out.write("<embed");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</embed>");
        return this;
    }
    
    /**
     * Closes the <em>embed</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _embed() throws IOException {
        return this.close("</embed>");
    }        /**
     * Defines the caption of a figure element.
     *
     * Opens the <em>figcaption</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_figcaption()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas figcaption() throws IOException {
        out.write("<figcaption>");
        openTagStack.add("</figcaption>");
        return this;
    }

    /**
     * Defines the caption of a figure element.
     *
     * Opens the <em>figcaption</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_figcaption()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas figcaption(CharactersWriteable attrs) throws IOException {
        out.write("<figcaption");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</figcaption>");
        return this;
    }
    
    /**
     * Closes the <em>figcaption</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _figcaption() throws IOException {
        return this.close("</figcaption>");
    }        /**
     * Defines a group of media content, and their caption.
     *
     * Opens the <em>figure</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_figure()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas figure() throws IOException {
        out.write("<figure>");
        openTagStack.add("</figure>");
        return this;
    }

    /**
     * Defines a group of media content, and their caption.
     *
     * Opens the <em>figure</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_figure()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas figure(CharactersWriteable attrs) throws IOException {
        out.write("<figure");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</figure>");
        return this;
    }
    
    /**
     * Closes the <em>figure</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _figure() throws IOException {
        return this.close("</figure>");
    }        /**
     * Defines a footer for a section or page.
     *
     * Opens the <em>footer</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_footer()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas footer() throws IOException {
        out.write("<footer>");
        openTagStack.add("</footer>");
        return this;
    }

    /**
     * Defines a footer for a section or page.
     *
     * Opens the <em>footer</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_footer()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas footer(CharactersWriteable attrs) throws IOException {
        out.write("<footer");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</footer>");
        return this;
    }
    
    /**
     * Closes the <em>footer</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _footer() throws IOException {
        return this.close("</footer>");
    }        /**
     * Defines a header for a section or page.
     *
     * Opens the <em>header</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_header()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas header() throws IOException {
        out.write("<header>");
        openTagStack.add("</header>");
        return this;
    }

    /**
     * Defines a header for a section or page.
     *
     * Opens the <em>header</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_header()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas header(CharactersWriteable attrs) throws IOException {
        out.write("<header");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</header>");
        return this;
    }
    
    /**
     * Closes the <em>header</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _header() throws IOException {
        return this.close("</header>");
    }        /**
     * Defines information about a section in a document.
     *
     * Opens the <em>hgroup</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_hgroup()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas hgroup() throws IOException {
        out.write("<hgroup>");
        openTagStack.add("</hgroup>");
        return this;
    }

    /**
     * Defines information about a section in a document.
     *
     * Opens the <em>hgroup</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_hgroup()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas hgroup(CharactersWriteable attrs) throws IOException {
        out.write("<hgroup");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</hgroup>");
        return this;
    }
    
    /**
     * Closes the <em>hgroup</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _hgroup() throws IOException {
        return this.close("</hgroup>");
    }        /**
     * Defines a generated key in a form.
     *
     * Opens the <em>keygen</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_keygen()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas keygen() throws IOException {
        out.write("<keygen>");
        openTagStack.add("</keygen>");
        return this;
    }

    /**
     * Defines a generated key in a form.
     *
     * Opens the <em>keygen</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_keygen()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas keygen(CharactersWriteable attrs) throws IOException {
        out.write("<keygen");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</keygen>");
        return this;
    }
    
    /**
     * Closes the <em>keygen</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _keygen() throws IOException {
        return this.close("</keygen>");
    }        /**
     * Defines marked text.
     *
     * Opens the <em>mark</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_mark()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas mark() throws IOException {
        out.write("<mark>");
        openTagStack.add("</mark>");
        return this;
    }

    /**
     * Defines marked text.
     *
     * Opens the <em>mark</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_mark()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas mark(CharactersWriteable attrs) throws IOException {
        out.write("<mark");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</mark>");
        return this;
    }
    
    /**
     * Closes the <em>mark</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _mark() throws IOException {
        return this.close("</mark>");
    }        /**
     * Defines measurement within a predefined range.
     *
     * Opens the <em>meter</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_meter()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas meter() throws IOException {
        out.write("<meter>");
        openTagStack.add("</meter>");
        return this;
    }

    /**
     * Defines measurement within a predefined range.
     *
     * Opens the <em>meter</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_meter()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas meter(CharactersWriteable attrs) throws IOException {
        out.write("<meter");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</meter>");
        return this;
    }
    
    /**
     * Closes the <em>meter</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _meter() throws IOException {
        return this.close("</meter>");
    }        
    /**
     * Defines navigation links.
     *
     * Opens the <em>nav</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_nav()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas nav() throws IOException {
        out.write("<nav>");
        openTagStack.add("</nav>");
        return this;
    }

    /**
     * Defines navigation links.
     *
     * Opens the <em>nav</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_nav()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas nav(CharactersWriteable attrs) throws IOException {
        out.write("<nav");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</nav>");
        return this;
    }
    
    /**
     * Closes the <em>nav</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _nav() throws IOException {
        return this.close("</nav>");
    }        
    /**
     * Defines some types of output.
     *
     * Opens the <em>output</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_output()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas output() throws IOException {
        out.write("<output>");
        openTagStack.add("</output>");
        return this;
    }

    /**
     * Defines some types of output.
     *
     * Opens the <em>output</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_output()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas output(CharactersWriteable attrs) throws IOException {
        out.write("<output");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</output>");
        return this;
    }
    
    /**
     * Closes the <em>output</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _output() throws IOException {
        return this.close("</output>");
    }        /**
     * Defines progress of a task of any kind.
     *
     * Opens the <em>progress</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_progress()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas progress() throws IOException {
        out.write("<progress>");
        openTagStack.add("</progress>");
        return this;
    }

    /**
     * Defines progress of a task of any kind.
     *
     * Opens the <em>progress</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_progress()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas progress(CharactersWriteable attrs) throws IOException {
        out.write("<progress");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</progress>");
        return this;
    }
    
    /**
     * Closes the <em>progress</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _progress() throws IOException {
        return this.close("</progress>");
    }        /**
     * Used in ruby annotations to define what to show browsers that to not support the ruby element..
     *
     * Opens the <em>rp</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_rp()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas rp() throws IOException {
        out.write("<rp>");
        openTagStack.add("</rp>");
        return this;
    }

    /**
     * Used in ruby annotations to define what to show browsers that to not support the ruby element..
     *
     * Opens the <em>rp</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_rp()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas rp(CharactersWriteable attrs) throws IOException {
        out.write("<rp");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</rp>");
        return this;
    }
    
    /**
     * Closes the <em>rp</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _rp() throws IOException {
        return this.close("</rp>");
    }        /**
     * Defines explanation to ruby annotations..
     *
     * Opens the <em>rt</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_rt()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas rt() throws IOException {
        out.write("<rt>");
        openTagStack.add("</rt>");
        return this;
    }

    /**
     * Defines explanation to ruby annotations..
     *
     * Opens the <em>rt</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_rt()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas rt(CharactersWriteable attrs) throws IOException {
        out.write("<rt");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</rt>");
        return this;
    }
    
    /**
     * Closes the <em>rt</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _rt() throws IOException {
        return this.close("</rt>");
    }        /**
     * Defines ruby annotations.
     *
     * Opens the <em>ruby</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_ruby()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas ruby() throws IOException {
        out.write("<ruby>");
        openTagStack.add("</ruby>");
        return this;
    }

    /**
     * Defines ruby annotations.
     *
     * Opens the <em>ruby</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_ruby()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas ruby(CharactersWriteable attrs) throws IOException {
        out.write("<ruby");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</ruby>");
        return this;
    }
    
    /**
     * Closes the <em>ruby</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _ruby() throws IOException {
        return this.close("</ruby>");
    }        /**
     * Defines a section.
     *
     * Opens the <em>section</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_section()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas section() throws IOException {
        out.write("<section>");
        openTagStack.add("</section>");
        return this;
    }

    /**
     * Defines a section.
     *
     * Opens the <em>section</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_section()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas section(CharactersWriteable attrs) throws IOException {
        out.write("<section");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</section>");
        return this;
    }
    
    /**
     * Closes the <em>section</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _section() throws IOException {
        return this.close("</section>");
    }        /**
     * Defines media resources.
     *
     * Opens the <em>source</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_source()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas source() throws IOException {
        out.write("<source>");
        openTagStack.add("</source>");
        return this;
    }

    /**
     * Defines media resources.
     *
     * Opens the <em>source</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_source()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas source(CharactersWriteable attrs) throws IOException {
        out.write("<source");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</source>");
        return this;
    }
    
    /**
     * Closes the <em>source</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _source() throws IOException {
        return this.close("</source>");
    }        /**
     * Defines the header of a "detail" element.
     *
     * Opens the <em>summary</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_summary()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas summary() throws IOException {
        out.write("<summary>");
        openTagStack.add("</summary>");
        return this;
    }

    /**
     * Defines the header of a "detail" element.
     *
     * Opens the <em>summary</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_summary()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas summary(CharactersWriteable attrs) throws IOException {
        out.write("<summary");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</summary>");
        return this;
    }
    
    /**
     * Closes the <em>summary</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _summary() throws IOException {
        return this.close("</summary>");
    }        /**
     * Defines a date/time.
     *
     * Opens the <em>time</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_time()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas time() throws IOException {
        out.write("<time>");
        openTagStack.add("</time>");
        return this;
    }

    /**
     * Defines a date/time.
     *
     * Opens the <em>time</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_time()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas time(CharactersWriteable attrs) throws IOException {
        out.write("<time");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</time>");
        return this;
    }
    
    /**
     * Closes the <em>time</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _time() throws IOException {
        return this.close("</time>");
    }        /**
     * Defines a video.
     *
     * Opens the <em>video</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_video()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas video() throws IOException {
        out.write("<video>");
        openTagStack.add("</video>");
        return this;
    }

    /**
     * Defines a video.
     *
     * Opens the <em>video</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_video()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas video(CharactersWriteable attrs) throws IOException {
        out.write("<video");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</video>");
        return this;
    }
    
    /**
     * Closes the <em>video</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _video() throws IOException {
        return this.close("</video>");
    }        /**
     * Defines a possible line-break.
     *
     * Opens the <em>wbr</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_wbr()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas wbr() throws IOException {
        out.write("<wbr>");
        openTagStack.add("</wbr>");
        return this;
    }

    /**
     * Defines a possible line-break.
     *
     * Opens the <em>wbr</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_wbr()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas wbr(CharactersWriteable attrs) throws IOException {
        out.write("<wbr");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</wbr>");
        return this;
    }
    
    /**
     * Closes the <em>wbr</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _wbr() throws IOException {
        return this.close("</wbr>");
    }  

    /**
     * Defines the main content of a document.
     *
     * Opens the <em>main</em> tag, without any attributes.
     *
     * <p>Close this tag by calling {@link #_main()} (the end tag is required).
     *
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas main() throws IOException {
        out.write("<main>");
        openTagStack.add("</main>");
        return this;
    }

    /**
     * Defines the main content of a document.
     *
     * Opens the <em>main</em> tag, with the specified attributes.
     *
     * <p>Close this tag by calling {@link #_main()} (the end tag is required).
     *
     * @param attrs the {@link CharactersWriteable}, or <code>null</code> if none.
     * @return the receiver, this <code>HtmlCanvas</code> instance.
     * @throws IOException in case of an I/O error.
     * @since HTML5
     */
    public HtmlCanvas main(CharactersWriteable attrs) throws IOException {
        out.write("<main");
        if (attrs != null)
            attrs.writeCharsOn(out);
        out.write('>');
        openTagStack.add("</main>");
        return this;
    }
    
    /**
     * Closes the <em>main</em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     * @since HTML5     
     */
    public HtmlCanvas _main() throws IOException {
        return this.close("</main>");
    }        
    
    /**
     * Convenience method that write the integer value (if not null).
     * @param i
     * @return
     * @throws IOException
     */     
    public HtmlCanvas write(Integer i) throws IOException {
        if (null == i) {
            return this;
        }
        out.append(Integer.toString(i));
        return this;
    }
    
    /**
     * Convenience method that calls write(Integer) and closes the last opened tag.
     * @param i
     * @return
     * @throws IOException
     */     
    public HtmlCanvas content(Integer i) throws IOException {
        if (null == i) {
            return this.close();
        }
        out.append(Integer.toString(i));
        return this.close(); 
    }    
}
