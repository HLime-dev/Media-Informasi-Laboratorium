package com.telematika.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.telematika.info.Adapter.AlatAdaptor;
import com.telematika.info.Adapter.GetDataAlat;
import com.telematika.info.Adapter.GetDataPengunjung;
import com.telematika.info.Adapter.PengunjungAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminPengunjung extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataPengunjung> model;
    PengunjungAdaptor pengunjungAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pengunjung);

        listView=findViewById(R.id.list);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        model = new ArrayList<>();
        pengunjungAdaptor = new PengunjungAdaptor(getApplicationContext(), model);
        listView.setAdapter(pengunjungAdaptor);

        load_data();
    }

    void load_data() {
        String url = new Konfigurasi().baseUrl() + "tampil_data_pengunjung.php";

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject getDataPengunjung = jsonArray.getJSONObject(i);
                        model.add(new GetDataPengunjung(
                                getDataPengunjung.getString("id"),
                                getDataPengunjung.getString("nama"),
                                getDataPengunjung.getString("identitas"),
                                getDataPengunjung.getString("tanggal")
                        ));
                    }
                    pengunjungAdaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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