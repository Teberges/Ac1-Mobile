package com.example.ac1;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String title;
    private String author;
    private String category;
    private boolean completed;

    public Book(String title, String author, String category, boolean completed) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}