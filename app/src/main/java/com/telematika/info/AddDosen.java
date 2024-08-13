package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.telematika.info.Adapter.Adaptor;
import com.telematika.info.Adapter.GetData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDosen extends AppCompatActivity {

    Uri selecteduri;
    Bitmap bitmap;
    String encodeImage;
    Toolbar toolbar;
    TextInputEditText name, nip, jabatan, email, penelitian;
    Button simpan_data, pilihfoto;
    ProgressBar progressBar;
    TextView label;
    ImageView fotoiv;
    String url="http://103.102.48.24/halim/simpan_dosen.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dosen);

        toolbar = findViewById(R.id.toolbar);
        name=findViewById(R.id.nama);
        nip=findViewById(R.id.nip);
        jabatan=findViewById(R.id.jabatan);
        email=findViewById(R.id.email);
        penelitian=findViewById(R.id.penelitian);
        simpan_data=findViewById(R.id.simpan_data);
        progressBar = findViewById(R.id.pb_img);
        label=findViewById(R.id.label);
        pilihfoto = findViewById(R.id.foto);
        fotoiv = findViewById(R.id.fotoiv);


        if (getIntent().hasExtra("edit_data"))
        {
            label.setText("Edit Data Dosen");
            getData();
            simpan_data.setText("Update Data");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pilihfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (nama.getText().toString().length()==0)
                {
                    nama.setError("Tidak boleh kosong");
                } else {
                    Intent intent = new Intent(getApplicationContext(), UploadFoto.class);
                    intent.putExtra("nama", nama.getText().toString());
                    startActivity(intent);
                }
                 */

                chooseImage();
            }
        });

        simpan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = true;

                if (name.getText().toString().length() == 0) {
                    name.setError("Tidak boleh kosong");
                    isValid = false;
                }
                if (nip.getText().toString().length() == 0) {
                    nip.setError("Tidak boleh kosong");
                    isValid = false;
                }
                if (jabatan.getText().toString().length() == 0) {
                    jabatan.setError("Tidak boleh kosong");
                    isValid = false;
                }
                if (email.getText().toString().length() == 0) {
                    email.setError("Tidak boleh kosong");
                    isValid = false;
                }
                if (penelitian.getText().toString().length() == 0) {
                    penelitian.setError("Tidak boleh kosong");
                    isValid = false;
                }

                if (isValid) {
                    //String url= new Konfigurasi().baseUrl()+"simpan.php";
                    progressBar.setVisibility(View.VISIBLE);

                    StringRequest stringRequest=new StringRequest(
                            1, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.d("ServerResponse", response);

                                    try {
                                        JSONObject jsonObject= new JSONObject(response);
                                        String status=jsonObject.getString("status");
                                        if (status.equals("data tersimpan"))
                                        {
                                            Boolean cekintent=getIntent().hasExtra("edit_data");
                                            AlertDialog.Builder builder=new AlertDialog.Builder(AddDosen.this);
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
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(AddDosen.this, "Pilih Foto", Toast.LENGTH_SHORT).show();
                            Log.e("VolleyError", error.toString());
                        }
                    }
                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> form=new HashMap<String,String>();

                            form.put("name", name.getText().toString());
                            form.put("nip", nip.getText().toString());
                            form.put("jabatan", jabatan.getText().toString());
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
                    RequestQueue requestQueue= Volley.newRequestQueue(AddDosen.this);
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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);

        byte[] imagebyte = stream.toByteArray();
        encodeImage = android.util.Base64.encodeToString(imagebyte, Base64.DEFAULT);
    }

    void getData()
    {
        String url=new Konfigurasi().baseUrl()+"get_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response).getJSONObject("data");
                            String gnama=jsonObject.getString("name");
                            String gnip=jsonObject.getString("nip");
                            String gjabatan=jsonObject.getString("jabatan");
                            String gemail=jsonObject.getString("email");
                            String gpenelitian=jsonObject.getString("penelitian");

                            name.setText(gnama);
                            nip.setText(gnip);
                            jabatan.setText(gjabatan);
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