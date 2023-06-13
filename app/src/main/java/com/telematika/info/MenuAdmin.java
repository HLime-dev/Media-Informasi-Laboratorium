package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuAdmin extends AppCompatActivity {

    LinearLayout dosen, mahasiswa, ruangan, alat, event, pengunjung, kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        dosen = findViewById(R.id.dosen);
        mahasiswa = findViewById(R.id.mahasiswa);
        ruangan = findViewById(R.id.ruangan);
        alat = findViewById(R.id.alat);
        event = findViewById(R.id.event);
        pengunjung = findViewById(R.id.pengunjung);
        kembali = findViewById(R.id.kembali);

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosen.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminDosen.class);
                startActivity(intent);
            }
        });

        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswa.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, MahasiswaActivity.class);
                startActivity(intent);
            }
        });

        ruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruangan.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, RuanganActivity.class);
                startActivity(intent);
            }
        });

        alat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alat.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AlatActivity.class);
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, EventActivity.class);
                startActivity(intent);
            }
        });

        pengunjung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pengunjung.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminPengunjung.class);
                startActivity(intent);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
