package com.telematika.info.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.telematika.info.AddDosen;
import com.telematika.info.AdminDosen;
import com.telematika.info.R;
import com.telematika.info.SelectListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adaptor extends RecyclerView.Adapter<ImageViewHolder>{

    private Context context;
    private List<GetData> imagelist;
    private SelectListener listener;
    private AdminDosen adminDosen;

    public Adaptor(Context context, List<GetData> imagelist, SelectListener listener, AdminDosen adminDosen) {
        this.context = context;
        this.imagelist = imagelist;
        this.listener = listener;
        this.adminDosen = adminDosen;
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_opsi, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.edit) {
                            Intent intent = new Intent(context, AddDosen.class);
                            intent.putExtra("edit_data", imagelist.get(position).getId());
                            context.startActivity(intent);
                            return true;
                        } else if (item.getItemId() == R.id.hapus) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Apakah Anda ingin menghapus data ini?");
                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    adminDosen._hapus(imagelist.get(position).getId());
                                }
                            });
                            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{

    TextView nametvimg, jabatantvimg, emailtvimg;
    ImageView imageView;
    CardView cardView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.iv_retrieve);
        nametvimg = itemView.findViewById(R.id.nametvimg);
        jabatantvimg = itemView.findViewById(R.id.jabatantvimg);
        emailtvimg = itemView.findViewById(R.id.emailtvimg);

        cardView = itemView.findViewById(R.id.card);
    }
}
