package com.example.m_aminpc.labinformatika.Model;

public class modelBerita {
    public String judul, gambar_berita, deskripsi_berita;
    public Integer id_berita;
    public modelBerita(Integer id_berita, String judul, String gambar_berita, String deskripsi_berita){
        this.id_berita= id_berita;
        this.judul= judul;
        this.gambar_berita= gambar_berita;
        this.deskripsi_berita= deskripsi_berita;
    }
}
