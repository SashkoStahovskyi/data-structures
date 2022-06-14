package com.stahovskyi.datastructures.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStackTest {

    private Stack<String> stack;

    @BeforeEach
    public void before() {
        stack = getStack();
    }

    protected abstract Stack<String> getStack();

    @DisplayName("test Push Add New Element In Stack And Change Size")
    @Test
    public void testPushAddNewElementInStackAndChangeSize() {
        assertEquals(0, stack.size());
        stack.push("B");
        stack.push("C");
        assertEquals(2, stack.size());
        stack.contains("B");
        stack.contains("C");
    }

    @DisplayName("test Pop Removes Element From Stack")
    @Test
    public void testPopRemovesElementFromStack() {
        stack.push("A");
        stack.push("B");

        assertEquals(2, stack.size());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        assertEquals(0, stack.size());
        assertFalse(stack.contains("A"));
        assertFalse(stack.contains("B"));
    }

    @DisplayName("test Pop Throw Illegal State Exception On Empty Stack")
    @Test
    public void testPopThrowIllegalStateExceptionOnEmptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
    }

    @DisplayName("test Peek Returns The Head Of The Stack Without Removing It")
    @Test
    public void testPeekReturnsTheHeadOfTheStackWithoutRemovingIt() {
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek());
        stack.pop();
        assertEquals("A", stack.peek());
    }

    @DisplayName("test Peek Throw Illegal State Exception On Empty Stack")
    @Test
    public void testPeekThrowIllegalStateExceptionOnEmptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            stack.peek();
        });
    }

    @DisplayName("test Is Empty Return True When Stack Is Empty")
    @Test
    public void testIsEmptyReturnTrueWhenStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @DisplayName("test Is Empty Return False On Stack With DAta")
    @Test
    public void testIsEmptyReturnFalseOnStackWithDAta() {
        stack.push("A");
        assertFalse(stack.isEmpty());
    }

    @DisplayName("test Is Empty Return True On Stack After Clear")
    @Test
    public void testIsEmptyReturnTrueOnStackAfterClear() {
        stack.push("A");
        stack.push("B");

        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @DisplayName("test Contains Return True When Element Exist In Stack")
    @Test
    public void testContainsReturnTrueWhenElementExistInStack() {
        stack.push("A");
        stack.push("B");

        assertTrue(stack.contains("A"));
    }

    @DisplayName("test Contains Return True When Element Not Exist In Stack")
    @Test
    public void testContainsReturnTrueWhenElementNotExistInStack() {
        stack.push("A");
        stack.push("B");

        assertFalse(stack.contains("H"));
    }

    @DisplayName("test Clear Removes All Element In Stack")
    @Test
    public void testClearRemovesAllElementInStack() {
        stack.push("A");
        stack.push("B");

        stack.clear();
        assertEquals(0, stack.size());
    }

    // ----- ITERATOR TEST ------ //

    @DisplayName("test Iterator Has Next Return True When Next Element Exist")
    @Test
    public void testIteratorHasNextReturnTrueWhenNextElementExist() {
        stack.push("H");
        stack.push("B");
        Iterator<String> itr = stack.iterator();

        assertTrue(itr.hasNext());
    }

    @DisplayName("test Iterator Next Return Next Element In Stack")
    @Test
    public void testIteratorNextReturnNextElementInStack() {
        stack.push("H");
        stack.push("B");
        Iterator<String> itr = stack.iterator();

        assertEquals("B", itr.next());
        assertEquals("H", itr.next());
    }

    @DisplayName("test Iterator Has Next Return False When Next Element Not Exist")
    @Test
    public void testIteratorHasNextReturnFalseWhenNextElementNotExist() {
        Iterator<String> itr = stack.iterator();
        assertFalse(itr.hasNext());
    }

    @DisplayName("test Iterator Next Throw NoSuchElementException When Next Element Not Exist")
    @Test
    public void testIteratorNextThrowRunTimeExceptionWhenNextElementNotExist() {
        Iterator<String> itr = stack.iterator();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            itr.next();
        });
    }

    @DisplayName("test Iterator Remove From Last Position Work Correctly")
    @Test
    public void testIteratorRemoveFromLastPositionWorkCorrectly() {
        stack.push("A");
        stack.push("B");
        Iterator<String> itr = stack.iterator();

        assertEquals(2, stack.size());
        itr.next();
        itr.next();
        itr.remove();
        assertEquals(1, stack.size());
        assertFalse(stack.contains("B"));

    }

    @DisplayName("test Iterator Remove From Middle Position Work Correctly")
    @Test
    public void testIteratorRemoveFromMiddlePositionWorkCorrectly() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        Iterator<String> itr = stack.iterator();

        assertEquals(3, stack.size());
        itr.next();
        itr.next();
        itr.remove();
        assertEquals(2, stack.size());
        assertFalse(stack.contains("B"));
    }

    @DisplayName("test Iterator Remove From First Position Work Correctly")
    @Test
    public void testIteratorRemoveFromFirstPositionWorkCorrectly() {
        stack.push("A");
        stack.push("B");
        Iterator<String> itr = stack.iterator();

        assertEquals(2, stack.size());
        itr.next();
        itr.next();
        itr.remove();
        assertEquals(1, stack.size());
        assertFalse(stack.contains("A"));
    }

    @DisplayName("test Iterator Remove One Element In Stack ")
    @Test
    public void testIteratorRemoveOneElementInStackWorkCorrectly() {
        stack.push("A");
        Iterator<String> itr = stack.iterator();

        assertEquals(1, stack.size());
        itr.next();
        itr.remove();
        assertEquals(0, stack.size());
        assertFalse(stack.contains("A"));
    }

    @DisplayName("test Iterator Remove Throw Illegal State Of Exception When Element Already Removed")
    @Test
    public void testIteratorThrowIllegalStateOfExceptionWhenElementAlreadyRemoved() {
        stack.push("A");
        stack.push("B");
        Iterator<String> itr = stack.iterator();

        itr.next();
        itr.remove();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            itr.remove();
        });
    }
}



    

