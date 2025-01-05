package DataStructures;

import Interface.MapInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Map<K, V> implements MapInterface<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private List<LinkedList<Entry<K, V>>> buckets;
    private int size;

    public Map() {
        buckets = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.size());
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
        size++;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
