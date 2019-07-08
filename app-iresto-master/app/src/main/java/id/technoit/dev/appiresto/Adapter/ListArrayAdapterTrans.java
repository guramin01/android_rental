package id.technoit.dev.appiresto.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import id.technoit.dev.appiresto.Model.ModelData;
import id.technoit.dev.appiresto.Model.ModelTrans;
import id.technoit.dev.appiresto.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by JhonDev on 07/10/2016.
 */

public class ListArrayAdapterTrans extends ArrayAdapter<ModelTrans> {

    private ArrayList<ModelTrans> list;
    private LayoutInflater inflater;
    private int res;
    public Context context;

    public ListArrayAdapterTrans(Context context, int resource, ArrayList<ModelTrans> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        MyHolder holder = null;


        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new MyHolder();

            holder.KD = (TextView) convertView.findViewById(R.id.listKD);
            holder.Nama = (TextView) convertView.findViewById(R.id.listNama);
            holder.Harga = (TextView) convertView.findViewById(R.id.listHargaTotal);


            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

        holder.KD.setText("Kode Transaksi: "+list.get(position).getKD());
        holder.Nama.setText("Nama  : "+list.get(position).getNama());
        holder.Harga.setText("Harga Total : Rp."+list.get(position).getHarga());

//        Glide.with(context)
//                .load("http://192.168.43.139/project_rental/rental_mobil/assets/upload/"+list.get(position).getGambar_mbl())
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.Gambar);


        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(ModelTrans object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static class MyHolder {

        TextView KD;
        TextView Nama;
        TextView Harga;
        ImageView Gambar;


    }
}
