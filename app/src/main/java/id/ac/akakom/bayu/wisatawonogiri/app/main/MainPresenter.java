package id.ac.akakom.bayu.wisatawonogiri.app.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Kategori;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Wisata;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.WisataMarker;
import id.ac.akakom.bayu.wisatawonogiri.utils.UtilWisata;
import lib.alframeworkx.base.BasePresenter;
import lib.alframeworkx.utils.AlRequest;
import lib.alframeworkx.utils.AlStatic;

public class MainPresenter extends BasePresenter implements MainView.Presenter {
    MainView.View view;
    List<Wisata> wisatas;
    public MainPresenter(Context context, MainView.View view) {
        super(context);
        this.view = view;
        wisatas = new ArrayList<>();
    }

    @Override
    public void requestWisata(MainParam param) {
        AlRequest.POST("http://tutorial-sourcecode.com/bayu/api/wisata", getContext(), new AlRequest.OnPostRequest() {
            @Override
            public void onPreExecuted() {
                view.onLoading();
            }

            @Override
            public void onSuccess(JSONObject response) {
                try {
                    if(response.getInt("status") == 200){
                        JSONObject data = response.getJSONObject("data");
                        JSONArray wisata = data.getJSONArray("wisata");
                        JSONArray kategori = data.getJSONArray("kategori");

                        List<Kategori> kategoris = new ArrayList<>();
                        wisatas = new ArrayList<>();

                        for (int i = 0; i < kategori.length(); i++) {
                            kategoris.add(getGson().fromJson(kategori.get(i).toString(), Kategori.class));
                        }
                        view.onRequestKategori(true, kategoris);

                        for (int i = 0; i < wisata.length(); i++) {
                            wisatas.add(getGson().fromJson(wisata.get(i).toString(), Wisata.class));
                        }
                        getBitmapWisata(0, response.getString("message"));
                    }else {
                        view.onHideLoading();
//                        view.onRequestKategori(false, new ArrayList<>());
                        view.onrequestWisata(false, new ArrayList<>(), response.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.onHideLoading();
                    view.onrequestWisata(false, new ArrayList<>(), "Bermasalah dengan Server");
                }
            }

            @Override
            public void onFailure(String error) {
                view.onHideLoading();
            }

            @Override
            public Map<String, String> requestParam() {
                Map<String, String> params = new HashMap<>();
                params.put("lat", ""+param.getLatitude());
                params.put("lng", ""+param.getLongitude());
                params.put("keyword", ""+param.getKeyword());
                params.put("id_kategori", ""+param.getId_kategori());
                return params;
            }

            @Override
            public Map<String, String> requestHeaders() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        });
//        view.onrequestWisata(true, new ArrayList<Wisata>(), "Suksess");
    }

    @Override
    public void requestKategori() {
        view.onRequestKategori(true, new ArrayList<Kategori>());
    }

    @Override
    public void addMarker(Wisata wisata, String type) {
        view.onAddMarker(new WisataMarker(getContext(), wisata, type));
    }

    @Override
    public void addMarkers(List<Wisata> wisatas) {
        List<WisataMarker> wisataMarkerList = new ArrayList<>();
        for(Wisata wisata : wisatas){
            wisataMarkerList.add(new WisataMarker(getContext(), wisata, "wisata"));
        }
        view.onAddMarkers(wisataMarkerList);
    }

    private static class BitmapFromURLTask extends AsyncTask<String, Void, Bitmap> {
        BitmapFromURLTask.OnBitmapOk onBitmapOK;

        public BitmapFromURLTask(OnBitmapOk onBitmapOK) {
            this.onBitmapOK = onBitmapOK;
        }

        @Override
        protected Bitmap doInBackground(String[] params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        interface OnBitmapOk{
            void onOk(Bitmap bitmap);
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            onBitmapOK.onOk(bmp);
        }
    }

    private void getBitmapWisata(int position, String message){
        if(position > wisatas.size()-1){
            view.onHideLoading();
            view.onrequestWisata(true, wisatas, message);
        } else {
            new BitmapFromURLTask(new BitmapFromURLTask.OnBitmapOk() {
                @Override
                public void onOk(Bitmap bitmap) {
                    wisatas.get(position).setBitmapPhoto(bitmap);
                    getBitmapWisata(position+1, message);
                }
            }).execute(wisatas.get(position).getFoto_kategori());
        }
    }
}
