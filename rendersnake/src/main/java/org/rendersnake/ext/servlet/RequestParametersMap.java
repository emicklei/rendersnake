package org.rendersnake.ext.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.rendersnake.internal.ContextMap;

public class RequestParametersMap implements ContextMap {

    private HttpServletRequest request;
    
    public RequestParametersMap(HttpServletRequest request) {
        this.request = request;
    }
    
    public ContextMap withBoolean(String key, Boolean trueOrFalse) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Boolean getBoolean(String key, Boolean... optional) {
        String s = request.getParameter(key);
        return s == null ? ((optional == null || optional.length == 0) ? null : optional[0]) : Boolean.parseBoolean(s);
    }

    public Long getLong(String key, Long... optional) {
        String s = request.getParameter(key);
        return s == null ? ((optional == null || optional.length == 0) ? null : optional[0]) : Long.parseLong(s);
    }

    public ContextMap withLong(String key, Long aLong) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Float getFloat(String key, Float... optional) {
        String s = request.getParameter(key);
        return s == null ? ((optional == null || optional.length == 0) ? null : optional[0]) : Float.parseFloat(s);
    }

    public ContextMap withFloat(String key, Float aFloat) {
        throw new UnsupportedOperationException("map is read-only");
   }

    public Integer getInteger(String key, Integer... optional) {
        String s = request.getParameter(key);
        return s == null ? ((optional == null || optional.length == 0) ? null : optional[0]) : Integer.parseInt(s);
    }

    public ContextMap withInteger(String key, Integer anInteger) {
        throw new UnsupportedOperationException("map is read-only");
    }
    /**
     * Return the paramter values for a key, an array of String
     */
    public Object getObject(String key, Object... optional) {
        String[] s = request.getParameterValues(key);
        return s == null ? ((optional == null || optional.length == 0) ? null : optional) : s;
    }

    public ContextMap withObject(String key, Object value) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public String getString(String key, String... optional) {
        String s = request.getParameter(key);
        return s == null ? ((optional == null || optional.length == 0) ? null : optional[0]) : s;
    }

    public ContextMap withString(String key, String value) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Object clear(String key) {
        throw new UnsupportedOperationException("map is read-only");
    }
    
    @SuppressWarnings("unchecked")
    public Map<Object, Object> toMap() {
        return request.getParameterMap();
    }    
}
