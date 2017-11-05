package com.solrom.edgar.petagram50.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.restAPI.EndpointsApi;
import com.solrom.edgar.petagram50.restAPI.adapter.RestApiAdapter;
import com.solrom.edgar.petagram50.restAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edgarsr on 03/11/17.
 */

public class NotificationsActivity extends AppCompatActivity {

    private static final String TAG = "TOKEN";
    private String userIdInstragram;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Intent intent = getIntent();
        userIdInstragram = intent.getStringExtra("userIdInstagram");

        Toolbar miactionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miactionBar);
    }

    public  void enviarToken(View v){
        String idDispositivo = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(idDispositivo);
    }

    private void enviarTokenRegistro(String idDispositivo){
        Log.d(TAG, idDispositivo);
        Log.d(TAG, userIdInstragram);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarTokenId(idDispositivo, userIdInstragram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("idfirebase", usuarioResponse.getId());
                Log.d("usuariofirebase", usuarioResponse.getIdDispositivo());
                Log.d("usuarioInstagram", usuarioResponse.getIdUsuarioInstagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
            }
        });


    }
}
