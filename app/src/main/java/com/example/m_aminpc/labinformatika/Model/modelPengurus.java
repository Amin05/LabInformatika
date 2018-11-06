package com.example.m_aminpc.labinformatika.Model;

public class modelPengurus {
    public Integer id_pengurus, id_lab;
    public String nama_pengurus, jabatan, no_hp, email_pengurus, gambar_pengurus;

    public modelPengurus( Integer id_pengurus, Integer id_lab, String nama_pengurus, String jabatan, String no_hp, String email_pengurus, String gambar_pengurus){
        this.id_pengurus= id_pengurus;
        this.id_lab= id_lab;
        this.nama_pengurus= nama_pengurus;
        this.jabatan= jabatan;
        this.no_hp= no_hp;
        this.email_pengurus= email_pengurus;
        this.gambar_pengurus= gambar_pengurus;
    }
}
