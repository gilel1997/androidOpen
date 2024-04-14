package com.example.tor4me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DayOffActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_off);
        GridView calendarGridView = findViewById(R.id.calendarGridView2);
        List<String> dates = new ArrayList<>();
        Spinner monthSpinner = findViewById(R.id.monthSpinner2);
        Spinner yearSpinner = findViewById(R.id.yearSpinner2);
        FirebaseApp.initializeApp(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMonth = parent.getItemAtPosition(position).toString();
                if(selectedMonth.equals("April") || selectedMonth.equals("June") || selectedMonth.equals("September") || selectedMonth.equals("November"))
                {
                    CreateCalendar(30,dates, calendarGridView);
                }
                else if(selectedMonth.equals("February"))
                {
                    CreateCalendar(28,dates, calendarGridView);
                }
                else
                {
                    CreateCalendar(31,dates, calendarGridView);
                }
                Toast.makeText(getApplicationContext(), "Selected Month: " + selectedMonth, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedYear = parent.getItemAtPosition(position).toString();
                // You can perform actions based on the selected year
                Toast.makeText(getApplicationContext(), "Selected Year: " + selectedYear, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the clicked date from the adapter
                String selectedDate = (String) parent.getItemAtPosition(position);
                int year = Integer.parseInt(yearSpinner.getSelectedItem().toString());
                WriteData(monthSpinner.getSelectedItem().toString(),Integer.parseInt(selectedDate),year,"0");
            }
        });
    }
    public void WriteData(String month, int day, int year, String hour) {
        if (hour.equals("0")) {
            mDatabase.child("hours of meetings").setValue(null);

        }
        else
        {
            MeetingsData meetingsData = new MeetingsData(month, day, year, hour);
            mDatabase.child("meetings").setValue(meetingsData);
        }
    }
    public void CreateCalendar(int index, List<String> dates, GridView calendarGridView)
    {
        dates.clear();
        CalendarAdapter calendarAdapter = new CalendarAdapter(this, dates);
        calendarGridView.setAdapter(calendarAdapter);
        for (int i = 1; i <= index; i++)
        {
            dates.add(String.valueOf(i));
        }
        calendarGridView = findViewById(R.id.calendarGridView2);
        calendarAdapter = new CalendarAdapter(this, dates);
        calendarGridView.setAdapter(calendarAdapter);
    }
}
