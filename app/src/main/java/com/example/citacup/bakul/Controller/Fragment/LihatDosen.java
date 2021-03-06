package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class LihatDosen extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);
    private TextView namaView;
    private TextView emailView;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatdosen, container, false);

        namaView = (TextView) rootview.findViewById(R.id.rinciannama);
        emailView = (TextView) rootview.findViewById(R.id.rincianemail);

        namaView.setText(InformasiDosen.selected.getNama());
        emailView.setText(InformasiDosen.selected.getEmail());
        return rootview;
    }

}
