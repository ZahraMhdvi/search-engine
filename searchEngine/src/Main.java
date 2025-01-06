import SearchEngine.SearchEngine;
import Interface.SearchEngineInterface;
import DataStructures.InvertedIndex;
import Exception.InvalidInput;
import Exception.LogicalError;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SearchEngineInterface searchEngine = new SearchEngine();

        try {
            searchEngine.preprocessDocuments("C:\\Users\\DataStructure\\EnglishData");

        } catch (Exception e) {
            System.out.println("Error processing files: " + e.getMessage());
            return;
        }

        System.out.println("Enter the number of queries:");
        int queryCount;
        try {
            queryCount = Integer.parseInt(scanner.nextLine());
            if (queryCount <= 0) {
                System.out.println("The number of queries must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid integer.");
            return;
        }

        for (int i = 0; i < queryCount; i++) {
            System.out.println("Enter query " + (i + 1) + ":");
            String query = scanner.nextLine();

            try {
                List<String> results = searchEngine.search(query);

                if (results.isEmpty()) {
                    System.out.println("No results found for query: " + query);
                } else {
                    System.out.println(results.size());
                    for (String r :results){
                        System.out.println(r);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
