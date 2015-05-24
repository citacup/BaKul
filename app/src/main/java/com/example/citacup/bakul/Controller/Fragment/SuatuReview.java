package com.example.citacup.bakul.Controller.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.citacup.bakul.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CITACUP on PPL.
 */
public class SuatuReview extends Fragment {
    View rootview;
    private TextView jumlahsuka;
    private TextView jumlahtidaksuka;
    private TextView judul;
    private TextView isi;
    private HttpPost httppost;
    private HttpResponse response;
    private HttpClient httpclient;
    private JSONObject jobject;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.suatureview, container, false);

        isi = (TextView) rootview.findViewById(R.id.isi);
        judul = (TextView) rootview.findViewById(R.id.labeltext);
        jumlahsuka = (TextView) rootview.findViewById(R.id.jumlahsuka);
        jumlahtidaksuka = (TextView) rootview.findViewById(R.id.jumlahtidaksuka);

        httpclient = new DefaultHttpClient();
        //url post web
        httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/getlikedislike.php");

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("idreview",
                    Pencarian.pilihReview.getIdrev()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            GetLikeDislikeTask doItInBackGround = new GetLikeDislikeTask(
                    new ProgressDialog(getActivity()),
                    getActivity());
            doItInBackGround.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        isi.setText(Pencarian.pilihReview.getKomentar());
        judul.setText("Review " + Pencarian.pilihReview.getNama());

        return rootview;
    }

    /**
     * task untuk review like or dislike
     */
    private class GetLikeDislikeTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        private ProgressDialog dialog;
        private Activity activity;

        private GetLikeDislikeTask(ProgressDialog dialog, Activity activity) {
            this.dialog = dialog;
            this.activity = activity;
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Koneksi ke Server");
            this.dialog.show();
            this.dialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Void v) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                String s = Pencarian.pilihReview.getLike();
                String ts = Pencarian.pilihReview.getDislike();
                jumlahsuka.setText(s);
                jumlahtidaksuka.setText(ts);

                //execute - means sending
                response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json + "");
                jobject = new JSONObject(json);
                Log.d("state response", jobject.getString("success"));
                if (jobject.getString("success").equals("1")) {
                    String suka = jobject.getString("suka");
                    String tdksuka = jobject.getString("tdksuka");
                    jumlahsuka.setText(suka);
                    jumlahtidaksuka.setText(tdksuka);
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("Get like or dislike", "Exception caught: ", e);
            }
            return null;
        }
    }
}
