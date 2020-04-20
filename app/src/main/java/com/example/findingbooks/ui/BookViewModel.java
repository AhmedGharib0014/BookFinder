package com.example.findingbooks.ui;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findingbooks.data.BookClient;
import com.example.findingbooks.model.BookEntity;
import com.example.findingbooks.model.SearchResult;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookViewModel extends ViewModel {
    MutableLiveData<List<BookEntity>> listMutableLiveData = new MutableLiveData<List<BookEntity>>();


    public  void  search(String key){

        Call<SearchResult> searchResultCall = BookClient.getINSTANCE().searchForBooks(key);
        searchResultCall.enqueue( new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                        listMutableLiveData.setValue(response.body().getBookEntityList());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }



}
