import dataTypes.Document;
import ds.Index;
import indexer.FoodDataIndexer;
import indexer.Indexer;
import managers.IndexManager;
import parser.FileParser;
import parser.FoodDataParser;
import searchers.IndexSearcher;
import tokenator.SimpleTokenator;
import tokenator.Tokenator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Init {
    private static final String FILE_PATH = "finefoods.txt";

    public static void main(String[] args){
        FileParser foodFileParser = null;
        Tokenator simpleTokenator = new SimpleTokenator();
        Indexer documentIndexer = new FoodDataIndexer(simpleTokenator);

        try {
            foodFileParser = new FoodDataParser(FILE_PATH);
        } catch (IOException e) {
            System.out.println("File with path " + FILE_PATH + " not found");
            e.printStackTrace();
        }

        IndexManager indexManager = new IndexManager(documentIndexer, foodFileParser);
        Index foodIndex = indexManager.index();
        //foodIndex.print();
        IndexSearcher indexSearcher = new IndexSearcher(simpleTokenator, foodIndex);

        Scanner sc = new Scanner(System.in);

        while (true) {

            String query = sc.nextLine();
            System.out.println(query);

            List<Document> documents = indexSearcher.search(query, 20);

            documents.stream().forEach(document -> {
                System.out.println(document.getResultScore() + " " + document.getScore() + " " +  document.getText());
            });
        }
    }
}
