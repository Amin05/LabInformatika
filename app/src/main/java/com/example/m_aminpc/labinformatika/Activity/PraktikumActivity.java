package com.example.m_aminpc.labinformatika.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.m_aminpc.labinformatika.Adapter.AdapterListInventaris;
import com.example.m_aminpc.labinformatika.Adapter.AdapterListPraktikum;
import com.example.m_aminpc.labinformatika.Model.modelInventaris;
import com.example.m_aminpc.labinformatika.Model.modelPraktikum;
import com.example.m_aminpc.labinformatika.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PraktikumActivity extends AppCompatActivity {
    TextView tvNmMenu;
    final static int timeout=5000;
    private Context ctx;
    private Activity avy;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lml;
    private List<modelPraktikum> arrayofPraktikum = new ArrayList<>();
    private AdapterListPraktikum adapterListPraktikum;
    private String url= Server.URL+"listLabMenu.php?id_menu=6&id_lab=";
    private String url2= Server.URL+"listSemester.php";
    final static RetryPolicy policy=new DefaultRetryPolicy(timeout,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    String semester,tahun1,tahun2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = this.getIntent();
        tvNmMenu=findViewById(R.id.tvNmMenu);
        to_getSemester();
//        tvNmMenu.setText("LIST PRAKTIKUM\n"+ intent.getStringExtra("nama_lab"));
        tvNmMenu.setGravity(Gravity.CENTER);
        int lab = Integer.valueOf(intent.getIntExtra(String.valueOf("id_lab"),0));
        //Log.i("ezz"+intent.getIntExtra(String.valueOf("id_lab"),0), "berhasil");
        to_viewInventaris(lab);

    }
    private void to_getSemester(){
        StringRequest kirim = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("ez1", response);
                    JSONArray ja = new JSONArray(response);
                    JSONObject jo = new JSONObject(ja.get(0).toString());
                    semester=jo.getString("semester");
                    tahun1=jo.getString("tahun1");
                    tahun2=jo.getString("tahun2");
                    tvNmMenu.setText("LIST PRAKTIKUM\n"+ getIntent().getStringExtra("nama_lab")+"\n SEMESTER "+semester+" "+tahun1+"/"+tahun2);

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

    private void to_viewInventaris(int id_lab) {

        url= url+Integer.valueOf(id_lab);

        recyclerView = findViewById(R.id.recycler_view);
        lml=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lml);
        adapterListPraktikum = new AdapterListPraktikum(arrayofPraktikum, this);
        recyclerView.setAdapter(adapterListPraktikum);
        recyclerView.setVisibility(View.VISIBLE);


        StringRequest kirim = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("ez1", response);
                    JSONArray ja = new JSONArray(response);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = new JSONObject(ja.get(i).toString());
                        arrayofPraktikum.add(new modelPraktikum(jo.getInt("id_praktikum"),jo.getInt("id_lab"), jo.getString("nama_praktikum"),jo.getString("hari"),jo.getString("tanggal"), jo.getString("waktu"), jo.getString("tempat"), jo.getString("asisten_lab"),jo.getString("gambar"), jo.getString("keterangan")));
                    }
                    adapterListPraktikum.notifyDataSetChanged();
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
