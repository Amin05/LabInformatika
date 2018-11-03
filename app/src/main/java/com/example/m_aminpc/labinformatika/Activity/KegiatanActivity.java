package com.example.m_aminpc.labinformatika.Activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.m_aminpc.labinformatika.API.Server;
import com.example.m_aminpc.labinformatika.Adapter.AdapterListBerita;
import com.example.m_aminpc.labinformatika.Adapter.AdapterListKegiatan;
import com.example.m_aminpc.labinformatika.Model.modelBerita;
import com.example.m_aminpc.labinformatika.Model.modelKegiatan;
import com.example.m_aminpc.labinformatika.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KegiatanActivity extends AppCompatActivity {
    TextView tvNmMenu;
    final static int timeout = 5000;
    private Context ctx;
    private Activity avy;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lml;
    private List<modelKegiatan> arrayofKegiatan = new ArrayList<>();
    private AdapterListKegiatan adapterListKegiatan;
    private String url = Server.URL + "listKegiatan.php";
    final static RetryPolicy policy = new DefaultRetryPolicy(timeout, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        tvNmMenu = findViewById(R.id.tvNmMenu);
        tvNmMenu.setText(String.valueOf("LIST KEGIATAN"));
        to_viewBerita();
    }

    private void to_viewBerita() {
        recyclerView = findViewById(R.id.recycler_view);
        lml = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lml);
        adapterListKegiatan = new AdapterListKegiatan(arrayofKegiatan, this);
        recyclerView.setAdapter(adapterListKegiatan);
        recyclerView.setVisibility(View.VISIBLE);
        //final Intent intent = getIntent();
        //url= url+"?id_berita="+intent.getStringExtra("id");

        StringRequest kirim = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("ez1", response);
                    JSONArray ja = new JSONArray(response);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = new JSONObject(ja.get(i).toString());
                        arrayofKegiatan.add(new modelKegiatan(jo.getInt("id_kegiatan"), jo.getString("nama_kegiatan"),jo.getString("tanggal"),jo.getString("waktu"),jo.getString("tempat"), jo.getString("gambar_kegiatan"), jo.getString("deskripsi_kegiatan")));
                    }
                    adapterListKegiatan.notifyDataSetChanged();
                } catch (JSONException jeo) {
                    Log.i("ez2", jeo.getMessage());

                } catch (Exception eo) {
                    Log.i("ez3", eo.getMessage());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ez4", error.toString());
                if (error instanceof NoConnectionError) {


                } else {

                }

            }

        });
        kirim.setRetryPolicy(policy);
        RequestQueue req;
        req = Volley.newRequestQueue(this);
        req.add(kirim);

    }
}