package com.telematika.info.Adapter;

public class GetDataRuangan {

    String nama, foto, id;

    public GetDataRuangan(String id, String nama, String foto){
        this.id=id;
        this.nama=nama;
        this.foto=foto;
    }

    public String getNama() {
        return nama;
    }

    public String getFoto() {
        return foto;
    }

    public String getId() {
        return id;
    }


}
