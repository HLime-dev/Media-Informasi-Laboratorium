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
import com.telematika.info.Adapter.GetDataMahasiswa;
import com.telematika.info.Adapter.GetDataRuangan;
import com.telematika.info.Adapter.MahasiswaAdaptor;
import com.telematika.info.Adapter.RuanganAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RuanganActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataRuangan> model;
    GetDataRuangan getDataRuangan;
    RuanganAdaptor ruanganAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruangan);

        listView=findViewById(R.id.list);
        model = new ArrayList<>();
        ruanganAdaptor = new RuanganAdaptor(getApplicationContext(), model);
        listView.setAdapter(ruanganAdaptor);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        load_data();
    }

    void load_data() {
        String url = new Konfigurasi().baseUrl() + "tampil_data_ruangan.php";

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String nama = object.getString("nama");
                            String url2 = object.getString("image");

                            String urlimage = "https://medtele.000webhostapp.com/images/" + url2;

                            getDataRuangan = new GetDataRuangan(id, nama, urlimage);
                            model.add(getDataRuangan);
                            ruanganAdaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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