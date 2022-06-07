package com.stahovskyi.datastructures.arraylist;

import java.util.Iterator;
import java.util.Objects;

public class DoubleLinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        Node<T> newNode = new Node(value);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        addNode(value, index);
    }

    @Override
    public T remove(int index) {
        emptyListCheck();
        validateIndex(index);
        Node<T> node = getNode(index);
        if (node != null) {
            T returnValue = node.value;
            removeNode(node);
            return returnValue;
        }
        return null;
    }

    @Override
    public T get(int index) {
        emptyListCheck();
        validateIndex(index);
        Node<T> node = getNode(index);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    @Override
    public T set(T value, int index) {
        emptyListCheck();
        Node<T> node = getNode(index);
        if (node != null) {
            T returnValue = node.value;
            node.value = value;
            return returnValue;
        }
        return null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
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
        emptyListCheck();
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
        emptyListCheck();
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
        emptyListCheck();
        Node<T> current = tail;
        for (int count = size - 1; count >= 0; count--) {
            if (Objects.equals(value, current.value)) {
                return count;
            }
            current = current.prev;
        }
        return -1;
    }

    private Node<T> getNode(int index) {
        if (index <= size / 2) {
            Node<T> current = head;
            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    return current;
                }
                current = current.next;
            }
        } else if (index >= size / 2) {
            Node<T> current = tail;
            for (int i = size - 1; i >= index / 2; i--) {
                if (i == index) {
                    return current;
                }
                current = current.prev;
            }
        }
        return null;
    }

    private void emptyListCheck() {
        if (isEmpty()) {
            throw new IllegalStateException(" List is empty !");
        }
    }

    public void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalStateException("Index more or less than list size ! " + " size is : " + size);
        }
    }

    private void addNode(T value, int index) {
        Node<T> newNode = new Node(value);
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

    private void removeNode(Node<T> node) {
        if (node == head & size > 1) {
            head = head.next;
            head.prev = null;
            size--;
            return;
        }
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        if (node == tail) {
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return;
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
        T value;

        public Node(T value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        private boolean alreadyRemoved = true;

        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException(" Next Element Not Exist !");
            } else {
                T returnValue = current.value;
                current = current.next;
                return returnValue;
            }
        }

        @Override
        public void remove() {
            if(!alreadyRemoved) {
                throw new IllegalStateException(" Element Already Removed !");
            }
            removeNode(current);
            alreadyRemoved = false;
        }
    }
}

