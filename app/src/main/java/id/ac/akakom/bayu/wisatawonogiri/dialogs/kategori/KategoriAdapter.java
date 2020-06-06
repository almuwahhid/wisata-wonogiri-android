package id.ac.akakom.bayu.wisatawonogiri.dialogs.kategori;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Kategori;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {

    Context context;
    List<Kategori> list;
    OnKategoriClick onKategoriClick;

    public KategoriAdapter(Context context, List<Kategori> list, OnKategoriClick onKategoriClick) {
        this.context = context;
        this.list = list;
        this.onKategoriClick = onKategoriClick;
    }

    @NonNull
    @Override
    public KategoriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_kategori, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriAdapter.ViewHolder holder, int position) {
        final Kategori kategori = list.get(position);

        Picasso.with(context)
                .load(kategori.getFoto_kategori())
                .error(R.drawable.ic_place)
                .into(holder.img_kategori);

        holder.tv_kategori.setText(kategori.getNama_kategori());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onKategoriClick.onClick(kategori);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_kategori)
        TextView tv_kategori;
        @BindView(R.id.img_kategori)
        ImageView img_kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface OnKategoriClick{
        void onClick(Kategori kategori);
    }
}
