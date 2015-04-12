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
import android.widget.SpinnerAdapter;

import com.example.citacup.bakul.Entities.MataKuliah;

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
        //listMatakuliah.setOnItemClickListener(new AdapterView.OnItemClickListener()

		ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                MyActivity.namaMatakuliah);
        
		listKategori.setAdapter(spinnerAdapter);
        listMatakuliah.setAdapter(files);

        listMatakuliah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //     selected = MyActivity.databaseHelper.getDosenFromNama(MyActivity.namaDosen.get(position));
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pencarian.pilih = MyActivity.databaseHelper.getMatakuliahFromNama(MyActivity.namaMatakuliah.get(position));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatMatkul())
                        .commit();
            }
        });
		return rootview;
    }

}
