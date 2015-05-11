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
public class UbahSemester2 extends Fragment {
    View rootview;
    ArrayList<Button> akan;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        akan.add((Button) rootview.findViewById(R.id.s9));
        akan.add((Button) rootview.findViewById(R.id.s10));
        akan.add((Button) rootview.findViewById(R.id.s11));
        akan.add((Button) rootview.findViewById(R.id.s12));

        for (int i = 11; i >= Integer.parseInt(MyActivity.semester) - 1; i--) {
            akan.get(i).setVisibility(View.VISIBLE);
        }

        return rootview;
    }

}
