package com.example.worknumtow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText editFullName;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editVerifyPassword;
    private Button btnRegister;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        // Initialize views
        editFullName = findViewById(R.id.edit_full_name);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editVerifyPassword = findViewById(R.id.edit_verify_password);
        btnRegister = findViewById(R.id.btn_register);

        // Initialize the database
        database = openOrCreateDatabase("YourDatabaseName.db", MODE_PRIVATE, null);
        createTable(); // Create the user table if it doesn't exist

        // Set click listener for the register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editFullName.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String username = editFullName.getText().toString();
                String verifyPassword = editVerifyPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals(verifyPassword)) {
                    registerUser(email, password, username, v);
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "password and verify password are not the same", Toast.LENGTH_SHORT).show();
                }
                // Passwords match, proceed with registration
                if (registerUser(email, password, username, v)) {
                    // Registration successful
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    // Add your code here to navigate to the next activity
                } else {
                    // Registration failed
                    Toast.makeText(RegisterActivity.this, "email already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        database.execSQL(sql);
        database.execSQL("CREATE TABLE IF NOT EXISTS Users (email TEXT PRIMARY KEY, password TEXT, fullname TEXT)");
    }

    private boolean registerUser(String email, String password, String username, View view) {
        try {
            database.execSQL("INSERT INTO Users (email, password, fullname) VALUES (?, ?, ?)",new String[]{email, password, username});
            goToLogin(view);
            return true;
        } catch (Exception e) {
            // Handle any errors that occur during the registration process
            return false;
        }
    }
    public void goToLogin(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("STRING_KEY", editFullName.getText().toString());
        startActivity(intent);
    }
}
