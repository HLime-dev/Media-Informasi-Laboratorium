package com.telematika.info.Adapter;

public class GetDataRuangan {

    String nama, image, id;

    public GetDataRuangan(String id, String nama, String image){
        this.id=id;
        this.nama=nama;
        this.image=image;
    }

    public String getNama() {
        return nama;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }


}
