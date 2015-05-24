package com.example.citacup.bakul.Controller.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by CITACUP on PPL.
 */
public class LihatReview extends Fragment {
    protected ListView listReview;
    protected TextView labeltext;
    View rootview;
    private HttpPost httppost;
    private HttpResponse response;
    private HttpClient httpclient;
    private JSONObject jobject;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatreview, container, false);
        labeltext = (TextView) rootview.findViewById(R.id.labeltext);
        labeltext.setText("Review " + Pencarian.pilih.getNama());

        GetReview doItInBackGround = new GetReview(
                new ProgressDialog(getActivity()),
                getActivity());
        doItInBackGround.execute();

        return rootview;
    }

    private void JSONtoDBReview(String json) {
        // Parse String to JSON object
        try {
            JSONArray jarray = new JSONArray(json);
            generateDatabaseReview(jarray);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
    }

    private void generateDatabaseReview(JSONArray data) {
        DatabaseHelper db = MyActivity.databaseHelper;
        db.getWritableDatabase();

        if (data != null) {
            for (int i = 0; i < data.length(); i++) {
                try {
                    JSONObject obj = data.getJSONObject(i);
                    String idrev = obj.getString("idrev");
                    String nama = obj.getString("nama");
                    String komentar = obj.getString("komentar");
                    String app_flag = obj.getString("app_flag");
                    String like = obj.getString("like");
                    String dislike = obj.getString("dislike");
                    db.insertReview(idrev, nama, komentar, app_flag, like,
                            dislike);

                } catch (JSONException e) {
                    Log.e("ErrorDBDosen", e.toString());
                }

            }
        }
    }

    /**
     * task untuk review get
     */
    private class GetReview extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        private ProgressDialog dialog;
        private Activity activity;

        private GetReview(ProgressDialog dialog, Activity activity) {
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

            listReview = (ListView) rootview.findViewById(R.id.listReview);
            Pencarian.selectedReview = MyActivity.databaseHelper
                    .getReviewFromNama(Pencarian.pilih.getNama());
            ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1,
                    Pencarian.selectedReview);
            listReview.setAdapter(files);

            listReview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String a =MyActivity.databaseHelper.getReviewFromNama(Pencarian.pilih.getNama());
                    Pencarian.pilihReview = MyActivity.databaseHelper
                            .getReviewFromKomentar(Pencarian.selectedReview.get(position));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                                   .add(R.id.container, new SuatuReview())
                                   .addToBackStack(null)
                                   .commit();
                }
            });
        }

        @Override
        protected Void doInBackground(Void... params) {
            httpclient = new DefaultHttpClient();
            //url post web
            httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/getreview.php");
            //execute - means sending
            try {
                response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json + "");
                jobject = new JSONObject(json);
                Log.d("state response", jobject.getString("success"));
                if (jobject.getString("success").equals("1")) {
                    JSONtoDBReview(jobject.getString("product"));
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("Get Data", "Exception caught: ", e);
            }
            return null;
        }
    }
}
