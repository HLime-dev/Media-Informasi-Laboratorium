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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DosenActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetData> model;
    Adaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        listView=findViewById(R.id.list);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        model = new ArrayList<>();
        adaptor = new Adaptor(getApplicationContext(), model);
       // listView.setAdapter(adaptor);

        load_data();
        load_image();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailDosen.class);
                intent.putExtra("detail_data", model.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void load_image() {

    }

    void load_data() {
        String url = new Konfigurasi().baseUrl() + "tampil_data.php";

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject getData = jsonArray.getJSONObject(i);
                        model.add(new GetData(
                                getData.getString("id"),
                                getData.getString("nama"),
                                getData.getString("jabatan"),
                                getData.getString("email"),
                                getData.getString("image")
                        ));
                    }
                    adaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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
