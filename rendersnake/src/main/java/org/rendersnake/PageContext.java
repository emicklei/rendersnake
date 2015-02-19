package org.rendersnake;

import static org.rendersnake.HtmlAttributesFactory.border;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.rendersnake.internal.ContextMap;
import org.rendersnake.internal.StackedMap;
import org.rendersnake.tools.Inspector;
/**
 * PageContext provides the interface to page-render scoped variables.
 * During rendering, each component can set or get values from a PageContext.
 * Values set by a component are only visible to the component and all its child components.
 * 
 * @author ernestmicklei
 *
 */
public class PageContext implements ContextMap {
    /**
     * Reserved keys for accessing data provided by the HttpRequest.
     */
    public static final String REQUEST_PATH         = "http.request.path";
    public static final String REQUEST_URIQ         = "http.request.uri.query";
    public static final String REQUEST_PARAMETERS   = "http.request.parameters";
    public static final String SESSION              = "http.session";
    public static final String REQUEST_HEADERS      = "http.request.headers";
    public static final String REQUEST_COOKIES      = "http.request.cookies";
    
    /**
     * Storage of the values for each component nesting level
     */
    public StackedMap attributes = new StackedMap();
    
    
    /**
     * Answer a ContextAttributesAccess by a key
     * @param key 
     * @return 
     */
    public ContextMap getContextMap(String key) {
        return (ContextMap)attributes.get(key);
    }
    
    /**
     * Store a value accessed by a key
     * @param key , not null
     * @param value , any object
     * @return this
     */
    public PageContext withObject(String key, Object value) {
        attributes.put(key, value);
        return this;
    }
    /**
     * Answer the value stored by a key
     * @param key , not null
     * @return Object | null | optional[0]
     */
    public Object getObject(String key, Object ... optional) {
        Object value = (Object) attributes.get(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return value;
        }
    }
    /**
     * Store a value accessed by a key
     * @param key , not null
     * @param value , any String
     * @return this
     */
    public PageContext withString(String key, String value) {
        attributes.put(key, value);
        return this;
    }    
    /**
     * Answer the String value stored by a key
     * @param key , not null
     * @return Object | null | optional[0]
     */
    public String getString(String key, String ... optional) {
        String value = (String) attributes.get(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return value;
        }
    }
    /**
     * @param key
     * @param number
     * @return this
     */
    public PageContext withInteger(String key,Integer number) {
        attributes.put(key, number);
        return this;
    }
    public Integer getInteger(String key, Integer ... optional) {
        Object value = attributes.get(key);
        if (value == null)
            return (optional == null || optional.length == 0) ? null : optional[0];
        if (value instanceof Integer)
            return (Integer)value;
        if (value instanceof String && !((String) value).isEmpty())
            return Integer.parseInt((String)value);
        return null;
    }
    /**
     * @param key
     * @param trueOrFalse
     */
    public PageContext withBoolean(String key,Boolean trueOrFalse) {
        attributes.put(key, trueOrFalse);
        return this;
    }
    public Boolean getBoolean(String key, Boolean ... optional) {
        Object value = attributes.get(key);
        if (value == null)
            return (optional == null || optional.length == 0) ? null : optional[0];
        if (value instanceof Boolean)
            return (Boolean)value;
        if (value instanceof String && !((String) value).isEmpty())
            return Boolean.parseBoolean((String)value);
        return null;
    }
    /**
     * @param key
     * @param aFloat
     * @return this
     */
    public PageContext withFloat(String key,Float aFloat) {
        attributes.put(key, aFloat);
        return this;
    }    
    public Float getFloat(String key, Float ... optional) {
        Object value = attributes.get(key);
        if (value == null)
        	return (optional == null || optional.length == 0) ? null : optional[0];
        if (value instanceof Float)
            return (Float)value;
        if (value instanceof String && !((String) value).isEmpty())
            return Float.parseFloat((String)value);
        return null;
    }    
    /**
     * @param key
     * @param aLong
     * @return this
     */
    public PageContext withLong(String key,Long aLong) {
        attributes.put(key, aLong);
        return this;
    }
    public Long getLong(String key, Long ... optional) {
        Object value = attributes.get(key);
        if (value == null)
            return (optional == null || optional.length == 0) ? null : optional[0];
        if (value instanceof Long)
            return (Long)value;
        if (value instanceof String && !((String) value).isEmpty())
            return Long.parseLong((String)value);
        return null;
    }        
    /**
     * The HtmlCanvas is about to render a new component.
     * This means values are stored on the next nesting level.
     */
    protected void beginRender() {
        attributes.push();
    }
    /**
     * The HtmlCanvas is finished rendering a component.
     * This means values are the current nesting level are unavailable.
     */
    protected void endRender() {
        attributes.pop();
    }
    /**
     * Render inspection information using the (debug) html
     * @param inspector 
     * @param html
     * @throws IOException
     */
    public void renderForInpectorOn(Inspector inspector,HtmlCanvas html) throws IOException {
        html.write("{d=" + attributes.getDepth() + "}");
        /**
        html
            .pre()
            .text(attributes.toString())
            ._pre();
        **/
    }
    /**
     * Render error reporting information using the (debug) html
     * @param html
     * @throws IOException
     */    
    public void renderForErrorOn(HtmlCanvas html) throws IOException {
        html.h3().write(this.getClass().getName())._h3();
        html.table(border("1px").cellpadding("4px"));
        
        for (String key : attributes.keySet()) {
            Object value = attributes.get(key);            
            html
                .tr()
                    .td().write(key)._td()
                    .td().write(value.toString())._td()
                ._tr();
        }        
        html._table();
    }   
    @Override
    public String toString() {
        return attributes.toString();
    }
	public Object clear(String key) {
		return attributes.remove(key);		
	}
    public Map<Object,Object> toMap() {
        HashMap<Object,Object> map = new HashMap<Object,Object>();
        for (String each : this.attributes.keySet()) {
            map.put(each, this.attributes.get(each));
        }
        return map;
    }
}