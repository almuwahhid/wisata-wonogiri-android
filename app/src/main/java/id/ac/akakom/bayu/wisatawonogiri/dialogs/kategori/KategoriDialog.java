package id.ac.akakom.bayu.wisatawonogiri.dialogs.kategori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.data.objectclass.Kategori;
import lib.alframeworkx.utils.DialogBuilder;

public class KategoriDialog extends DialogBuilder {

    @BindView(R.id.lay_dialog)
    RelativeLayout lay_dialog;
    @BindView(R.id.rv)
    RecyclerView rv;
    KategoriAdapter adapter;
    List<Kategori> list;
    onKategoriDialog onKategoriDialog;

    public KategoriDialog(Context context, List<Kategori> kategoris, final onKategoriDialog onKategoriDialog) {
        super(context, R.layout.dialog_kategori);
        ButterKnife.bind(this, getDialog());
        this.list = kategoris;
        this.onKategoriDialog = onKategoriDialog;

        setAnimation(R.style.DialogTopAnimation);
        setFullWidth(lay_dialog);
        setGravity(Gravity.TOP);

        adapter = new KategoriAdapter(getContext(), list, new KategoriAdapter.OnKategoriClick() {
            @Override
            public void onClick(Kategori kategori) {
                onKategoriDialog.onClick(kategori);
                dismiss();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        show();
    }

    public interface onKategoriDialog{
        void onClick(Kategori kategori);
    }
}
