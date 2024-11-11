package com.telematika.info.Adapter;

public class GetDataUmum {

    String nama, info1, info2, info3, foto, id;

    public GetDataUmum(String id, String nama, String info1, String info2, String info3,String foto){
        this.id=id;
        this.nama=nama;
        this.info1=info1;
        this.info2=info2;
        this.info3=info3;
        this.foto=foto;
    }

    public String getNama() {
        return nama;
    }
    public String getInfo1() {
        return info1;
    }
    public String getInfo2() {
        return info2;
    }
    public String getInfo3() {
        return info3;
    }

    public String getFoto() {
        return foto;
    }

    public String getId() {
        return id;
    }


}
