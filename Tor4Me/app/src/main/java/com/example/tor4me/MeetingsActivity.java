package com.example.tor4me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
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

public class MeetingsActivity extends AppCompatActivity {

    private Button returnBtn;
    private DatabaseReference mDatabase;
    private HoursAdapter hoursAdupter;
    private GridView HoursGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        returnBtn = findViewById(R.id.btn_return2);
        FirebaseApp.initializeApp(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        HoursGridView = findViewById(R.id.Hours_gridview2);
        ReadData();
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

    }
    public void ReadData()
    {
        mDatabase.child("meetings").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MeetingsActivity.this, "read data OK", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MeetingsActivity.this, "read data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}