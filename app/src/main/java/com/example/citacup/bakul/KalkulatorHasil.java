package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class KalkulatorHasil extends Fragment {
    View rootview;
    public static ListView komponen;
    public static ArrayList<String> isiKomponen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.kalkulatorhasil, container, false);

        TextView judul = (TextView) rootview.findViewById(R.id.namamatkul);
        judul.setText(KalkulatorNilai.selected.getNama());

        isiKomponen =  new ArrayList<String>();
        isiKomponen.add("Tambah Komponen...");

        komponen = (ListView) rootview.findViewById(R.id.listkomponen);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                isiKomponen);
        komponen.setAdapter(files);
        return rootview;
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
}
