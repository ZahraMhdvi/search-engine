package SearchEngine;

import DataStructures.InvertedIndex;
import SearchEngine.Preprocessor;

import java.util.*;

public class SearchEngine {
    private InvertedIndex invertedIndex;

    public SearchEngine() {
        this.invertedIndex = new InvertedIndex();
    }

    public void preprocessDocuments(String folderPath) {
        Preprocessor preprocessor = new Preprocessor();
        preprocessor.preprocessDocuments(folderPath);
        invertedIndex.buildIndex(preprocessor.getCleanedDocuments());
    }

    public List<String> search(String query) {
        Set<Integer> mustInclude = null;
        Set<Integer> atLeastOneInclude = new HashSet<>();
        Set<Integer> exclude = new HashSet<>();

        String[] terms = query.split("\\s+");
        for (String term : terms) {
            if (term.startsWith("+")) {
                Set<Integer> docsWithWord = invertedIndex.getDocuments(term.substring(1).toLowerCase());
                if (docsWithWord.isEmpty()) {

                    return Collections.emptyList();
                }
                atLeastOneInclude.addAll(docsWithWord);
            } else if (term.startsWith("-")) {
                exclude.addAll(invertedIndex.getDocuments(term.substring(1).toLowerCase()));
            } else {
                Set<Integer> docsWithWord = invertedIndex.getDocuments(term.toLowerCase());
                if (docsWithWord.isEmpty()) {

                    return Collections.emptyList();
                }
                if (mustInclude == null) {
                    mustInclude = new HashSet<>(docsWithWord);
                } else {
                    mustInclude.retainAll(docsWithWord);
                }
            }
        }


        Set<Integer> results;
        if (mustInclude != null) {
            results = new HashSet<>(mustInclude);
        } else {
            results = new HashSet<>(invertedIndex.getAllDocuments());
        }


        if (!atLeastOneInclude.isEmpty()) {
            results.retainAll(atLeastOneInclude);
        }

        results.removeAll(exclude);

        List<Integer> sortedResults = new ArrayList<>(results);
        Collections.sort(sortedResults);

        List<String> formattedResults = new ArrayList<>();
        for (int docId : sortedResults) {
            formattedResults.add(String.valueOf(docId));
        }

        return formattedResults;
    }

    public void printSearchResults(List<String> results) {
        if (results.isEmpty()) {
            System.out.println("0\nNo documents found");
        } else {
            System.out.println(results.size());
            results.forEach(System.out::println);
        }
    }
}
