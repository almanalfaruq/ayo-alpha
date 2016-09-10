package com.example.root.ayo_alpha;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by axellageraldinc on 9/6/16.
 */
public class add_kegiatan extends AppCompatActivity
{
    DatabaseHandler db;
    ImageButton goToHomeScreen;
    EditText textTitle, textdesc, txtPlace;
    TextView textDate, textTime, textLat, textLng, Date, Time, Location;
    String lat,lng;
    Button save;
    DatePickerFragment dp;
    TimePickerFragment tp;
    ImageButton btnSetLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onResume();
        setContentView(R.layout.add_kegiatan);

        //Tombol batal (tanda x) di klik, balik ke homescreen
        goToHomeScreen = (ImageButton) findViewById(R.id.batal_button);
        goToHomeScreen.setOnClickListener(new View.OnClickListener() {
            //Back
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }

        });

        btnSetLoc = (ImageButton) findViewById(R.id.btnSetLoc);
        btnSetLoc.setOnClickListener(new View.OnClickListener() {
            //Back
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), MapScreen.class);
                startActivityForResult(myIntent, 1);
            }

        });

        //Change font dari title, description, dan tombol save
        Date = (TextView) findViewById(R.id.textView6);
        Time = (TextView) findViewById(R.id.textView2);
        Location = (TextView) findViewById(R.id.textView);
        textDate = (TextView) findViewById(R.id.tv);
        textTime = (TextView) findViewById(R.id.tv2);
        textTitle = (EditText) findViewById(R.id.txtTitle);
        txtPlace = (EditText) findViewById(R.id.txtPlace);
        textLat = (TextView) findViewById(R.id.txtLat);
        textLng = (TextView) findViewById(R.id.txtLong);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        textTitle.setTypeface(type);
        textdesc = (EditText) findViewById(R.id.txtDesc);
        textdesc.setTypeface(type);
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        Date.setTypeface(type2);
        Time.setTypeface(type2);
        Location.setTypeface(type2);
        save = (Button) findViewById(R.id.btnSave);
        save.setTypeface(type2);
        save.setOnClickListener(new View.OnClickListener() {
            //Back
            @Override
            public void onClick(View view) {
                addData();
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }

        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                lat = data.getStringExtra("latitude");
                lng = data.getStringExtra("longitude");
                textLat.setText(lat);
                textLng.setText(lng);
            }
        }
    }

    public void addData() {
        db = new DatabaseHandler(this);
//        Toast.makeText(getApplicationContext(), dp.txtDay, Toast.LENGTH_SHORT).show();
        boolean isInserted = db.addEvent(textTitle.getText().toString()
                , txtPlace.getText().toString()
                , textDate.getText().toString()
                , textdesc.getText().toString()
                , textTime.getText().toString()
                , textLat.getText().toString()
                , textLng.getText().toString()
                ,0);
        if (isInserted)
            Toast.makeText(getApplicationContext()
                    , textTitle.getText().toString() + " is added"
                    , Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Can't insert data", Toast.LENGTH_SHORT).show();
    }

    //OnClick SetDate
    public void onButtonClicked(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }

    //OnClick SetTime
    public void onButtonClicked2 (View v){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"TimePicker");
    }


}