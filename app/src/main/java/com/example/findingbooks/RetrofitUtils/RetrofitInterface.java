package com.example.findingbooks.RetrofitUtils;

import com.example.findingbooks.model.SearchResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("books/v1/volumes")
    Observable<SearchResult> searchForBooks(@Query("q") String searchKey);
}
