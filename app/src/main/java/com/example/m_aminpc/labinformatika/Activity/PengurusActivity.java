package com.example.m_aminpc.labinformatika.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
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
import com.example.m_aminpc.labinformatika.Adapter.AdapterListPengurus;
import com.example.m_aminpc.labinformatika.Model.modelPengurus;
import com.example.m_aminpc.labinformatika.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PengurusActivity extends AppCompatActivity {
    TextView tvNmMenu;
    final static int timeout=5000;
    private Context ctx;
    private Activity avy;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lml;
    private List<modelPengurus> arrayofPengurus = new ArrayList<>();
    private AdapterListPengurus adapterListPengurus;
    private String url= Server.URL+"listLabMenu.php?id_menu=5&id_lab=";
    final static RetryPolicy policy=new DefaultRetryPolicy(timeout,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = this.getIntent();
        tvNmMenu=findViewById(R.id.tvNmMenu);
        tvNmMenu.setText("LIST PENGURUS\n"+ intent.getStringExtra("nama_lab"));
        tvNmMenu.setGravity(Gravity.CENTER);
        int lab = Integer.valueOf(intent.getIntExtra(String.valueOf("id_lab"),0));
        //Log.i("ezz"+intent.getIntExtra(String.valueOf("id_lab"),0), "berhasil");
        to_viewInventaris(lab);
    }

    private void to_viewInventaris(int id_lab) {

        url= url+Integer.valueOf(id_lab);

        recyclerView = findViewById(R.id.recycler_view);
        lml=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lml);
        adapterListPengurus = new AdapterListPengurus(arrayofPengurus, this);
        recyclerView.setAdapter(adapterListPengurus);
        recyclerView.setVisibility(View.VISIBLE);


        StringRequest kirim = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("ez1", response);
                    JSONArray ja = new JSONArray(response);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = new JSONObject(ja.get(i).toString());
                        arrayofPengurus.add(new modelPengurus(jo.getInt("id_pengurus"),jo.getInt("id_lab"), jo.getString("nama_pengurus"),jo.getString("jabatan"), jo.getString("no_hp"),jo.getString("email_pengurus"), jo.getString("gambar_pengurus")));
                    }
                    adapterListPengurus.notifyDataSetChanged();
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
