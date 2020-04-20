package com.example.findingbooks.data;

import com.example.findingbooks.model.SearchResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookClient {
    private static final String BASE_URL="https://www.googleapis.com/";
    private static BookClient INSTANCE = null;
    private  RetrofitInterface retrofitInterface;


    public BookClient() {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }


    public static BookClient getINSTANCE() {
        if(null==INSTANCE) {
            INSTANCE=new BookClient();
        }
        return INSTANCE;
    }



    public Call<SearchResult> searchForBooks(String key){
        return retrofitInterface.searchForBooks(key);
    }



}
