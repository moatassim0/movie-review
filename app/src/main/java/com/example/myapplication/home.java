package com.example.myapplication;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class home extends AppCompatActivity {
    Database db;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        extras = getIntent().getExtras();
        db = new Database(this);

        updateMostWatched();
        updateHighlyRated();
        updateRecentlyAdded();


    }

    //Might do update methods on separate thread
    private void updateMostWatched() {
        int first = R.id.mostWatched1;
        int second = R.id.mostWatched2;
        int third = R.id.mostWatched3;
        int fourth = R.id.mostWatched4;
        int[] a = {first, second, third, fourth};
        int counter = 0;
       Cursor c = db.getTopWatchedMovies(4);
       while (c.moveToNext()) {
           int id = c.getInt(0);
           String name = "movie_"+id;
           int fid = getResources().getIdentifier(name, "drawable", getPackageName());
           Drawable drawable = getResources().getDrawable(fid, null);
           ImageButton image = findViewById(a[counter]);
           image.setTag(id);
           image.setImageDrawable(drawable);
            counter++;
       }

    }

    private void updateRecentlyAdded(){
        int first = R.id.recentlyAdded1;
        int second = R.id.recentlyAdded2;
        int third = R.id.recentlyAdded3;
        int fourth = R.id.recentlyAdded4;
        int[] a = {first, second, third, fourth};
        int counter = 0;
        Cursor c = db.getRecentMovies(4);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = "movie_"+id;
            int fid = getResources().getIdentifier(name, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(fid, null);
            ImageButton image = findViewById(a[counter]);
            image.setTag(id);
            image.setImageDrawable(drawable);
            counter++;
        }
    }
    private void updateHighlyRated(){
        int first = R.id.highlyRated1;
        int second = R.id.highlyRated2;
        int third = R.id.highlyRated3;
        int fourth = R.id.highlyRated4;
        int[] a = {first, second, third, fourth};
        int counter = 0;
        Cursor c = db.getTopRatedMovies(4);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = "movie_"+id;
            int fid = getResources().getIdentifier(name, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(fid, null);
            ImageButton image = findViewById(a[counter]);
            image.setTag(id);
            image.setImageDrawable(drawable);

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
        int userID = extras.getInt("userID");
        Intent intent = new Intent(this, Directory.class);
        intent.putExtra("userID", userID);
        intent.putExtra("category", "recently_added"); //Directory displays movies in order of recently added by default.
        startActivity(intent);
        finish();
    }

    public void onClickPersonalButton(View view) {
        int userID = extras.getInt("userID");
        Intent intent = new Intent(this, Personal.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
        finish();
    }

    public void onClickHomeButton(View view) {
        // Do nothing
        // int userID = extras.getInt("userID");
        // Intent intent = new Intent(this, home.class);
        // intent.putExtra("userID", userID);
        // startActivity(intent);
        // finish();
        return;
    }

    public void onClickViewAll(View view) {
        int userID = extras.getInt("userID");
        Intent intent = new Intent(this, Directory.class);
        intent.putExtra("userID", userID);

        if (view.getId() == R.id.mostWatchedViewAll) {
            intent.putExtra("category", "most_watched");
        }
        else if (view.getId() == R.id.highlyRatedViewAll) {
            intent.putExtra("category", "highly_rated");
        }
        else {
            intent.putExtra("category", "recently_added");
        }
        startActivity(intent);
        finish();
    }
}