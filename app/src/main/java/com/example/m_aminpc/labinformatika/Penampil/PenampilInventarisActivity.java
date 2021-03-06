package com.example.m_aminpc.labinformatika.Penampil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.m_aminpc.labinformatika.API.MySingleton;
import com.example.m_aminpc.labinformatika.API.Server;
import com.example.m_aminpc.labinformatika.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PenampilInventarisActivity extends AppCompatActivity {
    TextView tvJudul, tvDeskripsi, tvKeterangan;
    NetworkImageView imgBerita;
    public ImageLoader imageLoader;
    final static int timeout=5000;
    private Context ctx;
    private Activity avy;
    private String url= Server.URL;
    final static RetryPolicy policy=new DefaultRetryPolicy(timeout,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penampil_list);
        url= url+"listLabMenu.php?id_menu=3&id_lab="+ getIntent().getIntExtra("id_lab",0)+"&id_inventaris=";
        tvJudul=findViewById(R.id.tvJudul);
        tvDeskripsi=findViewById(R.id.tvDeskripsi);
        tvKeterangan=findViewById(R.id.tvKeterangan);
        imgBerita=findViewById(R.id.imgBerita);
        this.imageLoader = MySingleton.getInstance(ctx).getImageLoader();
        Intent intent=this.getIntent();
        to_viewBerita(intent.getIntExtra("id_inventaris",0));

    }

    private void to_viewBerita(int id_berita) {
        url= url+ String.valueOf(id_berita);

        StringRequest kirim = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("ez1", response);
                    JSONArray ja = new JSONArray(response);
                    JSONObject jo = new JSONObject(ja.get(0).toString());
                    tvJudul.setText(jo.getString("nama_inventaris"));
                    tvDeskripsi.setText(jo.getString("deskripsi_inventaris"));
                    tvKeterangan.setText("Jumlah   : "+jo.getString("jumlah"));
                    imgBerita.setImageUrl(Server.URL+"gambar/gambarInventaris/"+ jo.getString("gambar_inventaris"),imageLoader);

                    //adapterBerita.notifyDataSetChanged();
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