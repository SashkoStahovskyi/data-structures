package com.stahovskyi.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayQueueTest extends AbstractQueueTest {

    @Override
    protected Queue getQueue() {
        return new ArrayQueue();
    }

}
