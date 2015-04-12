package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.Entities.Dosen;

/**
 * Created by CITACUP on PPL.
 */
public class LihatReview extends Fragment {
    View rootview;
    protected ListView listReview;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatreview, container, false);
       listReview = (ListView) rootview.findViewById(R.id.listReview);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                 MyActivity.databaseHelper.getReviewFromNama(Pencarian.pilih.getNama()));

        listReview.setAdapter(files);


        return rootview;
    }
}
