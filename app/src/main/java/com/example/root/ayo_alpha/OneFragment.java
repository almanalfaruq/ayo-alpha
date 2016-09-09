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

public class OneFragment extends Fragment{

    DatabaseHandler db;
    Button btnShow;
    GPSTracker gpsTracker;
    LocationManager lm;
    public OneFragment() {
        // Required empty public constructor
    }

    ListView listView;
    TextView activity, location, date, time, nextactivity;
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
                if(gpsTracker.canGetLocation()) {
                    double lat = gpsTracker.getLatitude();
                    double lng = gpsTracker.getLongitude();
                    Toast.makeText(getActivity(), "Latitude: " + String.valueOf(lat)
                            + "\nLogitude: " + String.valueOf(lng), Toast.LENGTH_LONG).show();
                }
                else {
                    gpsTracker.showSettingsAlert();
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

    public double CalculationByDistance(LatLng StartP, String LatEndP, String LngEndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = Double.parseDouble(LatEndP);
        double lon1 = StartP.longitude;
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
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return valueResult;
    }

}