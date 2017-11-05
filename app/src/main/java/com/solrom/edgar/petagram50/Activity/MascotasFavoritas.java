package com.solrom.edgar.petagram50.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram50.adapter.MascotaFavoritaAdapter;
import com.solrom.edgar.petagram50.bd.ConstructorMascotas;
import com.solrom.edgar.petagram50.pojo.Mascota;
import com.solrom.edgar.petagram50.restAPI.EndpointsApi;
import com.solrom.edgar.petagram50.restAPI.adapter.RestApiAdapter;
import com.solrom.edgar.petagram50.restAPI.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edgarsr on 26/10/17.
 */

public class MascotasFavoritas extends AppCompatActivity {

    public ArrayList<Mascota> mascotas;
    public RecyclerView listaMascotasDummy;
    public MascotaFavoritaAdapter adapter;
    public Integer likesRecibidos = 0;
    public TextView tvLikesDummy;
    public TextView tvNombreMascotaDummy;
    public ConstructorMascotas constructorMascotas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_favorita);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotasDummy = (RecyclerView)findViewById(R.id.rvMascota2);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotasDummy.setLayoutManager(llm);

        mascotas = new ArrayList<Mascota>();

        //obtenerMascotasBaseDatos();
        //iniciaAdapterMascotas();
        obtenerMediosRecientes();

    }




    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(getApplicationContext());
        mascotas = constructorMascotas.obtenerMascotasFavoritas();
    }



    private void iniciaAdapterMascotas()
    {
        //adapter = new MascotaFavoritaAdapter(mascotas,this);
        listaMascotasDummy.setAdapter(adapter);
    }

    public void obtenerMediosRecientes(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();

                mostrarMascotas();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(MascotasFavoritas.this, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("Favoritos", "Falló la conexión" + t.toString());
            }
        });
    }

    public void mostrarMascotas(){
        ArrayList<Mascota> mascotas2 = new ArrayList<Mascota>(mascotas.subList(0, 5));
        iniciaAdapterMascotas(crearAdaptador(mascotas2));
    }

    public void iniciaAdapterMascotas(MascotaAdaptador adaptador){
        listaMascotasDummy.setAdapter(adaptador);
    }

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, MascotasFavoritas.this);
        return adaptador;
    }
}
