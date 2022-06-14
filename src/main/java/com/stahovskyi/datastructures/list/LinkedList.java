package com.stahovskyi.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        Node<T> newNode = new Node<>(value);

        if (size == 0) {
            head = tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        } else {
            Node<T> current = head;
            int count = 0;
            while (current != null) {
                if (count == index) {
                    current.prev.next = newNode;
                    newNode.prev = current.prev;
                    newNode.next = current;
                    current.prev = newNode;
                }
                current = current.next;
                count++;
            }
        }
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> removeNode = getNode(index);
        removeNode(removeNode);
        return removeNode.value;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        Node<T> returnValue = getNode(index);
        return returnValue.value;
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        T returnValue = get(index);
        getNode(index).value = value;
        return returnValue;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
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
            if (Objects.equals(current.value, value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        Node<T> current = head;
        int count = 0;
        while (current != null) {
            if (Objects.equals(value, current.value)) {
                return count;
            }
            current = current.next;
            count++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> current = tail;
        for (int count = size - 1; count >= 0; count--) {
            if (Objects.equals(value, current.value)) {
                return count;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[ ", " ]");
        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    private Node<T> getNode(int index) {
        Node<T> current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index large or less than list size ! " + " size is : " + size);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index less than zero or large than last index in list!");
        }
    }

    private void removeNode(Node<T> node) {
        if (size == 1) {
            head = tail = null;
        } else if (node == head) {
            head = node.next;
            head.prev = null;
        } else if (node == tail) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            Node<T> prevNode = node.prev;
            Node<T> nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        size--;
    }

    private static class Node<T> {

        private Node<T> next;
        private Node<T> prev;
        private T value;

        private Node(T value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current = head;
        private boolean canRemove;

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
            return returnValue;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Method Has Already Been Called After The Last Call Or Method Next Not Yet Been Called!");
            }
            removeNode(current);
            canRemove = false;
        }
    }
}

