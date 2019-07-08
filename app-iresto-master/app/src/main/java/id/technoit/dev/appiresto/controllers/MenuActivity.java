package id.technoit.dev.appiresto.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import id.technoit.dev.appiresto.DaftarMahasiswa;
import id.technoit.dev.appiresto.R;
import id.technoit.dev.appiresto.TransActivity;
import id.technoit.dev.appiresto.home;
import id.technoit.dev.appiresto.transaksi;

public class MenuActivity extends AppCompatActivity {

    SessionManager sessionManager;

    ImageView mnMeja, mnMenu, mnPesanan, mnKasir, mnLaporan, mnKeluar;

    TextView txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        mnMeja = findViewById(R.id.mnMeja);
        mnMenu = findViewById(R.id.mnMenu);
        mnPesanan = findViewById(R.id.mnPesanan);
        mnKasir = findViewById(R.id.mnKasir);
        mnLaporan = findViewById(R.id.mnLaporan);
        mnKeluar = findViewById(R.id.mnKeluar);

        txtUser = findViewById(R.id.txtUser);

        mnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sintak untuk pindah activity
                Intent intent = new Intent(MenuActivity.this, DaftarMahasiswa.class);
                MenuActivity.this.startActivity(intent);
            }
        });

        // memanggil method getUserDetail untuk mengecek data user yg login
        HashMap<String, String> user = sessionManager.getUserDetail();

        // inisialisasi variabel dengan atribut data user yg login
        String vid_user = user.get(sessionManager.ID_USER);
        String vnama_user = user.get(sessionManager.NAMA_USER);

        txtUser.setText("User : " + vid_user + " | " + vnama_user);

        mnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });

    }
}
