package com.example.tor4me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.checkerframework.common.subtyping.qual.Bottom;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Button dayOf = findViewById(R.id.btn_dayOff);
        dayOf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToDayOffActivity();
            }
        });
    }
    public void goToDayOffActivity()
    {
        Intent intent = new Intent(AdminMainActivity.this, DayOffActivity.class);
        startActivity(intent);
    }
}