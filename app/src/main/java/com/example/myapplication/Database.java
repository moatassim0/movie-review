package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class Database extends SQLiteOpenHelper {
    // Constructor to create the database and tables
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static final String DATABASE_NAME = "project.db";

    //TABLE1 = Movies
    //TABLE2 = Users
    //TABLE3 = Directors
    //TABLE4 = Reviews
    //TABLE5 = Favourites
    //TABLE6 = Views

    // Movies Table
    private static final String TABLE1_NAME = "movies";
    private static final String TABLE1_ATT1 = "movie_id";
    private static final String TABLE1_ATT2 = "title";
    private static final String TABLE1_ATT3 = "genre";
    private static final String TABLE1_ATT4 = "duration";
    private static final String TABLE1_ATT5 = "year";
    private static final String TABLE1_ATT6 = "restriction";
    private static final String TABLE1_ATT7 = "viewNo";
    private static final String TABLE1_ATT8 = "reviewNo";
    private static final String TABLE1_ATT9 = "director_id";

    // Users Table

    private static final String TABLE2_NAME = "users";
    private static final String TABLE2_ATT1 = "user_id";
    private static final String TABLE2_ATT2 = "username";
    private static final String TABLE2_ATT3 = "password";
    private static final String TABLE2_ATT4 = "gender";
    private static final String TABLE2_ATT5 = "age";

    // Directors Table

    private static final String TABLE3_NAME = "directors";
    private static final String TABLE3_ATT1 = "director_id";
    private static final String TABLE3_ATT2 = "name";
    private static final String TABLE3_ATT3 = "age";

    // Reviews Table

    private static final String TABLE4_NAME = "reviews";
    private static final String TABLE4_ATT1 = "review_id";
    private static final String TABLE4_ATT2 = "score";
    private static final String TABLE4_ATT3 = "description";
    private static final String TABLE4_ATT4 = "user_id";
    private static final String TABLE4_ATT5 = "movie_id";

    // Favourites Table

    private static final String TABLE5_NAME = "favourites";
    private static final String TABLE5_ATT1 = "user_id";
    private static final String TABLE5_ATT2 = "movie_id";

    // Views Table

    private static final String TABLE6_NAME = "views";
    private static final String TABLE6_ATT1 = "user_id";
    private static final String TABLE6_ATT2 = "movie_id";
    private static final String TABLE6_ATT3 = "watched";

    @Override
    public void onCreate(SQLiteDatabase db) {

        //TABLE1 = Movies
        //TABLE2 = Users
        //TABLE3 = Directors
        //TABLE4 = Reviews
        //TABLE5 = Favourites
        //TABLE6 = Views

        // Movies Table
        db.execSQL("CREATE TABLE " + TABLE1_NAME + " ("
                + TABLE1_ATT1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE1_ATT2 + " TEXT, "
                + TABLE1_ATT3 + " TEXT, "
                + TABLE1_ATT4 + " INTEGER, "
                + TABLE1_ATT5 + " INTEGER, "
                + TABLE1_ATT6 + " TEXT, "
                + TABLE1_ATT7 + " INTEGER, "
                + TABLE1_ATT8 + " INTEGER, "
                + TABLE1_ATT9 + " INTEGER, "
                + "FOREIGN KEY(" + TABLE1_ATT9 + ") REFERENCES " + TABLE3_NAME + "(" + TABLE3_ATT1 + "))");

        // Users Table
        db.execSQL("CREATE TABLE " + TABLE2_NAME + " ("
                + TABLE2_ATT1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE2_ATT2 + " TEXT, "
                + TABLE2_ATT3 + " TEXT, "
                + TABLE2_ATT4 + " TEXT, "
                + TABLE2_ATT5 + " INTEGER)");

        // Directors Table
        db.execSQL("CREATE TABLE " + TABLE3_NAME + " ("
                + TABLE3_ATT1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE3_ATT2 + " TEXT, "
                + TABLE3_ATT3 + " INTEGER)");

        // Reviews Table
        db.execSQL("CREATE TABLE " + TABLE4_NAME + " ("
                + TABLE4_ATT1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE4_ATT2 + " REAL, "
                + TABLE4_ATT3 + " TEXT, "
                + TABLE4_ATT4 + " INTEGER, "
                + TABLE4_ATT5 + " INTEGER, "
                + "FOREIGN KEY(" + TABLE4_ATT4 + ") REFERENCES " + TABLE2_NAME + "(" + TABLE2_ATT1 + "), "
                + "FOREIGN KEY(" + TABLE4_ATT5 + ") REFERENCES " + TABLE1_NAME + "(" + TABLE1_ATT1 + "))");

        // Favourites Table
        db.execSQL("CREATE TABLE " + TABLE5_NAME + " ("
                + TABLE5_ATT1 + " INTEGER, "
                + TABLE5_ATT2 + " INTEGER, "
                + "PRIMARY KEY (" + TABLE5_ATT1 + ", " + TABLE5_ATT2 + "), "
                + "FOREIGN KEY(" + TABLE5_ATT1 + ") REFERENCES " + TABLE2_NAME + "(" + TABLE2_ATT1 + "), "
                + "FOREIGN KEY(" + TABLE5_ATT2 + ") REFERENCES " + TABLE1_NAME + "(" + TABLE1_ATT1 + "))");

        // Views Table
        db.execSQL("CREATE TABLE " + TABLE6_NAME + " ("
                + TABLE6_ATT1 + " INTEGER, "
                + TABLE6_ATT2 + " INTEGER, "
                + TABLE6_ATT3 + " BOOLEAN, "
                + "PRIMARY KEY (" + TABLE6_ATT1 + ", " + TABLE6_ATT2 + "), "
                + "FOREIGN KEY(" + TABLE6_ATT1 + ") REFERENCES " + TABLE2_NAME + "(" + TABLE2_ATT1 + "), "
                + "FOREIGN KEY(" + TABLE6_ATT2 + ") REFERENCES " + TABLE1_NAME + "(" + TABLE1_ATT1 + "))");
    }








    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE6_NAME);
        onCreate(db);
    }






    // Insert data into Movies table
    public boolean insertMovie(String title, String genre, int duration, int year, String restriction, int viewNo, int reviewNo, int director_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE1_ATT2, title);
        contentValues.put(TABLE1_ATT3, genre);
        contentValues.put(TABLE1_ATT4, duration);
        contentValues.put(TABLE1_ATT5, year);
        contentValues.put(TABLE1_ATT6, restriction);
        contentValues.put(TABLE1_ATT7, viewNo);
        contentValues.put(TABLE1_ATT8, reviewNo);
        contentValues.put(TABLE1_ATT9, director_id);
        long result = db.insert(TABLE1_NAME, null, contentValues);
        return result != -1;
    }

    // Insert data into Users table
    public boolean insertUser(String username, String password, String gender, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE2_ATT2, username);
        contentValues.put(TABLE2_ATT3, password);
        contentValues.put(TABLE2_ATT4, gender);
        contentValues.put(TABLE2_ATT5, age);
        long result = db.insert(TABLE2_NAME, null, contentValues);
        return result != -1;
    }

    // Insert data into Directors table
    public boolean insertDirector(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE3_ATT2, name);
        contentValues.put(TABLE3_ATT3, age);
        long result = db.insert(TABLE3_NAME, null, contentValues);
        return result != -1;
    }

    // Insert data into Reviews table
    public boolean insertReview(float score, String description, int user_id, int movie_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE4_ATT2, score);
        contentValues.put(TABLE4_ATT3, description);
        contentValues.put(TABLE4_ATT4, user_id);
        contentValues.put(TABLE4_ATT5, movie_id);
        long result = db.insert(TABLE4_NAME, null, contentValues);
        return result != -1;
    }

    // Insert data into Favourites table
    public boolean insertFavourite(int user_id, int movie_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE5_ATT1, user_id);
        contentValues.put(TABLE5_ATT2, movie_id);
        long result = db.insert(TABLE5_NAME, null, contentValues);
        return result != -1;
    }

    // Insert data into Views table
    public boolean insertView(int user_id, int movie_id, boolean watched) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE6_ATT1, user_id);
        contentValues.put(TABLE6_ATT2, movie_id);
        contentValues.put(TABLE6_ATT3, watched ? 1 : 0);  // boolean stored as INTEGER (1 for true, 0 for false)
        long result = db.insert(TABLE6_NAME, null, contentValues);
        return result != -1;
    }

    // Remove an entry from Favourites table
    public boolean removeFromFavourites(int userId, int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(
                TABLE5_NAME,
                TABLE5_ATT1 + " = ? AND " + TABLE5_ATT2 + " = ?",
                new String[]{String.valueOf(userId), String.valueOf(movieId)}
        );
        return rowsAffected > 0;
    }

    // Remove an entry from Views table
    public boolean removeFromViews(int userId, int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(
                TABLE6_NAME,
                TABLE6_ATT1 + " = ? AND " + TABLE6_ATT2 + " = ?",
                new String[]{String.valueOf(userId), String.valueOf(movieId)}
        );
        return rowsAffected > 0;
    }





    // Get all data from Movies table
    public Cursor getAllMovies() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE1_NAME, null);
    }

    // Get all data from Users table
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE2_NAME, null);
    }

    // Get all data from Directors table
    public Cursor getAllDirectors() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE3_NAME, null);
    }

    // Get all data from Reviews table
    public Cursor getAllReviews() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE4_NAME, null);
    }

    // Get all data from Favourites table
    public Cursor getAllFavourites() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE5_NAME, null);
    }

    // Get all data from Views table
    public Cursor getAllViews() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE6_NAME, null);
    }








    // Get a movie by its title
    public Cursor getMovieFilteredTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE1_NAME + " WHERE " + TABLE1_ATT2 + " LIKE ?", new String[]{title + "%"});
    }
    // Get movies by genre
    public Cursor getMoviesByGenre(String genre) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE1_NAME + " WHERE " + TABLE1_ATT3 + " LIKE ?", new String[]{genre + "%"});
    }
    // Get movies by year
    public Cursor getMoviesByYear(int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE1_NAME + " WHERE " + TABLE1_ATT5 + " = ?", new String[]{String.valueOf(year)});
    }

    // Check if a username and password pair exists in the database
    public boolean isValidUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE2_NAME +
                        " WHERE " + TABLE2_ATT2 + " = ? AND " + TABLE2_ATT3 + " = ?",
                new String[]{username, password}
        );

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0;
        } else {
            cursor.close();
            return false;
        }
    }

    // Get user by username
    public int getUserByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ TABLE2_ATT1 + " FROM " + TABLE2_NAME + " WHERE " + TABLE2_ATT2 + " = ? ", new String[]{username});

        if (cursor.moveToFirst()) {
            int userID = cursor.getInt(cursor.getColumnIndex(TABLE2_ATT1));
            cursor.close();
            return userID;
        }
        else {
            cursor.close();
            return -1;
        }
    }

    // Check if user has favourited movie
    public boolean isFavourite(int userID, int movieID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE5_NAME + " WHERE " + TABLE5_ATT1 + " = ?" + " AND " + TABLE5_ATT2 + " = ? ", new String[]{String.valueOf(userID), String.valueOf(movieID)});

        return cursor.moveToFirst();
    }

    // Check if user has seen movie
    public boolean isSeen(int userID, int movieID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE6_NAME + " WHERE " + TABLE6_ATT1 + " = ?" + " AND " + TABLE6_ATT2 + " = ? ", new String[]{String.valueOf(userID), String.valueOf(movieID)});

        return cursor.moveToFirst();
    }

    // Get movie by movie ID
    public Cursor getMovieByMovieID(int movieID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE1_NAME + " WHERE " + TABLE1_ATT1 + " = ? ", new String[]{String.valueOf(movieID)});
    }

    // Get all reviews from a specific movie
    public Cursor getReviewsByMovieID(int movieID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE4_NAME + " WHERE " + TABLE4_ATT5 + " = ? " +
                " ORDER BY " + TABLE4_ATT1 + " DESC", new String[]{String.valueOf(movieID)});
    }

    // Get specific user by their ID
    public Cursor getUserByUserID(int userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE2_NAME + " WHERE " + TABLE2_ATT1 + " = ? ", new String[]{String.valueOf(userID)});
    }

    // Get director by director ID
    public Cursor getDirectorByDirectorID(int directorID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE3_NAME + " WHERE " + TABLE3_ATT1 + " = ? ", new String[]{String.valueOf(directorID)});
    }

    // Get top 4 most watched movies
    public Cursor getTopWatchedMovies(int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (limit != -1) {
            return db.rawQuery(
                    "SELECT * FROM " + TABLE1_NAME +
                            " ORDER BY " + TABLE1_ATT7 + " DESC " +  // Order by viewNo
                            "LIMIT " + limit,
                    null
            );
        }
        else {
            return db.rawQuery(
                    "SELECT * FROM " + TABLE1_NAME +
                            " ORDER BY " + TABLE1_ATT7 + " DESC ",  // Order by viewNo
                    null
            );
        }

    }

    // Get top 4 most recent movies
    public Cursor getRecentMovies(int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (limit != -1){
            return db.rawQuery(
                    "SELECT * FROM " + TABLE1_NAME +
                            " ORDER BY " + TABLE1_ATT5 + " DESC " +  // Order by year
                            "LIMIT " + limit,
                    null
            );
        }
        else {
            return db.rawQuery(
                    "SELECT * FROM " + TABLE1_NAME +
                            " ORDER BY " + TABLE1_ATT5 + " DESC ",  // Order by year
                    null
            );
        }

    }

    // Get top 4 highest rated movies
    public Cursor getTopRatedMovies(int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (limit != -1) {
            return db.rawQuery(
                    "SELECT m.*, AVG(r." + TABLE4_ATT2 + ") as avg_rating " +
                            "FROM " + TABLE1_NAME + " m " +
                            "LEFT JOIN " + TABLE4_NAME + " r " +
                            "ON m." + TABLE1_ATT1 + " = r." + TABLE4_ATT5 + " " +
                            "GROUP BY m." + TABLE1_ATT1 + " " +
                            "ORDER BY avg_rating DESC " +
                            "LIMIT " + limit,
                    null
            );
        }
        else {
            return db.rawQuery(
                    "SELECT m.*, AVG(r." + TABLE4_ATT2 + ") as avg_rating " +
                            "FROM " + TABLE1_NAME + " m " +
                            "LEFT JOIN " + TABLE4_NAME + " r " +
                            "ON m." + TABLE1_ATT1 + " = r." + TABLE4_ATT5 + " " +
                            "GROUP BY m." + TABLE1_ATT1 + " " +
                            "ORDER BY avg_rating DESC ",
                    null
            );
        }

    }

    // Get 4 most recently favorited movies for a specific user
    public Cursor getUserRecentFavorites(int userId, int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (limit != -1){
            return db.rawQuery(
                    "SELECT m.* FROM " + TABLE1_NAME + " m " +
                            "JOIN " + TABLE5_NAME + " f ON m." + TABLE1_ATT1 + " = f." + TABLE5_ATT2 + " " +
                            "WHERE f." + TABLE5_ATT1 + " = ? " +
                            "ORDER BY f.rowid DESC " +  // Using row id to get insertion order
                            "LIMIT " + limit,
                    new String[]{String.valueOf(userId)}
            );
        } else {
            return db.rawQuery(
                    "SELECT m.* FROM " + TABLE1_NAME + " m " +
                            "JOIN " + TABLE5_NAME + " f ON m." + TABLE1_ATT1 + " = f." + TABLE5_ATT2 + " " +
                            "WHERE f." + TABLE5_ATT1 + " = ? " +
                            "ORDER BY f.rowid DESC ", // Using row id to get insertion order
                    new String[]{String.valueOf(userId)}
            );
        }

    }

    // Get 4 most recently reviewed movies for a specific user
    public Cursor getUserRecentReviews(int userId, int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (limit != -1) {
            return db.rawQuery(
                    "SELECT m.*, r." + TABLE4_ATT2 + " as rating, r." + TABLE4_ATT3 + " as review_text " +
                            "FROM " + TABLE1_NAME + " m " +
                            "JOIN " + TABLE4_NAME + " r ON m." + TABLE1_ATT1 + " = r." + TABLE4_ATT5 + " " +
                            "WHERE r." + TABLE4_ATT4 + " = ? " +
                            "ORDER BY r." + TABLE4_ATT1 + " DESC " +
                            "LIMIT " + limit,
                    new String[]{String.valueOf(userId)}
            );
        } else {
            return db.rawQuery(
                    "SELECT m.*, r." + TABLE4_ATT2 + " as rating, r." + TABLE4_ATT3 + " as review_text " +
                            "FROM " + TABLE1_NAME + " m " +
                            "JOIN " + TABLE4_NAME + " r ON m." + TABLE1_ATT1 + " = r." + TABLE4_ATT5 + " " +
                            "WHERE r." + TABLE4_ATT4 + " = ? " +
                            "ORDER BY r." + TABLE4_ATT1 + " DESC ",
                    new String[]{String.valueOf(userId)}
            );
        }

    }

}
