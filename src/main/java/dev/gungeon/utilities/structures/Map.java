package dev.gungeon.utilities.structures;

public class Map<K,V> implements MapInterface<K,V> {
    private K[] keys;
    private V[] values;
    private int size;

    public Map(){
        keys = (K[]) new Object[10];
        values = (V[]) new Object[10];
    }

    public Map(int size){
        keys = (K[]) new Object[size];
        values = (V[]) new Object[size];
    }

    public boolean Add(K key, V value) {
        int x = GetIndex(key);
        if(x != -1)
            return false;

        if(size == keys.length) {
            K[] newKeys = (K[]) new Object[keys.length * 2];
            V[] newValues = (V[]) new Object[values.length * 2];
            for(int i = 0; i < size; i++) {
                newKeys[i] = keys[i];
                newValues[i] = values[i];
            }
            keys = newKeys;
            values = newValues;
        }
        keys[size] = key;
        values[size++] = value;

        return true;
    }

    public void Remove(K key) {
        int ind = -1;
        for(int i = 0; i < size; i++) {
            if(keys[i] == key) {
                for(int j = i+1; j < size;j++) {
                    keys[j-1] = keys[j];
                    values[j-1] = values[j];
                }
                size--;
            }
        }
    }

    public boolean ContainsKey(K key) {
        for(int i = 0; i < size; i++) {
            if(keys[i] == key)
                return true;
        }
        return false;
    }

    public boolean ContainsValue(V value) {
        for(int i = 0; i < size; i++) {
            if(values[i] == value)
                return true;
        }
        return false;
    }

    public V Get(K key) {
        int ind = GetIndex(key);
        if(ind != -1) {
            return values[ind];
        }
        return null;
    }

    public K GetByValue(V value) {
        for(int i = 0; i < size; i++) {
            if(values[i] == value)
                return keys[i];
        }
        return null;
    }

    public int GetIndex(K key) {
        for (int i = 0; i < size; i++) {
            if(keys[i] == key)
                return i;
        }
        return -1;
    }

    public K GetKeyByIndex(int x) {
        return keys[x];
    }

    public V GetByIndex(int x) {
        return values[x];
    }

    public boolean Set(K key, V value) {
        int x = GetIndex(key);
        if(x != -1) {
            values[x] = value;
            return true;
        }
        return Add(key, value);
    }

    public int Size() {
        return size;
    }
}
