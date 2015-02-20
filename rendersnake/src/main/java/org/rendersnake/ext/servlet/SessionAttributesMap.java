package org.rendersnake.ext.servlet;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.rendersnake.internal.ContextMap;

/**
 * @author emicklei
 *
 */
public class SessionAttributesMap implements ContextMap {

    private HttpServletRequest request;
    private HttpSession session;

    /**
     * @param request
     */
    public SessionAttributesMap(HttpServletRequest request) {
        super();
        this.request = request;
    }

    private HttpSession getSession(boolean createIfAbsent) {
        if (session != null)
            return session;
        session = request.getSession(createIfAbsent);
        return session;
    }

    public SessionAttributesMap withBoolean(String key, Boolean trueOrFalse) {
        getSession(true).setAttribute(key, trueOrFalse);
        return this;
    }

    public Boolean getBoolean(String key, Boolean... optional) {
        HttpSession currentSession = getSession(false);
        Object value = null;
        if (currentSession != null)
            value = currentSession.getAttribute(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return (Boolean) value;
        }
    }

    public Long getLong(String key, Long... optional) {
        HttpSession currentSession = getSession(false);
        Object value = null;
        if (currentSession != null)
            value = currentSession.getAttribute(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return (Long) value;
        }
    }

    public SessionAttributesMap withLong(String key, Long aLong) {
        getSession(true).setAttribute(key, aLong);
        return this;
    }

    public Float getFloat(String key, Float... optional) {
        HttpSession currentSession = getSession(false);
        Object value = null;
        if (currentSession != null)
            value = currentSession.getAttribute(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return (Float) value;
        }
    }

    public SessionAttributesMap withFloat(String key, Float aFloat) {
        getSession(true).setAttribute(key, aFloat);
        return this;
    }

    public SessionAttributesMap withInteger(String key, Integer anInteger) {
        getSession(true).setAttribute(key, anInteger);
        return this;
    }   

    public Integer getInteger(String key, Integer... optional) {
        HttpSession currentSession = getSession(false);
        Object value = null;
        if (currentSession != null)
            value = currentSession.getAttribute(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return (Integer) value;
        }
    }

    public Object getObject(String key, Object... optional) {
        HttpSession currentSession = getSession(false);
        Object value = null;
        if (currentSession != null)
            value = currentSession.getAttribute(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return value;
        }
    }

    public SessionAttributesMap withObject(String key, Object value) {
        getSession(true).setAttribute(key, value);
        return this;
    }

    public SessionAttributesMap withString(String key, String value) {
        getSession(true).setAttribute(key, value);
        return this;
    }

    public String getString(String key, String... optional) {
        HttpSession currentSession = getSession(false);
        Object value = null;
        if (currentSession != null)
            value = currentSession.getAttribute(key);
        if (value == null) {
            return (optional == null || optional.length == 0) ? null : optional[0];
        } else {
            return (String) value;
        }
    }

    public Object clear(String key) {
        HttpSession currentSession = getSession(false);
        if (currentSession == null)
            return null;
        Object last = currentSession.getAttribute(key);
        currentSession.removeAttribute(key);
        return last;
    }
    
    public Map<Object,Object> toMap() {
        HttpSession currentSession = getSession(false);
        if (currentSession == null)
            return null;
        HashMap<Object,Object> map = new HashMap<Object,Object>();
        @SuppressWarnings("unchecked")
        Enumeration<String> enumy = currentSession.getAttributeNames();
        while (enumy.hasMoreElements()) {
            String key = enumy.nextElement();
            map.put(key, currentSession.getAttribute(key));
        }
        return map;
    }    
}
