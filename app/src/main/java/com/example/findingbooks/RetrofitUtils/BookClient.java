package com.example.findingbooks.RetrofitUtils;

import com.example.findingbooks.model.SearchResult;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookClient {
    private static final String BASE_URL="https://www.googleapis.com/";
    private static BookClient INSTANCE = null;
    private  RetrofitInterface retrofitInterface;

    //init the retrofit and its interface
    public BookClient() {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    //insure that it is a singele tone class ..no nead for new  instance for each API hit ,one is enough
    public static BookClient getINSTANCE() {
        if(null==INSTANCE) {
            INSTANCE=new BookClient();
        }
        return INSTANCE;
    }


    //searchForBooks to hit google book Api and search for data
    public Observable<SearchResult> searchForBooks(String key){
        return retrofitInterface.searchForBooks(key);
    }



}
