package com.stahovskyi.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {

    private List<String> list;

    @BeforeEach
    public void before() {
        list = getList();
    }

    protected abstract List<String> getList();

    @DisplayName("test Add New Value And Next Value To List")
    @Test
    public void testAddNewValueAndNextValueToList() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @DisplayName("test Add By Index In Head Of The List")
    @Test
    public void testAddByIndexInHeadOfTheList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.add("INDEX FIRST", 0);
        assertEquals(5, list.size());
        assertEquals(0, list.indexOf("INDEX FIRST"));
        assertEquals(1, list.indexOf("A"));
    }

    @DisplayName("test Add By Index In The Middle Of The List")
    @Test
    public void testAddByIndexInTheMiddleOfTheList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.add("INDEX MIDDLE", 2);
        assertEquals(5, list.size());
        assertEquals(2, list.indexOf("INDEX MIDDLE"));
        assertEquals(3, list.indexOf("C"));
    }

    @DisplayName("test Add By Index In The End Of The List")
    @Test
    public void testAddByIndexInTheEndOfTheList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.add("INDEX LAST", 4);
        assertEquals(5, list.size());
        assertEquals(3, list.indexOf("D"));
        assertEquals(4, list.indexOf("INDEX LAST"));
    }

    @DisplayName("test Add By Index Throw Index Of Bounds Exceptions When Index Less Than Zero Index")
    @Test
    public void testAddByIndexThrowIndexOfBoundsExceptionsWhenIndexLessThanZeroIndex() {
        list.add("A", 0);
        list.add("A", 1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("A", -1);
        });
    }

    @DisplayName("test Add By Index Throw Index Of Bounds Exceptions When Index Large Than Size")
    @Test
    public void testAddByIndexThrowIndexOfBoundsExceptionsWhenIndexLargeThanSize() {
        list.add("A", 0);
        list.add("A", 1);

        assertEquals(2, list.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("A", 3);
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

    @DisplayName("test Remove By Index Throw Index Of Bounds Exceptions When Index Less Than Zero")
    @Test
    public void testRemoveByIndexThrowIndexOfBoundsExceptionsWhenListIsEmpty() {
        list.add("A", 0);
        list.add("A", 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
    }

    @DisplayName("test Remove By Index Throw Index Of Bounds Exceptions When Index Large Than Last Element Index")
    @Test
    public void testRemoveByIndexThrowIndexOfBoundsExceptionsWhenIndexLargeThanLastElementIndex() {
        list.add("A", 0);
        list.add("B", 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(2);
        });
    }

    @DisplayName("test Contains Return True When Contains Element Exist")
    @Test
    public void testContainsReturnTrueWhenContainsElementExist() {
        list.add("A");
        list.add("B");

        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
    }

    @DisplayName("test Contains Return False When Contains Element Not Exist")
    @Test
    public void testContainsReturnFalseWhenContainsElementNotExist() {
        list.add("A");
        list.add("B");

        assertFalse(list.contains("K"));
    }

    @DisplayName("test Get Value By Index")
    @Test
    public void testGetValueByIndex() {
        list.add("A", 0);
        list.add("B", 1);

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @DisplayName("test Get Throw Index Out Of Bounds Exception When Index Less Than Zero")
    @Test
    public void testGetThrowIllegalStateOfExceptionWhenIndexLessThanZero() {
        list.add("A", 0);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
    }

    @DisplayName("test Get Throw Index Out Of Bounds Exception When Index Large Than Last Element Index")
    @Test
    public void testGetThrowIndexOutOfBoundsExceptionWhenIndexLargeThanLastElementIndex() {
        list.add("A", 0);
        list.add("B", 1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(2);
        });
    }

    @DisplayName("test Set Replaces The Element At The Specified Position")
    @Test
    public void testSetReplacesTheElementAtTheSpecifiedPosition() {
        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertEquals("A", list.set("NEW OBJECT", 0));
        assertEquals("NEW OBJECT", list.get(0));
    }

    @DisplayName("test Set Throw Index Out Of Bounds Exception When Index Less Than Zero")
    @Test
    public void testSetThrowIllegalStateOfExceptionWhenIndexLessThanZero() {
        list.add("A", 0);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("value", -1);
        });
    }

    @DisplayName("test Set Throw Index Out Of Bounds Exception When Index Large Than Last Element Index")
    @Test
    public void testSetThrowIndexOutOfBoundsExceptionWhenIndexLargeThanLastElementIndex() {
        list.add("A", 0);
        list.add("B", 1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("value", 2);
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

    @DisplayName("test Clear Removes All Of The Elements From This List")
    @Test
    public void testClearRemovesAllOfTheElementsFromThisList() {
        list.add("A");
        list.clear();
        assertTrue(list.isEmpty());
    }

    @DisplayName("test Size Change Size After Add Element And After Clear")
    @Test
    public void testSizeChangeSizeAfterAddElementAndAfterClear() {
        list.add("A");
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @DisplayName("test Index Of Returns Index Of The First Position Of The Specified Element In This List")
    @Test
    public void testIndexOfReturnsIndexOfTheFirstPositionOfTheSpecifiedElementInThisList() {
        list.add("A", 0);
        list.add("B", 1);
        list.add("A", 2);

        assertEquals(0, list.indexOf("A"));
    }

    @DisplayName("test Index Of Returns Index Of The Middle Position Of The Specified Element In This List")
    @Test
    public void testIndexOfReturnsIndexOfTheMiddlePositionOfTheSpecifiedElementInThisList() {
        list.add("A", 0);
        list.add("B", 1);
        list.add("A", 2);

        assertEquals(1, list.indexOf("B"));
    }

    @DisplayName("test Last Index Of Returns Index Of The Last Position Of The Specified Element In This List")
    @Test
    public void testLastIndexOfReturnsIndexOfTheLastPositionOfTheSpecifiedElementInThisList() {
        list.add("B", 0);
        list.add("A", 1);
        list.add("B", 2);

        assertEquals(2, list.lastIndexOf("B"));
    }

    @DisplayName("test Index Of Return Minus One If This List Does Not Contain The Element")
    @Test
    public void testIndexOfReturnMinusOneIfThisListDoesNotContainTheElement() {
        list.add("B", 0);
        list.add("A", 1);

        assertEquals(-1, list.indexOf("NotExistElement"));
    }

    @DisplayName("test Last Index Of Return Minus One If This List Does Not Contain The Element")
    @Test
    public void testLastIndexOfReturnMinusOneIfThisListDoesNotContainTheElement() {
        list.add("B", 0);
        list.add("A", 1);

        assertEquals(-1, list.indexOf("NotExistElement"));
    }

    @DisplayName("test To String Create String Of Element Of This List")
    @Test
    public void testToStringCreateStringOfElementOfThisList() {
        list.add("A");
        list.add("B");
        list.add("C");

        String expected = "[ A, B, C ]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @DisplayName("test Iterator Has Next Return True When Next Element Exist")
    @Test
    public void testIteratorHasNextReturnTrueWhenNextElementExist() {
        list.add("H");
        list.add("B");
        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
    }

    @DisplayName("test Iterator Has Next Return False When Next Element Not Exist")
    @Test
    public void testIteratorHasNextReturnFalseWhenNextElementNotExist() {
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @DisplayName("test Iterator Next Return Next Element")
    @Test
    public void testIteratorNextReturnNextElement() {
        list.add("H");
        list.add("B");
        Iterator<String> iterator = list.iterator();

        assertEquals("H", iterator.next());
        assertEquals("B", iterator.next());
    }

    @DisplayName("test Iterator Next Throw No Such Element Exception When Next Element Not Exist")
    @Test
    public void testIteratorNextThrowNoSuchElementExceptionWhenNextElementNotExist() {
        list.add("A");
        Iterator<String> iterator = list.iterator();

        assertEquals("A", iterator.next());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @DisplayName("test Iterator Remove From List With One Element")
    @Test
    public void testIteratorRemoveFromListWithOneElement() {
        list.add("A");
        Iterator<String> iterator = list.iterator();

        assertEquals(1, list.size());
        iterator.next();
        iterator.remove();
        assertEquals(0, list.size());
    }


    @DisplayName("test Iterator Remove From Head Position")
    @Test
    public void testIteratorRemoveFromHeadPosition() {
        list.add("A");
        list.add("B");
        Iterator<String> iterator = list.iterator();

        assertEquals(2, list.size());
        iterator.next();
        iterator.remove();
        assertEquals(1, list.size());
    }


    @DisplayName("test Iterator Remove From Middle Position")
    @Test
    public void testIteratorRemoveFromMiddlePosition() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        Iterator<String> iterator = list.iterator();

        assertEquals(4, list.size());
        assertEquals("C", list.get(2));
        iterator.next();
        iterator.remove();
        assertEquals(3, list.size());
        assertEquals("C", list.get(1));
    }

    @DisplayName("test Iterator Remove From Last Position")
    @Test
    public void testIteratorRemoveFromLastPosition() {
        list.add("A");
        list.add("B");
        list.add("C");
        Iterator<String> iterator = list.iterator();

        assertEquals(3, list.size());
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals(2, list.size());
        assertFalse(list.contains("C"));
    }

    @DisplayName("test Iterator Remove Throw IllegalStateException When Method Has Already Been Called After The Last Call")
    @Test
    public void testIteratorRemoveThrowIllegalStateExceptionWhenMethodHasAlreadyBeenCalledAfterTheLastCall() {
        list.add("A");
        list.add("B");
        Iterator<String> iterator = list.iterator();

        assertEquals(2, list.size());
        iterator.next();
        iterator.remove();
        assertEquals(1, list.size());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @DisplayName("test Iterator Remove Throw IllegalStateException If The Next Method Has Not Yet Been Called")
    @Test
    public void testIteratorRemoveThrowIllegalStateExceptionIfTheNextMethodHasNotYetBeenCalled() {
        list.add("A");
        list.add("B");
        Iterator<String> iterator = list.iterator();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }
}





