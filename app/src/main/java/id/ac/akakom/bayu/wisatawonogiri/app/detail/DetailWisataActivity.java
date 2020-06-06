package id.ac.akakom.bayu.wisatawonogiri.app.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.FotoWisata;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Wisata;
import id.ac.akakom.bayu.wisatawonogiri.module.WisataActivity;
import id.ac.akakom.bayu.wisatawonogiri.utils.slider.SliderLayout;
import id.ac.akakom.bayu.wisatawonogiri.utils.slider.SliderTypes.ImageSliderView;

public class DetailWisataActivity extends WisataActivity {

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_kategori)
    ImageView img_kategori;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_kategori)
    TextView tv_kategori;
    @BindView(R.id.tv_jarak)
    TextView tv_jarak;
    @BindView(R.id.tv_wisata)
    TextView tv_wisata;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.slider)
    SliderLayout slider;

    Wisata wisatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);
        ButterKnife.bind(this);

        wisatas = (Wisata) getIntent().getSerializableExtra("wisata");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(wisatas.getNama_wisata());

        Picasso.with(getContext())
                .load(wisatas.getFoto_kategori())
                .error(R.drawable.ic_place)
                .into(img_kategori);

        tv_jarak.setText(wisatas.getJarak()+" km");
        tv_wisata.setText(wisatas.getNama_wisata());
        tv_kategori.setText(wisatas.getNama_kategori());
        webview.loadData("<div style='font-size:13px'>"+wisatas.getDeskripsi()+"</div>", "text/html", "utf-8");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("direction"))));
            }
        });

        for (FotoWisata foto : wisatas.getPhotos()) {
            slider.addSlider(new ImageSliderView(getContext(), foto.getUrl_foto()));
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setDuration(3000);

    }
}
