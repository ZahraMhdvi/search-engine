package Interface;

import java.util.List;

public interface SearchEngineInterface {
    void preprocessDocuments(String folderPath);
    List<String> search(String query) throws Exception;
}
