package com.solrom.edgar.petagram50.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.solrom.edgar.petagram50.Activity.DetalleMascota;
import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.pojo.Mascota;
import com.solrom.edgar.petagram50.restAPI.EndpointsApi;
import com.solrom.edgar.petagram50.restAPI.adapter.RestApiAdapter;
import com.solrom.edgar.petagram50.restAPI.model.LikeResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edgarsr on 26/10/17.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    private ArrayList<Mascota> mascotas;

    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas , Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_mascota , parent , false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.schnauzer_blackandpepper)
                .into(holder.imgFoto);

        //holder.tvLikes.setText(mascota.getLikes().toString());

        final Integer numeroLikes = mascota.getLikes();

        holder.tvLikes.setText(numeroLikes.toString());

        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, mascota.getNombreMascota() + "  !!!", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(activity , DetalleMascota.class);
                intent.putExtra("url" , mascota.getUrlFoto());
                intent.putExtra("like" , mascota.getLikes());
                activity.startActivity(intent);*/
                likePic(mascota.getId(), mascota.getUrlFoto(), mascota.getNombreCompleto(), mascota.getLikes());

                Integer nuevoLike = numeroLikes + 1;
                holder.tvLikes.setText(nuevoLike.toString());

            }
        });

    }

    public void likePic(String idFoto, String urlFoto, String nombreCompleto, int numeroDeLikes){
        Log.d("LIKE", "true");

        final Mascota mascota = new Mascota(idFoto, urlFoto, nombreCompleto, numeroDeLikes);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagramSinDes();
        Call<ResponseBody> responseBody = endpointsApi.darLike(mascota.getId());

        Log.d("idfoto", mascota.getId());

        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("response", "postJSONRequest response.code :" + response.code());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("like error", t.toString());
            }
        });

        String idDispositivo = FirebaseInstanceId.getInstance().getToken();
        this.enviarRegistrolike(idDispositivo, idFoto, "user_id");
    }

    private void enviarRegistrolike(String idDispositivo, String idFoto, String idUsuario){
        Log.d("LIKE", idDispositivo);
        Log.d("LIKE", idFoto);
        Log.d("LIKE", idUsuario);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApi();
        Call<LikeResponse> usuarioResponseCall = endpoints.registrarLike(idDispositivo, idUsuario, idFoto);

        usuarioResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
                Log.d("id_firebase", likeResponse.getId());
                Log.d("usuario_firebase", likeResponse.getIdDispositivo());
                Log.d("usuario_instagram", likeResponse.getIdUsuarioInstagram());
                Log.d("id_foto", likeResponse.getIdFoto());

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });

        this.enviarNotificacion("-KvQez7samAkvTFS1Kkk", "evi");

    }

    private void enviarNotificacion(String idDispositivo, String nombreUsuario){
        Log.d("LIKE", idDispositivo);
        Log.d("LIKE", nombreUsuario);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApi();
        Call<ResponseBody> enviarNotificacionCall = endpoints.enviarNotificacion(idDispositivo, nombreUsuario);

        enviarNotificacionCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("notificacion enviada", "notificacion correcta");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imgFoto;
        private TextView tvLikes;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikesMascota);
        }
    }


}
