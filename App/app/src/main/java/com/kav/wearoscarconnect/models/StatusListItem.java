package com.kav.wearoscarconnect.models;

public class StatusListItem {
    private String text;
    private int image;

    public StatusListItem(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }
}
