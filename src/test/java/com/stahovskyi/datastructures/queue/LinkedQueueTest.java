package com.stahovskyi.datastructures.queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedQueueTest extends AbstractQueueTest {

    @Override
    protected Queue getQueue() {
        return new ArrayQueue();
    }
}
