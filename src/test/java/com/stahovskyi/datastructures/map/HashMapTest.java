package com.stahovskyi.datastructures.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private Map<String, String> hashMap;

    @BeforeEach
    public void before() {
        hashMap = new HashMap<>();
    }

    @DisplayName("test Put New Value And Size Work Correctly")
    @Test
    public void testPutNewValueAndSizeWorkCorrectly() {
        assertEquals(0, hashMap.size());
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
    }

    @DisplayName("test Put Replace Exist Value On New Value")
    @Test
    public void testPutReplaceExistValueOnNewValue() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "oldValue");
        assertEquals(2, hashMap.size());
        assertEquals("oldValue", hashMap.put("key2", "newValue"));
    }

    @DisplayName("tes Get Work Correctly")
    @Test
    public void testGetWorkCorrectly() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        assertEquals("value2", hashMap.get("key2"));
    }

    @DisplayName("test Get Return Null When Key Not Exist")
    @Test
    public void testGetReturnNullWhenKeyNotExist() {
        assertNull(hashMap.get("key1"));
    }

    @DisplayName("test Is Empty Return True When Map IS Empty")
    @Test
    public void testIsEmptyReturnTrueWhenMapISEmpty() {
        assertTrue(hashMap.isEmpty());
    }

    @DisplayName("test Is Empty Return False On Map With Data")
    @Test
    public void testIsEmptyReturnFalseOnMapWithData() {
        hashMap.put("key1", "value1");
        assertFalse(hashMap.isEmpty());
    }

    @DisplayName("test Remove Work Correctly")
    @Test
    public void testRemoveWorkCorrectly() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("value2", hashMap.remove("key2"));
        assertEquals(1, hashMap.size());
    }

    @DisplayName("test Remove Return Null When Key Not Exist")
    @Test
    public void testRemoveReturnNullWhenKeyNotExist() {
        hashMap.put("key1", "value1");
        assertNull(hashMap.remove("keyNotExist"));
    }

    @DisplayName("test Contains Work Correctly")
    @Test
    public void testContainsWorkCorrectly() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertTrue(hashMap.containsKey("key2"));
    }

    @DisplayName("test Contains Throw False When Key Not Exist")
    @Test
    public void testContainsThrowFalseWhenKeyNotExist() {
        hashMap.put("key1", "value1");
        assertFalse(hashMap.containsKey("key2"));
    }

    @DisplayName("test Iterator Has Next Work Correctly")
    @Test
    public void testIteratorHasNextWorkCorrectly() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");
        Iterator<HashMap.Entry<String, String>> itr = hashMap.iterator();

        assertTrue(itr.hasNext());
    }

    @DisplayName("test Iterator Next And Remove Work Correctly")
    @Test
    public void testIteratorNextAndRemoveWorkCorrectly() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        Iterator<HashMap.Entry<String, String>> itr = hashMap.iterator();

        assertEquals(2, hashMap.size());
        itr.next();
        itr.remove();
        assertEquals(1, hashMap.size());
        assertFalse(hashMap.containsKey("key1"));
    }


    @DisplayName("test Iterator Has Next Return False When Next Element Not Exist")
    @Test
    public void testIteratorHasNextReturnFalseWhenNextElementNotExist() {
        Iterator<HashMap.Entry<String, String>> itr = hashMap.iterator();
        assertFalse(itr.hasNext());
    }

    @DisplayName("test Iterator Next Throw Run Time Exception When Next Element Not Exist")
    @Test
    public void testIteratorNextThrowRunTimeExceptionWhenNextElementNotExist() {
        Iterator<HashMap.Entry<String, String>> itr = hashMap.iterator();
        Assertions.assertThrows(RuntimeException.class, () -> {
            itr.next();
        });
    }
}






