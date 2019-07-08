package com.bagicode.www.videotestapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bagicode.www.videotestapi.API.ApiService;
import com.bagicode.www.videotestapi.Adapter.ListArrayAdapter;
import com.bagicode.www.videotestapi.Model.ModelData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarMahasiswa extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ArrayList<ModelData> datamahasiswa = new ArrayList<ModelData>();
    ListView listview;
    ListArrayAdapter adapter;

    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_mahasiswa);

        Button button = (Button) findViewById(R.id.tbl_pesan);

        layout_loading = (LinearLayout) findViewById(R.id.layout_loading);

        text_load = (TextView) findViewById(R.id.text_load);
        icon_load = (ImageView) findViewById(R.id.icon_load);

        listview = (ListView) findViewById(R.id.listMhsMhs);
        listview.setOnItemClickListener(DaftarMahasiswa.this);
        listview.setDividerHeight(0);
        setup();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Saya mau pesan", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DaftarMahasiswa.this,Transaksi.class);

            }
        });

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelData>> call = service.getSemuaMhs();
        call.enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {

                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelData data = new ModelData(
                                response.body().get(i).getidMobil(),
                                response.body().get(i).getNama(),
                                response.body().get(i).getKelas_mhs(),
                                response.body().get(i).getGambar_mbl(),
                                response.body().get(i).getStatusmbl());
                        datamahasiswa.add(data);
                        Log.d("RESPON", "onResponse: " + response.body().get(i).getidMobil());

                    }



                    listview.setVisibility(View.VISIBLE);
                    adapter = new ListArrayAdapter(DaftarMahasiswa.this, R.layout.row_mahasiswa, datamahasiswa);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {
                        layout_loading.setVisibility(View.VISIBLE);
                        String error = "Daftar mahasiswa Kosong";
                        text_load.setText(error);
                        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_data_kosong);
                        icon_load.setImageBitmap(icon);
                    } else {
                        layout_loading.setVisibility(View.GONE);
                    }
                } else {
                    String error = "Error Retrive Data from Server !!!";
                    text_load.setText(error);
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                    icon_load.setImageBitmap(icon);

                }

            }

            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {
                String error = "Error Retrive Data from Server wwaau!!!\n" + t.getMessage();
                text_load.setText(error);
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                icon_load.setImageBitmap(icon);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String id_mobil = datamahasiswa.get(position).getidMobil();
        String nama_mobil = datamahasiswa.get(position).getNama();
        String harga_mobil = datamahasiswa.get(position).getKelas_mhs();
        String gambar = datamahasiswa.get(position).getGambar_mbl();
        String status = datamahasiswa.get (position).getStatusmbl();

        String detailmobil = id_mobil+nama_mobil+harga_mobil+gambar;

        Intent intent=new Intent(DaftarMahasiswa.this,DetailMobil.class);

        intent.putExtra("IdMobil", id_mobil);
        intent.putExtra("Nama", nama_mobil);
        intent.putExtra("Harga", harga_mobil);
        intent.putExtra("Gambar", gambar);
        intent.putExtra("Status", status);

        startActivity(intent);

        Toast.makeText(DaftarMahasiswa.this,
                "Mobil "+nama_mobil,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            adapter.clear();
            setup();
        }
    }
}
