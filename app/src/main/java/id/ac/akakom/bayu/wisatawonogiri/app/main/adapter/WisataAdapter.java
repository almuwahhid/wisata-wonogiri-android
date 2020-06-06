package id.ac.akakom.bayu.wisatawonogiri.app.main.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Wisata;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> {

    Context context;
    List<Wisata> list;
    OnWisataClick onWisataClick;

    public WisataAdapter(Context context, List<Wisata> list, OnWisataClick onWisataClick) {
        this.context = context;
        this.list = list;
        this.onWisataClick = onWisataClick;
    }

    @NonNull
    @Override
    public WisataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wisata, parent, false);
        WisataAdapter.ViewHolder rcv = new WisataAdapter.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull WisataAdapter.ViewHolder holder, int position) {
        Wisata wisata = list.get(position);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int width25 = displayMetrics.widthPixels*20/100;
        Log.d(".widthP", "onBindViewHolder: "+displayMetrics.widthPixels);
        Log.d(".widthP", "onBindViewHolder: "+displayMetrics.widthPixels*25/100);
        holder.lay_adapterwisata.setLayoutParams(new FrameLayout.LayoutParams(width-width25, LinearLayout.LayoutParams.WRAP_CONTENT));

        holder.card_wisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWisataClick.onClickedWisata(wisata);
            }
        });


        Picasso.with(context)
                .load(wisata.getFoto_kategori())
                .error(R.drawable.ic_tool)
                .into(holder.img_kategori);

        Picasso.with(context)
                .load((list.get(position).getPhotos().size() > 0 ? list.get(position).getPhotos().get(0).getUrl_foto() : wisata.getFoto_kategori()))
                .error(R.drawable.default_bg)
                .placeholder(R.drawable.default_bg)
                .into(holder.img_gallery);
//        holder.tv_deskripsi.setText(wisata.getDeskripsi());

        holder.webview.loadData("<div style='font-size:13px'>"+wisata.getDeskripsi()+"</div>", "text/html", "utf-8");
        holder.webview.setVerticalScrollBarEnabled(false);
        holder.webview.setHorizontalScrollBarEnabled(false);

        holder.tv_wisata.setText(wisata.getNama_wisata());
        holder.tv_jarak.setText(wisata.getJarak()+" km");
        holder.tv_kategori.setText(wisata.getNama_kategori());
    }

    public interface OnWisataClick{
        void onClickedWisata(Wisata wisata);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_wisata)
        CardView card_wisata;
        @BindView(R.id.lay_adapterwisata)
        LinearLayout lay_adapterwisata;
        @BindView(R.id.tv_kategori)
        TextView tv_kategori;
        @BindView(R.id.img_kategori)
        ImageView img_kategori;
        @BindView(R.id.img_gallery)
        ImageView img_gallery;
        @BindView(R.id.webview)
        WebView webview;
        @BindView(R.id.tv_wisata)
        TextView tv_wisata;
        @BindView(R.id.tv_jarak)
        TextView tv_jarak;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
