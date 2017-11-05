package com.solrom.edgar.petagram50.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram50.pojo.Mascota;
import com.solrom.edgar.petagram50.presentador.IPrincipalFragmentPresenter;
import com.solrom.edgar.petagram50.presentador.PrincipalFragmentPresenter;
import com.solrom.edgar.petagram50.restAPI.ConstantesRestApi;
import com.solrom.edgar.petagram50.restAPI.EndpointsApi;
import com.solrom.edgar.petagram50.restAPI.adapter.RestApiAdapter;
import com.solrom.edgar.petagram50.restAPI.model.MascotaResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edgarsr on 26/10/17.
 */

/*public class PrincipalFragment extends Fragment implements IPrincipalFragment {

    public ArrayList<Mascota> mascotas;
    public RecyclerView listaMascotas;
    public RecyclerView listaMascotas2;
    public ImageView imvMiMascota;
    public MascotaAdaptador adapter;
    private ImageView imageView;
    private TextView tvMiMascota;
    private IPrincipalFragmentPresenter presenter;
    private IPrincipalFragmentPresenter presenter2;

    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_principal , container , false);

        listaMascotas = (RecyclerView)v.findViewById(R.id.rvMiMascota);
        imageView = (ImageView)v.findViewById(R.id.imvPrincipal);
        tvMiMascota = (TextView)v.findViewById(R.id.tvNombreMiMascota);

        presenter = new PrincipalFragmentPresenter(this , getContext());


        //extraemos el drawable en un bitmap
        Drawable originalDrawable = getResources().getDrawable(R.drawable.shibainu);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());


        imageView.setImageDrawable(roundedDrawable);

        return v;
    }


    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 2);
        listaMascotas.setLayoutManager(gridLayoutManager);
    }


    @Override
    public MascotaAdaptador crearAdaptadorListas(ArrayList<Mascota> mascotas) {

        Picasso.with(getActivity())
                .load(mascotas.get(0).getUrlFoto())
                .placeholder(R.drawable.schnauzer_blackandpepper)
                .into(imageView);

        tvMiMascota.setText(mascotas.get(0).getNombreCompleto());

        adapter = new MascotaAdaptador(mascotas,getActivity());
        return adapter;

    }

    @Override
    public void actualizaPerfil()
    {
        Picasso.with(getActivity())
                .load(ConstantesRestApi.urlPerfil)
                .placeholder(R.drawable.schnauzer_blackandpepper)
                .into(imageView);
    }


    @Override
    public void crearAdaptador(ArrayList<Mascota> mascotas) {

        Picasso.with(getActivity())
                .load(mascotas.get(0).getUrlFoto())
                .placeholder(R.drawable.shibainu)
                .into(imageView);

        tvMiMascota.setText(mascotas.get(0).getNombreCompleto());

        adapter = new MascotaAdaptador(mascotas,getActivity());


        //DESDE AQUI, LANZAMOS EL SHARED PREFERENCE DE ID DEL USUARIO PARA BUSCAR LAS FOTOGRAFIAS
        SharedPreferences preferencias=getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("id", mascotas.get(0).getIdInstagram());
        editor.putString("urlPerfil" , mascotas.get(0).getUrlFoto());
        editor.commit();

        ConstantesRestApi.idUsuario = mascotas.get(0).getIdInstagram();
        ConstantesRestApi.urlPerfil = mascotas.get(0).getUrlFoto();
        presenter2 = new PrincipalFragmentPresenter(this , getContext() , true);
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adapter);
    }


    private void iniciaListaMascotas()
    {
        mascotas = new ArrayList<Mascota>();
    }



    private void iniciaAdapterMascotas()
    {
        adapter = new MascotaAdaptador(mascotas,getActivity());
        listaMascotas.setAdapter(adapter);
    }
}*/

public class PrincipalFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaFotos;
    private String TAG = this.getClass().getSimpleName();
    private String user;
    private TextView tvNombreMascota;

    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_principal, container, false);

        listaFotos = (RecyclerView) v.findViewById(R.id.rvFotos);
        tvNombreMascota = (TextView) v.findViewById(R.id.tv_nombre_mascota);

        LinearLayoutManager llm = new GridLayoutManager(getActivity(), 3);

        listaFotos.setLayoutManager(llm);

        Intent intent = getActivity().getIntent();
        user = intent.getStringExtra("user");



        //inicializarListaAnimalesCompania();
        obtenerMediosRecientes(v);

        return  v;
    }

    public void obtenerMediosRecientes(final View v){
        Log.d(TAG, "obtenerMediosRecientes()");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        Call<MascotaResponse> animalCompaniaResponseCall = endpointsApi.getRecentMedia();

        //aquí revisar si hay intent
        if(user != null){
            Log.d("se seteo un usuario", user);
            if(user.equals("Mu")){
                tvNombreMascota.setText("Mu");
                animalCompaniaResponseCall= endpointsApi.getRecentMediaMu();
            }else if(user.equals("Toby")){
                tvNombreMascota.setText("Toby");
                animalCompaniaResponseCall = endpointsApi.getRecentMediaToby();
            }
        }



        animalCompaniaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse animalCompaniaResponse = response.body();
                mascotas = animalCompaniaResponse.getMascotas();


                mostrarAnimalesCompania(v);
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Falló la conexión " + t.toString());
            }
        });
    }

    public void mostrarAnimalesCompania(View v) {
        Log.d(TAG, "mostrarAnimalesCompania()");

        ImageView profile = (ImageView) v.findViewById(R.id.ivProfilePic);

        Picasso.with(getActivity())
                .load(mascotas.get(0).getUrlFoto())
                .placeholder(R.drawable.shibainu)
                .into(profile);

        inicializarAdaptador(crearAdaptador(mascotas));
    }

    public void inicializarAdaptador(MascotaAdaptador adaptador){
        listaFotos.setAdapter(adaptador);
    }

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> animalesCompania) {
        MascotaAdaptador adaptador = new MascotaAdaptador(animalesCompania, getActivity());
        return adaptador;
    }

}