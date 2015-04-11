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

import com.example.citacup.bakul.Business.DatabaseHelper;

/**
 * Created by CITACUP on PPL.
 */
public class PencarianNama extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    protected ListView listMatakuliah;
    protected ListView listDosen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.pencariannama, container, false);
		
		listMatakuliah = (ListView) rootview.findViewById(R.id.listNamaMatkul);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
              MyActivity.namaMatakuliah);

        listMatakuliah.setAdapter(files);
        return rootview;
    }

}
