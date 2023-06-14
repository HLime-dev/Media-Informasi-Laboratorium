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

public class PengunjungAdaptor extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetDataPengunjung> model;

    public PengunjungAdaptor(Context context, ArrayList<GetDataPengunjung> model) {
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



    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nama, identitas, tanggal;
        View view1 = inflater.inflate(R.layout.list_pengunjung, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            identitas = view1.findViewById(R.id.identitas);
            tanggal = view1.findViewById(R.id.tanggal);

            nama.setText(model.get(position).getNama());
            identitas.setText(model.get(position).getIdentitas());
            tanggal.setText(model.get(position).getTanggal());
        }
        return view1;
    }
}
