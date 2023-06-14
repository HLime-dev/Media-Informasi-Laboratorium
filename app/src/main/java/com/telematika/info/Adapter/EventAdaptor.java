package com.telematika.info.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.telematika.info.R;

import java.util.ArrayList;

public class EventAdaptor extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetDataEvent> model;

    public EventAdaptor(Context context, ArrayList<GetDataEvent> model) {
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
        TextView nama, deskripsi, tanggal;
        View view1 = inflater.inflate(R.layout.list_event, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            deskripsi = view1.findViewById(R.id.deskripsi);
            tanggal = view1.findViewById(R.id.tanggal);

            nama.setText(model.get(position).getNama());
            deskripsi.setText(model.get(position).getDeskripsi());
            tanggal.setText(model.get(position).getTanggal());
        }
        return view1;
    }
}
