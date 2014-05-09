package org.rendersnake.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A nested hash-based <code>Map</code> implementation.
 * If an entry is not found in the map at nesting level <em>N</em>
 * then retry in map <em>N</em>-1, if N &gt;= 0;
 * return <code>null</code> otherwise.
 *
 * @author ernestmicklei
 */
public class StackedMap implements Map<String, Object> {

    public static int MAX_STACK_DEPTH = 16; // tuneable parameters
    public static int INITIAL_MAP_CAPACITY = 7;
    private HashMap<String,Object>[] stack;
    /**
     * The current depth. Initially 0 (after construction).
     */
    private int depth = -1;    
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
        this.depth = -1;
        this.stack = new HashMap[MAX_STACK_DEPTH];
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
        this.depth++;
        Map<String,Object> existing = this.stack[depth];
        if (existing == null) {
            this.stack[depth] = new HashMap<String,Object>(INITIAL_MAP_CAPACITY);
        } else {
           existing.clear(); 
        }
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
        if (this.depth == 0)
            throw new IllegalStateException("getDepth() == 0");
        this.depth--;
    }
    /**
     * Returns the depth of the stack.
     *
     * @return
     *    the depth of the stack, always &gt;= 0.
     */
    public int getDepth() {
        return this.depth;
    }
    /**
     * Retrieves the map at the top of the stack.
     *
     * @return
     *    the {@link HashMap} at the top of the stack ; can be <code>null</code>
     */
    private HashMap<String, Object> top() {
        return this.stack[depth];
    }
    
    //
    // Map API
    //
    public void clear() {
        for(HashMap<String,Object> each : stack) {
            if (each != null) each.clear();
        }
    }

    public boolean containsKey(Object key) {
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            if (here != null && here.containsKey(key))
                return true;
            else
                level--;
        }
        return false;
    }

    public boolean containsValue(Object value) {
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            if (here.containsValue(value))
                return true;
            else
                level--;
        }
        return false;
    }

    public Set<java.util.Map.Entry<String, Object>> entrySet() {
        Set<java.util.Map.Entry<String, Object>> union = new HashSet<java.util.Map.Entry<String, Object>>();
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            union.addAll(here.entrySet());
            level--;
        }
        return union;
    }

    public boolean isEmpty() {
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            if (!here.isEmpty())
                return false;
            else
                level--;
        }
        return true;
    }
    public Set<String> keySet() {
        Set<String> union = new HashSet<String>();
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            union.addAll(here.keySet());
            level--;
        }
        return union;
    }

    public Object get(Object key) {
        if (key == null)
           throw new IllegalArgumentException("key == null");

        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            if (here != null && here.containsKey(key))
                return here.get(key);
            else
                level--;
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
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            final Object value = here.remove(key);
            if (objectToReturn == null)
                objectToReturn = value; // may still be null
            level--;
        }
        return objectToReturn;
    }

    public int size() {
        int total = 0;
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            total += here.size();
            level--;
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
        int level = depth;
        while (level != -1) {
            final HashMap<String, Object> here = stack[level];
            hash = hash | here.hashCode();
            level--;
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
        StackedMap otherStackedMap = (StackedMap) otherMap;
        int level = depth;
        while (level != -1) {
            if (!stack[level].equals(otherStackedMap.stack[level]))
                return false;
            level--;
        }
        return true;
    }
    
    /**
     *  for debugging
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append('[');
        Object[] keys = this.keySet().toArray();
        Arrays.sort(keys);
        for (Object each : keys) {
            sb  .append(each)
                .append('=')
                .append(this.get(each))
                .append('\n');
        }
        sb.append(']');
        return sb.toString();
    }
}
