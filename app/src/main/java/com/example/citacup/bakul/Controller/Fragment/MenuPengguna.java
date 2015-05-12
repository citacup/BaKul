package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class MenuPengguna extends Fragment {
    protected Spinner listHuruf;
    protected Spinner listWarna;
    protected String[] huruf = {"Besar", "Sedang", "Kecil"};
    protected String[] warna = {"Biru", "Merah"};
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menupengguna, container, false);

        listHuruf = (Spinner) rootview.findViewById(R.id.spinnerHuruf);
        listWarna = (Spinner) rootview.findViewById(R.id.spinnerWarna);

        ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                huruf);
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                warna);

        listHuruf.setAdapter(spinnerAdapter1);
        listWarna.setAdapter(spinnerAdapter2);
        return rootview;
    }
}
