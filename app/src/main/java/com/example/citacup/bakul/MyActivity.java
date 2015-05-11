package com.example.citacup.bakul;

import android.app.ActionBar;
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
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.Business.JSONParser;
import com.example.citacup.bakul.Controller.Activity.Logout;
import com.example.citacup.bakul.Controller.Fragment.Faq;
import com.example.citacup.bakul.Controller.Fragment.InformasiDosen;
import com.example.citacup.bakul.Controller.Fragment.InformasiKuliah;
import com.example.citacup.bakul.Controller.Fragment.KalkulatorNilai;
import com.example.citacup.bakul.Controller.Fragment.KirimPesan;
import com.example.citacup.bakul.Controller.Fragment.LaporReview;
import com.example.citacup.bakul.Controller.Fragment.LihatMatkul;
import com.example.citacup.bakul.Controller.Fragment.LihatRancangan;
import com.example.citacup.bakul.Controller.Fragment.LihatRantai;
import com.example.citacup.bakul.Controller.Fragment.LihatReview;
import com.example.citacup.bakul.Controller.Fragment.MainMenu;
import com.example.citacup.bakul.Controller.Fragment.MenuPengguna;
import com.example.citacup.bakul.Controller.Fragment.OlahRancangan;
import com.example.citacup.bakul.Controller.Fragment.Pencarian;
import com.example.citacup.bakul.Controller.Fragment.PencarianKategori;
import com.example.citacup.bakul.Controller.Fragment.PencarianNama;
import com.example.citacup.bakul.Controller.Fragment.PerancanganKuliah;
import com.example.citacup.bakul.Controller.Fragment.PerancanganKuliah1;
import com.example.citacup.bakul.Controller.Fragment.Perbarui;
import com.example.citacup.bakul.Controller.Fragment.SuatuReview;
import com.example.citacup.bakul.Controller.Fragment.TentangKami;
import com.example.citacup.bakul.Controller.Fragment.Tutorial;
import com.example.citacup.bakul.Controller.Fragment.UbahIsian1;
import com.example.citacup.bakul.Controller.Fragment.UbahIsian2;
import com.example.citacup.bakul.Controller.Fragment.UbahSemester1;
import com.example.citacup.bakul.Controller.Fragment.UbahSemester2;
import com.example.citacup.bakul.NavigationDrawer.DividerItemDecoration;
import com.example.citacup.bakul.NavigationDrawer.MyAdapter;

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

/**
 * Main Activity
 */
public class MyActivity extends ActionBarActivity {

    public static DatabaseHelper databaseHelper;
    public static ArrayList<String> namaDosen = new ArrayList<String>();
    public static ArrayList<String> namaMatakuliah = new ArrayList<String>();
    public static ArrayList<String> listReview = new ArrayList<String>();
    //ini untuk settingan setiap user
    public static String currentUser = "";
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view
    String NAME = currentUser;
    String EMAIL = currentUser + "@ui.ac.id";
    public static int jurusan = 0;
    public static String semester;
    /**
     * Used to store for navdrawer
     */
    String TITLES[] = {
            "Halaman Utama",
            "Informasi Kuliah",
            "Perancangan Kuliah",
            "Kalkulator Nilai",
            "Tanya Jawab",
            "Panduan Pengguna",
            "Perbarui Aplikasi",
            "Pengaturan",
            "Hubungi Kami",
            "Tentang Kami",
            "Log out"
    };
    int ICONS[] = {
            R.drawable.home,
            R.drawable.informasikuliah,
            R.drawable.perancangan,
            R.drawable.kalkulator,
            R.drawable.faq,
            R.drawable.panduan,
            R.drawable.perbarui,
            R.drawable.pengaturan,
            R.drawable.hubungi,
            R.drawable.tentang,
            R.drawable.logout
    };
    int PROFILE = R.drawable.avatar1;
    RecyclerView mRecyclerView;
    // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;
    // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;
    // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    //edittext for review text
    EditText reviewText = null;
    //kirim pesan ke admin edittext
    EditText pesanAdmin = null;
    // Declaring the Toolbar Object
    //edittext untuk report review
    EditText reportMessage = null;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private Context context;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    // Declaring DrawerLayout
    private HttpPost httppost;
    // Declaring Action Bar Drawer Toggle
    private HttpClient httpclient;
    private HttpResponse response;
    private Toolbar toolbar;

    /**
     * Method onCreate, method yang pertama kali dipanggil ketika dijalankan
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mTitle = getTitle();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.logo_kecil);

        // Assigning the RecyclerView Object to the xml View
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        //mRecyclerView.addItemDecoration(
        //        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        // Letting the system know that the list objects are of fixed size
        mRecyclerView.setHasFixedSize(true);

        // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE, this);

        // Setting the adapter to RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        // Creating a layout Manager
        mLayoutManager = new LinearLayoutManager(this);

        // Setting the layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Drawer object Assigned to the view
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened
                // ( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        }; // Drawer Toggle Object Made

        // Drawer Listener set to the Drawer toggle
        Drawer.setDrawerListener(mDrawerToggle);
        // Finally we set the drawer toggle sync State
        mDrawerToggle.syncState();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.container, new MainMenu())
                       .commit();


        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.getWritableDatabase();

        new ProgressTask(MyActivity.this).execute();

        Toast.makeText(this, "Log in as " + currentUser, Toast.LENGTH_SHORT).show();
    }

    /**
     * what to do if we click on navigation drawer
     *
     * @param position position in array of list
     */
    public void onNavigationDrawerItemSelected(int position) {

        if (Drawer != null) {
            Drawer.closeDrawer(findViewById(R.id.RecyclerView));
        }

        Fragment objFragment = null;
        switch (position) {
            case 0:
                Toast.makeText(this, "Login as " + currentUser, Toast.LENGTH_SHORT).show();
                return;
            case 1:
                objFragment = new MainMenu();
                break;
            case 2:
                objFragment = new InformasiKuliah();
                break;
            case 3:
                if (MyActivity.semester == null) {
                    objFragment = new PerancanganKuliah1();
                } else {
                    objFragment = new PerancanganKuliah();
                }
                break;
            case 4:
                objFragment = new KalkulatorNilai();
                break;
            case 5:
                objFragment = new Faq();
                break;
            case 6:
                objFragment = new Tutorial();
                break;
            case 7:
                objFragment = new Perbarui();
                break;
            case 8:
                objFragment = new MenuPengguna();
                break;
            case 9:
                objFragment = new KirimPesan();
                break;
            case 10:
                objFragment = new TentangKami();
                break;
            case 11:
                this.alertMessage();
                return;
        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.container, objFragment)
                       .commit();
    }

    /**
     * what to do if we confirm to logout
     */
    public void logout() {
        databaseHelper.switchSessionPengguna(currentUser, 0);
        startActivity(new Intent(getBaseContext(), Logout.class));
        this.finish();
    }

    /**
     * what to do if we click log out on navigation drawer
     */
    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener
                = new DialogInterface.OnClickListener() {
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

    /**
     * ubah parameter mTitle dengan fragment yang dipilih
     *
     * @param number posisi yang dipilih
     */
    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = "Bantu Kuliah";
                break;
            case 1:
                mTitle = getString(R.string.title_section0);
                break;
            case 2:
                mTitle = getString(R.string.title_section1);
                break;
            case 3:
                mTitle = getString(R.string.title_section2);
                break;
            case 4:
                mTitle = getString(R.string.title_section3);
                break;
            case 5:
                mTitle = getString(R.string.title_section4);
                break;
            case 6:
                mTitle = getString(R.string.title_section5);
                break;
            case 7:
                mTitle = getString(R.string.title_section6);
                break;
            case 8:
                mTitle = getString(R.string.title_section7);
                break;
            case 9:
                mTitle = getString(R.string.title_section8);
                break;
            case 10:
                mTitle = getString(R.string.title_section9);
                break;
            case 11:
                mTitle = getString(R.string.title_section10);
                break;
        }
    }

    /**
     * ubah action bar dengan title
     */
    public void restoreActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>" + mTitle + "</font>"));
    }


    //-------------------------------LISTENER START HERE !!-------------------------------------//

    /**
     * option menu ketika di create
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!Drawer.isDrawerOpen(findViewById(R.id.RecyclerView))) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * option ketika item selected
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    /**
     * for mainMenu listener page
     *
     * @param v
     */
    public void mainMenuListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case (R.id.satulayout):
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new InformasiKuliah())
                               .commit();
                break;
            case (R.id.satuimage):
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new InformasiKuliah())
                               .commit();
                break;
            case (R.id.satutext):
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new InformasiKuliah())
                               .commit();
                break;
            case (R.id.dualayout):
                //fitur 2
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                if (semester == null) {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new PerancanganKuliah1())
                                   .commit();
                } else {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new PerancanganKuliah())
                                   .commit();
                }
                break;
            case (R.id.duaimage):
                //fitur 2
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                if (semester == null) {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new PerancanganKuliah1())
                                   .commit();
                } else {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new PerancanganKuliah())
                                   .commit();
                }
                break;
            case (R.id.duatext):
                //fitur 2
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                if (semester == null) {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new PerancanganKuliah1())
                                   .commit();
                } else {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new PerancanganKuliah())
                                   .commit();
                }
                break;
            case (R.id.tigalayout):
                //fitur 3
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new KalkulatorNilai())
                               .commit();
                break;
            case (R.id.tigaimage):
                //fitur 3
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new KalkulatorNilai())
                               .commit();
                break;
            case (R.id.tigatext):
                //fitur 3
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new KalkulatorNilai())
                               .commit();
                break;
        }
    }

    /**
     * for informasiKuliah listener page
     *
     * @param v
     */
    public void informasiKuliahListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case (R.id.matkullayout):
                //informasi matkul
                //startActivity(new Intent(getBaseContext(), Pencarian.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new Pencarian())
                               .commit();
                break;
            case (R.id.matkulimage):
                //informasi matkul
                //startActivity(new Intent(getBaseContext(), Pencarian.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new Pencarian())
                               .commit();
                break;
            case (R.id.matkultext):
                //informasi matkul
                //startActivity(new Intent(getBaseContext(), Pencarian.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new Pencarian())
                               .commit();
                break;
            case (R.id.dosenlayout):
                //informasi dosen
                //startActivity(new Intent(getBaseContext(), InformasiDosen.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new InformasiDosen())
                               .commit();
                break;
            case (R.id.dosenimage):
                //informasi dosen
                //startActivity(new Intent(getBaseContext(), InformasiDosen.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new InformasiDosen())
                               .commit();
                break;
            case (R.id.dosentext):
                //informasi dosen
                //startActivity(new Intent(getBaseContext(), InformasiDosen.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new InformasiDosen())
                               .commit();
                break;
        }
    }

    /**
     * for pencarian listener page
     *
     * @param v
     */
    public void pencarianListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case (R.id.namalayout):
                //pencarian berdasar nama
                //startActivity(new Intent(getBaseContext(), PencarianNama.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PencarianNama())
                               .commit();
                break;
            case (R.id.namaimage):
                //pencarian berdasar nama
                //startActivity(new Intent(getBaseContext(), PencarianNama.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PencarianNama())
                               .commit();
                break;
            case (R.id.namatext):
                //pencarian berdasar nama
                //startActivity(new Intent(getBaseContext(), PencarianNama.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PencarianNama())
                               .commit();
                break;
            case (R.id.kategorilayout):
                //pencarian berdasar kategori
                //startActivity(new Intent(getBaseContext(), PencarianKategori.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PencarianKategori())
                               .commit();
                break;
            case (R.id.kategoriimage):
                //pencarian berdasar kategori
                //startActivity(new Intent(getBaseContext(), PencarianKategori.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PencarianKategori())
                               .commit();
                break;
            case (R.id.kategoritext):
                //pencarian berdasar kategori
                //startActivity(new Intent(getBaseContext(), PencarianKategori.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PencarianKategori())
                               .commit();
                break;
        }
    }

    /**
     * for informasiDosen listener page
     *
     * @param v
     */
    public void informasiDosenListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.ok:
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new MainMenu())
                               .commit();
                break;
        }
    }

    /**
     * for pencarianNama listener page
     *
     * @param v
     */
    public void pencarianNamaListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.satulayout:
                //fitur 1
                //startActivity(new Intent(getBaseContext(), InformasiMatkul.class));
                break;
        }
    }

    /**
     * for pencarianKategori listener page
     *
     * @param v
     */
    public void pencarianKategoriListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.satulayout:
                //pencarian kategori
                //startActivity(new Intent(getBaseContext(), InformasiMatkul.class));
                break;
        }
    }

    /**
     * for lihatDosen listener page
     *
     * @param v
     */
    public void lihatDosenListener(View v) {
        v.startAnimation(buttonClick);
        switch (v.getId()) {
            case R.id.email:
                Log.i("Send email", "");

                String[] TO = {InformasiDosen.selected.getEmail()};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");


                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MyActivity.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    /**
     * for lihatMatkul listener page
     *
     * @param v
     */
    public void lihatMatkulListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.rantai:
                //fitur 1
                //startActivity(new Intent(getBaseContext(), LihatRantai.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatRantai())
                               .commit();
                break;
            case R.id.review:
                //fitur 1
                //startActivity(new Intent(getBaseContext(), LihatRantai.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatReview())
                               .commit();
                break;
        }
    }

    /**
     * for lihatRantai listener page
     *
     * @param v
     */
    public void lihatRantaiListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.ok:
                //tombol ok
                //startActivity(new Intent(getBaseContext(), LihatMatkul.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatMatkul())
                               .commit();
                break;
        }
    }

    /**
     * for lihatReview listener page
     *
     * @param v
     */
    public void lihatReviewListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.tambahReivew:
                reviewText = (EditText) findViewById(R.id.editTextReview);
                String pesan = reviewText.getText().toString();

                //check whether the msg empty or not
                if (pesan.length() > 0) {
                    httpclient = new DefaultHttpClient();
                    //url post web
                    httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/submitReview.php");

                    try {
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                        nameValuePairs.add(new BasicNameValuePair("komentar", pesan));
                        nameValuePairs.add(new BasicNameValuePair("idMatkul",
                                Pencarian.pilih.getKodemk()));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        KirimKomentarTask doItInBackGround = new KirimKomentarTask(
                                new ProgressDialog(this), MyActivity.this);
                        doItInBackGround.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //display message if text field is empty
                    Toast.makeText(getBaseContext(), "Review tidak boleh kosong!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * kirim komentar to web helper
     *
     * @param success
     */
    public void kirimKomentarHelper(boolean success) {
        if (success) {
            Toast.makeText(getBaseContext(), "Review Berhasil di Kirim", Toast.LENGTH_SHORT).show();
            reviewText.setText("");
        } else {
            Toast.makeText(getBaseContext(), "Review Gagal di Kirim", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * for suatuReview listener page
     *
     * @param v
     */
    public void suatuReviewListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.suka:
                httpclient = new DefaultHttpClient();
                //url post web
                httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/likeReview.php");

                try {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                    nameValuePairs.add(new BasicNameValuePair("idreview",
                            Pencarian.pilihReview.getIdrev()));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    SuatuReviewTask doItInBackGround = new SuatuReviewTask(new ProgressDialog(this),
                            MyActivity.this);
                    doItInBackGround.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatReview())
                               .commit();
                break;
            case R.id.tidaksuka:
                httpclient = new DefaultHttpClient();
                //url post web
                httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/dislikeReview.php");

                try {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                    nameValuePairs.add(new BasicNameValuePair("idreview",
                            Pencarian.pilihReview.getIdrev()));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    SuatuReviewTask doItInBackGround = new SuatuReviewTask(new ProgressDialog(this),
                            MyActivity.this);
                    doItInBackGround.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatReview())
                               .commit();
                break;
            case R.id.lapor:
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LaporReview())
                               .commit();
                break;
            case R.id.ok:
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatReview())
                               .commit();
                break;
        }
    }

    /**
     * helper untuk kirim like dislike
     *
     * @param success
     */
    public void suatuReviewHelper(boolean success) {
        if (success) {
            Toast.makeText(getBaseContext(), "Like/Dislike Telah di Tambah", Toast.LENGTH_SHORT)
                 .show();
        } else {
            Toast.makeText(getBaseContext(), "Like/Dislike Gagal di Tambah", Toast.LENGTH_SHORT)
                 .show();
        }
    }

    /**
     * for kalkulatorNilai listener page
     *
     * @param v
     */
    public void kalkulatorNilaiListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.tambah:
                //fitur 1
                //startActivity(new Intent(getBaseContext(), KalkulatorHasil.class));
                if (databaseHelper
                        .insertKalkulator(currentUser, KalkulatorNilai.selectedTambah.getNama())) {
                    ArrayAdapter<String> files = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1,
                            MyActivity.databaseHelper.getAllMatkulKalkulator());
                    KalkulatorNilai.listKalkulator.setAdapter(files);

                    KalkulatorNilai.spinnerkalkulator
                            .remove(KalkulatorNilai.selectedTambah.getNama());
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_spinner_dropdown_item,
                            KalkulatorNilai.spinnerkalkulator);
                    KalkulatorNilai.spinnermatkul.setAdapter(spinnerAdapter);
                    Toast.makeText(getBaseContext(), "Kalkulator berhasil ditambahkan",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Kalkulator gagal ditambahkan",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * for kalkulatorHasil listener page
     *
     * @param v
     */
    public void kalkulatorHasilListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.simpan:
                //simpan
                //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new KalkulatorNilai())
                               .commit();
                Toast.makeText(this, "Kalkulator nilai disimpan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hitung:
                //kalkulasi
                EditText total = (EditText) findViewById(R.id.total3);
                EditText a3 = (EditText) findViewById(R.id.A3);
                EditText am3 = (EditText) findViewById(R.id.Am3);
                EditText bp3 = (EditText) findViewById(R.id.Bp3);
                EditText b3 = (EditText) findViewById(R.id.B3);
                EditText bm3 = (EditText) findViewById(R.id.Bm3);
                EditText cp3 = (EditText) findViewById(R.id.Cp3);
                EditText c3 = (EditText) findViewById(R.id.C3);

                total.setText("0");
                a3.setText("0");
                am3.setText("0");
                bp3.setText("0");
                b3.setText("0");
                bm3.setText("0");
                cp3.setText("0");
                c3.setText("0");

                break;
            case R.id.delete:
                KalkulatorNilai.spinnerkalkulator.add(KalkulatorNilai.selected.getNama());
                if (databaseHelper
                        .deleteKalkulator(currentUser, KalkulatorNilai.selected.getNama())) {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.container, new KalkulatorNilai())
                                   .commit();
                    Toast.makeText(getBaseContext(), "Kalkulator berhasil dihapus",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Kalkulator gagal dihapus", Toast.LENGTH_SHORT)
                         .show();
                }
        }
    }

    /**
     * for perancanganKuliah first open listener page
     *
     * @param v
     */
    public void perancanganKuliah1Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.lanjut:
                //fitur 1
                databaseHelper.insertRancangan(currentUser, PerancanganKuliah1.semSelect);
                semester = PerancanganKuliah1.semSelect;

                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PerancanganKuliah())
                               .commit();
                break;
        }
    }

    /**
     * for perancanganKuliah listener page
     *
     * @param v
     */
    public void perancanganKuliahListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
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
            case (R.id.ubahlayout):
                //ubah rancangan
                //startActivity(new Intent(getBaseContext(), UbahSemester1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new UbahSemester1())
                               .commit();
                break;
            case (R.id.ubahimage):
                //ubah rancangan
                //startActivity(new Intent(getBaseContext(), UbahSemester1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new UbahSemester1())
                               .commit();
                break;
            case (R.id.ubahtext):
                //ubah rancangan
                //startActivity(new Intent(getBaseContext(), UbahSemester1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new UbahSemester1())
                               .commit();
                break;
        }
    }

    /**
     * for lihatRancangan listener page
     *
     * @param v
     */
    public void lihatRancanganListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.ok:
                //kembali
                //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new PerancanganKuliah())
                               .commit();
                break;
        }
    }

    /**
     * for ubahSemester1 yang telah lulus listener page
     *
     * @param v
     */
    public void ubahSemester1Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.s1:
                //semester
                //startActivity(new Intent(getBaseContext(), UbahIsian1.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new UbahIsian1())
                               .commit();
                break;
            case R.id.lanjut:
                //lanjut
                //startActivity(new Intent(getBaseContext(), UbahSemester2.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new OlahRancangan())
                               .commit();
                break;
        }
    }

    /**
     * for ubahIsian1 yang telah lulus listener page
     *
     * @param v
     */
    public void ubahIsian1Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.simpan:
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

    /**
     * for olahRancnagan listener page
     *
     * @param v
     */
    public void olahRancanganListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.lanjut:
                //lanjut
                //startActivity(new Intent(getBaseContext(), UbahSemester2.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new UbahSemester2())
                               .commit();
                break;
        }
    }

    /**
     * for ubahSemeter2 yang akan diambil listener page
     *
     * @param v
     */
    public void ubahSemester2Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.s1:
                //semester
                //startActivity(new Intent(getBaseContext(), UbahIsian2.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new UbahIsian2())
                               .commit();
                break;
            case R.id.lanjut:
                //lihat
                //startActivity(new Intent(getBaseContext(), LihatRancangan.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new LihatRancangan())
                               .commit();
                break;
        }
    }

    /**
     * for ubahIsian2 yang akan diambil listener page
     *
     * @param v
     */
    public void ubahIsian2Listener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.simpan:
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

    /**
     * for perbarui listener page
     *
     * @param v
     */
    public void perbaruiListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();
    }

    /**
     * for kirimPesan listener page
     *
     * @param v
     */
    public void kirimPesanListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.buttonPesan:
                //kirim pesan
                pesanAdmin = (EditText) findViewById(R.id.editTextPesan);
                String pesan = pesanAdmin.getText().toString();

                //check whether the msg empty or not
                if (pesan.length() > 0) {
                    httpclient = new DefaultHttpClient();
                    //url post web
                    httppost = new HttpPost(
                            "http://ppl-a07.cs.ui.ac.id/test/submitAdminMessage.php");

                    try {
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                        nameValuePairs.add(new BasicNameValuePair("message", pesan));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        KirimPesanTask doItInBackGround = new KirimPesanTask(
                                new ProgressDialog(this), MyActivity.this);
                        doItInBackGround.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //display message if text field is empty
                    Toast.makeText(getBaseContext(), "Pesan tidak boleh kosong!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * kirim pesan helper
     *
     * @param success
     */
    public void kirimPesanHelper(boolean success) {
        if (success) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                           .replace(R.id.container, new MainMenu())
                           .commit();
            Toast.makeText(this, "Pesan terkirim...", Toast.LENGTH_SHORT).show();
            pesanAdmin.setText("");
        } else {
            Toast.makeText(this, "Pesan gagal dikirim", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * for sendReport listener page
     *
     * @param v
     */
    public void sendReportListener(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        reportMessage = (EditText) findViewById(R.id.editTextReport);
        String pesan = reportMessage.getText().toString();

        //check whether the msg empty or not
        if (pesan.length() > 0) {
            httpclient = new DefaultHttpClient();
            //url post web
            httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/submitReport.php");

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("report", pesan));
                nameValuePairs
                        .add(new BasicNameValuePair("idreview", Pencarian.pilihReview.getIdrev()));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                //Log.d("kirim report", Pencarian.pilihReview.getIdrev());
                KirimLaporanTask doItInBackGround = new KirimLaporanTask(new ProgressDialog(this),
                        MyActivity.this);
                doItInBackGround.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //display message if text field is empty
            Toast.makeText(getBaseContext(), "Laporan tidak boleh kosong!", Toast.LENGTH_SHORT)
                 .show();
        }
    }

    /**
     * helper untuk kirim laporan
     *
     * @param success
     */
    public void kirimLaporanHelper(boolean success) {
        if (success) {
            FragmentManager fragmentManager = getFragmentManager();
            Toast.makeText(getBaseContext(), "Laporan Berhasil di Kirim", Toast.LENGTH_SHORT)
                 .show();
            reportMessage.setText("");
            fragmentManager.beginTransaction()
                           .replace(R.id.container, new SuatuReview())
                           .commit();
        } else {
            Toast.makeText(getBaseContext(), "Laporan Gagal di Kirim", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * for tutorial listener page
     *
     * @param v
     */
    public void tutorialListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.ok:
                //kirim pesan
                //startActivity(new Intent(getBaseContext(), MainMenu.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new MainMenu())
                               .commit();
                break;
        }
    }

    /**
     * for faq listener page
     *
     * @param v
     */
    public void faqListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.ok:
                //ok button
                //startActivity(new Intent(getBaseContext(), MainMenu.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new MainMenu())
                               .commit();
                break;
        }
    }

    /**
     * for tentangKami listener page
     *
     * @param v
     */
    public void tentangKamiListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.ok:
                //ok button
                //startActivity(new Intent(getBaseContext(), MainMenu.class));
                //this.finish();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new MainMenu())
                               .commit();
                break;
        }
    }

    /**
     * for menuPengguna listener page
     *
     * @param v
     */
    public void menuPenggunaListener(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.simpan:
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

    /**
     * Kelas progress task melakukan task ketika "Sedang Mengambil data"
     * sebelum mempersiapkan aplikasi
     */
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
            semester = databaseHelper.getRancangan(currentUser);

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
                for (int i = 0; i < data.length(); i++) {
                    try {
                        JSONObject obj = data.getJSONObject(i);
                        String iddosen = obj.getString("iddosen");
                        String nama = obj.getString("nama");
                        String email = obj.getString("email");

                        databaseHelper.insertDosen(iddosen, nama, email);

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
                for (int i = 0; i < data.length(); i++) {
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


                        databaseHelper
                                .insertMatakuliah(kodemk, nama, sks, semester, islulus, deskripsi,
                                        referensi, objektif, kategori);

                    } catch (JSONException e) {
                        Log.e("ErrorDBDosen", e.toString());
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
                for (int i = 0; i < data.length(); i++) {
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
                for (int i = 0; i < data.length(); i++) {
                    try {
                        JSONObject obj = data.getJSONObject(i);
                        String idrev = obj.getString("idrev");
                        String username = obj.getString("username");
                        String nama = obj.getString("nama");
                        String komentar = obj.getString("komentar");
                        String app_flag = obj.getString("app_flag");
                        String like = obj.getString("like");
                        String dislike = obj.getString("dislike");
                        databaseHelper.insertReview(idrev, username, nama, komentar, app_flag, like,
                                dislike);

                    } catch (JSONException e) {
                        Log.e("ErrorDBDosen", e.toString());
                    }

                }
            }
        }
    }

    /**
     * task for kirim komentar review
     */
    private class KirimKomentarTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        private ProgressDialog dialog;
        private MyActivity activity;

        private KirimKomentarTask(ProgressDialog dialog, MyActivity activity) {
            this.dialog = dialog;
            this.activity = activity;
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Mengirim Review");
            this.dialog.show();
            this.dialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Void v) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            kirimKomentarHelper(success);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //execute - means sending
                response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json + "");
                if (json.equals("Success")) {
                    success = true;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("kirim review", "Exception caught: ", e);
            }
            return null;
        }
    }

    /**
     * task untuk review like or dislike
     */
    private class SuatuReviewTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        private ProgressDialog dialog;
        private MyActivity activity;

        private SuatuReviewTask(ProgressDialog dialog, MyActivity activity) {
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
            suatuReviewHelper(success);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //execute - means sending
                response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json + "");
                if (json.equals("Success")) {
                    success = true;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("kirim review", "Exception caught: ", e);
            }
            return null;
        }
    }

    /**
     * task untuk kirim pesan
     */
    private class KirimPesanTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        private ProgressDialog dialog;
        private MyActivity activity;

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
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json + "");
                if (json.equals("Success")) {
                    success = true;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("Kirim pesan", "Exception caught: ", e);
            }
            return null;
        }
    }

    /**
     * task untuk kirim laporan
     */
    private class KirimLaporanTask extends AsyncTask<Void, Void, Void> {
        boolean success = false;
        private ProgressDialog dialog;
        private MyActivity activity;

        private KirimLaporanTask(ProgressDialog dialog, MyActivity activity) {
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
            kirimLaporanHelper(success);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //execute - means sending
                response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json + "");
                if (json.equals("Success")) {
                    success = true;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("kirim laporan", "Exception caught: ", e);
            }
            return null;
        }
    }

    //------------------------------------LISTENER END HERE!!------------------------------------//

}
