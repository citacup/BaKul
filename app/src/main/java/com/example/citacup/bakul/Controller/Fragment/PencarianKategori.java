package com.example.citacup.bakul.Controller.Fragment;

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

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class PencarianKategori extends Fragment {
    public static String[] kategoriIlkom = {"Semua Mata Kuliah", "Wajib UI", "Wajib Rumpun Sains",
            "Wajib Fakultas", "Wajib Jurusan Ilmu Komputer", "Pilihan Bidang Minat Fakultas",
            "Pilihan Bidang Minat Ilmu Komputer", "Pilihan Lain" };
    public static String[] kategoriSi = {"Semua Mata Kuliah", "Wajib UI", "Wajib Rumpun Sains",
            "Wajib Fakultas", "Wajib Jurusan Sistem Informasi", "Pilihan Bidang Minat Fakultas",
            "Pilihan Bidang Minat Sistem Informasi", "Pilihan Lain" };
    protected ListView listMatakuliah;
    protected Spinner listKategori;
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.pencariankategori, container, false);

        listKategori = (Spinner) rootview.findViewById(R.id.spinnerKategori);
        ArrayAdapter<String> spinnerAdapter = null;

        if (MyActivity.jurusan == 0) {
            spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item,
                    PencarianKategori.kategoriIlkom);
            listKategori.setAdapter(spinnerAdapter);

            listKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(
                        AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Your code here
                    if (position == 0) {
                        Pencarian.selected = MyActivity.databaseHelper.getAllMatakuliah();
                    } else if (position == 7) {
                        Pencarian.selected = MyActivity.databaseHelper
                                .getMatakuliahfromKategori("Wajib Jurusan Sistem Informasi");
                        Pencarian.selected.addAll(MyActivity.databaseHelper
                                .getMatakuliahfromKategori(
                                        "Pilihan Bidang Minat Sistem Informasi"));
                    } else {
                        Pencarian.selected = MyActivity.databaseHelper.getMatakuliahfromKategori(
                                PencarianKategori.kategoriIlkom[position]);
                    }
                    ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1,
                            Pencarian.selected);
                    listMatakuliah.setAdapter(files);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });
        } else {
            spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item,
                    PencarianKategori.kategoriSi);
            listKategori.setAdapter(spinnerAdapter);

            listKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(
                        AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Your code here
                    if (position == 0) {
                        Pencarian.selected = MyActivity.databaseHelper.getAllMatakuliah();
                    } else if (position == 7) {
                        Pencarian.selected = MyActivity.databaseHelper
                                .getMatakuliahfromKategori("Wajib Jurusan Ilmu Komputer");
                        Pencarian.selected.addAll(MyActivity.databaseHelper
                                .getMatakuliahfromKategori("Pilihan Bidang Minat Ilmu Komputer"));
                    } else {
                        Pencarian.selected = MyActivity.databaseHelper
                                .getMatakuliahfromKategori(PencarianKategori.kategoriSi[position]);
                    }
                    ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1,
                            Pencarian.selected);
                    listMatakuliah.setAdapter(files);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });
        }

        listMatakuliah = (ListView) rootview.findViewById(R.id.listNamaMatkul);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                Pencarian.selected);
        listMatakuliah.setAdapter(files);

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
