package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class LihatRancangan extends Fragment {
    View rootview;
    public static ArrayList<TextView> textLihat;
    public static ArrayList<ListView> listLihat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatrancangan, container, false);

        textLihat = new ArrayList<>();
        textLihat.add((TextView) rootview.findViewById(R.id.s1));
        textLihat.add((TextView) rootview.findViewById(R.id.s2));
        textLihat.add((TextView) rootview.findViewById(R.id.s3));
        textLihat.add((TextView) rootview.findViewById(R.id.s4));
        textLihat.add((TextView) rootview.findViewById(R.id.s5));
        textLihat.add((TextView) rootview.findViewById(R.id.s6));
        textLihat.add((TextView) rootview.findViewById(R.id.s7));
        textLihat.add((TextView) rootview.findViewById(R.id.s8));

        listLihat = new ArrayList<>();
        listLihat.add((ListView) rootview.findViewById(R.id.sem1));
        listLihat.add((ListView) rootview.findViewById(R.id.sem2));
        listLihat.add((ListView) rootview.findViewById(R.id.sem3));
        listLihat.add((ListView) rootview.findViewById(R.id.sem4));
        listLihat.add((ListView) rootview.findViewById(R.id.sem5));
        listLihat.add((ListView) rootview.findViewById(R.id.sem6));
        listLihat.add((ListView) rootview.findViewById(R.id.sem7));
        listLihat.add((ListView) rootview.findViewById(R.id.sem8));

        for (int i = 0; i < Integer.parseInt(MyActivity.semester)-1; i++) {
            textLihat.get(i).setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < Integer.parseInt(MyActivity.semester)-1; i++) {
            listLihat.get(i).setVisibility(View.VISIBLE);
        }

        ArrayAdapter<String> files2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                // MyActivity.databaseHelper.getAllRancangan());
                MyActivity.databaseHelper.getMatkulLulus());
        listLihat.get(0).setAdapter(files2);
        return rootview;
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

}
