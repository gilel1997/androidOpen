package com.example.tor4me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        GridView calendarGridView = findViewById(R.id.calendarGridView);
        List<String> dates = new ArrayList<>();
        Spinner monthSpinner = findViewById(R.id.monthSpinner);
        Spinner yearSpinner = findViewById(R.id.yearSpinner);

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

                // Start a new activity passing the selected date as an extra
                Intent intent = new Intent(CreateMeetingActivity.this, SetTimeActivity.class);
                intent.putExtra("selected_day", selectedDate);
                intent.putExtra("selected_month", monthSpinner.getSelectedItem().toString());
                intent.putExtra("selected_Year", yearSpinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
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
        calendarGridView = findViewById(R.id.calendarGridView);
        calendarAdapter = new CalendarAdapter(this, dates);
        calendarGridView.setAdapter(calendarAdapter);
    }
}