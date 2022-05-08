package com.stahovskyi.datastructures.arraylist;

import java.util.Objects;

public class LinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);
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
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IllegalStateException("Index more or less than size list ! " + " size is : " + size);
        }
        Node newNode = new Node(value);
        if (size == 0) {
            head = tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == size - 1) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        } else {
            Node current = head;
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
    public Object remove(int index) {  // size????
        if (isEmpty()) {
            throw new IllegalStateException(" List is empty !");
        }
        if (index < 0 || index > size) {
            throw new IllegalStateException("Index Out Of Bounds Exception ! " + " Size is : " + size);
        }
        int count = 0;
        if (index == 0) {
            Object object = head.value;
            head = head.next;
            size--;
            return object;
        }
        if (size == 1) {
            Object object = head.value;
            head = tail = null;
            size--;
            return object;
        }
        if (index == size - 1) {
            Object object = tail.value;
            tail = tail.prev;
            size--;
            return object;
        } else {
            Node current = head;
            while (current != null) {
                if (index == count) {
                    Object object = current.value;
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    current.prev = null;
                    current.next = null;
                    size--;
                    return object;
                }
                current = current.next;
                count++;
            }
        }
        return null;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalStateException(" Index more or less than list size ! " + " Size is : " + size);
        }
        if (isEmpty()) {
            throw new IllegalStateException("List is empty !");
        } else {
            int count = 0;
            Node current = head;
            while (current != null) {
                if (index == count) {
                    return current.value; // work different in test  add and add by index & remove
                }
                current = current.next;
                count++;
            }
            return null;
        }
    }

    /* @Override
    public Object set(Object value, int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty ! ");
        }
        Node newValue = new Node(value);
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                Object object = current.value;
                current.value = newValue.value;
                return object;
            }
            current = current.next;
            count++;
        }
        return null;
    } */

    @Override
    public Object set(Object value, int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty ! ");
        }
        Node newValue = new Node(value);
        Node node = (Node) getNode(index).value;
        Object object = node;
        node.value = value;//newValue.value;
        return object;
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
    public boolean contains(Object value) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty ");
        }
        Node current = head;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty !");
        }
        Node current = head;
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
    public int lastIndexOf(Object value) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty !");
        }
        Node current = tail;
        for (int count = size - 1; count >= 0; count--) {
            if (Objects.equals(value, current.value)) {
                return count;
            }
            current = current.prev;
        }
        return -1;
    }

    private Node getNode(int index) {
        if (index < size) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
        return null;
    }
}
