package com.stahovskyi.datastructures.map;

import java.util.Iterator;

public interface Map<K, V> {

    V put (K key,V value);

    V get(K key);

    int size();

    boolean isEmpty();

    V remove (K key);

    boolean containsKey(K key);

    Iterator <HashMap.Entry<K, V>>  iterator();
}
