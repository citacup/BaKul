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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.citacup.bakul.Entities.MataKuliah;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class UbahIsian1 extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    protected Spinner spinnerKategori;
    public static String[] kategoriIlkom = {"Semua Mata Kuliah", "Wajib UI", "Wajib Rumpun Sains", "Wajib Fakultas", "Wajib Jurusan Ilmu Komputer", "Pilihan Bidang Minat Fakultas", "Pilihan Bidang Minat Ilmu Komputer", "Pilihan Lain"};
    public static String[] kategoriSi = {"Semua Mata Kuliah", "Wajib UI", "Wajib Rumpun Sains", "Wajib Fakultas", "Wajib Jurusan Sistem Informasi", "Pilihan Bidang Minat Fakultas", "Pilihan Bidang Minat Sistem Informasi", "Pilihan Lain"};
    protected ListView listMatakuliah;
    protected EditText matkulcari;
    protected ImageView cari;
    public static ListView listlulus;
    public static ArrayList<String> selectedPerancangan = MyActivity.namaMatakuliah;
    public static MataKuliah selectedSimpan;
    public static MataKuliah selectedHapus;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.ubahisian1, container, false);

/////////////////////////UNTUK SEARCH KATEGORI
        spinnerKategori = (Spinner) rootview.findViewById(R.id.spinnerKategori);
        ArrayAdapter<String> spinnerAdapter = null;

        if (MyActivity.jurusan==0) {
            spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item,
                    PencarianKategori.kategoriIlkom);

            spinnerKategori.setAdapter(spinnerAdapter);
            spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Your code here
                    if (position==0) {
                        selectedPerancangan = MyActivity.databaseHelper.getAllMatakuliah();
                    } else if (position==7){
                        selectedPerancangan = MyActivity.databaseHelper.getMatakuliahfromKategori("Wajib Jurusan Ilmu Komputer");
                        selectedPerancangan.addAll(MyActivity.databaseHelper.getMatakuliahfromKategori("Pilihan Bidang Minat Ilmu Komputer"));
                    } else {
                        selectedPerancangan = MyActivity.databaseHelper.getMatakuliahfromKategori(PencarianKategori.kategoriSi[position]);
                    }
                    ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1,
                            selectedPerancangan);
                    listMatakuliah.setAdapter(files);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });
        }

        else {
            spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item,
                    PencarianKategori.kategoriSi);

            spinnerKategori.setAdapter(spinnerAdapter);
            spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Your code here
                    if (position==0) {
                        selectedPerancangan = MyActivity.databaseHelper.getAllMatakuliah();
                    } else if (position==7){
                        selectedPerancangan = MyActivity.databaseHelper.getMatakuliahfromKategori("Wajib Jurusan Sistem Informasi");
                        selectedPerancangan.addAll(MyActivity.databaseHelper.getMatakuliahfromKategori("Pilihan Bidang Minat Sistem Informasi"));
                    } else {
                        selectedPerancangan = MyActivity.databaseHelper.getMatakuliahfromKategori(PencarianKategori.kategoriSi[position]);
                    }
                    ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1,
                            selectedPerancangan);
                    listMatakuliah.setAdapter(files);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });


        }

        listMatakuliah = (ListView) rootview.findViewById(R.id.listmatakuliah);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                selectedPerancangan);
        listMatakuliah.setAdapter(files);

///////////////////////////////// UNTUK SEARCH NAMA ////////////////

        cari = (ImageView) rootview.findViewById(R.id.cari);
        matkulcari = (EditText) rootview.findViewById(R.id.matkulcari);

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPerancangan= MyActivity.databaseHelper.getMataKuliahFromNama2(matkulcari.getText().toString());
                ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        selectedPerancangan);

                listMatakuliah.setAdapter(files);
            }
        });


        listMatakuliah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSimpan = MyActivity.databaseHelper.getMatakuliahFromNama(selectedPerancangan.get(position));

                if (MyActivity.databaseHelper.cekValidLulus(selectedSimpan.getSks())) {
                    MyActivity.databaseHelper.setLulus(selectedSimpan.getNama());
                    Toast.makeText(rootview.getContext(), "Mata kuliah berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(rootview.getContext(), "Mata kuliah gagal ditambahkan!", Toast.LENGTH_SHORT).show();
                }
                listlulus = (ListView) rootview.findViewById(R.id.listlulus);
                ArrayAdapter<String> files2 = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,
                       // MyActivity.databaseHelper.getAllRancangan());
                MyActivity.databaseHelper.getMatkulLulus());
                listlulus.setAdapter(files2);

            }
        });
////////////////////////////////////////////////////////////////////////

        listlulus = (ListView) rootview.findViewById(R.id.listlulus);
        ArrayAdapter<String> files2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                // MyActivity.databaseHelper.getAllRancangan());
                MyActivity.databaseHelper.getMatkulLulus());
        listlulus.setAdapter(files2);

        listlulus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //selectedSimpan = MyActivity.databaseHelper.getMatakuliahFromNama(Pencarian.selectedPerancangan.get(position));
                MyActivity.databaseHelper.setTidakLulus(MyActivity.databaseHelper.getMatkulLulus().get(position));

                ArrayAdapter<String> files2 = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        // MyActivity.databaseHelper.getAllRancangan());
                        MyActivity.databaseHelper.getMatkulLulus());
                listlulus.setAdapter(files2);
            }
        });

        return rootview;
    }
}
