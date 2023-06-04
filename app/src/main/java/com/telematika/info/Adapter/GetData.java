package com.telematika.info.Adapter;

public class GetData {
    String nama="", nip="", jabatan="", email="", penelitian="", foto="", id="";

    public GetData (String id, String nama, String jabatan, String email){
        this.id=id;
        this.nama=nama;
        this.jabatan=jabatan;
        this.email=email;
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPenelitian() {
        return penelitian;
    }

    public void setPenelitian(String penelitian) {
        this.penelitian = penelitian;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}
