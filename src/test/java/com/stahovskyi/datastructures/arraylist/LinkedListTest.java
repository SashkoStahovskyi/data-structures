package com.stahovskyi.datastructures.arraylist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    void add(Object value); - ok
    void add (Object value, int index); - ok
    testAddByIndexThrowIllegalStateOfExceptionWhenIndexLessThenSize - ok
    testAddByIndexThrowIllegalStateOfExceptionWhenIndexLargeThenSize - ok
     testRemoveByIndexFromHeadPosition - ok
     testRemoveByIndexFromMiddlePosition -ok
     testRemoveByIndexFromTailPosition - ok
     testRemoveByIndexFromListWithOneElement - ok
     testRemoveThrowIllegalStateOfExceptionOnEmptyList - ok
     testRemoveThrowIndexOfBoundsExceptionWhenIndexMoreOrLessThanListSize - ok
    Object get (int index); - ok
    testGetThrowIllegalStateOfExceptionWhenIndexMoreOrLessThanList - ok
     testGetThrowIllegalStateOfExceptionWhenListIsEmpty - ok
    Object set(Object value, int index0); - ok
    testSetThrowIllegalStateExceptionOnEmptyList - ok
    void clear(); - ok
    int size(); - ok
    testIsEmptyReturnTrueOnEmptyList - ok!
    testIsEmptyReturnFalseOnListWithData - ok
    testIsEmptyReturnTrueAfterClear - ok
    boolean contains(Object value); - ok
    testContainsReturnTrue - ok
    testContainsReturnFalse - ok
    testContainsThrowIllegalStateOfExceptionOnEmptyList - ok
    int indexOf(Object value); - ok
    testIndexOfThrowIllegalStateExceptionOnEmptyList - ko
    int lastIndexOf(Object value); - ok
    testLastIndexOfThrowIllegalStateExceptionOnEmptyList - ok
      */

public class LinkedListTest {  // stoppers : method  get ( value ) method remove ( size )

    @Test
    public void testAddToList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals(3, linkedList.size());
        assertEquals("A", linkedList.get(0)); // work with value in method Get
        assertEquals("C", linkedList.get(2));
    }

    @Test
    public void testAddByIndexInHeadOfList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        linkedList.add("INDEX FIRST", 0);
        assertEquals(5, linkedList.size());
        assertEquals(0, linkedList.indexOf("INDEX FIRST"));
        assertEquals(1, linkedList.indexOf("A"));
    }

    @Test
    public void testAddByIndexInTheMiddleOfList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        linkedList.add("INDEX MIDDLE", 2);
        assertEquals(5, linkedList.size());
        assertEquals(2, linkedList.indexOf("INDEX MIDDLE"));
        assertEquals(3, linkedList.indexOf("C"));
    }

    @Test
    public void testAddByIndexInTheEndOfList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        linkedList.add("INDEX LAST", 3);
        assertEquals(5, linkedList.size());
        assertEquals(2, linkedList.indexOf("C"));
        assertEquals(3, linkedList.indexOf("D"));
        assertEquals(4, linkedList.indexOf("INDEX LAST"));
    }

    @Test
    public void testAddByIndexThrowIllegalStateOfExceptionWhenIndexLargeThenSize() {   /// ok
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.add("A", 3);
        });
    }

    @Test
    public void testAddByIndexThrowIllegalStateOfExceptionWhenIndexLessThenSize() {   /// ok
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.add("A", -3);
        });
    }


    @Test
    public void testRemoveByIndexFromHeadPosition() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals("A", linkedList.remove(0));
        assertEquals(2, linkedList.size());
        assertEquals("B", linkedList.get(0)); //  work with value in method Get
        assertEquals("C", linkedList.get(1));
    }

    @Test
    public void testRemoveByIndexFromListWithOneElement() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");

        assertEquals("A", linkedList.remove(0));
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testRemoveByIndexFromMiddlePosition() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C"); //2
        linkedList.add("D");

        assertEquals(4, linkedList.size());
        assertEquals("C", linkedList.remove(2));
        assertEquals(3, linkedList.size());
        assertEquals(1, linkedList.indexOf("B"));
        assertEquals(2, linkedList.indexOf("D")); //  work with value in method Get
    }

    @Test
    public void testRemoveByIndexFromTailPosition() {   //  work with value in method Get
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        assertEquals(4, linkedList.size());
        assertEquals("D", linkedList.remove(3));
        assertEquals(3, linkedList.size());
        assertEquals(2, linkedList.indexOf("C"));

    }


    @Test
    public void testRemoveThrowIllegalStateOfExceptionOnEmptyList() {   /// ok
        LinkedList linkedList = new LinkedList();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.remove(3);
        });
    }

    @Test
    public void testRemoveThrowIndexOfBoundsExceptionWhenIndexMoreOrLessThanListSize() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.remove(2);

        });
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.remove(-1);

        });
    }

    @Test
    public void testContainsReturnTrue() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");

        assertTrue(linkedList.contains("B"));
    }

    @Test
    public void testContainsReturnFalse() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");

        assertFalse(linkedList.contains("K"));
    }

    @Test
    public void testContainsThrowIllegalStateOfExceptionOnEmptyList() {
        LinkedList linkedList = new LinkedList();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.contains("A");
        });
    }


    @Test
    public void testGetByIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals("B", linkedList.get(1));
    }

    @Test
    public void testGetThrowIllegalStateOfExceptionWhenListIsEmpty() {  // ok !
        LinkedList linkedList = new LinkedList();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.get(2);
        });
    }

    @Test
    public void testGetThrowIllegalStateOfExceptionWhenIndexMoreOrLessThanList() {   // ok !
        LinkedList linkedList = new LinkedList();
        linkedList.add("B");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.get(-1);

        });
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.get(2);

        });
    }

    @Test
    public void testSetByIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");

        assertEquals(2, linkedList.size());
        assertEquals("A", linkedList.set("NEW OBJECT", 0));
        assertEquals("NEW OBJECT", linkedList.get(0));
    }

    @Test
    public void testSetThrowIllegalStateExceptionOnEmptyList() {
        LinkedList linkedList = new LinkedList();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.set("H", 1);
        });
    }

    @Test
    public void testIsEmptyReturnTrueOnEmptyList() {
        LinkedList linkedList = new LinkedList();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testIsEmptyReturnFalseOnListWithData() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testIsEmptyReturnTrueAfterClear() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testClearWorkCorrectly() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testSizeWorkCorrectly() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        assertEquals(1, linkedList.size());
        linkedList.clear();
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testIndexOfWorkCorrectly() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("A");

        assertEquals(0, linkedList.indexOf("A"));
    }

    @Test
    public void testIndexOfThrowIllegalStateExceptionOnEmptyList() {
        LinkedList linkedList = new LinkedList();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.indexOf(1);
        });
    }

    @Test
    public void testLastIndexOf() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("H");
        linkedList.add("B");
        linkedList.add("H");

        assertEquals(2, linkedList.lastIndexOf("H"));
    }

    @Test
    public void testLastIndexOfThrowIllegalStateExceptionOnEmptyList() {
        LinkedList linkedList = new LinkedList();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedList.lastIndexOf("H");
        });
    }
}

 /*
   @Test
    public void testAddByIndexAndChangeSize() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        linkedList.add("INDEX FIRST", 0);   // first case add in head
        assertEquals(5, linkedList.size());  // check size
        assertEquals(0, linkedList.indexOf("INDEX FIRST"));  // check insert element index
        assertEquals(1, linkedList.indexOf("A")); // check next index

        linkedList.add("INDEX MIDDLE", 2);  // second case add in the middle of the link
        assertEquals(6, linkedList.size());  // check size
        assertEquals(2, linkedList.indexOf("INDEX MIDDLE"));  // check insert element index
        assertEquals(3, linkedList.indexOf("B")); // check next index

        linkedList.add("INDEX LAST", 5);  // third case add int the tail
        assertEquals(7, linkedList.size());  // check size
        assertEquals(4, linkedList.indexOf("C")); // check index
        assertEquals(5, linkedList.indexOf("D")); // check index
        assertEquals(6, linkedList.indexOf("INDEX LAST"));  // check insert element index
    }
         */


