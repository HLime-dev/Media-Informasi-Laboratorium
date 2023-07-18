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

public class RuanganAdaptor extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<GetDataRuangan> model;

    public RuanganAdaptor(Context context, ArrayList<GetDataRuangan> model) {
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
        TextView nama;
        ImageView foto;
        View view1 = inflater.inflate(R.layout.list_ruangan, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            foto = view1.findViewById(R.id.foto);

            nama.setText(model.get(position).getNama());
            Glide.with(context)
                    .load(model.get(position).getImage())
                    .into(foto);
        }
        return view1;
    }
}
