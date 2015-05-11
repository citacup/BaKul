package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class PerancanganKuliah1 extends Fragment {
    public static String semSelect;
    protected Spinner semester;
    protected String[] sem = {"1", "2", "3", "4", "5", "6", "7", "8" };
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.perancangankuliah1, container, false);
        semester = (Spinner) rootview.findViewById(R.id.semester);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                sem);
        semester.setAdapter(spinnerAdapter);

        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view, int position, long l) {
                // Your code here
                semSelect = sem[position];
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        return rootview;
    }
}
