package SearchEngine;

import DataStructures.InvertedIndex;
import Interface.SearchEngineInterface;
import Exception.InvalidInput;
import Exception.LogicalError;

import java.util.*;

public class SearchEngine implements SearchEngineInterface {
    private final InvertedIndex invertedIndex;
    private final Preprocessor preprocessor;

    public SearchEngine() {
        this.invertedIndex = new InvertedIndex();
        this.preprocessor = new Preprocessor();
    }

    @Override
    public void preprocessDocuments(String folderPath) {
        preprocessor.preprocessDocuments(folderPath);
        invertedIndex.buildIndex(preprocessor.getCleanedDocuments());
    }

    @Override
    public List<String> search(String query) throws InvalidInput, LogicalError {
        validateQuery(query);

        Set<Integer> mustInclude = null;
        Set<Integer> atLeastOneInclude = new HashSet<>();
        Set<Integer> exclude = new HashSet<>();

        String[] terms = query.split("\\s+");
        for (String term : terms) {
            if (term.startsWith("+")) {
                String cleanTerm = term.substring(1).toLowerCase();
                Set<Integer> docsWithWord = invertedIndex.getDocuments(cleanTerm);
                if (docsWithWord.isEmpty()) {
                    return Collections.emptyList();
                }
                atLeastOneInclude.addAll(docsWithWord);
            } else if (term.startsWith("-")) {
                String cleanTerm = term.substring(1).toLowerCase();
                exclude.addAll(invertedIndex.getDocuments(cleanTerm));
            } else {
                String cleanTerm = term.toLowerCase();
                Set<Integer> docsWithWord = invertedIndex.getDocuments(cleanTerm);
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

        Set<Integer> results = (mustInclude != null) ? new HashSet<>(mustInclude) : new HashSet<>(invertedIndex.getAllDocuments());

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

    public void validateQuery(String query) throws InvalidInput, LogicalError {
        if (query == null || query.isEmpty()) {
            throw new InvalidInput();
        }

        Map<String, Set<String>> termOperators = new HashMap<>();
        String[] terms = query.split("\\s+");

        for (String term : terms) {
            String cleanTerm;
            String operator;

            if (term.startsWith("+")) {
                cleanTerm = term.substring(1).toLowerCase();
                operator = "+";
            } else if (term.startsWith("-")) {
                cleanTerm = term.substring(1).toLowerCase();
                operator = "-";
            } else {
                cleanTerm = term.toLowerCase();
                operator = "no_operator";
            }

            if (!cleanTerm.matches("[a-zA-Z0-9]+")) {
                throw new InvalidInput();
            }

            termOperators.putIfAbsent(cleanTerm, new HashSet<>());
            termOperators.get(cleanTerm).add(operator);
        }

        for (Map.Entry<String, Set<String>> entry : termOperators.entrySet()) {
            Set<String> operators = entry.getValue();
            if (operators.size() > 1) {
                throw new LogicalError();
            }
        }
    }

}
