package indexer;

import dataTypes.Document;
import ds.Index;
import tokenator.Tokenator;

import java.util.List;
import java.util.Set;

public class FoodDataIndexer implements Indexer {
    private Index index;
    private final Tokenator tokenator;

    public FoodDataIndexer(Tokenator tokenator){
        this.index = new Index();
        this.tokenator = tokenator;
    }
    @Override
    public Index index(List<Document> documentList)  {
        for(int i = 0; i < documentList.size(); i++){
            System.out.println("Indexing " + i + " document");
            indexDocument(documentList.get(i));
        }

        index.setTotalDocuments(documentList.size());
        return index;
    }

    private void indexDocument(Document document) {
        Set<String> tokens = tokenator.getTokens(document.getText());
        if(tokens.size() == 0) return;

        addTokensInIndex(tokens, document);

    }

    private void addTokensInIndex(Set<String> tokens, Document document) {
        tokens.stream().forEach(token -> {
            this.index.indexToken(token, document);
        });
    }
}
