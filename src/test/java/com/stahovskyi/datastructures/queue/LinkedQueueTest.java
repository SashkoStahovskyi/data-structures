package com.stahovskyi.datastructures.queue;

public class LinkedQueueTest extends AbstractQueueTest {

    @Override
    public Queue<String> getQueue() {
        return new LinkedQueue<>();
    }
}
