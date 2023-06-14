package com.telematika.info.Adapter;

public class GetDataMahasiswa {

    String nama="", nim="", email="", id="";

    public GetDataMahasiswa(String id, String nama, String nim, String email){
        this.id=id;
        this.nama=nama;
        this.nim=nim;
        this.email=email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
