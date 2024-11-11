package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.telematika.info.Adapter.GetDataMahasiswa;
import com.telematika.info.Adapter.GetDataRuangan;
import com.telematika.info.Adapter.GetDataUmum;
import com.telematika.info.Adapter.MahasiswaAdaptor;
import com.telematika.info.Adapter.RuanganAdaptor;
import com.telematika.info.Adapter.UmumAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfoUmum extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataUmum> model;
    GetDataUmum getDataUmum;
    UmumAdaptor umumAdaptor;
    String urlPlus = "";
    ImageView tutup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_umum);

        listView=findViewById(R.id.list);
        model = new ArrayList<>();
        umumAdaptor = new UmumAdaptor(getApplicationContext(), model);
        listView.setAdapter(umumAdaptor);
        toolbar = findViewById(R.id.toolbar);
        tutup = findViewById(R.id.tutup);

        /*
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         */

        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUmum.this, MenuActivity.class);
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
        String url = new Konfigurasi().baseUrlUmum() + "umum_" + urlPlus;

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
                            String info1 = object.getString("info1");
                            String info2 = object.getString("info2");
                            String info3 = object.getString("info3");
                            String url2 = object.getString("foto");

                            String urlimage = new Konfigurasi().baseUrlImages() + url2;

                            getDataUmum = new GetDataUmum(id, nama, info1, info2, info3, urlimage);
                            model.add(getDataUmum);
                            umumAdaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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
}