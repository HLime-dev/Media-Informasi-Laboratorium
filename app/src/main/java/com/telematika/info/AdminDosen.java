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
import com.telematika.info.Adapter.MahasiswaAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminDosen extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetData> model;
    Adaptor adaptor;
    GetData getData;
    FloatingActionButton tambah;
    String urlPlus = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dosen);


        listView=findViewById(R.id.list);
        model = new ArrayList<>();
        adaptor = new Adaptor(getApplicationContext(), model);
        listView.setAdapter(adaptor);
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
                Intent intent = new Intent(getApplicationContext(), AddDosen.class);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu=new PopupMenu(getApplicationContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_opsi, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.edit) {
                            Intent intent = new Intent(getApplicationContext(), AddDosen.class);
                            intent.putExtra("edit_data", model.get(position).getId());
                            // Loop through all lab extras
                            for (int i = 1; i <= 13; i++) {
                                String labKey = "lab" + i;
                                if (getIntent().hasExtra(labKey)) {
                                    intent.putExtra("edit_" + labKey, "dsn" + i + ".php");
                                    break;
                                }
                            }

                            for (int i = 1; i <= 13; i++) {
                                String labKey = "lab" + i;
                                if (getIntent().hasExtra(labKey)) {
                                    intent.putExtra(labKey, "dsn" + i + ".php");
                                    break;
                                }
                            }
                            startActivity(intent);
                            return true;
                        } else if (item.getItemId() == R.id.hapus) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(AdminDosen.this);
                            builder.setMessage("Apakah Anda ingin menghapus data ini?");
                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    _hapus(model.get(position).getId());
                                }
                            });
                            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            AlertDialog alertDialog=builder.create();
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

    void load_data() {
        String url = new Konfigurasi().baseUrlTampilDosen() + "tampil_data_" + urlPlus;

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
                            String jabatan = object.getString("jabatan");
                            String email = object.getString("email");
                            String url2 = object.getString("image");

                           // String urlimage = "http://192.168.123.139/lab_elektro/images/" + url2;

                            //lab telkom
                            String urlimage = new Konfigurasi().baseUrlImages() + url2;

                            getData = new GetData(id, name, jabatan, email, urlimage);
                            model.add(getData);
                            adaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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
        String url=new Konfigurasi().baseUrlHapusDosen()+ "hapus_" + urlPlus;
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
                                Toast.makeText(AdminDosen.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
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