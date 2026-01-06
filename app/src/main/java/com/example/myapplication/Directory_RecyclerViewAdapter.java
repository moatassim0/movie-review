package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Directory_RecyclerViewAdapter extends RecyclerView.Adapter<Directory_RecyclerViewAdapter.MyViewHolder>{
    private final DirectoryRecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<DirectoryEntryModel> entryModels;

    public Directory_RecyclerViewAdapter(DirectoryRecyclerViewInterface recyclerViewInterface, Context context, ArrayList<DirectoryEntryModel> entryModels) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.entryModels = entryModels;

    }

    @NonNull
    @Override
    public Directory_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.directory_recycler_view_row, parent, false);

        return new Directory_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface   );
    }

    @Override
    public void onBindViewHolder(@NonNull Directory_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.button.setImageDrawable(entryModels.get(position).getPoster());
        holder.button.setTag(entryModels.get(position).getMovieID());

    }

    @Override
    public int getItemCount() {
        return entryModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageButton button;

        public MyViewHolder(@NonNull View itemView, DirectoryRecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            button = itemView.findViewById(R.id.imageButtonRV);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onMovieClick(pos);
                        }
                    }

                }
            });
        }
    }
}
