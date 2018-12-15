package com.example.m_aminpc.labinformatika.Model;

public class modelPraktikum {
    public String nama_praktikum, tanggal, waktu, tempat, asisten_lab, gambar, keterangan,hari;
    public Integer id_praktikum, id_lab;

    public modelPraktikum(Integer id_praktikum, Integer id_lab, String nama_praktikum, String hari, String tanggal, String waktu, String tempat, String asisten_lab, String gambar, String keterangan) {
        this.id_praktikum = id_praktikum;
        this.id_lab = id_lab;
        this.nama_praktikum = nama_praktikum;
        this.hari = hari;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.tempat = tempat;
        this.asisten_lab = asisten_lab;
        this.gambar = gambar;
        this.keterangan = keterangan;
    }
}
