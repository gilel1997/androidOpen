package com.example.worknumtow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button RegisterButton;
    private SQLiteDatabase database;
    private String fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailEditText = findViewById(R.id.editTextemail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        RegisterButton = findViewById(R.id.buttonToRegister);
        // Initialize the database
        database = openOrCreateDatabase("YourDatabaseName.db", MODE_PRIVATE, null);
        createTable(); // Create the user table if it doesn't exist

        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (loginUser(email, password)) {
                    // Login successful
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Cursor cursor = database.rawQuery("SELECT fullname FROM Users WHERE email = (?)", new String[]{email});
                    if (cursor != null && cursor.moveToFirst())
                    {
                        fullName = cursor.getString(0);
                        cursor.close();
                    }
                    // Add your code here to navigate to the MainActivity
                    goToMain();
                } else {
                    // Login failed
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSingUp();
            }
        });
    }

    private void createTable() {
        database.execSQL("CREATE TABLE IF NOT EXISTS Users (email TEXT PRIMARY KEY, password TEXT)");
    }

    private boolean loginUser(String email, String password) {
        Cursor cursor = database.rawQuery("SELECT * FROM Users WHERE email = ? AND password = ?",
                new String[]{email, password});

        boolean isLoggedIn = cursor.moveToFirst();
        cursor.close();
        return isLoggedIn;
    }
    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("STRING_KEY", fullName);
        startActivity(intent);
        finish(); // Optional: Call finish() if you want to finish the LoginActivity and prevent going back to it with the back button
    }
    private void navigateToSingUp() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish(); // Finish the LoginActivity to prevent the user from navigating back to it
    }

}
