package com.example.findingbooks.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.findingbooks.R;
import com.example.findingbooks.model.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private ArrayList<BookEntity> bookEntities =new ArrayList<BookEntity>();;
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
       holder.bookTitle.setText(bookEntities.get(position).getVolumInfo().getTitle());
    }

    @Override
    public int getItemCount() {
        if(bookEntities == null )return 0;
        else return bookEntities.size();
    }

    public  void setData(List<BookEntity> bn){
        for (BookEntity bookEntity : bn){
            bookEntities.add(bookEntity);
        }
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle  ;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle=(TextView) itemView.findViewById(R.id.Book_title);
        }
    }
}
