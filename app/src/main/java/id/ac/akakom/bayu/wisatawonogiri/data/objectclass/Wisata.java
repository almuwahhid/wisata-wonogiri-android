package id.ac.akakom.bayu.wisatawonogiri.data.objectclass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap.ClusterItem;
import id.ac.akakom.bayu.wisatawonogiri.utils.UtilWisata;

public class Wisata implements Serializable {
    String id_wisata, nama_wisata, deskripsi, id_kategori, keyword, nama_kategori, foto_kategori, lat, lng, jarak;
    List<FotoWisata> photos;
    public transient Bitmap bitmapPhoto;

    public Wisata(String nama_wisata, String nama_kategori, String lat, String lng) {
        this.nama_wisata = nama_wisata;
        this.nama_kategori = nama_kategori;
        this.lat = lat;
        this.lng = lng;
    }

    public Bitmap getBitmapPhoto(Context context) {
        if(bitmapPhoto == null){
            return UtilWisata.getBitmap(R.drawable.ic_place, ((Activity) context));
        }
        return bitmapPhoto;
    }

    public void setBitmapPhoto(Bitmap bitmapPhoto) {
        this.bitmapPhoto = bitmapPhoto;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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

    public List<FotoWisata> getPhotos() {
        return photos;
    }

    public void setPhotos(List<FotoWisata> photos) {
        this.photos = photos;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setId_wisata(String id_wisata) {
        this.id_wisata = id_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getId_wisata() {
        return id_wisata;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getId_kategori() {
        return id_kategori;
    }

//    @Override
//    public double getLatitude() {
//        try {
//            return Double.valueOf(lat);
//        } catch (Exception e){
//            return 0;
//        }
//    }
//
//    @Override
//    public double getLongitude() {
//        try {
//            return Double.valueOf(lng);
//        } catch (Exception e){
//            return 0;
//        }
//    }

//    @Nullable
//    @Override
//    public String getTitle() {
//        return nama_wisata;
//    }
//
//    @Nullable
//    @Override
//    public String getSnippet() {
//        return deskripsi;
//    }
}
