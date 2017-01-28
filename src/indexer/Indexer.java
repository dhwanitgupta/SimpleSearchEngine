package indexer;

import dataTypes.Document;
import ds.Index;

import java.util.List;

public interface Indexer {
    public Index index(List<Document> documentList);
}
