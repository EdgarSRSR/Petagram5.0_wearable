package com.solrom.edgar.petagram50.restAPI.model;

/**
 * Created by edgarsr on 27/10/17.
 */

public class UsuarioResponse {

    private String id;
    private String idDispositivo;
    private String idUsuarioInstagram;

    public UsuarioResponse(String id, String idDispositivo, String idUsuarioInstagram) {
        this.id = id;
        this.idDispositivo = idDispositivo;
        this.idUsuarioInstagram = idUsuarioInstagram;
    }

    public UsuarioResponse() {
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

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getIdUsuarioInstagram() {
        return idUsuarioInstagram;
    }

    public void setIdUsuarioInstagram(String idUsuarioInstagram) {
        this.idUsuarioInstagram = idUsuarioInstagram;
    }
}
