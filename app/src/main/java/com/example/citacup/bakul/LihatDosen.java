package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/**
 * Created by CITACUP on PPL.
 */
public class LihatDosen extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    protected final String nama = "Nama : ";
    protected final String email = "Email : ";

    private TextView namaView;
    private TextView emailView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatdosen, container, false);

        namaView = (TextView) rootview.findViewById(R.id.rinciannama);
        emailView = (TextView) rootview.findViewById(R.id.rincianemail);

        namaView.setText(nama+InformasiDosen.selected.getNama());
        emailView.setText(email+InformasiDosen.selected.getEmail());
        return rootview;
    }

}
