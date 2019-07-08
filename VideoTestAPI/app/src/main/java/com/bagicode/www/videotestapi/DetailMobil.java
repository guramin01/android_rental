package com.bagicode.www.videotestapi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bagicode.www.videotestapi.Model.ModelData;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.bagicode.www.videotestapi.Model.ModelData.gambar;

public class DetailMobil extends AppCompatActivity {

    ArrayList<ModelData>datamahasiswa = new ArrayList<ModelData>();
    TextView textView;

    public Context context;
    //private DatePickerDialog datePickerDialog;
    //private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private Button btDatePicker;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);
        Button button = (Button) findViewById(R.id.tbl_pesan);
//        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//
//        tvDateResult = (TextView)findViewById(R.id.tanggalpesan);
//        btDatePicker = (Button) findViewById(R.id.tbl_tanggal);
//        btDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDateDialog();
//            }
//        });



        //
        textView=(TextView)findViewById(R.id.id_mobil);

        String ID =getIntent().getStringExtra("IdMobil");

        textView.setText(ID);

        //
        textView=(TextView)findViewById(R.id.detailnama);

        String NAMA =getIntent().getStringExtra("Nama");

        textView.setText(NAMA);

        //
        textView=(TextView)findViewById(R.id.harga);

        String HARGA =getIntent().getStringExtra("Harga");

        textView.setText(HARGA);

        //

        imageView=(ImageView)findViewById(R.id.foto);

        String GAMBAR =getIntent().getStringExtra("Gambar");


        Glide.with(getApplicationContext())
                .load("http://192.168.1.12/project_rental/rental_mobil/assets/upload/"+GAMBAR)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Saya mau pesan", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(DetailMobil.this,Transaksi.class);
//            }
//        });
    }
//    private void showDateDialog(){
//
//        /**
//         * Calendar untuk mendapatkan tanggal sekarang
//         */
//        Calendar newCalendar = Calendar.getInstance();
//
//        /**
//         * Initiate DatePicker dialog
//         */
//        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                /**
//                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
//                 */
//
//                /**
//                 * Set Calendar untuk menampung tanggal yang dipilih
//                 */
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//
//                /**
//                 * Update TextView dengan tanggal yang kita pilih
//                 */
//                tvDateResult.setText("Tanggal dipesan : "+dateFormatter.format(newDate.getTime()));
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//        /**
//         * Tampilkan DatePicker dialog
//         */
//        datePickerDialog.show();
//    }

    public void pesan(View view, int position, long id )
    {

        String id_mobil = datamahasiswa.get(position).getidMobil();
        String nama_mobil = datamahasiswa.get(position).getNama();
        String harga_mobil = datamahasiswa.get(position).getKelas_mhs();
//        String gambar = datamahasiswa.get(position).getGambar_mbl();
//        String status = datamahasiswa.get (position).getStatusmbl();

        String detailmobil = id_mobil+nama_mobil+harga_mobil+gambar;

        Intent pindah = new Intent(DetailMobil.this,Transaksi.class);
        startActivity(pindah);

        pindah.putExtra("IdMobil", id_mobil);
        pindah.putExtra("Nama", nama_mobil);
        pindah.putExtra("Harga", harga_mobil);
//        pindah.putExtra("Gambar", gambar);
//        pindah.putExtra("Status", status);

        startActivity(pindah);

        Toast.makeText(DetailMobil.this,
                "Mobil "+nama_mobil,
                Toast.LENGTH_SHORT)
                .show();
    }

}
