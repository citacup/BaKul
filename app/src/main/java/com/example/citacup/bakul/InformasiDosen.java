package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */

public class InformasiDosen extends Fragment {
    View rootview;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    protected ListView listDosen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.informasidosen, container, false);
        listDosen = (ListView) rootview.findViewById(R.id.listDosen);

        //String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
         //       "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
         //       "Linux", "OS/2" };

        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                MyActivity.namaDosen);

        listDosen.setAdapter(files);
        return rootview;
    }

}
