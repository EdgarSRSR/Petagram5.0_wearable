package com.solrom.edgar.petagram50.bd;

import android.content.ContentValues;
import android.content.Context;

import com.solrom.edgar.petagram50.R;
import com.solrom.edgar.petagram50.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 26/10/17.
 */

public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context contexto;

    public ConstructorMascotas(Context contexto)
    {
        this.contexto = contexto;
    }


    public ArrayList<Mascota> obtenerDatos()
    {
        BaseDatos db = new BaseDatos(contexto);
        if(!verificaExistenciaTabla())
        {
            insertar5Mascotas(db);
        }
        return db.obtenerTodasLasMascotas();
    }


    public ArrayList<Mascota> obtenerMascotasFavoritas()
    {
        BaseDatos db = new BaseDatos(contexto);
        return db.listaFavoritos();
    }


    public boolean verificaExistenciaTabla()
    {
        BaseDatos db = new BaseDatos(contexto);
        return db.validaExistenciaTabla();
    }

    public boolean verificaExistenciaTablaCuenta()
    {
        BaseDatos db = new BaseDatos(contexto);
        return db.validaExistenciaTablaCuenta();
    }

    public void insertar5Mascotas(BaseDatos db)
    {
        ContentValues contentValuesSpark = new ContentValues();
        contentValuesSpark.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE , "Toby");
        contentValuesSpark.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO , R.drawable.schnauzer_black);
        db.insertarMascotas(contentValuesSpark);

        ContentValues contentValuesCoffee = new ContentValues();
        contentValuesCoffee.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE , "Kiby");
        contentValuesCoffee.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO , R.drawable.hamster);
        db.insertarMascotas(contentValuesCoffee);

        ContentValues contentValuesKaiser = new ContentValues();
        contentValuesKaiser.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE , "Hachiko");
        contentValuesKaiser.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO , R.drawable.shibainu);
        db.insertarMascotas(contentValuesKaiser);

        ContentValues contentValuesShamu = new ContentValues();
        contentValuesShamu.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE , "Pilon");
        contentValuesShamu.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO , R.drawable.schnauzer_black);
        db.insertarMascotas(contentValuesShamu);

        ContentValues contentValuesBingo = new ContentValues();
        contentValuesBingo.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE , "Cochon");
        contentValuesBingo.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO , R.drawable.french_bulldog);
        db.insertarMascotas(contentValuesBingo);

    }



    public void darlikeMascota(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(contexto);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA , mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLA_LIKES_MASCOTA_NUMERO_LIKES , LIKE);
        db.insertarLikeMascota(contentValues);

        //db.actualizaMascotaLikes(mascota.getId());

        ArrayList<Mascota> list = new ArrayList<Mascota>();
        list = db.obtenerTodasLasMascotas();
    }



    public int obtenerLikesMascota(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(contexto);
        return db.obtenerLikesMascota(mascota);
    }


    public void eliminarUsuarioInstagram()
    {
        BaseDatos db = new BaseDatos(contexto);
        db.eliminaUsuarioInstagram();
    }


    public String obtenerUsuarioInstagram()
    {
        BaseDatos db = new BaseDatos(contexto);
        return db.obtenerUsuarioInstagram();
    }


    public void insertarUsuarioInstagram(String nombreUsuario)
    {
        BaseDatos db = new BaseDatos(contexto);
        ContentValues contentValuesUsuario = new ContentValues();
        contentValuesUsuario.put(ConstantesBaseDatos.TABLA_CUENTA_USUARIO , nombreUsuario);
        db.insertarUsuarioInstagram(contentValuesUsuario);
    }
}
