package dataTypes;

public class Document {

    private final Integer id;
    private final String productId;
    private final String userId;
    private final String profileName;
    private final String helpfulNess;
    private final String score;
    private final String reviewTime;
    private final String summary;
    private final String text;
    private Integer resultScore;

    public Document(final Integer id, final String productId, final String userId, final String profileName,
                    final String helpfulNess, final String score, final  String reviewTime, final String summary,
                    final String text) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.profileName = profileName;
        this.helpfulNess = helpfulNess;
        this.reviewTime = reviewTime;
        this.summary = summary;
        this.text = text;
        this.score = score;
    }

    public Integer getId() { return  id; }

    public String getProductId(){
        return productId;
    }

    public String getUserId(){
        return userId;
    }

    public String getProfileName(){
        return profileName;
    }

    public String getHelpfulNess(){
        return helpfulNess;
    }

    public String getScore() { return  score; }

    public String getReviewTime(){
        return reviewTime;
    }

    public String getSummary(){
        return summary;
    }

    public String getText(){ return text; }

    public void setResultScore(Integer resultScore){
        this.resultScore = resultScore;
    }

    public Integer getResultScore(){
        return this.resultScore;
    }

    @Override
    public String toString(){
        return "{ id: " + id + ", productId: " + productId + ", userId: " + userId + ", profileName: " + profileName +
                ", helpfulness: " + helpfulNess + ", score: " + score + ", reviewTime: " + reviewTime +
                ", summary: " + summary + ", text: " + text + "}";
    }
}
