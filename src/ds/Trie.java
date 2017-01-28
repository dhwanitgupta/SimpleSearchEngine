package ds;

import dataTypes.Document;

import java.util.*;

public class Trie {

    private static final Integer CHILDS = 37;
    private List<Trie> childs;
    private Boolean isLeaf;
    private List<Document> documents;
    private final char nodeValue;

    public Trie(char nodeValue){
        this.nodeValue = nodeValue;
        childs = new ArrayList<>();
        for(int i = 0; i < CHILDS; i++){
            childs.add(null);
        }
        isLeaf = false;
        documents = new ArrayList<>();
    }

    public void markLeaf(){
        isLeaf = true;
    }

    public void addDocument(Document document){
        documents.add(document);
    }

    public Trie addChild(char c){
        childs.set(getIndex(c), new Trie(c));
        return childs.get(getIndex(c));
    }

    public Boolean hasChild(char c){
        if(childs.get(getIndex(c)) == null) return false;
        return true;
    }

    public Trie getChild(char c){
        return childs.get(getIndex(c));
    }

    public Trie getChildByIndex(int index){
        return childs.get(index);
    }

    public List<Document> getDocuments() { return documents; }

    public char getNodeValue(){ return nodeValue; }

    public Boolean getIsLeaf() { return  isLeaf; }

    public void print(Trie node, String value){
        if(node == null) return;

        if(node.getIsLeaf()) {
            System.out.println(value + " -> " + node.getDocumentIds().toString());
        }

        for(int i = 0; i < CHILDS; i++){
            Trie child = node.getChildByIndex(i);
            if (child == null) continue;
            print(child, value + child.getNodeValue());
        }
    }

    private Integer getIndex(char c){
        if(c >= '0' && c <= '9') return  (int)(c - '0');
        return ((int)c - (int)'a') + 10;
    }

    private List<Integer> getDocumentIds(){
        List<Integer> documentIds = new ArrayList<>();
        for (Document document:documents
             ) {
            documentIds.add(document.getId());
        }
        return documentIds;
    }
}
