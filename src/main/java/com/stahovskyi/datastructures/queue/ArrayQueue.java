package com.stahovskyi.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayQueue<T> implements Queue<T> {
    private final int DEFAULT_INITIAL_CAPACITY = 10;
    private final double DEFAULT_GROW_FACTOR = 1.5;
    private int size;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        array = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public void enqueue(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public T dequeue() {
        emptyQueueCheck();
        T returnValue = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        size--;
        return returnValue;
    }

    @Override
    public T peek() {
        emptyQueueCheck();
        return array[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        emptyQueueCheck();
        for (int i = 0; i < size; i++) {
            T valueInQueue = array[i];
            if (Objects.equals(value, valueInQueue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[ ", " ]");
        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    private void emptyQueueCheck() {
        if (isEmpty()) {
            throw new IllegalStateException(" Queue Is Empty !!");
        }
    }

    private void ensureCapacity() {
        if (array.length == size) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[(int) (array.length * DEFAULT_GROW_FACTOR) + 1];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<T> {

        private int index;
        private boolean canRemove;

        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException(" Next Element Not Exist !");
            }
            T returnValue = array[index];
            index++;
            canRemove = true;
            return returnValue;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Method Has Already Been Called After The Last Call Or Method Next Not Yet Been Called!");
            }
            System.arraycopy(array, index, array, index - 1, size - index);
            array[size - 1] = null;
            size--;
            canRemove = false;
        }
    }
}
