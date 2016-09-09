package com.example.root.ayo_alpha;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.ayo_alpha.R;

/**
 * Created by root on 07/09/16.
 */
public class ListAdapter extends AppCompatActivity {
    TextView activity, location, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_list);

        activity = (TextView) findViewById(R.id.txtActivity);
        location = (TextView) findViewById(R.id.txtLocation);
        date = (TextView) findViewById(R.id.txtDate);
        time = (TextView) findViewById(R.id.txtTime);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        activity.setTypeface(type);
        location.setTypeface(type);
        date.setTypeface(type);
        time.setTypeface(type);

//    public ListAdapter(Context context, Cursor cursor) {
//        super(context, cursor, 0);
//    }
//
//    // The newView method is used to inflate a new view and return it,
//    // you don't bind any data to the view at this point.
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        return LayoutInflater.from(context).inflate(R.layout.adapter_list, parent, false);
//    }
//
//    // The bindView method is used to bind all data to a given view
//    // such as setting the text on a TextView.
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        // Find fields to populate in inflated template
//        TextView txtActivity = (TextView) view.findViewById(R.id.txtActivity);
//        TextView txtLocation = (TextView) view.findViewById(R.id.txtLocation);
//        TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
//        TextView txtTime = (TextView) view.findViewById(R.id.txtTime);
//        // Extract properties from cursor
//        String event = cursor.getString(cursor.getColumnIndexOrThrow("event"));
//        String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
//        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
//        String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
//        // Populate fields with extracted properties
//        txtActivity.setText(event);
//        txtLocation.setText(location);
//        txtDate.setText(date);
//        txtTime.setText(time);
//    }
    }
}