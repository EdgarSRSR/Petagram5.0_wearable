package com.solrom.edgar.petagram50.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.solrom.edgar.petagram50.R;

/**
 * Created by edgarsr on 27/10/17.
 */

public class NotificacionService extends FirebaseMessagingService {

    public static final String TAG = "Firebase";
    private String user = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("Firebase", "From: " + remoteMessage.getFrom());
        Log.d("Firebase", "Notification Message Body: " + remoteMessage.getNotification());

        if(remoteMessage.getData().size() > 0){
            Log.d(TAG, "Dta del mensaje: " + remoteMessage.getData());
            user = remoteMessage.getData().get("user");
        }

        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "Mensaje de la notificación: " + remoteMessage.getNotification().getBody());
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("goingToProfile");
        intent.putExtra("user", "whatever");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icons8_dog_bone_48)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setAutoCancel(true);


        notificacion.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());


    }
}
