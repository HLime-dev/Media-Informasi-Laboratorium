package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddEvent extends AppCompatActivity {

    Toolbar toolbar;
    TextInputEditText nama, deskripsi, lokasi, tanggal, foto;
    Button simpan_data;
    TextView label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        toolbar = findViewById(R.id.toolbar);
        nama=findViewById(R.id.nama);
        deskripsi=findViewById(R.id.deskripsi);
        lokasi=findViewById(R.id.lokasi);
        tanggal=findViewById(R.id.tanggal);
        foto=findViewById(R.id.foto);
        simpan_data=findViewById(R.id.simpan_data);
        label=findViewById(R.id.label);
        if (getIntent().hasExtra("edit_data"))
        {
            label.setText("Edit Data");
            getData();
            simpan_data.setText("Update Data");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        simpan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nama.getText().toString().length()==0)
                {
                    nama.setError("Tidak boleh kosong");
                }
                if (deskripsi.getText().toString().length()==0)
                {
                    deskripsi.setError("Tidak boleh kosong");
                }
                if (lokasi.getText().toString().length()==0)
                {
                    lokasi.setError("Tidak boleh kosong");
                }
                if (tanggal.getText().toString().length()==0)
                {
                    tanggal.setError("Tidak boleh kosong");
                }
                if (foto.getText().toString().length()==0)
                {
                    foto.setError("Tidak boleh kosong");
                }
                else
                {
                    String url= new Konfigurasi().baseUrl()+"simpan_event.php";

                    StringRequest stringRequest=new StringRequest(
                            1, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject= new JSONObject(response);
                                        String status=jsonObject.getString("status");
                                        if (status.equals("data tersimpan"))
                                        {
                                            Boolean cekintent=getIntent().hasExtra("edit_data");
                                            AlertDialog.Builder builder=new AlertDialog.Builder(AddEvent.this);
                                            builder.setTitle("Sukses");
                                            builder.setMessage(cekintent?"Data berhasil diupdate":"Data berhasil disimpan");
                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            });

                                            AlertDialog alertDialog= builder.create();
                                            alertDialog.show();
                                        }
                                        else {

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddEvent.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> form=new HashMap<String,String>();
                            form.put("nama", nama.getText().toString());
                            form.put("lokasi", deskripsi.getText().toString());
                            form.put("tanggal", tanggal.getText().toString());
                            form.put("foto", foto.getText().toString());
                            if (getIntent().hasExtra("edit_data"))
                            {
                                form.put("id", getIntent().getStringExtra("edit_data"));
                            }
                            return form;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });

    }

    void getData()
    {
        String url=new Konfigurasi().baseUrl()+"get_data_event.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response).getJSONObject("data");
                            String gnama=jsonObject.getString("nama");
                            String glokasi=jsonObject.getString("lokasi");
                            String gtanggal=jsonObject.getString("tanggal");
                            String gdeskripsi=jsonObject.getString("deskripsi");
                            String gfoto=jsonObject.getString("foto");

                            nama.setText(gnama);
                            lokasi.setText(glokasi);
                            tanggal.setText(gtanggal);
                            deskripsi.setText(gdeskripsi);
                            foto.setText(gfoto);


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
                form.put("id", getIntent().getStringExtra("edit_data"));
                return form;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
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