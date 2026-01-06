package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Movie extends AppCompatActivity {
    Database db;
    Bundle extras;

    ImageView moviePoster;
    TextView movieTitle, movieGenre, movieDuration, movieYear, movieRestriction, movieViews, movieDirector, reviewUsername;
    EditText userReviewContent;
    Button favouriteButton, seenButton, addReviewButton;
    RatingBar userRating;

    ArrayList<ReviewModel> reviewModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        extras = getIntent().getExtras();
        db = new Database(this);

        movieTitle = findViewById(R.id.movieTitle);
        moviePoster = findViewById(R.id.moviePoster);
        movieGenre = findViewById(R.id.movieGenre);
        movieDuration = findViewById(R.id.movieDuration);
        movieYear = findViewById(R.id.movieYear);
        movieRestriction = findViewById(R.id.movieRestriction);
        movieViews = findViewById(R.id.movieViews);
        movieDirector = findViewById(R.id.movieDirector);
        favouriteButton = findViewById(R.id.favoriteButton);
        seenButton = findViewById(R.id.seenButton);

        reviewUsername = findViewById(R.id.reviewUsername);
        addReviewButton = findViewById(R.id.addReviewButton);
        userReviewContent = findViewById(R.id.userReviewContent);
        userRating = findViewById(R.id.userRating);


        RecyclerView recyclerView = findViewById(R.id.reviewsRecyclerView);

        loadMovie();
        initFavouriteAndSeenButtons();
        setUpReviewModels();

        //Adapter must be created *after* models
        Review_RecyclerViewAdapter adapter = new Review_RecyclerViewAdapter(this, reviewModels);

        //Attach adapter to recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void loadMovie() {
        int movieID = extras.getInt("movieID");
        int userID = extras.getInt("userID");

        //Set username TextView in review input box
        Cursor u = db.getUserByUserID(userID);
        u.moveToNext();
        reviewUsername.setText(u.getString(1));


        //Fetch poster
        int fid = getResources().getIdentifier("movie_"+movieID, "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(fid, null);
        moviePoster.setImageDrawable(drawable);

        //Fetch everything else from db
        Cursor c = db.getMovieByMovieID(movieID);
        c.moveToNext();
        Cursor director = db.getDirectorByDirectorID(c.getInt(8));
        director.moveToNext();

        movieTitle.setText(c.getString(1));
        movieGenre.setText(c.getString(2));
        movieDuration.setText(c.getString(3) + " minutes");
        movieYear.setText(c.getString(4));
        movieRestriction.setText(c.getString(5));
        movieViews.setText(c.getString(6));
        movieDirector.setText(director.getString(1));

        u.close();
        c.close();
        director.close();

    }

    public void initFavouriteAndSeenButtons() {
        int movieID = extras.getInt("movieID");
        int userID = extras.getInt("userID");
        boolean seen = db.isSeen(userID, movieID);
        boolean fave = db.isFavourite(userID, movieID);

        if (fave) {
            favouriteButton.setText("Favourite!");
            favouriteButton.setTextColor(Color.parseColor("#30377E")); //blurple
            favouriteButton.setBackgroundColor(Color.parseColor("#E6C01B")); //gold
            favouriteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.favourite_filled, 0, 0, 0);
        }
        else {
            //do nothing
        }

        if (seen) {
            seenButton.setText("Seen!");
            seenButton.setTextColor(Color.parseColor("#30377E"));
            seenButton.setBackgroundColor(Color.parseColor("#E6C01B"));
            seenButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.eye_filled, 0, 0, 0);
        }
        else {
            //do nothing
        }
    }

    public void onClickBackButton(View v) {
        finish();
        //finishActivity(1);
    }

    private void setUpReviewModels() {
        int movieID = extras.getInt("movieID");
        Cursor reviews = db.getReviewsByMovieID(movieID);
        //Log.d("TEST", reviews.getColumnName(3)); //user_id

        reviews.moveToFirst();
        for(int i=0; i < reviews.getCount(); i++) {
            int uid = reviews.getInt(3);
            Cursor u = db.getUserByUserID(uid);
            u.moveToFirst();
            String username = u.getString(1);
            String content = reviews.getString(2);
            //Log.d("TEST", content);
            int score = reviews.getInt(1);

            reviewModels.add(new ReviewModel(username, score, content));
            u.moveToNext();
            reviews.moveToNext();
        }
    }

    public void onClickFavouriteButton(View view) {
        int movieID = extras.getInt("movieID");
        int userID = extras.getInt("userID");
        boolean fave = db.isFavourite(userID, movieID);

        if (fave) {
            //remove entry from db
            db.removeFromFavourites(userID, movieID);
            //Change button
            favouriteButton.setText("Not Favourite");
            favouriteButton.setTextColor(Color.parseColor("#E6C01B")); //gold
            favouriteButton.setBackgroundColor(Color.parseColor("#664FA3")); //purple
            favouriteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.favourite_outline, 0, 0, 0);
        }
        else {
            //add entry to db
            db.insertFavourite(userID, movieID);
            //Change button
            favouriteButton.setText("Favourite!");
            favouriteButton.setTextColor(Color.parseColor("#30377E")); //blurple
            favouriteButton.setBackgroundColor(Color.parseColor("#E6C01B")); //gold
            favouriteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.favourite_filled, 0, 0, 0);
        }
    }

    public void onClickSeenButton(View view) {
        int movieID = extras.getInt("movieID");
        int userID = extras.getInt("userID");
        boolean seen = db.isSeen(userID, movieID);

        if (seen) {
            //remove entry from db
            db.removeFromViews(userID, movieID);
            //change button
            seenButton.setText("Not Seen");
            seenButton.setTextColor(Color.parseColor("#E6C01B")); //gold
            seenButton.setBackgroundColor(Color.parseColor("#664FA3")); //purple
            seenButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.eye_outline, 0, 0, 0);
        }
        else {
            //add entry to db
            db.insertView(userID, movieID, true);
            //change button
            seenButton.setText("Seen!");
            seenButton.setTextColor(Color.parseColor("#30377E"));
            seenButton.setBackgroundColor(Color.parseColor("#E6C01B"));
            seenButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.eye_filled, 0, 0, 0);

        }
    }

    public void onClickAddReviewButton(View view) {
        int movieID = extras.getInt("movieID");
        int userID = extras.getInt("userID");
        String content = userReviewContent.getText().toString();
        float rating = userRating.getRating();

        if (content.isEmpty()) {
            Toast.makeText(this, "Can't submit empty review.", Toast.LENGTH_SHORT).show();
            return;
        }

        db.insertReview(rating, content, userID, movieID);

        //refresh activity
        finish();
        startActivity(getIntent());


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
         int userID = extras.getInt("userID");
         Intent intent = new Intent(this, home.class);
         intent.putExtra("userID", userID);
         startActivity(intent);
         finish();
    }
}