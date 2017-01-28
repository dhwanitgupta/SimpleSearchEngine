package searchers;


import dataTypes.Document;

import java.util.List;

public interface Searcher {
    public List<Document> search(String query, Integer topN);
}
