package com.example.findingbooks.Repositories;

import android.util.Log;

import com.example.findingbooks.RetrofitUtils.BookClient;
import com.example.findingbooks.model.BookEntity;
import com.example.findingbooks.model.SearchResult;
import com.example.findingbooks.ViewModels.BookViewModel;

import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;



public class BookFinderRepo {

    public  static  BookFinderRepo bookFinderRepo;
    public  BookViewModel mBookViewModel;
    public CompositeDisposable disposable= new CompositeDisposable();

    public static  BookFinderRepo getInstance(){
        if(bookFinderRepo == null){
            return  new  BookFinderRepo();
        }
        else{
            return bookFinderRepo;
        }
    }

    public void setBookViewmodelInstace(BookViewModel bookViewModel){
        mBookViewModel=bookViewModel;
    }



    public  void searchForBooks(String qaury){

          BookClient.getINSTANCE().searchForBooks(qaury)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                            disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull SearchResult searchResult) {
                        mBookViewModel.OndataRetreived(searchResult.getBookEntityList());
                        Log.i("TAG",searchResult.getBookEntityList().get(0).getVolumInfo().toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("TAG", "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void clearDisposable(){
        disposable.clear();
    }


    public  interface  ReposatoryInterface{
        void OndataRetreived(List<BookEntity> boohEntityList);
    }

}
