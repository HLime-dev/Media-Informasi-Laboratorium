package com.telematika.info.Adapter;

public class GetDataPengunjung {

    String id="", nama="", identitas="", tanggal="";
    public GetDataPengunjung(String id, String nama, String identitas, String tanggal){
        this.id=id;
        this.nama=nama;
        this.identitas=identitas;
        this.tanggal=tanggal;
    }
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getIdentitas() {
        return identitas;
    }

    public String getTanggal() {
        return tanggal;
    }


}
