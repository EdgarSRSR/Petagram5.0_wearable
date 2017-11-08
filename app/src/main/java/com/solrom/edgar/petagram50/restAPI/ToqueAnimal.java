package com.solrom.edgar.petagram50.restAPI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.solrom.edgar.petagram50.restAPI.adapter.RestApiAdapter;
import com.solrom.edgar.petagram50.restAPI.model.UsuarioResponse;

import retrofit2.Call;

/**
 * Created by edgarsr on 07/11/17.
 */

public class ToqueAnimal extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        private static final String ANIMAL_RECEPTOR = "Mu";
        private static final String ANIMAL_EMISOR = "Toby";
        private static final String TOBY = "";
        private static final String RATON = "";
        private static final String ID_RECEPTOR = TOBY;


        @Override
        public void onReceive(Context context, Intent intent) {
            String ACTION_KEY = "TOQUE_ANIMAL";
            String accion = intent.getAction();

            if (ACTION_KEY.equals(accion)){
                toqueAnimal();
                Toast.makeText(context, "Diste un toque a " + ANIMAL_RECEPTOR, Toast.LENGTH_SHORT).show();
            }
        }

        public void toqueAnimal(){
            Log.d("TOQUE_ANIMAL", "true");
            final UsuarioResponse usuarioResponse = new UsuarioResponse(ID_RECEPTOR, "123", ANIMAL_RECEPTOR);
            RestApiAdapter restApiAdapter =  new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestAPI();
            Call<UsuarioResponse> usuarioResponseCall = endpointsApi.toqueAnimal(usuarioResponse.getId(), ANIMAL_EMISOR);
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
}
