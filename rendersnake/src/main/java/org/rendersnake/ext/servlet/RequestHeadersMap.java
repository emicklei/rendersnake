package org.rendersnake.ext.servlet;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.rendersnake.internal.ContextMap;

public class RequestHeadersMap implements ContextMap {

    private HttpServletRequest request;
    
    public RequestHeadersMap(HttpServletRequest request) {
        this.request = request;
    }    
    
    public ContextMap withBoolean(String key, Boolean trueOrFalse) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Boolean getBoolean(String key, Boolean... optional) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Long getLong(String key, Long... optional) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public ContextMap withLong(String key, Long aLong) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Float getFloat(String key, Float... optional) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public ContextMap withFloat(String key, Float aFloat) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Integer getInteger(String key, Integer... optional) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public ContextMap withInteger(String key, Integer anInteger) {
        throw new UnsupportedOperationException("map is read-only");
    }

    public Object getObject(String key, Object... optional) {
        throw new UnsupportedOperationException("map is read-only");
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

    public Map<Object,Object> toMap() {
        HashMap<Object,Object> map = new HashMap<Object,Object>();
        Enumeration<String> enumy = request.getHeaderNames();
        while (enumy.hasMoreElements()) {
            String key = enumy.nextElement();
            map.put(key, request.getHeader(key));
        }
        return map;
    }    
}
