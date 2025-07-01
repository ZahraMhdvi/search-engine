package Interface;

import java.util.List;
import DataStructures.Map;

public interface PreprocessorInterface {
    void preprocessDocuments(String folderPath);
    List<String> cleanContent(String content);
    Map<Integer, List<String>> getCleanedDocuments();
}
