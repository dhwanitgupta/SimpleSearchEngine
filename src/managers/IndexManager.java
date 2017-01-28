package managers;

import dataTypes.Document;
import ds.Index;
import indexer.Indexer;
import parser.FileParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexManager {

    private final Indexer indexer;
    private final FileParser fileParser;

    public IndexManager(Indexer indexer, FileParser fileParser) {
        this.indexer = indexer;
        this.fileParser = fileParser;
    }

    public Index index(){
        System.out.println("Starting indexing");
        List<Document> documents = getDocuments();
        Index index = indexer.index(documents);
        index.setTotalDocuments(documents.size());
        System.out.println("Successfully indexed " + documents.size() + " documents");
        return index;
    }

    private List<Document> getDocuments() {
        List<Document> documents = new ArrayList<Document>();

        while(true){
            try {
                documents.add(getNextDocument());

            } catch (IOException ex) {
                break;
            }
        }

        return documents;
    }

    private Document getNextDocument() throws IOException {
        return fileParser.getNextDocument();
    }
}
