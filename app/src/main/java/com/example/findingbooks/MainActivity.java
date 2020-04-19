package com.example.findingbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.findingbooks.model.SearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL="https://www.googleapis.com/";
    private static final String TAG = MainActivity.class.getSimpleName();
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchButton = (Button) findViewById(R.id.button);
        EditText searchEditText = (EditText) findViewById(R.id.editText);
        initializeRetRofit();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQaury = searchEditText.getText().toString();

                Call<SearchResult> call = retrofitInterface.searchForBooks(searchQaury);
                call.enqueue(new Callback<SearchResult>() {
                    @Override
                    public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                        Log.i(TAG,response.body().toString());
                    }
                    @Override
                    public void onFailure(Call<SearchResult> call, Throwable t) {
                        Log.i(TAG, "error");

                    }
                });
            }
        });
    }



    public void initializeRetRofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }
}
