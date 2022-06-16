package com.stahovskyi.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 5; // when finish change to 10
    private static final int DEFAULT_GROW_FACTOR = 2;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int size;
    private List<Entry<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = new ArrayList[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        growIfNeeded();
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            V returnValue = entry.value;
            entry.value = value;
            return returnValue;
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
        return size == 0;
    }

    @Override
    public V remove(K key) {
        List<Entry<K, V>> bucket = getBucket(key);
        Iterator<Entry<K, V>> iterator = bucket.iterator();
        for (Entry<K, V> entry : this ) {
            Entry<K, V> e = iterator.next();
            if (Objects.equals(e.key, key)) {
                V returnValue = e.value;
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
        for (Entry<K, V> value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

  // @VisibleForTesting  // assertj         // change for private    !!!!!!!!!!!!
     public void growIfNeeded() {
        if (buckets.length * DEFAULT_LOAD_FACTOR < size) {
            int newBucketCount = DEFAULT_INITIAL_CAPACITY * DEFAULT_GROW_FACTOR;
            List<Entry<K, V>>[] newBuckets = new ArrayList[newBucketCount];
            System.arraycopy(buckets, 0, newBuckets, 0, size);
            buckets = newBuckets;
        }
    }

   /* @VisibleForTesting  */        //  <--- ??????
    public int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % buckets.length;
    }

    // annotation
    private Entry<K, V> getEntry(K key) {
        List<Entry<K, V>> list = getBucket(key);
        if (list != null) {                  // need check for null ??
            for (Entry<K, V> entry : list) {
                if (Objects.equals(entry.key, key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    private List<Entry<K, V>> getBucket(K key) {
        int index = getIndex(key);
        return buckets[index];
    }

    static class Entry<K, V> {   // ??? private   override toString ????

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
