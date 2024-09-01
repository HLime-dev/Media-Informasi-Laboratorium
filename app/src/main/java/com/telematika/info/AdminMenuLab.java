package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AdminMenuLab extends AppCompatActivity {

    LinearLayout lab1, lab2, lab3, lab4, lab5, lab6, lab7, lab8, lab9, lab10, lab11, lab12, lab13, pengunjung;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_lab);

        lab1 = findViewById(R.id.lab1);
        lab2 = findViewById(R.id.lab2);
        lab3 = findViewById(R.id.lab3);
        lab4 = findViewById(R.id.lab4);
        lab5 = findViewById(R.id.lab5);
        lab6 = findViewById(R.id.lab6);
        lab7 = findViewById(R.id.lab7);
        lab8 = findViewById(R.id.lab8);
        lab9 = findViewById(R.id.lab9);
        lab10 = findViewById(R.id.lab10);
        lab11 = findViewById(R.id.lab11);
        lab12 = findViewById(R.id.lab12);
        lab13 = findViewById(R.id.lab13);
        pengunjung = findViewById(R.id.pengunjung);
        toolbar = findViewById(R.id.toolbar);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab1", "lab1.php");
                startActivity(intent);
            }
        });

        lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab2", "lab2.php");
                startActivity(intent);
            }
        });

        lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab3", "lab3.php");
                startActivity(intent);
            }
        });

        lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab4", "lab4.php");
                startActivity(intent);
            }
        });

        lab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab5", "lab5.php");
                startActivity(intent);
            }
        });

        lab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab6", "lab6.php");
                startActivity(intent);
            }
        });

        lab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab7", "lab7.php");
                startActivity(intent);
            }
        });

        lab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab8", "lab8.php");
                startActivity(intent);
            }
        });

        lab9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab9", "lab9.php");
                startActivity(intent);
            }
        });

        lab10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab10", "lab10.php");
                startActivity(intent);
            }
        });

        lab11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab11", "lab11.php");
                startActivity(intent);
            }
        });

        lab12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab12", "lab12.php");
                startActivity(intent);
            }
        });

        lab13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, MenuAdmin.class);
                intent.putExtra("lab13", "lab13.php");
                startActivity(intent);
            }
        });

        pengunjung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuLab.this, AdminPengunjung.class);
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