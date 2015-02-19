package org.rendersnake;


/**
 * @author emicklei
 *
 */
public abstract class HtmlAttributesFactory {
    /**
     * Intention revealing constant to emphasize that escaping for a text item is needed or not.
     */
    public final static boolean NO_ESCAPE = false;    
    /**
     * the reverse
     */
    public final static boolean ESCAPE_CHARS = true;    
          
    /**
     * Return a new HtmlAttributes with the <em>xmlns</em> attribute and the given String parameter as its value.
     *
     * <p>to declare an xml namespace</p>
     *
     * <p>The value of xmlns will NOT be XML escaped. 
     * Use new HtmlAttributes().add("xmlns",<i>value</i>,true) if the value could have characters that need escaping.</p>
     *      
     * @param xmlns the String | null.
     * @return a new <code>HtmlAttributes</code> instance.
     */    
    public static HtmlAttributes xmlns(String xmlns) {
        return new HtmlAttributes().add("xmlns", xmlns, ESCAPE_CHARS);
    }     
    /**
     * @param flashvars
     * @return a HtmlAttributes
     */
    public static HtmlAttributes flashvars(String flashvars) {
        return new HtmlAttributes().add("flashvars", flashvars, ESCAPE_CHARS);
    }  
    /**
     * @param allow
     * @return a HtmlAttributes
     */
    public static HtmlAttributes allowFullscreen(boolean allow) {
        return new HtmlAttributes().add("allowFullScreen", Boolean.toString(allow), NO_ESCAPE);
    }    
    /**
     * @param allow
     * @return  a HtmlAttributes
     */
    public static HtmlAttributes allowScriptAccess(String allow) {
        return new HtmlAttributes().add("allowScriptAccess", allow, ESCAPE_CHARS);
    }     
    /**
     * @param key
     * @param value
     * @param escapeNeeded
     * @return  a HtmlAttributes
     */
    public static HtmlAttributes add(String key,String value,boolean escapeNeeded) {
        return new HtmlAttributes().add(key,value,escapeNeeded);
    }
    /**
     * If condition is true then add the attribute selected with value "selected".
     * @param condition
     * @return a HtmlAttributes
     */        
    public static HtmlAttributes selected_if(boolean condition) {
        return new HtmlAttributes().selected_if(condition);
    }
    /**
     * If condition is true then add the attribute checked with value "checked".
     * @param condition
     * @return  a HtmlAttributes
     */        
    public static HtmlAttributes checked__if(boolean condition) {
        return new HtmlAttributes().checked_if(condition);
    }
    /**
     * If condition is true then add the attribute disabled with value "disabled".
     * @param condition
     * @return  a HtmlAttributes
     */        
    public static HtmlAttributes disabled_if(boolean condition) {
        return new HtmlAttributes().disabled_if(condition);
    }
    /**
     * If condition is true then add the attribute autofocus with value "autofocus".
     * @param condition
     * @return  a HtmlAttributes
     */        
    public static HtmlAttributes autofocus_if(boolean condition) {
        return new HtmlAttributes().autofocus_if(condition);
    }
    /**
     * If condition is true then add the attribute required with value "required".
     * @param condition
     * @return a HtmlAttributes
     */    
    public static HtmlAttributes required_if(boolean condition) {
        return new HtmlAttributes().required_if(condition);
    }    
    
    ///////////////////////////////////////////////////////////////////////////////////
    //
    //  Methods below are generated using the AttributesMethodsGenerator. DO NOT EDIT
    //   
    ///////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Return a new HtmlAttributes with the <em>summary</em> attribute and the given String parameter as its value.
     *
     * <p>purpose/structure for speech output</p>
     *
     * <p>The value of summary will be XML escaped. 
     * Use new HtmlAttributes().add("summary",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param summary the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes summary(String summary) {
        return new HtmlAttributes().add("summary", summary, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>marginheight</em> attribute and the given String parameter as its value.
     *
     * <p>margin height in pixels</p>
     *
     * <p>The value of marginheight will be XML escaped. 
     * Use new HtmlAttributes().add("marginheight",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param marginheight the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes marginheight(String marginheight) {
        return new HtmlAttributes().add("marginheight", marginheight, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>for</em> attribute and the given String parameter as its value.
     *
     * <p>matches field ID value</p>
     *
     * <p>The value of for will be XML escaped. 
     * Use new HtmlAttributes().add("for",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param for_ the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes for_(String for_) {
        return new HtmlAttributes().add("for", for_, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>accept</em> attribute and the given String parameter as its value.
     *
     * <p>list of MIME types for file upload</p>
     *
     * <p>The value of accept will be XML escaped. 
     * Use new HtmlAttributes().add("accept",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param accept the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes accept(String accept) {
        return new HtmlAttributes().add("accept", accept, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>bgcolor</em> attribute and the given String parameter as its value.
     *
     * <p>document background color</p>
     *
     * <p>The value of bgcolor will be XML escaped. 
     * Use new HtmlAttributes().add("bgcolor",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param bgcolor the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes bgcolor(String bgcolor) {
        return new HtmlAttributes().add("bgcolor", bgcolor, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>accept-charset</em> attribute and the given String parameter as its value.
     *
     * <p>list of supported charsets</p>
     *
     * <p>The value of accept_charset will be XML escaped. 
     * Use new HtmlAttributes().add("accept_charset",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param accept_charset the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes accept_charset(String accept_charset) {
        return new HtmlAttributes().add("accept-charset", accept_charset, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>scheme</em> attribute and the given String parameter as its value.
     *
     * <p>select form of content</p>
     *
     * <p>The value of scheme will be XML escaped. 
     * Use new HtmlAttributes().add("scheme",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param scheme the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes scheme(String scheme) {
        return new HtmlAttributes().add("scheme", scheme, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>border</em> attribute and the given String parameter as its value.
     *
     * <p>link border width</p>
     *
     * <p>The value of border will be XML escaped. 
     * Use new HtmlAttributes().add("border",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param border the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes border(String border) {
        return new HtmlAttributes().add("border", border, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>vspace</em> attribute and the given String parameter as its value.
     *
     * <p>vertical gutter</p>
     *
     * <p>The value of vspace will be XML escaped. 
     * Use new HtmlAttributes().add("vspace",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param vspace the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes vspace(String vspace) {
        return new HtmlAttributes().add("vspace", vspace, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>href</em> attribute and the given String parameter as its value.
     *
     * <p>URI that acts as base URI</p>
     *
     * <p>The value of href will be XML escaped. 
     * Use new HtmlAttributes().add("href",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param href the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes href(String href) {
        return new HtmlAttributes().add("href", href, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondblclick</em> attribute and the given String parameter as its value.
     *
     * <p>a pointer button was double clicked</p>
     *
     * <p>The value of onDblclick will be XML escaped. 
     * Use new HtmlAttributes().add("onDblclick",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onDblclick the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onDblclick(String onDblclick) {
        return new HtmlAttributes().addScript("ondblclick", onDblclick, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>charset</em> attribute and the given String parameter as its value.
     *
     * <p>char encoding of linked resource</p>
     *
     * <p>The value of charset will be XML escaped. 
     * Use new HtmlAttributes().add("charset",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param charset the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes charset(String charset) {
        return new HtmlAttributes().add("charset", charset, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>longdesc</em> attribute and the given String parameter as its value.
     *
     * <p>link to long description (complements title)</p>
     *
     * <p>The value of longdesc will be XML escaped. 
     * Use new HtmlAttributes().add("longdesc",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param longdesc the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes longdesc(String longdesc) {
        return new HtmlAttributes().add("longdesc", longdesc, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>noshade</em> attribute and the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of noshade will be XML escaped. 
     * Use new HtmlAttributes().add("noshade",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param noshade the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes noshade(String noshade) {
        return new HtmlAttributes().add("noshade", noshade, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>declare</em> attribute and the given String parameter as its value.
     *
     * <p>declare but don't instantiate flag</p>
     *
     * <p>The value of declare will be XML escaped. 
     * Use new HtmlAttributes().add("declare",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param declare the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes declare(String declare) {
        return new HtmlAttributes().add("declare", declare, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>content</em> attribute and the given String parameter as its value.
     *
     * <p>associated information</p>
     *
     * <p>The value of content will be XML escaped. 
     * Use new HtmlAttributes().add("content",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param content the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes content(String content) {
        return new HtmlAttributes().add("content", content, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>cite</em> attribute and the given String parameter as its value.
     *
     * <p>info on reason for change</p>
     *
     * <p>The value of cite will be XML escaped. 
     * Use new HtmlAttributes().add("cite",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param cite the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes cite(String cite) {
        return new HtmlAttributes().add("cite", cite, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>standby</em> attribute and the given String parameter as its value.
     *
     * <p>message to show while loading</p>
     *
     * <p>The value of standby will be XML escaped. 
     * Use new HtmlAttributes().add("standby",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param standby the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes standby(String standby) {
        return new HtmlAttributes().add("standby", standby, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>start</em> attribute and the given String parameter as its value.
     *
     * <p>starting sequence number</p>
     *
     * <p>The value of start will be XML escaped. 
     * Use new HtmlAttributes().add("start",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param start the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes start(String start) {
        return new HtmlAttributes().add("start", start, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onMousedown</em> attribute and the given String parameter as its value.
     *
     * <p>a pointer button was pressed down</p>
     *
     * <p>The value of onMousedown will be XML escaped. 
     * Use new HtmlAttributes().add("onMousedown",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onMousedown the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onMousedown(String onMousedown) {
        return new HtmlAttributes().addScript("onmousedown", onMousedown, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>language</em> attribute and the given String parameter as its value.
     *
     * <p>predefined script language name</p>
     *
     * <p>The value of language will be XML escaped. 
     * Use new HtmlAttributes().add("language",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param language the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes language(String language) {
        return new HtmlAttributes().add("language", language, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>nohref</em> attribute and the given String parameter as its value.
     *
     * <p>this region has no action</p>
     *
     * <p>The value of nohref will be XML escaped. 
     * Use new HtmlAttributes().add("nohref",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param nohref the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes nohref(String nohref) {
        return new HtmlAttributes().add("nohref", nohref, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>vlink</em> attribute and the given String parameter as its value.
     *
     * <p>color of visited links</p>
     *
     * <p>The value of vlink will be XML escaped. 
     * Use new HtmlAttributes().add("vlink",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param vlink the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes vlink(String vlink) {
        return new HtmlAttributes().add("vlink", vlink, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>face</em> attribute and the given String parameter as its value.
     *
     * <p>comma separated list of font names</p>
     *
     * <p>The value of face will be XML escaped. 
     * Use new HtmlAttributes().add("face",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param face the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes face(String face) {
        return new HtmlAttributes().add("face", face, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>rev</em> attribute and the given String parameter as its value.
     *
     * <p>reverse link types</p>
     *
     * <p>The value of rev will be XML escaped. 
     * Use new HtmlAttributes().add("rev",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param rev the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes rev(String rev) {
        return new HtmlAttributes().add("rev", rev, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>hspace</em> attribute and the given String parameter as its value.
     *
     * <p>horizontal gutter</p>
     *
     * <p>The value of hspace will be XML escaped. 
     * Use new HtmlAttributes().add("hspace",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param hspace the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes hspace(String hspace) {
        return new HtmlAttributes().add("hspace", hspace, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>link</em> attribute and the given String parameter as its value.
     *
     * <p>color of links</p>
     *
     * <p>The value of link will be XML escaped. 
     * Use new HtmlAttributes().add("link",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param link the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes link(String link) {
        return new HtmlAttributes().add("link", link, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onunload</em> attribute and the given String parameter as its value.
     *
     * <p>the document has been removed</p>
     *
     * <p>The value of onUnload will be XML escaped. 
     * Use new HtmlAttributes().add("onUnload",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onUnload the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onUnload(String onUnload) {
        return new HtmlAttributes().addScript("onunload", onUnload, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>data</em> attribute and the given String parameter as its value.
     *
     * <p>reference to object's data</p>
     *
     * <p>The value of data will be XML escaped. 
     * Use new HtmlAttributes().add("data",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param data the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes data(String data) {
        return new HtmlAttributes().add("data", data, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the generic <em>data</em> attribute and the given String parameter as its value.
     *
     * <p>The value of data will be XML escaped
     * Use new HtmlAttributes().add("data-" + extension,<i>data</i>,false) if the value does not have characters that need escaping.</p>
     * 
     * @param extension 
     * @param data the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5      
     */
    public static HtmlAttributes data(String extension, String data) {
        return new HtmlAttributes().add("data-" + extension, data, ESCAPE_CHARS);
    }    
    /**
     * Return a new HtmlAttributes with the <em>marginwidth</em> attribute and the given String parameter as its value.
     *
     * <p>margin widths in pixels</p>
     *
     * <p>The value of marginwidth will be XML escaped. 
     * Use new HtmlAttributes().add("marginwidth",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param marginwidth the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes marginwidth(String marginwidth) {
        return new HtmlAttributes().add("marginwidth", marginwidth, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>accesskey</em> attribute and the given String parameter as its value.
     *
     * <p>accessibility key character</p>
     *
     * <p>The value of accesskey will be XML escaped. 
     * Use new HtmlAttributes().add("accesskey",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param accesskey the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes accesskey(String accesskey) {
        return new HtmlAttributes().add("accesskey", accesskey, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>version</em> attribute and the given String parameter as its value.
     *
     * <p>Constant</p>
     *
     * <p>The value of version will be XML escaped. 
     * Use new HtmlAttributes().add("version",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param version the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes version(String version) {
        return new HtmlAttributes().add("version", version, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>http_equiv</em> attribute and the given String parameter as its value.
     *
     * <p>HTTP response header name</p>
     *
     * <p>The value of http_equiv will be XML escaped. 
     * Use new HtmlAttributes().add("http_equiv",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param http_equiv the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes http_equiv(String http_equiv) {
        return new HtmlAttributes().add("http-equiv", http_equiv, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>clear</em> attribute and the given String parameter as its value.
     *
     * <p>control of text flow</p>
     *
     * <p>The value of clear will be XML escaped. 
     * Use new HtmlAttributes().add("clear",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param clear the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes clear(String clear) {
        return new HtmlAttributes().add("clear", clear, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>valuetype</em> attribute and the given String parameter as its value.
     *
     * <p>How to interpret value</p>
     *
     * <p>The value of valuetype will be XML escaped. 
     * Use new HtmlAttributes().add("valuetype",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param valuetype the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes valuetype(String valuetype) {
        return new HtmlAttributes().add("valuetype", valuetype, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>defer</em> attribute and the given String parameter as its value.
     *
     * <p>UA may defer execution of script</p>
     *
     * <p>The value of defer will be XML escaped. 
     * Use new HtmlAttributes().add("defer",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param defer the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes defer(String defer) {
        return new HtmlAttributes().add("defer", defer, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>title</em> attribute and the given String parameter as its value.
     *
     * <p>advisory title</p>
     *
     * <p>The value of title will be XML escaped. 
     * Use new HtmlAttributes().add("title",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param title the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes title(String title) {
        return new HtmlAttributes().add("title", title, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>enctype</em> attribute and the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of enctype will be XML escaped. 
     * Use new HtmlAttributes().add("enctype",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param enctype the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes enctype(String enctype) {
        return new HtmlAttributes().add("enctype", enctype, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>src</em> attribute and the given String parameter as its value.
     *
     * <p>URI of image to embed</p>
     *
     * <p>The value of src will be XML escaped. 
     * Use new HtmlAttributes().add("src",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param src the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes src(String src) {
        return new HtmlAttributes().add("src", src, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>datetime</em> attribute and the given String parameter as its value.
     *
     * <p>date and time of change</p>
     *
     * <p>The value of datetime will be XML escaped. 
     * Use new HtmlAttributes().add("datetime",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param datetime the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes datetime(String datetime) {
        return new HtmlAttributes().add("datetime", datetime, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>codetype</em> attribute and the given String parameter as its value.
     *
     * <p>content type for code</p>
     *
     * <p>The value of codetype will be XML escaped. 
     * Use new HtmlAttributes().add("codetype",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param codetype the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes codetype(String codetype) {
        return new HtmlAttributes().add("codetype", codetype, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>charoff</em> attribute and the given String parameter as its value.
     *
     * <p>offset for alignment char</p>
     *
     * <p>The value of charoff will be XML escaped. 
     * Use new HtmlAttributes().add("charoff",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param charoff the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes charoff(String charoff) {
        return new HtmlAttributes().add("charoff", charoff, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onkeydown</em> attribute and the given String parameter as its value.
     *
     * <p>a key was pressed down</p>
     *
     * <p>The value of onkeydown will be XML escaped. 
     * Use new HtmlAttributes().add("onkeydown",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onKeydown the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onKeydown(String onKeydown) {
        return new HtmlAttributes().addScript("onkeydown", onKeydown, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onkeypress</em> attribute and the given String parameter as its value.
     *
     * <p>a key was pressed and released</p>
     *
     * <p>The value of onkeypress will be XML escaped. 
     * Use new HtmlAttributes().add("onkeypress",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onKeypress the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onKeypress(String onKeypress) {
        return new HtmlAttributes().addScript("onkeypress", onKeypress, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onsubmit</em> attribute and the given String parameter as its value.
     *
     * <p>the form was submitted</p>
     *
     * <p>The value of onsubmit will be XML escaped. 
     * Use new HtmlAttributes().add("onsubmit",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onSubmit the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onSubmit(String onSubmit) {
        return new HtmlAttributes().addScript("onsubmit", onSubmit, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>alink</em> attribute and the given String parameter as its value.
     *
     * <p>color of selected links</p>
     *
     * <p>The value of alink will be XML escaped. 
     * Use new HtmlAttributes().add("alink",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param alink the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes alink(String alink) {
        return new HtmlAttributes().add("alink", alink, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>background</em> attribute and the given String parameter as its value.
     *
     * <p>texture tile for document background</p>
     *
     * <p>The value of background will be XML escaped. 
     * Use new HtmlAttributes().add("background",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param background the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes background(String background) {
        return new HtmlAttributes().add("background", background, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>method</em> attribute and the given String parameter as its value.
     *
     * <p>HTTP method used to submit the form</p>
     *
     * <p>The value of method will be XML escaped. 
     * Use new HtmlAttributes().add("method",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param method the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes method(String method) {
        return new HtmlAttributes().add("method", method, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>archive</em> attribute and the given String parameter as its value.
     *
     * <p>comma separated archive list</p>
     *
     * <p>The value of archive will be XML escaped. 
     * Use new HtmlAttributes().add("archive",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param archive the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes archive(String archive) {
        return new HtmlAttributes().add("archive", archive, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>prompt</em> attribute and the given String parameter as its value.
     *
     * <p>prompt message</p>
     *
     * <p>The value of prompt will be XML escaped. 
     * Use new HtmlAttributes().add("prompt",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param prompt the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes prompt(String prompt) {
        return new HtmlAttributes().add("prompt", prompt, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>rel</em> attribute and the given String parameter as its value.
     *
     * <p>forward link types</p>
     *
     * <p>The value of rel will be XML escaped. 
     * Use new HtmlAttributes().add("rel",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param rel the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes rel(String rel) {
        return new HtmlAttributes().add("rel", rel, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>checked</em> attribute and the given String parameter as its value.
     *
     * <p>for radio buttons and check boxes</p>
     *
     * <p>The value of checked will be XML escaped. 
     * Use new HtmlAttributes().add("checked",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param checked the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes checked(String checked) {
        return new HtmlAttributes().add("checked", checked, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>readonly</em> attribute and the given String parameter as its value.
     *
     * <p>for text and passwd</p>
     *
     * <p>The value of readonly will be XML escaped. 
     * Use new HtmlAttributes().add("readonly",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param readonly the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes readonly(String readonly) {
        return new HtmlAttributes().add("readonly", readonly, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>headers</em> attribute and the given String parameter as its value.
     *
     * <p>list of id's for header cells</p>
     *
     * <p>The value of headers will be XML escaped. 
     * Use new HtmlAttributes().add("headers",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param headers the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes headers(String headers) {
        return new HtmlAttributes().add("headers", headers, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>cols</em> attribute and the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of cols will be XML escaped. 
     * Use new HtmlAttributes().add("cols",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param cols the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes cols(String cols) {
        return new HtmlAttributes().add("cols", cols, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>char</em> attribute and the given String parameter as its value.
     *
     * <p>alignment char, e.g. char=':'</p>
     *
     * <p>The value of char will be XML escaped. 
     * Use new HtmlAttributes().add("char",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param char_ the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes char_(String char_) {
        return new HtmlAttributes().add("char", char_, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>cellpadding</em> attribute and the given String parameter as its value.
     *
     * <p>spacing within cells</p>
     *
     * <p>The value of cellpadding will be XML escaped. 
     * Use new HtmlAttributes().add("cellpadding",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param cellpadding the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes cellpadding(String cellpadding) {
        return new HtmlAttributes().add("cellpadding", cellpadding, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>type</em> attribute and the given String parameter as its value.
     *
     * <p>for use as form button</p>
     *
     * <p>The value of type will be XML escaped. 
     * Use new HtmlAttributes().add("type",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param type the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes type(String type) {
        return new HtmlAttributes().add("type", type, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>cellspacing</em> attribute and the given String parameter as its value.
     *
     * <p>spacing between cells</p>
     *
     * <p>The value of cellspacing will be XML escaped. 
     * Use new HtmlAttributes().add("cellspacing",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param cellspacing the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes cellspacing(String cellspacing) {
        return new HtmlAttributes().add("cellspacing", cellspacing, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>hreflang</em> attribute and the given String parameter as its value.
     *
     * <p>language code</p>
     *
     * <p>The value of hreflang will be XML escaped. 
     * Use new HtmlAttributes().add("hreflang",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param hreflang the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes hreflang(String hreflang) {
        return new HtmlAttributes().add("hreflang", hreflang, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>frameborder</em> attribute and the given String parameter as its value.
     *
     * <p>request frame borders?</p>
     *
     * <p>The value of frameborder will be XML escaped. 
     * Use new HtmlAttributes().add("frameborder",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param frameborder the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes frameborder(String frameborder) {
        return new HtmlAttributes().add("frameborder", frameborder, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>compact</em> attribute and the given String parameter as its value.
     *
     * <p>reduced interitem spacing</p>
     *
     * <p>The value of compact will be XML escaped. 
     * Use new HtmlAttributes().add("compact",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param compact the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes compact(String compact) {
        return new HtmlAttributes().add("compact", compact, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>height</em> attribute and the given String parameter as its value.
     *
     * <p>height for cell</p>
     *
     * <p>The value of height will be XML escaped. 
     * Use new HtmlAttributes().add("height",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param height the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes height(String height) {
        return new HtmlAttributes().add("height", height, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>maxlength</em> attribute and the given String parameter as its value.
     *
     * <p>max chars for text fields</p>
     *
     * <p>The value of maxlength will be XML escaped. 
     * Use new HtmlAttributes().add("maxlength",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param maxlength the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes maxlength(String maxlength) {
        return new HtmlAttributes().add("maxlength", maxlength, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onblur</em> attribute and the given String parameter as its value.
     *
     * <p>the element lost the focus</p>
     *
     * <p>The value of onblur will be XML escaped. 
     * Use new HtmlAttributes().add("onblur",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onBlur the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onBlur(String onBlur) {
        return new HtmlAttributes().addScript("onblur", onBlur, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>value</em> attribute and the given String parameter as its value.
     *
     * <p>reset sequence number</p>
     *
     * <p>The value of value will be XML escaped. 
     * Use new HtmlAttributes().add("value",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param value the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes value(String value) {
        return new HtmlAttributes().add("value", value, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>action</em> attribute and the given String parameter as its value.
     *
     * <p>server-side form handler</p>
     *
     * <p>The value of action will be XML escaped. 
     * Use new HtmlAttributes().add("action",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param action the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes action(String action) {
        return new HtmlAttributes().add("action", action, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>text</em> attribute and the given String parameter as its value.
     *
     * <p>document text color</p>
     *
     * <p>The value of text will be XML escaped. 
     * Use new HtmlAttributes().add("text",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param text the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes text(String text) {
        return new HtmlAttributes().add("text", text, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>colspan</em> attribute and the given String parameter as its value.
     *
     * <p>number of cols spanned by cell</p>
     *
     * <p>The value of colspan will be XML escaped. 
     * Use new HtmlAttributes().add("colspan",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param colspan the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes colspan(String colspan) {
        return new HtmlAttributes().add("colspan", colspan, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onmouseout</em> attribute and the given String parameter as its value.
     *
     * <p>a pointer was moved away</p>
     *
     * <p>The value of onmouseout will be XML escaped. 
     * Use new HtmlAttributes().add("onmouseout",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onMouseout the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onMouseout(String onMouseout) {
        return new HtmlAttributes().addScript("onmouseout", onMouseout, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>width</em> attribute and the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of width will be XML escaped. 
     * Use new HtmlAttributes().add("width",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param width the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes width(String width) {
        return new HtmlAttributes().add("width", width, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>align</em> attribute and the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of align will be XML escaped. 
     * Use new HtmlAttributes().add("align",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param align the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes align(String align) {
        return new HtmlAttributes().add("align", align, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>abbr</em> attribute and the given String parameter as its value.
     *
     * <p>abbreviation for header cell</p>
     *
     * <p>The value of abbr will be XML escaped. 
     * Use new HtmlAttributes().add("abbr",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param abbr the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes abbr(String abbr) {
        return new HtmlAttributes().add("abbr", abbr, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>class</em> attribute and the given String parameter as its value.
     *
     * <p>space separated list of classes</p>
     *
     * <p>The value of class will be XML escaped. 
     * Use new HtmlAttributes().add("class",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param class_ the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes class_(String class_) {
        return new HtmlAttributes().add("class", class_, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onkeyup</em> attribute and the given String parameter as its value.
     *
     * <p>a key was released</p>
     *
     * <p>The value of onkeyup will be XML escaped. 
     * Use new HtmlAttributes().add("onkeyup",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onKeyup the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onKeyup(String onKeyup) {
        return new HtmlAttributes().addScript("onkeyup", onKeyup, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>label</em> attribute and the given String parameter as its value.
     *
     * <p>for use in hierarchical menus</p>
     *
     * <p>The value of label will be XML escaped. 
     * Use new HtmlAttributes().add("label",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param label the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes label(String label) {
        return new HtmlAttributes().add("label", label, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onfocus</em> attribute and the given String parameter as its value.
     *
     * <p>the element got the focus</p>
     *
     * <p>The value of onfocus will be XML escaped. 
     * Use new HtmlAttributes().add("onfocus",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onFocus the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onFocus(String onFocus) {
        return new HtmlAttributes().addScript("onfocus", onFocus, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>shape</em> attribute and the given String parameter as its value.
     *
     * <p>for use with client-side image maps</p>
     *
     * <p>The value of shape will be XML escaped. 
     * Use new HtmlAttributes().add("shape",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param shape the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes shape(String shape) {
        return new HtmlAttributes().add("shape", shape, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>code</em> attribute and the given String parameter as its value.
     *
     * <p>applet class file</p>
     *
     * <p>The value of code will be XML escaped. 
     * Use new HtmlAttributes().add("code",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param code the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes code(String code) {
        return new HtmlAttributes().add("code", code, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>rowspan</em> attribute and the given String parameter as its value.
     *
     * <p>number of rows spanned by cell</p>
     *
     * <p>The value of rowspan will be XML escaped. 
     * Use new HtmlAttributes().add("rowspan",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param rowspan the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes rowspan(String rowspan) {
        return new HtmlAttributes().add("rowspan", rowspan, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>noresize</em> attribute and the given String parameter as its value.
     *
     * <p>allow users to resize frames?</p>
     *
     * <p>The value of noresize will be XML escaped. 
     * Use new HtmlAttributes().add("noresize",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param noresize the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes noresize(String noresize) {
        return new HtmlAttributes().add("noresize", noresize, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>size</em> attribute and the given String parameter as its value.
     *
     * <p>rows visible</p>
     *
     * <p>The value of size will be XML escaped. 
     * Use new HtmlAttributes().add("size",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param size the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes size(String size) {
        return new HtmlAttributes().add("size", size, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onreset</em> attribute and the given String parameter as its value.
     *
     * <p>the form was reset</p>
     *
     * <p>The value of onreset will be XML escaped. 
     * Use new HtmlAttributes().add("onreset",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onReset the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onReset(String onReset) {
        return new HtmlAttributes().addScript("onreset", onReset, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>rows</em> attribute and the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of rows will be XML escaped. 
     * Use new HtmlAttributes().add("rows",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param rows the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes rows(String rows) {
        return new HtmlAttributes().add("rows", rows, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>frame</em> attribute and the given String parameter as its value.
     *
     * <p>which parts of frame to render</p>
     *
     * <p>The value of frame will be XML escaped. 
     * Use new HtmlAttributes().add("frame",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param frame the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes frame(String frame) {
        return new HtmlAttributes().add("frame", frame, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onselect</em> attribute and the given String parameter as its value.
     *
     * <p>some text was selected</p>
     *
     * <p>The value of onselect will be XML escaped. 
     * Use new HtmlAttributes().add("onselect",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onSelect the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onSelect(String onSelect) {
        return new HtmlAttributes().addScript("onselect", onSelect, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>scrolling</em> attribute and the given String parameter as its value.
     *
     * <p>scrollbar or none</p>
     *
     * <p>The value of scrolling will be XML escaped. 
     * Use new HtmlAttributes().add("scrolling",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param scrolling the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes scrolling(String scrolling) {
        return new HtmlAttributes().add("scrolling", scrolling, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>media</em> attribute and the given String parameter as its value.
     *
     * <p>for rendering on these media</p>
     *
     * <p>The value of media will be XML escaped. 
     * Use new HtmlAttributes().add("media",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param media the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes media(String media) {
        return new HtmlAttributes().add("media", media, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>span</em> attribute and the given String parameter as its value.
     *
     * <p>default number of columns in group</p>
     *
     * <p>The value of span will be XML escaped. 
     * Use new HtmlAttributes().add("span",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param span the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes span(String span) {
        return new HtmlAttributes().add("span", span, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>scope</em> attribute and the given String parameter as its value.
     *
     * <p>scope covered by header cells</p>
     *
     * <p>The value of scope will be XML escaped. 
     * Use new HtmlAttributes().add("scope",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param scope the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes scope(String scope) {
        return new HtmlAttributes().add("scope", scope, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>usemap</em> attribute and the given String parameter as its value.
     *
     * <p>use client-side image map</p>
     *
     * <p>The value of usemap will be XML escaped. 
     * Use new HtmlAttributes().add("usemap",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param usemap the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes usemap(String usemap) {
        return new HtmlAttributes().add("usemap", usemap, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>object</em> attribute and the given String parameter as its value.
     *
     * <p>serialized applet file</p>
     *
     * <p>The value of object will be XML escaped. 
     * Use new HtmlAttributes().add("object",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param object the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes object(String object) {
        return new HtmlAttributes().add("object", object, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>lang</em> attribute and the given String parameter as its value.
     *
     * <p>language code</p>
     *
     * <p>The value of lang will be XML escaped. 
     * Use new HtmlAttributes().add("lang",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param lang the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes lang(String lang) {
        return new HtmlAttributes().add("lang", lang, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>id</em> attribute and the given String parameter as its value.
     *
     * <p>document-wide unique id</p>
     *
     * <p>The value of id will be XML escaped. 
     * Use new HtmlAttributes().add("id",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param id the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes id(String id) {
        return new HtmlAttributes().add("id", id, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>selected</em> attribute and the given String parameter as its value.
     *
     * <p></p>
     *
     * <p>The value of selected will be XML escaped. 
     * Use new HtmlAttributes().add("selected",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param selected the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes selected(String selected) {
        return new HtmlAttributes().add("selected", selected, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ismap</em> attribute and the given String parameter as its value.
     *
     * <p>use server-side image map</p>
     *
     * <p>The value of ismap will be XML escaped. 
     * Use new HtmlAttributes().add("ismap",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ismap the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes ismap(String ismap) {
        return new HtmlAttributes().add("ismap", ismap, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>style</em> attribute and the given String parameter as its value.
     *
     * <p>associated style info</p>
     *
     * <p>The value of style will be XML escaped. 
     * Use new HtmlAttributes().add("style",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param style the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes style(String style) {
        return new HtmlAttributes().add("style", style, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>dir</em> attribute and the given String parameter as its value.
     *
     * <p>directionality</p>
     *
     * <p>The value of dir will be XML escaped. 
     * Use new HtmlAttributes().add("dir",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param dir the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes dir(String dir) {
        return new HtmlAttributes().add("dir", dir, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>alt</em> attribute and the given String parameter as its value.
     *
     * <p>short description</p>
     *
     * <p>The value of alt will be XML escaped. 
     * Use new HtmlAttributes().add("alt",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param alt the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes alt(String alt) {
        return new HtmlAttributes().add("alt", alt, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>name</em> attribute and the given String parameter as its value.
     *
     * <p>metainformation name</p>
     *
     * <p>The value of name will be XML escaped. 
     * Use new HtmlAttributes().add("name",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param name the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes name(String name) {
        return new HtmlAttributes().add("name", name, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onmouseup</em> attribute and the given String parameter as its value.
     *
     * <p>a pointer button was released</p>
     *
     * <p>The value of onmouseup will be XML escaped. 
     * Use new HtmlAttributes().add("onmouseup",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onMouseup the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onMouseup(String onMouseup) {
        return new HtmlAttributes().addScript("onmouseup", onMouseup, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>nowrap</em> attribute and the given String parameter as its value.
     *
     * <p>suppress word wrap</p>
     *
     * <p>The value of nowrap will be XML escaped. 
     * Use new HtmlAttributes().add("nowrap",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param nowrap the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes nowrap(String nowrap) {
        return new HtmlAttributes().add("nowrap", nowrap, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>multiple</em> attribute and the given String parameter as its value.
     *
     * <p>default is single selection</p>
     *
     * <p>The value of multiple will be XML escaped. 
     * Use new HtmlAttributes().add("multiple",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param multiple the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes multiple(String multiple) {
        return new HtmlAttributes().add("multiple", multiple, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>classid</em> attribute and the given String parameter as its value.
     *
     * <p>identifies an implementation</p>
     *
     * <p>The value of classid will be XML escaped. 
     * Use new HtmlAttributes().add("classid",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param classid the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes classid(String classid) {
        return new HtmlAttributes().add("classid", classid, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>profile</em> attribute and the given String parameter as its value.
     *
     * <p>named dictionary of meta info</p>
     *
     * <p>The value of profile will be XML escaped. 
     * Use new HtmlAttributes().add("profile",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param profile the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes profile(String profile) {
        return new HtmlAttributes().add("profile", profile, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>axis</em> attribute and the given String parameter as its value.
     *
     * <p>names groups of related headers</p>
     *
     * <p>The value of axis will be XML escaped. 
     * Use new HtmlAttributes().add("axis",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param axis the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes axis(String axis) {
        return new HtmlAttributes().add("axis", axis, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onmousemove</em> attribute and the given String parameter as its value.
     *
     * <p>a pointer was moved within</p>
     *
     * <p>The value of onmousemove will be XML escaped. 
     * Use new HtmlAttributes().add("onmousemove",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onMousemove the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onMousemove(String onMousemove) {
        return new HtmlAttributes().addScript("onmousemove", onMousemove, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>tabindex</em> attribute and the given String parameter as its value.
     *
     * <p>position in tabbing order</p>
     *
     * <p>The value of tabindex will be XML escaped. 
     * Use new HtmlAttributes().add("tabindex",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param tabindex the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes tabindex(String tabindex) {
        return new HtmlAttributes().add("tabindex", tabindex, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onchange</em> attribute and the given String parameter as its value.
     *
     * <p>the element value was changed</p>
     *
     * <p>The value of onchange will be XML escaped. 
     * Use new HtmlAttributes().add("onchange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onChange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onChange(String onChange) {
        return new HtmlAttributes().addScript("onchange", onChange, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>rules</em> attribute and the given String parameter as its value.
     *
     * <p>rulings between rows and cols</p>
     *
     * <p>The value of rules will be XML escaped. 
     * Use new HtmlAttributes().add("rules",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param rules the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes rules(String rules) {
        return new HtmlAttributes().add("rules", rules, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onmouseover</em> attribute and the given String parameter as its value.
     *
     * <p>a pointer was moved onto</p>
     *
     * <p>The value of onmouseover will be XML escaped. 
     * Use new HtmlAttributes().add("onmouseover",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onMouseover the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onMouseover(String onMouseover) {
        return new HtmlAttributes().addScript("onmouseover", onMouseover, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>coords</em> attribute and the given String parameter as its value.
     *
     * <p>for use with client-side image maps</p>
     *
     * <p>The value of coords will be XML escaped. 
     * Use new HtmlAttributes().add("coords",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param coords the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes coords(String coords) {
        return new HtmlAttributes().add("coords", coords, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>color</em> attribute and the given String parameter as its value.
     *
     * <p>text color</p>
     *
     * <p>The value of color will be XML escaped. 
     * Use new HtmlAttributes().add("color",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param color the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes color(String color) {
        return new HtmlAttributes().add("color", color, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onload</em> attribute and the given String parameter as its value.
     *
     * <p>the document has been loaded</p>
     *
     * <p>The value of onload will be XML escaped. 
     * Use new HtmlAttributes().add("onload",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onLoad the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onLoad(String onLoad) {
        return new HtmlAttributes().addScript("onload", onLoad, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>target</em> attribute and the given String parameter as its value.
     *
     * <p>render in this frame</p>
     *
     * <p>The value of target will be XML escaped. 
     * Use new HtmlAttributes().add("target",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param target the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes target(String target) {
        return new HtmlAttributes().add("target", target, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onclick</em> attribute and the given String parameter as its value.
     *
     * <p>a pointer button was clicked</p>
     *
     * <p>The value of onclick will be XML escaped. 
     * Use new HtmlAttributes().add("onclick",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onClick the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes onClick(String onClick) {
        return new HtmlAttributes().addScript("onclick", onClick, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>valign</em> attribute and the given String parameter as its value.
     *
     * <p>vertical alignment in cells</p>
     *
     * <p>The value of valign will be XML escaped. 
     * Use new HtmlAttributes().add("valign",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param valign the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes valign(String valign) {
        return new HtmlAttributes().add("valign", valign, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>disabled</em> attribute and the given String parameter as its value.
     *
     * <p>unavailable in this context</p>
     *
     * <p>The value of disabled will be XML escaped. 
     * Use new HtmlAttributes().add("disabled",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param disabled the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes disabled(String disabled) {
        return new HtmlAttributes().add("disabled", disabled, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>codebase</em> attribute and the given String parameter as its value.
     *
     * <p>optional base URI for applet</p>
     *
     * <p>The value of codebase will be XML escaped. 
     * Use new HtmlAttributes().add("codebase",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param codebase the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML4.01     
     */
    public static HtmlAttributes codebase(String codebase) {
        return new HtmlAttributes().add("codebase", codebase, ESCAPE_CHARS);
    }


    ///////////////////////////////////////////////////////////////////////////////////
    //
    //  Methods below are generated using the Html5AttributesGenerator. DO NOT EDIT
    //   
    ///////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Return a new HtmlAttributes with the <em>oncontextmenu</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a context menu is triggered</p>
     *
     * <p>The value of oncontextmenu will be XML escaped. 
     * Use new HtmlAttributes().add("oncontextmenu",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param oncontextmenu the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onContextmenu(String oncontextmenu) {
        return new HtmlAttributes().addScript("oncontextmenu", oncontextmenu, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onformchange</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a form changes</p>
     *
     * <p>The value of onformchange will be XML escaped. 
     * Use new HtmlAttributes().add("onformchange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onformchange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onFormchange(String onformchange) {
        return new HtmlAttributes().addScript("onformchange", onformchange, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onforminput</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a form gets user input</p>
     *
     * <p>The value of onforminput will be XML escaped. 
     * Use new HtmlAttributes().add("onforminput",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onforminput the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onForminput(String onforminput) {
        return new HtmlAttributes().addScript("onforminput", onforminput, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>oninput</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an element gets user input</p>
     *
     * <p>The value of oninput will be XML escaped. 
     * Use new HtmlAttributes().add("oninput",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param oninput the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onInput(String oninput) {
        return new HtmlAttributes().addScript("oninput", oninput, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>oninvalid</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an element is invalid</p>
     *
     * <p>The value of oninvalid will be XML escaped. 
     * Use new HtmlAttributes().add("oninvalid",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param oninvalid the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onInvalid(String oninvalid) {
        return new HtmlAttributes().addScript("oninvalid", oninvalid, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>oncanplay</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media can start play, but might has to 
stop for buffering</p>
     *
     * <p>The value of oncanplay will be XML escaped. 
     * Use new HtmlAttributes().add("oncanplay",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param oncanplay the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onCanplay(String oncanplay) {
        return new HtmlAttributes().addScript("oncanplay", oncanplay, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>oncanplaythrough</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media can be played to the end, without 
stopping for buffering</p>
     *
     * <p>The value of oncanplaythrough will be XML escaped. 
     * Use new HtmlAttributes().add("oncanplaythrough",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param oncanplaythrough the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onCanplaythrough(String oncanplaythrough) {
        return new HtmlAttributes().addScript("oncanplaythrough", oncanplaythrough, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondurationchange</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the length of the media is changed</p>
     *
     * <p>The value of ondurationchange will be XML escaped. 
     * Use new HtmlAttributes().add("ondurationchange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondurationchange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDurationchange(String ondurationchange) {
        return new HtmlAttributes().addScript("ondurationchange", ondurationchange, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onemptied</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a media resource element suddenly becomes 
empty (network errors, errors on load etc.)</p>
     *
     * <p>The value of onemptied will be XML escaped. 
     * Use new HtmlAttributes().add("onemptied",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onemptied the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onEmptied(String onemptied) {
        return new HtmlAttributes().addScript("onemptied", onemptied, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onended</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media has reach the end</p>
     *
     * <p>The value of onended will be XML escaped. 
     * Use new HtmlAttributes().add("onended",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onended the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onEnded(String onended) {
        return new HtmlAttributes().addScript("onended", onended, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onloadeddata</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media data is loaded</p>
     *
     * <p>The value of onloadeddata will be XML escaped. 
     * Use new HtmlAttributes().add("onloadeddata",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onloadeddata the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onLoadeddata(String onloadeddata) {
        return new HtmlAttributes().addScript("onloadeddata", onloadeddata, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onloadedmetadata</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the duration and other media data of a 
media element is loaded</p>
     *
     * <p>The value of onloadedmetadata will be XML escaped. 
     * Use new HtmlAttributes().add("onloadedmetadata",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onloadedmetadata the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onLoadedmetadata(String onloadedmetadata) {
        return new HtmlAttributes().addScript("onloadedmetadata", onloadedmetadata, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onloadstart</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the browser starts to load the media data</p>
     *
     * <p>The value of onloadstart will be XML escaped. 
     * Use new HtmlAttributes().add("onloadstart",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onloadstart the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onLoadstart(String onloadstart) {
        return new HtmlAttributes().addScript("onloadstart", onloadstart, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onpause</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media data is paused</p>
     *
     * <p>The value of onpause will be XML escaped. 
     * Use new HtmlAttributes().add("onpause",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onpause the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onPause(String onpause) {
        return new HtmlAttributes().addScript("onpause", onpause, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onplay</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media data is going to start playing</p>
     *
     * <p>The value of onplay will be XML escaped. 
     * Use new HtmlAttributes().add("onplay",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onplay the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onPlay(String onplay) {
        return new HtmlAttributes().addScript("onplay", onplay, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onplaying</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media data has start playing</p>
     *
     * <p>The value of onplaying will be XML escaped. 
     * Use new HtmlAttributes().add("onplaying",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onplaying the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onPlaying(String onplaying) {
        return new HtmlAttributes().addScript("onplaying", onplaying, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onprogress</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the browser is fetching the media data</p>
     *
     * <p>The value of onprogress will be XML escaped. 
     * Use new HtmlAttributes().add("onprogress",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onprogress the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onProgress(String onprogress) {
        return new HtmlAttributes().addScript("onprogress", onprogress, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onratechange</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the media data's playing rate has changed</p>
     *
     * <p>The value of onratechange will be XML escaped. 
     * Use new HtmlAttributes().add("onratechange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onratechange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onRatechange(String onratechange) {
        return new HtmlAttributes().addScript("onratechange", onratechange, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onreadystatechange</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the ready-state changes</p>
     *
     * <p>The value of onreadystatechange will be XML escaped. 
     * Use new HtmlAttributes().add("onreadystatechange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onreadystatechange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onReadystatechange(String onreadystatechange) {
        return new HtmlAttributes().addScript("onreadystatechange", onreadystatechange, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onseeked</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a media element's seeking attribute is no 
longer true, and the seeking has ended</p>
     *
     * <p>The value of onseeked will be XML escaped. 
     * Use new HtmlAttributes().add("onseeked",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onseeked the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onSeeked(String onseeked) {
        return new HtmlAttributes().addScript("onseeked", onseeked, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onseeking</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a media element's seeking attribute is 
true, and the seeking has begun</p>
     *
     * <p>The value of onseeking will be XML escaped. 
     * Use new HtmlAttributes().add("onseeking",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onseeking the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onSeeking(String onseeking) {
        return new HtmlAttributes().addScript("onseeking", onseeking, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onstalled</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when there is an error in fetching media data 
(stalled)</p>
     *
     * <p>The value of onstalled will be XML escaped. 
     * Use new HtmlAttributes().add("onstalled",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onstalled the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onStalled(String onstalled) {
        return new HtmlAttributes().addScript("onstalled", onstalled, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onsuspend</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the browser has been fetching media data, 
but stopped before the entire media file was fetched</p>
     *
     * <p>The value of onsuspend will be XML escaped. 
     * Use new HtmlAttributes().add("onsuspend",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onsuspend the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onSuspend(String onsuspend) {
        return new HtmlAttributes().addScript("onsuspend", onsuspend, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ontimeupdate</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media changes its playing position</p>
     *
     * <p>The value of ontimeupdate will be XML escaped. 
     * Use new HtmlAttributes().add("ontimeupdate",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ontimeupdate the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onTimeupdate(String ontimeupdate) {
        return new HtmlAttributes().addScript("ontimeupdate", ontimeupdate, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onvolumechange</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media changes the volume, also when 
volume is set to "mute"</p>
     *
     * <p>The value of onvolumechange will be XML escaped. 
     * Use new HtmlAttributes().add("onvolumechange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onvolumechange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onVolumechange(String onvolumechange) {
        return new HtmlAttributes().addScript("onvolumechange", onvolumechange, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onwaiting</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when media has stopped playing, but is 
expected to resume</p>
     *
     * <p>The value of onwaiting will be XML escaped. 
     * Use new HtmlAttributes().add("onwaiting",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onwaiting the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onWaiting(String onwaiting) {
        return new HtmlAttributes().addScript("onwaiting", onwaiting, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondrag</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an element is dragged</p>
     *
     * <p>The value of ondrag will be XML escaped. 
     * Use new HtmlAttributes().add("ondrag",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondrag the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDrag(String ondrag) {
        return new HtmlAttributes().addScript("ondrag", ondrag, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondragend</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run at the end of a drag operation</p>
     *
     * <p>The value of ondragend will be XML escaped. 
     * Use new HtmlAttributes().add("ondragend",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondragend the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDragend(String ondragend) {
        return new HtmlAttributes().addScript("ondragend", ondragend, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondragenter</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an element has been dragged to a valid 
drop target</p>
     *
     * <p>The value of ondragenter will be XML escaped. 
     * Use new HtmlAttributes().add("ondragenter",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondragenter the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDragenter(String ondragenter) {
        return new HtmlAttributes().addScript("ondragenter", ondragenter, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondragleave</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an element leaves a valid drop target</p>
     *
     * <p>The value of ondragleave will be XML escaped. 
     * Use new HtmlAttributes().add("ondragleave",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondragleave the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDragleave(String ondragleave) {
        return new HtmlAttributes().addScript("ondragleave", ondragleave, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondragover</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an element is being dragged over a 
valid drop target</p>
     *
     * <p>The value of ondragover will be XML escaped. 
     * Use new HtmlAttributes().add("ondragover",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondragover the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDragover(String ondragover) {
        return new HtmlAttributes().addScript("ondragover", ondragover, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondragstart</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run at the start of a drag operation</p>
     *
     * <p>The value of ondragstart will be XML escaped. 
     * Use new HtmlAttributes().add("ondragstart",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondragstart the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDragstart(String ondragstart) {
        return new HtmlAttributes().addScript("ondragstart", ondragstart, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ondrop</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when dragged element is being dropped</p>
     *
     * <p>The value of ondrop will be XML escaped. 
     * Use new HtmlAttributes().add("ondrop",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ondrop the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onDrop(String ondrop) {
        return new HtmlAttributes().addScript("ondrop", ondrop, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onmousewheel</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the mouse wheel is being rotated</p>
     *
     * <p>The value of onmousewheel will be XML escaped. 
     * Use new HtmlAttributes().add("onmousewheel",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onmousewheel the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onMousewheel(String onmousewheel) {
        return new HtmlAttributes().addScript("onmousewheel", onmousewheel, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onscroll</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an element's scrollbar is being scrolled</p>
     *
     * <p>The value of onscroll will be XML escaped. 
     * Use new HtmlAttributes().add("onscroll",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onscroll the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onScroll(String onscroll) {
        return new HtmlAttributes().addScript("onscroll", onscroll, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>contenteditable</em> attribute and the given String parameter as its value.
     *
     * <p>Specifies if the user is allowed to edit the content or not</p>
     *
     * <p>The value of contenteditable will be XML escaped. 
     * Use new HtmlAttributes().add("contenteditable",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param contenteditable the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes contenteditable(String contenteditable) {
        return new HtmlAttributes().add("contenteditable", contenteditable, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>contextmenu</em> attribute and the given String parameter as its value.
     *
     * <p>Specifies the context menu for an element</p>
     *
     * <p>The value of contextmenu will be XML escaped. 
     * Use new HtmlAttributes().add("contextmenu",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param contextmenu the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes contextmenu(String contextmenu) {
        return new HtmlAttributes().add("contextmenu", contextmenu, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>draggable</em> attribute and the given String parameter as its value.
     *
     * <p>Specifies whether or not a user is allowed to drag an element</p>
     *
     * <p>The value of draggable will be XML escaped. 
     * Use new HtmlAttributes().add("draggable",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param draggable the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes draggable(String draggable) {
        return new HtmlAttributes().add("draggable", draggable, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>dropzone</em> attribute and the given String parameter as its value.
     *
     * <p>Specifies what happens when dragged items/data is dropped in 
the element</p>
     *
     * <p>The value of dropzone will be XML escaped. 
     * Use new HtmlAttributes().add("dropzone",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param copymovelink the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes dropzone(String copymovelink) {
        return new HtmlAttributes().add("dropzone", copymovelink, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>hidden</em> attribute and the given String parameter as its value.
     *
     * <p>Specifies that the element is not relevant. Hidden elements are 
not displayed</p>
     *
     * <p>The value of hidden will be XML escaped. 
     * Use new HtmlAttributes().add("hidden",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param hidden the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes hidden(String hidden) {
        return new HtmlAttributes().add("hidden", hidden, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>spellcheck</em> attribute and the given String parameter as its value.
     *
     * <p>Specifies if the element must have its spelling and grammar 
checked</p>
     *
     * <p>The value of spellcheck will be XML escaped. 
     * Use new HtmlAttributes().add("spellcheck",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param spellcheck the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes spellcheck(String spellcheck) {
        return new HtmlAttributes().add("spellcheck", spellcheck, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onafterprint</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run after the document is printed</p>
     *
     * <p>The value of onafterprint will be XML escaped. 
     * Use new HtmlAttributes().add("onafterprint",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onafterprint the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onAfterprint(String onafterprint) {
        return new HtmlAttributes().addScript("onafterprint", onafterprint, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onbeforeprint</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run before the document is printed</p>
     *
     * <p>The value of onbeforeprint will be XML escaped. 
     * Use new HtmlAttributes().add("onbeforeprint",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onbeforeprint the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onBeforeprint(String onbeforeprint) {
        return new HtmlAttributes().addScript("onbeforeprint", onbeforeprint, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onbeforeonload</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run before the document loads</p>
     *
     * <p>The value of onbeforeonload will be XML escaped. 
     * Use new HtmlAttributes().add("onbeforeonload",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onbeforeonload the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onBeforeonload(String onbeforeonload) {
        return new HtmlAttributes().addScript("onbeforeonload", onbeforeonload, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onerror</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when an error occur</p>
     *
     * <p>The value of onerror will be XML escaped. 
     * Use new HtmlAttributes().add("onerror",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onerror the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onError(String onerror) {
        return new HtmlAttributes().addScript("onerror", onerror, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onhaschange</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the document has change</p>
     *
     * <p>The value of onhaschange will be XML escaped. 
     * Use new HtmlAttributes().add("onhaschange",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onhaschange the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onHaschange(String onhaschange) {
        return new HtmlAttributes().addScript("onhaschange", onhaschange, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onmessage</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the message is triggered</p>
     *
     * <p>The value of onmessage will be XML escaped. 
     * Use new HtmlAttributes().add("onmessage",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onmessage the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onMessage(String onmessage) {
        return new HtmlAttributes().addScript("onmessage", onmessage, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onoffline</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the document goes offline</p>
     *
     * <p>The value of onoffline will be XML escaped. 
     * Use new HtmlAttributes().add("onoffline",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onoffline the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onOffline(String onoffline) {
        return new HtmlAttributes().addScript("onoffline", onoffline, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>ononline</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the document comes online</p>
     *
     * <p>The value of ononline will be XML escaped. 
     * Use new HtmlAttributes().add("ononline",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param ononline the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onOnline(String ononline) {
        return new HtmlAttributes().addScript("ononline", ononline, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onpagehide</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the window is hidden</p>
     *
     * <p>The value of onpagehide will be XML escaped. 
     * Use new HtmlAttributes().add("onpagehide",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onpagehide the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onPagehide(String onpagehide) {
        return new HtmlAttributes().addScript("onpagehide", onpagehide, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onpageshow</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the window becomes visible </p>
     *
     * <p>The value of onpageshow will be XML escaped. 
     * Use new HtmlAttributes().add("onpageshow",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onpageshow the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onPageshow(String onpageshow) {
        return new HtmlAttributes().addScript("onpageshow", onpageshow, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onpopstate</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the window's history changes</p>
     *
     * <p>The value of onpopstate will be XML escaped. 
     * Use new HtmlAttributes().add("onpopstate",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onpopstate the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onPopstate(String onpopstate) {
        return new HtmlAttributes().addScript("onpopstate", onpopstate, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onredo</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the document performs a redo</p>
     *
     * <p>The value of onredo will be XML escaped. 
     * Use new HtmlAttributes().add("onredo",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onredo the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onRedo(String onredo) {
        return new HtmlAttributes().addScript("onredo", onredo, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onresize</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when the window is resized</p>
     *
     * <p>The value of onresize will be XML escaped. 
     * Use new HtmlAttributes().add("onresize",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onresize the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onResize(String onresize) {
        return new HtmlAttributes().addScript("onresize", onresize, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onstorage</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a document loads</p>
     *
     * <p>The value of onstorage will be XML escaped. 
     * Use new HtmlAttributes().add("onstorage",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onstorage the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onStorage(String onstorage) {
        return new HtmlAttributes().addScript("onstorage", onstorage, ESCAPE_CHARS);
    }
    /**
     * Return a new HtmlAttributes with the <em>onundo</em> attribute and the given String parameter as its value.
     *
     * <p>Script to be run when a document performs an undo</p>
     *
     * <p>The value of onundo will be XML escaped. 
     * Use new HtmlAttributes().add("onundo",<i>value</i>,false) if the value does not have characters that need escaping.</p>
     *      
     * @param onundo the String | null.
     * @return the receiver, this <code>HtmlAttributes</code> instance.
     * @since HTML5     
     */
    public static HtmlAttributes onUndo(String onundo) {
        return new HtmlAttributes().addScript("onundo", onundo, ESCAPE_CHARS);
    }
    //
    // Methods below are for convencience when using e.g. JQuery mobile 
    //
    /**
     * @param enabled
     * @return
     */
    public static HtmlAttributes dataAjax(boolean enabled) {
        return new HtmlAttributes().add("data-ajax", Boolean.toString(enabled),false);
    }    
    
    /**
     * @param role
     * @return
     */
    public static HtmlAttributes dataRole(String role) {
        return new HtmlAttributes().add("data-role", role);
    }

    /**
     * @param icon
     * @return
     */
    public static HtmlAttributes dataIcon(String icon) {
        return new HtmlAttributes().add("data-icon", icon);
    }

    /**
     * @param type
     * @return
     */
    public static HtmlAttributes dataType(String type) {
        return new HtmlAttributes().add("data-type", type);
    }

    /**
     * @param inline
     * @return
     */
    public static HtmlAttributes dataInline(boolean inline) {
        return new HtmlAttributes().add("data-inline", String.valueOf(inline));
    }

    /**
     * @param collapsed
     * @return
     */
    public static HtmlAttributes dataCollapsed(boolean collapsed) {
        return new HtmlAttributes().add("data-collapsed", String.valueOf(collapsed));
    }

    /**
     * @param theme
     * @return
     */
    public static HtmlAttributes dataTheme(String theme) {
        return new HtmlAttributes().add("data-theme", theme);
    }

    /**
     * @param inset
     * @return
     */
    public static HtmlAttributes dataInset(boolean inset) {
        return new HtmlAttributes().add("data-inset", Boolean.toString(inset));
    }

    /**
     * @param direction
     * @return
     */
    public static HtmlAttributes dataDirection(String direction) {
        return new HtmlAttributes().add("data-direction", direction, false);
    }
    
    /**
     * @param xy
     * @return
     */
    public static HtmlAttributes dataScroll(String xy) {
        return new HtmlAttributes().add("data-scroll", xy, false);
    }

    /**
     * @param rel
     * @return
     */
    public static HtmlAttributes dataRel(String rel) {
        return new HtmlAttributes().add("data-rel", rel, false);
    }

    /**
     * @param inline
     * @return
     */
    public static HtmlAttributes dataInline(String inline) {
        return new HtmlAttributes().add("data-inline", inline, false);
    }     
    /**
     * @param isMini
     * @return
     */
    public static HtmlAttributes dataMini(boolean isMini) {
        return new HtmlAttributes().add("data-mini", Boolean.toString(isMini), false);
    }        
    // fixed, inline
    /**
     * @param dataPosition
     * @return
     */
    public static HtmlAttributes dataPosition(String dataPosition) {
        return new HtmlAttributes().add("data-position", dataPosition, false);
    }    
    /**
     * @param dataContentTheme
     * @return
     */
    public static HtmlAttributes dataContentTheme(String dataContentTheme) {
        return new HtmlAttributes().add("data-content-theme", dataContentTheme, false);
    }   
}
