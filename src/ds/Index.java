package ds;

import dataTypes.Document;

import java.util.ArrayList;
import java.util.List;

public class Index {
    private Integer totalDocuments;
    private Trie rootNode;

    public Index(){
        rootNode = new Trie('#');
    }

    public void setTotalDocuments(Integer totalDocuments){
        this.totalDocuments = totalDocuments;
    }

    public Integer getTotalDocuments() { return  totalDocuments; }

    public void indexToken(String token, Document document) {
        Trie rootNodeCopy = rootNode;
       // System.out.println(token);
        for(int i = 0 ; i < token.length(); i++){
            char c = token.charAt(i);
            if(rootNodeCopy.hasChild(c)){
                rootNodeCopy = rootNodeCopy.getChild(c);
            } else {
                rootNodeCopy = rootNodeCopy.addChild(c);
            }
        }
        rootNodeCopy.markLeaf();
        rootNodeCopy.addDocument(document);
    }

    public List<Document> getDocumentsForToken(String token){
        Trie rootNodeCopy = rootNode;
        for(int i = 0; i < token.length(); i++){
            char c = token.charAt(i);
            if(!rootNodeCopy.hasChild(c)) return new ArrayList<>();
            rootNodeCopy = rootNodeCopy.getChild(c);
        }
        if(!rootNodeCopy.getIsLeaf()) return new ArrayList<>();

        return rootNodeCopy.getDocuments();
    }

    public void print() {
        rootNode.print(this.rootNode, "");
    }
}
