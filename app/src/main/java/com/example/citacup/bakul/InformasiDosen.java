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
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.citacup.bakul.Entities.Dosen;

import java.util.ArrayList;

/**
 * Created by CITACUP on PPL.
 */

public class InformasiDosen extends Fragment {
    View rootview;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    protected ListView listDosen;
    public static Dosen selected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.informasidosen, container, false);
        listDosen = (ListView) rootview.findViewById(R.id.listDosen);
        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                MyActivity.namaDosen);

        listDosen.setAdapter(files);

        listDosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                //Toast.makeText(getApplicationContext(),
                //        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                selected = MyActivity.databaseHelper.getDosenFromNama(MyActivity.namaDosen.get(position));

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatDosen())
                        .commit();
            }
        });

        return rootview;
    }

}
