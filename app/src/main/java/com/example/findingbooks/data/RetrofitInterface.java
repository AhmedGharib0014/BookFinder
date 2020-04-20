package com.example.findingbooks.data;

import com.example.findingbooks.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("books/v1/volumes")
    Call<SearchResult> searchForBooks(@Query("q") String searchKey);
}
