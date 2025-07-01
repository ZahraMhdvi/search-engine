package SearchEngine;

import Interface.PreprocessorInterface;
import DataStructures.Map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Preprocessor implements PreprocessorInterface {
    private final Map<Integer, String> originalDocuments;
    private final Map<Integer, List<String>> cleanedDocuments;

    public Preprocessor() {
        this.originalDocuments = new Map<>();
        this.cleanedDocuments = new Map<>();
    }

    @Override
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
                    System.out.println("Error reading file: " + file.getName());
                }
            }
        }
    }

    @Override
    public List<String> cleanContent(String content) {
        String[] words = content.toLowerCase()
                .replaceAll("[^a-z0-9 ]", " ")
                .split("\\s+");
        return Arrays.asList(words);
    }

    @Override
    public Map<Integer, List<String>> getCleanedDocuments() {
        return cleanedDocuments;
    }
}
