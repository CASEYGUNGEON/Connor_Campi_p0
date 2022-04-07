package dev.gungeon.utilities;

public interface MapInterface<K,V> {

    boolean Add(K key, V value);

    void Remove(K key);

    boolean ContainsKey(K key);

    boolean ContainsValue(V value);

    V Get(K key);

    K GetByValue(V value);

    int GetIndex(K key);

    K GetKeyByIndex(int x);

    V GetByIndex(int x);

    boolean Set(K key, V value);

    int Size();
}