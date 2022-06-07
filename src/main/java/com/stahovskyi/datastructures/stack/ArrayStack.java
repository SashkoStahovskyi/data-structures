package com.stahovskyi.datastructures.stack;

import com.stahovskyi.datastructures.arraylist.ArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
    private final int DEFAULT_INITIAL_CAPACITY = 10;
    private int size;
    private T[] array;

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
        emptyStackCheck();
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
            throw new IllegalStateException("Stack Is Empty !!");
        }
    }

    private void ensureCapacity() {
        if (array.length == size) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
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
        private boolean alreadyRemoved = true;
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException(" Next Element Not Exist !");
            } else {
                T result = array[size - 1];
                size--;
                return result;
            }
        }

        @Override
        public void remove() {
            if (!alreadyRemoved) {
                throw new IllegalStateException(" Element Already Removed!");
            } else {
                array[index -1] = null;
                System.arraycopy(array,index -1,array,index,size - index -1);
                size--;
                alreadyRemoved = false;
            }
        }
    }
}
