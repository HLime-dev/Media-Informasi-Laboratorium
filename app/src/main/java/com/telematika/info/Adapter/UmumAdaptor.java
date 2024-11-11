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

public class UmumAdaptor extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<GetDataUmum> model;

    public UmumAdaptor(Context context, ArrayList<GetDataUmum> model) {
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
        TextView nama, info1, info2, info3;
        ImageView foto;
        View view1 = inflater.inflate(R.layout.list_umum, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            info1 = view1.findViewById(R.id.info1);
            info2 = view1.findViewById(R.id.info2);
            info3 = view1.findViewById(R.id.info3);
            foto = view1.findViewById(R.id.foto);

            nama.setText(model.get(position).getNama());
            info1.setText(model.get(position).getInfo1());
            info2.setText(model.get(position).getInfo2());
            info3.setText(model.get(position).getInfo3());
            Glide.with(context)
                    .load(model.get(position).getFoto())
                    .into(foto);
        }
        return view1;
    }
}
