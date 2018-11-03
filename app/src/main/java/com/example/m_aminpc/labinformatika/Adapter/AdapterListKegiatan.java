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
import com.example.m_aminpc.labinformatika.Model.modelKegiatan;
import com.example.m_aminpc.labinformatika.Penampil.PenampilKegiatanActivity;
import com.example.m_aminpc.labinformatika.R;

import java.util.List;

public class AdapterListKegiatan extends RecyclerView.Adapter<AdapterListKegiatan.praktekholder> {
    public List<modelKegiatan> listKegiatan;
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
            this.baca.setText("LIHAT");
        }
    }


    @Override
    public int getItemCount() {
        return listKegiatan.size();
    }

    public AdapterListKegiatan(List<modelKegiatan> listpraktek, Context ctx) {
        this.imageLoader = MySingleton.getInstance(ctx).getImageLoader();
        this.listKegiatan = listpraktek;
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
    public void onBindViewHolder(@NonNull AdapterListKegiatan.praktekholder holder, int position) {
        final modelKegiatan praktek = listKegiatan.get(position);
        holder.judul.setText(praktek.nama_kegiatan);
        holder.keterangan.setText("Tanggal  : "+ praktek.tanggal + "\nJam  : " + praktek.waktu + " WIB ");

        holder.gambar.setImageUrl(Server.URL + "gambar/gambarKegiatan/" + praktek.gambar_kegiatan, imageLoader);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.baca.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(ctx, PenampilKegiatanActivity.class);
                                               intent.putExtra("id_kegiatan", praktek.id_kegiatan);
                                               //Log.i("ez",praktek.id_berita);
                                               ctx.startActivity(intent);
                                           }
                                       }

        );
    }
}
