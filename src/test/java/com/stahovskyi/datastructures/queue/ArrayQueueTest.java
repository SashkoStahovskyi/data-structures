package com.stahovskyi.datastructures.queue;

public class ArrayQueueTest extends AbstractQueueTest {

    @Override
    public Queue<String> getQueue() {
        return new ArrayQueue<>();
    }
}
