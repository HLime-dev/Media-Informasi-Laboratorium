package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
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

public class DetailEvent extends AppCompatActivity {

    Toolbar toolbar;
    TextView nama, tanggal, lokasi, deskripsi;
    ImageView foto;
    String urlGet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        toolbar = findViewById(R.id.toolbar);
        foto = findViewById(R.id.foto);
        nama = findViewById(R.id.nama);
        tanggal = findViewById(R.id.tanggal);
        lokasi = findViewById(R.id.lokasi);
        deskripsi = findViewById(R.id.deskripsi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("detail_data"))
        {
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

    void getData()
    {
        String url=new Konfigurasi().baseUrlGetEvent()+"get_data_" + urlGet;
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response).getJSONObject("data");
                            String gnama=jsonObject.getString("nama");
                            String gtanggal=jsonObject.getString("tanggal");
                            String glokasi=jsonObject.getString("lokasi");
                            String gdeskripsi=jsonObject.getString("deskripsi");
                            String url2=jsonObject.getString("foto");

                            String urlimage=new Konfigurasi().baseUrlImages() + url2;

                            nama.setText(gnama);
                            tanggal.setText(gtanggal);
                            lokasi.setText(glokasi);
                            deskripsi.setText(gdeskripsi);

                            RequestOptions requestOptions = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL);

                            Glide.with(DetailEvent.this)
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