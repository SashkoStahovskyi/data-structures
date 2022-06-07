package com.stahovskyi.datastructures.queue;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayQueue<T> implements Queue<T> {
    private final int DEFAULT_INITIAL_CAPACITY = 10;
    private int size;
    private T[] array;

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
        if (size == 0) {
            return true;
        }
        return false;
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
        for (int i = 0; i < size; i++) {
            stringJoiner.add(array[i].toString());
        }
        return stringJoiner.toString();
    }

    private void emptyQueueCheck() {
        if (isEmpty()) {
            throw new IllegalStateException(" Queue is empty !!");
        }
    }

    private void ensureCapacity() {
        if (array.length == size) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = (T[]) newArray;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<T> {

        private int index = 0;
        private boolean alreadyRemoved = true;

        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException(" Next Element Not Exist !");
            }
            T returnValue = array[index];
            index++;
            return returnValue;
        }

        @Override
        public void remove() {
            if (!alreadyRemoved) {
                throw new IllegalStateException(" Element Already Removed !");
            }
            removeByIndex(index - 1);
            alreadyRemoved = false;
        }

        private void removeByIndex(int index) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[size - 1] = null; // memory lick fix
            size--;
        }
    }
}
