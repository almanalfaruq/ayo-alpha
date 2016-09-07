package com.example.root.ayo_alpha;

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

/**
 * Created by axellageraldinc on 9/6/16.
 */
public class add_kegiatan extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_kegiatan);

        ImageButton goToHomeScreen = (ImageButton) findViewById(R.id.batal_button);
        goToHomeScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });
        EditText textTitle = (EditText) findViewById(R.id.txtTitle);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
        textTitle.setTypeface(type);
        EditText textdesc = (EditText) findViewById(R.id.txtDesc);
        textdesc.setTypeface(type);
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        Button save = (Button) findViewById(R.id.btnSave);
        save.setTypeface(type2);
    }
}