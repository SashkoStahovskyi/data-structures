package com.stahovskyi.datastructures.stack;

public class LinkedStackTest extends AbstractStackTest {

    @Override
    protected Stack getStack() {
        return new LinkedStack();
    }
}
