package com.example.tech_shop.modules;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String img_url;
    String name;
    String type;
    String description;
    String rating;
    String price;

    public ViewAllModel() {
    }

    public ViewAllModel(String img_url, String name, String type, String description, String rating, String price) {
        this.img_url = img_url;
        this.name = name;
        this.type = type;
        this.description = description;
        this.rating = rating;
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
