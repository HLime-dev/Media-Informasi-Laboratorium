package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    LinearLayout dosen, mahasiswa, ruangan, alat, event, kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dosen = (LinearLayout) findViewById(R.id.dosen);
        mahasiswa = (LinearLayout) findViewById(R.id.mahasiswa);
        ruangan = (LinearLayout) findViewById(R.id.ruangan);
        alat = (LinearLayout) findViewById(R.id.alat);
        event = (LinearLayout) findViewById(R.id.event);
        kembali = (LinearLayout) findViewById(R.id.kembali);

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosen.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, DosenActivity.class);
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswa.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, MahasiswaActivity.class);
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        ruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruangan.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, RuanganActivity.class);
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        alat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alat.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, AlatActivity.class);
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        LinearLayout kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}