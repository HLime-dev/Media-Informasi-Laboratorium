package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PengunjungActivity extends AppCompatActivity {

    EditText nama, identitas, pekerjaan;
    Button btnlanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengunjung);

        btnlanjut = findViewById(R.id.btnlanjut);
        nama = findViewById(R.id.nama);
        identitas = findViewById(R.id.identitas);
        pekerjaan = findViewById(R.id.pekerjaan);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nama.getText().toString().length()==0)
                {
                    nama.setError("Tidak boleh kosong");
                }
                if (identitas.getText().toString().length()==0)
                {
                    identitas.setError("Tidak boleh kosong");
                }
                if (pekerjaan.getText().toString().length()==0)
                {
                    pekerjaan.setError("Tidak boleh kosong");
                }
                else
                {
                    String url= new Konfigurasi().baseUrl()+"simpan_pengunjung.php";

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
                                            Intent intent = new Intent(PengunjungActivity.this, ScanActivity.class);
                                            startActivity(intent);
                                            //Boolean cekintent=getIntent().hasExtra("edit_data");
                                           // AlertDialog.Builder builder=new AlertDialog.Builder(PengunjungActivity.this);
                                            //builder.setTitle("Sukses");
                                           // builder.setMessage(cekintent?"Data berhasil diupdate":"Data berhasil disimpan");
                                            //builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                             //   @Override
                                             //   public void onClick(DialogInterface dialog, int which) {
                                            //        finish();
                                            //    }
                                            //});

                                           // AlertDialog alertDialog= builder.create();
                                            //alertDialog.show();
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
                            Toast.makeText(PengunjungActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> form=new HashMap<String,String>();
                            form.put("nama", nama.getText().toString());
                            form.put("identitas", identitas.getText().toString());
                            form.put("pekerjaan", pekerjaan.getText().toString());
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



}