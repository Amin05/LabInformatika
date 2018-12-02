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
import com.example.m_aminpc.labinformatika.Model.modelModul;
import com.example.m_aminpc.labinformatika.Penampil.PenampilInventarisActivity;
import com.example.m_aminpc.labinformatika.Penampil.PenampilModulActivity;
import com.example.m_aminpc.labinformatika.R;

import java.util.List;

public class AdapterListModul extends RecyclerView.Adapter<AdapterListModul.praktekholder> {
    public List<modelModul> listModul;
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
        return listModul.size();
    }

    public AdapterListModul(List<modelModul> listpraktek, Context ctx) {
        this.imageLoader = MySingleton.getInstance(ctx).getImageLoader();
        this.listModul = listpraktek;
        this.ctx = ctx;
        this.lay = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdapterListModul.praktekholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = lay.inflate(R.layout.item_list_adapter, parent, false);
        return new praktekholder(rootview);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListModul.praktekholder holder, int position) {
        final modelModul praktek = listModul.get(position);
        holder.judul.setText(praktek.nama_modul);
        //holder.keterangan.setText("Tanggal  : "+ praktek.tanggal + "\nJam  : " + praktek.waktu + " WIB ");

        holder.gambar.setImageUrl(Server.URL + "gambar/gambarModul/" + praktek.gambar_modul, imageLoader);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.baca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, PenampilModulActivity.class);
                intent.putExtra("file", praktek.file_modul);
                Log.i("ez",""+praktek.id_modul);
                ctx.startActivity(intent);
            }
        });
    }
}
