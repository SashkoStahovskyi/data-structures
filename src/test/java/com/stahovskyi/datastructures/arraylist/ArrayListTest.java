package com.stahovskyi.datastructures.arraylist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 add(Object value) -ok !

add(Object value, int index) -ok!
testAddByIndexThrowIllegalStateOfExceptionWhenIndexLargeThenSize -ok
testAddByIndexThrowIllegalStateOfExceptionWhenIndexLessThenSize - ok

 remove(int index) - ok !
 get(int index)  - ok!
 set(Object value, int index0)  - ok!
 clear() - ok!
 size() - ok!
 isEmpty() - ok!
 contains(Object value) -ok !
 indexOf(Object value) -OK !
lastIndexOf(Object value) - ok!
 test Add ByIndex Throw Illegal State Of Exception
 test Remove Throw Illegal State Of Exception
 test Get Throw Illegal State Of Exception When Array Is Empty
 */

public class ArrayListTest {

    @Test
    public void testAddToList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals(3, arrayList.size());
        assertEquals("A", arrayList.get(0));
        assertEquals("B", arrayList.get(1));
    }

    @Test
    public void testAddByIndex() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        arrayList.add("NEW OBJECT", 1);
        assertEquals(4, arrayList.size());
        assertEquals(1, arrayList.indexOf("NEW OBJECT"));
        assertEquals(2, arrayList.indexOf("B"));
    }

    @Test
    public void testAddByIndexThrowIllegalStateOfExceptionWhenIndexLargeThenSize() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayList.add("A", 3);
        });
    }

    @Test
    public void testAddByIndexThrowIllegalStateOfExceptionWhenIndexLessThenSize() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayList.add("A", -3);
        });
    }









    // ***************************************//


    @Test
    public void testAddAndRemoveAndChangeSize() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        arrayList.remove(2);
        assertEquals(2, arrayList.size());
    }

    @Test
    public void testRemoveThrowIllegalStateOfExceptionWhenIndexLargerThanArray() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayList.remove(3);
        });
    }

    @Test
    public void testRemoveThrowIllegalStateOfExceptionWhenArrayIsEmpty() {
        ArrayList arrayList = new ArrayList();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayList.remove(3);
        });
    }


    @Test
    public void testAddByIndexAndContainsAndIndexOf() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("NEW INDEX", 1);
        assertEquals(4, arrayList.size());
        assertTrue(arrayList.contains("NEW INDEX"));
        assertEquals(1, arrayList.indexOf("NEW INDEX"));
    }

    @Test
    public void testAddByIndexThrowIllegalStateOfException() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayList.add("B",3);
        });
    }

    @Test
    public void testGetAndSet() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        arrayList.set("NEW VALUE", 2);
        assertEquals("NEW VALUE", arrayList.get(2));
    }

    @Test
    public void testGetThrowIllegalStateOfExceptionWhenArrayIsEmpty () {
        ArrayList arrayList = new ArrayList();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayList.get(2);
        });
    }

    @Test
    public void testLastIndexOfAndClearAndIsEmpty(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("B");
        arrayList.add("H");

        assertEquals(3,arrayList.lastIndexOf("B"));
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }
}

