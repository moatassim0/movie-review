package com.example.myapplication;

import android.content.Context;
import android.media.Rating;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Review_RecyclerViewAdapter extends RecyclerView.Adapter<Review_RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<ReviewModel> reviewModels;

    public Review_RecyclerViewAdapter(Context context, ArrayList<ReviewModel> reviewModels) {
        this.context = context;
        this.reviewModels = reviewModels;
    }

    @NonNull
    @Override
    public Review_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_review_item, parent, false);

        return new Review_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Review_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //Assigning values to views created in movie_review_item layout based on recycler view position

        holder.username.setText(reviewModels.get(position).getUserName());
        holder.content.setText(reviewModels.get(position).getReviewContent());
        holder.rating.setRating(reviewModels.get(position).getReviewScore());

    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username, content;
        RatingBar rating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.userName);
            content = itemView.findViewById(R.id.reviewContent);
            rating = itemView.findViewById(R.id.userRating);
        }
    }
}
