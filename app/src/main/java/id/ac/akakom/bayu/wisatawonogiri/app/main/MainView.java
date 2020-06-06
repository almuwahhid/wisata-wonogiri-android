package id.ac.akakom.bayu.wisatawonogiri.app.main;

import com.google.android.gms.maps.model.Marker;

import java.util.List;

import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Kategori;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Wisata;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.WisataMarker;
import lib.alframeworkx.base.BaseView;

public interface MainView {
    interface View extends BaseView{
        void onrequestWisata(boolean isSuccess, List<Wisata> wisatas, String message);
        void onRequestKategori(boolean isSuccess, List<Kategori> kategoris);
        void onAddMarker(WisataMarker wisata);
        void onAddMarkers(List<WisataMarker> wisatas);
    }

    interface Presenter {
        void requestWisata(MainParam param);
        void requestKategori();
        void addMarker(Wisata wisata, String type);
        void addMarkers(List<Wisata> wisatas);
    }

}
