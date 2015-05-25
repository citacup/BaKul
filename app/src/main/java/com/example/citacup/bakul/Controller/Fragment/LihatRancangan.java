package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by CITACUP on PPL.
 */
public class LihatRancangan extends Fragment {
    public static ArrayList<TextView> textLihat;
    public static ArrayList<ListView> listLihat;

    public static String[] kategoriIlkom = {"Semua Mata Kuliah", "Wajib UI", "Wajib Rumpun Sains",
            "Wajib Fakultas", "Wajib Jurusan Ilmu Komputer", "Pilihan Bidang Minat Fakultas",
            "Pilihan Bidang Minat Ilmu Komputer", "Pilihan Lain"};
    public static String[] kategoriSi = {"Semua Mata Kuliah", "Wajib UI", "Wajib Rumpun Sains",
            "Wajib Fakultas", "Wajib Jurusan Sistem Informasi", "Pilihan Bidang Minat Fakultas",
            "Pilihan Bidang Minat Sistem Informasi", "Pilihan Lain"};
    int[] hasil = new int[kategoriIlkom.length];


    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatrancangan, container, false);

        TextView skslulus = (TextView) rootview.findViewById(R.id.skslulus);
        int total = 0;

        for (int i = 0; i < MyActivity.databaseHelper.getSKSMatkulLulus().size(); i++) {
            total = total + MyActivity.databaseHelper.getSKSMatkulLulus().get(i);
        }
        skslulus.setText(Integer.toString(total));

        if (MyActivity.jurusan == 0) {
            for (int i = 0; i < kategoriIlkom.length; i++) {
                int tot = 0;
                for (int j = 0; j <
                        MyActivity.databaseHelper.getSKSMatkulLulusKategori(kategoriIlkom[i])
                                                 .size(); j++) {
                    tot = tot +
                            MyActivity.databaseHelper.getSKSMatkulLulusKategori(kategoriIlkom[i])
                                                     .get(j);
                }
                hasil[i] = tot;
            }
        } else {
            for (int i = 0; i < kategoriSi.length; i++) {
                int tot = 0;
                for (int j = 0;
                     j < MyActivity.databaseHelper.getSKSMatkulLulusKategori(kategoriSi[i]).size();
                     j++) {
                    tot = tot + MyActivity.databaseHelper.getSKSMatkulLulusKategori(kategoriSi[i])
                                                         .get(j);
                }
                hasil[i] = tot;
            }
        }

        TextView wajibui = (TextView) rootview.findViewById(R.id.wajibui);
        wajibui.setText(Integer.toString(hasil[1]));
        TextView wajibsains = (TextView) rootview.findViewById(R.id.wajibsains);
        wajibsains.setText(Integer.toString(hasil[2]));
        TextView wajibfakultas = (TextView) rootview.findViewById(R.id.wajibfakultas);
        wajibfakultas.setText(Integer.toString(hasil[3]));
        TextView wajibprodi = (TextView) rootview.findViewById(R.id.wajibprodi);
        wajibprodi.setText(Integer.toString(hasil[4]));
        TextView pilihanbidangminat = (TextView) rootview.findViewById(R.id.pilihanbidangminat);
        pilihanbidangminat.setText(Integer.toString(hasil[5]));
        TextView sisa = (TextView) rootview.findViewById(R.id.sisa);
        sisa.setText(Integer.toString(144 - total));
        TextView pilihanlain = (TextView) rootview.findViewById(R.id.pilihanlain);
        pilihanlain.setText(Integer.toString(hasil[6]));

        textLihat = new ArrayList<>();
        textLihat.add((TextView) rootview.findViewById(R.id.s1));
        textLihat.add((TextView) rootview.findViewById(R.id.s2));
        textLihat.add((TextView) rootview.findViewById(R.id.s3));
        textLihat.add((TextView) rootview.findViewById(R.id.s4));
        textLihat.add((TextView) rootview.findViewById(R.id.s5));
        textLihat.add((TextView) rootview.findViewById(R.id.s6));
        textLihat.add((TextView) rootview.findViewById(R.id.s7));
        textLihat.add((TextView) rootview.findViewById(R.id.s8));
        textLihat.add((TextView) rootview.findViewById(R.id.s9));
        textLihat.add((TextView) rootview.findViewById(R.id.s10));
        textLihat.add((TextView) rootview.findViewById(R.id.s11));
        textLihat.add((TextView) rootview.findViewById(R.id.s12));

        listLihat = new ArrayList<>();
        listLihat.add((ListView) rootview.findViewById(R.id.sem1));
        listLihat.add((ListView) rootview.findViewById(R.id.sem2));
        listLihat.add((ListView) rootview.findViewById(R.id.sem3));
        listLihat.add((ListView) rootview.findViewById(R.id.sem4));
        listLihat.add((ListView) rootview.findViewById(R.id.sem5));
        listLihat.add((ListView) rootview.findViewById(R.id.sem6));
        listLihat.add((ListView) rootview.findViewById(R.id.sem7));
        listLihat.add((ListView) rootview.findViewById(R.id.sem8));
        listLihat.add((ListView) rootview.findViewById(R.id.sem9));
        listLihat.add((ListView) rootview.findViewById(R.id.sem10));
        listLihat.add((ListView) rootview.findViewById(R.id.sem11));
        listLihat.add((ListView) rootview.findViewById(R.id.sem12));

        for (int i = 0; i < Integer.parseInt(MyActivity.semester) - 1; i++) {
            textLihat.get(i).setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < Integer.parseInt(MyActivity.semester) - 1; i++) {
            listLihat.get(i).setVisibility(View.VISIBLE);
        }

        for (int i = 1; i < Integer.parseInt(MyActivity.semester); i++) {
            ArrayList matkulLulus = MyActivity.databaseHelper.getMatkulLulus2(Integer.toString(i));
            Collections.sort(matkulLulus);

            ArrayAdapter<String> files2 = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1,
                    matkulLulus);

            listLihat.get(i - 1).setAdapter(files2);
            setListViewHeightBasedOnChildren(listLihat.get(i - 1));

        }
        return rootview;
    }

}
