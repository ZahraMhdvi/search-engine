package DataStructures;

import Interface.Index;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InvertedIndex implements Index {
    private final Map<String, Set<Integer>> index;

    public InvertedIndex() {
        this.index = new Map<>();
    }

    @Override
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

    @Override
    public Set<Integer> getDocuments(String word) {
        return index.getOrDefault(word, new HashSet<>());
    }

    @Override
    public Set<Integer> getAllDocuments() {
        Set<Integer> allDocuments = new HashSet<>();
        for (Set<Integer> docSet : index.values()) {
            allDocuments.addAll(docSet);
        }
        return allDocuments;
    }
}
