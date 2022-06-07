package com.stahovskyi.datastructures.queue;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedQueue<T> implements Queue<T> {

    private int size;
    private Node<T> head;

    @Override
    public void enqueue(T value) {
        Node<T> newNode = new Node<T>(value);
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
        emptyQueueCheck();
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public T peek() {
        emptyQueueCheck();
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
        emptyQueueCheck();
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
        Node<T> current = head;
        while (current.value != null) {
            stringJoiner.add(current.value.toString());
            current = current.next;
        }
        return stringJoiner.toString();
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private void emptyQueueCheck() {
        if (isEmpty()) {
            throw new IllegalStateException(" Queue is empty !");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<T> {

        private Node<T> current = head;
        private boolean alreadyRemoved = true;
        private int index;

        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException(" Next Element Not Exist !");
            }
            T returnValue = current.value;
            current = current.next;
            index++;
            return returnValue;
        }

        @Override
        public void remove() {
            if (!alreadyRemoved) {
                throw new IllegalStateException(" Element Already Removed !");
            }
            if (size == 1) { // one element
                current = null;
            }
            if (index == 0 & size > 1) {  // first element
                current = current.next;
            }
            if (index == size - 1) {  // last element
                while (current.next != null) {
                    current = null;
                }
            } else {
                for (int i = 0; i < size - 1; i++) {
                    if (i == index) {     // find node for delete
                        Node<T> prevNode = head;
                        for (int j = 0; j <= index - 1; j++) {
                            prevNode = prevNode.next;
                            if (j == index - 1) {
                                prevNode = prevNode.next.next;
                                current = null;
                            }
                        }
                    }
                }
            }
            alreadyRemoved = false;
            size--;
        }
    }
}


