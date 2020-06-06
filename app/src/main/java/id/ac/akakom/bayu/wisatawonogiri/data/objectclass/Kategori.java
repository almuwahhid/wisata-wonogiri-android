package id.ac.akakom.bayu.wisatawonogiri.data.objectclass;

public class Kategori {
    String id_kategori, nama_kategori, foto_kategori;

    public Kategori(String id_kategori, String nama_kategori, String foto_kategori) {
        this.id_kategori = id_kategori;
        this.nama_kategori = nama_kategori;
        this.foto_kategori = foto_kategori;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public String getFoto_kategori() {
        return "http://tutorial-sourcecode.com/bayu/datas/kategori/"+foto_kategori;
    }

    public void setFoto_kategori(String foto_kategori) {
        this.foto_kategori = foto_kategori;
    }
}
