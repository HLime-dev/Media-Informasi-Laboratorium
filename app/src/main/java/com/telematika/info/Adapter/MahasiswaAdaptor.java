package com.telematika.info.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.telematika.info.R;

import java.util.ArrayList;

public class MahasiswaAdaptor extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<GetDataMahasiswa> model;

    public MahasiswaAdaptor(Context context, ArrayList<GetDataMahasiswa> model) {
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
        TextView nama, nim, email;
        View view1 = inflater.inflate(R.layout.list_mahasiswa, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            nim = view1.findViewById(R.id.nim);
            email = view1.findViewById(R.id.email);

            nama.setText(model.get(position).getNama());
            nim.setText(model.get(position).getNim());
            email.setText(model.get(position).getEmail());
        }
        return view1;
    }
}
