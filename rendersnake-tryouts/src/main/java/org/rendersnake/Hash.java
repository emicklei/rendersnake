package org.rendersnake;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.rendersnake.internal.CharactersWriteable;

/**
 * 
 * Usage:
 * <pre>new Hash("key","value","bool",true,"int,42,....)
 * 
 * toJavascript()
 *      {key:'value',bool:true,int:42}
 * 
 * toAttributesString()
 *      key="value" bool="true" int="42"      
 * </pre>
 * 
 * @author e.micklei
 */
public class Hash implements CharactersWriteable , ToJavascript {

    public Map<Object,Object> map = new HashMap<Object,Object>();

    public Hash(Object... args) {
        if (args.length % 2 == 1) {
            throw new IllegalArgumentException("Missing value");
        }
        for (int i = 0; i < args.length - 1; i = i + 2) {
            map.put(args[i], args[i + 1]);
        }
    }

    public Hash put(Object key,Object value) {
        map.put(key,value);
        return this;
    }
    
    public String toJavascript() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean separate = false;
        for (Object each : map.keySet()) {
            if (separate)
                sb.append(',');
            else
                separate = true;
            sb
                .append(each)
                .append(':');
            Object value = map.get(each);
            if (value instanceof String)
                sb.append('\'')
                  .append(StringEscapeUtils.escapeXml((String)value))
                  .append('\'');
            else
                sb.append(value);
        }
        sb.append('}');
        return sb.toString();
    }

    public String toString() {
        return this.toJavascript();
    }

    public String asCharSequence() {
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        boolean separate = false;
        for (Object each : map.keySet()) {
            if (separate)
                sb.append(' ');
            else
                separate = true;
            sb
                .append(each)
                .append('=')
                .append('"').append(StringEscapeUtils.escapeXml(map.get(each).toString())).append('"');
        }
        return sb.toString();
    }

    public void writeCharsOn(Writer writer) throws IOException {
        
        writer.append(' ');
        boolean separate = false;
        for (Object each : map.keySet()) {
            if (separate)
                writer.append(' ');
            else
                separate = true;
            writer
                .append((String)each)
                .append('=')
                .append('"').append(StringEscapeUtils.escapeXml(map.get(each).toString()))
                    .append('"');
        }               
    }
}
