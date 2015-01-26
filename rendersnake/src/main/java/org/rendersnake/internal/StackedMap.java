package org.rendersnake.internal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * A nested hash-based <code>Map</code> implementation.
 * If an entry is not found in the map at nesting level <em>N</em>
 * then retry in map <em>N</em>-1, if N &gt;= 0;
 * return <code>null</code> otherwise.
 *
 * @author ernestmicklei
 */
public class StackedMap implements Map<String, Object> {

    public static int INITIAL_MAP_CAPACITY = 7;
    private Deque<Map<String,Object>> stack;

    /**
     * Constructs an empty <code>StackedMap</code>.
     */
    public StackedMap() {
        this.init();
    } 
    /**
     * Constructs a new <code>StackedMap</code> with the same mappings as
     * the specified <code>Map</code>. The mappings will be created at depth
     * 0.
     *
     * @param m
     *    the <code>Map</code> whose mappings are to be placed in this
     *    <code>Map</code>, cannot be <code>null</code>.
     *
     * @throws IllegalArgumentException
     *    if <code>m == null</code>.
     */
    public StackedMap(Map<? extends String, ? extends Object> m) {
        this();
        this.putAll(m);
    }    
    /**
     * Initialize the receiver with an empty map.
     */
    @SuppressWarnings("unchecked")
    private void init() {
        this.stack = new ArrayDeque<Map<String,Object>>();
        this.push();
    }    
    /**
     * Increases the depth of the stack of maps. From now on all
     * {@link #put(String,Object)} operations will store the mappings at the
     * higher depth.
     *
     * <p>Note that {@link #pop()} will make all mappings at the current
     * depth unavailable.
     */
    public void push() {
        stack.addFirst(new HashMap<String,Object>(INITIAL_MAP_CAPACITY));
    }
    /**
     * Decreases the depth of the stack of maps, effectively removing all
     * mappings at the current level. From now on all
     * {@link #put(String,Object)} operations will store the mappings at the
     * lower depth.
     *
     * <p>Note that {@link #push()} will create a higher depth.
     *
     * @throws IllegalStateException
     *    if <code>getDepth() == 0</code>.
     */
    public void pop() {
        if (stack.size() == 1)
            throw new IllegalStateException("getDepth() == 0");

        stack.removeFirst();
    }
    /**
     * Returns the depth of the stack.
     *
     * @return
     *    the depth of the stack, always &gt;= 0.
     */
    public int getDepth() {
        return stack.size();
    }
    /**
     * Retrieves the map at the top of the stack.
     *
     * @return
     *    the {@link HashMap} at the top of the stack ; can be <code>null</code>
     */
    private Map<String, Object> top() {
        return this.stack.peekFirst();
    }
    
    //
    // Map API
    //
    public void clear() {
        for(Map<String,Object> each : stack) {
            each.clear();
        }
    }

    public boolean containsKey(Object key) {
        for (Map<String,Object> here : stack) {
            if (here.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (Map<String,Object> here : stack) {
            if (here.containsValue(value)) {
                return true;
            }
        }
        return false;
    }

    public Set<java.util.Map.Entry<String, Object>> entrySet() {
        Set<java.util.Map.Entry<String, Object>> union = new HashSet<java.util.Map.Entry<String, Object>>();
        for (Map<String,Object> here : stack) {
            union.addAll(here.entrySet());
        }
        return union;
    }

    public boolean isEmpty() {
        for (Map<String,Object> here : stack) {
            if (!here.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public Set<String> keySet() {
        Set<String> union = new HashSet<String>();
        for (Map<String,Object> here : stack) {
            union.addAll(here.keySet());
        }
        return union;
    }

    public Object get(Object key) {
        if (key == null)
           throw new IllegalArgumentException("key == null");

        if (!String.class.isAssignableFrom(key.getClass()))
            throw new IllegalArgumentException("key must be a string");

        String sKey = (String) key;
        for (Map<String,Object> here : stack) {
            if (here != null && here.containsKey(sKey)) {
                return here.get(sKey);
            }
        }

        return null;
    }

    public Object put(String key, Object value) {
        if (key == null)
            throw new IllegalArgumentException("key == null");
        
        return this.top().put(key, value);
    }

    public void putAll(Map<? extends String, ? extends Object> m) {
        if (m == null)
            throw new IllegalArgumentException("m == null");
        this.top().putAll(m);
    }

    // note: returns the first non-null value removed
    public Object remove(Object key) {
        Object objectToReturn = null;
        for (Map<String,Object> here : stack) {
            final Object value = here.remove(key);
            if (objectToReturn == null)
                objectToReturn = value; // may still be null
        }
        return objectToReturn;
    }

    public int size() {
        int total = 0;
        for (Map<String,Object> here : stack) {
            total += here.size();
        }
        return total;
    }

    public Collection<Object> values() {
        // might not be the most efficient implementation....
        List<Object> union = new ArrayList<Object>();
        for (String each : this.keySet()) {
            union.add(this.get(each));
        }
        return union;
    }
    // Equals API
    /**
     * @return the hash of the receiver
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (Map<String,Object> here : stack) {
            hash = hash | here.hashCode();
        }
        return hash;
    }

    /**
     * @return whether the contents of otherMap equals to that of the receiver
     */
    @Override
    public boolean equals(Object otherMap) {
        if (!(otherMap instanceof StackedMap))
            return false;

        return this.entrySet().equals(((StackedMap) otherMap).entrySet());
    }
    
    /**
     *  for debugging
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append('[');
        Set<String> keys = new TreeSet<String>(this.keySet());
        for (String each : keys) {
            sb  .append(each)
                .append('=')
                .append(this.get(each))
                .append('\n');
        }
        sb.append(']');
        return sb.toString();
    }
}
