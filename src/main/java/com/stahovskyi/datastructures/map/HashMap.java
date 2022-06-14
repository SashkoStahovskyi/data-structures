package com.stahovskyi.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 5;
    private int size;
    private final List<Entry<K, V>>[] buckets;

    public HashMap() {
        buckets = new ArrayList[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList();
        }
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            V returnValue = entry.value;
            entry.value = value;
            return returnValue;
        }
        int index = getIndex(key);
        List<Entry<K, V>> list = buckets[index];
        list.add(new Entry<K, V>(key, value));
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            return entry.value;
        }
        return null;
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
    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            V returnEntry = entry.value;
            buckets[index] = null;
            size--;
            return returnEntry;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (Entry<K, V> value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % buckets.length;
    }

    private Entry<K, V> getEntry(K key) {
        int index = getIndex(key);
        List<Entry<K, V>> list = buckets[index];
        if (list != null) {
            for (Entry<K, V> entry : list) {
                if (Objects.equals(entry.key, key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    static class Entry<K, V> {   // ??? private  ????

        private final K key;
        private V value;

        public V getValue() {
            return value;
        }

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Entry<K, V>> {
        private int bucketIndex;
        private int entryCount;
        private Iterator<Entry<K, V>> bucketIterator;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return entryCount != size;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException(" Not exist next element !");
            }
            while (true) {
                List<Entry<K, V>> currentBucket = buckets[bucketIndex];
                if (currentBucket == null) {
                    bucketIndex++;
                } else {
                    if (bucketIterator == null) {
                        bucketIterator = currentBucket.iterator();
                    }
                    if (!bucketIterator.hasNext()) {
                        bucketIndex++;
                        bucketIterator = null;
                    } else {
                        entryCount++;
                        canRemove = true;
                        return bucketIterator.next();
                    }
                }
            }
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Method Has Already Been Called After The Last Call Or Method Next Not Yet Been Called!");
            }
            bucketIterator.remove();
            canRemove = false;
            entryCount--;
            size--;
        }
    }
}
