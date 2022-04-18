package com.stahovskyi.datastructures.arraylist;

import java.util.StringJoiner;

public class ArrayList implements List {
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[10];
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (index > size) {
            throw new IllegalStateException(" Index is larger than the array !!! Array size is: " + size);
        }
        grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void grow() {
        if (array.length == size) {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public Object remove(int index) {
        if (isEmpty()) {
            throw new IllegalStateException(" Array is empty!!!");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception !! Array size is: " + size);
        }
        Object result = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size] = null;  // memory leek  !!
        size--;
        return result;
    }

    @Override
    public Object get(int index) {   // ??
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty !!");
        }
        return array[index];
    }

    // we can set value by index between[0, size-1]
    // otherwise throw new IndexOfBoundsExceptions
    @Override
    public Object set(Object value, int index) {
        if(index > size) {
            throw new IllegalStateException(" Index is larger than the array!!! Array size is: " + size );
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
    public int indexOf(Object value) {      // if array index is - null !!!???
        for (int i = 0; i < size; i++) {
            if (value.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size; i > 0; i--) {
            if (value.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString () {
        StringJoiner stringJoiner = new StringJoiner(", ","[","]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(array[i].toString());
        }
        return StringJoiner.toString();
    }
}
