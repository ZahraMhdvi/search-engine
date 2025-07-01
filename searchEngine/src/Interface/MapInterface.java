package Interface;

public interface MapInterface<K, V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    boolean containsKey(K key);
    int size();
    V getOrDefault(K key, V defaultValue);
}
