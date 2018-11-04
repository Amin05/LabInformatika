package com.example.m_aminpc.labinformatika.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.example.m_aminpc.labinformatika.Adapter.AdapterMenu;
import com.example.m_aminpc.labinformatika.Model.modelMenu;
import com.example.m_aminpc.labinformatika.R;
import com.example.m_aminpc.labinformatika.Sliders.FragmentSlider;
import com.example.m_aminpc.labinformatika.Sliders.SliderIndicator;
import com.example.m_aminpc.labinformatika.Sliders.SliderPagerAdapter;
import com.example.m_aminpc.labinformatika.Sliders.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    final static int timeout=5000;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lml;
    private List<modelMenu> arrayofMenu = new ArrayList<>();
    private AdapterMenu adapterMenu;
    private String url= Server.URL;
    final static RetryPolicy policy=new DefaultRetryPolicy(timeout,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sliderView = findViewById(R.id.sliderView);
        //TextView textView=findViewById(R.id.textView);
        //Typeface font= Typeface.createFromFile("font/abril_fatface.tff");
        //textView.setTypeface(font);
        mLinearLayout = findViewById(R.id.pagesContainer);
        setupSlider();
        to_viewMenu();
    }


    private void setupSlider() {
        sliderView.setDurationScroll(1000);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance(Server.URL+"gambar/gambarSlide/gambar1.jpg"));
        fragments.add(FragmentSlider.newInstance(Server.URL+"gambar/gambarSlide/gambar2.jpg"));
        fragments.add(FragmentSlider.newInstance(Server.URL+"gambar/gambarSlide/gambar3.jpg"));
        fragments.add(FragmentSlider.newInstance(Server.URL+"gambar/gambarSlide/gambar4.jpg"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    private void to_viewMenu() {
        recyclerView = findViewById(R.id.recycler_view);
        lml = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(lml);
        adapterMenu = new AdapterMenu(arrayofMenu, this);
        recyclerView.setAdapter(adapterMenu);
        recyclerView.setVisibility(View.VISIBLE);
        final Intent intent = getIntent();
        url = url + "listMenu.php";

        StringRequest kirim = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("ez1", response);
                    JSONArray ja = new JSONArray(response);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = new JSONObject(ja.get(i).toString());
                        arrayofMenu.add(new modelMenu(jo.getString("id_menu"), jo.getString("nama_menu"), jo.getString("tampilan"), jo.getString("logo_menu")));
                    }
                    adapterMenu.notifyDataSetChanged();
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