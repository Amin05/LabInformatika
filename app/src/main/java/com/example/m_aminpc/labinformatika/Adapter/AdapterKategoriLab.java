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
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.m_aminpc.labinformatika.API.MySingleton;
import com.example.m_aminpc.labinformatika.API.Server;
import com.example.m_aminpc.labinformatika.Activity.BeritaActivity;
import com.example.m_aminpc.labinformatika.Activity.KategoriLabActivity;
import com.example.m_aminpc.labinformatika.Activity.KegiatanActivity;
import com.example.m_aminpc.labinformatika.Model.modelKategoriLab;
import com.example.m_aminpc.labinformatika.Model.modelMenu;
import com.example.m_aminpc.labinformatika.Penampil.PenampilKegiatanActivity;
import com.example.m_aminpc.labinformatika.R;

import java.util.List;

public class AdapterKategoriLab extends RecyclerView.Adapter<AdapterKategoriLab.praktekholder> {
public List<modelKategoriLab> listKategoriLab;
public Context ctx;
public LayoutInflater lay;
public ImageLoader imageLoader;

public class praktekholder extends RecyclerView.ViewHolder{
    TextView nama_menu;
    NetworkImageView gambar;
    CardView cvMenu;

    public praktekholder(View itemView) {
        super(itemView);
        this.nama_menu = itemView.findViewById(R.id.tv_nama_menu);
        this.gambar = itemView.findViewById(R.id.imgMenu);
        this.cvMenu = itemView.findViewById(R.id.cvMenu);
    }
}

    @Override
    public int getItemCount() {

        return listKategoriLab.size();
    }

    public AdapterKategoriLab(List<modelKategoriLab> listpraktek, Context ctx) {
        this.imageLoader = MySingleton.getInstance(ctx).getImageLoader();
        this.listKategoriLab = listpraktek;
        this.ctx = ctx;
        this.lay=(LayoutInflater)ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public praktekholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview=lay.inflate(R.layout.item_menu_adapter,parent,false);
        return new praktekholder(rootview);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterKategoriLab.praktekholder holder, int position) {
        final modelKategoriLab praktek=listKategoriLab.get(position);
        holder.nama_menu.setText(praktek.nama_lab);
        holder.gambar.setImageUrl(Server.URL+"gambar/gambarLab/"+praktek.gambar_lab,imageLoader);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, PenampilKegiatanActivity.class);
                intent.putExtra("id_lab", praktek.id_lab);
                Log.i("ez",String.valueOf(praktek.id_lab));
                //ctx.startActivity(intent);

                //intent.putExtra("kategori", praktek.id_kategori);
                //ctx.startActivity(intent);
            }
        });
    }


}
