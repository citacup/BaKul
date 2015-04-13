package com.example.citacup.bakul;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.Entities.Dosen;

/**
 * Created by CITACUP on PPL.
 */
public class LihatReview extends Fragment {
    View rootview;
    protected ListView listReview;
    protected TextView labeltext;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatreview, container, false);
        labeltext = (TextView) rootview.findViewById(R.id.labeltext);
        labeltext.setText("Review "+Pencarian.pilih.getNama());

        listReview = (ListView) rootview.findViewById(R.id.listReview);
        Pencarian.selectedReview = MyActivity.databaseHelper.getReviewFromNama(Pencarian.pilih.getNama());
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                Pencarian.selectedReview);
        listReview.setAdapter(files);

        listReview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String a =MyActivity.databaseHelper.getReviewFromNama(Pencarian.pilih.getNama());
                Pencarian.pilihReview = MyActivity.databaseHelper.getReviewFromKomentar(Pencarian.selectedReview.get(position));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new SuatuReview())
                        .commit();
            }
        });

        return rootview;
    }
}
