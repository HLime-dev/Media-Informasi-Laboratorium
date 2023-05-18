package com.telematika.info.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.telematika.info.R;

import java.util.ArrayList;

public class Adaptor extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<GetData> model;

    public Adaptor(Context context, ArrayList<GetData> model){
        inflater=LayoutInflater.from(context);
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
        TextView nama, nip, jabatan, email, penelitian, foto;
        View view1=inflater.inflate(R.layout.list_dosen, null);
        if (view1!=null)
        {
            nama=view1.findViewById(R.id.nama);
            jabatan=view1.findViewById(R.id.jabatan);
            email=view1.findViewById(R.id.email);

            nama.setText(model.get(position).getNama());
            jabatan.setText(model.get(position).getJabatan());
            email.setText(model.get(position).getEmail());
        }
        return view1;
    }
}
