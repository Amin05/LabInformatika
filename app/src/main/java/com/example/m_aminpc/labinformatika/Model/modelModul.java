package com.example.m_aminpc.labinformatika.Model;

public class modelModul {
    public Integer id_modul, id_lab;
    public String nama_modul, gambar_modul, file_modul;

    public modelModul( Integer id_modul, Integer id_lab, String nama_modul, String gambar_modul, String file_modul) {
        this.id_modul= id_modul;
        this.id_lab= id_lab;
        this.nama_modul= nama_modul;
        this.gambar_modul= gambar_modul;
        this.file_modul= file_modul;
    }
}
