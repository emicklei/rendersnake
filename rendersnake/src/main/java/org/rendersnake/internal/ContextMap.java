package org.rendersnake.internal;

import java.util.Map;

public interface ContextMap {

    /**
     * Return the Boolean value stored a the key, or the value of optional if
     * absent.
     * 
     * @param key
     * @param optional
     * @return
     */
    Boolean getBoolean(String key, Boolean... optional);

    /**
     * Add (or overwrite) a key,Boolean pair to the Map. Return the Map.
     * 
     * @param key
     * @param value
     * @return
     */
    ContextMap withBoolean(String key, Boolean trueOrFalse);

    /**
     * Return the Float value stored a the key, or the value of optional if
     * absent.
     * 
     * @param key
     * @param optional
     * @return
     */
    Long getLong(String key, Long... optional);

    /**
     * Add (or overwrite) a key,Long pair to the Map. Return the Map.
     * 
     * @param key
     * @param value
     * @return
     */
    ContextMap withLong(String key, Long aLong);

    /**
     * Return the Float value stored a the key, or the value of optional if
     * absent.
     * 
     * @param key
     * @param optional
     * @return
     */
    Float getFloat(String key, Float... optional);

    /**
     * Add (or overwrite) a key,Float pair to the Map. Return the Map.
     * 
     * @param key
     * @param value
     * @return
     */
    ContextMap withFloat(String key, Float aFloat);

    /**
     * Return the Integer value stored a the key, or the value of optional if
     * absent.
     * 
     * @param key
     * @param optional
     * @return
     */
    Integer getInteger(String key, Integer... optional);

    /**
     * Add (or overwrite) a key,Integer pair to the Map. Return the Map.
     * 
     * @param key
     * @param value
     * @return
     */
    ContextMap withInteger(String key, Integer anInteger);

    /**
     * Return the Object value stored a the key, or the value of optional if
     * absent.
     * 
     * @param key
     * @param optional
     * @return
     */
    Object getObject(String key, Object... optional);

    /**
     * Add (or overwrite) a key,Object pair to the Map. Return the Map.
     * 
     * @param key
     * @param value
     * @return
     */
    ContextMap withObject(String key, Object value);

    /**
     * Return the String value stored a the key, or the value of optional if
     * absent.
     * 
     * @param key
     * @param optional
     * @return
     */
    String getString(String key, String... optional);

    /**
     * Add (or overwrite) a key,String pair to the Map. Return the Map.
     * 
     * @param key
     * @param value
     * @return
     */
    ContextMap withString(String key, String value);

    /**
     * Remove all entries from the Map.
     * 
     * @param key
     * @return
     */
    Object clear(String key);

    /**
     * Return a new Map containing all the context key,value pairs.
     * 
     * @return
     */
    Map<Object, Object> toMap();
}
