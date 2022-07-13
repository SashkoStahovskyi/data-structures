package com.stahovskyi.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.65;
    private static final int DEFAULT_GROW_FACTOR = 2;
    private List<Entry<K, V>>[] buckets;
    private int size;


    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = new ArrayList[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(1);
        }
    }

    @Override
    public V put(K key, V value) {
        growIfNeeded();
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        }

        List<Entry<K, V>> list = getBucket(key);
        list.add(new Entry<>(key, value));
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
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
    public V remove(K key) {
        List<Entry<K, V>> bucket = getBucket(key);
        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();

            if (Objects.equals(entry.getKey(), key)) {
                V returnValue = entry.getValue();
                iterator.remove();
                size--;
                return returnValue;
            }
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
        for (Map.Entry<K, V> value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    private void growIfNeeded() {
        if (buckets.length * DEFAULT_LOAD_FACTOR < size) {
            List<Entry<K, V>>[] newBuckets = createNewBuckets();
            innerPut(newBuckets);
            buckets = newBuckets;
        }
    }

    private void innerPut(List<Entry<K, V>>[] newBuckets) {
        for (List<Entry<K, V>> list : buckets) {
            for (Entry<K, V> entry : list) {
                int index = getIndexForNewBuckets(entry, newBuckets);
                newBuckets[index].add(entry);
            }
        }
    }

    private List<Entry<K, V>>[] createNewBuckets() {
        int newBucketCount = DEFAULT_INITIAL_CAPACITY * DEFAULT_GROW_FACTOR;
        @SuppressWarnings("unchecked")
        List<Entry<K, V>>[] newBuckets = new ArrayList[newBucketCount];

        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = new ArrayList<>(1);
        }
        return newBuckets;
    }

    private int getIndexForNewBuckets(Entry<K, V> entry, List<Entry<K, V>>[] newBuckets) {
        K key = entry.getKey();
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(hashCode) % newBuckets.length;
    }

    private int getBucketsIndex(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(hashCode) % buckets.length;
    }

    private Entry<K, V> getEntry(K key) {
        if (key == null) {
            List<Entry<K, V>> list = buckets[0];
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    if (entry.getKey() == null) {
                        return entry;
                    }
                }
            }
        }
        List<Entry<K, V>> list = getBucket(key);
        if (list != null) {
            for (Entry<K, V> entry : list) {
                if (Objects.equals(entry.getKey(), key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    private List<Entry<K, V>> getBucket(K key) {
        int index = getBucketsIndex(key);
        return buckets[index];
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {

        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public String toString() {
            return ""
                    + "Key='" + key + '\''
                    + ", Value=" + value;
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
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
                throw new NoSuchElementException(" Next Element Not Exist ! ");
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
