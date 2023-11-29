package com.ilkin;


public class Book {
    private int bookID;
    private String title;
    private int authorID;
    private int stockQuantity;

    // Constructor with parameters
    public Book(int bookID, String title, int authorID, int stockQuantity) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.stockQuantity = stockQuantity;
    }

    // Getter and Setter methods for BookID
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    // Getter and Setter methods for Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter methods for AuthorID
    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    // Getter and Setter methods for StockQuantity
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
