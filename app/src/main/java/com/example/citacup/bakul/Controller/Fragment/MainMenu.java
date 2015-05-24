package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class MainMenu extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.mainmenu, container, false);

        switch (MyActivity.user.getTema()) {
            case 1:
                ((LinearLayout) rootview.findViewById(R.id.mainlayout)).setBackgroundResource(
                        R.color.birumuda);
                break;
            case 2:
                ((LinearLayout) rootview.findViewById(R.id.mainlayout)).setBackgroundResource(
                        R.color.merahmuda);
                break;
            case 3:
                ((LinearLayout) rootview.findViewById(R.id.mainlayout)).setBackgroundResource(
                        R.color.kuningmuda);
                break;
            case 4:
                ((LinearLayout) rootview.findViewById(R.id.mainlayout)).setBackgroundResource(
                        R.color.orenmuda);
                break;
            case 5:
                ((LinearLayout) rootview.findViewById(R.id.mainlayout)).setBackgroundResource(
                        R.color.hijaumuda);
                break;
            case 6:
                ((LinearLayout) rootview.findViewById(R.id.mainlayout)).setBackgroundResource(
                        R.color.ungumuda);
                break;
        }

        return rootview;
    }
}
