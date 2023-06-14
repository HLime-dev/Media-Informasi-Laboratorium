package com.telematika.info.Adapter;

public class GetDataAlat {

    String nama="", deskripsi="", jumlah="", id="";

    public GetDataAlat(String id, String nama, String deskripsi, String jumlah){
        this.id=id;
        this.nama=nama;
        this.deskripsi=deskripsi;
        this.jumlah=jumlah;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getId() {
        return id;
    }


}
