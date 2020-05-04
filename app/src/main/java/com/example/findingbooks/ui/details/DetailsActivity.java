package com.example.findingbooks.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Guideline;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.findingbooks.R;
import com.example.findingbooks.ViewModels.DetailsModelView;
import com.example.findingbooks.model.BookEntity;
import com.example.findingbooks.model.VolumInfo;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DetailsActivity";
    DetailsModelView detailsModelView;
    BookEntity mBookEntity;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.detailsTitle)
    TextView detailsTitle;
    @BindView(R.id.detailsAuthors)
    TextView detailsAuthors;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.previewLink)
    TextView previewLink;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.detailes_toolbar)
    Toolbar detailesToolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.guideline3)
    Guideline guideline3;
    @BindView(R.id.guideline)
    Guideline guideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        mBookEntity = new BookEntity();
        detailsModelView = ViewModelProviders.of(this).get(DetailsModelView.class);
        previewLink.setOnClickListener(this);
        toolBarInit();


        Intent intent = getIntent();
        //deSerialsize  data for particular bookEntity and send it to modelView to be LiveData
        //so when rotaion it still displayed
        if (intent != null) {
            Gson gson = new Gson();
            String jSonString = intent.getStringExtra("details");
            BookEntity bookEntity = gson.fromJson(jSonString, BookEntity.class);
            if (bookEntity != null) {
                detailsModelView.SetBookEntity(bookEntity);
            }
        }

        detailsModelView.currentBookEntity.observe(this, new Observer<BookEntity>() {
            @Override
            public void onChanged(BookEntity bookEntity) {
                expandData(bookEntity);
                mBookEntity = bookEntity;
            }
        });

    }

    //init the tool bar
    private void toolBarInit() {
        setSupportActionBar(detailesToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        detailesToolbar.setTitle("Book Details");

    }



    //expand data ov views of the detainls activity
    public void expandData(BookEntity bookEntity) {
        VolumInfo volumInfo = bookEntity.getVolumInfo();
        String bookTitle = "";
        String descriptionText = "there is no discription";
        String imageUrl = null;
        String authers ="Authors unknown";

        image.setVisibility(View.VISIBLE);
        ratingBar.setVisibility(View.VISIBLE);



        if (volumInfo != null) {
            if (volumInfo.getTitle() != null) {
                bookTitle = volumInfo.getTitle();
            }

            if (volumInfo.getAuthors() != null) {
                for (String auther : volumInfo.getAuthors()) {
                    if(authers.equals("Authors unknown")){
                        authers = auther;
                    }else{
                        authers += "  " + auther;
                    }
                }
            }
            if (volumInfo.getDescription() != null) {
                descriptionText = volumInfo.getDescription();
            }

            if (volumInfo.getImageLinks().getSmallThumbnail() != null) {
                imageUrl = volumInfo.getImageLinks().getSmallThumbnail();
                Picasso.get().load(imageUrl).into(image);
            } else if (volumInfo.getImageLinks().getThumbnail() != null) {
                imageUrl = volumInfo.getImageLinks().getThumbnail();
                Picasso.get().load(imageUrl).into(image);
            } else {
                image.setVisibility(View.GONE);
            }

            detailsTitle.setText(bookTitle);
            detailsAuthors.setText(authers);
            description.setText(descriptionText);


            if (bookEntity.getVolumInfo().getAverageRating() != null) {
                ratingBar.setRating(Float.parseFloat(bookEntity.getVolumInfo().getAverageRating()));
            } else {
                ratingBar.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    //when the user need more info about the book , write review or by it
    //the user will be redirected to the volume page on goolge by any browser
    @Override
    public void onClick(View v) {
        String url = null;
        VolumInfo volumInfo = mBookEntity.getVolumInfo();
        if (v.getId() == R.id.previewLink) {
            if (volumInfo.getPreviewLink() != null) url = volumInfo.getPreviewLink();
        }

        if (url != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


}

