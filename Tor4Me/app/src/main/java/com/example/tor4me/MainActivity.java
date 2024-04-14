package com.example.tor4me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createMeeting;
    private Button listHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMeeting = findViewById(R.id.btn_createMeeting);
        listHours = findViewById(R.id.btn_Meetings);

        //onClick

        createMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToCreateMeeting(v);
            }
            });
        listHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToMeetings(v);
            }
        });
    }
    public void goToCreateMeeting(View view) {
        Intent intent = new Intent(MainActivity.this, CreateMeetingActivity.class);
        startActivity(intent);
    }
    public void goToMeetings(View view) {
        Intent intent = new Intent(MainActivity.this, MeetingsActivity.class);
        startActivity(intent);
    }
}