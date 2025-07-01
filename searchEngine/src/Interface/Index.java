package Interface;

import DataStructures.Map;
import java.util.List;
import java.util.Set;

public interface Index {
    void buildIndex(Map<Integer, List<String>> cleanedDocuments);
    Set<Integer> getDocuments(String word);
    Set<Integer> getAllDocuments();
}
