package com.stahovskyi.datastructures.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedStack<T> implements Stack<T> {

    private Node<T> top;
    private int size;

    @Override
    public void push(T value) {
        if (isEmpty()) {
            top = new Node<>(value);
        } else {
            Node<T> newNode = new Node<>(value);
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

    @Override
    public T pop() {
        emptyStackCheck();
        T returnValue = top.value;
        top = top.next;
        size--;
        return returnValue;
    }

    @Override
    public T peek() {
        emptyStackCheck();
        return top.value;
    }

    @Override
    public boolean contains(T value) {
        Node<T> current = top;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return true;
            }
            current = current.next;
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
        top = null;
        size = 0;
    }

    private void emptyStackCheck() {
        if (isEmpty()) {
            throw new IllegalStateException(" Stack Is Empty !!");
        }
    }

    private static class Node<T> {

        private Node<T> next;
        T value;

        private Node(T value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<T> {

        private boolean canRemove = true;
        private Node<T> current = top;
        private int index;

        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException(" Next Element Not Exist !");
            }
            T returnValue = current.value;
            current = current.next;
            index++;
            return returnValue;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Method Has Already Been Called After The Last Call Or Method Next Not Yet Been Called!");
            } else if (size > 1) {  // from first position
                for (int i = size - 1; i >= index + 1; i--) {
                    current = current.next;
                    if (i == index + 1) {
                        current.next = null;
                    }
                }
            } else if (size == 1) {  // with one element - ok !
                current = null;
                size--;
                return;
            } else if (index == size - 1) {  // last element
                current.next = top;

            }
            for (int i = size - 1; i >= index - 1; i--) {
                current = current.next;
                if (i == index - 1) {
                    current = current.next.next;

                }
            }

            size--;
            canRemove = false;
        }
    }
}




