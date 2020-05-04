package com.example.findingbooks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    @SerializedName("kind")
    @Expose
    String kind;

    @SerializedName("totalItems")
    @Expose
     int totalItems;

    @SerializedName("items")
    @Expose
    List<BookEntity> bookEntityList;

    @Override
    public String toString() {
        return "SearchResult{" +
                "kind='" + kind + '\'' +
                ", totalItems=" + totalItems +
                ", bookEntityList=" + bookEntityList +
                '}';
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }
}
