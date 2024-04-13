package com.example.tor4me;

public class MeetingsData
{
    public String  month;
    public int day;
    public int year;
    public String hour;

    public MeetingsData(String month, int day, int year, String hour) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
    }

    public MeetingsData()
    {

    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
