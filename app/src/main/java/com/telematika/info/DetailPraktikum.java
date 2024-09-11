package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailPraktikum extends AppCompatActivity {

    Toolbar toolbar;
    TextView nama, tanggal, tempat, detail;
    ImageView foto;
    String urlGet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_praktikum);

        toolbar = findViewById(R.id.toolbar);
        foto = findViewById(R.id.foto);
        nama = findViewById(R.id.nama);
        tanggal = findViewById(R.id.tanggal);
        tempat = findViewById(R.id.tempat);
        detail = findViewById(R.id.detail);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("detail_data")) {
            for (int i = 1; i <= 13; i++) {
                String labKey = "lab" + i;
                if (getIntent().hasExtra(labKey)) {
                    urlGet = getIntent().getStringExtra(labKey);
                    break;
                }
            }
            getData();
        }
    }

    void getData() {
        String url = new Konfigurasi().baseUrlGetPrak() + "get_data_" + urlGet;
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                            String gnama = jsonObject.getString("name");
                            String gtanggal = jsonObject.getString("tanggal");
                            String gtempat = jsonObject.getString("tempat");
                            String gdetail = jsonObject.getString("detail");
                            String url2 = jsonObject.getString("image");

                            String urlimage = new Konfigurasi().baseUrlImages() + url2;

                            nama.setText(gnama);
                            tanggal.setText(gtanggal);
                            tempat.setText(gtempat);
                            detail.setText(gdetail);

                            RequestOptions requestOptions = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL);

                            Glide.with(DetailPraktikum.this)
                                    .load(urlimage)
                                    .apply(requestOptions)
                                    .into(foto);
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
                HashMap<String, String> form= new HashMap<String, String>();
                form.put("id", getIntent().getStringExtra("detail_data"));
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

}
