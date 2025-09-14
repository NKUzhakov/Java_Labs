package tests;

import customDataStructures.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MyCacheTest {
    @Test
    public void normalDataManipulationTest(){
        MyCache cache = new MyCache(5);
        assertTrue(cache.add("key1", "value1"));
        cache.add("key2", "value2");
        cache.add("key3", "value1");
        assertFalse(cache.add("key1", "value1"));
        assertFalse(cache.add("key1", "valueN"));

        Object[] pairs1 = cache.toArray();
        Object[] expectedPairs1 = {new MyCache.KeyValuePair("key1", "value1"), 
                                    new MyCache.KeyValuePair("key2", "value2"),
                                    new MyCache.KeyValuePair("key3", "value1")};
        assertArrayEquals(pairs1, expectedPairs1);
        assertEquals(cache.size(), 3);
        assertEquals(cache.get("key1"), "value1");
        assertEquals(cache.get("key999"), null);

        cache.add("key4", "value4");
        cache.add("key5", "value5");
        cache.add("key6", "value6");
        Object[] pairs2 = cache.toArray();
        Object[] expectedPairs2 = {new MyCache.KeyValuePair("key3", "value1"),
                                    new MyCache.KeyValuePair("key1", "value1"),
                                    new MyCache.KeyValuePair("key4", "value4"), 
                                    new MyCache.KeyValuePair("key5", "value5"),
                                    new MyCache.KeyValuePair("key6", "value6")};
        assertArrayEquals(pairs2, expectedPairs2);
        assertEquals(cache.size(), 5);
        assertEquals(cache.get("key2"), null);
        assertEquals(cache.get("key1"), "value1");
        assertEquals(cache.get("key6"), "value6");

        assertTrue(cache.set("key4", "newValue4"));
        Object[] pairs3 = cache.toArray();
        Object[] expectedPairs3 = {new MyCache.KeyValuePair("key3", "value1"),                              
                                    new MyCache.KeyValuePair("key5", "value5"),                                    
                                    new MyCache.KeyValuePair("key1", "value1"),
                                    new MyCache.KeyValuePair("key6", "value6"),
                                    new MyCache.KeyValuePair("key4", "newValue4")};
        assertArrayEquals(pairs3, expectedPairs3);
        assertFalse(cache.set("NotExistingKey", "magicValue++"));

        assertTrue(cache.remove("key3"));
        cache.remove("key1");
        cache.remove("key4");
        assertFalse(cache.remove("NotExistingKey"));
        Object[] pairs4 = cache.toArray();
        Object[] expectedPairs4 = {new MyCache.KeyValuePair("key5", "value5"), 
                                    new MyCache.KeyValuePair("key6", "value6")};
        assertArrayEquals(pairs4, expectedPairs4);
        assertEquals(cache.size(), 2);
    }
    @Test
    public void zeroSizedCacheDataManipulationTest(){
        MyCache cache = new MyCache(0);
        assertFalse(cache.add("key1", "value1"));
        assertTrue(cache.get("key1") == null);
        assertFalse(cache.set("key1", "valueN"));
        assertFalse(cache.remove("key1"));
        assertEquals(cache.size(), 0);
        Object[] result = cache.toArray();
        Object[] emptyArray = {};
        assertArrayEquals(result, emptyArray);
    }
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void wrongCreation(){
        MyCache cache = new MyCache(-1);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void nullKeyAdding(){
        MyCache cache = new MyCache(5);
        cache.add(null, "value");
    }
    @org.junit.Test(expected = NullPointerException.class)
    public void nullValueAdding(){
        MyCache cache = new MyCache(5);
        cache.add("key", null);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void nullKeySetting(){
        MyCache cache = new MyCache(5);
        cache.add("key", "value");
        cache.set(null, "newValue");
    }
    @org.junit.Test(expected = NullPointerException.class)
    public void nullValueSetting(){
        MyCache cache = new MyCache(5);
        cache.add("key", "value");
        cache.set("key", null);
    }
}
