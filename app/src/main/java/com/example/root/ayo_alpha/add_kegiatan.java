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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by axellageraldinc on 9/6/16.
 */
public class add_kegiatan extends AppCompatActivity
{
    DatabaseHandler db;
    ImageButton goToHomeScreen, btnSetLoc;
    EditText textTitle, textdesc;
    TextView textDate, textTime, textLat, textLng;
    Button save;
    DatePickerFragment dp;
    TimePickerFragment tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                startActivity(myIntent);
            }

        });

        //Change font dari title, description, dan tombol save
        textDate = (TextView) findViewById(R.id.tv);
        textTime = (TextView) findViewById(R.id.tv2);
        textTitle = (EditText) findViewById(R.id.txtTitle);
        textLat = (TextView) findViewById(R.id.txtLat);
        textLng = (TextView) findViewById(R.id.txtLong);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        textTitle.setTypeface(type);
        textdesc = (EditText) findViewById(R.id.txtDesc);
        textdesc.setTypeface(type);
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
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

    public void addData() {
        db = new DatabaseHandler(this);
//        Toast.makeText(getApplicationContext(), dp.txtDay, Toast.LENGTH_SHORT).show();
        boolean isInserted = db.addEvent(textTitle.getText().toString()
                , ""
                , textDate.getText().toString()
                , textdesc.getText().toString()
                , textTime.getText().toString()
                , ""
                , "");
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

    public void writeData(Double lat, Double lng) {
        textLng.setText(String.valueOf(lat));
        textLat.setText(String.valueOf(lng));
    }

}