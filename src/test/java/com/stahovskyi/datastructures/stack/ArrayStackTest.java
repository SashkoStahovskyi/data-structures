package com.stahovskyi.datastructures.stack;

public class ArrayStackTest extends AbstractStackTest {

    @Override
    protected Stack<String> getStack() {
        return new ArrayStack<>();
    }
}
