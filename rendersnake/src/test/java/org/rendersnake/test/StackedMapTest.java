 package org.rendersnake.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.rendersnake.internal.StackedMap;

public class StackedMapTest extends TestCase {

    private StackedMap map;
    private Map<String,Object> source;

    public void setUp() {
        this.map = new StackedMap();

        this.source = new HashMap<String,Object>();
        this.source.put("foo",  "bar");
        this.source.put("john", "doe");
    }

    public void testPutAllNull() {
        // Test with null key
        try {
           this.map.putAll(null);
           fail("Expected StackedHashMap.putAll(\"this\",null) to throw an IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
           // as expected
        }
    }
    
    public void testBasic() {
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        this.map.put("key", "value");
        assertEquals("value", map.get("key"));
        assertFalse(map.isEmpty());
    }

    public void testIsEmpty() {
       assertTrue(map.isEmpty());
       this.map.push();
       assertTrue(map.isEmpty());
       this.map.push();
       assertTrue(map.isEmpty());

       this.map.put("foo", "bar1");
       assertFalse(map.isEmpty());
       this.map.push();
       assertFalse(map.isEmpty());
       this.map.put("foo", "bar2");
       assertFalse(map.isEmpty());
       this.map.pop();
       this.map.pop();
       assertTrue(map.isEmpty());
    }

    public void testCopyConstructor() {

        StackedMap map2 = new StackedMap(source);
        assertFalse(map2.isEmpty());
        assertEquals(2, map2.size());
        assertEquals("bar", map2.get("foo"));
        assertEquals("bar", map2.get("foo")); // make sure a <get> is not a <remove>
        assertEquals("doe", map2.get("john"));
    }

    public void testHashCode() {
        long emptyHashCode = this.map.hashCode();
        assertEquals("Expected consistent hash code for empty StackedHashMap.", emptyHashCode, new StackedMap().hashCode());

        this.map.put("foo", "bar");
        assertFalse("Expected hash code to change when adding a mapping.", map.hashCode() == emptyHashCode);

        this.map.clear();
        assertEquals("Expected consistent hash code for empty StackedHashMap.", emptyHashCode, map.hashCode());
    }

    public void testEquals() {
        map.putAll(this.source);
        StackedMap map2 = new StackedMap(this.source);
        assertEquals("Expected StackedMap(source) consistent hashCode", map.hashCode(), map.hashCode());
        assertTrue("Expected StackedMap(source) to create equals() instances (1/2).", map.equals(map2));
        assertTrue("Expected StackedMap(source) to create equals() instances (2/2).", map2.equals(map));
        assertFalse("Map is not a String", map.equals("othermap"));
    }

    public void testLevel0FromLevel1() {
        map.put("key0", "value");
        map.push();
        assertEquals("value", map.get("key0"));
    }

    public void testLevel1FromLevel1() {
        map.put("key0", "value0");
        map.push();
        map.put("key1", "value1");
        assertEquals(2,map.size());
        assertEquals("value0", map.get("key0"));
        assertEquals("value1", map.get("key1"));
        assertTrue(map.containsValue("value1"));
        assertNull(map.get("key3"));
    }

    public void testPop() {
        try {
            map.pop();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException ex) {
            // as expected
        }

        assertEquals(0, map.size());
        map.put("key", "val1");
        map.push();
        map.put("foo", "bar");
        map.put("key", "val2");
        assertEquals(3, map.size());
        assertEquals("val2", map.get("key"));
        map.pop();
        assertEquals(1, map.size());
        assertEquals("val1", map.get("key"));
    }

    public void testToString() {
        map.put("key0", "value0");
        assertNotNull(map.toString());
    }

    public void testClear() {
        this.map.put("key0", "value0");
        this.map.push();
        this.map.put("key1", "value1");
        this.map.push();

        this.map.clear();
        assertEquals(0,    this.map.size());
        assertEquals(null, this.map.get("key0"));
        assertEquals(null, this.map.get("key1"));
    }

    public void testGet() {

       // Test with null key
       try {
          this.map.get(null);
          fail("Expected StackedHashMap.get(null) to throw an IllegalArgumentException.");
       } catch (IllegalArgumentException e) {
          // as expected
       }

       // Test with non-null values
       this.map.put("a", null);
       assertNull(this.map.get("a"));
       this.map.put("a", "0");
       assertEquals("0", this.map.get("a"));
       this.map.push();
       assertEquals("0", this.map.get("a"));
       this.map.put("a", "1");
       assertEquals("1", this.map.get("a"));
       this.map.push();
       this.map.put("a", null);
       assertNull(this.map.get("a"));
       this.map.pop();
       this.map.pop();
       assertEquals("0", this.map.get("a"));
    }

    public void testPut() {

        // Test with non-null key and non-null value
        assertEquals(null, this.map.get("foo"));
        this.map.put("foo", "bar");
        assertEquals("bar", this.map.get("foo"));

        // Test with null key and non-null value
        try {
            this.map.put((String) null, "bar");
            fail("Expected map.put(null) to throw an IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // as expected
        }

        // Test with non-null key and null value
        this.map.put("key", null);
        assertEquals(null, this.map.get("key"));

        // Override an existing value with a null
        this.map.push();
        this.map.put("foo", null);
        assertEquals(null, this.map.get("foo"));
        this.map.pop();
        assertEquals("bar", this.map.get("foo"));
    }

    public void testPutAll() {
        map.put("key0", "value0");
        StackedMap map2 = new StackedMap();
        map2.put("key1","value1");
        map.putAll(map2);
        assertTrue(map.containsKey("key0"));
        assertTrue(map.containsKey("key1"));
    }

    public void testContainsValue() {
        assertFalse(map.containsValue("value0"));
        map.put("key0", "value0");
        assertTrue(map.containsValue("value0"));
    }

    public void testContainsKey() {
        assertFalse(map.containsKey("key0"));
        map.put("key0", "value0");
        assertTrue(map.containsKey("key0"));

        map.remove("key0");
        assertFalse(map.containsKey("key0"));
    }

    public void testRemove() {

        // Test with empty map
        assertNull(map.remove("key0"));

        // Test with single level
        map.put("key0", "value0");
        assertEquals("value0", map.remove("key0"));
        assertFalse(map.containsKey("key0"));

        // Test with multiple levels
        map.put("key0", "value0");
        map.push();
        map.put("key0", "BLABLA");
        map.put("key1", "value1");
        assertEquals("value1", map.remove("key1"));
        assertTrue(map.containsKey("key0"));
        assertFalse(map.containsKey("key1"));

        // Remove one mapping that has an overridden value
        map.push();
        assertEquals("BLABLA", map.remove("key0"));
        assertFalse(map.containsKey("key0"));
        assertFalse(map.containsKey("key1"));
        assertTrue(map.isEmpty());
    }

    public void testPopNotEmpty() {
        map.put("k", "v");
        map.push();
        map.pop();
        assertFalse(map.isEmpty());
    }

    public void testKeySet() {

        // Test with empty map
        Set<String> keys = map.keySet();
        assertEquals(0, keys.size());

        // Non-empty map
        map.put("key0", "value0");
        map.push();
        map.put("key1", "value1");
        keys = map.keySet();
        assertEquals(2, keys.size());
        assertTrue(keys.contains("key0"));
        assertTrue(keys.contains("key1"));

        // Override should work OK
        map.push();
        map.put("key1", "override");
        keys = map.keySet();
        assertEquals(2, keys.size());
        assertTrue(keys.contains("key0"));
        assertTrue(keys.contains("key1"));
    }

    public void testValues() {
        assertEquals(0, this.map.values().size());
        this.map.push();
        assertEquals(0, this.map.values().size());
        this.map.push();

        // Test with one mapping
        this.map.put("foo", "bar");
        Collection<Object> values = this.map.values();
        assertEquals(1, values.size());
        assertTrue(values.contains("bar"));

        // Test with two mappings
        this.map.put("foo", "bar2");
        this.map.push();
        this.map.put("key", "val");
        values = this.map.values();
        assertEquals(2, values.size());
        assertTrue(values.contains("bar2"));
        assertTrue(values.contains("val"));
    }

    public void testEntrySet() {
        assertEquals(0, this.map.entrySet().size());
        this.map.push();
        assertEquals(0, this.map.entrySet().size());
        this.map.push();

        // Test with one mapping
        this.map.put("foo", "bar");
        Set<Map.Entry<String, Object>> en3s = this.map.entrySet();
        assertEquals(1, en3s.size());
        Map.Entry<String, Object> en3 = en3s.iterator().next();
        assertEquals("foo", en3.getKey());
        assertEquals("bar", en3.getValue());

        // Test with two mappings
        this.map.put("foo", "bar2");
        this.map.push();
        this.map.put("key", "val");
        en3s = this.map.entrySet();
        assertEquals(2, en3s.size());
    }
    
    public void testOldData(){
        map.put("one",1);
        map.push();
        map.put("two", 2);
        map.pop();
        map.push();
        assertFalse(map.containsKey("two"));
    }
}
