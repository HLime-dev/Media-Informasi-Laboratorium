package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.Manifest.permission;

import org.json.JSONException;
import org.json.JSONObject;

public class UploadFoto extends AppCompatActivity {

    Uri selecteduri;
    Button pilihFoto, upload;
    ProgressBar progressBar;
    EditText nameEt;
    ImageView foto;
    Bitmap bitmap;
    String url="https://medtele.000webhostapp.com/image.php";
    String encodeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_foto);

        nameEt=findViewById(R.id.imgname);
        pilihFoto=findViewById(R.id.fff);
        foto = findViewById(R.id.foto);
        upload = findViewById(R.id.upload);
        progressBar = findViewById(R.id.pb_img);

        if (savedInstanceState == null) {
            String gnama =getIntent().getStringExtra("nama");
            nameEt.setText(gnama);

        }

        pilihFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameimg = nameEt.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                StringRequest stringRequest = new StringRequest(
                        1, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                nameEt.setText("");
                                progressBar.setVisibility(View.INVISIBLE);
                                try {

                                    JSONObject jsonObject= new JSONObject(response);
                                    String status=jsonObject.getString("status");
                                    if (status.equals("data tersimpan"))
                                    {
                                        AlertDialog.Builder builder=new AlertDialog.Builder(UploadFoto.this);
                                        builder.setTitle("Sukses");
                                        builder.setMessage("Image berhasil diupload");
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
                        Toast.makeText(UploadFoto.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                ) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();

                        param.put("image",encodeImage);
                        param.put("name",nameimg);

                        return param;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(UploadFoto.this);
                requestQueue.add(stringRequest);
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
                foto.setImageBitmap(bitmap);
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

    /*
    private void askPermission() {
        String[] permissionsStorage = {Manifest.permission.READ_EXTERNAL_STORAGE};
        int requestExternalStorage = 1;
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionsStorage, requestExternalStorage);
        }
    }

     */
}


