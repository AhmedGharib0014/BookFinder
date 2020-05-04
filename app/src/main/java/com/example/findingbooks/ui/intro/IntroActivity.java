package com.example.findingbooks.ui.intro;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.findingbooks.R;
import com.example.findingbooks.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {
    @BindView(R.id.searchView)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro3);
        ButterKnife.bind(this);

        //init the SearchView on the toolbar
        searchView.setIconified(false);
        searchView.setQueryHint("searsh for your book");
        searchView.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        //take the qaury key to the main activity if it is a valid string and the user connect to the network
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && query.length() > 0) {
                    if (isNetworkAvailable()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("query", query);
                        startActivity(intent);
                    } else {
                        Toast.makeText(IntroActivity.this, "check your network connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(IntroActivity.this, "enter a valid book name  to search for", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    //check the network connection
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
