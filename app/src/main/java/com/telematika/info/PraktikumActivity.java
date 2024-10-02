package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.telematika.info.Adapter.GetDataMahasiswa;
import com.telematika.info.Adapter.GetDataPraktikum;
import com.telematika.info.Adapter.PraktikumAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PraktikumActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataPraktikum> model;
    GetDataPraktikum getDataPraktikum;
    PraktikumAdaptor praktikumAdaptor;
    String urlPlus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praktikum);

        listView=findViewById(R.id.list);
        model=new ArrayList<>();
        praktikumAdaptor= new PraktikumAdaptor(getApplicationContext(), model);
        listView.setAdapter(praktikumAdaptor);
        toolbar=findViewById(R.id.toolbar);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(PraktikumActivity.this, DetailPraktikum.class);
                intent.putExtra("detail_data", model.get(position).getId());
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