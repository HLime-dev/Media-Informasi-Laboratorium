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

public class PraktikumAdaptor extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetDataPraktikum> model;

    public PraktikumAdaptor (Context context, ArrayList<GetDataPraktikum> model) {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.model=model;
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
        TextView name, tanggal, tempat;
        ImageView foto;
        View view1 = inflater.inflate(R.layout.list_praktikum, null);
        if (view1 != null) {
            name = view1.findViewById(R.id.nama);
            tempat = view1.findViewById(R.id.tempat);
            tanggal = view1.findViewById(R.id.tanggal);
            foto = view1.findViewById(R.id.foto);

            name.setText(model.get(position).getName());
            tempat.setText(model.get(position).getTempat());
            tanggal.setText(model.get(position).getTanggal());

            Glide.with(context)
                    .load(model.get(position).getImage())
                    .into(foto);
        }
        return view1;
    }
}
