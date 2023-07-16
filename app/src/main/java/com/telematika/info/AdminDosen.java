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
import android.widget.Button;
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
import com.telematika.info.Adapter.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDosen extends AppCompatActivity implements SelectListener{

    Toolbar toolbar;
    RecyclerView recyclerView;
    String url = "https://medtele.000webhostapp.com/get_data_dosen.php";
    List<GetData> imagelist;
    GetData getData;
    LinearLayoutManager linearLayoutManager;
    Adaptor adaptor;
    FloatingActionButton tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dosen);

        recyclerView = findViewById(R.id.rv);
        linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        imagelist = new ArrayList<>();
        adaptor = new Adaptor(this, imagelist, this, this);
        recyclerView.setAdapter(adaptor);

        toolbar = findViewById(R.id.toolbar);
        tambah=findViewById(R.id.tambah);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        load_data();

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDosen.class);
                startActivity(intent);
            }
        });

    }

    private void load_data() {
        //String url = new Konfigurasi().baseUrl() + "tampil_data.php";

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //model.clear(); // Clear the existing data
                imagelist.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            /*
                            model.add(new GetData(

                                    getData.getString("id"),
                                    getData.getString("name"),
                                    getData.getString("jabatan"),
                                    getData.getString("email"),
                                    getData.getString("image")
                            ));

                             */

                            String id = object.getString("id");
                            String name = object.getString("name");
                            String jabatan = object.getString("jabatan");
                            String email = object.getString("email");
                            String url2 = object.getString("image");

                            String urlimage = "https://medtele.000webhostapp.com/images/"+url2;

                            getData = new GetData(id, name, jabatan, email, urlimage);
                            imagelist.add(getData);
                            adaptor.notifyDataSetChanged();

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
                        Toast.makeText(AdminDosen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(AdminDosen.this);
        requestQueue.add(request);
    }

    public void _hapus(String id)
    {
        String url=new Konfigurasi().baseUrl()+"hapus.php";
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
                                Toast.makeText(AdminDosen.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        load_data();
        super.onResume();
    }

    @Override
    public void onItemClicked(GetData getData) {

    }
}