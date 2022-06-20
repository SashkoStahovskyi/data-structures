package com.stahovskyi.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedQueue<T> implements Queue<T> {

    private int size;
    private Node<T> head;

    @Override
    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() {
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public T peek() {
        return head.value;
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
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(value, current.value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[ ", " ]");
        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    private static class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<T> {

        private Node<T> current = head;
        private boolean canRemove;
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
            canRemove = true;
            index++;
            return returnValue;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException(" Method Has Already Been Called After The Last Call Or Method Next Not Yet Been Called! ");

            } else if (size == 1) {                     // one element
                current = null;

            } else if (index - 1 == 0 & size > 1) {     // first element
                head = current;

            } else if (index - 1 == size - 1) {          // last element
                Node<T> prevNode = head;
                for (int i = 0; i <= index - 2; i++) {
                    if (i == index - 2) {
                        prevNode.next = null;
                    }
                    prevNode = prevNode.next;
                }

            } else {                                     // middle position
                for (int i = 0; i <= index - 2; i++) {
                    Node<T> prevNode = head;
                    if (i == index - 2) {
                        prevNode.next = current;
                    }
                }
            }
            canRemove = false;
            size--;
        }
    }
}


