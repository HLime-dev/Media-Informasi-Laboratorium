package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.telematika.info.Adapter.Adaptor;
import com.telematika.info.Adapter.GetData;
import com.telematika.info.Adapter.GetDataMahasiswa;
import com.telematika.info.Adapter.GetDataPraktikum;
import com.telematika.info.Adapter.MahasiswaAdaptor;
import com.telematika.info.Adapter.PraktikumAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminPraktikum extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataPraktikum> model;
    PraktikumAdaptor praktikumAdaptor;
    GetDataPraktikum getDataPraktikum;
    FloatingActionButton tambah;
    String urlPlus = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_praktikum);


        listView=findViewById(R.id.list);
        model = new ArrayList<>();
        praktikumAdaptor = new PraktikumAdaptor(getApplicationContext(), model);
        listView.setAdapter(praktikumAdaptor);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 1; i <= 13; i++) {
            String labKey = "lab" + i;
            if (getIntent().hasExtra(labKey)) {
                urlPlus = getIntent().getStringExtra(labKey);
                break;
            }
        }

        load_data();
        tambah=findViewById(R.id.tambah);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPraktikum.class);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(AdminPraktikum.this, view); // Menggunakan Activity sebagai konteks
                popupMenu.getMenuInflater().inflate(R.menu.menu_opsi, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent intent = new Intent(AdminPraktikum.this, AddPraktikum.class);
                                intent.putExtra("edit_data", model.get(position).getId());

                                // Loop through all lab extras
                                for (int i = 1; i <= 13; i++) {
                                    String labKey = "lab" + i;
                                    if (getIntent().hasExtra(labKey)) {
                                        intent.putExtra("edit_" + labKey, "prak" + i + ".php");
                                        break;
                                    }
                                }

                                for (int i = 1; i <= 13; i++) {
                                    String labKey = "lab" + i;
                                    if (getIntent().hasExtra(labKey)) {
                                        intent.putExtra(labKey, "prak" + i + ".php");
                                        break;
                                    }
                                }

                                startActivity(intent);
                                return true;

                            case R.id.hapus:
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminPraktikum.this);
                                builder.setMessage("Apakah Anda ingin menghapus data ini?")
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                _hapus(model.get(position).getId());
                                            }
                                        })
                                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                return true;

                            default:
                                return false;
                        }
                    }
                });
            }
        });

    }

    void load_data() {
        String url = new Konfigurasi().baseUrlTampilPrak() + "tampil_data_" + urlPlus;

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                model.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String tanggal = object.getString("tanggal");
                            String tempat = object.getString("tempat");
                            String url2 = object.getString("image");

                            String urlimage = new Konfigurasi().baseUrlImages() + url2;

                            getDataPraktikum = new GetDataPraktikum(id, name, tanggal, tempat, urlimage);
                            model.add(getDataPraktikum);
                            praktikumAdaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    void _hapus(String id)
    {
        String url=new Konfigurasi().baseUrlHapusPrak()+ "hapus_" + urlPlus;
        StringRequest request=new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if (status.equals("data_berhasil_dihapus"))
                            {
                                Toast.makeText(AdminPraktikum.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                load_data();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form=new HashMap<String, String>();
                form.put("id", id);
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        load_data();
        super.onResume();
    }
}