package com.example.citacup.bakul;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.Entities.MataKuliah;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class KalkulatorNilai extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    public static MataKuliah selectedTambah;
    public static MataKuliah selected;
    public static ListView listKalkulator;
    public static Spinner spinnermatkul;
    public static ArrayList<String> spinnerkalkulator = MyActivity.namaMatakuliah;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.kalkulatornilai, container, false);

        spinnermatkul = (Spinner) rootview.findViewById(R.id.spinnermatkul);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerkalkulator);
        spinnermatkul.setAdapter(spinnerAdapter);
        spinnermatkul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Your code here
                selectedTambah = MyActivity.databaseHelper.getMatakuliahFromNama(spinnerkalkulator.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        listKalkulator = (ListView) rootview.findViewById(R.id.listkalkulator);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                MyActivity.databaseHelper.getAllMatkulKalkulator());
        listKalkulator.setAdapter(files);

        listKalkulator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseHelper db = MyActivity.databaseHelper;
                selected = db.getMatakuliahFromNama(db.getAllMatkulKalkulator().get(position));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new KalkulatorHasil())
                        .commit();
            }
        });
        return rootview;
    }
}
