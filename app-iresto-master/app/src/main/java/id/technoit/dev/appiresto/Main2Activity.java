package id.technoit.dev.appiresto;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;

import id.technoit.dev.appiresto.controllers.SessionManager;

public class Main2Activity extends AppCompatActivity {
    public static final String ROOT_URL = "http://192.168.43.139/project_rental/api_mobil/index.php/api/";

//    SessionManager sessionManager;
//    TextView txtUser1;
//    TextView mnKeluar;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();


////        mnKeluar = findViewById(R.id.nav_manage);
//        txtUser1 = findViewById(R.id.txtUser1);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);
//        sessionManager = new SessionManager(getApplicationContext());

//        // memanggil method getUserDetail untuk mengecek data user yg login
//        HashMap<String, String> user = sessionManager.getUserDetail();
//
//        // inisialisasi variabel dengan atribut data user yg login
//        String vid_user = user.get(sessionManager.ID_USER);
//        String vnama_user = user.get(sessionManager.NAMA_USER);
//
//        txtUser1.setText("User : " + vid_user + " | " + vnama_user);
//

//        HashMap<String, String> user = sessionManager.getUserDetail();
//        String vid_user = user.get(sessionManager.ID_USER);
//        String vnama_user = user.get(sessionManager.NAMA_USER);
//        txtUser1.setText("User : " + vid_user + " | " + vnama_user);
//        sessionManager.checkLogin();
//        // memanggil method getUserDetail untuk mengecek data user yg login
//        HashMap<String, String> user = sessionManager.getUserDetail();
//
//        // inisialisasi variabel dengan atribut data user yg login
//        String vid_user = user.get(sessionManager.ID_USER);
//        String vnama_user = user.get(sessionManager.NAMA_USER);
//
//        txtUser1.setText("User : " + vid_user + " | " + vnama_user);
////        mnKeluar.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                sessionManager.logout();
////            }
////        });

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);

                return true;
            }
        });
    }

    //method untuk eksekusi action dari tiap menu item
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;

        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                //action
                break;
            case R.id.nav_gallery:
                //action
                break;
            case R.id.nav_slideshow:
                //action
                break;
            case R.id.nav_manage:
                //action
                break;

        }


        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}