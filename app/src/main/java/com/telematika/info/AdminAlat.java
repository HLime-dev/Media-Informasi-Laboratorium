package com.telematika.info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.telematika.info.Adapter.Adaptor;
import com.telematika.info.Adapter.AlatAdaptor;
import com.telematika.info.Adapter.GetData;
import com.telematika.info.Adapter.GetDataAlat;
import com.telematika.info.Adapter.GetDataMahasiswa;
import com.telematika.info.Adapter.MahasiswaAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminAlat extends AppCompatActivity implements SelectListener{

    Toolbar toolbar;
    RecyclerView recyclerView;
    String url = "https://medtele.000webhostapp.com/tampil_data_alat.php";
    List<GetDataAlat> alatimagelist;
    GetDataAlat getDataAlat;
    LinearLayoutManager linearLayoutManager;
    AlatAdaptor alatAdaptor;
    FloatingActionButton tambah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_alat);


        recyclerView = findViewById(R.id.rv);
        linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        alatimagelist = new ArrayList<>();
        alatAdaptor = new AlatAdaptor(this, alatimagelist, this, this);
        recyclerView.setAdapter(alatAdaptor);

        toolbar = findViewById(R.id.toolbar);
        tambah=findViewById(R.id.tambah);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        load_data();


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAlat.class);
                startActivity(intent);
            }
        });

    }

    void load_data() {
        //String url = new Konfigurasi().baseUrl() + "tampil_data_alat.php";

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alatimagelist.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String name = object.getString("name");
                            String kategori = object.getString("kategori");
                            String jumlah = object.getString("jumlah");
                            String url2 = object.getString("image");

                            String urlimage = "https://medtele.000webhostapp.com/images/"+url2;

                            getDataAlat = new GetDataAlat(id, name, kategori, jumlah, urlimage);
                            alatimagelist.add(getDataAlat);
                            alatAdaptor.notifyDataSetChanged();

                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        Toast.makeText(AdminAlat.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(AdminAlat.this);
        requestQueue.add(request);
    }

    public void _hapus(String id)
    {
        String url=new Konfigurasi().baseUrl()+"hapus_alat.php";
        StringRequest request=new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if (status.equals("data_berhasil_dihapus"))
                            {
                                Toast.makeText(AdminAlat.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                load_data();
                            }
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
                HashMap<String, String> form=new HashMap<String, String>();
                form.put("id", id);
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

    @Override
    protected void onResume() {
        load_data();
        super.onResume();
    }

    @Override
    public void onItemClicked(GetData getData) {

    }

    @Override
    public void onItemClicked(GetDataAlat getDataAlat) {

    }
}