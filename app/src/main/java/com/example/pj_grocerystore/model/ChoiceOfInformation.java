package com.example.pj_grocerystore.model;

public class ChoiceOfInformation {
    private int image;
    private String title1;
    private String title2;

    public ChoiceOfInformation(int image, String title1, String title2) {
        this.image = image;
        this.title1 = title1;
        this.title2 = title2;
    }

    public int getImage() {
        return image;
    }

    public String getTitle1() {
        return title1;
    }

    public String getTitle2() {
        return title2;
    }
}
