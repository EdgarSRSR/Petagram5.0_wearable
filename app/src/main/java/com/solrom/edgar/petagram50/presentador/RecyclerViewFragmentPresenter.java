package com.solrom.edgar.petagram50.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.solrom.edgar.petagram50.bd.ConstructorMascotas;
import com.solrom.edgar.petagram50.fragment.IRecyclerViewFragmentView;
import com.solrom.edgar.petagram50.pojo.Mascota;
import com.solrom.edgar.petagram50.restAPI.EndpointsApi;
import com.solrom.edgar.petagram50.restAPI.adapter.RestApiAdapter;
import com.solrom.edgar.petagram50.restAPI.model.MascotaResponse;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edgarsr on 26/10/17.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascota;
    private ArrayList<Mascota> mascota2;
    private ArrayList<Mascota> mascota3;
    private ArrayList<Mascota> mascotaCompleto;
    private boolean service1 = false;
    private boolean service2 = false;
    private boolean service3 = false;
    private String usuario = "";

    private String TAG = this.getClass().getSimpleName();

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMediosRecientes();
    }


    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascota = constructorMascotas.obtenerDatos();
        mostrarMascotasRecyclerView();
    }

    @Override
    public void eliminarUsuarioBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        constructorMascotas.eliminarUsuarioInstagram();
    }

    @Override
    public void obtenerUsuarioInstagramBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        usuario = constructorMascotas.obtenerUsuarioInstagram();
        //mostrarMascotasRecyclerView();
    }

    @Override
    public void mostrarMascotasRecyclerView() {
        Log.d(TAG, "mostrarAnimalesCompaniaRV()");
        mascota.addAll(mascota2);
        mascota.addAll(mascota3);
        mascotaCompleto = mascota;
        Collections.shuffle(mascotaCompleto);
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascota));
        //iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void obtenerMediosRecientes(){
        Log.d(TAG, "obtenerMediosRecientes()");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        Call<MascotaResponse> animalCompaniaResponseCall= endpointsApi.getRecentMedia();

        animalCompaniaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse animalCompaniaResponse = response.body();
                mascota2 = animalCompaniaResponse.getMascotas();
                service2 = true;
                if(service1 == true && service2 == true && service3 == true){
                    mostrarMascotasRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Falló la conexión " + t.toString());
            }
        });

        mascotaResponseCall = endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse animalCompaniaResponse = response.body();
                mascota3 = animalCompaniaResponse.getMascotas();
                service3 = true;
                if(service1 == true && service2 == true && service3 == true){
                    mostrarMascotasRecyclerView();
                }

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Falló la conexión " + t.toString());
            }
        });

        animalCompaniaResponseCall= endpointsApi.getRecentMedia();
        animalCompaniaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse animalCompaniaResponse = response.body();
                mascota = animalCompaniaResponse.getMascotas();
                service1 = true;
                if(service1 == true && service2 == true && service3 == true){
                    mostrarMascotasRecyclerView();
                }

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Falló la conexión " + t.toString());
            }
        });



    }