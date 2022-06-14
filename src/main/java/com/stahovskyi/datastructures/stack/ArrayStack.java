package com.stahovskyi.datastructures.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {
    private final int DEFAULT_INITIAL_CAPACITY = 10;
    private final double DEFAULT_ENSURE_CAPACITY = 1.5;
    private int size;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        array = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public void push(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public T pop() {
        emptyStackCheck();
        T result = array[size - 1];
        size--;
        return result;
    }

    @Override
    public T peek() {
        emptyStackCheck();
        return array[size - 1];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            T valueInStack = array[i];
            if (value.equals(valueInStack)) {
                return true;
            }
        }
        return false;
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
        Arrays.fill(array, null);
        size = 0;
    }

    private void emptyStackCheck() {
        if (isEmpty()) {
            throw new IllegalStateException(" Stack Is Empty !! ");
        }
    }

    private void ensureCapacity() {
        if (array.length == size) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[(int) (array.length * DEFAULT_ENSURE_CAPACITY) + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<T> {

        private int index;
        private boolean canRemove = true;

        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException(" Next Element Not Exist !");
            }
            T result = array[size - 1];
            size--;
            return result;
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
