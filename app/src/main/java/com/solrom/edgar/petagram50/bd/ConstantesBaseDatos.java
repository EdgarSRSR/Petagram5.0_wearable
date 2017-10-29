package com.solrom.edgar.petagram50.bd;

/**
 * Created by edgarsr on 26/10/17.
 */

public class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLA_MASCOTA = "mascota";
    public static final String TABLA_MASCOTA_ID     = "id";
    public static final String TABLA_MASCOTA_NOMBRE = "nombre";
    public static final String TABLA_MASCOTA_LIKES  = "likes";
    public static final String TABLA_MASCOTA_FOTO   = "foto";

    public static final String TABLA_LIKES_MASCOTA = "mascota_likes";
    public static final String TABLA_LIKES_MASCOTA_ID = "id";
    public static final String TABLA_LIKES_MASCOTA_ID_MASCOTA = "id_mascota";
    public static final String TABLA_LIKES_MASCOTA_NUMERO_LIKES = "numero_likes";

    public static final String TABLA_CUENTA = "cuenta";
    public static final String TABLA_CUENTA_ID = "cuenta_id";
    public static final String TABLA_CUENTA_USUARIO = "cuenta_usuario";
}
