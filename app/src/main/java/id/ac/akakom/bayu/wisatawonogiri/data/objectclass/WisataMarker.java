package id.ac.akakom.bayu.wisatawonogiri.data.objectclass;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap.ClusterItem;
import id.ac.akakom.bayu.wisatawonogiri.utils.UtilWisata;

public class WisataMarker implements ClusterItem {
    private BitmapDescriptor icon;
    Context context;
    Wisata wisata;
    String type;

    public void setWisata(Wisata wisata) {
        this.wisata = wisata;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WisataMarker(Context context, Wisata wisata, String type) {
        this.context = context;
        this.wisata = wisata;
        this.type = type;
    }

    public Wisata getWisata() {
        return wisata;
    }

        @Override
    public double getLatitude() {
        try {
            return Double.valueOf(wisata.getLat());
        } catch (Exception e){
            return 0;
        }
    }

    public Context getContext() {
        return context;
    }

    @Override
    public double getLongitude() {
        try {
            return Double.valueOf(wisata.getLng());
        } catch (Exception e){
            return 0;
        }
    }
    @Nullable
    @Override
    public String getTitle() {
        return wisata.getNama_wisata();
    }

    @Nullable
    @Override
    public String getSnippet() {
        return wisata.getNama_wisata()+" - "+wisata.getNama_kategori();
    }

    public BitmapDescriptor getIcon() {
        switch (type){
            case "user" :
                icon = BitmapDescriptorFactory.fromBitmap(UtilWisata.getBitmap(R.drawable.marker_user, ((Activity) getContext())));
                break;
            default:
//                icon = BitmapDescriptorFactory.fromBitmap(UtilWisata.getBitmapFromURL("http://tutorial-sourcecode.com/bayu/datas/kategori/"+wisata.getFoto_kategori()));
                icon = BitmapDescriptorFactory.fromBitmap(wisata.getBitmapPhoto(getContext()));
                break;
        }
        return icon;
    }
}
