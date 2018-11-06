package com.example.m_aminpc.labinformatika.Model;

public class modelInventaris {
    public Integer id_inventaris, id_lab, jumlah;
    public String nama_inventaris, deskripsi_inventaris, gambar_inventaris;

    public modelInventaris(Integer id_inventaris, Integer id_lab, String nama_inventaris, Integer jumlah, String deskripsi_inventaris, String gambar_inventaris){
        this.id_inventaris=id_inventaris;
        this.id_lab=id_lab;
        this.nama_inventaris=nama_inventaris;
        this.jumlah=jumlah;
        this.deskripsi_inventaris=deskripsi_inventaris;
        this.gambar_inventaris=gambar_inventaris;
    }

}
