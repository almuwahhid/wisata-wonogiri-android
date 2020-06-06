package id.ac.akakom.bayu.wisatawonogiri.data.objectclass;

import java.io.Serializable;

public class FotoWisata implements Serializable {
    String id_foto_wisata, id_wisata, url_foto;

    public String getId_foto_wisata() {
        return id_foto_wisata;
    }

    public String getId_wisata() {
        return id_wisata;
    }

    public String getUrl_foto() {
        return "http://tutorial-sourcecode.com/bayu/datas/wisata/"+url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }
}
