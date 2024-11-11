package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuAdmin extends AppCompatActivity {

    LinearLayout umum, dosen, mahasiswa, ruangan, alat, event, praktikum;
    Toolbar toolbar;
    private LinearLayout selectedLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        umum = findViewById(R.id.umum);
        dosen = findViewById(R.id.dosen);
        mahasiswa = findViewById(R.id.mahasiswa);
        ruangan = findViewById(R.id.ruangan);
        alat = findViewById(R.id.alat);
        event = findViewById(R.id.event);
        praktikum = findViewById(R.id.praktikum);

        toolbar = findViewById(R.id.toolbar);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set latar belakang LinearLayout yang baru dipilih
                //dosen.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminUmum.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "lab" + i + ".php");
                        break;
                    }
                }
                startActivity(intent);
            }
        });

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set latar belakang LinearLayout yang baru dipilih
                //dosen.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminDosen.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "dsn" + i + ".php");
                        break;
                    }
                }
                startActivity(intent);
            }
        });

        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mahasiswa.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminMahasiswa.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "mhs" + i + ".php");
                        break;
                    }
                }
                startActivity(intent);
            }
        });

        ruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ruangan.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminRuangan.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "ruang" + i + ".php");
                        break;
                    }
                }
                startActivity(intent);
            }
        });

        alat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alat.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminAlat.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "alat" + i + ".php");
                        break;
                    }
                }
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //event.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminEvent.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "event" + i + ".php");
                        break;
                    }
                }
                startActivity(intent);
            }
        });

        praktikum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // pengunjung.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminPraktikum.class);
                for (int i = 1; i <= 13; i++) {
                    String labKey = "lab" + i;
                    if (getIntent().hasExtra(labKey)) {
                        intent.putExtra(labKey, "prak" + i + ".php");
                        break;
                    }
                }
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

    @Override
    protected void onResume() {
        super.onResume();

    }



}
