package com.example.tor4me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createMeeting;
    private Button changeMeeting;
    private Button cancelMeeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMeeting = findViewById(R.id.btn_createMeeting);

        //onClick

        createMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToCreateMeeting(v);
            }
            });

    }
    public void goToCreateMeeting(View view) {
        Intent intent = new Intent(MainActivity.this, CreateMeetingActivity.class);
        startActivity(intent);
    }
}