package com.example.worknumtow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView usernameText;
    private String fullName;
    private Button addButton;
    private Button deleteButton;
    private Button showListButton;
    private Item product;
    private ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        fullName = intent.getStringExtra("STRING_KEY");
        product = intent.getParcelableExtra("MyItem");
        items.add(product);


        usernameText = findViewById(R.id.text_username);
        addButton = findViewById(R.id.btn_addItem);
        deleteButton = findViewById(R.id.btn_deleteItem);
        showListButton = findViewById(R.id.btn_showList);
        usernameText.setText("hello " + fullName);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAddActivity();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyDialogDelete dialog = MyDialogDelete.newInstance(items, product);
                dialog.show(getSupportFragmentManager(), "MyDialogDelete");
            }
        });

        showListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Item itemN= new Item("gil",3);
                items.add(itemN);
                MyDialogShow dialogShow = MyDialogShow.newInstance(items);
                dialogShow.show(getSupportFragmentManager(), "MyDialogDelete");
            }
        });


    }

    private void navigateToAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);

    }
}