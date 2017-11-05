package com.solrom.edgar.petagram50.pojo;

/**
 * Created by edgarsr on 03/11/17.
 */

public class MascotaProfile {

    private String numeroLikes;
    private int foto;

    public MascotaProfile(String numeroLikes, int foto) {
        this.numeroLikes = numeroLikes;
        this.foto = foto;
    }

    public String getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(String numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
