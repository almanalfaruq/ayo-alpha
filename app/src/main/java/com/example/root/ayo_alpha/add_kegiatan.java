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
import android.widget.Toast;

/**
 * Created by axellageraldinc on 9/6/16.
 */
public class add_kegiatan extends AppCompatActivity
{
    DatabaseHandler db;
    ImageButton goToHomeScreen;
    EditText textTitle, textdesc;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_kegiatan);

        //Tombol batal (tanda x) di klik, balik ke homescreen
        goToHomeScreen = (ImageButton) findViewById(R.id.batal_button);
        goToHomeScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }

        });

        //Change font dari title, description, dan tombol save
        textTitle = (EditText) findViewById(R.id.txtTitle);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        textTitle.setTypeface(type);
        textdesc = (EditText) findViewById(R.id.txtDesc);
        textdesc.setTypeface(type);
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        save = (Button) findViewById(R.id.btnSave);
        save.setTypeface(type2);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addData();
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
            }

        });
    }

    public void addData() {
        db = new DatabaseHandler(this);
        boolean isInserted = db.addEvent(textTitle.getText().toString(), "", "", textdesc.getText().toString(), "");
        if (isInserted)
            Toast.makeText(getApplicationContext(), textTitle.getText().toString() + " is added", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Can't insert data", Toast.LENGTH_LONG).show();
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