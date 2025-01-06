package SearchEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import DataStructures.Map;

public class Preprocessor {
    private Map<Integer, String> originalDocuments;
    private Map<Integer, List<String>> cleanedDocuments;

    public Preprocessor() {
        this.originalDocuments = new Map<>();
        this.cleanedDocuments = new Map<>();
    }

    public void preprocessDocuments(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.matches("\\d+\\.txt"));

        if (files != null) {
            for (File file : files) {
                try {
                    int documentId = Integer.parseInt(file.getName().replace(".txt", ""));
                    String content = Files.readString(file.toPath());
                    originalDocuments.put(documentId, content);
                    cleanedDocuments.put(documentId, cleanContent(content));
                } catch (IOException e) {
                    System.err.println("Error reading file: " + file.getName());
                }
            }
        }
    }

    private List<String> cleanContent(String content) {
        String[] words = content.toLowerCase()
                .replaceAll("[^a-z0-9 ]", " ")
                .split("\\s+");
        return Arrays.asList(words);
    }

    public Map<Integer, String> getOriginalDocuments() {
        return originalDocuments;
    }

    public Map<Integer, List<String>> getCleanedDocuments() {
        return cleanedDocuments;
    }
}
