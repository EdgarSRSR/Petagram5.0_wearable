package com.solrom.edgar.petagram50.restAPI.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.solrom.edgar.petagram50.restAPI.ConstantesRestApi;
import com.solrom.edgar.petagram50.restAPI.EndpointsApi;
import com.solrom.edgar.petagram50.restAPI.deserializador.MascotaDeserializador;
import com.solrom.edgar.petagram50.restAPI.deserializador.MascotaDeserializadorUser;
import com.solrom.edgar.petagram50.restAPI.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edgarsr on 26/10/17.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }


    public EndpointsApi establecerConexionRestApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionRestApiInstagramSinDes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }


    public Gson construyeGsonDeserializadorMediaRecent()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class , new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUser()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class , new MascotaDeserializadorUser());
        return gsonBuilder.create();
    }

    public EndpointsApi registrarUsuario()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_PETAGRAM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);

    }

    /*
    public Gson construyeGsonDeserializadorUserPropio()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioPropioResponse.class , new UsuarioDeserealizadorUserPropio());
        return gsonBuilder.create();
    }*/


}
