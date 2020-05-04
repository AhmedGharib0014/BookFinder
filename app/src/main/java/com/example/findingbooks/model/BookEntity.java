package com.example.findingbooks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/*
all info about the book is in this class

*/

public  class BookEntity {

    @SerializedName("volumeInfo")
    @Expose
    VolumInfo volumInfo ;

    @Override
    public String toString() {
        return "BookEntity{" +
                "volumInfo=" + volumInfo +
                '}';
    }

    public VolumInfo getVolumInfo() {
        return volumInfo;
    }

    public void setVolumInfo(VolumInfo volumInfo) {
        this.volumInfo = volumInfo;
    }
}
