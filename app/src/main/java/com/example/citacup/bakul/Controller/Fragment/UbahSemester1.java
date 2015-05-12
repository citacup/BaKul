package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class UbahSemester1 extends Fragment {
    View rootview;
    ArrayList<Button> lulus;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        lulus.add((Button) rootview.findViewById(R.id.s9));
        lulus.add((Button) rootview.findViewById(R.id.s10));
        lulus.add((Button) rootview.findViewById(R.id.s11));
        lulus.add((Button) rootview.findViewById(R.id.s12));

        for (int i = 0; i < Integer.parseInt(MyActivity.semester) - 1; i++) {
            lulus.get(i).setVisibility(View.VISIBLE);
        }

        return rootview;
    }

}
