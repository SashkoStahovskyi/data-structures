package com.stahovskyi.datastructures.arraylist;

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

public class DoubleLinkedListTest extends AbstractListTest {
    @Override
    protected List getList() {
        return new DoubleLinkedList();
    }
}



