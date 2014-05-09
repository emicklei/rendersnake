package org.rendersnake.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class SimpleContextMap implements Serializable, ContextMap {
    private static final long serialVersionUID = -6319488346145394221L;
    private Map<String, Serializable> attributes = new HashMap<String, Serializable>();

    public SimpleContextMap() { super(); }
    
    @SuppressWarnings("unchecked")
    public SimpleContextMap(Map<?,?> attributesMap) {
        this.attributes = (Map<String, Serializable>)attributesMap;
    }    
    public Object getObject(String key, Object... optional) {
        Object value = attributes.get(key);
        if (value == null) {
            return optional.length == 0 ? null : optional[0];
        } else {
            return (Object) value;
        }
    }
    public Boolean getBoolean(String key, Boolean... optional) {
        Object value = attributes.get(key);
        if (value == null) {
            return optional.length == 0 ? null : optional[0];
        } else {
            if (value instanceof String) {
                return Boolean.parseBoolean((String)value);
            } else {
                return (Boolean) value;
            }
        }
    }
    public Float getFloat(String key, Float... optional) {
        Object value = attributes.get(key);
        if (value == null) {
            return optional.length == 0 ? null : optional[0];
        } else {
            if (value instanceof String) {
                return Float.parseFloat((String)value);
            } else {
                return (Float) value;
            }
        }
    }
    public Integer getInteger(String key, Integer... optional) {
        Object value = attributes.get(key);
        if (value == null) {
            return optional.length == 0 ? null : optional[0];
        } else {
            if (value instanceof String) {
                return Integer.parseInt((String)value);
            } else {            
                return (Integer) value;
            }
        }
    }
    public Long getLong(String key, Long... optional) {
        Object value = attributes.get(key);
        if (value == null) {
            return optional.length == 0 ? null : optional[0];
        } else {
            if (value instanceof String) {
                return Long.parseLong((String)value);
            } else {              
                return (Long) value;
            }
        }
    }
    public String getString(String key, String... optional) {
        Object value = attributes.get(key);
        if (value == null) {
            return optional.length == 0 ? null : optional[0];
        } else {
            return (String) value;
        }
    }
    public SimpleContextMap withString(String key, String value) {
        attributes.put(key, (Serializable) value);
        return this;
    }
    public SimpleContextMap withBoolean(String key, Boolean value) {
        attributes.put(key, value);
        return this;
    }
    public SimpleContextMap withLong(String key, Long value) {
        attributes.put(key, value);
        return this;
    }
    public SimpleContextMap withFloat(String key, Float value) {
        attributes.put(key, value);
        return this;
    }
    public SimpleContextMap withInteger(String key, Integer value) {
        attributes.put(key, value);
        return this;
    }
    public SimpleContextMap withObject(String key, Object value) {
        attributes.put(key, (Serializable) value);
        return this;
    }
    public Object clear(String key) {
        return attributes.remove(key);
    }  
    public Map<Object,Object> toMap() {
        HashMap<Object,Object> map = new HashMap<Object,Object>();
        for (Map.Entry<String, Serializable> each : this.attributes.entrySet()) {
            map.put(each.getKey(), each.getValue());
        }
        return map;
    }
    
    public String toString() { return this.getClass().getSimpleName() + attributes.toString(); }
}
