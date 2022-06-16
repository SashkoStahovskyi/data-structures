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

    @DisplayName("test Put Add New Entry In Bucket By Specified Key")
    @Test
    public void testPutAddNewEntryInBucketBySpecifiedKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.get("key1"));
        assertEquals("value2", hashMap.get("key2"));
    }

    @DisplayName("test Put Replace Old Value On New Value If The Map Previously Contained A Mapping For This Key")
    @Test
    public void testPutReplaceOldValueOnNewValueIfTheMapPreviouslyContainedAMappingForThisKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "oldValue");

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

    @DisplayName("test Get Returns Value Which Corresponding To This Entry")
    @Test
    public void testGetReturnsValueWhichCorrespondingToThisEntry() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        assertEquals("value2", hashMap.get("key2"));
    }

    @DisplayName("test Get Return Null When Specified Key Not Exist")    // method get return Null ??
    @Test
    public void testGetReturnNullWhenSpecifiedKeyNotExist() {
        assertNull(hashMap.get("key1"));
    }

    @DisplayName("test Size Returns The Number Of Entry Mappings In This Map")
    @Test
    public void testSizeReturnsTheNumberOfEntryMappingsInThisMap() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(2, hashMap.size());
    }

    @DisplayName("test IsEmpty Returns True If This Map Contains No Entry Mappings")
    @Test
    public void testReturnsIsEmptyTrueIfThisMapContainsNoEntryMappings() {
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

    @DisplayName("test Remove Removes Entry From Bucket ")
    @Test
    public void testRemoveRemovesEntryFromBucket () {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value2");
        hashMap.put("key4", "value2");
        hashMap.put("key5", "value2");
        hashMap.put("key6", "value2");
        hashMap.put("key7", "value2");
        hashMap.put("key8", "value2");
        hashMap.put("key9", "value2");
        hashMap.put("key10", "value222");

        assertEquals(10, hashMap.size());

        assertEquals("value222", hashMap.remove("key10"));
        assertEquals(9, hashMap.size());
        assertFalse(hashMap.containsKey("key10"));
    }

    @DisplayName("test Remove Return Null When No Mapping In Map For This Key")
    @Test
    public void testRemoveReturnNullWhenNoMappingInMapForThisKey() {
        hashMap.put("key1", "value1");

        assertNull(hashMap.remove("NotExist"));
    }

    @DisplayName("test Returns True If This Map Contains A Mapping For The Specified Key")
    @Test
    public void testReturnsTrueIfThisMapContainsAMappingForTheSpecifiedKey() {
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

    @DisplayName("test GrowIfNeeded Change The Number Of Bucket In Buckets Array")
    @Test
    public void testGrowIfNeededChangeTheNumberOfBucketInBucketsArray() {
        hashMap.

    }

   /* @DisplayName("test To String Create String Of Element Of This List")
    @Test
    public void testToStringCreateStringOfElementOfThisList() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        String expected = "[ A, B, C ]";
        String actual = hashMap.();
        assertEquals(expected, actual);

        HashMap.Entry<String, String> actualFirst = hashMap.();
        assertEquals("value1", actualFirst.getValue());
    }
*/
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
        Iterator<HashMap.Entry<String, String>> iterator = hashMap.iterator();

        HashMap.Entry<String, String> actualFirst = iterator.next();
        assertEquals("value1", actualFirst.getValue());

        HashMap.Entry<String, String> actualSecond = iterator.next();
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

    // ---------- Test For Private Methods ---------- //



}






