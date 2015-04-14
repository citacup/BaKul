package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class UbahSemester1 extends Fragment {
    View rootview;
    ArrayList<Button> lulus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.ubahsemester1, container, false);

        lulus = new ArrayList<>();
        lulus.add((Button) rootview.findViewById(R.id.s1));
        lulus.add((Button) rootview.findViewById(R.id.s2));
        lulus.add((Button) rootview.findViewById(R.id.s3));
        lulus.add((Button) rootview.findViewById(R.id.s4));
        lulus.add((Button) rootview.findViewById(R.id.s5));
        lulus.add((Button) rootview.findViewById(R.id.s6));
        lulus.add((Button) rootview.findViewById(R.id.s7));
        lulus.add((Button) rootview.findViewById(R.id.s8));

        for (int i = 0; i < Integer.parseInt(MyActivity.semester)-1; i++) {
            lulus.get(i).setVisibility(View.VISIBLE);
        }

        return rootview;
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

}
