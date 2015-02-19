package org.rendersnake;

import static org.rendersnake.HtmlAttributesFactory.ESCAPE_CHARS;
import static org.rendersnake.HtmlAttributesFactory.NO_ESCAPE;

import java.io.IOException;
import java.io.Writer;

import org.rendersnake.internal.CharactersWriteable;
import org.rendersnake.internal.WriteBuffer;

/**
 * HtmlAttributes captures a set of attribute key and values.
 * Instances have a write-once-only behavior; values can not be read or changed.
 * The result is available using <em>asCharSequence()</em>
 * 
 * @author e.micklei
 */
public class HtmlAttributes implements CharactersWriteable {
    /**
     * This class field controls whether the data-test attribute will be written when dataTest("...") is called.
     * It can be used by Web testing tools that need element identification where the id is missing. 
     * On default, it is rendered (true).
     */
    public static boolean RENDER_DATA_TEST_ATTRIBUTE = true;
    /**
     * Default constructor
     */
    public HtmlAttributes() {}
    /**
     * Return a new HtmlAttributes with the contents of the constructor argument.
     * @param other
     */
    public HtmlAttributes(HtmlAttributes other) {
        this.out.append(other.toHtml()); 
    }
    /**
     * Internal buffer to stream attributes to.
     */
	final WriteBuffer out = new WriteBuffer(64); 
	/**
	 * Create a new HtmlAttributes and add a single key,value pair. The value will be XML-escaped.
	 * @param key String (not-null)
	 * @param value String | null
	 */
	public HtmlAttributes(String key, String value) {
		this.add(key, value, ESCAPE_CHARS);
	}
	/**
	 * Add a key=value pair to the receiver. XML escape the value.
	 * 
	 * @param key
	 *            String (not-null)
	 * @param value
	 *            String | null
	 * @return the receiver, an HtmlAttributes
	 */
	public HtmlAttributes add(String key, String value) {
		return this.add(key, value, ESCAPE_CHARS);
	}
	/**
	 * Add a key=value pair to the receiver. Xml escape the value if needed.
	 * 
	 * @param key
	 *            String (not-null)
	 * @param value
	 *            String | null
	 * @param doEscape
	 *            boolean
	 * @return the receiver, an HtmlAttributes
	 */
	public HtmlAttributes add(String key, String value, boolean doEscape) {
		if (value == null)
			return this;
		out.append(' ');
		out.append(key);
		out.append('=');
		out.append('"');
		if (doEscape) {
		    // open-code for speed 
		    // HTML4 (http://www.w3.org/TR/html4/charset.html#entities) 
		    // Draft HTLM5 (http://www.w3.org/TR/2012/WD-html5-20120329/named-character-references.html#named-character-references)
		    for (int i=0;i<value.length();i++) {
		        char each = value.charAt(i);
		        if ('\"' == each) {
		            out.append("&quot;");
		        } else if ('\'' == each) {
		            out.append("&#39;");
                } else if ('<' == each) {
                    out.append("&lt;");
                } else if ('>' == each) {
                    out.append("&gt;");
                } else if ('&' == each) {
                    out.append("&amp;");
                } else {
                    out.append(each);
                }
		    }		    
		} else {
			out.append(value);
		}
		out.append('\"');
		return this;
	}
    /**
     * Add a key=value pair to the receiver.
     * 
     * @param key
     *            String (not-null)
     * @param value
     *            Integer | null
     * @return the receiver, an HtmlAttributes
     */	
    public HtmlAttributes add(String key, Integer value) {
        if (value == null)
            return this;
        out.append(' ');
        out.append(key);
        out.append('=');
        out.append(value);
        return this;
    }
	/**
	 * Add a key=value pair to the receiver. Apply javascript escaping to the value if needed.
	 * 
	 * @param key
	 *            String (not-null)
	 * @param value
	 *            String | null
	 * @param doEscape
	 *            boolean  NOTE: this value is ignored: NO Ecmascript escaping.
	 * @return the receiver, an HtmlAttributes
	 */
	public HtmlAttributes addScript(String key, String value, boolean doEscape) {
		if (value == null)
			return this;
		out.append(' ');
		out.append(key);
		out.append('=');
		out.append('"');
		out.append(value);
		out.append('\"');
		return this;
	}	
	/**
	 * Return a description of the receiver for debugging purposes.
	 */
	@Override
	public String toString() {
		return super.toString() + "[" + out.toString() + "]";
	}
	
	public void writeCharsOn(Writer writer) throws IOException {
	    out.writeCharsOn(writer);
	}
	/**
	 * @return
	 */
	public String toHtml(){
	    return out.toString();
	}

    /**
     * @param flashvars
     * @return
     */
    public HtmlAttributes flashvars(String flashvars) {
        return this.add("flashvars", flashvars, ESCAPE_CHARS);
    }  
    /**
     * @param allow
     * @return
     */
    public HtmlAttributes allowFullscreen(boolean allow) {
        return this.add("allowFullScreen", Boolean.toString(allow), NO_ESCAPE);
    }    
    /**
     * always: Always permit ActionScript-to-JavaScript calls
     * sameDomain: Permit ActionScript-to-JavaScript calls only when the SWF and HTML page come from the same domain
     * never: Never permit ActionScript-to-JavaScript calls
     * @param allow
     * @return
     */
    public HtmlAttributes allowScriptAccess(String allow) {
        return this.add("allowScriptAccess", allow, ESCAPE_CHARS);
    } 	
	
	// /////////////////////////////////////////////////////////////////////////
	//
	// Methods below are for convenience.
	//
	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @param xmlLang
	 * @return
	 */
	public HtmlAttributes xml_lang(String xmlLang) {
		return this.add("xml:lang", xmlLang, ESCAPE_CHARS);
	}	
	
	/**
	 * Append the <em>data-test</em> attribute with the given String parameter as its value.
	 * The actual rendering of this attribute is controlled by the value of RENDER_DATA_TEST_ATTRIBUTE.
	 * 
	 * @param value
	 * @return the receiver, this <code>HtmlAttributes</code> instance.
	 * @throws IOException
	 */
	/**
	 * @param value
	 * @return
	 */
	public HtmlAttributes dataTest(String value) {
	    if (RENDER_DATA_TEST_ATTRIBUTE)
	        return this.add("data-test", value, ESCAPE_CHARS);
	    else
	        return this;
    }   
    /**
     * Append the <em>min</em> attribute with the given String parameter as its value.
     *
     * <p>The value of min will be XML escaped. Use add("value",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param min the INteger | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes min(Integer min) {
        return this.add("min", min);
    }    
    /**
     * Append the <em>max</em> attribute with the given String parameter as its value.
     *
     * <p>The value of max will be XML escaped. Use add("value",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param max the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */  
    public HtmlAttributes max(Integer max) {
        return this.add("max", max);
    }	
    /**
     * Append the <em>value</em> attribute with the given String parameter as its value.
     *
     * <p>The value of value will be XML escaped. Use add("value",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param value the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */    
    public HtmlAttributes value(Integer value) {
        return this.add("value", value);        
    }
    /**
     * Append the generic <em>data</em> attribute with the given String parameter as its value.
     *
     * <p>The value of data will be XML escaped. Use add("data",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     * @param extension 
     *     
     * @param data the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes data(String extension, String data) {
        return this.add("data-" + extension, data, ESCAPE_CHARS);
    } 
    /**
     * Append the generic <em>data</em> attribute with the given int parameter as its value.
     *
     * <p>The value of data will be XML escaped. Use add("data",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     * @param extension 
     *     
     * @param number the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes data(String extension, Integer number) {
        return this.add("data-" + extension, number);
    }    
	
	// ///////////////////////////////////////////////////////////////////////////////
	//
	// Methods below are generated using the AttributesMethodsGenerator. DO NOT EDIT
	//
	// ///////////////////////////////////////////////////////////////////////////////

    /**
     * Append the <em>summary</em> attribute with the given String parameter as its value.
     *
     * <p>purpose/structure for speech output</p>
     *
     * <p>The value of summary will be XML escaped. Use add("summary",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param summary the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes summary(String summary) {
        return this.add("summary", summary, ESCAPE_CHARS);
    }
    /**
     * Append the <em>marginheight</em> attribute with the given String parameter as its value.
     *
     * <p>margin height in pixels</p>
     *
     * <p>The value of marginheight will be XML escaped. Use add("marginheight",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param marginheight the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes marginheight(String marginheight) {
        return this.add("marginheight", marginheight, ESCAPE_CHARS);
    }
    /**
     * Append the <em>for</em> attribute with the given String parameter as its value.
     *
     * <p>matches field ID value</p>
     *
     * <p>The value of for will be XML escaped. Use add("for",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param for_ the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes for_(String for_) {
        return this.add("for", for_, ESCAPE_CHARS);
    }
    /**
     * Append the <em>accept</em> attribute with the given String parameter as its value.
     *
     * <p>list of MIME types for file upload</p>
     *
     * <p>The value of accept will be XML escaped. Use add("accept",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param accept the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes accept(String accept) {
        return this.add("accept", accept, ESCAPE_CHARS);
    }
    /**
     * Append the <em>bgcolor</em> attribute with the given String parameter as its value.
     *
     * <p>document background color</p>
     *
     * <p>The value of bgcolor will be XML escaped. Use add("bgcolor",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param bgcolor the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes bgcolor(String bgcolor) {
        return this.add("bgcolor", bgcolor, ESCAPE_CHARS);
    }
    /**
     * Append the <em>accept-charset</em> attribute with the given String parameter as its value.
     *
     * <p>list of supported charsets</p>
     *
     * <p>The value of accept-charset will be XML escaped. Use add("accept-charset",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param accept_charset the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes accept_charset(String accept_charset) {
        return this.add("accept-charset", accept_charset, ESCAPE_CHARS);
    }
    /**
     * Append the <em>scheme</em> attribute with the given String parameter as its value.
     *
     * <p>select form of content</p>
     *
     * <p>The value of scheme will be XML escaped. Use add("scheme",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param scheme the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes scheme(String scheme) {
        return this.add("scheme", scheme, ESCAPE_CHARS);
    }
    /**
     * Append the <em>border</em> attribute with the given String parameter as its value.
     *
     * <p>link border width</p>
     *
     * <p>The value of border will be XML escaped. Use add("border",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param border the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes border(String border) {
        return this.add("border", border, ESCAPE_CHARS);
    }
    /**
     * Append the <em>vspace</em> attribute with the given String parameter as its value.
     *
     * <p>vertical gutter</p>
     *
     * <p>The value of vspace will be XML escaped. Use add("vspace",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param vspace the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes vspace(String vspace) {
        return this.add("vspace", vspace, ESCAPE_CHARS);
    }
    /**
     * Append the <em>href</em> attribute with the given String parameter as its value.
     *
     * <p>URI that acts as base URI</p>
     *
     * <p>The value of href will be XML escaped. Use add("href",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param href the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes href(String href) {
        return this.add("href", href, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondblclick</em> attribute with the given String parameter as its value.
     *
     * <p>a pointer button was double clicked</p>
     *
     * <p>The value of ondblclick will be XML escaped. Use add("ondblclick",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondblclick the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onDblclick(String ondblclick) {
        return this.addScript("ondblclick", ondblclick, ESCAPE_CHARS);
    }
    /**
     * Append the <em>charset</em> attribute with the given String parameter as its value.
     *
     * <p>char encoding of linked resource</p>
     *
     * <p>The value of charset will be XML escaped. Use add("charset",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param charset the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes charset(String charset) {
        return this.add("charset", charset, ESCAPE_CHARS);
    }
    /**
     * Append the <em>longdesc</em> attribute with the given String parameter as its value.
     *
     * <p>link to long description (complements title)</p>
     *
     * <p>The value of longdesc will be XML escaped. Use add("longdesc",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param longdesc the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes longdesc(String longdesc) {
        return this.add("longdesc", longdesc, ESCAPE_CHARS);
    }
    /**
     * Append the <em>noshade</em> attribute with the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of noshade will be XML escaped. Use add("noshade",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param noshade the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes noshade(String noshade) {
        return this.add("noshade", noshade, ESCAPE_CHARS);
    }
    /**
     * Append the <em>declare</em> attribute with the given String parameter as its value.
     *
     * <p>declare but don't instantiate flag</p>
     *
     * <p>The value of declare will be XML escaped. Use add("declare",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param declare the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes declare(String declare) {
        return this.add("declare", declare, ESCAPE_CHARS);
    }
    /**
     * Append the <em>content</em> attribute with the given String parameter as its value.
     *
     * <p>associated information</p>
     *
     * <p>The value of content will be XML escaped. Use add("content",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param content the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes content(String content) {
        return this.add("content", content, ESCAPE_CHARS);
    }
    /**
     * Append the <em>cite</em> attribute with the given String parameter as its value.
     *
     * <p>info on reason for change</p>
     *
     * <p>The value of cite will be XML escaped. Use add("cite",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param cite the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes cite(String cite) {
        return this.add("cite", cite, ESCAPE_CHARS);
    }
    /**
     * Append the <em>standby</em> attribute with the given String parameter as its value.
     *
     * <p>message to show while loading</p>
     *
     * <p>The value of standby will be XML escaped. Use add("standby",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param standby the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes standby(String standby) {
        return this.add("standby", standby, ESCAPE_CHARS);
    }
    /**
     * Append the <em>start</em> attribute with the given String parameter as its value.
     *
     * <p>starting sequence number</p>
     *
     * <p>The value of start will be XML escaped. Use add("start",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param start the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes start(String start) {
        return this.add("start", start, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onmousedown</em> attribute with the given String parameter as its value.
     *
     * <p>a pointer button was pressed down</p>
     *
     * <p>The value of onmousedown will be XML escaped. Use add("onmousedown",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onmousedown the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onMousedown(String onmousedown) {
        return this.addScript("onmousedown", onmousedown, ESCAPE_CHARS);
    }
    /**
     * Append the <em>language</em> attribute with the given String parameter as its value.
     *
     * <p>predefined script language name</p>
     *
     * <p>The value of language will be XML escaped. Use add("language",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param language the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes language(String language) {
        return this.add("language", language, ESCAPE_CHARS);
    }
    /**
     * Append the <em>nohref</em> attribute with the given String parameter as its value.
     *
     * <p>this region has no action</p>
     *
     * <p>The value of nohref will be XML escaped. Use add("nohref",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param nohref the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes nohref(String nohref) {
        return this.add("nohref", nohref, ESCAPE_CHARS);
    }
    /**
     * Append the <em>vlink</em> attribute with the given String parameter as its value.
     *
     * <p>color of visited links</p>
     *
     * <p>The value of vlink will be XML escaped. Use add("vlink",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param vlink the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes vlink(String vlink) {
        return this.add("vlink", vlink, ESCAPE_CHARS);
    }
    /**
     * Append the <em>face</em> attribute with the given String parameter as its value.
     *
     * <p>comma separated list of font names</p>
     *
     * <p>The value of face will be XML escaped. Use add("face",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param face the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes face(String face) {
        return this.add("face", face, ESCAPE_CHARS);
    }
    /**
     * Append the <em>rev</em> attribute with the given String parameter as its value.
     *
     * <p>reverse link types</p>
     *
     * <p>The value of rev will be XML escaped. Use add("rev",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param rev the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes rev(String rev) {
        return this.add("rev", rev, ESCAPE_CHARS);
    }
    /**
     * Append the <em>hspace</em> attribute with the given String parameter as its value.
     *
     * <p>horizontal gutter</p>
     *
     * <p>The value of hspace will be XML escaped. Use add("hspace",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param hspace the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes hspace(String hspace) {
        return this.add("hspace", hspace, ESCAPE_CHARS);
    }
    /**
     * Append the <em>link</em> attribute with the given String parameter as its value.
     *
     * <p>color of links</p>
     *
     * <p>The value of link will be XML escaped. Use add("link",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param link the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes link(String link) {
        return this.add("link", link, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onunload</em> attribute with the given String parameter as its value.
     *
     * <p>the document has been removed</p>
     *
     * <p>The value of onunload will be XML escaped. Use add("onunload",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onunload the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onUnload(String onunload) {
        return this.addScript("onunload", onunload, ESCAPE_CHARS);
    }
    /**
     * Append the <em>data</em> attribute with the given String parameter as its value.
     *
     * <p>reference to object's data</p>
     *
     * <p>The value of data will be XML escaped. Use add("data",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param data the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes data(String data) {
        return this.add("data", data, ESCAPE_CHARS);
    }
    /**
     * Append the <em>marginwidth</em> attribute with the given String parameter as its value.
     *
     * <p>margin widths in pixels</p>
     *
     * <p>The value of marginwidth will be XML escaped. Use add("marginwidth",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param marginwidth the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes marginwidth(String marginwidth) {
        return this.add("marginwidth", marginwidth, ESCAPE_CHARS);
    }
    /**
     * Append the <em>accesskey</em> attribute with the given String parameter as its value.
     *
     * <p>accessibility key character</p>
     *
     * <p>The value of accesskey will be XML escaped. Use add("accesskey",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param accesskey the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes accesskey(String accesskey) {
        return this.add("accesskey", accesskey, ESCAPE_CHARS);
    }
    /**
     * Append the <em>version</em> attribute with the given String parameter as its value.
     *
     * <p>Constant</p>
     *
     * <p>The value of version will be XML escaped. Use add("version",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param version the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes version(String version) {
        return this.add("version", version, ESCAPE_CHARS);
    }
    /**
     * Append the <em>http_equiv</em> attribute with the given String parameter as its value.
     *
     * <p>HTTP response header name</p>
     *
     * <p>The value of http_equiv will be XML escaped. Use add("http_equiv",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param http_equiv the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes http_equiv(String http_equiv) {
        return this.add("http-equiv", http_equiv, ESCAPE_CHARS);
    }
    /**
     * Append the <em>clear</em> attribute with the given String parameter as its value.
     *
     * <p>control of text flow</p>
     *
     * <p>The value of clear will be XML escaped. Use add("clear",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param clear the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes clear(String clear) {
        return this.add("clear", clear, ESCAPE_CHARS);
    }
    /**
     * Append the <em>valuetype</em> attribute with the given String parameter as its value.
     *
     * <p>How to interpret value</p>
     *
     * <p>The value of valuetype will be XML escaped. Use add("valuetype",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param valuetype the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes valuetype(String valuetype) {
        return this.add("valuetype", valuetype, ESCAPE_CHARS);
    }
    /**
     * Append the <em>defer</em> attribute with the given String parameter as its value.
     *
     * <p>UA may defer execution of script</p>
     *
     * <p>The value of defer will be XML escaped. Use add("defer",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param defer the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes defer(String defer) {
        return this.add("defer", defer, ESCAPE_CHARS);
    }
    /**
     * Append the <em>title</em> attribute with the given String parameter as its value.
     *
     * <p>advisory title</p>
     *
     * <p>The value of title will be XML escaped. Use add("title",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param title the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes title(String title) {
        return this.add("title", title, ESCAPE_CHARS);
    }
    /**
     * Append the <em>enctype</em> attribute with the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of enctype will be XML escaped. Use add("enctype",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param enctype the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes enctype(String enctype) {
        return this.add("enctype", enctype, ESCAPE_CHARS);
    }
    /**
     * Append the <em>src</em> attribute with the given String parameter as its value.
     *
     * <p>URI of image to embed</p>
     *
     * <p>The value of src will be XML escaped. Use add("src",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param src the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes src(String src) {
        return this.add("src", src, ESCAPE_CHARS);
    }
    /**
     * Append the <em>datetime</em> attribute with the given String parameter as its value.
     *
     * <p>date and time of change</p>
     *
     * <p>The value of datetime will be XML escaped. Use add("datetime",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param datetime the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes datetime(String datetime) {
        return this.add("datetime", datetime, ESCAPE_CHARS);
    }
    /**
     * Append the <em>codetype</em> attribute with the given String parameter as its value.
     *
     * <p>content type for code</p>
     *
     * <p>The value of codetype will be XML escaped. Use add("codetype",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param codetype the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes codetype(String codetype) {
        return this.add("codetype", codetype, ESCAPE_CHARS);
    }
    /**
     * Append the <em>charoff</em> attribute with the given String parameter as its value.
     *
     * <p>offset for alignment char</p>
     *
     * <p>The value of charoff will be XML escaped. Use add("charoff",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param charoff the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes charoff(String charoff) {
        return this.add("charoff", charoff, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onkeydown</em> attribute with the given String parameter as its value.
     *
     * <p>a key was pressed down</p>
     *
     * <p>The value of onkeydown will be XML escaped. Use add("onkeydown",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onkeydown the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onKeydown(String onkeydown) {
        return this.addScript("onkeydown", onkeydown, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onkeypress</em> attribute with the given String parameter as its value.
     *
     * <p>a key was pressed and released</p>
     *
     * <p>The value of onkeypress will be XML escaped. Use add("onkeypress",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onkeypress the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onKeypress(String onkeypress) {
        return this.addScript("onkeypress", onkeypress, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onsubmit</em> attribute with the given String parameter as its value.
     *
     * <p>the form was submitted</p>
     *
     * <p>The value of onsubmit will be XML escaped. Use add("onsubmit",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onsubmit the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onSubmit(String onsubmit) {
        return this.addScript("onsubmit", onsubmit, ESCAPE_CHARS);
    }
    /**
     * Append the <em>alink</em> attribute with the given String parameter as its value.
     *
     * <p>color of selected links</p>
     *
     * <p>The value of alink will be XML escaped. Use add("alink",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param alink the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes alink(String alink) {
        return this.add("alink", alink, ESCAPE_CHARS);
    }
    /**
     * Append the <em>background</em> attribute with the given String parameter as its value.
     *
     * <p>texture tile for document background</p>
     *
     * <p>The value of background will be XML escaped. Use add("background",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param background the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes background(String background) {
        return this.add("background", background, ESCAPE_CHARS);
    }
    /**
     * Append the <em>method</em> attribute with the given String parameter as its value.
     *
     * <p>HTTP method used to submit the form</p>
     *
     * <p>The value of method will be XML escaped. Use add("method",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param method the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes method(String method) {
        return this.add("method", method, ESCAPE_CHARS);
    }
    /**
     * Append the <em>archive</em> attribute with the given String parameter as its value.
     *
     * <p>comma separated archive list</p>
     *
     * <p>The value of archive will be XML escaped. Use add("archive",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param archive the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes archive(String archive) {
        return this.add("archive", archive, ESCAPE_CHARS);
    }
    /**
     * Append the <em>prompt</em> attribute with the given String parameter as its value.
     *
     * <p>prompt message</p>
     *
     * <p>The value of prompt will be XML escaped. Use add("prompt",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param prompt the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes prompt(String prompt) {
        return this.add("prompt", prompt, ESCAPE_CHARS);
    }
    /**
     * Append the <em>rel</em> attribute with the given String parameter as its value.
     *
     * <p>forward link types</p>
     *
     * <p>The value of rel will be XML escaped. Use add("rel",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param rel the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes rel(String rel) {
        return this.add("rel", rel, ESCAPE_CHARS);
    }
    /**
     * Append the <em>checked</em> attribute with the given String parameter as its value.
     *
     * <p>for radio buttons and check boxes</p>
     *
     * <p>The value of checked will be XML escaped. Use add("checked",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param checked the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes checked(String checked) {
        return this.add("checked", checked, ESCAPE_CHARS);
    }
    /**
     * Append the <em>required</em> attribute with the given String parameter as its value.
     *
     * <p>for input elements</p>
     *
     * @param required the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5    
     */
    public HtmlAttributes required(String required) {
        return this.add("required", required, ESCAPE_CHARS);
    }    
    /**
     * Append the <em>autofocus</em> attribute with the given String parameter as its value.
     *
     * <p>for input elements</p>
     *
     * @param autofocus the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5    
     */
    public HtmlAttributes autofocus(String autofocus) {
        return this.add("autofocus", autofocus, ESCAPE_CHARS);
    }        
    /**
     * Append the <em>readonly</em> attribute with the given String parameter as its value.
     *
     * <p>for text and passwd</p>
     *
     * <p>The value of readonly will be XML escaped. Use add("readonly",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param readonly the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes readonly(String readonly) {
        return this.add("readonly", readonly, ESCAPE_CHARS);
    }
    /**
     * Append the <em>headers</em> attribute with the given String parameter as its value.
     *
     * <p>list of id's for header cells</p>
     *
     * <p>The value of headers will be XML escaped. Use add("headers",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param headers the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes headers(String headers) {
        return this.add("headers", headers, ESCAPE_CHARS);
    }
    /**
     * Append the <em>cols</em> attribute with the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of cols will be XML escaped. Use add("cols",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param cols the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes cols(String cols) {
        return this.add("cols", cols, ESCAPE_CHARS);
    }
    /**
     * Append the <em>cols</em> attribute with the given Integer parameter as its value.
     *    
     * @param cols the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes cols(Integer cols) {
        return this.add("cols", cols);
    }    
    /**
     * Append the <em>char</em> attribute with the given String parameter as its value.
     *
     * <p>alignment char, e.g. char=':'</p>
     *
     * <p>The value of char will be XML escaped. Use add("char",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param char_ the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes char_(String char_) {
        return this.add("char", char_, ESCAPE_CHARS);
    }
    /**
     * Append the <em>cellpadding</em> attribute with the given String parameter as its value.
     *
     * <p>spacing within cells</p>
     *
     * <p>The value of cellpadding will be XML escaped. Use add("cellpadding",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param cellpadding the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes cellpadding(String cellpadding) {
        return this.add("cellpadding", cellpadding, ESCAPE_CHARS);
    }
    /**
     * Append the <em>type</em> attribute with the given String parameter as its value.
     *
     * <p>for use as form button</p>
     *
     * <p>The value of type will be XML escaped. Use add("type",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param type the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes type(String type) {
        return this.add("type", type, ESCAPE_CHARS);
    }
    /**
     * Append the <em>cellspacing</em> attribute with the given String parameter as its value.
     *
     * <p>spacing between cells</p>
     *
     * <p>The value of cellspacing will be XML escaped. Use add("cellspacing",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param cellspacing the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes cellspacing(String cellspacing) {
        return this.add("cellspacing", cellspacing, ESCAPE_CHARS);
    }
    /**
     * Append the <em>hreflang</em> attribute with the given String parameter as its value.
     *
     * <p>language code</p>
     *
     * <p>The value of hreflang will be XML escaped. Use add("hreflang",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param hreflang the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes hreflang(String hreflang) {
        return this.add("hreflang", hreflang, ESCAPE_CHARS);
    }
    /**
     * Append the <em>frameborder</em> attribute with the given String parameter as its value.
     *
     * <p>request frame borders?</p>
     *
     * <p>The value of frameborder will be XML escaped. Use add("frameborder",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param frameborder the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes frameborder(String frameborder) {
        return this.add("frameborder", frameborder, ESCAPE_CHARS);
    }
    /**
     * Append the <em>compact</em> attribute with the given String parameter as its value.
     *
     * <p>reduced interitem spacing</p>
     *
     * <p>The value of compact will be XML escaped. Use add("compact",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param compact the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes compact(String compact) {
        return this.add("compact", compact, ESCAPE_CHARS);
    }
    /**
     * Append the <em>height</em> attribute with the given String parameter as its value.
     *
     * <p>height for cell</p>
     *
     * <p>The value of height will be XML escaped. Use add("height",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param height the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes height(String height) {
        return this.add("height", height, ESCAPE_CHARS);
    }
    /**
     * Append the <em>height</em> attribute with the given Integer parameter as its value.
     *
     * <p>height for cell</p>
     *
     * @param height the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes height(Integer height) {
        return this.add("height", height);
    }    
    /**
     * Append the <em>maxlength</em> attribute with the given String parameter as its value.
     *
     * <p>max chars for text fields</p>
     *
     * <p>The value of maxlength will be XML escaped. Use add("maxlength",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param maxlength the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes maxlength(String maxlength) {
        return this.add("maxlength", maxlength, ESCAPE_CHARS);
    }
    /**
     * Append the <em>maxlength</em> attribute with the given Integer parameter as its value.
     *    
     * @param maxlength the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes maxlength(int maxlength) {
        return this.add("maxlength", maxlength);
    }    
    /**
     * Append the <em>onblur</em> attribute with the given String parameter as its value.
     *
     * <p>the element lost the focus</p>
     *
     * <p>The value of onblur will be XML escaped. Use add("onblur",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onblur the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onBlur(String onblur) {
        return this.addScript("onblur", onblur, ESCAPE_CHARS);
    }
    /**
     * Append the <em>value</em> attribute with the given String parameter as its value.
     *
     * <p>reset sequence number</p>
     *
     * <p>The value of value will be XML escaped. Use add("value",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param value the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes value(String value) {
        return this.add("value", value, ESCAPE_CHARS);
    }
    /**
     * Append the <em>action</em> attribute with the given String parameter as its value.
     *
     * <p>server-side form handler</p>
     *
     * <p>The value of action will be XML escaped. Use add("action",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param action the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes action(String action) {
        return this.add("action", action, ESCAPE_CHARS);
    }
    /**
     * Append the <em>text</em> attribute with the given String parameter as its value.
     *
     * <p>document text color</p>
     *
     * <p>The value of text will be XML escaped. Use add("text",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param text the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes text(String text) {
        return this.add("text", text, ESCAPE_CHARS);
    }
    /**
     * Append the <em>colspan</em> attribute with the given String parameter as its value.
     *
     * <p>number of cols spanned by cell</p>
     *
     * <p>The value of colspan will be XML escaped. Use add("colspan",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param colspan the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes colspan(String colspan) {
        return this.add("colspan", colspan, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onmouseout</em> attribute with the given String parameter as its value.
     *
     * <p>a pointer was moved away</p>
     *
     * <p>The value of onmouseout will be XML escaped. Use add("onmouseout",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onmouseout the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onMouseout(String onmouseout) {
        return this.addScript("onmouseout", onmouseout, ESCAPE_CHARS);
    }
    /**
     * Append the <em>width</em> attribute with the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of width will be XML escaped. Use add("width",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param width the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes width(String width) {
        return this.add("width", width, ESCAPE_CHARS);
    }
    /**
     * Append the <em>width</em> attribute with the given integer parameter as its value.
     *     
     * @param width the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes width(Integer width) {
        return this.add("width", width);
    }    
    /**
     * Append the <em>align</em> attribute with the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of align will be XML escaped. Use add("align",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param align the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes align(String align) {
        return this.add("align", align, ESCAPE_CHARS);
    }
    /**
     * Append the <em>abbr</em> attribute with the given String parameter as its value.
     *
     * <p>abbreviation for header cell</p>
     *
     * <p>The value of abbr will be XML escaped. Use add("abbr",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param abbr the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes abbr(String abbr) {
        return this.add("abbr", abbr, ESCAPE_CHARS);
    }
    /**
     * Append the <em>class</em> attribute with the given String parameter as its value.
     *
     * <p>space separated list of classes</p>
     *
     * <p>The value of class will be XML escaped. Use add("class",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param class_ the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes class_(String class_) {
        return this.add("class", class_, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onkeyup</em> attribute with the given String parameter as its value.
     *
     * <p>a key was released</p>
     *
     * <p>The value of onkeyup will be XML escaped. Use add("onkeyup",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onkeyup the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onKeyup(String onkeyup) {
        return this.addScript("onkeyup", onkeyup, ESCAPE_CHARS);
    }
    /**
     * Append the <em>label</em> attribute with the given String parameter as its value.
     *
     * <p>for use in hierarchical menus</p>
     *
     * <p>The value of label will be XML escaped. Use add("label",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param label the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes label(String label) {
        return this.add("label", label, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onfocus</em> attribute with the given String parameter as its value.
     *
     * <p>the element got the focus</p>
     *
     * <p>The value of onfocus will be XML escaped. Use add("onfocus",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onfocus the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onFocus(String onfocus) {
        return this.addScript("onfocus", onfocus, ESCAPE_CHARS);
    }
    /**
     * Append the <em>shape</em> attribute with the given String parameter as its value.
     *
     * <p>for use with client-side image maps</p>
     *
     * <p>The value of shape will be XML escaped. Use add("shape",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param shape the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes shape(String shape) {
        return this.add("shape", shape, ESCAPE_CHARS);
    }
    /**
     * Append the <em>code</em> attribute with the given String parameter as its value.
     *
     * <p>applet class file</p>
     *
     * <p>The value of code will be XML escaped. Use add("code",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param code the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes code(String code) {
        return this.add("code", code, ESCAPE_CHARS);
    }
    /**
     * Append the <em>rowspan</em> attribute with the given String parameter as its value.
     *
     * <p>number of rows spanned by cell</p>
     *
     * <p>The value of rowspan will be XML escaped. Use add("rowspan",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param rowspan the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes rowspan(String rowspan) {
        return this.add("rowspan", rowspan, ESCAPE_CHARS);
    }
    /**
     * Append the <em>noresize</em> attribute with the given String parameter as its value.
     *
     * <p>allow users to resize frames?</p>
     *
     * <p>The value of noresize will be XML escaped. Use add("noresize",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param noresize the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes noresize(String noresize) {
        return this.add("noresize", noresize, ESCAPE_CHARS);
    }
    /**
     * Append the <em>size</em> attribute with the given String parameter as its value.
     *
     * <p>rows visible</p>
     *
     * <p>The value of size will be XML escaped. Use add("size",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param size the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes size(String size) {
        return this.add("size", size, ESCAPE_CHARS);
    }
    /**
     * Append the <em>size</em> attribute with the given Integer parameter as its value.
     * 
     * @param size the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes size(Integer size) {
        return this.add("size", size);
    }    
    /**
     * Append the <em>onreset</em> attribute with the given String parameter as its value.
     *
     * <p>the form was reset</p>
     *
     * <p>The value of onreset will be XML escaped. Use add("onreset",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onreset the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onReset(String onreset) {
        return this.addScript("onreset", onreset, ESCAPE_CHARS);
    }
    /**
     * Append the <em>rows</em> attribute with the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of rows will be XML escaped. Use add("rows",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param rows the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes rows(String rows) {
        return this.add("rows", rows, ESCAPE_CHARS);
    }
    /**
     * Append the <em>rows</em> attribute with the given Integer parameter as its value.
     *     
     * @param rows the Integer | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */    
    public HtmlAttributes rows(Integer rows) {
        return this.add("rows", rows);
    }    
    /**
     * Append the <em>frame</em> attribute with the given String parameter as its value.
     *
     * <p>which parts of frame to render</p>
     *
     * <p>The value of frame will be XML escaped. Use add("frame",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param frame the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes frame(String frame) {
        return this.add("frame", frame, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onselect</em> attribute with the given String parameter as its value.
     *
     * <p>some text was selected</p>
     *
     * <p>The value of onselect will be XML escaped. Use add("onselect",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onselect the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onSelect(String onselect) {
        return this.addScript("onselect", onselect, ESCAPE_CHARS);
    }
    /**
     * Append the <em>scrolling</em> attribute with the given String parameter as its value.
     *
     * <p>scrollbar or none</p>
     *
     * <p>The value of scrolling will be XML escaped. Use add("scrolling",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param scrolling the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes scrolling(String scrolling) {
        return this.add("scrolling", scrolling, ESCAPE_CHARS);
    }
    /**
     * Append the <em>media</em> attribute with the given String parameter as its value.
     *
     * <p>for rendering on these media</p>
     *
     * <p>The value of media will be XML escaped. Use add("media",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param media the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes media(String media) {
        return this.add("media", media, ESCAPE_CHARS);
    }
    /**
     * Append the <em>span</em> attribute with the given String parameter as its value.
     *
     * <p>default number of columns in group</p>
     *
     * <p>The value of span will be XML escaped. Use add("span",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param span the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes span(String span) {
        return this.add("span", span, ESCAPE_CHARS);
    }
    /**
     * Append the <em>scope</em> attribute with the given String parameter as its value.
     *
     * <p>scope covered by header cells</p>
     *
     * <p>The value of scope will be XML escaped. Use add("scope",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param scope the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes scope(String scope) {
        return this.add("scope", scope, ESCAPE_CHARS);
    }
    /**
     * Append the <em>usemap</em> attribute with the given String parameter as its value.
     *
     * <p>use client-side image map</p>
     *
     * <p>The value of usemap will be XML escaped. Use add("usemap",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param usemap the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes usemap(String usemap) {
        return this.add("usemap", usemap, ESCAPE_CHARS);
    }
    /**
     * Append the <em>object</em> attribute with the given String parameter as its value.
     *
     * <p>serialized applet file</p>
     *
     * <p>The value of object will be XML escaped. Use add("object",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param object the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes object(String object) {
        return this.add("object", object, ESCAPE_CHARS);
    }
    /**
     * Append the <em>lang</em> attribute with the given String parameter as its value.
     *
     * <p>language code</p>
     *
     * <p>The value of lang will be XML escaped. Use add("lang",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param lang the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes lang(String lang) {
        return this.add("lang", lang, ESCAPE_CHARS);
    }
    /**
     * Append the <em>id</em> attribute with the given String parameter as its value.
     *
     * <p>document-wide unique id</p>
     *
     * <p>The value of id will be XML escaped. Use add("id",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param id the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes id(String id) {
        return this.add("id", id, ESCAPE_CHARS);
    }
    /**
     * Append the <em>selected</em> attribute with the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of selected will be XML escaped. Use add("selected",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param selected the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes selected(String selected) {
        return this.add("selected", selected, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ismap</em> attribute with the given String parameter as its value.
     *
     * <p>use server-side image map</p>
     *
     * <p>The value of ismap will be XML escaped. Use add("ismap",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ismap the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes ismap(String ismap) {
        return this.add("ismap", ismap, ESCAPE_CHARS);
    }
    /**
     * Append the <em>style</em> attribute with the given String parameter as its value.
     *
     * <p>associated style info</p>
     *
     * <p>The value of style will be XML escaped. Use add("style",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param style the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes style(String style) {
        return this.add("style", style, ESCAPE_CHARS);
    }
    /**
     * Append the <em>dir</em> attribute with the given String parameter as its value.
     *
     * <p>directionality</p>
     *
     * <p>The value of dir will be XML escaped. Use add("dir",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param dir the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes dir(String dir) {
        return this.add("dir", dir, ESCAPE_CHARS);
    }
    /**
     * Append the <em>alt</em> attribute with the given String parameter as its value.
     *
     * <p>short description</p>
     *
     * <p>The value of alt will be XML escaped. Use add("alt",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param alt the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes alt(String alt) {
        return this.add("alt", alt, ESCAPE_CHARS);
    }
    /**
     * Append the <em>name</em> attribute with the given String parameter as its value.
     *
     * <p>metainformation name</p>
     *
     * <p>The value of name will be XML escaped. Use add("name",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param name the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes name(String name) {
        return this.add("name", name, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onmouseup</em> attribute with the given String parameter as its value.
     *
     * <p>a pointer button was released</p>
     *
     * <p>The value of onmouseup will be XML escaped. Use add("onmouseup",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onmouseup the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onMouseup(String onmouseup) {
        return this.addScript("onmouseup", onmouseup, ESCAPE_CHARS);
    }
    /**
     * Append the <em>nowrap</em> attribute with the given String parameter as its value.
     *
     * <p>suppress word wrap</p>
     *
     * <p>The value of nowrap will be XML escaped. Use add("nowrap",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param nowrap the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes nowrap(String nowrap) {
        return this.add("nowrap", nowrap, ESCAPE_CHARS);
    }
    /**
     * Append the <em>multiple</em> attribute with the given String parameter as its value.
     *
     * <p>default is single selection</p>
     *
     * <p>The value of multiple will be XML escaped. Use add("multiple",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param multiple the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes multiple(String multiple) {
        return this.add("multiple", multiple, ESCAPE_CHARS);
    }
    /**
     * Append the <em>classid</em> attribute with the given String parameter as its value.
     *
     * <p>identifies an implementation</p>
     *
     * <p>The value of classid will be XML escaped. Use add("classid",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param classid the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes classid(String classid) {
        return this.add("classid", classid, ESCAPE_CHARS);
    }
    /**
     * Append the <em>profile</em> attribute with the given String parameter as its value.
     *
     * <p>named dictionary of meta info</p>
     *
     * <p>The value of profile will be XML escaped. Use add("profile",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param profile the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes profile(String profile) {
        return this.add("profile", profile, ESCAPE_CHARS);
    }
    /**
     * Append the <em>axis</em> attribute with the given String parameter as its value.
     *
     * <p>names groups of related headers</p>
     *
     * <p>The value of axis will be XML escaped. Use add("axis",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param axis the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes axis(String axis) {
        return this.add("axis", axis, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onmousemove</em> attribute with the given String parameter as its value.
     *
     * <p>a pointer was moved within</p>
     *
     * <p>The value of onmousemove will be XML escaped. Use add("onmousemove",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onmousemove the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onMousemove(String onmousemove) {
        return this.addScript("onmousemove", onmousemove, ESCAPE_CHARS);
    }
    /**
     * Append the <em>tabindex</em> attribute with the given String parameter as its value.
     *
     * <p>position in tabbing order</p>
     *
     * <p>The value of tabindex will be XML escaped. Use add("tabindex",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param tabindex the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes tabindex(String tabindex) {
        return this.add("tabindex", tabindex, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onchange</em> attribute with the given String parameter as its value.
     *
     * <p>the element value was changed</p>
     *
     * <p>The value of onchange will be XML escaped. Use add("onchange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onchange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onChange(String onchange) {
        return this.addScript("onchange", onchange, ESCAPE_CHARS);
    }
    /**
     * Append the <em>rules</em> attribute with the given String parameter as its value.
     *
     * <p>rulings between rows and cols</p>
     *
     * <p>The value of rules will be XML escaped. Use add("rules",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param rules the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes rules(String rules) {
        return this.add("rules", rules, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onmouseover</em> attribute with the given String parameter as its value.
     *
     * <p>a pointer was moved onto</p>
     *
     * <p>The value of onmouseover will be XML escaped. Use add("onmouseover",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onmouseover the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onMouseover(String onmouseover) {
        return this.addScript("onmouseover", onmouseover, ESCAPE_CHARS);
    }
    /**
     * Append the <em>coords</em> attribute with the given String parameter as its value.
     *
     * <p>for use with client-side image maps</p>
     *
     * <p>The value of coords will be XML escaped. Use add("coords",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param coords the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes coords(String coords) {
        return this.add("coords", coords, ESCAPE_CHARS);
    }
    /**
     * Append the <em>color</em> attribute with the given String parameter as its value.
     *
     * <p>text color</p>
     *
     * <p>The value of color will be XML escaped. Use add("color",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param color the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes color(String color) {
        return this.add("color", color, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onload</em> attribute with the given String parameter as its value.
     *
     * <p>the document has been loaded</p>
     *
     * <p>The value of onload will be XML escaped. Use add("onload",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onload the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onLoad(String onload) {
        return this.addScript("onload", onload, ESCAPE_CHARS);
    }
    /**
     * Append the <em>target</em> attribute with the given String parameter as its value.
     *
     * <p>render in this frame</p>
     *
     * <p>The value of target will be XML escaped. Use add("target",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param target the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes target(String target) {
        return this.add("target", target, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onclick</em> attribute with the given String parameter as its value.
     *
     * <p>a pointer button was clicked</p>
     *
     * <p>The value of onclick will be XML escaped. Use add("onclick",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onclick the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes onClick(String onclick) {
        return this.addScript("onclick", onclick, ESCAPE_CHARS);
    }
    /**
     * Append the <em>valign</em> attribute with the given String parameter as its value.
     *
     * <p>vertical alignment in cells</p>
     *
     * <p>The value of valign will be XML escaped. Use add("valign",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param valign the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes valign(String valign) {
        return this.add("valign", valign, ESCAPE_CHARS);
    }
    /**
     * Append the <em>disabled</em> attribute with the given String parameter as its value.
     *
     * <p>unavailable in this context</p>
     *
     * <p>The value of disabled will be XML escaped. Use add("disabled",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param disabled the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes disabled(String disabled) {
        return this.add("disabled", disabled, ESCAPE_CHARS);
    }
    /**
     * Append the <em>codebase</em> attribute with the given String parameter as its value.
     *
     * <p>optional base URI for applet</p>
     *
     * <p>The value of codebase will be XML escaped. Use add("codebase",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param codebase the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01      
     */
    public HtmlAttributes codebase(String codebase) {
        return this.add("codebase", codebase, ESCAPE_CHARS);
    }
    
	// ///////////////////////////////////////////////////////////////////////////////
	//
	// Methods below are generated using the Html5AttributesGenerator. DO NOT EDIT
	//
	// ///////////////////////////////////////////////////////////////////////////////    
    
    /**
     * Append the <em>oncontextmenu</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a context menu is triggered</p>
     *
     * <p>The value of oncontextmenu will be XML escaped. Use add("oncontextmenu",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param oncontextmenu the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onContextmenu(String oncontextmenu) {
        return this.addScript("oncontextmenu", oncontextmenu, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onformchange</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a form changes</p>
     *
     * <p>The value of onformchange will be XML escaped. Use add("onformchange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onformchange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onFormchange(String onformchange) {
        return this.addScript("onformchange", onformchange, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onforminput</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a form gets user input</p>
     *
     * <p>The value of onforminput will be XML escaped. Use add("onforminput",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onforminput the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onForminput(String onforminput) {
        return this.addScript("onforminput", onforminput, ESCAPE_CHARS);
    }
    /**
     * Append the <em>oninput</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an element gets user input</p>
     *
     * <p>The value of oninput will be XML escaped. Use add("oninput",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param oninput the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onInput(String oninput) {
        return this.addScript("oninput", oninput, ESCAPE_CHARS);
    }
    /**
     * Append the <em>oninvalid</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an element is invalid</p>
     *
     * <p>The value of oninvalid will be XML escaped. Use add("oninvalid",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param oninvalid the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onInvalid(String oninvalid) {
        return this.addScript("oninvalid", oninvalid, ESCAPE_CHARS);
    }
    /**
     * Append the <em>oncanplay</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media can start play, but might has to 
stop for buffering</p>
     *
     * <p>The value of oncanplay will be XML escaped. Use add("oncanplay",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param oncanplay the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onCanplay(String oncanplay) {
        return this.addScript("oncanplay", oncanplay, ESCAPE_CHARS);
    }
    /**
     * Append the <em>oncanplaythrough</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media can be played to the end, without 
stopping for buffering</p>
     *
     * <p>The value of oncanplaythrough will be XML escaped. Use add("oncanplaythrough",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param oncanplaythrough the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onCanplaythrough(String oncanplaythrough) {
        return this.addScript("oncanplaythrough", oncanplaythrough, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondurationchange</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the length of the media is changed</p>
     *
     * <p>The value of ondurationchange will be XML escaped. Use add("ondurationchange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondurationchange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDurationchange(String ondurationchange) {
        return this.addScript("ondurationchange", ondurationchange, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onemptied</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a media resource element suddenly becomes 
empty (network errors, errors on load etc.)</p>
     *
     * <p>The value of onemptied will be XML escaped. Use add("onemptied",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onemptied the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onEmptied(String onemptied) {
        return this.addScript("onemptied", onemptied, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onended</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media has reach the end</p>
     *
     * <p>The value of onended will be XML escaped. Use add("onended",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onended the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onEnded(String onended) {
        return this.addScript("onended", onended, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onloadeddata</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media data is loaded</p>
     *
     * <p>The value of onloadeddata will be XML escaped. Use add("onloadeddata",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onloadeddata the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onLoadeddata(String onloadeddata) {
        return this.addScript("onloadeddata", onloadeddata, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onloadedmetadata</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the duration and other media data of a 
media element is loaded</p>
     *
     * <p>The value of onloadedmetadata will be XML escaped. Use add("onloadedmetadata",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onloadedmetadata the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onLoadedmetadata(String onloadedmetadata) {
        return this.addScript("onloadedmetadata", onloadedmetadata, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onloadstart</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the browser starts to load the media data</p>
     *
     * <p>The value of onloadstart will be XML escaped. Use add("onloadstart",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onloadstart the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onLoadstart(String onloadstart) {
        return this.addScript("onloadstart", onloadstart, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onpause</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media data is paused</p>
     *
     * <p>The value of onpause will be XML escaped. Use add("onpause",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onpause the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onPause(String onpause) {
        return this.addScript("onpause", onpause, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onplay</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media data is going to start playing</p>
     *
     * <p>The value of onplay will be XML escaped. Use add("onplay",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onplay the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onPlay(String onplay) {
        return this.addScript("onplay", onplay, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onplaying</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media data has start playing</p>
     *
     * <p>The value of onplaying will be XML escaped. Use add("onplaying",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onplaying the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onPlaying(String onplaying) {
        return this.addScript("onplaying", onplaying, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onprogress</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the browser is fetching the media data</p>
     *
     * <p>The value of onprogress will be XML escaped. Use add("onprogress",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onprogress the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onProgress(String onprogress) {
        return this.addScript("onprogress", onprogress, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onratechange</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the media data's playing rate has changed</p>
     *
     * <p>The value of onratechange will be XML escaped. Use add("onratechange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onratechange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onRatechange(String onratechange) {
        return this.addScript("onratechange", onratechange, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onreadystatechange</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the ready-state changes</p>
     *
     * <p>The value of onreadystatechange will be XML escaped. Use add("onreadystatechange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onreadystatechange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onReadystatechange(String onreadystatechange) {
        return this.addScript("onreadystatechange", onreadystatechange, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onseeked</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a media element's seeking attribute is no 
longer true, and the seeking has ended</p>
     *
     * <p>The value of onseeked will be XML escaped. Use add("onseeked",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onseeked the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onSeeked(String onseeked) {
        return this.addScript("onseeked", onseeked, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onseeking</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a media element's seeking attribute is 
true, and the seeking has begun</p>
     *
     * <p>The value of onseeking will be XML escaped. Use add("onseeking",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onseeking the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onSeeking(String onseeking) {
        return this.addScript("onseeking", onseeking, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onstalled</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when there is an error in fetching media data 
(stalled)</p>
     *
     * <p>The value of onstalled will be XML escaped. Use add("onstalled",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onstalled the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onStalled(String onstalled) {
        return this.addScript("onstalled", onstalled, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onsuspend</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the browser has been fetching media data, 
but stopped before the entire media file was fetched</p>
     *
     * <p>The value of onsuspend will be XML escaped. Use add("onsuspend",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onsuspend the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onSuspend(String onsuspend) {
        return this.addScript("onsuspend", onsuspend, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ontimeupdate</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media changes its playing position</p>
     *
     * <p>The value of ontimeupdate will be XML escaped. Use add("ontimeupdate",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ontimeupdate the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onTimeupdate(String ontimeupdate) {
        return this.addScript("ontimeupdate", ontimeupdate, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onvolumechange</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media changes the volume, also when 
volume is set to "mute"</p>
     *
     * <p>The value of onvolumechange will be XML escaped. Use add("onvolumechange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onvolumechange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onVolumechange(String onvolumechange) {
        return this.addScript("onvolumechange", onvolumechange, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onwaiting</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when media has stopped playing, but is 
expected to resume</p>
     *
     * <p>The value of onwaiting will be XML escaped. Use add("onwaiting",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onwaiting the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onWaiting(String onwaiting) {
        return this.addScript("onwaiting", onwaiting, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondrag</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an element is dragged</p>
     *
     * <p>The value of ondrag will be XML escaped. Use add("ondrag",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondrag the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDrag(String ondrag) {
        return this.addScript("ondrag", ondrag, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondragend</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run at the end of a drag operation</p>
     *
     * <p>The value of ondragend will be XML escaped. Use add("ondragend",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondragend the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDragend(String ondragend) {
        return this.addScript("ondragend", ondragend, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondragenter</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an element has been dragged to a valid 
drop target</p>
     *
     * <p>The value of ondragenter will be XML escaped. Use add("ondragenter",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondragenter the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDragenter(String ondragenter) {
        return this.addScript("ondragenter", ondragenter, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondragleave</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an element leaves a valid drop target</p>
     *
     * <p>The value of ondragleave will be XML escaped. Use add("ondragleave",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondragleave the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDragleave(String ondragleave) {
        return this.addScript("ondragleave", ondragleave, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondragover</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an element is being dragged over a 
valid drop target</p>
     *
     * <p>The value of ondragover will be XML escaped. Use add("ondragover",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondragover the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDragover(String ondragover) {
        return this.addScript("ondragover", ondragover, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondragstart</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run at the start of a drag operation</p>
     *
     * <p>The value of ondragstart will be XML escaped. Use add("ondragstart",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondragstart the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDragstart(String ondragstart) {
        return this.addScript("ondragstart", ondragstart, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ondrop</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when dragged element is being dropped</p>
     *
     * <p>The value of ondrop will be XML escaped. Use add("ondrop",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ondrop the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onDrop(String ondrop) {
        return this.addScript("ondrop", ondrop, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onmousewheel</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the mouse wheel is being rotated</p>
     *
     * <p>The value of onmousewheel will be XML escaped. Use add("onmousewheel",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onmousewheel the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onMousewheel(String onmousewheel) {
        return this.addScript("onmousewheel", onmousewheel, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onscroll</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an element's scrollbar is being scrolled</p>
     *
     * <p>The value of onscroll will be XML escaped. Use add("onscroll",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onscroll the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onScroll(String onscroll) {
        return this.addScript("onscroll", onscroll, ESCAPE_CHARS);
    }
    /**
     * Append the <em>contenteditable</em> attribute with the given String parameter as its value.
     *
     * <p>Specifies if the user is allowed to edit the content or not</p>
     *
     * <p>The value of contenteditable will be XML escaped. Use add("contenteditable",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param contenteditable the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes contenteditable(String contenteditable) {
        return this.add("contenteditable", contenteditable, ESCAPE_CHARS);
    }
    /**
     * Append the <em>contextmenu</em> attribute with the given String parameter as its value.
     *
     * <p>Specifies the context menu for an element</p>
     *
     * <p>The value of contextmenu will be XML escaped. Use add("contextmenu",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param contextmenu the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes contextmenu(String contextmenu) {
        return this.add("contextmenu", contextmenu, ESCAPE_CHARS);
    }
    /**
     * Append the <em>draggable</em> attribute with the given String parameter as its value.
     *
     * <p>Specifies whether or not a user is allowed to drag an element</p>
     *
     * <p>The value of draggable will be XML escaped. Use add("draggable",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param draggable the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes draggable(String draggable) {
        return this.add("draggable", draggable, ESCAPE_CHARS);
    }
    /**
     * Append the <em>dropzone</em> attribute with the given String parameter as its value.
     *
     * <p>Specifies what happens when dragged items/data is dropped in 
the element</p>
     *
     * <p>The value of dropzone will be XML escaped. Use add("dropzone",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param dropzone the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes dropzone(String dropzone) {
        return this.add("dropzone", dropzone, ESCAPE_CHARS);
    }
    /**
     * Append the <em>hidden</em> attribute with the given String parameter as its value.
     *
     * <p>Specifies that the element is not relevant. Hidden elements are 
not displayed</p>
     *
     * <p>The value of hidden will be XML escaped. Use add("hidden",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param hidden the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes hidden(String hidden) {
        return this.add("hidden", hidden, ESCAPE_CHARS);
    }
    /**
     * Append the <em>spellcheck</em> attribute with the given String parameter as its value.
     *
     * <p>Specifies if the element must have its spelling and grammar 
checked</p>
     *
     * <p>The value of spellcheck will be XML escaped. Use add("spellcheck",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param spellcheck the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes spellcheck(String spellcheck) {
        return this.add("spellcheck", spellcheck, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onafterprint</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run after the document is printed</p>
     *
     * <p>The value of onafterprint will be XML escaped. Use add("onafterprint",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onafterprint the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onAfterprint(String onafterprint) {
        return this.addScript("onafterprint", onafterprint, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onbeforeprint</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run before the document is printed</p>
     *
     * <p>The value of onbeforeprint will be XML escaped. Use add("onbeforeprint",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onbeforeprint the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onBeforeprint(String onbeforeprint) {
        return this.addScript("onbeforeprint", onbeforeprint, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onbeforeonload</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run before the document loads</p>
     *
     * <p>The value of onbeforeonload will be XML escaped. Use add("onbeforeonload",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onbeforeonload the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onBeforeonload(String onbeforeonload) {
        return this.addScript("onbeforeonload", onbeforeonload, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onerror</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when an error occur</p>
     *
     * <p>The value of onerror will be XML escaped. Use add("onerror",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onerror the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onError(String onerror) {
        return this.addScript("onerror", onerror, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onhaschange</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the document has change</p>
     *
     * <p>The value of onhaschange will be XML escaped. Use add("onhaschange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onhaschange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onHaschange(String onhaschange) {
        return this.addScript("onhaschange", onhaschange, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onmessage</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the message is triggered</p>
     *
     * <p>The value of onmessage will be XML escaped. Use add("onmessage",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onmessage the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onMessage(String onmessage) {
        return this.addScript("onmessage", onmessage, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onoffline</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the document goes offline</p>
     *
     * <p>The value of onoffline will be XML escaped. Use add("onoffline",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onoffline the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onOffline(String script) {
        return this.addScript("onoffline", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>ononline</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the document comes online</p>
     *
     * <p>The value of ononline will be XML escaped. Use add("ononline",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param ononline the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onOnline(String script) {
        return this.addScript("ononline", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onpagehide</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the window is hidden</p>
     *
     * <p>The value of onpagehide will be XML escaped. Use add("onpagehide",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onpagehide the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onPagehide(String script) {
        return this.addScript("onpagehide", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onpageshow</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the window becomes visible </p>
     *
     * <p>The value of onpageshow will be XML escaped. Use add("onpageshow",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onpageshow the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onPageshow(String script) {
        return this.addScript("onpageshow", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onpopstate</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the window's history changes</p>
     *
     * <p>The value of onpopstate will be XML escaped. Use add("onpopstate",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onpopstate the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onPopstate(String script) {
        return this.addScript("onpopstate", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onredo</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the document performs a redo</p>
     *
     * <p>The value of onredo will be XML escaped. Use add("onredo",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onredo the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onRedo(String script) {
        return this.addScript("onredo", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onresize</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when the window is resized</p>
     *
     * <p>The value of onresize will be XML escaped. Use add("onresize",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onresize the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onResize(String script) {
        return this.addScript("onresize", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onstorage</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a document loads</p>
     *
     * <p>The value of onstorage will be XML escaped. Use add("onstorage",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onstorage the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onStorage(String script) {
        return this.addScript("onstorage", script, ESCAPE_CHARS);
    }
    /**
     * Append the <em>onundo</em> attribute with the given String parameter as its value.
     *
     * <p>Script to be run when a document performs an undo</p>
     *
     * <p>The value of onundo will be XML escaped. Use add("onundo",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *     
     * @param onundo the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public HtmlAttributes onUndo(String script) {
        return this.addScript("onundo", script, ESCAPE_CHARS);
    }
    //
    // Methods below are for convencience when using e.g. JQuery mobile 
    //
    /**
     * @param dataTheme
     * @return
     */
    public HtmlAttributes dataTheme(String dataTheme) {
        return this.add("data-theme", dataTheme, false);
    }
    // fixed, inline
    /**
     * @param dataPosition
     * @return
     */
    public HtmlAttributes dataPosition(String dataPosition) {
        return this.add("data-position", dataPosition, false);
    }

    /**
     * @param noBackButton
     * @return
     */
    public HtmlAttributes dataNoBackButton(boolean noBackButton) {
        return this.add("data-nobackbtn", String.valueOf(noBackButton), false);
    }

    // pop, slide, slideup, slidedown, pop, fade, flip
    /**
     * @param transition
     * @return
     */
    public HtmlAttributes dataTransition(String transition) {
        return this.add("data-transition", transition, false);
    }

    // dialog
    /**
     * @param rel
     * @return
     */
    public HtmlAttributes dataRel(String rel) {
        return this.add("data-rel", rel, false);
    }

    // gear, check, delete
    /**
     * @param icon
     * @return
     */
    public HtmlAttributes dataIcon(String icon) {
        return this.add("data-icon", icon, false);
    }

    /**
     * @param pos
     * @return
     */
    public HtmlAttributes dataIconPos(String pos) {
        return this.add("data-iconpos", pos, false);
    }

    // xhtml(5) http://stackoverflow.com/questions/1678238/what-is-xhtmls-role-attribute-what-do-you-use-it-for
    public HtmlAttributes role(String role) {
        return this.add("role", role, false);
    }

    /**
     * @param inset
     * @return
     */
    public HtmlAttributes dataInset(boolean inset) {
        return this.add("data-inset", String.valueOf(inset), false);
    }

    /**
     * @param inline
     * @return
     */
    public HtmlAttributes dataInline(boolean inline) {
        return this.add("data-inline", String.valueOf(inline), false);
    }

    /**
     * @param role
     * @return
     */
    public HtmlAttributes dataRole(String role) {
        return this.add("data-role", role, false);
    }

    /**
     * @param collapsed
     * @return
     */
    public HtmlAttributes dataCollapsed(boolean collapsed) {
        return this.add("data-collapsed", String.valueOf(collapsed), false);
    }    
    /**
     * @param type
     * @return
     */
    public HtmlAttributes dataType(String type) {
        return this.add("data-type", type, false);
    }    
    /**
     * @param direction
     * @return
     */
    public HtmlAttributes dataDirection(String direction) {
        return this.add("data-direction", direction, false);
    }  
    /**
     * @param xy
     * @return
     */
    public HtmlAttributes dataScroll(String xy) {
        return this.add("data-scroll", xy, false);
    }
    /**
     * @param inline
     * @return
     */
    public HtmlAttributes dataInline(String inline) {
        return this.add("data-inline", inline, false);
    } 
    /**
     * @param isMini
     * @return
     */
    public HtmlAttributes dataMini(boolean isMini) {
        return this.add("data-mini", Boolean.toString(isMini), false);
    }     
    /**
     * @param dataContentTheme
     * @return
     */
    public HtmlAttributes dataContentTheme(String dataContentTheme) {
        return this.add("data-content-theme", dataContentTheme, false);
    }  
    /**
     * @param enabled
     * @return
     */
    public HtmlAttributes dataAjax(boolean enabled) {
        return this.add("data-ajax", Boolean.toString(enabled), false);
    }     
    /**
     * If condition is true then add the attribute selected with value "selected".
     * @param condition
     * @return
     */
    public HtmlAttributes selected_if(boolean condition) {
        return condition ? selected("selected") : this;
    }
    /**
     * If condition is true then add the attribute checked with value "checked".
     * @param condition
     * @return
     */
    public HtmlAttributes checked_if(boolean condition) {
        return condition ? checked("checked") : this;
    }  
    /**
     * If condition is true then add the attribute disabled with value "disabled".
     * @param condition
     * @return
     */
    public HtmlAttributes disabled_if(boolean condition) {
        return condition ? disabled("disabled") : this;
    }
    /**
     * If condition is true then add the attribute autofocus with value "autofocus".
     * @param condition
     * @return
     */
    public HtmlAttributes autofocus_if(boolean condition) {
        return condition ? autofocus("autofocus") : this;
    } 
    /**
     * If condition is true then add the attribute required with value "required".
     * @param condition
     * @return
     */
    public HtmlAttributes required_if(boolean condition) {
        return condition ? required("required") : this;
    }    
}