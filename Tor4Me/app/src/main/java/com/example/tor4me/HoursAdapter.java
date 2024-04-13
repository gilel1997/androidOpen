package com.example.tor4me;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class HoursAdapter extends BaseAdapter
{
    private Context mContext;
    private List<MeetingsData> mDates;

    public HoursAdapter(Context context, List<MeetingsData> dates) {
        mContext = context;
        mDates = dates;
    }

    @Override
    public int getCount() {
        return mDates.size();
    }

    @Override
    public Object getItem(int position) {
        return mDates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 100)); // Adjust height as needed
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
        } else {
            textView = (TextView) convertView;
        }

        // Set the date text
        textView.setText(mDates.get(position).hour);

        return textView;
    }
}