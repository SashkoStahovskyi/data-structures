package com.stahovskyi.datastructures.stack;

import java.util.Iterator;

public interface Stack <T> {

    void push(T value) ;

    T pop();

    T peek();

    boolean contains(T value);

    int size();

    boolean isEmpty();

    void clear();


    Iterator<T> iterator();
}
