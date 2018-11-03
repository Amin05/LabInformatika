package com.example.m_aminpc.labinformatika.Model;

public class modelKegiatan {
    public Integer id_kegiatan;
    public String nama_kegiatan, tanggal, waktu, tempat, gambar_kegiatan, deskripsi_kegiatan;

    public modelKegiatan(Integer id_kegiatan, String nama_kegiatan,String tanggal, String waktu, String tempat, String gambar_kegiatan, String deskripsi_kegiatan){
        this.id_kegiatan= id_kegiatan;
        this.nama_kegiatan= nama_kegiatan;
        this.tanggal= tanggal;
        this.waktu= waktu;
        this.tempat= tempat;
        this.gambar_kegiatan= gambar_kegiatan;
        this.deskripsi_kegiatan= deskripsi_kegiatan;
    }
}
