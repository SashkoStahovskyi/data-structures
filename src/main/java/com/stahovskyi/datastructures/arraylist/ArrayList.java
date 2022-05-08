package com.stahovskyi.datastructures.arraylist;

import java.util.Objects;

public class ArrayList implements List {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IllegalStateException("Index more or less than size list ! " + " size is : " + size);
        }
        grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public Object remove(int index) {
        if (isEmpty()) {
            throw new IllegalStateException(" Array is empty !!!");
        }
        if (index < 0 || index > size) {
            throw new IllegalStateException("Index more or less than list size ! " + " size is : " + size);
        }
        Object result = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return result;
    }

    @Override
    public Object get(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty !!");
        }
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IllegalStateException("Index more or less than list size ! " + " size is : " + size);
        }
        Object object = array[index];
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
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size-1; i++) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size-1; i > 0; i--) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        if (array.length == size) {
            Object[] newArray = new Object[size * 2]; // how make 1,5 ??
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
