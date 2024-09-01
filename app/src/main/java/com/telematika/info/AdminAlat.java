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

public class AdminAlat extends AppCompatActivity{

    Toolbar toolbar;
    ListView listView;
    ArrayList<GetDataAlat> model;
    AlatAdaptor alatAdaptor;
    GetDataAlat getDataAlat;
    FloatingActionButton tambah;

    String urlPlus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_alat);

        listView=findViewById(R.id.list);
        model = new ArrayList<>();
        alatAdaptor = new AlatAdaptor(getApplicationContext(), model);
        listView.setAdapter(alatAdaptor);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("lab1")) {
            urlPlus = getIntent().getStringExtra("lab1");
        } else if (getIntent().hasExtra("lab2")) {
            urlPlus = getIntent().getStringExtra("lab2");
        } else if (getIntent().hasExtra("lab3")) {
            urlPlus = getIntent().getStringExtra("lab3");
        }else if (getIntent().hasExtra("lab4")) {
            urlPlus = getIntent().getStringExtra("lab4");
        }else if (getIntent().hasExtra("lab5")) {
            urlPlus = getIntent().getStringExtra("lab5");
        }else if (getIntent().hasExtra("lab6")) {
            urlPlus = getIntent().getStringExtra("lab6");
        }else if (getIntent().hasExtra("lab7")) {
            urlPlus = getIntent().getStringExtra("lab7");
        }else if (getIntent().hasExtra("lab8")) {
            urlPlus = getIntent().getStringExtra("lab8");
        }else if (getIntent().hasExtra("lab9")) {
            urlPlus = getIntent().getStringExtra("lab9");
        }else if (getIntent().hasExtra("lab10")) {
            urlPlus = getIntent().getStringExtra("lab10");
        }else if (getIntent().hasExtra("lab11")) {
            urlPlus = getIntent().getStringExtra("lab11");
        }else if (getIntent().hasExtra("lab12")) {
            urlPlus = getIntent().getStringExtra("lab12");
        }else if (getIntent().hasExtra("lab13")) {
            urlPlus = getIntent().getStringExtra("lab13");
        }

        load_data();
        tambah=findViewById(R.id.tambah);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAlat.class);
                if (getIntent().hasExtra("lab1")) {
                    intent.putExtra("lab1", "alat1.php");
                } else if (getIntent().hasExtra("lab2")) {
                    intent.putExtra("lab2", "alat2.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab3", "alat3.php");
                } else if (getIntent().hasExtra("lab4")) {
                    intent.putExtra("lab4", "alat4.php");
                } else if (getIntent().hasExtra("lab3")) {
                    intent.putExtra("lab5", "alat5.php");
                } else if (getIntent().hasExtra("lab6")) {
                    intent.putExtra("lab6", "alat6.php");
                } else if (getIntent().hasExtra("lab7")) {
                    intent.putExtra("lab7", "alat7.php");
                } else if (getIntent().hasExtra("lab8")) {
                    intent.putExtra("lab8", "alat8.php");
                } else if (getIntent().hasExtra("lab9")) {
                    intent.putExtra("lab9", "alat9.php");
                } else if (getIntent().hasExtra("lab10")) {
                    intent.putExtra("lab10", "alat10.php");
                } else if (getIntent().hasExtra("lab11")) {
                    intent.putExtra("lab11", "alat11.php");
                } else if (getIntent().hasExtra("lab12")) {
                    intent.putExtra("lab12", "alat12.php");
                } else if (getIntent().hasExtra("lab13")) {
                    intent.putExtra("lab13", "alat13.php");
                }
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(AdminAlat.this, view);  // Menggunakan Activity sebagai konteks
                popupMenu.getMenuInflater().inflate(R.menu.menu_opsi, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent intent = new Intent(AdminAlat.this, AddAlat.class);
                                intent.putExtra("edit_data", model.get(position).getId());

                                // Loop through all lab extras
                                for (int i = 1; i <= 13; i++) {
                                    String labKey = "lab" + i;
                                    if (getIntent().hasExtra(labKey)) {
                                        intent.putExtra("edit_" + labKey, "alat" + i + ".php");
                                        break;
                                    }
                                }

                                for (int i = 1; i <= 13; i++) {
                                    String labKey = "lab" + i;
                                    if (getIntent().hasExtra(labKey)) {
                                        intent.putExtra(labKey, "alat" + i + ".php");
                                        break;
                                    }
                                }

                                startActivity(intent);
                                return true;

                            case R.id.hapus:
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminAlat.this);
                                builder.setMessage("Apakah Anda ingin menghapus data ini?")
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                _hapus(model.get(position).getId());
                                            }
                                        })
                                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                return true;

                            default:
                                return false;
                        }
                    }
                });
            }
        });

    }

    void load_data() {
        String url = new Konfigurasi().baseUrl() + "tampil_data_" + urlPlus;

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                model.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String nama = object.getString("nama");
                            String kategori = object.getString("kategori");
                            String jumlah = object.getString("jumlah");
                            String url2 = object.getString("image");

                            String urlimage = "http://192.168.43.116/lab_elektro/images/" + url2;

                            getDataAlat = new GetDataAlat(id, nama, kategori, jumlah, urlimage);
                            model.add(getDataAlat);
                            alatAdaptor.notifyDataSetChanged(); // Notify the adapter that the data has changed
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
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    void _hapus(String id)
    {
        String url=new Konfigurasi().baseUrl()+"hapus_"+urlPlus;
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

}