package com.stahovskyi.datastructures.map;

public interface Map<K, V> extends Iterable<HashMap.Entry<K, V>> {

    V put(K key, V value);

    V get(K key);

    int size();

    boolean isEmpty();

    V remove(K key);

    boolean containsKey(K key);
}
