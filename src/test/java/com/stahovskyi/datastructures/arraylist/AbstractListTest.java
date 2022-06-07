package com.stahovskyi.datastructures.arraylist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {

    private List<String> list;

    @BeforeEach
    public void before() {
        list = getList();
    }

    protected abstract List<String> getList();

    @DisplayName("test Add To List")
    @Test
    public void testAddToList() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(2));
    }

    @DisplayName("test Add By Index In Head Of List")
    @Test
    public void testAddByIndexInHeadOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.add("INDEX FIRST", 0);
        assertEquals(5, list.size());
        assertEquals(0, list.indexOf("INDEX FIRST"));
        assertEquals(1, list.indexOf("A"));
    }

    @DisplayName("test Add By Index In The Middle Of List")
    @Test
    public void testAddByIndexInTheMiddleOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.add("INDEX MIDDLE", 2);
        assertEquals(5, list.size());
        assertEquals(2, list.indexOf("INDEX MIDDLE"));
        assertEquals(3, list.indexOf("C"));
    }

    @DisplayName("test Add By Index In The End Of List")
    @Test
    public void testAddByIndexInTheEndOfList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.add("INDEX LAST", 4);
        assertEquals(5, list.size());
        assertEquals(3, list.indexOf("D"));
        assertEquals(4, list.indexOf("INDEX LAST"));
    }

    @DisplayName("test Add By Index Throw Illegal State Of ExceptionWhen Index Large Then Size")
    @Test
    public void testAddByIndexThrowIllegalStateOfExceptionWhenIndexLargeThenSize() {   /// ok
        list.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.add("A", 3);
        });
    }

    @DisplayName("test Add By Index Throw Illegal State Of Exception When Index Less Then Size")
    @Test
    public void testAddByIndexThrowIllegalStateOfExceptionWhenIndexLessThenSize() {   /// ok
        list.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.add("A", -3);
        });
    }

    @DisplayName("test Remove By Index From Head Position")
    @Test
    public void testRemoveByIndexFromHeadPosition() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("A", list.remove(0));
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
        assertEquals("C", list.get(1));
    }

    @DisplayName("test Remove By Index From List With One Element")
    @Test
    public void testRemoveByIndexFromListWithOneElement() {
        list.add("A");

        assertEquals("A", list.remove(0));
        assertEquals(0, list.size());
    }

    @DisplayName("test Remove By Index From Middle Position")
    @Test
    public void testRemoveByIndexFromMiddlePosition() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.size());
        assertEquals("C", list.remove(2));
        assertEquals(3, list.size());
        assertEquals(1, list.indexOf("B"));
        assertEquals(2, list.indexOf("D"));
    }

    @DisplayName("test Remove By Index From Tail Position")
    @Test
    public void testRemoveByIndexFromTailPosition() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.size());
        assertEquals("D", list.remove(3));
        assertEquals(3, list.size());
        assertEquals(2, list.indexOf("C"));

    }

    @DisplayName("test Remove Throw Illegal State Of Exception On Empty List")
    @Test
    public void testRemoveThrowIllegalStateOfExceptionOnEmptyList() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.remove(3);
        });
    }

    @DisplayName("test Remove Throw Index Of Bounds Exception When Index More Or Less Than List Size")
    @Test
    public void testRemoveThrowIndexOfBoundsExceptionWhenIndexMoreOrLessThanListSize() {
        list.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.remove(2);

        });
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.remove(-1);

        });
    }

    @DisplayName("test Contains Return True")
    @Test
    public void testContainsReturnTrue() {
        list.add("A");
        list.add("B");

        assertTrue(list.contains("B"));
    }

    @DisplayName("test Contains Return False")
    @Test
    public void testContainsReturnFalse() {
        list.add("A");
        list.add("B");

        assertFalse(list.contains("K"));
    }

    @DisplayName("test Contains Throw Illegal State Of Exception On Empty List")
    @Test
    public void testContainsThrowIllegalStateOfExceptionOnEmptyList() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.contains("A");
        });
    }

    @DisplayName("test Get By Index")
    @Test
    public void testGetByIndex() {
        list.add("A");
        list.add("B");

        assertEquals("B", list.get(1));
    }

    @DisplayName("test Get Throw Illegal State Of Exception When List Is Empty")
    @Test
    public void testGetThrowIllegalStateOfExceptionWhenListIsEmpty() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.get(2);
        });
    }

    @DisplayName("test Get Throw Illegal State Of Exception When Index More Or Less Than List")
    @Test
    public void testGetThrowIllegalStateOfExceptionWhenIndexMoreOrLessThanList() {
        list.add("B");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.get(-1);

        });
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.get(2);

        });
    }

    @DisplayName("test Set By Index")
    @Test
    public void testSetByIndex() {
        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertEquals("A", list.set("NEW OBJECT", 0));
        assertEquals("NEW OBJECT", list.get(0));
    }

    @DisplayName("test Set Throw Illegal State Exception On Empty List")
    @Test
    public void testSetThrowIllegalStateExceptionOnEmptyList() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.set("H", 1);
        });
    }

    @DisplayName("test Is Empty Return True On Empty List")
    @Test
    public void testIsEmptyReturnTrueOnEmptyList() {
        assertTrue(list.isEmpty());
    }

    @DisplayName("test Is Empty Return False On List With Data")
    @Test
    public void testIsEmptyReturnFalseOnListWithData() {
        list.add("A");
        assertFalse(list.isEmpty());
    }

    @DisplayName("test Is Empty Return True After Clear")
    @Test
    public void testIsEmptyReturnTrueAfterClear() {
        list.add("A");
        list.clear();
        assertTrue(list.isEmpty());
    }

    @DisplayName("test Clear Work Correctly")
    @Test
    public void testClearWorkCorrectly() {
        list.add("A");
        list.clear();
        assertTrue(list.isEmpty());
    }

    @DisplayName("test Size Work Correctly")
    @Test
    public void testSizeWorkCorrectly() {
        list.add("A");
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @DisplayName(" test Index Of Work Correctly")
    @Test
    public void testIndexOfWorkCorrectly() {
        list.add("A");
        list.add("B");
        list.add("A");

        assertEquals(0, list.indexOf("A"));
    }

    @DisplayName("test Index Of Throw Illegal State Exception On Empty List")
    @Test
    public void testIndexOfThrowIllegalStateExceptionOnEmptyList() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.indexOf("A");
        });
    }

    @DisplayName("test Last Index Of")
    @Test
    public void testLastIndexOf() {
        list.add("H");
        list.add("B");
        list.add("H");

        assertEquals(2, list.lastIndexOf("H"));
    }

    @DisplayName("test Last Index Of Throw Illegal State Exception On Empty List")
    @Test
    public void testLastIndexOfThrowIllegalStateExceptionOnEmptyList() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            list.lastIndexOf("H");
        });
    }

    @DisplayName("test Iterator Has Next And Next Work Correctly")
    @Test
    public void testIteratorHasNextAndNextWorkCorrectly() {
        list.add("H");
        list.add("B");
        Iterator<String> itr = list.iterator();

        assertTrue(itr.hasNext());
        assertEquals("H", itr.next());
        assertTrue(itr.hasNext());
        assertEquals("B", itr.next());
    }

    @DisplayName("test Iterator Has Next Return False When Next Element Not Exist")
    @Test
    public void testIteratorHasNextReturnFalseWhenNextElementNotExist() {
        Iterator<String> itr = list.iterator();
        assertFalse(itr.hasNext());
    }

    @DisplayName("test Iterator Next Throw Run Time Exception When Next Element Not Exist")
    @Test
    public void testIteratorNextThrowRunTimeExceptionWhenNextElementNotExist() {
        Iterator<String> itr = list.iterator();
        Assertions.assertThrows(RuntimeException.class, () -> {
            itr.next();
        });
    }

    @DisplayName("test Iterator Remove From List With One Element")
    @Test
    public void testIteratorRemoveFromListWithOneElement() {
        list.add("A");
        Iterator<String> itr = list.iterator();

        assertEquals(1, list.size());
        itr.next();
        itr.remove();
        assertEquals(0, list.size());
    }


    @DisplayName("test Iterator Remove From Head Position")
    @Test
    public void testIteratorRemoveFromHeadPosition() {
        list.add("A");
        list.add("B");
        Iterator<String> itr = list.iterator();

        assertEquals(2, list.size());
        itr.next();
        itr.remove();
        assertEquals(1, list.size());
    }


    @DisplayName("test Iterator Remove From Middle Position")
    @Test
    public void testIteratorRemoveFromMiddlePosition() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        Iterator<String> itr = list.iterator();

        assertEquals(4, list.size());
        assertEquals("C", list.get(2));
        itr.next();
        itr.remove();
        assertEquals(3, list.size());
        assertEquals("C", list.get(1));
    }

    @DisplayName("test Iterator Remove From Last Position")
    @Test
    public void testIteratorRemoveFromLastPosition() {
        list.add("A");
        list.add("B");
        list.add("C");
        Iterator<String> itr = list.iterator();

        assertEquals(3, list.size());
        itr.next();
        itr.next();
        itr.next();
        itr.remove();
        assertEquals(2, list.size());
        assertFalse(list.contains("C"));
    }

    @DisplayName("test Iterator Throw Illegal State Of Exception When Element Already Removed")
    @Test
    public void testIteratorThrowIllegalStateOfExceptionWhenElementAlreadyRemoved() {
        list.add("A");
        list.add("B");
        Iterator<String> itr = list.iterator();

        assertEquals(2, list.size());
        itr.next();
        itr.remove();
        assertEquals(1, list.size());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            itr.remove();
        });
    }
}





