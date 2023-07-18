package com.telematika.info.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.telematika.info.R;

import java.util.ArrayList;

public class AlatAdaptor extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<GetDataAlat> model;

    public AlatAdaptor(Context context, ArrayList<GetDataAlat> model) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nama, kategori, jumlah;
        ImageView foto;
        View view1 = inflater.inflate(R.layout.list_alat, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            kategori = view1.findViewById(R.id.kategori);
            jumlah = view1.findViewById(R.id.jumlah);
            foto = view1.findViewById(R.id.foto);

            nama.setText(model.get(position).getName());
            kategori.setText(model.get(position).getKategori());
            jumlah.setText(model.get(position).getJumlah());
            Glide.with(context)
                    .load(model.get(position).getImage())
                    .into(foto);
        }
        return view1;
    }
}
