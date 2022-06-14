package com.stahovskyi.datastructures.stack;

public interface Stack <T> extends Iterable <T> {

    void push(T value) ;

    T pop();

    T peek();

    boolean contains(T value);

    int size();

    boolean isEmpty();

    void clear();
}
