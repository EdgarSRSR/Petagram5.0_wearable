package com.solrom.edgar.petagram50.restAPI;

import com.solrom.edgar.petagram50.restAPI.model.MascotaResponse;
import com.solrom.edgar.petagram50.restAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by edgarsr on 26/10/17.
 */

public interface EndpointsApi {


    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET("users/search?access_token=6216568161.c002059.032bb9dc8fe84b62ad3e36566348d490")
    Call<MascotaResponse> getUser(@Query("q") String username2);

    @GET(ConstantesRestApi.KEY_USERS + "{idUsuario}" +  ConstantesRestApi.KEY_GET_RECENT_MEDIA)
    Call<MascotaResponse> getMediaUser(@Path("idUsuario") String idUsuario2);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("token")String token);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_REGISTRO_USUARIO)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo")String id_dispositivo , @Field("id_usuario_instagram")String id_usuario_instagram , @Field("id")String id);

}
