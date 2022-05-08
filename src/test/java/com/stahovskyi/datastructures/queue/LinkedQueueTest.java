package com.stahovskyi.datastructures.queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedQueueTest {

    @Test
    public void testEnqueueAndDequeueAndChangeSizeWorkCorrectly() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);

        assertEquals(2, linkedQueue.size());
        linkedQueue.dequeue();
        linkedQueue.dequeue();
        assertEquals(0, linkedQueue.size());
    }

    @Test
    public void testDequeueThrowIllegalStateExceptionOnEmptyQueue() {
        LinkedQueue linkedQueue = new LinkedQueue();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedQueue.dequeue();
        });
    }

    @Test
    public void testPeekThrowIllegalStateExceptionOnEmptyQueue() {
        LinkedQueue linkedQueue = new LinkedQueue();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedQueue.peek();
        });
    }

    @Test
    public void testPeekAndIsEmptyAndClearWorkCorrectly() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);

        assertEquals(1, linkedQueue.peek());
        linkedQueue.clear();
        assertEquals(0, linkedQueue.size());
    }

    @Test
    public void testContainsReturnFalseAndReturnTrue() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");

        assertTrue(linkedQueue.contains("B"));
        assertFalse(linkedQueue.contains("W"));
    }

    @Test
    public void testContainsReturnFalseOnEmptyQueue() {
        LinkedQueue linkedQueue = new LinkedQueue();
        assertFalse(linkedQueue.contains("W"));
    }

    @Test
    public void testIsEmptyFalseOnQueueWithData() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("Q");
        linkedQueue.enqueue("Q");

        assertFalse(linkedQueue.isEmpty());
    }

    @Test
    public void testIsEmptyTrueOnQueueAfterClear() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("Q");
        linkedQueue.enqueue("Q");

        linkedQueue.clear();
        assertTrue(linkedQueue.isEmpty());
    }

    @Test
    public void testContainsNull() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue(null);
        linkedQueue.enqueue("G");
        linkedQueue.enqueue("O");

        assertEquals(4, linkedQueue.size());
        assertTrue(linkedQueue.contains(null));
    }
}
