package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.telematika.info.Adapter.AlatAdaptor;
import com.telematika.info.Adapter.GetData;
import com.telematika.info.Adapter.GetDataAlat;
import com.telematika.info.Adapter.GetDataMahasiswa;
import com.telematika.info.Adapter.MahasiswaAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlatActivity extends AppCompatActivity{

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataAlat> model;
    AlatAdaptor alatAdaptor;
    GetDataAlat getDataAlat;

    String urlPlus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alat);

        listView=findViewById(R.id.list);
        model = new ArrayList<>();
        alatAdaptor = new AlatAdaptor(getApplicationContext(), model);
        listView.setAdapter(alatAdaptor);
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
    }

    void load_data() {
        String url = new Konfigurasi().baseUrlTampilAlat() + "tampil_data_" + urlPlus;

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
                            String nama = object.getString("nama");
                            String kategori = object.getString("kategori");
                            String jumlah = object.getString("jumlah");
                            String url2 = object.getString("image");

                            String urlimage = new Konfigurasi().baseUrlImages() + url2;

                            getDataAlat = new GetDataAlat(id, nama, kategori, jumlah, urlimage);
                            model.add(getDataAlat);
                            alatAdaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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