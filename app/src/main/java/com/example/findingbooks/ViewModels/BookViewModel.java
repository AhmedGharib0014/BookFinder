package com.example.findingbooks.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findingbooks.Repositories.BookFinderRepo;
import com.example.findingbooks.model.BookEntity;

import java.util.List;

public class BookViewModel extends ViewModel implements BookFinderRepo.ReposatoryInterface{
    MutableLiveData<List<BookEntity>> listMutableLiveData =new MutableLiveData<>();
    private BookFinderRepo bookFinderRepo;



    public void  init(BookViewModel bookViewModel){
        if(bookFinderRepo != null){
            return;
        }
        bookFinderRepo = BookFinderRepo.getInstance();
        bookFinderRepo.setBookViewmodelInstace(bookViewModel);
    }



    public LiveData<List<BookEntity>> getBookEntities(){
        return listMutableLiveData;
    }



    public  void  search(String key){
        bookFinderRepo.searchForBooks(key);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        bookFinderRepo.clearDisposable();
    }

    @Override
    public void OndataRetreived(List<BookEntity> bookEntityList) {
        listMutableLiveData.setValue(bookEntityList);
    }


}
