import SearchEngine.SearchEngine;
import Exception.InvalidInput;
import Exception.LogicalError;

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

            try {
                List<String> results = searchEngine.search(query);
                if (results.isEmpty()) {
                    System.out.println("No results found");
                } else {
                    System.out.println(results.size());
                    for (String r : results) {
                        System.out.println(r);

                    }

                }
            } catch (InvalidInput | LogicalError e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
