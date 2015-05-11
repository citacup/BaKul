package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    public static ListView listkomponen;
    public static ArrayList<String> isiKomponen = new ArrayList<String>();
    public static KomponenPenilaian dipilih;
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.kalkulatorhasil, container, false);

        TextView judul = (TextView) rootview.findViewById(R.id.namamatkul);
        ListView listkomponen = (ListView) rootview.findViewById(R.id.listkomponen);
        judul.setText(KalkulatorNilai.selected.getNama());

        isiKomponen = new ArrayList<String>();
        isiKomponen.add("+ Tambah Komponen...");


        for (int i = 0; i < MyActivity.databaseHelper.getAllKomponen().size(); i++) {
            isiKomponen.add(MyActivity.databaseHelper.getAllKomponen().get(i));
            //isiKomponen.add("Tambah Komponen...");
            Log.d("MASUK SINI", "A");
        }

        listkomponen = (ListView) rootview.findViewById(R.id.listkomponen);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, isiKomponen);
        listkomponen.setAdapter(files);

        listkomponen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Pencarian.pilih = MyActivity.databaseHelper.getMatakuliahFromNama(Pencarian.selected.get(position));

                if (position == 0) {
                    dipilih = MyActivity.databaseHelper.getKomponenFromNama(isiKomponen.get(0));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new SuatuKomponen())
                                   .commit();
                } else {
                    dipilih = MyActivity.databaseHelper
                            .getKomponenFromNama(isiKomponen.get(position));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new HasilKomponen())
                                   .commit();
                }

            }
        });

        return rootview;
    }
}
