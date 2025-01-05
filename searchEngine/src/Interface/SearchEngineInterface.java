package Interface;

import java.util.Set;

public interface SearchEngineInterface {
    void preprocessDocuments(String folderPath);
    Set<String> search(String query);
    void printSearchResults(Set<String> results);
}
