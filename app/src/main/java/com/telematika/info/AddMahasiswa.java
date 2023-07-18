package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddMahasiswa extends AppCompatActivity {

    Uri selecteduri;
    Bitmap bitmap;
    String encodeImage;
    ProgressBar progressBar;
    ImageView fotoiv;
    String url="https://medtele.000webhostapp.com/simpan_mhs.php";
    Toolbar toolbar;
    TextInputEditText nama, nim, email, penelitian;
    Button simpan_data, pilihfoto;
    TextView label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        toolbar = findViewById(R.id.toolbar);
        nama=findViewById(R.id.nama);
        nim=findViewById(R.id.nim);
        email=findViewById(R.id.email);
        penelitian=findViewById(R.id.penelitian);
        fotoiv=findViewById(R.id.fotoiv);
        simpan_data=findViewById(R.id.simpan_data);
        pilihfoto=findViewById(R.id.pilihfoto);
        progressBar =findViewById(R.id.pb_img);
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

        pilihfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseImage();
            }
        });

        simpan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nama.getText().toString().length()==0)
                {
                    nama.setError("Tidak boleh kosong");
                }
                if (nim.getText().toString().length()==0)
                {
                    nim.setError("Tidak boleh kosong");
                }
                if (email.getText().toString().length()==0)
                {
                    email.setError("Tidak boleh kosong");
                }
                if (penelitian.getText().toString().length()==0)
                {
                    penelitian.setError("Tidak boleh kosong");
                }
                else
                {
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
                                            AlertDialog.Builder builder=new AlertDialog.Builder(AddMahasiswa.this);
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
                            Toast.makeText(AddMahasiswa.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> form=new HashMap<String,String>();
                            form.put("nama", nama.getText().toString());
                            form.put("nim", nim.getText().toString());
                            form.put("email", email.getText().toString());
                            form.put("penelitian", penelitian.getText().toString());
                            form.put("image", encodeImage);
                            if (getIntent().hasExtra("edit_data"))
                            {
                                form.put("id", getIntent().getStringExtra("edit_data"));
                            }
                            return form;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(AddMahasiswa.this);
                    requestQueue.add(stringRequest);
                }
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 || requestCode == RESULT_OK || data != null || data.getData() != null) {

            selecteduri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selecteduri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                fotoiv.setImageBitmap(bitmap);
                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imagebyte = stream.toByteArray();
        encodeImage = android.util.Base64.encodeToString(imagebyte, Base64.DEFAULT);
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

                            nama.setText(gnama);
                            nim.setText(gnim);
                            email.setText(gemail);
                            penelitian.setText(gpenelitian);

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
        RequestQueue requestQueue=Volley.newRequestQueue(AddMahasiswa.this);
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