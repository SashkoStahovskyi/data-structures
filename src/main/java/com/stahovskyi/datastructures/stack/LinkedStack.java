package com.stahovskyi.datastructures.stack;

import java.util.Iterator;
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
        emptyStackCheck();
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

        public Node(T value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<T> {

        private boolean alreadyRemoved = true;
        private Node<T> current = top;
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
                throw new IllegalStateException(" Already Removed Element ! ");
            }
            if ( size > 1) {  // from first position
                for (int i = size - 1; i >= index + 1; i--) {
                    current = current.next;
                    if (i == index + 1) {
                        current.next = null;
                    }
                }
            }

            if (size == 1) {  // with one element - ok !
                current = null;
                size--;
                return;
            }

            if (index == size - 1) {  // last element
                current.next = top;

            } else {
                for (int i = size - 1; i >= index - 1; i--) {
                    current = current.next;
                    if (i == index - 1) {
                        current = current.next.next;

                    }
                }
            }
            size--;
            alreadyRemoved = false;
        }
    }

}
//removeNode(current);

       /* private void removeNode(Node<T> node) {
            if (node == first) {  // from first position
                current = current.prev;
                current.prev = null;
                size--;
                return;
            }
            if (size == 1) {  // with one element - ok !
                current = first = null;
                size--;
                return;
            }
            if (node == last) {  // last element
                current.prev.next = null;
                current = current.prev;
                size--;
                return;
            } else {
                                      // middle element
                Node<T> prevNode = node.prev;
                Node<T> nextNode = node.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;

            }
            size--;
        }*/




