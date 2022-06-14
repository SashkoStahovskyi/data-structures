package com.stahovskyi.datastructures.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private Map<String, String> hashMap;
    private Iterator<HashMap.Entry<String, String>> iterator;

    @BeforeEach
    public void before() {
        hashMap = new HashMap<>();
        iterator = hashMap.iterator();
    }

    @DisplayName("test Put Add New Value And Change Size Work Correctly")
    @Test
    public void testPutAddNewValueAndChangeSizeWorkCorrectly() {
        assertEquals(0, hashMap.size());
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.get("key1"));
        assertEquals("value2", hashMap.get("key2"));
    }

    @DisplayName("test Put Replace Exist Value On New Value")
    @Test
    public void testPutReplaceExistValueOnNewValue() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "oldValue");

        assertEquals("oldValue", hashMap.put("key2", "newValue"));
        assertEquals("newValue", hashMap.get("key2"));
    }

    @DisplayName("tes Get Returns The Value To Which The Specified Key Is Mapped")
    @Test
    public void tesGetReturnsTheValueToWhichTheSpecifiedKeyIsMapped() {
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

    @DisplayName("test Remove Removes Mapping For A Key")
    @Test
    public void testRemoveRemovesMappingForAKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.remove("key1"));
        assertEquals("value2", hashMap.remove("key2"));
        assertEquals(0, hashMap.size());
        assertNull(hashMap.get("key1"));
        assertNull(hashMap.get("key2"));
    }

    @DisplayName("test Remove Return Null When Key Not Exist")
    @Test
    public void testRemoveReturnNullWhenKeyNotExist() {
        hashMap.put("key1", "value1");

        assertNull(hashMap.remove("keyNotExist"));
    }

    @DisplayName("test Contains Return True When Key Exist")
    @Test
    public void testContainsReturnTrueWhenKeyExist() {
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

    // Iterator Test

    @DisplayName("test Iterator Has Next Return True When Next Element Exist")
    @Test
    public void testIteratorHasNextReturnTrueWhenNextElementExist() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertTrue(iterator.hasNext());
    }


    @DisplayName("test Iterator Has Next Return False When Next Element Not Exist")
    @Test
    public void testIteratorHasNextReturnFalseWhenNextElementNotExist() {
        assertFalse(iterator.hasNext());
    }

    @DisplayName("test Iterator Next Return Next Element")
    @Test
    public void testIteratorNextReturnNextElement() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        Iterator<HashMap.Entry<String, String>> iterator = hashMap.iterator();

        HashMap.Entry<String, String> actual = iterator.next();
        assertEquals("value1",actual.getValue());

    }

    @DisplayName("test Iterator Next Throw No Such Element When Map Is Empty")
    @Test
    public void testIteratorNextThrowNoSuchElementExceptionWhenMapEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @DisplayName("test Iterator Remove Removes The Last Element Returned By This Iterator")
    @Test
    public void testIteratorRemoveRemovesTheLastElementReturnedByThisIterator() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(0, hashMap.size());
    }

    @DisplayName("test Iterator Remove Throw IllegalStateException When Method Has Already Been Called After The Last Call")
    @Test
    public void testIteratorRemoveThrowIllegalStateExceptionWhenMethodHasAlreadyBeenCalledAfterTheLastCall() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        iterator.next();
        iterator.remove();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @DisplayName("test Iterator Remove Throw IllegalStateException If The Next Method Has Not Yet Been Called")
    @Test
    public void testIteratorRemoveThrowIllegalStateExceptionIfTheNextMethodHasNotYetBeenCalled() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }
}






