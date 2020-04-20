package com.example.findingbooks.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.findingbooks.R;
import com.example.findingbooks.data.BookClient;
import com.example.findingbooks.model.BookEntity;
import com.example.findingbooks.model.SearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchButton = (Button) findViewById(R.id.button);
        EditText searchEditText = (EditText) findViewById(R.id.editText);


        BookViewModel bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        BookAdapter bookAdapter = new BookAdapter();
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        bookViewModel.listMutableLiveData.observe(this, new Observer<List<BookEntity>>() {
            @Override
            public void onChanged(List<BookEntity> bookEntities) {
                bookAdapter.setData(bookEntities);
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookViewModel.search(searchEditText.getText().toString());
            }
        });
    }




}
