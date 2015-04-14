package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class UbahSemester2 extends Fragment {
    View rootview;
    ArrayList<Button> akan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.ubahsemester2, container, false);

        akan = new ArrayList<>();
        akan.add((Button) rootview.findViewById(R.id.s1));
        akan.add((Button) rootview.findViewById(R.id.s2));
        akan.add((Button) rootview.findViewById(R.id.s3));
        akan.add((Button) rootview.findViewById(R.id.s4));
        akan.add((Button) rootview.findViewById(R.id.s5));
        akan.add((Button) rootview.findViewById(R.id.s6));
        akan.add((Button) rootview.findViewById(R.id.s7));
        akan.add((Button) rootview.findViewById(R.id.s8));

        for (int i = 7; i >= Integer.parseInt(MyActivity.semester)-1; i--) {
            akan.get(i).setVisibility(View.VISIBLE);
        }

        return rootview;
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

}
