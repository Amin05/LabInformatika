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
import com.example.m_aminpc.labinformatika.Model.modelMenu;
import com.example.m_aminpc.labinformatika.R;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.praktekholder> {
    public List<modelMenu> listMenu;
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

        return listMenu.size();
    }

    public AdapterMenu(List<modelMenu> listpraktek, Context ctx) {
        this.imageLoader = MySingleton.getInstance(ctx).getImageLoader();
        this.listMenu = listpraktek;
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
    public void onBindViewHolder(@NonNull final praktekholder holder, int position) {
        final modelMenu praktek=listMenu.get(position);
        holder.nama_menu.setText(praktek.nama_menu);
        holder.gambar.setImageUrl(Server.URL+"gambar/gambarMenu/"+praktek.logo_menu,imageLoader);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (praktek.id_menu){
                    case "1":
                        Intent intent = new Intent(ctx,BeritaActivity.class);
                        ctx.startActivity(intent);
                        Log.i("ez_idMenu",praktek.id_menu);
                        break;
                    case "2":
                        Intent intent2 = new Intent(ctx,KegiatanActivity.class);
                        ctx.startActivity(intent2);
                        Log.i("ez_idMenu",praktek.id_menu);
                        break;

                    case "3":
                        Intent intent3 = new Intent(ctx,KategoriLabActivity.class);
                        intent3.putExtra("id_menu",praktek.id_menu);
                        intent3.putExtra("nama_menu","MENU INVENTARIS LAB");
                        ctx.startActivity(intent3);
                        Log.i("ez_idMenu",praktek.id_menu);
                        break;
                    case "4":
                        Intent intent4 = new Intent(ctx,KategoriLabActivity.class);
                        intent4.putExtra("id_menu",praktek.id_menu);
                        intent4.putExtra("nama_menu","MENU MODUL PRAKTIKUM");
                        ctx.startActivity(intent4);
                        Log.i("ez_idMenu",praktek.id_menu);
                        break;
                    case "5":
                        Intent intent5 = new Intent(ctx,KategoriLabActivity.class);
                        intent5.putExtra("id_menu",praktek.id_menu);
                        intent5.putExtra("nama_menu","MENU PENGURUS LAB");
                        ctx.startActivity(intent5);
                        Log.i("ez_idMenu",praktek.id_menu);
                        break;
                }

                //intent.putExtra("kategori", praktek.id_kategori);
                //ctx.startActivity(intent);
            }
        });
    }

}