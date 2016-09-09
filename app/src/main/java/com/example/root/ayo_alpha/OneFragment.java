package com.example.root.ayo_alpha;

/**
 * Created by axellageraldinc on 9/6/16.
 */
        import android.content.res.AssetManager;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.widget.CursorAdapter;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

public class OneFragment extends Fragment{

    DatabaseHandler db;
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
}