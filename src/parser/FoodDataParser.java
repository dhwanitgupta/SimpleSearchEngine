package parser;

import dataTypes.Document;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

public class FoodDataParser implements FileParser {

    private final String filePath;
    private BufferedReader bufferedReader;
    private Boolean state;
    private Integer documentNumber;

    public FoodDataParser(String filePath) throws IOException {
        this.filePath = filePath;
        this.state = false;
        documentNumber = 0;
        loadFile(filePath);
    }

    private void loadFile(String filePath) throws IOException {
        this.bufferedReader = new BufferedReader(new FileReader(filePath));
    }


    @Override
    public Document getNextDocument() throws IOException {
        String line = bufferedReader.readLine();
        if(line == null ) {
            throw new EOFException();
        }
        documentNumber++;
        String productId, userId, profileName, helpfulness, score, time, summary, text;

        productId = line.split("product/productId: ")[1];
        while(true) {
            line = bufferedReader.readLine();
            if (line.startsWith("review/userId:")) { break; }
            productId += line;
        }
        //System.out.println(productId);
        userId = line.split("review/userId: ")[1];

        while(true) {
            line = bufferedReader.readLine();
            if (line.startsWith("review/profileName:")) { break; }
            userId += line;
        }
        //System.out.println(userId);
        profileName = line.split("review/profileName:")[1];
        while(true) {
            line = bufferedReader.readLine();
            if (line.startsWith("review/helpfulness:")) { break; }
            profileName += line;
        }
        //System.out.println(profileName);
        helpfulness = line.split("review/helpfulness: ")[1];
        while(true) {
            line = bufferedReader.readLine();
            if (line.startsWith("review/score:")) { break; }
            helpfulness += line;
        }

        score = line.split("review/score: ")[1];
        while(true) {
            line = bufferedReader.readLine();
            if (line.startsWith("review/time:")) { break; }
            score += line;
        }

        time = line.split("review/time: ")[1];
        while(true) {
            line = bufferedReader.readLine();
            if (line.startsWith("review/summary:")) { break; }
            time += line;
        }

        summary = line.split("review/summary: ")[1];
        while(true) {
            line = bufferedReader.readLine();
            if (line.startsWith("review/text:")) { break; }
            summary += line;
        }

        text = line.split("review/text: ")[1];

        while(true) {
            line = bufferedReader.readLine();
            if (line.isEmpty()) { break; }
            summary += line;
        }

        return new Document(documentNumber, productId, userId, profileName,
                helpfulness, score, time, summary, text);
    }
}
