package com.solrom.edgar.petagram50.Activity;

//import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
//import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

import com.solrom.edgar.petagram50.R;

/**
 * Created by edgarsr on 27/10/17.
 */

public class NotificacionService extends FirebaseMessagingService {

    Intent i = new Intent();
    public static final String TAG = "Firebase";
    public static final int NOTIFICATION_ID = 001;
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

        android.support.v7.app.NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_full_poke,
                getString(R.string.textoacciontoque), pendingIntent).build();

        android.support.v7.app.NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender().setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.bk_androidwear_notification))
                .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icons8_dog_bone_48)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action))
                //.addAction(R.drawable.ic_full_poke, getString(R.string.textoacciontoque), pendingIntent)
                ;


        notificacion.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());


    }

    public void enviarNotificacion(RemoteMessage remoteMessage){

        Intent i = new Intent();
        i.setAction("TOQUE_ANIMAL");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_poke,
                        getString(R.string.textoacciontoque), pendingIntent)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(),
                                R.drawable.bk_androidwear_notification))
                        .setGravity(Gravity.CENTER_VERTICAL)
                ;


        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icons8_dog_bone_48)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action))
                //.addAction(R.drawable.ic_full_poke, getString(R.string.texto_accion_toque), pendingIntent)
                ;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());
    }
}
