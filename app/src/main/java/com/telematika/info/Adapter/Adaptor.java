package com.telematika.info.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.telematika.info.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adaptor extends RecyclerView.Adapter<ImageViewHolder>{

    private Context context;
    private List<GetData> imagelist;

    public Adaptor(Context context, List<GetData> imagelist) {
        this.context = context;
        this.imagelist = imagelist;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dosen,parent,false);
        return new ImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Glide.with(context).load(imagelist.get(position).getImage()).into(holder.imageView);
        holder.nametvimg.setText(imagelist.get(position).getName());
        holder.jabatantvimg.setText(imagelist.get(position).getJabatan());
        holder.emailtvimg.setText(imagelist.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{

    TextView nametvimg, jabatantvimg, emailtvimg;
    ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.iv_retrieve);
        nametvimg = itemView.findViewById(R.id.nametvimg);
        jabatantvimg = itemView.findViewById(R.id.jabatantvimg);
        emailtvimg = itemView.findViewById(R.id.emailtvimg);
    }
}
