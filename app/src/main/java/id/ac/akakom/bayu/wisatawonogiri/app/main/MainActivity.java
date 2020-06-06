package id.ac.akakom.bayu.wisatawonogiri.app.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.app.detail.DetailWisataActivity;
import id.ac.akakom.bayu.wisatawonogiri.app.main.adapter.WisataAdapter;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Kategori;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Wisata;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.WisataMarker;
import id.ac.akakom.bayu.wisatawonogiri.dialogs.kategori.KategoriDialog;
import id.ac.akakom.bayu.wisatawonogiri.module.WisataActivity;
import id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap.Cluster;
import id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap.ClusterManager;
import id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap.DefaultIconGenerator;
import id.ac.akakom.bayu.wisatawonogiri.utils.ClusteringMap.IconGenerator;
import id.ac.akakom.bayu.wisatawonogiri.utils.Haversine;
import id.ac.akakom.bayu.wisatawonogiri.utils.UtilWisata;
import lib.alframeworkx.Activity.Interfaces.PermissionResultInterface;
import lib.alframeworkx.utils.AlStatic;

public class MainActivity extends WisataActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, MainView.View {

    @BindView(R.id.img_close)
    ImageView img_close;
    @BindView(R.id.edt_search)
    EditText edt_search;
    @BindView(R.id.card_filter)
    CardView card_filter;
    @BindView(R.id.pb_kategori)
    ProgressBar pb_kategori;
    @BindView(R.id.img_kategori)
    ImageView img_kategori;
    @BindView(R.id.card_myloc)
    CardView card_myloc;
    @BindView(R.id.rv)
    RecyclerView rv;
    SupportMapFragment map;

    LocationRequest locationRequest;
    private static final long UPDATE_INTERVAL = 5000, FASTEST_INTERVAL = 5000; // = 5 seconds

    WisataAdapter wisataAdapter;
    List<Wisata> list;
    List<WisataMarker> list_marker;
    List<Kategori> kategoris = new ArrayList<>();

    Boolean isKategoriDone = false;
    Boolean isFirst = true;
    String msgKategoriDone = "";

    MainPresenter presenter;

    MainParam mainParam = new MainParam();

    private GoogleMap googleMap;
    LatLng position, myltlng;

    GoogleApiClient googleApiClient;
    private ClusterManager<WisataMarker> mClusterManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        list_marker = new ArrayList<>();
        presenter = new MainPresenter(getContext(), this);

        askCompactPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionResultInterface(){
            @Override
            public void permissionGranted() {
                map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                map.getMapAsync(MainActivity.this);
            }

            @Override
            public void permissionDenied() {
                AlStatic.ToastShort(getContext(), "Anda harus memberikan akses lokasi terlebih dahulu");
                finish();
            }
        });

        wisataAdapter = new WisataAdapter(getContext(), list, new WisataAdapter.OnWisataClick() {
            @Override
            public void onClickedWisata(Wisata wisata) {
                Intent i = new Intent(getContext(), DetailWisataActivity.class);
                i.putExtra("wisata", wisata);
                i.putExtra("direction", "http://maps.google.com/maps?saddr="+myltlng.latitude+","+myltlng.longitude+"&daddr="+wisata.getLat()+","+wisata.getLng());
                startActivity(i);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new LinearSnapHelper(){
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                int snapPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                Log.d("findme", "findTargetSnapPosition: "+snapPosition);
                if(snapPosition != -1)
                    onFocusMap(Double.valueOf(list.get(snapPosition).getLat()), Double.valueOf(list.get(snapPosition).getLng() ), 0);
                return snapPosition;
            }
        };
        snapHelper.attachToRecyclerView(rv);
        rv.setAdapter(wisataAdapter);

        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    if(!edt_search.getText().toString().equalsIgnoreCase("")){
//                    }
                    mainParam.setKeyword(edt_search.getText().toString());
                    UtilWisata.hideSoftKeyboard(getContext(), edt_search);
                    presenter.requestWisata(mainParam);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
//        location.setLatitude(location.getLatitude());
//        location.setLongitude(location.getLongitude());

        myltlng = new LatLng(location.getLatitude(), location.getLongitude());
        onFocusMap(myltlng.latitude, myltlng.longitude, 8);
        if(isFirst){
            isFirst = false;
            mainParam.setLatitude(String.valueOf(myltlng.latitude));
            mainParam.setLongitude(String.valueOf(myltlng.longitude));
            presenter.requestWisata(mainParam);
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlStatic.ToastShort(getContext(), "Anda perlu memberi izin fitur GPS terlebih dahulu");
            return;
        }
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleApiClient = new GoogleApiClient.Builder(getContext()).addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();
        googleApiClient.connect();
        mClusterManager = new ClusterManager<WisataMarker>(getContext(), googleMap);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        mClusterManager.setCallbacks(new ClusterManager.Callbacks<WisataMarker>() {

            @Override
            public boolean onClusterClick(@NonNull Cluster<WisataMarker> cluster) {
                onClusterClicking(cluster);
                return true;
            }

            @Override
            public boolean onClusterItemClick(@NonNull WisataMarker clusterItem) {
                onFocusMap(clusterItem.getLatitude(), clusterItem.getLongitude(), 0);
                for (int i = 0; i < list.size(); i++) {
                    if(clusterItem.getWisata().getId_wisata() == list.get(i).getId_wisata()){
                        rv.smoothScrollToPosition(i);
                    }
                }
                return true;
            }
        });
        final DefaultIconGenerator d = new DefaultIconGenerator(getContext());
        IconGenerator<WisataMarker> markerClusterIconGenerator = new IconGenerator<WisataMarker>() {
            @NonNull
            @Override
            public BitmapDescriptor getClusterIcon(@NonNull Cluster<WisataMarker> cluster) {
                return d.getClusterIcon(cluster);
            }

            @NonNull
            @Override
            public BitmapDescriptor getClusterItemIcon(@NonNull WisataMarker clusterItem) {
                return clusterItem.getIcon();
            }
        };
        mClusterManager.setIconGenerator(markerClusterIconGenerator);
    }

    @OnClick({ R.id.card_myloc, R.id.card_filter, R.id.img_close})
    public void setViewOnClickEvent(View view) {
        switch (view.getId()){
            case R.id.card_filter :
                if(isKategoriDone){
                    new KategoriDialog(getContext(), kategoris, new KategoriDialog.onKategoriDialog() {
                        @Override
                        public void onClick(Kategori kategori) {
                            mainParam.setId_kategori(kategori.getId_kategori());
                            presenter.requestWisata(mainParam);
                            Picasso.with(getContext())
                                    .load(kategori.getFoto_kategori())
                                    .error(R.drawable.ic_place)
                                    .into(img_kategori);

                        }
                    });
                } else {
                    if(!msgKategoriDone.equalsIgnoreCase("")){
                        AlStatic.ToastShort(getContext(), msgKategoriDone);
                    }
                }
                break;
            case R.id.card_myloc :
                if(myltlng!=null){
                    onFocusMap(myltlng.latitude, myltlng.longitude, 10);
                }
                break;
            case R.id.img_close :
                edt_search.setText("");
                img_close.setVisibility(View.GONE);
                break;

        }
    }

    private void onClusterClicking(Cluster<WisataMarker> cluster){
        if(isZoomable(cluster.getItems())){
            try {
                LatLng ltlng = new LatLng(cluster.getLatitude(), cluster.getLongitude());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ltlng, (float) Math.floor(googleMap.getCameraPosition().zoom + 1)), 300, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    @Override
    public void onLoading() {
        AlStatic.showLoadingDialog(getContext(), R.drawable.ic_logo);
    }

    @Override
    public void onHideLoading() {
        AlStatic.hideLoadingDialog(getContext());
    }

    @Override
    public void onErrorConnection() {

    }

    @Override
    public void onrequestWisata(boolean isSuccess, List<Wisata> wisatas, String message) {
        if(isSuccess){
            list.clear();
            list.addAll(wisatas);
            wisataAdapter.notifyDataSetChanged();

            list_marker.clear();
            presenter.addMarker(new Wisata("Lokasi", "Saya", ""+myltlng.latitude, ""+myltlng.longitude), "user");
            presenter.addMarkers(list);
        } else {
            AlStatic.ToastShort(getContext(), message);
        }
    }

    @Override
    public void onRequestKategori(boolean isSuccess, List<Kategori> kategoris) {
        pb_kategori.setVisibility(View.GONE);
        img_kategori.setVisibility(View.VISIBLE);
        if(isSuccess){
            this.kategoris.clear();
            this.kategoris.add(new Kategori("", "Semua Kategori", "google.com"));
            this.kategoris.addAll(kategoris);
            isKategoriDone = true;
//            img_kategori.setImageResource(R.drawable.ic_tool);
        } else {
            msgKategoriDone = "Kategori gagal diambil";
//            img_kategori.setImageResource(R.drawable.ic_close_bullet);
        }
    }

    @Override
    public void onAddMarker(WisataMarker wisata) {
        list_marker.add(wisata);
        onUpdateMap();
    }

    @Override
    public void onAddMarkers(List<WisataMarker> wisatas) {
        list_marker.addAll(wisatas);
        onUpdateMap();
    }

    private void onFocusMap(double lat, double lng, int x){
        position = new LatLng(lat, lng);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        if(x != 0){
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(x));
        }
    }

    private void startLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(UPDATE_INTERVAL);
//        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    public static boolean isZoomable(List<WisataMarker> markerClusters){
        Log.d("markerCLuster", "isZoomable: "+markerClusters.size());
        for (WisataMarker m : markerClusters){
            for (WisataMarker n : markerClusters){
                if(Haversine.distance(m.getLatitude(), m.getLongitude(), n.getLatitude(), n.getLongitude()) > 0.01){
                    return true;
                }
            }
        }
        return false;
    }

    private void onUpdateMap(){
        mClusterManager.setItems(list_marker);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Log.d("TAG", "onUpdateMap: "+list_marker.size());
        for (WisataMarker marker : list_marker) {
            Log.d("TAG", "onUpdateMap: "+marker.getLatitude()+" "+marker.getLongitude());
            builder.include(new LatLng(marker.getLatitude(), marker.getLongitude()));
        }
        LatLngBounds bounds = builder.build();
        int padding = 0; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        try {
            googleMap.animateCamera(cu);
        } catch (Exception e){

        }
//        map.animateCamera(CameraUpdateFactory.newLatLngZoom(myltlng, (float) Math.floor(map.getCameraPosition().zoom + 1)), 200, null);
    }
}
