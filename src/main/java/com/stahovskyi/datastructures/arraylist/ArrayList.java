package com.stahovskyi.datastructures.arraylist;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double DEFAULT_GROW_FACTOR = 1.5;
    private int size;
    private T[] array;
    private double growFactor;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        this(initialCapacity, DEFAULT_GROW_FACTOR);
    }

    public ArrayList(int initialCapacity, double growFactor) {
        array = (T[]) new Object[initialCapacity];
        this.growFactor = growFactor;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        checkEmptyArray();
        validateIndex(index);
        T result = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null; // memory lick fix
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        checkEmptyArray();
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        T object = array[index];
        array[index] = value;
        return object;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
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
    public boolean contains(T value) {
        checkEmptyArray();
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        checkEmptyArray();
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        checkEmptyArray();
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < array.length; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    private void grow() {
        if (array.length == size) {
            Object[] newArray = new Object[(int) (size * DEFAULT_GROW_FACTOR)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = (T[]) newArray;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalStateException(" Index more or less than list size ! " + " size is : " + size);
        }
    }

    private void checkEmptyArray() {
        if (isEmpty()) {
            throw new IllegalStateException(" ArrayList is empty !");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index;
        private boolean alreadyRemoved = true;

        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException(" Next Element Not Exist !");
            } else {
                T returnValue = array[index];
                index++;
                return returnValue;
            }
        }

        @Override
        public void remove() {
            if (!alreadyRemoved) {
                throw new IllegalStateException(" Element Already Removed !");
            } else {
                ArrayList.this.remove(index - 1);
                alreadyRemoved = false;
            }
        }
    }
}

