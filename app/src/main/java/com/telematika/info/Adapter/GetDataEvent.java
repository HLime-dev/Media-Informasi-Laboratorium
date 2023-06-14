package com.telematika.info.Adapter;

public class GetDataEvent {

    String nama="", deskripsi="", tanggal="", id="";

    public GetDataEvent(String id, String nama, String deskripsi, String tanggal){
        this.id=id;
        this.nama=nama;
        this.deskripsi=deskripsi;
        this.tanggal=tanggal;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getId() {
        return id;
    }
}
