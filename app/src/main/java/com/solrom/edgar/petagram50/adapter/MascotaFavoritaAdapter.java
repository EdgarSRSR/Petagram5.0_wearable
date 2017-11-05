package com.solrom.edgar.petagram50.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.solrom.edgar.petagram50.Activity.DetalleMascota;
import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by edgarsr on 26/10/17.
 */

public class MascotaFavoritaAdapter extends RecyclerView.Adapter<MascotaFavoritaAdapter.MascotaFavoritaViewHolder>{

    private ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaFavoritaAdapter(ArrayList<Mascota> mascotas , Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaFavoritaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_favorita , parent , false);
        return new MascotaFavoritaViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(final MascotaFavoritaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.shibainu)
                .into(holder.imgFotoDummy);
        holder.tvLikesDummy.setText(mascota.getLikes().toString());


    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaFavoritaViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imgFotoDummy;
        private TextView tvNombreMascotaDummy;
        private TextView tvLikesDummy;
        private ImageButton btnLikesDummy;
        private ImageButton btnFavoritosDummy;


        public MascotaFavoritaViewHolder(View itemView) {
            super(itemView);
            imgFotoDummy = (ImageView) itemView.findViewById(R.id.imgFotoDummy);
            //tvNombreMascotaDummy = (TextView) itemView.findViewById(R.id.tvNombreMascotaDummy);
            tvLikesDummy = (TextView) itemView.findViewById(R.id.tvLikesMascotaDummy);
            //btnLikesDummy = (ImageButton)itemView.findViewById(R.id.btnLikesDummy);
            //btnFavoritosDummy = (ImageButton)itemView.findViewById(R.id.btnFavoritos);
        }
    }


}