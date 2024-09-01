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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AddAlat extends AppCompatActivity {

    Uri selecteduri;
    Bitmap bitmap;
    String encodeImage = null; // Default to null
    String oldImage = null; // To store the old image URL
    Toolbar toolbar;
    TextInputEditText name, kategori, jumlah;
    Button simpan_data, pilihfoto;
    ProgressBar progressBar;
    TextView label;
    ImageView fotoiv;
    String urlPlus = "";
    String urlGet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alat);

        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.nama);
        kategori = findViewById(R.id.kategori);
        jumlah = findViewById(R.id.jumlah);
        fotoiv = findViewById(R.id.fotoiv);
        simpan_data = findViewById(R.id.simpan_data);
        pilihfoto = findViewById(R.id.foto);
        progressBar = findViewById(R.id.pb_img);
        label = findViewById(R.id.label);

        // Check if editing data
        if (getIntent().hasExtra("edit_data")) {
            label.setText("Edit Data Alat");
            for (int i = 1; i <= 13; i++) {
                String labKey = "edit_lab" + i;
                if (getIntent().hasExtra(labKey)) {
                    urlGet = getIntent().getStringExtra(labKey);
                    break;
                }
            }
            getData(); // Load the existing data
            simpan_data.setText("Update Data");
        }

        // Determine which lab's data to save based on intent extras
        for (int i = 1; i <= 13; i++) {
            String labKey = "lab" + i;
            if (getIntent().hasExtra(labKey)) {
                urlPlus = getIntent().getStringExtra(labKey);
                break;
            }
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pilihfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage(); // Start the image picker
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
                if (kategori.getText().toString().length() == 0) {
                    kategori.setError("Tidak boleh kosong");
                    isValid = false;
                }
                if (jumlah.getText().toString().length() == 0) {
                    jumlah.setError("Tidak boleh kosong");
                    isValid = false;
                }

                if (isValid) {
                    String url = "http://192.168.43.116/lab_elektro/" + "simpan_" + urlPlus;

                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String status = jsonObject.getString("status");
                                        if (status.equals("data tersimpan")) {
                                            Boolean cekintent = getIntent().hasExtra("edit_data");
                                            AlertDialog.Builder builder = new AlertDialog.Builder(AddAlat.this);
                                            builder.setTitle("Sukses");
                                            builder.setMessage(cekintent ? "Data berhasil diupdate" : "Data berhasil disimpan");
                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            });

                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(AddAlat.this, "Pilih Foto", Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> form = new HashMap<>();
                            form.put("name", name.getText().toString());
                            form.put("kategori", kategori.getText().toString());
                            form.put("jumlah", jumlah.getText().toString());

                            // Use the full URL for the old image when encoding it
                            if (encodeImage != null) {
                                form.put("image", encodeImage);
                            } else if (oldImage != null) {
                                // Full URL for oldImage
                                String oldImageUrl = "http://192.168.43.116/lab_elektro/images/" + oldImage;
                                String encodedOldImage = encodeImageFromUrl(oldImageUrl);
                                if (encodedOldImage != null) {
                                    form.put("image", encodedOldImage);
                                }
                            }

                            if (getIntent().hasExtra("edit_data")) {
                                form.put("id", getIntent().getStringExtra("edit_data"));
                            }

                            return form;
                        }


                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddAlat.this);
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    // Start the image picker
    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selecteduri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selecteduri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                fotoiv.setImageBitmap(bitmap); // Set the selected image in the ImageView
                imageStore(bitmap); // Encode the image to Base64

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Encode the selected image to a Base64 string
    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);

        byte[] imagebyte = stream.toByteArray();
        encodeImage = Base64.encodeToString(imagebyte, Base64.DEFAULT);
    }

    private String encodeImageFromUrl(String imageUrl) {
        try {
            InputStream inputStream = new URL(imageUrl).openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load the existing data for editing
    void getData() {
        String url = new Konfigurasi().baseUrl() + "get_data_" + urlGet;
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                            String gname = jsonObject.getString("name");
                            String gkategori = jsonObject.getString("kategori");
                            String gjumlah = jsonObject.getString("jumlah");
                            oldImage = jsonObject.getString("image"); // Save the old image URL

                            String urlimage = "http://192.168.43.116/lab_elektro/images/" + oldImage;

                            name.setText(gname);
                            kategori.setText(gkategori);
                            jumlah.setText(gjumlah);

                            // Load the old image into the ImageView
                            Glide.with(AddAlat.this).load(urlimage).into(fotoiv);

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
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form = new HashMap<>();
                form.put("id", getIntent().getStringExtra("edit_data"));

                return form;
            }
        };
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
