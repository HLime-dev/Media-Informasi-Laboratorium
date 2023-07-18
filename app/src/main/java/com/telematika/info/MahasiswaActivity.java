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
import com.telematika.info.Adapter.Adaptor;
import com.telematika.info.Adapter.GetData;
import com.telematika.info.Adapter.GetDataMahasiswa;
import com.telematika.info.Adapter.MahasiswaAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MahasiswaActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataMahasiswa> model;
    GetDataMahasiswa getDataMahasiswa;
    MahasiswaAdaptor mahasiswaAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        listView=findViewById(R.id.list);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        model = new ArrayList<>();
        mahasiswaAdaptor = new MahasiswaAdaptor(getApplicationContext(), model);
        listView.setAdapter(mahasiswaAdaptor);

        load_data();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailMahasiswa.class);
                intent.putExtra("detail_data", model.get(position).getId());
                startActivity(intent);
            }
        });
    }

    void load_data() {
        String url = new Konfigurasi().baseUrl() + "tampil_data_mhs.php";

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
                            String nim = object.getString("nim");
                            String email = object.getString("email");
                            String url2 = object.getString("image");

                            String urlimage = "https://medtele.000webhostapp.com/images/" + url2;

                            getDataMahasiswa = new GetDataMahasiswa(id, nama, nim, email, urlimage);
                            model.add(getDataMahasiswa);
                            mahasiswaAdaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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
