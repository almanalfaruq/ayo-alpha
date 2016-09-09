package com.example.root.ayo_alpha;

/**
 * Created by axellageraldinc on 9/6/16.
 */
        import android.app.DialogFragment;
        import android.content.Intent;
        import android.content.res.AssetManager;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Typeface;
        import android.location.Location;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.widget.CursorAdapter;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.maps.model.LatLng;

        import java.text.DecimalFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;

public class OneFragment extends Fragment{

    DatabaseHandler db;
    Button btnShow;
    GPSTracker gpsTracker;
//    Location loc;
    Event event;
    Calendar calendar;
    Date d1 = null, d2 = null;
    int secc;
    String timeNow, timeEvent;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    float[] distance = new float[2];
    public OneFragment() {
        // Required empty public constructor
    }

    ListView listView;
    TextView activity, location, date, time, nextactivity;
    Double valueResult;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseHandler(getActivity());
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_real, container, false);

        //Ganti font di bagian tabel di homescreen
        listView = (ListView) view.findViewById(R.id.listView);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        activity = (TextView) view.findViewById(R.id.textView3);
        activity.setTypeface(type);
        location = (TextView) view.findViewById(R.id.textView4);
        location.setTypeface(type);
        date = (TextView) view.findViewById(R.id.textView5);
        date.setTypeface(type);
        time = (TextView) view.findViewById(R.id.textView11);
        time.setTypeface(type);
        btnShow = (Button) view.findViewById(R.id.btnTest);
        btnShow.setOnClickListener(new View.OnClickListener() {
            //Back
            @Override
            public void onClick(View view) {
                gpsTracker = new GPSTracker(getActivity());
                double lat = gpsTracker.getLocation().getLatitude();
                double lng = gpsTracker.getLocation().getLongitude();
                event = db.getEvent(5);
                timeEvent = event.getTime();
                calendar = Calendar.getInstance();
                timeNow = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(calendar.get(Calendar.MINUTE));
                try {
                    d2 = format.parse(timeNow);
                    Log.d("Jam Sekarang", timeNow);
                    d1 = format.parse(timeEvent);
                    long diff = d1.getTime() - d2.getTime();
                    int days = (int) (diff / (1000*60*60*24));
                    int hours = (int) ((diff - (1000*60*60*24*days)) / (1000*60*60));
                    int min = (int) (diff - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                    secc = (int) (diff - (1000*60*60*24*days) - (1000*60*60*hours)) - (1000*60*min) / (1000);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Location.distanceBetween(lat
                        , lng
                        , Double.parseDouble(event.getLatitude())
                        , Double.parseDouble(event.getLongitude())
                        , distance);
                valueResult = CalculationByDistance(lat, lng, event.getLatitude(), event.getLongitude());
                Log.d("Selisih waktu", String.valueOf(secc));
                if (distance[0] > 100.0 && secc <= 0.0) {
                    Toast.makeText(getActivity(), "Terlalu jauh dan anda terlambat", Toast.LENGTH_SHORT).show();
                } else if (distance[0] > 100.0 && secc > 0.0) {
                    Toast.makeText(getActivity(), "Terlalu jauh dan anda belum terlambat", Toast.LENGTH_SHORT).show();
                } else if (distance[0] < 100 && secc > 0.0) {
                    Toast.makeText(getActivity()
                            , "Jarak anda dengan tempat itu: " + String.valueOf(valueResult) + " meter" , Toast.LENGTH_LONG).show();
                } else if (distance[0] < 100 && secc <= 0.0) {
                    Toast.makeText(getActivity()
                            , "Jarak anda dengan tempat itu: " + String.valueOf(valueResult) + " meter, dan anda terlambat" , Toast.LENGTH_LONG).show();
                }

            }

        });
        populateListView();
        return view;

    }

    public void populateListView() {
        Cursor cursor = db.getAllData();
        String[] fieldName = new String[] {"event", "location", "date", "time"};
        int[] toView = new int[] {R.id.txtActivity, R.id.txtLocation, R.id.txtDate, R.id.txtTime};
        CursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.adapter_list, cursor, fieldName, toView, 0);
        listView.setAdapter(cursorAdapter);
    }

    public double CalculationByDistance(Double LatStartP, Double LngStartP, String LatEndP, String LngEndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = LatStartP;
        double lat2 = Double.parseDouble(LatEndP);
        double lon1 = LngStartP;
        double lon2 = Double.parseDouble(LngEndP);
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = km*1000 ;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return meter;
    }

}