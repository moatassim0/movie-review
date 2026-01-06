package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Directory extends AppCompatActivity implements DirectoryRecyclerViewInterface {
    Database db;
    Bundle extras;

    ArrayList<DirectoryEntryModel> entryModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.directory);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        extras = getIntent().getExtras();
        db = new Database(this);

        RecyclerView recyclerView = findViewById(R.id.directoryRecyclerView);

        //updateDirectory();
        setUpEntryModels();

        Directory_RecyclerViewAdapter adapter = new Directory_RecyclerViewAdapter(this,
                this, entryModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    private void setUpEntryModels() {
        String category = extras.getString("category");
        int userID = extras.getInt("userID");
        Cursor c;

        switch (category) {
            case "recently_added":
                c = db.getRecentMovies(-1);
                break;
            case "most_watched":
                c = db.getTopWatchedMovies(-1);
                break;
            case "highly_rated":
                c = db.getTopRatedMovies(-1);
                break;
            case "recent_favourite":
                c = db.getUserRecentFavorites(userID,-1);
                break;
            case "recent_review":
                c = db.getUserRecentReviews(userID,-1);
                break;
            default:
                c = db.getRecentMovies(-1);
        }

        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++) {
            int id = c.getInt(0);

            String name = "movie_"+id;
            int fid = getResources().getIdentifier(name, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(fid, null);

            entryModels.add(new DirectoryEntryModel(id, drawable));
            c.moveToNext();
        }

    }

    private void updateDirectory() {
//        int first = R.id.recentlyAdded1;
//        int second = R.id.recentlyAdded2;
//        int third = R.id.recentlyAdded3;
//        int fourth = R.id.recentlyAdded4;
//        int[] a = {first, second, third, fourth};
        int[] a = new int[32];
        for (int i = 0; i < a.length; i++){
            int counter = i + 1;
            String btnID = "imageButton"+counter;
            int btn = getResources().getIdentifier(btnID, "id", getPackageName());
            a[i] = btn;
            //Log.d("TEST", String.valueOf(a[i]));
        }

        String category = extras.getString("category");
        Cursor c;

        switch (category) {
            case "recently_added":
                c = db.getRecentMovies(-1);
                break;
            case "most_watched":
                c = db.getTopWatchedMovies(-1);
                break;
            case "highly_rated":
                c = db.getTopRatedMovies(-1);
                break;
            default:
                c = db.getRecentMovies(-1);
        }

        int counter = 0;
        while (c.moveToNext()) {
            int id = c.getInt(0);
            //int a = getResources().getIdentifier("imageButton"+id, "id", getPackageName());
            String name = "movie_"+id;
            int fid = getResources().getIdentifier(name, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(fid, null);
            ImageButton image = findViewById(a[counter]);
            image.setTag(id);
            image.setImageDrawable(drawable);
            //Log.d("TEST", String.valueOf(counter));
            counter++;
        }
    }

    public void onClickMovie(View view) {
        int userID = extras.getInt("userID");
        int movieID = (int) view.getTag();
        Intent intent = new Intent(this, Movie.class);
        intent.putExtra("userID", userID);
        intent.putExtra("movieID", movieID);
        startActivity(intent);
        //startActivityForResult(intent, 1);
        //finish();
        //Log.d("TEST", id);
    }

    public void onClickDirectoryButton(View view) {
        // Do nothing
        return;
//        int userID = extras.getInt("userID");
//        Intent intent = new Intent(this, Directory.class);
//        intent.putExtra("userID", userID);
//        startActivity(intent);
//        finish();
    }

    public void onClickPersonalButton(View view) {
        int userID = extras.getInt("userID");
        Intent intent = new Intent(this, Personal.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
        finish();
    }

    public void onClickHomeButton(View view) {
        int userID = extras.getInt("userID");
        Intent intent = new Intent(this, home.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
        finish();
    }

    @Override
    public void onMovieClick(int position) {
        Log.d("TEST", "AAAA");
        int userID = extras.getInt("userID");
        int movieID = entryModels.get(position).getMovieID();
        Intent intent = new Intent(this, Movie.class);
        intent.putExtra("userID", userID);
        intent.putExtra("movieID", movieID);
        startActivity(intent);
    }
}
