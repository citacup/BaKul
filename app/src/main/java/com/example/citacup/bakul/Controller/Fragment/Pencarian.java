package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.example.citacup.bakul.Entities.MataKuliah;
import com.example.citacup.bakul.Entities.Review;
import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */
public class Pencarian extends Fragment {
    public static MataKuliah pilih;
    public static Review pilihReview;
    public static ArrayList<String> selected = MyActivity.namaMatakuliah;
    public static ArrayList<String> selectedReview = null;
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.pencarian, container, false);
        return rootview;
    }
}
