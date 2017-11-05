package com.solrom.edgar.petagram50.restAPI.deserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.solrom.edgar.petagram50.pojo.Mascota;
import com.solrom.edgar.petagram50.restAPI.JsonKeys;
import com.solrom.edgar.petagram50.restAPI.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by edgarsr on 26/10/17.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();

        MascotaResponse mascotaResponse = gson.fromJson(json , MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarContactoDeJson(mascotaResponseData));

        return mascotaResponse;
    }


    private ArrayList<Mascota> deserializarContactoDeJson(JsonArray mascotaResponseData)
    {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        for (int i = 0; i < mascotaResponseData.size(); i++)
        {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            String picId = mascotaResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString();

            JsonObject userJson             = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id                       = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto           = userJson.get(JsonKeys.USER.FULL_NAME).getAsString();

            JsonObject imageJson            = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson            = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            Integer likes                   = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setIdFoto(id);
            mascotaActual.setNombreCompleto(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);

            mascotas.add(mascotaActual);

            Log.d(TAG, mascotaActual.getId().toString() + " " +
                    mascotaActual.getNombreCompleto().toString()  + " " +
                    mascotaActual.getIdFoto().toString() + " " +
                    mascotaActual.getUrlFoto().toString()  + " " +
                    mascotaActual.getLikes()

            );


        }
        return mascotas;
    }
}
