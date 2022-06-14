package com.stahovskyi.datastructures.stack;

public class LinkedStackTest extends AbstractStackTest {

    @Override
    protected Stack<String> getStack() {
        return new LinkedStack<>();
    }
}
