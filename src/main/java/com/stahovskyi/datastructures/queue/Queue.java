package com.stahovskyi.datastructures.queue;

public interface Queue<T> extends Iterable<T> {

    void enqueue(T value);

    T dequeue();

    T peek();

    int size();

    boolean isEmpty();

    boolean contains(T value);

    void clear();

    String toString();
}
