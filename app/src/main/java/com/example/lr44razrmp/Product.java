package com.example.lr44razrmp;


public class Product {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;


    public Product(int id, String title, double price, String description,
                   String category, String image, Rating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getImage() { return image; }
    public Rating getRating() { return rating; }
}

class Rating {
    private double rate;
    private int count;

    public Rating(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }

    public double getRate() { return rate; }
    public int getCount() { return count; }
}
