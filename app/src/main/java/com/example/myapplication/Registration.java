package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    Database db;

    private EditText usernameEditText, passwordEditText, ageEditText;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new Database(this);

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        ageEditText = findViewById(R.id.age);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);

        Button signupButton = findViewById(R.id.signupButton);

        // Set up button click listener
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String age = ageEditText.getText().toString();
                int age_int = Integer.parseInt(age);

                // Get selected gender
                int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
                String gender = selectedGenderRadioButton != null ? selectedGenderRadioButton.getText().toString() : "";

                // Validate inputs (basic validation)
                if (username.isEmpty() || password.isEmpty() || age.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(Registration.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (db.getUserByUsername(username) != -1){ // Check if username already in use. Function only returns -1 when username is not in use.
                    Toast.makeText(Registration.this, "A user with that name already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    db.insertUser(username, password, gender, age_int);
                    int userID = db.getUserByUsername(username);

                    // Store data (for now we are just using Toast to show data)
                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    // Redirect to home activity (or another layout)
                    Intent intent = new Intent(Registration.this, home.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                    finish();
                }




            }
        });
    }
}
