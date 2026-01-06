package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//When login button clicked, check database
//If there's no match, show toast message
//If login details correct, redirect to home
public class MainActivity extends AppCompatActivity {
    Database db;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void onClickLoginButton(View view) {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            db = new Database(this);
            boolean isValid = db.isValidUserCredentials(username, password);
            if(isValid){
                int userID = db.getUserByUsername(username);
                Toast.makeText(this, "Welcome, "+ username + ".", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, home.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    public void toRegisterPage(View view) {
        // Redirect to registration activity
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
        finish();
    }

}