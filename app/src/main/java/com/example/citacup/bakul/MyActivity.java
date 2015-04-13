package com.example.citacup.bakul;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.Business.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Context context;
    public static DatabaseHelper databaseHelper;
    public static ArrayList<String> namaDosen = new ArrayList<String>();
    public static ArrayList<String> namaMatakuliah = new ArrayList<String>();
    public static ArrayList<String> listReview = new ArrayList<String>();

    public static String currentUser = "";
    public static int jurusan = 0;

     /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private HttpPost httppost;
    private HttpClient httpclient;
    private HttpResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.getWritableDatabase();

        new ProgressTask(MyActivity.this).execute();

        Toast.makeText(this, "Log in as "+currentUser, Toast.LENGTH_SHORT).show();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private MyActivity activity;

        public ProgressTask(MyActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Sedang mengambil data...");
            this.dialog.show();
            this.dialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            jurusan = databaseHelper.whoHasSession().getJurusan();

            namaDosen = databaseHelper.getAllDosen();
            namaMatakuliah = databaseHelper.getAllMatakuliah();
            //namaKategori = databaseHelper.getAllKategori();
            listReview = databaseHelper.getAllKategori();

            databaseHelper.close();
        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            String urlDosen = "http://mahasiswa.cs.ui.ac.id/~tondhy.eko/ppl/dosen.json";
            String urlMatakuliah = "http://mahasiswa.cs.ui.ac.id/~tondhy.eko/ppl/matakuliah.json";
            String urlKategori = "http://mahasiswa.cs.ui.ac.id/~tondhy.eko/ppl/kategori.json";
            String urlReview = "http://mahasiswa.cs.ui.ac.id/~tondhy.eko/ppl/review.json";
            JSONtoDBDosen(urlDosen);
            JSONtoDBMatakuliah(urlMatakuliah);
            JSONtoDBKategori(urlKategori);
            JSONtoDBReview(urlReview);

            return null;
        }

        private void JSONtoDBDosen(String url) {
            databaseHelper = new DatabaseHelper(getApplicationContext());
            databaseHelper.getWritableDatabase();

            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.getJSONFromUrl(url);
            generateDatabaseDosen(json);

        }

        private void generateDatabaseDosen(JSONArray data) {
            if (data != null) {
                for (int i = 0; i <data.length(); i++) {
                    try {
                        JSONObject obj = data.getJSONObject(i);
                        String iddosen = obj.getString("iddosen");
                        String nama = obj.getString("nama");
                        String email = obj.getString("email");

                        databaseHelper.insertDosen(iddosen, nama,email);

                    } catch (JSONException e) {
                        Log.e("ErrorDBDosen", e.toString());
                    }

                }
            }
        }

        private void JSONtoDBMatakuliah(String url) {
            databaseHelper = new DatabaseHelper(getApplicationContext());
            databaseHelper.getWritableDatabase();

            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.getJSONFromUrl(url);
            generateDatabaseMatakuliah(json);

        }
        private void generateDatabaseMatakuliah(JSONArray data) {
            if (data != null) {
                for (int i = 0; i <data.length(); i++) {
                    try {
                        JSONObject obj = data.getJSONObject(i);
                        String kodemk = obj.getString("kodemk");
                        String nama = obj.getString("nama");
                        String sks = obj.getString("sks");
                        String semester = obj.getString("semester");
                        String islulus = obj.getString("islulus");
                        String deskripsi = obj.getString("deskripsi");
                        String referensi = obj.getString("referensi");
                        String objektif = obj.getString("objektif");
                        String kategori = obj.getString("kategori");


                        databaseHelper.insertMatakuliah(kodemk,nama,sks,semester,islulus,deskripsi,referensi,objektif,kategori);

                    } catch (JSONException e) {
                        Log.e("ErrorDBDosen", e.toString());
                    }

                }
            }
        }
    }

    private void JSONtoDBKategori(String url) {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.getWritableDatabase();

        JSONParser jParser = new JSONParser();
        JSONArray json = jParser.getJSONFromUrl(url);
        generateDatabaseKategori(json);

    }

    private void generateDatabaseKategori(JSONArray data) {
        if (data != null) {
            for (int i = 0; i <data.length(); i++) {
                try {
                    JSONObject obj = data.getJSONObject(i);
                    String kategori = obj.getString("kategori");

                    databaseHelper.insertKategori(kategori);

                } catch (JSONException e) {
                    Log.e("ErrorDBDosen", e.toString());
                }

            }
        }
    }

    private void JSONtoDBReview(String url) {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.getWritableDatabase();

        JSONParser jParser = new JSONParser();
        JSONArray json = jParser.getJSONFromUrl(url);
        generateDatabaseReview(json);

    }

    private void generateDatabaseReview(JSONArray data) {
        if (data != null) {
            for (int i = 0; i <data.length(); i++) {
                try {
                    JSONObject obj = data.getJSONObject(i);
                    String idrev = obj.getString("idrev");
                    String username = obj.getString("username");
                    String nama = obj.getString("nama");
                    String komentar = obj.getString("komentar");
                    String app_flag = obj.getString("app_flag");
                    String like = obj.getString("like");
                    String dislike = obj.getString("dislike");
                    databaseHelper.insertReview(idrev,username,nama,komentar,app_flag,like,dislike);

                } catch (JSONException e) {
                    Log.e("ErrorDBDosen", e.toString());
                }

            }
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment objFragment = null;

        switch (position) {
            case 0:
                objFragment = new MainMenu();
                break;
            case 1:
                objFragment = new InformasiKuliah();
                break;
            case 2:
                objFragment = new PerancanganKuliah1();
                break;
            case 3:
                objFragment = new KalkulatorNilai();
                break;
            case 4:
                objFragment = new Tutorial();
                break;
            case 5:
                objFragment = new Faq();
                break;
            case 6:
                objFragment = new MenuPengguna();
                break;
            case 7:
                objFragment = new KirimPesan();
                break;
            case 8:
                objFragment = new Perbarui();
                break;
            case 9:
                this.alertMessage();
                return;
        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section0);
                break;
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
            case 8:
                mTitle = getString(R.string.title_section8);
                break;
            case 9:
                mTitle = getString(R.string.title_section9);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.my, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MyActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    public void logout() {
        databaseHelper.switchSessionPengguna(currentUser, 0);
        startActivity(new Intent(getBaseContext(), Logout.class));
        this.finish();
    }

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                    // Yes button clicked
                        logout();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                    // No button clicked
                    // do nothing
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda yakin keluar dari sistem?")
                .setPositiveButton("Ya", dialogClickListener)
                .setNegativeButton("Tidak", dialogClickListener)
                .show();
    }

    public void mainMenuListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case (R.id.satulayout) :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new InformasiKuliah())
                        .commit();
                break;
            case (R.id.satuimage) :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new InformasiKuliah())
                        .commit();
                break;
            case (R.id.satutext) :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new InformasiKuliah())
                        .commit();
                break;
            case (R.id.dualayout):
                //fitur 2
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PerancanganKuliah1())
                        .commit();
                break;
            case (R.id.duaimage):
                //fitur 2
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PerancanganKuliah1())
                        .commit();
                break;
            case (R.id.duatext):
                //fitur 2
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PerancanganKuliah1())
                        .commit();
                break;
            case (R.id.tigalayout) :
                //fitur 3
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new KalkulatorNilai())
                        .commit();
                break;
            case (R.id.tigaimage) :
                //fitur 3
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new KalkulatorNilai())
                        .commit();
                break;
            case (R.id.tigatext) :
                //fitur 3
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new KalkulatorNilai())
                        .commit();
                break;
        }
    }

    public void informasiKuliahListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case (R.id.matkullayout) :
                //informasi matkul
                //startActivity(new Intent(getBaseContext(), Pencarian.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new Pencarian())
                        .commit();
                break;
            case (R.id.matkulimage) :
                //informasi matkul
                //startActivity(new Intent(getBaseContext(), Pencarian.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new Pencarian())
                        .commit();
                break;
            case (R.id.matkultext) :
                //informasi matkul
                //startActivity(new Intent(getBaseContext(), Pencarian.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new Pencarian())
                        .commit();
                break;
            case (R.id.dosenlayout) :
                //informasi dosen
                //startActivity(new Intent(getBaseContext(), InformasiDosen.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new InformasiDosen())
                        .commit();
                break;
            case (R.id.dosenimage) :
                //informasi dosen
                //startActivity(new Intent(getBaseContext(), InformasiDosen.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new InformasiDosen())
                        .commit();
                break;
            case (R.id.dosentext) :
                //informasi dosen
                //startActivity(new Intent(getBaseContext(), InformasiDosen.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new InformasiDosen())
                        .commit();
                break;
        }
    }

    public void pencarianListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case (R.id.namalayout) :
                //pencarian berdasar nama
                //startActivity(new Intent(getBaseContext(), PencarianNama.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PencarianNama())
                        .commit();
                break;
            case (R.id.namatext) :
                //pencarian berdasar nama
                //startActivity(new Intent(getBaseContext(), PencarianNama.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PencarianNama())
                        .commit();
                break;
            case (R.id.kategorilayout) :
                //pencarian berdasar kategori
                //startActivity(new Intent(getBaseContext(), PencarianKategori.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PencarianKategori())
                        .commit();
                break;
            case (R.id.kategoritext) :
                //pencarian berdasar kategori
                //startActivity(new Intent(getBaseContext(), PencarianKategori.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PencarianKategori())
                        .commit();
                break;
        }
    }

    public void informasiDosenListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.satulayout:
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
                break;
        }
    }

    public void pencarianNamaListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.satulayout :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiMatkul.class));
                break;
        }
    }

    public void pencarianKategoriListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.satulayout :
                //pencarian kategori
                //startActivity(new Intent(getBaseContext(), InformasiMatkul.class));
                break;
        }
    }

    public void lihatDosenListener(View v) {
        v.startAnimation(buttonClick);
        switch(v.getId()) {
            case R.id.email :
                Log.i("Send email", "");

                String[] TO = {InformasiDosen.selected.getEmail()};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");


                emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MyActivity.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
        }
    }
	
	public void lihatMatkulListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.rantai :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), LihatRantai.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatRantai())
                        .commit();
                break;
            case R.id.review :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), LihatRantai.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatReview())
                        .commit();
                break;
        }
    }

    public void lihatRantaiListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.ok :
                //tombol ok
                //startActivity(new Intent(getBaseContext(), LihatMatkul.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatMatkul())
                        .commit();
                break;
        }
    }

    public void lihatReviewListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.tambah :
                //tombol ok
                //startActivity(new Intent(getBaseContext(), LihatMatkul.class));
                //this.finish();
                Toast.makeText(this, "Review dikirim...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void suatuReviewListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.suka :
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatReview())
                        .commit();
                break;
            case R.id.tidaksuka :
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatReview())
                        .commit();
                break;
            case R.id.lapor :
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LaporReview())
                        .commit();
                break;
            case R.id.ok :
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatReview())
                        .commit();
                break;
        }
    }

    public void kalkulatorNilaiListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.tambah :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), KalkulatorHasil.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new KalkulatorHasil())
                        .commit();
                break;
        }
    }

    public void kalkulatorHasilListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.simpan :
                //simpan
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new KalkulatorNilai())
                        .commit();
                Toast.makeText(this, "Kalkulator nilai disimpan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hitung :
                //kalkulasi
                break;
        }
    }

    public void perancanganKuliah1Listener(View v){
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.lanjut :
                //fitur 1
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PerancanganKuliah())
                        .commit();
                break;
        }
    }

    public void perancanganKuliahListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case (R.id.lihatlayout):
                //lihat rancangan
                //startActivity(new Intent(getBaseContext(), LihatRancangan.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatRancangan())
                        .commit();
                break;
            case (R.id.lihatimage):
                //lihat rancangan
                //startActivity(new Intent(getBaseContext(), LihatRancangan.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatRancangan())
                        .commit();
                break;
            case (R.id.lihattext):
                //lihat rancangan
                //startActivity(new Intent(getBaseContext(), LihatRancangan.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatRancangan())
                        .commit();
                break;
            case (R.id.ubahlayout) :
                //ubah rancangan
                //startActivity(new Intent(getBaseContext(), UbahSemester1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahSemester1())
                        .commit();
                break;
            case (R.id.ubahimage) :
                //ubah rancangan
                //startActivity(new Intent(getBaseContext(), UbahSemester1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahSemester1())
                        .commit();
                break;
            case (R.id.ubahtext) :
                //ubah rancangan
                //startActivity(new Intent(getBaseContext(), UbahSemester1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahSemester1())
                        .commit();
                break;
        }
    }

    public void lihatRancanganListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.ok :
                //kembali
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PerancanganKuliah())
                        .commit();
                break;
        }
    }

    public void ubahSemester1Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.s1 :
                //semester
                //startActivity(new Intent(getBaseContext(), UbahIsian1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahIsian1())
                        .commit();
                break;
            case R.id.lanjut :
                //lanjut
                //startActivity(new Intent(getBaseContext(), UbahSemester2.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahSemester2())
                        .commit();
                break;
        }
    }

    public void ubahIsian1Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.simpan :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), UbahSemester1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahSemester1())
                        .commit();
                Toast.makeText(this, "Isian disimpan", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void ubahSemester2Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.s1 :
                //semester
                //startActivity(new Intent(getBaseContext(), UbahIsian2.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahIsian2())
                        .commit();
                break;
            case R.id.lihat :
                //lihat
                //startActivity(new Intent(getBaseContext(), LihatRancangan.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new LihatRancangan())
                        .commit();
                break;
        }
    }

    public void ubahIsian2Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.simpan :
                //fitur 1
                //startActivity(new Intent(getBaseContext(), UbahSemester2.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UbahSemester2())
                        .commit();
                Toast.makeText(this, "Isian disimpan", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void perbaruiListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();
    }

    public void kirimPesanListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.buttonPesan :
                //kirim pesan
                //startActivity(new Intent(getBaseContext(), MainMenu.class));
                //this.finish();
                EditText pesanAdmin = (EditText) findViewById(R.id.editTextPesan);
                String  pesan = pesanAdmin.getText().toString();

                //check whether the msg empty or not
                if(pesan.length()>0) {
                    httpclient = new DefaultHttpClient();
                    //url post web
                    httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/submitAdminMessage.php");

                    try {
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                        nameValuePairs.add(new BasicNameValuePair("message", pesan));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        KirimPesanTask doItInBackGround = new KirimPesanTask(new ProgressDialog(this), MyActivity.this);
                        doItInBackGround.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //display message if text field is empty
                    Toast.makeText(getBaseContext(), "Pesan Kosong", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void kirimPesanHelper(boolean success){
        if(success) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new MainMenu())
                    .commit();
            Toast.makeText(this, "Pesan terkirim...", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Pesan gagal dikirim", Toast.LENGTH_SHORT).show();
        }
    }

    private class KirimPesanTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private MyActivity activity;
        boolean success = false;

        private KirimPesanTask(ProgressDialog dialog, MyActivity activity) {
            this.dialog = dialog;
            this.activity = activity;
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Mengirim Pesan");
            this.dialog.show();
            this.dialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Void v) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            kirimPesanHelper(success);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //execute - means sending
                response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json+"");
                if(json.equals("Success")){
                    success = true;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("Login SSO", "Exception caught: ", e);
            }
            return null;
        }
    }

    public void tutorialListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.ok :
                //kirim pesan
                //startActivity(new Intent(getBaseContext(), MainMenu.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MainMenu())
                        .commit();
                break;
        }
    }

    public void faqListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.ok :
                //ok button
                //startActivity(new Intent(getBaseContext(), MainMenu.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MainMenu())
                        .commit();
                break;
        }
    }

    public void menuPenggunaListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch(v.getId()) {
            case R.id.simpan :
                //simpan menu pengguna
                //startActivity(new Intent(getBaseContext(), MainMenu.class));
                //this.finish();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MainMenu())
                        .commit();
                Toast.makeText(this, "Menu pengguna disimpan", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
