package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuAdmin extends AppCompatActivity {

    LinearLayout dosen, mahasiswa, ruangan, alat, event, pengunjung;
    Toolbar toolbar;

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

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Intent intent = new Intent(MenuAdmin.this, AdminMahasiswa.class);
                startActivity(intent);
            }
        });

        ruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruangan.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminRuangan.class);
                startActivity(intent);
            }
        });

        alat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alat.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminAlat.class);
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminEvent.class);
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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Menghapus stack aktivitas sebelumnya
        startActivity(intent);
        finish(); // Menutup aktivitas saat ini
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}
