package com.example.m_aminpc.labinformatika.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.example.m_aminpc.labinformatika.API.Server;
import com.example.m_aminpc.labinformatika.R;
import com.example.m_aminpc.labinformatika.Sliders.FragmentSlider;
import com.example.m_aminpc.labinformatika.Sliders.SliderIndicator;
import com.example.m_aminpc.labinformatika.Sliders.SliderPagerAdapter;
import com.example.m_aminpc.labinformatika.Sliders.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    final static int timeout=5000;
    private String url= Server.URL;
    final static RetryPolicy policy=new DefaultRetryPolicy(timeout,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sliderView = findViewById(R.id.sliderView);
        mLinearLayout = findViewById(R.id.pagesContainer);
        setupSlider();
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

    public void berita(View v)
    {
        Intent intent = new Intent(HomeActivity.this, BeritaActivity.class);
        intent.putExtra("nama_menu","MENU BERITA");
        startActivity(intent);
        Log.i("ez","berita di klik");

    }

    public void kegiatan(View v)
    {
        Intent intent = new Intent(HomeActivity.this, KegiatanActivity.class);
        intent.putExtra("nama_menu","MENU JADWAL KEGIATAN");
        startActivity(intent);
        Log.i("ez","kegiatan di klik");
    }

    public void komputasi(View v)
    {
        Intent intent = new Intent(HomeActivity.this, KategoriActivity.class);
        intent.putExtra("nama_lab","LAB. KOMPUTASI DAN BAHASA");
        intent.putExtra("id_lab","1");
        startActivity(intent);
        Log.i("ez","komputasi di klik");
    }

    public void pemrograman(View v)
    {
        Intent intent = new Intent(HomeActivity.this, KategoriActivity.class);
        intent.putExtra("nama_lab","LAB. KOMPUTER DASAR DAN PEMROGRAMAN");
        intent.putExtra("id_lab","2");
        startActivity(intent);
        Log.i("ez","pemrograman di klik");
    }

    public void jarkom(View v)
    {
        Intent intent = new Intent(HomeActivity.this, KategoriActivity.class);
        intent.putExtra("nama_lab","LAB. JARINGAN KOMPUTER DAN KEAMANAN DATA");
        intent.putExtra("id_lab","3");
        startActivity(intent);
        Log.i("ez","jarkom di klik");
    }

    public void rpl(View v)
    {
        Intent intent = new Intent(HomeActivity.this, KategoriActivity.class);
        intent.putExtra("nama_lab","LAB. REKAYASA PERANGKAT LUNAK DAN DATA");
        intent.putExtra("id_lab","4");
        startActivity(intent);
        Log.i("ez","rpl di klik");
    }

    public void sti(View v)
    {
        Intent intent = new Intent(HomeActivity.this, KategoriActivity.class);
        intent.putExtra("nama_lab","LAB. SISTEM DAN TEKNOLOGI INFORMASI");
        intent.putExtra("id_lab","5");
        startActivity(intent);
        Log.i("ez","sti di klik");
    }

}
