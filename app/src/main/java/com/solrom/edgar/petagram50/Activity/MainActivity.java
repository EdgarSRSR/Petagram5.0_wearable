package com.solrom.edgar.petagram50.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram50.adapter.PageAdapter;
import com.solrom.edgar.petagram50.fragment.PrincipalFragment;
import com.solrom.edgar.petagram50.fragment.RecyclerViewFragment;
import com.solrom.edgar.petagram50.pojo.Mascota;
import com.solrom.edgar.petagram50.restAPI.ConstantesRestApi;
import com.solrom.edgar.petagram50.restAPI.EndpointsApi;
import com.solrom.edgar.petagram50.restAPI.adapter.RestApiAdapter;
import com.solrom.edgar.petagram50.restAPI.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //public ArrayList<Mascota> mascotas;
    //public RecyclerView listaMascotas;
    //public MascotaAdaptador adaptador;
    //public Integer likesRecibidos = 0;
    //public TextView tvLikes;
    //public TextView tvNombresMascota;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String userId;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        Log.i( "dd","Extra:" + user );

        setUpViewPager();


        if (toolbar != null) {
            viewPager.setCurrentItem(1);
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String usuario = extras.getString("usuario");

            if (usuario != null) {
                Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show();
                ConstantesRestApi.usuario = usuario;
            }

            String tipoFragment = extras.getString("mainFragment");

            if (tipoFragment != null) {
                PrincipalFragment fragment = new PrincipalFragment();
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

                if (viewPager != null) {
                    viewPager.removeAllViews();
                }

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.viewPager, fragment).commit();
                viewPager.setCurrentItem(1);

            }

            if (ConstantesRestApi.usuario == null) {
                SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
                String verifica = prefe.getString("usuario", "");

                if (!verifica.equals("")) {
                    ConstantesRestApi.usuario = verifica;
                }
            }
        }
    }

    private ArrayList<Fragment> agregarFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PrincipalFragment());

        return fragments;
    }

    private void setUpViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager() , agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_home_100);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_cat_footprint_96);
    }


    private void iniciaListaMascotas()
    {
        mascotas = new ArrayList<Mascota>();
    }



    private void iniciaAdapterMascotas()
    {
        adaptador = new MascotaAdaptador(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.mContacto:
                Intent intentContacto = new Intent(getApplicationContext() , FormularioActivity.class);
                startActivity(intentContacto);
                break;
            case R.id.mFavoritos:
                Intent intentFavorito = new Intent(getApplicationContext() , MascotasFavoritas.class);
                startActivity(intentFavorito);
                break;
            case R.id.mAcercaDe:
                Intent intentAcercaDe = new Intent(getApplicationContext() , AcercadeActivity.class);
                startActivity(intentAcercaDe);
                break;
            case R.id.mConfigurarCuenta:
                Intent intentConfigurarCuenta = new Intent(getApplicationContext() , ConfigurarCuentaActivity.class);
                startActivity(intentConfigurarCuenta);
                break;
            case R.id.mRecibirNotificaciones:
                //NO GENERA INTENT, SOLO GENERA LOS ENVIOS A GCM
                enviarToken();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    public void enviarToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token){
        Log.d("TOKEN", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarTokenID(token);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("InstagramID", usuarioResponse.getId());
                Log.d("token", usuarioResponse.getToken());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });

    }

    private void recibirNotificaciones()
    {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN" , token);
    }

    public void toqueAnimal(View v){
        Log.d("TOQUE_ANIMAL", "true");
        final UsuarioResponse usuarioResponse = new UsuarioResponse(ID_RECEPTOR, "123", ANIMAL_RECEPTOR);
        RestApiAdapter restApiAdapter =  new RestApiAdapter();
        EndpointsApi endponits = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endponits.toqueAnimal(usuarioResponse.getId(), ANIMAL_EMISOR);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 =response.body();
                Log.d("ID_FIREBASE", usuarioResponse1.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse1.getToken());
                Log.d("ANIMAL_FIREBASE", usuarioResponse1.getAnimal());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
