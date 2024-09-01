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

    LinearLayout dosen, mahasiswa, ruangan, alat, event, praktikum;
    Toolbar toolbar;
    private LinearLayout selectedLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

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

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set latar belakang LinearLayout yang baru dipilih
                //dosen.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminDosen.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "dsn1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "dsn2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "dsn3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "dsn4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "dsn5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "dsn6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "dsn7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "dsn8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "dsn9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "dsn10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "dsn11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "dsn12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "dsn13.php");
                }
                startActivity(intent);
            }
        });

        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mahasiswa.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminMahasiswa.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "mhs1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "mhs2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "mhs3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "mhs4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "mhs5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "mhs6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "mhs7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "mhs8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "mhs9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "mhs10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "mhs11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "mhs12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "mhs13.php");
                }
                startActivity(intent);
            }
        });

        ruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ruangan.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminRuangan.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "ruang1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "ruang2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "ruang3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "ruang4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "ruang5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "ruang6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "ruang7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "ruang8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "ruang9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "ruang10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "ruang11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "ruang12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "ruang13.php");
                }
                startActivity(intent);
            }
        });

        alat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alat.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminAlat.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "alat1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "alat2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "alat3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "alat4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "alat5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "alat6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "alat7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "alat8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "alat9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "alat10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "alat11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "alat12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "alat13.php");
                }
                startActivity(intent);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //event.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminEvent.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "event1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "event2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "event3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "event4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "event5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "event6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "event7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "event8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "event9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "event10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "event11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "event12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "event13.php");
                }
                startActivity(intent);
            }
        });

        praktikum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // pengunjung.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, PraktikumActivity.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "prak1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "prak2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "prak3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "prak4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "prak5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "prak6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "prak7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "prak8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "prak9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "prak10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "prak11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "prak12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "prak13.php");
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
