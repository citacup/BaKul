package com.example.citacup.bakul.Controller.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.example.citacup.bakul.Controller.Activity.Login;
import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class Perbarui extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.perbarui, container, false);
        /**new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                new AlertDialog.Builder(getActivity())
                        .setMessage("Sistem telah terbaharui")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // whatever...
                            }
                        }).create().show();
            }

            private void finish() {
                // TODO Auto-generated method stub
            }
        }, cek);*/
        return rootview;
    }

}
