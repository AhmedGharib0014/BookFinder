package com.example.findingbooks.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.findingbooks.R;
import com.example.findingbooks.model.BookEntity;
import com.example.findingbooks.model.VolumInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    OnBookClickListner mOnBookClickListner;
    private ArrayList<BookEntity> bookEntities =new ArrayList<BookEntity>();;

    public BookAdapter(OnBookClickListner mOnBookClickListner) {
        this.mOnBookClickListner = mOnBookClickListner;
    }

    @NonNull
    @Override

    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false),mOnBookClickListner);
    }


    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        VolumInfo volumInfo = bookEntities.get(position).getVolumInfo();
        String title = volumInfo.getTitle();
        String imageUrls= volumInfo.getImageLinks().getSmallThumbnail();
        String authers ="";
        String descriptionText = volumInfo.getDescription();
        if(volumInfo.getAuthors() != null && volumInfo.getAuthors().size() >0) {
            for (String auther : volumInfo.getAuthors()) {
                if(authers.equals("")){
                    authers +=auther;
                }else{
                    authers += "  " + auther;
                }
            }

        }
        if(title != null){
            holder.bookTitle.setText(title);
        }
        if(authers != null) {
            holder.bookAuthors.setText(authers);
        }
        if(imageUrls != null) {
            Picasso.get().load(imageUrls).into(holder.bookImage);
        }
        if(descriptionText != null){
            if(descriptionText.length() > 200) {
                descriptionText = descriptionText.substring(0,200) + "...";
            }
            holder.description.setText(descriptionText);
        }

    }


    public interface OnBookClickListner{
        public  void onBookitemClicked(int position);
    }

    @Override
    public int getItemCount() {
        if(bookEntities == null )return 0;
        else return bookEntities.size();
    }

    public  void setData(List<BookEntity> bn){
        bookEntities.clear();
        if(bn != null) {
            for (BookEntity bookEntity : bn) {
                bookEntities.add(bookEntity);
            }
            notifyDataSetChanged();
        }
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView bookTitle  ;
        ImageView bookImage;
        TextView bookAuthors;
        TextView description;
        OnBookClickListner mOnBookClickListner;
        public BookViewHolder(@NonNull View itemView,OnBookClickListner onBookClickListner) {
            super(itemView);
            itemView.setOnClickListener(this);
            bookTitle=(TextView) itemView.findViewById(R.id.Book_title);
            bookAuthors=(TextView)itemView.findViewById(R.id.Book_authers);
            bookImage=(ImageView) itemView.findViewById(R.id.Book_image);
            description=(TextView) itemView.findViewById(R.id.descriptin);
            mOnBookClickListner=onBookClickListner;
        }

        @Override
        public void onClick(View v) {
            mOnBookClickListner.onBookitemClicked(getAdapterPosition());
        }
    }
}
