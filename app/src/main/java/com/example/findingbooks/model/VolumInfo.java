package com.example.findingbooks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumInfo {
    @SerializedName("title")
            @Expose
    String title ;

    @SerializedName("authors")
    @Expose
    List<String> authors;

    @SerializedName("publisher")
    @Expose
    String publisher;

    @SerializedName("imageLinks")
    @Expose
    ImageLinks imageLinks ;

    @SerializedName("previewLink")
    @Expose
    String previewLink ;

    @SerializedName("infoLink")
    @Expose
    String infoLink ;


    @SerializedName("description")
    @Expose
    String description ;


    @SerializedName("averageRating")
    @Expose
    String averageRating ;

    @Override
    public String toString() {
        return "VolumInfo{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", imageLinks=" + imageLinks +
                ", previewLink='" + previewLink + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", description='" + description + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }




    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }


    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
