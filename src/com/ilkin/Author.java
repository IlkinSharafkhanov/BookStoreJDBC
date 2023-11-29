package com.ilkin;


public class Author {
    private int authorID;
    private String authorName;

    // Constructor with parameters
    public Author(int authorID, String authorName) {
        this.authorID = authorID;
        this.authorName = authorName;
    }

    // Getter and Setter methods for AuthorID
    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    // Getter and Setter methods for AuthorName
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
