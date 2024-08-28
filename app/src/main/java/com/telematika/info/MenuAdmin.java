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
                    intent.putExtra("lab1", "tampil_data_dsn1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "tampil_data_dsn2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "tampil_data_dsn3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "tampil_data_dsn4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "tampil_data_dsn5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "tampil_data_dsn6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "tampil_data_dsn7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "tampil_data_dsn8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "tampil_data_dsn9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "tampil_data_dsn10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "tampil_data_dsn11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "tampil_data_dsn12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "tampil_data_dsn13.php");
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
                    intent.putExtra("lab1", "tampil_data_mhs1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "tampil_data_mhs2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "tampil_data_mhs3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "tampil_data_mhs4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "tampil_data_mhs5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "tampil_data_mhs6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "tampil_data_mhs7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "tampil_data_mhs8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "tampil_data_mhs9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "tampil_data_mhs10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "tampil_data_mhs11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "tampil_data_mhs12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "tampil_data_mhs13.php");
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
                    intent.putExtra("lab1", "tampil_data_ruang1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "tampil_data_ruang2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "tampil_data_ruang3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "tampil_data_ruang4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "tampil_data_ruang5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "tampil_data_ruang6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "tampil_data_ruang7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "tampil_data_ruang8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "tampil_data_ruang9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "tampil_data_ruang10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "tampil_data_ruang11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "tampil_data_ruang12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "tampil_data_ruang13.php");
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
                    intent.putExtra("lab1", "tampil_data_alat1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "tampil_data_alat2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "tampil_data_alat3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "tampil_data_alat4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "tampil_data_alat5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "tampil_data_alat6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "tampil_data_alat7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "tampil_data_alat8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "tampil_data_alat9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "tampil_data_alat10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "tampil_data_alat11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "tampil_data_alat12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "tampil_data_alat13.php");
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
                    intent.putExtra("lab1", "tampil_data_event1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "tampil_data_event2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "tampil_data_event3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "tampil_data_event4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "tampil_data_event5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "tampil_data_event6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "tampil_data_event7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "tampil_data_event8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "tampil_data_event9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "tampil_data_event10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "tampil_data_event11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "tampil_data_event12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "tampil_data_event13.php");
                }
                startActivity(intent);
            }
        });

        praktikum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // pengunjung.setBackground(getDrawable(R.drawable.bg_item_selected));
                Intent intent = new Intent(MenuAdmin.this, AdminPengunjung.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "tampil_data_dsn1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "tampil_data_dsn2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "tampil_data_dsn3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "tampil_data_dsn4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "tampil_data_dsn5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "tampil_data_dsn6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "tampil_data_dsn7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "tampil_data_dsn8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "tampil_data_dsn9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "tampil_data_dsn10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "tampil_data_dsn11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "tampil_data_dsn12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "tampil_data_dsn13.php");
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
