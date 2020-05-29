package com.example.tugasdatabasesqlite;

public class Mahasiswa {
    private  String nama,stb,alamat;
    private int angkatan;

    public Mahasiswa(String nama, String stb, int angkatan, String alamat) {
        this.nama = nama;
        this.stb = stb;
        this.alamat = alamat;
        this.angkatan = angkatan;
    }

    public String getNama() {
        return nama;
    }

    public String getStb() {
        return stb;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getAngkatan() {
        return angkatan;
    }
}
