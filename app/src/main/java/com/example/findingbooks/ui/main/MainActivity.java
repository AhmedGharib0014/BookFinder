package com.example.findingbooks.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.findingbooks.ViewModels.BookViewModel;
import com.example.findingbooks.ui.details.DetailsActivity;
import com.example.findingbooks.R;
import com.example.findingbooks.model.BookEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookAdapter.OnBookClickListner {
    private static final String TAG = MainActivity.class.getSimpleName();
    InputMethodManager inputMethodManager;
    BookViewModel bookViewModel;
    List<BookEntity> mBookEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        bookViewModel.init(bookViewModel);
        mBookEntities = new ArrayList<>();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        BookAdapter bookAdapter = new BookAdapter(this);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        bookViewModel.getBookEntities().observe(this, new Observer<List<BookEntity>>() {
            @Override
            public void onChanged(List<BookEntity> bookEntities) {
                bookAdapter.setData(bookEntities);
                mBookEntities=bookEntities;
            }
        });

        Intent intent = getIntent();
        if(intent != null){
            String query = intent.getStringExtra("query");
            if(query != null) {
                if(isNetworkAvailable()) bookViewModel.search(query);
                else Toast.makeText(this, "check your network connection", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "enter a valid book name  to search for", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                bookViewModel.search(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBookitemClicked(int position) {
        Gson gson = new Gson();
        String JsonBookEntity = gson.toJson(mBookEntities.get(position));
        Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
        intent.putExtra("details", JsonBookEntity);
        startActivity(intent);

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




}
