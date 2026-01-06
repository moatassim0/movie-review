package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Personal extends AppCompatActivity {
    Database db;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.personal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        extras = getIntent().getExtras();

        db = new Database(this);

        updateFavorites();
        updateReviewed();
    }

    private void updateFavorites() {
        int userID = extras.getInt("userID");
        int first = R.id.favorite1;
        int second = R.id.favorite2;
        int third = R.id.favorite3;
        int fourth = R.id.favorite4;
        int[] a = {first, second, third, fourth};
        int counter = 0;
        Cursor c = db.getUserRecentFavorites(userID, 4);
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

    private void updateReviewed() {
        int userID = extras.getInt("userID");
        int first = R.id.reviewed1;
        int second = R.id.reviewed2;
        int third = R.id.reviewed3;
        int fourth = R.id.reviewed4;
        int[] a = {first, second, third, fourth};
        int counter = 0;
        Cursor c = db.getUserRecentReviews(userID, 4);
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
        intent.putExtra("category", "recently_added");
        startActivity(intent);
        finish();
    }

    public void onClickPersonalButton(View view) {
        //Do Nothing
        return;
//        int userID = extras.getInt("userID");
//        Intent intent = new Intent(this, Personal.class);
//        intent.putExtra("userID", userID);
//        startActivity(intent);
//        finish();
    }

    public void onClickHomeButton(View view) {
        int userID = extras.getInt("userID");
        Intent intent = new Intent(this, home.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
        finish();
    }

    public void onClickViewAll(View view) {
        int userID = extras.getInt("userID");
        Intent intent = new Intent(this, Directory.class);
        intent.putExtra("userID", userID);

        if (view.getId() == R.id.favouriteViewAll) {
            intent.putExtra("category", "recent_favourite");
        }
        else if (view.getId() == R.id.reviewsViewAll) {
            intent.putExtra("category", "recent_review");
        }
        else {
            intent.putExtra("category", "recently_added");
        }
        startActivity(intent);
        finish();
    }
}