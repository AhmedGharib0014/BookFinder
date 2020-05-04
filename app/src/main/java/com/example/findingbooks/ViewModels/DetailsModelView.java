package com.example.findingbooks.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findingbooks.model.BookEntity;

public class DetailsModelView extends ViewModel {
    public MutableLiveData<BookEntity> currentBookEntity = new MutableLiveData<>();
    public  void SetBookEntity(BookEntity bookEntity){
        currentBookEntity.setValue(bookEntity);
    }

}
