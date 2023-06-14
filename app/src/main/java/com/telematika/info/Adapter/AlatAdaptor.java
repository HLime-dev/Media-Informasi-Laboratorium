package com.telematika.info.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        TextView nama, deskripsi, jumlah;
        View view1 = inflater.inflate(R.layout.list_alat, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            deskripsi = view1.findViewById(R.id.deskripsi);
            jumlah = view1.findViewById(R.id.jumlah);

            nama.setText(model.get(position).getNama());
            deskripsi.setText(model.get(position).getDeskripsi());
            jumlah.setText(model.get(position).getJumlah());
        }
        return view1;
    }
}
