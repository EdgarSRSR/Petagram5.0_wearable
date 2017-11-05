package com.solrom.edgar.petagram50.restAPI.model;

/**
 * Created by edgarsr on 03/11/17.
 */

public class LikeResponse {

    private String id;
    private String idDispositivo;
    private String idUsuarioInstagram;
    private String idFoto;

    public LikeResponse(String id, String idDispositivo, String idUsuarioInstagram, String idFoto) {
        this.id = id;
        this.idDispositivo = idDispositivo;
        this.idUsuarioInstagram = idUsuarioInstagram;
        this.idFoto = idFoto;
    }

    public LikeResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String id_dispositivo) {
        this.idDispositivo = id_dispositivo;
    }

    public String getIdUsuarioInstagram() {
        return idUsuarioInstagram;
    }

    public void setIdUsuarioInstagram(String id_usuario_instagram) {
        this.idUsuarioInstagram = id_usuario_instagram;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String id_foto) {
        this.idFoto = id_foto;
    }
}
