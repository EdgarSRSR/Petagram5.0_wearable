package com.solrom.edgar.petagram50.pojo;

/**
 * Created by edgarsr on 26/10/17.
 */

public class Mascota {
    private String id;
    //private String nombreMascota;
    private String idFoto;

    //private String idInstagram;
    private String nombreCompleto;
    private String urlFoto;
    private Integer likes = 0;


    public Mascota(String idFoto, String urlFoto, String nombreCompleto, int numeroLikes) {
        this.idFoto = idFoto;
        this.urlFoto = urlFoto;
        this.nombreCompleto = nombreCompleto;
        this.likes = numeroLikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /*
    public Mascota(String idInstagram , String nombreCompleto , String urlFoto , Integer likes)
    {
        this.idInstagram = idInstagram;
        this.nombreCompleto = nombreCompleto;
        this.urlFoto = urlFoto;
        this.likes = likes;
    }


    public Mascota(String nombreMascota , Integer foto)
    {
        this.nombreMascota = nombreMascota;
        this.foto = foto;
    }

    public Mascota() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public Integer getFoto() {
        return foto;
    }

    public void setFoto(Integer foto) {
        this.foto = foto;
    }

    public String getIdInstagram() {
        return idInstagram;
    }

    public void setIdInstagram(String idInstagram) {
        this.idInstagram = idInstagram;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreComleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }*/
}
