package com.example.m_aminpc.labinformatika.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.m_aminpc.labinformatika.API.MySingleton;
import com.example.m_aminpc.labinformatika.API.Server;
import com.example.m_aminpc.labinformatika.Model.modelPengurus;
import com.example.m_aminpc.labinformatika.Model.modelPraktikum;
import com.example.m_aminpc.labinformatika.Penampil.PenampilKegiatanActivity;
import com.example.m_aminpc.labinformatika.Penampil.PenampilPraktikumActivity;
import com.example.m_aminpc.labinformatika.R;

import java.util.List;

public class AdapterListPraktikum extends RecyclerView.Adapter<AdapterListPraktikum.praktekholder> {
public List<modelPraktikum> listPengurus;
public Context ctx;
public LayoutInflater lay;
public ImageLoader imageLoader;

public class praktekholder extends RecyclerView.ViewHolder {
    TextView judul,keterangan;
    NetworkImageView gambar;
    Button baca;
    CardView cvList;

    public praktekholder(View itemView) {
        super(itemView);
        this.judul = itemView.findViewById(R.id.tvJudul);
        this.gambar = itemView.findViewById(R.id.imgGambar);
        this.baca = itemView.findViewById(R.id.btnBaca);
        this.cvList = itemView.findViewById(R.id.cvList);
        this.keterangan = itemView.findViewById(R.id.tvKeterangan);
        this.baca.setText("BACA");
    }
}


    @Override
    public int getItemCount() {
        return listPengurus.size();
    }

    public AdapterListPraktikum(List<modelPraktikum> listpraktek, Context ctx) {
        this.imageLoader = MySingleton.getInstance(ctx).getImageLoader();
        this.listPengurus = listpraktek;
        this.ctx = ctx;
        this.lay = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdapterListPraktikum.praktekholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = lay.inflate(R.layout.item_list_adapter, parent, false);
        return new praktekholder(rootview);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListPraktikum.praktekholder holder, int position) {
        final modelPraktikum praktek = listPengurus.get(position);
        holder.judul.setText(praktek.nama_praktikum);
        //holder.keterangan.setText("Tanggal  : "+ praktek.tanggal + "\nJam  : " + praktek.waktu + " WIB ");

        holder.gambar.setImageUrl(Server.URL + "gambar/gambarPraktikum/" + praktek.gambar, imageLoader);
        holder.keterangan.setText("Tanggal  : "+ praktek.tanggal + "\nJam  : " + praktek.waktu + " WIB ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.baca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, PenampilPraktikumActivity.class);
                intent.putExtra("id_praktikum", praktek.id_praktikum);
                intent.putExtra("id_lab", praktek.id_lab);
                Log.i("ez",""+praktek.id_praktikum);
                ctx.startActivity(intent);
            }
        });
    }
}