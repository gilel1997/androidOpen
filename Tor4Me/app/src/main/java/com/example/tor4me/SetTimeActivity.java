package com.example.tor4me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SetTimeActivity extends AppCompatActivity {

    private Button returnBtn;
    private TextView textTitle;
    private DatabaseReference mDatabase;
    private String day;
    private String month;
    private String year;
    private GridView HoursGridView;
    private HoursAdapter hoursAdupter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        Intent intent = getIntent();
        returnBtn = findViewById(R.id.btn_return);
        textTitle = findViewById(R.id.text_title);
        FirebaseApp.initializeApp(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        day = intent.getStringExtra("selected_day");
        month = intent.getStringExtra("selected_month");
        year = intent.getStringExtra("selected_year");
        HoursGridView = findViewById(R.id.Hours_gridview);

        ReadData(month, Integer.parseInt(day), Integer.parseInt(year));
        List<MeetingsData> meetingsDataLst = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MeetingsData meetingsData = new MeetingsData();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey(); // Get the key of the snapshot
                    Object value = snapshot.getValue(); // Get the value of the snapshot
                    if (key.equals("day"))
                    {
                        meetingsData.day = Integer.parseInt(value.toString());
                    }
                    else if (key.equals("month"))
                    {
                        meetingsData.month = value.toString();
                    }
                    else if (key.equals("year"))
                    {
                        meetingsData.year = Integer.parseInt(value.toString());
                    }
                    else if (key.equals("hour"))
                    {
                        meetingsData.hour = value.toString();
                    }
                }
                meetingsDataLst.add(meetingsData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
        hoursAdupter = new HoursAdapter(this,meetingsDataLst);
        HoursGridView.setAdapter(hoursAdupter);

        HoursGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 // Retrieve the clicked date from the adapter
                 String selectedHour = (String) parent.getItemAtPosition(position);
                 WriteData(month, Integer.parseInt(day), Integer.parseInt(year), selectedHour);

             }
         });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateMeeting(v);
            }
        });


    }

    public void goToCreateMeeting(View view) {
        Intent intent = new Intent(SetTimeActivity.this, CreateMeetingActivity.class);
        startActivity(intent);
    }

    public void WriteData(String month, int day, int year, String hour) {
        MeetingsData meetingsData = new MeetingsData(month, day, year, hour);

        mDatabase.child("meetings").setValue(meetingsData);
    }
    public void ReadData(String month, int day, int year)
    {
        mDatabase.child("hours of meetings").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(SetTimeActivity.this, "read data OK", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SetTimeActivity.this, "read data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}