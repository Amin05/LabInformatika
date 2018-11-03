package com.example.m_aminpc.labinformatika.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.m_aminpc.labinformatika.API.MySingleton;
import com.example.m_aminpc.labinformatika.API.Server;
import com.example.m_aminpc.labinformatika.Penampil.PenampilBeritaActivity;
import com.example.m_aminpc.labinformatika.Model.modelBerita;
import com.example.m_aminpc.labinformatika.R;

import java.util.List;

public class AdapterListBerita extends RecyclerView.Adapter<AdapterListBerita.praktekholder> {
    public List<modelBerita> listBerita;
    public Context ctx;
    public LayoutInflater lay;
    public ImageLoader imageLoader;

    public class praktekholder extends RecyclerView.ViewHolder {
        TextView judul;
        NetworkImageView gambar;
        Button baca;
        CardView cvList;

        public praktekholder(View itemView) {
            super(itemView);
            this.judul = itemView.findViewById(R.id.tvJudul);
            this.gambar = itemView.findViewById(R.id.imgGambar);
            this.baca = itemView.findViewById(R.id.btnBaca);
            this.cvList = itemView.findViewById(R.id.cvList);
        }
    }


    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    public AdapterListBerita(List<modelBerita> listpraktek, Context ctx) {
        this.imageLoader = MySingleton.getInstance(ctx).getImageLoader();
        this.listBerita = listpraktek;
        this.ctx = ctx;
        this.lay = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public praktekholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = lay.inflate(R.layout.item_list_adapter, parent, false);
        return new praktekholder(rootview);

    }

    @Override
    public void onBindViewHolder(@NonNull praktekholder holder, int position) {
        final modelBerita praktek = listBerita.get(position);
        holder.judul.setText(praktek.judul);

        holder.gambar.setImageUrl(Server.URL + "gambar/gambarBerita/" + praktek.gambar_berita, imageLoader);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.baca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, PenampilBeritaActivity.class);
                intent.putExtra("id_berita", praktek.id_berita);
                //Log.i("ez",praktek.id_berita);
                ctx.startActivity(intent);
            }
        }

        );
    }
}