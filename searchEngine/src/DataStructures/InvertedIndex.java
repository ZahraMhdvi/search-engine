package DataStructures;

import java.util.*;

public class InvertedIndex {
    private Map<String, Set<Integer>> index;

    public InvertedIndex() {
        this.index = new Map<>();
    }

    public void buildIndex(Map<Integer, List<String>> cleanedDocuments) {
        for (Integer documentId : cleanedDocuments.keySet()) {
            List<String> words = cleanedDocuments.get(documentId);
            for (String word : words) {
                if (!index.containsKey(word)) {
                    index.put(word, new HashSet<>());
                }
                index.get(word).add(documentId);
            }
        }
    }

    public Set<Integer> getDocuments(String word) {
        return index.getOrDefault(word, new HashSet<>());
    }

    public Set<Integer> getAllDocuments() {
        Set<Integer> allDocuments = new HashSet<>();
        for (Set<Integer> docSet : index.values()) {
            allDocuments.addAll(docSet);
        }
        return allDocuments;
    }
}
