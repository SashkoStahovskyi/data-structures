package com.stahovskyi.datastructures.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStackTest {

    private Stack<String> stack;

    @BeforeEach
    public void before() {
        stack = getStack();
    }

    protected abstract Stack<String> getStack();

    @DisplayName("test Push And Change Size Work Correctly")
    @Test
    public void testPushAndChangeSizeWorkCorrectly() {
        assertEquals(0, stack.size());
        stack.push("B");
        stack.push("C");
        assertEquals(2, stack.size());
    }

    @DisplayName("test Pop Work Correctly")
    @Test
    public void testPopWorkCorrectly() {
        stack.push("B");
        stack.push("C");

        assertEquals(2, stack.size());
        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals(0, stack.size());
    }

    @DisplayName("test Pop Throw Illegal State Exception On Empty Stack")
    @Test
    public void testPopThrowIllegalStateExceptionOnEmptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
    }

    @DisplayName("test Peek Work Correctly")
    @Test
    public void testPeekWorkCorrectly() {
        stack.push("A");
        stack.push("B");

        assertEquals(2, stack.size());
        assertEquals("B", stack.peek());
    }

    @DisplayName("test Peek Throw Illegal State Exception On Empty Stack")
    @Test
    public void testPeekThrowIllegalStateExceptionOnEmptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            stack.peek();
        });
    }

    @DisplayName("test Is Empty Work Correctly")
    @Test
    public void testIsEmptyWorkCorrectly() {
        assertTrue(stack.isEmpty());
    }

    @DisplayName("test Is Empty Return True On New Stack")
    @Test
    public void testIsEmptyReturnTrueOnNewStack() {
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

    @DisplayName("test Contains Return True")
    @Test
    public void testContainsReturnTrue() {
        stack.push("A");
        stack.push("B");

        assertTrue(stack.contains("A"));
    }

    @DisplayName("test Contains Return False")
    @Test
    public void testContainsReturnFalse() {
        stack.push("A");
        stack.push("B");

        assertFalse(stack.contains("H"));
    }

    @DisplayName("test Contains Illegal State Exception False On Empty Stack")
    @Test
    public void testContainsReturnIllegalStateExceptionOnEmptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            stack.contains("H");
        });
    }

    @DisplayName("test Clear Work Correctly")
    @Test
    public void testClearWorkCorrectly() {
        stack.push("A");
        stack.push("B");

        stack.clear();
        assertEquals(0, stack.size());
    }

    //==========================

    @DisplayName("test Iterator Has Next And Next Work Correctly")
    @Test
    public void testIteratorHasNextAndNextWorkCorrectly() {
        stack.push("H");
        stack.push("B");
        Iterator<String> itr = stack.iterator();

        assertTrue(itr.hasNext());
        assertEquals("B", itr.next());
        assertTrue(itr.hasNext());
        assertEquals("H", itr.next());
    }

    @DisplayName("test Iterator Has Next Return False When Next Element Not Exist")
    @Test
    public void testIteratorHasNextReturnFalseWhenNextElementNotExist() {
        Iterator<String> itr = stack.iterator();
        assertFalse(itr.hasNext());
    }

    @DisplayName("test Iterator Next Throw Run Time Exception When Next Element Not Exist")
    @Test
    public void testIteratorNextThrowRunTimeExceptionWhenNextElementNotExist() {
        Iterator<String> itr = stack.iterator();
        Assertions.assertThrows(RuntimeException.class, () -> {
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

    @DisplayName("test Iterator Remove One Element In Stack Work Correctly")
    @Test
    public void testIteratorRemoveOneElementInStackWorkCorrectly() {
        stack.push("A");
        Iterator<String> itr = stack.iterator();

        assertEquals(1, stack.size());
        itr.next();
        itr.remove();
        assertEquals(0, stack.size());
    }

    @DisplayName("test Iterator Throw Illegal State Of Exception When Element Already Removed")
    @Test
    public void testIteratorThrowIllegalStateOfExceptionWhenElementAlreadyRemoved() {
        stack.push("A");
        stack.push("B");
        Iterator<String> itr = stack.iterator();

        assertEquals(2, stack.size());
        itr.next();
        itr.remove();
        assertEquals(1, stack.size());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            itr.remove();
        });
    }
}



    

