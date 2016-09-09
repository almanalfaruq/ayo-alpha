package com.example.root.ayo_alpha;

/**
 * Created by axellageraldinc on 9/6/16.
 */
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TwoFragment extends Fragment{

    public TwoFragment() {
        // Required empty public constructor
    }

    TextView persen, totals, lates, ontimes;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.conclusion, container, false);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        persen = (TextView) view.findViewById(R.id.persent);
        persen.setTypeface(type);
        totals = (TextView) view.findViewById(R.id.total);
        totals.setTypeface(type);
        ontimes = (TextView) view.findViewById(R.id.ontime);
        ontimes.setTypeface(type);
        lates = (TextView) view.findViewById(R.id.late);
        lates.setTypeface(type);
        return view;
    }

}