package com.example.citacup.bakul.Controller.Fragment;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class PencarianNama extends Fragment {
    protected ListView listMatakuliah;
    protected ListView listDosen;
    protected ImageView cari;
    protected EditText kolomcari;
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.pencariannama, container, false);
        listMatakuliah = (ListView) rootview.findViewById(R.id.listNamaMatkul);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                Pencarian.selected);
        listMatakuliah.setAdapter(files);

        cari = (ImageView) rootview.findViewById(R.id.cari);
        kolomcari = (EditText) rootview.findViewById(R.id.kolomCari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pencarian.selected = MyActivity.databaseHelper
                        .getMataKuliahFromNama2(kolomcari.getText().toString());
                ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        Pencarian.selected);

                listMatakuliah.setAdapter(files);
            }
        });


        listMatakuliah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pencarian.pilih = MyActivity.databaseHelper
                        .getMatakuliahFromNama(Pencarian.selected.get(position));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatMatkul())
                               .commit();
            }
        });

        return rootview;
    }
}