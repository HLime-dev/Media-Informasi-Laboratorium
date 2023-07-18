package com.telematika.info.Adapter;

public class GetDataAlat {

    private String id, name, kategori, jumlah, image;

    public GetDataAlat() {

    }

    public GetDataAlat(String id, String name, String kategori, String jumlah, String image){
        this.id=id;
        this.name=name;
        this.kategori=kategori;
        this.jumlah=jumlah;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getKategori() {
        return kategori;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
