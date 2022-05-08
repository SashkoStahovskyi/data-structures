package com.stahovskyi.datastructures.queue;

/* void enqueue(Object value); ok
    Object dequeue();
    Object peek();
    int size();
    boolean isEmpty();
    boolean contains(Object value);
    void clear();
    String toString() */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {

    @Test
    public void testEnqueueAndDequeueAndChangeSizeWorkCorrectly() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);


        assertEquals(2, arrayQueue.size());
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        assertEquals(0, arrayQueue.size());
    }

    @Test
    public void testDequeueThrowIllegalStateExceptionOnEmptyQueue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayQueue.dequeue();
        });
    }

    @Test
    public void testPeekThrowIllegalStateExceptionOnEmptyQueue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayQueue.peek();
        });
    }

    @Test
    public void testPeekAndIsEmptyAndClearWorkCorrectly() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);

        assertEquals(3, arrayQueue.peek());
        arrayQueue.clear();
        assertEquals(0, arrayQueue.size());
    }

    @Test
    public void testContainsReturnFalseAndReturnTrue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");

        assertTrue(arrayQueue.contains("B"));
        assertFalse(arrayQueue.contains("W"));
    }

    @Test
    public void testContainsReturnFalseOnEmptyQueue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        assertFalse(arrayQueue.contains("W"));
    }

    @Test
    public void testIsEmptyFalseOnQueueWithData() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("Q");

        assertFalse(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyTrueOnQueueAfterClear() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("Q");

        arrayQueue.clear();
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyTrueOnNewQueue() {
        ArrayQueue arrayQueue = new ArrayQueue();
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testContainsNull () {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue(null);
        arrayQueue.enqueue("G");
        arrayQueue.enqueue("O");

        assertEquals(4,arrayQueue.size());
        assertFalse(arrayQueue.contains(null));
    }

    @Test
    public void testToString(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("A");
        arrayQueue.enqueue("B");
        arrayQueue.enqueue("C");

        String expected = "[A,B,C]";
        String actual = arrayQueue.toString();
        assertEquals(expected,actual);
    }
}
