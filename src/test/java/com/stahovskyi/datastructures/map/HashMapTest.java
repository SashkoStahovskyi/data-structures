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
    private Iterator<Map.Entry<String, String>> iterator;

    @BeforeEach
    public void before() {
        hashMap = new HashMap<>();
        iterator = hashMap.iterator();
    }

    @DisplayName("test Put Add New Entry In Bucket By Specified Key")
    @Test
    public void testPutAddNewEntryInBucketBySpecifiedKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.get("key1"));
        assertEquals("value2", hashMap.get("key2"));
    }

    @DisplayName("test Method Put Change Size When Default Buckets Size Is Almost Full And Size Must Grow")
    @Test
    public void testMethodPutChangeSizeWhenDefaultBucketsSizeIsAlmostFullAndSizeMustGrow() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");
        hashMap.put("key4", "value4");
        hashMap.put("key5", "value5");
        hashMap.put("key6", "value6");
        hashMap.put("key7", "value7");

        assertEquals(7, hashMap.size());
        assertEquals("value1", hashMap.get("key1"));
        assertEquals("value5", hashMap.get("key5"));
        assertEquals("value7", hashMap.get("key7"));
    }

    @DisplayName("test Put Replace Old Value On New Value If The Map Previously Contained A Mapping For This Key")
    @Test
    public void testPutReplaceOldValueOnNewValueIfTheMapPreviouslyContainedAMappingForThisKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "oldValue");

        assertEquals(2, hashMap.size());
        assertEquals("oldValue", hashMap.put("key2", "newValue"));
        assertEquals("newValue", hashMap.get("key2"));
    }

    @DisplayName("test Put Return Null If There Was No Mapping For Key")
    @Test
    public void testPutReturnNullIfThereWasNoMappingForKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertNull(hashMap.put("notExistKey", "value"));
    }

    @DisplayName("test Put Add New Element With Key Null")
    @Test
    public void testPutAddNewElementWithKeyNull() {
        hashMap.put(null, "valueWithKeyNull");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("valueWithKeyNull", hashMap.get(null));
    }

    @DisplayName("test Put Change Exist Element Value On New Value When Key Null")
    @Test
    public void testPutChangeExistElementValueOnNewValueWhenKeyNull() {
        hashMap.put(null, "oldValue");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("oldValue", hashMap.put(null, "newValue"));
        assertEquals(2, hashMap.size());
        assertEquals("newValue", hashMap.get(null));
    }

    @DisplayName("test Put Add New Element With Key Null And Value Null")
    @Test
    public void testPutAddNewElementWithKeyNullAndValueNull() {
        hashMap.put(null, null);
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertNull(null, hashMap.get(null));
    }

    @DisplayName("test Get Returns Value Which Corresponding To This Entry")
    @Test
    public void testGetReturnsValueWhichCorrespondingToThisEntry() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals("value2", hashMap.get("key2"));
    }

    @DisplayName("test Get Return Null When Specified Key Not Exist")
    @Test
    public void testGetReturnNullWhenSpecifiedKeyNotExist() {
        assertNull(hashMap.get("key1"));
    }

    @DisplayName("test Get Return Value Which Corresponding Key Null")
    @Test
    public void testGetReturnValueWhichCorrespondingKeyNull() {
        hashMap.put(null, "value1");
        hashMap.put("key2", "value2");

        assertEquals("value1", hashMap.get(null));
    }

    @DisplayName("test Size Returns The Number Of Entry Mappings In This Map")
    @Test
    public void testSizeReturnsTheNumberOfEntryMappingsInThisMap() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        hashMap.remove("key1");
        hashMap.remove("key2");
        assertEquals(0, hashMap.size());
    }

    @DisplayName("test IsEmpty Returns True If This Map Contains No Entry Mappings")
    @Test
    public void testReturnsIsEmptyTrueIfThisMapContainsNoEntryMappings() {
        assertTrue(hashMap.isEmpty());
    }

    @DisplayName("test IsEmpty Returns True On Map After Remove Element")
    @Test
    public void testIsEmptyReturnsTrueOnMapAfterRemoveElement() {
        hashMap.put("key1", "value1");

        assertEquals(1, hashMap.size());
        hashMap.remove("key1");
        assertTrue(hashMap.isEmpty());
    }

    @DisplayName("test IsEmpty Returns False If This Map Contains Entry Mappings")
    @Test
    public void testIsEmptyReturnsFalseIfThisMapContainsEntryMappings() {
        hashMap.put("key1", "value1");
        assertFalse(hashMap.isEmpty());
    }

    @DisplayName("test Remove Removes Mapping For A Key From This Map And Return Removed Value Associated With This Key")
    @Test
    public void testRemoveRemovesMappingForAKeyFromThisMaAndReturnRemovedValueAssociatedWithThisKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.remove("key1"));
        assertEquals("value2", hashMap.remove("key2"));
        assertEquals(0, hashMap.size());
        assertFalse(hashMap.containsKey("key1"));
        assertFalse(hashMap.containsKey("key2"));
    }

    @DisplayName("test Remove Removes Mapping For Key When Key Is Null")
    @Test
    public void testRemoveRemovesMappingForKeyWhenKeyIsNull() {
        hashMap.put("key1", "value1");
        hashMap.put(null, "value2");

        assertEquals(2, hashMap.size());
        assertEquals("value2", hashMap.remove(null));
        assertEquals(1, hashMap.size());
        assertFalse(hashMap.containsKey(null));
    }

    @DisplayName("test Remove Return Null When No Mapping In Map For This Key")
    @Test
    public void testRemoveReturnNullWhenNoMappingInMapForThisKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertNull(hashMap.remove("keyNotExist"));
        assertEquals(2, hashMap.size());
    }

    @DisplayName("test Contains Returns True If This Map Contains A Mapping For The Specified Key")
    @Test
    public void testContainsReturnsTrueIfThisMapContainsAMappingForTheSpecifiedKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertTrue(hashMap.containsKey("key2"));
    }

    @DisplayName("test Contains Returns False If This Map No Contains A Mapping For The Specified Key")
    @Test
    public void testContainsReturnsFalseIfThisMapNoContainsAMappingForTheSpecifiedKey() {
        hashMap.put("key1", "value1");

        assertFalse(hashMap.containsKey("key2"));
    }

    @DisplayName("test Contains Returns True If This Map Contains A Mapping For Key Null")
    @Test
    public void testContainsReturnsTrueIfThisMapContainsAMappingForKeyNull() {
        hashMap.put("key1", "value1");
        hashMap.put(null, "value1");

        assertTrue(hashMap.containsKey(null));
    }

    @DisplayName("test Contains Returns False If This Map No Contains A Mapping For Key Null")
    @Test
    public void testContainsReturnsFalseIfThisMapNoContainsAMappingForKeyNull() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value1");

        assertFalse(hashMap.containsKey(null));
    }

    @DisplayName("test Contains Returns False On Empty Map")
    @Test
    public void testContainsReturnsFalseOnEmptyMap() {

        assertFalse(hashMap.containsKey("key1"));
    }

    @DisplayName("test To String Create String Of Element Of This List")
    @Test
    public void testToStringCreateStringOfElementOfThisList() {
        hashMap.put("key1", "value1");

        String expected = "[Key='key1', Value=value1]";
        String actual = hashMap.toString();
        assertEquals(expected, actual);
    }

    // ------------- Iterator Test  --------------- //

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
        Iterator<Map.Entry<String, String>> iterator = hashMap.iterator();

        Map.Entry<String, String> actualFirst = iterator.next();
        assertEquals("value1", actualFirst.getValue());

        Map.Entry<String, String> actualSecond = iterator.next();
        assertEquals("value2", actualSecond.getValue());
    }

    @DisplayName("test Iterator Next Throw No Such Element Exception If Next Element Not Exist")
    @Test
    public void testIteratorNextThrowNoSuchElementExceptionIfNextElementNotExist() {
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






