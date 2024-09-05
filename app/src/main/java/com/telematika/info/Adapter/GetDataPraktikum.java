package com.telematika.info.Adapter;

public class GetDataPraktikum {
    String id, name, tanggal, tempat, image;

    public GetDataPraktikum(){

    }

    public GetDataPraktikum (String id, String name, String tanggal, String tempat, String image) {
        this.id = id;
        this.name=name;
        this.tanggal=tanggal;
        this.tempat=tempat;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getTempat() {
        return tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getId() {
        return id;
    }

    public String getImage() { return image; }

}
