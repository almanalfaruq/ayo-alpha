package com.example.root.ayo_alpha;

/**
 * Created by axellageraldinc on 9/6/16.
 */
        import android.database.Cursor;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

public class OneFragment extends Fragment{

    DatabaseHandler db = new DatabaseHandler(getActivity());
    public OneFragment() {
        // Required empty public constructor
    }

    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_real, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        return view;

    }

    public void populateListView() {
        Cursor cursor = db.getAllData();
        String[] fieldName = new String[] {"event", "location", "date", "time"};
        int[] toView = new int[] {R.id.txtActivity, R.id.txtLocation, R.id.txtDate, R.id.txtTime};
        SimpleCursorAdapter simpleCursorAdapter;
        simpleCursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.adapter_list, cursor, fieldName, toView, 0);
        listView.setAdapter(simpleCursorAdapter);
    }
}