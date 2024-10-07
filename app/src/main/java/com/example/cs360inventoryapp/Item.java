package com.example.cs360inventoryapp;

import android.net.Uri;

public class Item {
    private String name;
    private String description;
    private int image;

    public Item(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getImage() {
        return this.image;
    }
}
