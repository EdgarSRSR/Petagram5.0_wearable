package com.solrom.edgar.petagram50.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.adapter.MascotaFavoritaAdapter;
import com.solrom.edgar.petagram50.bd.ConstructorMascotas;
import com.solrom.edgar.petagram50.pojo.Mascota;

import java.util.ArrayList;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotasDummy = (RecyclerView)findViewById(R.id.rvMascota2);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotasDummy.setLayoutManager(llm);

        obtenerMascotasBaseDatos();
        iniciaAdapterMascotas();
    }



    private void iniciaListaMascotas()
    {
        mascotas = new ArrayList<Mascota>();


    }


    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(getApplicationContext());
        mascotas = constructorMascotas.obtenerMascotasFavoritas();
    }



    private void iniciaAdapterMascotas()
    {
        adapter = new MascotaFavoritaAdapter(mascotas,this);
        listaMascotasDummy.setAdapter(adapter);
    }
}
