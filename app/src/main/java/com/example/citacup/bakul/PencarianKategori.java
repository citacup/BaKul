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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * Created by CITACUP on PPL.
 */
public class PencarianKategori extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    protected ListView listMatakuliah;
    protected Spinner listKategori;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.pencariankategori, container, false);
        
		listKategori = (Spinner) rootview.findViewById(R.id.spinnerKategori);
        //ArrayAdapter<String> spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.MyActivity.namaKategori,android.R.layout.simple_spinner_item);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                MyActivity.namaKategori);

        listMatakuliah = (ListView) rootview.findViewById(R.id.listNamaMatkul);
        
		ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                MyActivity.namaMatakuliah);
        
		listKategori.setAdapter(spinnerAdapter);
        listMatakuliah.setAdapter(files);
        
		return rootview;
    }

}
