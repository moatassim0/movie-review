package com.example.myapplication;

public class ReviewModel {
    String userName;
    int reviewScore;
    String reviewContent;

    public ReviewModel(String userName, int reviewScore, String reviewContent) {
        this.userName = userName;
        this.reviewScore = reviewScore;
        this.reviewContent = reviewContent;
    }

    public String getUserName() {
        return userName;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public String getReviewContent() {
        return reviewContent;
    }
}
