package id.technoit.dev.appiresto;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailMobil extends AppCompatActivity {

    TextView textView;


    public Context context;
    private Spinner spinnerSopir, spinnerBank;
    private String [] arrSopir = {"-Pilih Sopir-", "Menggunakan Sopir", "Tidak Menggunakan Sopir"}, arrBank={"-Pilih Bank-", "BRI", "Bank BCA", "Bank Mandiri"};
    private TextView ttlharga, tvDateResult, tvDateBack;
    private Button btnpesan, btDatePicker, btDateKbl;
    private TextInputLayout validasiSopir, validasiBank;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String bank, sopir;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tvDateResult = (TextView)findViewById(R.id.tanggalpesan);
        tvDateBack = (TextView)findViewById(R.id.tanggalkembali);
        btDatePicker = (Button)findViewById(R.id.tombol_tglpesan);
        btDateKbl = (Button)findViewById(R.id.tombol_tglkembali);

        btDatePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showDateDialog();
            }
        });


        //
        textView=(TextView)findViewById(R.id.id_transaksi);


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
                .load("http://192.168.43.139/project_rental/rental_mobil/assets/upload/"+GAMBAR)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
        //
        validasiBank =findViewById(R.id.validasiBank);
        validasiSopir =findViewById(R.id.validasiSopir);
        btnpesan = findViewById(R.id.btnpesan);

        spinnerBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    bank = arrBank[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSopir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    sopir = arrSopir[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> arrayBank = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrBank);
        arrayBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBank.setAdapter(arrayBank);

        ArrayAdapter<String> arraySopir = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrSopir);
        arraySopir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSopir.setAdapter(arraySopir);






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
