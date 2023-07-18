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

public class DetailMahasiswa extends AppCompatActivity {

    Toolbar toolbar;
    TextView nama, nim, email, penelitian;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        toolbar = findViewById(R.id.toolbar);
        foto = findViewById(R.id.foto);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        email = findViewById(R.id.email);
        penelitian = findViewById(R.id.penelitian);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("detail_data"))
        {
            getData();
        }
    }

    void getData()
    {
        String url=new Konfigurasi().baseUrl()+"get_data_mahasiswa.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response).getJSONObject("data");
                            String gnama=jsonObject.getString("nama");
                            String gnim=jsonObject.getString("nim");
                            String gemail=jsonObject.getString("email");
                            String gpenelitian=jsonObject.getString("penelitian");
                            String url2=jsonObject.getString("foto");

                            String urlimage="https://medtele.000webhostapp.com/images/" + url2;

                            nama.setText(gnama);
                            nim.setText(gnim);
                            email.setText(gemail);
                            penelitian.setText(gpenelitian);

                            RequestOptions requestOptions = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL);

                            Glide.with(DetailMahasiswa.this)
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