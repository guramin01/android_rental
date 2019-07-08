package id.technoit.dev.appiresto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.technoit.dev.appiresto.API.ApiService;
import id.technoit.dev.appiresto.Adapter.ListArrayAdapter;
import id.technoit.dev.appiresto.Adapter.ListArrayAdapterTrans;
import id.technoit.dev.appiresto.Model.ModelData;
import id.technoit.dev.appiresto.Model.ModelTrans;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class transaksi extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ArrayList<ModelTrans> datatransaksi = new ArrayList<ModelTrans>();
    ListView listview;
    ListArrayAdapterTrans adapter;

    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);layout_loading = (LinearLayout) findViewById(R.id.layout_loading);

        text_load = (TextView) findViewById(R.id.text_load);
        icon_load = (ImageView) findViewById(R.id.icon_load);

        listview = (ListView) findViewById(R.id.listTrans);
        listview.setOnItemClickListener(transaksi.this);



        listview.setDividerHeight(0);
        setup();

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TransActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelTrans>> call = service.getSemuaTransaksi();
        call.enqueue(new Callback<List<ModelTrans>>() {
            @Override
            public void onResponse(Call<List<ModelTrans>> call, Response<List<ModelTrans>> response) {

                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelTrans data = new ModelTrans(
                                response.body().get(i).getKD(),
                                response.body().get(i).getNama(),
                                response.body().get(i).getHarga());
//                                response.body().get(i).getGambar_mbl());
                        datatransaksi.add(data);
                        Log.d("RESPON", "onResponse: " + response.body().get(i).getKD());

                    }



                    listview.setVisibility(View.VISIBLE);
                    adapter = new ListArrayAdapterTrans(transaksi.this, R.layout.row_transaksi, datatransaksi);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {
                        layout_loading.setVisibility(View.VISIBLE);
                        String error = "Daftar Transaksi Kosong";
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
            public void onFailure(Call<List<ModelTrans>> call, Throwable t) {
                String error = "Error Retrive Data from Server wwaau!!!\n" + t.getMessage();
                text_load.setText(error);
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                icon_load.setImageBitmap(icon);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String id_mobil = datatransaksi.get(position).getKD();
        String nama_mobil = datatransaksi.get(position).getNama();
        String harga_mobil = datatransaksi.get(position).getHarga();
//        String gambar = datatransaksi.get(position).getGambar_mbl();

//        String detailmobil = id_mobil+nama_mobil+harga_mobil+gambar;

        Intent intent=new Intent(transaksi.this,DetailMobil.class);

//        intent.putExtra("KD", id_transaksi);
//        intent.putExtra("Nama", nama);
//        intent.putExtra("Harga", total_harga);
//        intent.putExtra("Gambar", gambar);

        startActivity(intent);

//        Toast.makeText(transaksi.this,
//                "trans "+nama,
//                Toast.LENGTH_SHORT)
//                .show();
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

