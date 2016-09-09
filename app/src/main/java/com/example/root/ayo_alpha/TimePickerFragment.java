package com.example.root.ayo_alpha;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.app.DialogFragment;
import android.app.Dialog;
import java.util.Calendar;
import android.widget.TimePicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

//    public static String txtHour, txtMin;
    TextView tv;
    public static String text = "";
    String sminute;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //Do something with the user chosen time
        //Get reference of host activity (XML Layout File) TextView widget
        tv = (TextView) getActivity().findViewById(R.id.tv2);
//        //Set a message for user
//        tv.setText("Your chosen time is...\n\n");
//        //Display the user changed time on TextView
//        tv.setText(tv.getText()+ "Hour : " + String.valueOf(hourOfDay)
//                + "\nMinute : " + String.valueOf(minute) + "\n");
        if (minute == 0)
        {
            sminute = "00";
        }
        tv.setText(hourOfDay + ":" + sminute);
//        txtHour = String.valueOf(hourOfDay); txtMin = String.valueOf(minute);
    }

//    public String getTxtHour() {
//        return txtHour;
//    }
//
//    public String getTxtMin() {
//        return txtMin;
//    }

    public String getClock() {
        text = tv.getText().toString();
        return text;
    }
}