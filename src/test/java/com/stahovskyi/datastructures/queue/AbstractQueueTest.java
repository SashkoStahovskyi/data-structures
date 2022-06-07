package com.stahovskyi.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractQueueTest {

    private Queue<String> queue;

    @BeforeEach
    public void before() {
        queue = getQueue();
    }

    protected abstract Queue<String> getQueue();

    @Test
    @DisplayName(" test Enqueue Work Correctly")
    public void testEnqueueWorkCorrectly() {
        queue.enqueue("A");
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName(" test Dequeue Work Correctly")
    public void testDequeueWorkCorrectly() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size());
        assertEquals("A", queue.dequeue());
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName(" test Dequeue Throw Illegal State Exception On Empty Queue")
    public void testDequeueThrowIllegalStateExceptionOnEmptyQueue() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    @DisplayName("test Peek Work Correctly")
    public void testPeekWorkCorrectly() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.peek());
    }

    @Test
    @DisplayName("test Peek Throw Illegal State Exception On Empty Queue")
    public void testPeekThrowIllegalStateExceptionOnEmptyQueue() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            queue.peek();
        });
    }

    @Test
    @DisplayName(" test Size Work Correctly")
    public void testSizeWorkCorrectly() {
        queue.enqueue("A");

        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("test Contains Return True")
    public void testContainsReturnTrue() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertTrue(queue.contains("B"));
    }

    @Test
    @DisplayName("test Contains Return False")
    public void testContainsReturnFalse() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertFalse(queue.contains("W"));
    }

    @Test
    @DisplayName("test Contains Throw Illegal State Exception When Queue Is Empty")
    public void testContainsThrowIllegalStateExceptionWhenQueueIsEmpty() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            queue.contains("W");
        });
    }

    @Test
    @DisplayName("test Is Empty And Clear Work Correctly")
    public void testIsEmptyAndClearWorkCorrectly() {
        queue.enqueue("A");

        assertEquals(1, queue.size());
        queue.clear();
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("test Is Empty False On Queue With Data")
    public void testIsEmptyFalseOnQueueWithData() {
        queue.enqueue("Q");
        queue.enqueue("Q");

        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("test Is Empty True On Queue After Clear")
    public void testIsEmptyTrueOnQueueAfterClear() {
        queue.enqueue("Q");
        queue.enqueue("Q");

        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("test Is Empty True On New Queue")
    public void testIsEmptyTrueOnNewQueue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testToString() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        String expected = "[ A, B, C ]";
        String actual = queue.toString();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test Iterator Has Next Work Correctly")
    public  void testHasNextWorkCorrectly() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> itr = queue.iterator();

        assertTrue(itr.hasNext());
    }

    @Test
    @DisplayName("test Iterator Next Work Correctly")
    public  void testNextWorkCorrectly() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> itr = queue.iterator();

        assertEquals("A",itr.next());
        assertEquals("B",itr.next());
    }

    @Test
    @DisplayName("test Iterator Next Throw IllegalState Exception When Next Element Not Exist")
    public void testIteratorNextThrowIllegalStateExceptionWhenNextElementNotExist() {
        queue.enqueue("A");
        Iterator<String> itr = queue.iterator();

        assertEquals("A",itr.next());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            itr.next();
        });
    }

    @DisplayName("test Iterator Remove From List With One Element")
    @Test
    public void testIteratorRemoveFromListWithOneElement() {
        queue.enqueue("A");
        Iterator<String> itr = queue.iterator();

        assertEquals(1, queue.size());
        itr.next();
        itr.remove();
        assertEquals(0, queue.size());
    }


    @DisplayName("test Iterator Remove From Head Position")
    @Test
    public void testIteratorRemoveFromHeadPosition() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> itr = queue.iterator();

        assertEquals(2, queue.size());
        itr.next();
        itr.remove();
        assertEquals(1, queue.size());
        assertFalse(queue.contains("A"));
    }


    @DisplayName("test Iterator Remove From Middle Position")
    @Test
    public void testIteratorRemoveFromMiddlePosition() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Iterator<String> itr = queue.iterator();

        assertEquals(3, queue.size());
        itr.next();
        itr.next();
        itr.remove();
        assertEquals(2, queue.size());
        assertFalse(queue.contains("B"));
    }

    @DisplayName("test Iterator Remove From Last Position")
    @Test
    public void testIteratorRemoveFromLastPosition() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Iterator<String> itr = queue.iterator();

        assertEquals(3, queue.size());
        itr.next();
        itr.next();
        itr.next();
        itr.remove();
        assertEquals(2, queue.size());
        assertFalse(queue.contains("C"));
    }

    @DisplayName("test Iterator Throw Illegal State Of Exception When Element Already Removed")
    @Test
    public void testIteratorThrowIllegalStateOfExceptionWhenElementAlreadyRemoved() {
        queue.enqueue("A");
        queue.enqueue("B");
        Iterator<String> itr = queue.iterator();

        assertEquals(2, queue.size());
        itr.next();
        itr.remove();
        assertEquals(1, queue.size());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            itr.remove();
        });
    }
}



