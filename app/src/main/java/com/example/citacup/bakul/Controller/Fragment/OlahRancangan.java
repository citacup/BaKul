package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class OlahRancangan extends Fragment {
    public static ArrayList<TextView> textLihat;
    public static ArrayList<ListView> listLihat;


    public static TextView skslulus;
    public static TextView wajibui;
    public static TextView wajibsains;
    public static TextView wajibfakultas;
    public static TextView wajibprodi;
    public static TextView pilihanbidangminat;
    public static TextView sisa;
    public static TextView pilihanlain;

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
        rootview = inflater.inflate(R.layout.olahrancangan, container, false);

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
        TextView pilihanlain = (TextView) rootview.findViewById(R.id.pilihanlain);
        pilihanlain.setText(Integer.toString(hasil[6]));


        TextView sisawajibui = (TextView) rootview.findViewById(R.id.sisawajibui);
        sisawajibui.setText(Integer.toString(18 - hasil[1]));
        TextView sisawajibsains = (TextView) rootview.findViewById(R.id.sisawajibsains);
        sisawajibsains.setText(Integer.toString(5 - hasil[2]));
        TextView sisawajibfakultas = (TextView) rootview.findViewById(R.id.sisawajibfakultas);
        sisawajibfakultas.setText(Integer.toString(43 - hasil[3]));
        TextView sisawajibprodi = (TextView) rootview.findViewById(R.id.sisawajibprodi);
        sisawajibprodi.setText(Integer.toString(47 - hasil[4]));
        TextView sisapilihanbidangminat = (TextView) rootview
                .findViewById(R.id.sisapilihanbidangminat);
        sisapilihanbidangminat.setText(Integer.toString(21 - hasil[5]));
        TextView sisapilihanlain = (TextView) rootview.findViewById(R.id.sisapilihanlain);
        sisapilihanlain.setText(Integer.toString(10 - hasil[6]));

        TextView sisasks = (TextView) rootview.findViewById(R.id.sisasks);
        sisasks.setText(Integer.toString(144 - total));

        return rootview;
    }

}
