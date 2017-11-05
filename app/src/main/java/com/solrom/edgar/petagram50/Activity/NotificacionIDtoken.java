package com.solrom.edgar.petagram50.Activity;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by edgarsr on 27/10/17.
 */

public class NotificacionIDtoken extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE TOKEN";

    @Override
    public void onTokenRefresh(){

        Log.d(TAG, "Solicitando token");

        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);

    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
