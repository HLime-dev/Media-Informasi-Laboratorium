package com.telematika.info.Adapter;

public class GetDataEvent {

    String nama="", lokasi="", tanggal="", id="";

    public GetDataEvent(String id, String nama, String lokasi,String tanggal){
        this.id=id;
        this.nama=nama;
        this.lokasi=lokasi;
        this.tanggal=tanggal;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getId() {
        return id;
    }
}
