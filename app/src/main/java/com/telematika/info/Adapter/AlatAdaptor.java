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
import com.telematika.info.AddAlat;
import com.telematika.info.AddDosen;
import com.telematika.info.AdminAlat;
import com.telematika.info.AdminDosen;
import com.telematika.info.R;
import com.telematika.info.SelectListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AlatAdaptor extends RecyclerView.Adapter<AlatImageViewHolder>{

    private Context context;
    private List<GetDataAlat> alatimagelist;
    private SelectListener listener;
    private AdminAlat adminAlat;

    public AlatAdaptor(Context context, List<GetDataAlat> alatimagelist, SelectListener listener, AdminAlat adminAlat) {
        this.context = context;
        this.alatimagelist = alatimagelist;
        this.listener = listener;
        this.adminAlat = adminAlat;
    }

    @NonNull
    @Override
    public AlatImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alat,parent,false);
        return new AlatImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AlatImageViewHolder holder, int position) {

        Glide.with(context).load(alatimagelist.get(position).getImage()).into(holder.imageView);
        holder.nametvimg.setText(alatimagelist.get(position).getName());
        holder.kategoritvimg.setText(alatimagelist.get(position).getKategori());
        holder.jumlahtvimg.setText(alatimagelist.get(position).getJumlah());

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
                            Intent intent = new Intent(context, AddAlat.class);
                            intent.putExtra("edit_data", alatimagelist.get(position).getId());
                            context.startActivity(intent);
                            return true;
                        } else if (item.getItemId() == R.id.hapus) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Apakah Anda ingin menghapus data ini?");
                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    adminAlat._hapus(alatimagelist.get(position).getId());
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
        return alatimagelist.size();
    }
}

class AlatImageViewHolder extends RecyclerView.ViewHolder{

    TextView nametvimg, kategoritvimg, jumlahtvimg;
    ImageView imageView;
    CardView cardView;

    public AlatImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.iv_retrieve);
        nametvimg = itemView.findViewById(R.id.nametvimg);
        kategoritvimg = itemView.findViewById(R.id.kategoritvimg);
        jumlahtvimg = itemView.findViewById(R.id.jumlahtvimg);

        cardView = itemView.findViewById(R.id.card);
    }
}
