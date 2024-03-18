package com.example.worknumtow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class AddActivity extends AppCompatActivity
{
    private EditText itemNameEditText;
    private EditText itemNumberOfEditText;
    private Button addButton;
    private Item product;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        itemNameEditText = findViewById(R.id.editText_ProductName);
        itemNumberOfEditText = findViewById(R.id.editText_numberOf);
        addButton = findViewById(R.id.btn_addProduct);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product = new Item(itemNameEditText.getText().toString(),Integer.parseInt(itemNumberOfEditText.getText().toString()));
                navigateToMain();
            }
        });
    }
    private void navigateToMain() {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        intent.putExtra("MyItem", product);
        startActivity(intent);
        finish();
    }
}