package com.bagicode.www.videotestapi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Transaksi extends AppCompatActivity {

    TextView textView;


    public Context context;
    private Spinner spinersopir;
    private String [] arrsopir={"Pilih Sopir", "Menggunakan Sopir", "Tidak Menggunakan Sopir"};
    private TextInputLayout validasisopir;
    private String sopir;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult,tvDateBack;
    private Button btDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi2);
        //Button button = (Button) findViewById(R.id.tbl_pesan);
        //textView=(TextView)findViewById(R.id.id_mobil);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tvDateResult = (TextView)findViewById(R.id.tanggalpesan);
        tvDateBack = (TextView)findViewById(R.id.tanggalkembali);
        btDatePicker = (Button) findViewById(R.id.tombol_tglpesan);
        btDatePicker = (Button) findViewById(R.id.tombol_tglkbl);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
            });
        //
        textView=(TextView)findViewById(R.id.id_mobilpesan);

        String ID =getIntent().getStringExtra("IdMobil");

        textView.setText(ID);
        //
        textView=(TextView)findViewById(R.id.nama_penyewa);

        String NAMA =getIntent().getStringExtra("Nama");

        textView.setText(NAMA);
        //
        textView=(TextView)findViewById(R.id.hargasewa);

        String HARGA =getIntent().getStringExtra("Harga");

        textView.setText(HARGA);
        //
        spinersopir = (Spinner) findViewById(R.id.spinersopir);
        spinersopir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    sopir = arrsopir[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvDateResult.setText("Tanggal dipesan : "+dateFormatter.format(newDate.getTime()));
                tvDateBack.setText("Tanggal Kembali :"+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
