package searchers;

import dataTypes.Document;
import ds.Index;
import tokenator.Tokenator;

import java.util.*;

public class IndexSearcher implements Searcher {

    private final Tokenator tokenator;
    private final Index index;
    private HashMap<Document, Integer> documentCountMap;
    public IndexSearcher(Tokenator tokenator, Index index) {
        this.tokenator = tokenator;
        this.index = index;
    }

    @Override
    public List<Document> search(String query, Integer topN) {
        documentCountMap = new HashMap<Document, Integer>();
        documentCountMap.clear();
        Set<String> queryTokens = tokenator.getTokens(query);
        queryTokens.stream().forEach(token -> {
            searchForToken(token);
        });
        return sortMap(topN);
    }

    private List<Document> sortMap(Integer topN) {
        List<Document> resultList = new ArrayList<>();
        Comparator<Document> comparator = new ValueComparator(documentCountMap);
        TreeMap<Document, Integer> result = new TreeMap<Document, Integer>(comparator);
        result.putAll(documentCountMap);

        for (HashMap.Entry<Document, Integer> entry : result.entrySet()) {
            entry.getKey().setResultScore(entry.getValue());
            resultList.add(entry.getKey());
            if(resultList.size() == topN) break;
        }

        return resultList;
    }

    private void searchForToken(String token) {
        List<Document> matchDocuments = index.getDocumentsForToken(token);
        matchDocuments.stream().forEach(document -> {
            if(!documentCountMap.containsKey(document)){
                documentCountMap.put(document, 0);
            }
            documentCountMap.put(document, documentCountMap.get(document) + 1);
        });
    }


}

class ValueComparator implements Comparator<Document> {
    HashMap<Document, Integer> map = new HashMap<Document, Integer>();

    public ValueComparator(HashMap<Document, Integer> map){
        this.map.putAll(map);
    }

    @Override
    public int compare(Document doc1, Document doc2) {
        if(map.get(doc1) == map.get(doc2)){
            if(Double.parseDouble(doc1.getScore()) > Double.parseDouble(doc2.getScore())) return -1;
            return 1;
        }

        if(map.get(doc1) > map.get(doc2)) return -1;

        return 1;

    }
}

