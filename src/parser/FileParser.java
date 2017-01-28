package parser;

import dataTypes.Document;

import java.io.IOException;

public interface FileParser {
    public Document getNextDocument() throws IOException;
}
