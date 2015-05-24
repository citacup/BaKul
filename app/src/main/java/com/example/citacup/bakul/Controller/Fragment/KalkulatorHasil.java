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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.citacup.bakul.Entities.KomponenPenilaian;
import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class KalkulatorHasil extends Fragment {
    public static ArrayList<String> isiKomponen = new ArrayList<String>();
    public static KomponenPenilaian dipilih;
    public static ImageView hitung;
    public static int test;
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.kalkulatorhasil, container, false);

        TextView judul = (TextView) rootview.findViewById(R.id.namamatkul);
        judul.setText(KalkulatorNilai.selected.getNama());
        hitung = (ImageView) rootview.findViewById(R.id.hitung);
        final EditText total = (EditText) rootview.findViewById(R.id.total3);
        final EditText menujuA = (EditText) rootview.findViewById(R.id.A3);
        final EditText menujuAm = (EditText) rootview.findViewById(R.id.Am3);
        final EditText menujuBp = (EditText) rootview.findViewById(R.id.Bp3);
        final EditText menujuB = (EditText) rootview.findViewById(R.id.B3);
        final EditText menujuBm = (EditText) rootview.findViewById(R.id.Bm3);
        final EditText menujuCp = (EditText) rootview.findViewById(R.id.Cp3);
        final EditText menujuC = (EditText) rootview.findViewById(R.id.C3);
        isiKomponen = new ArrayList<String>();
        isiKomponen.add("+ Tambah Komponen...");


        for (int i = 0; i <
                MyActivity.databaseHelper.getKomponenFromMatkul2(KalkulatorNilai.selected.getNama())
                                         .size(); i++) {
            isiKomponen.add(MyActivity.databaseHelper
                    .getKomponenFromMatkul2(KalkulatorNilai.selected.getNama()).get(i));
        }

        ListView listkomponen = (ListView) rootview.findViewById(R.id.listkomponen);
        final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, isiKomponen);
        listkomponen.setAdapter(files);

        listkomponen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    dipilih = MyActivity.databaseHelper.getKomponenFromNama(isiKomponen.get(0));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                                   .add(R.id.container, new NewKomponen())
                                   .addToBackStack(null)
                                   .commit();
                } else {
                    dipilih = MyActivity.databaseHelper
                            .getKomponenFromNama(isiKomponen.get(position));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                                   .add(R.id.container, new EditKomponen())
                                   .addToBackStack(null)
                                   .commit();
                }
            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nilai = 0;
                String matkul = KalkulatorNilai.selected.getNama();
                for (int i = 0;
                     i < MyActivity.databaseHelper.getBobotKomponenFromMatkul(matkul).size(); i++) {
                    double bobot =
                            MyActivity.databaseHelper.getNilaiKomponenFromMatkul(matkul).get(i) /
                                    100.0;
                    test = MyActivity.databaseHelper.getBobotKomponenFromMatkul(matkul).get(0);
                    nilai += MyActivity.databaseHelper.getBobotKomponenFromMatkul(matkul).get(i) *
                            bobot;
                }

                //set nilai yang dibutuhkan
                total.setText(String.valueOf(nilai));
                menujuA.setText(String.valueOf(85 - nilai));
                menujuAm.setText(String.valueOf(80 - nilai));
                menujuBp.setText(String.valueOf(75 - nilai));
                menujuB.setText(String.valueOf((70 - nilai)));
                menujuBm.setText(String.valueOf((65 - nilai)));
                menujuCp.setText(String.valueOf((60 - nilai)));
                menujuC.setText(String.valueOf((55 - nilai)));
            }
        });

        return rootview;
    }
}
