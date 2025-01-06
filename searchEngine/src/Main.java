
import SearchEngine.SearchEngine;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SearchEngine searchEngine = new SearchEngine();

        try {
            searchEngine.preprocessDocuments("C:\\Users\\DataStructure\\EnglishData");
        } catch (Exception e) {
            System.out.println("Error processing files: " + e.getMessage());
            return;
        }

        System.out.println("Enter the number of queries:");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Enter query " + (i + 1) + ":");
            String query = scanner.nextLine();

            List<String> results = searchEngine.search(query);
            searchEngine.printSearchResults(results);
        }

    }
}
