package com.stahovskyi.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractQueueTest {

    private Queue<String> queue;

    @BeforeEach
    public void before() {
        queue = getQueue();
    }

    protected abstract Queue<String> getQueue();

    @Test
    @DisplayName("test Enqueue enqueue New Element To The Queue And Change Size")
    public void testEnqueueNewElementToTheQueueAndChangeSize() {
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals(2, queue.size());
    }

    @Test
    @DisplayName("test Dequeue Removes Element From Queue")
    public void testDequeueRemoveElementFromQueue() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size());
        assertEquals("A", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("B", queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("test Peek Returns The Head Of The Queue Without Removing It")
    public void testPeekReturnsTheHeadOfTheQueueWithoutRemovingIt() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.peek());
    }

    @Test
    @DisplayName("test Size Return Actual Size Of Queue")
    public void testSizeReturnActualSizeOfQueue() {
        queue.enqueue("A");
        assertEquals(1, queue.size());
        queue.enqueue("B");
        assertEquals(2, queue.size());
    }

    @Test
    @DisplayName("test Contains Return True When Element Exist")
    public void testContainsReturnTrueWhenElementExist() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertTrue(queue.contains("B"));
    }

    @Test
    @DisplayName("test Contains Return False When Element Not Exist")
    public void testContainsReturnFalseWhenElementNotExist() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertFalse(queue.contains("W"));
    }

    @Test
    @DisplayName("test Is Empty Return True When Queue Is Empty After Clear")
    public void testIsEmptyReturnTrueWhenQueueIsEmptyAfterDequeue() {
        queue.enqueue("A");

        assertEquals(1, queue.size());
        queue.clear();
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("test Is Empty Return False On Queue With Data")
    public void testIsEmptyReturnFalseOnQueueWithData() {
        queue.enqueue("Q");
        queue.enqueue("Q");

        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("test Is Empty Return True On New Queue")
    public void testIsEmptyReturnTrueOnNewQueue() {
        assertTrue(queue.isEmpty());
    }


    @Test
    @DisplayName("test Clear Removes All Element From Queue")
    public void testClearRemovesAllElementFromQueue() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size());
        queue.clear();
        assertEquals(0, queue.size());
    }


    @DisplayName("test To String Create String Of Element Of This List")
    @Test
    public void testToStringCreateStringOfElementOfThisList() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        String expected = "[ A, B, C ]";
        String actual = queue.toString();
        assertEquals(expected, actual);
    }

    // ----  ITERATOR TEST  --- //

    @DisplayName("test Iterator Has Next Return True When Next Element Exist")
    @Test
    public void testIteratorHasNextReturnTrueWhenNextElementExist() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
    }

    @DisplayName("test Iterator Has Next Return False When Next Element Not Exist")
    @Test
    public void testIteratorHasNextReturnFalseWhenNextElementNotExist() {
        Iterator<String> iterator = queue.iterator();
        assertFalse(iterator.hasNext());
    }

    @DisplayName("test Iterator Next Return Next Element")
    @Test
    public void testIteratorNextReturnNextElement() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> iterator = queue.iterator();

        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
    }

    @DisplayName("test Iterator Next Throw No Such Element Exception When Next Element Not Exist")
    @Test
    public void testIteratorNextThrowNoSuchElementExceptionWhenNextElementNotExist() {
        queue.enqueue("A");
        Iterator<String> iterator = queue.iterator();

        assertEquals("A", iterator.next());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @DisplayName("test Iterator Remove From Queue With One Element")
    @Test
    public void testIteratorRemoveFromQueueWithOneElement() {
        queue.enqueue("A");
        Iterator<String> iterator = queue.iterator();

        assertEquals(1, queue.size());
        iterator.next();
        iterator.remove();
        assertEquals(0, queue.size());
    }

    @DisplayName("test Iterator Remove Element From Head Position")
    @Test
    public void testIteratorRemoveElementFromHeadPosition() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> iterator = queue.iterator();

        assertEquals(2, queue.size());
        iterator.next();
        iterator.remove();
        assertEquals(1, queue.size());
        assertFalse(queue.contains("A"));
        assertTrue(queue.contains("B"));
    }

    @DisplayName("test Iterator Remove From Middle Position")
    @Test
    public void testIteratorRemoveFromMiddlePosition() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Iterator<String> iterator = queue.iterator();

        assertEquals(3, queue.size());
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals(2, queue.size());
        assertTrue(queue.contains("A"));
        assertTrue(queue.contains("C"));
        assertFalse(queue.contains("B"));
    }

    @DisplayName("test Iterator Remove From Last Position")
    @Test
    public void testIteratorRemoveFromLastPosition() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Iterator<String> iterator = queue.iterator();

        assertEquals(3, queue.size());
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals(2, queue.size());
        assertTrue(queue.contains("A"));
        assertTrue(queue.contains("B"));
        assertFalse(queue.contains("C"));
    }

    @DisplayName("test Iterator Remove Throw IllegalStateException When Method Has Already Been Called After The Last Call")
    @Test
    public void testIteratorRemoveThrowIllegalStateExceptionWhenMethodHasAlreadyBeenCalledAfterTheLastCall() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> iterator = queue.iterator();

        assertEquals(2, queue.size());
        iterator.next();
        iterator.remove();
        assertEquals(1, queue.size());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @DisplayName("test Iterator Remove Throw IllegalStateException If The Next Method Has Not Yet Been Called")
    @Test
    public void testIteratorRemoveThrowIllegalStateExceptionIfTheNextMethodHasNotYetBeenCalled() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> iterator = queue.iterator();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }
}



