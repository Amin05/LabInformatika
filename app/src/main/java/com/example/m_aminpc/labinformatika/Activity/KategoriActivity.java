package com.example.m_aminpc.labinformatika.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.example.m_aminpc.labinformatika.R;

public class KategoriActivity extends AppCompatActivity {
    TextView nama_menu;
    final static int timeout = 5000;
    final static RetryPolicy policy = new DefaultRetryPolicy(timeout, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    Integer lab;
    String nama_lab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        nama_menu=findViewById(R.id.tvNamaLab);
        Intent intent=this.getIntent();
        nama_menu.setText(intent.getStringExtra("nama_lab"));

        lab = Integer.valueOf(intent.getStringExtra("id_lab"));
        nama_lab = intent.getStringExtra("nama_lab");
        Log.i("ez","kategori lab yang dipilih "+lab);

    }

    public void modul(View v)
    {
        Intent intent = new Intent(KategoriActivity.this, ModulActivity.class);
        intent.putExtra("id_lab",lab);
        intent.putExtra("nama_lab",nama_lab);
        startActivity(intent);
        Log.i("ez","modul di klik "+lab);

    }

    public void praktikum(View v)
    {
        Intent intent = new Intent(KategoriActivity.this, PraktikumActivity.class);
        intent.putExtra("id_lab",lab);
        intent.putExtra("nama_lab",nama_lab);
        startActivity(intent);
        Log.i("ez","praktikum di klik "+lab);
    }

    public void inventaris(View v)
    {
        Intent intent = new Intent(KategoriActivity.this, InventarisActivity.class);
        intent.putExtra("id_lab",lab);
        intent.putExtra("nama_lab",nama_lab);
        startActivity(intent);
        Log.i("ez","inventaris di klik "+lab);

    }

    public void pengurus(View v)
    {
        Intent intent = new Intent(KategoriActivity.this, PengurusActivity.class);
        intent.putExtra("id_lab",lab);
        intent.putExtra("nama_lab",nama_lab);
        startActivity(intent);
        Log.i("ez","pengurus di klik "+lab);
    }


}
