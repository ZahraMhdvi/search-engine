package DataStructures;
import java.util.*;
import DataStructures.Map;

import DataStructures.*;
import Interface.MapInterface;

public class InvertedIndex {
    private MapInterface<String, List<Integer>> index;

    public InvertedIndex() {
        this.index = new Map<>();
    }

    public void addWord(String word, int documentId) {
        if (!index.containsKey(word)) {
            index.put(word, new ArrayList<>());
        }
        index.get(word).add(documentId);
    }

    public List<Integer> getDocuments(String word) {
        return index.getOrDefault(word, new ArrayList<>());
    }

}
