package com.stahovskyi.datastructures.queue;

public class ArrayQueue implements Queue {
    private int size;
    private Object[] array;

    public ArrayQueue() {
        array = new Object[10];
    }

    @Override
    public void enqueue(Object value) {
        array[size] = value;
        size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty !!");
        }
        Object value = array[size - 1];
        array[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public Object peek() {
        if (size == 0) {
            throw new IllegalStateException("Array is empty !!");
        }
        return array[size - 1];
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
    public boolean contains(Object value){      // null - ?
        for (int i = 0; i < size - 1; i++) {
            Object result = array[i];
            if (value.equals(result)) {
                return true;
            }
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
}
