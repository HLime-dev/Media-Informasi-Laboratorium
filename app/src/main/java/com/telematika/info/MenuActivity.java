package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    LinearLayout umum, dosen, mahasiswa, ruangan, alat, event, praktikum;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dosen = (LinearLayout) findViewById(R.id.dosen);
        //umum = (LinearLayout) findViewById(R.id.umum);
        mahasiswa = (LinearLayout) findViewById(R.id.mahasiswa);
        ruangan = (LinearLayout) findViewById(R.id.ruangan);
        alat = (LinearLayout) findViewById(R.id.alat);
        event = (LinearLayout) findViewById(R.id.event);
        praktikum = findViewById(R.id.praktikum);

        toolbar = findViewById(R.id.toolbar);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // umum.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, InfoUmum.class);
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

         */

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dosen.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, DosenActivity.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "dsn" + i + ".php");
                        break;
                    }
                }
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mahasiswa.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, MahasiswaActivity.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "mhs" + i + ".php");
                        break;
                    }
                }
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        ruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ruangan.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, RuanganActivity.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "ruang" + i + ".php");
                        break;
                    }
                }
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        alat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // alat.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, AlatActivity.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "alat" + i + ".php");
                        break;
                    }
                }
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //event.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, EventActivity.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "event" + i + ".php");
                        break;
                    }
                }
                // Mulai activity tujuan
                startActivity(intent);
            }
        });

        praktikum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //event.setBackground(getDrawable(R.drawable.bg_item_selected));
                // Buat Intent untuk activity tujuan
                Intent intent = new Intent(MenuActivity.this, PraktikumActivity.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "prak" + i + ".php");
                        break;
                    }
                }
                // Mulai activity tujuan
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Menghapus stack aktivitas sebelumnya
        startActivity(intent);
        finish(); // Menutup aktivitas saat ini

         */
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}