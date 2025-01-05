package DataStructures;

import Interface.MapInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Map<K, V> implements MapInterface<K, V> {
    private List<Node<K, V>> bucket;
    private int size;

    public Map() {
        this.bucket = new ArrayList<>();
        this.size = 0;
    }



    @Override
    public void put(K key, V value) {
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node<>(key, value));
        size++;
    }

    @Override
    public V get(K key) {
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) {
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                bucket.remove(i);
                size--;
                return;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

@Override
    public V getOrDefault(K key, V defaultValue) {
        if (containsKey(key)) {
            return get(key);
        }
        return defaultValue;
    }
}


class Node<K, V> {
    K key;
    V value;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}