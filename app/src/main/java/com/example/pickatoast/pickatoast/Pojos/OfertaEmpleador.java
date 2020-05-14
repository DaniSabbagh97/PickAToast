package com.example.pickatoast.pickatoast.Pojos;

import java.io.Serializable;

public class OfertaEmpleador implements Serializable {

    private String id;
    private String nombreOferta;
    private String descripcionOferta;
    private String restaurante;
    private String localizacionOferta;
    private String duracionContrato;
    private String linkImagen;
    private boolean esEvento;

    public OfertaEmpleador(String id, String nombreOferta,String descripcionOferta, String restaurante, String localizacionOferta, String duracionContrato, String linkImagen, boolean esEvento) {
        this.id = id;
        this.nombreOferta = nombreOferta;
        this.descripcionOferta = descripcionOferta;
        this.restaurante = restaurante;
        this.localizacionOferta = localizacionOferta;
        this.duracionContrato = duracionContrato;
        this.linkImagen = linkImagen;
        this.esEvento = esEvento;
    }
    public OfertaEmpleador(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        restaurante = restaurante;
    }

    public String getLocalizacionOferta() {
        return localizacionOferta;
    }

    public void setLocalizacionOferta(String localizacionOferta) {
        this.localizacionOferta = localizacionOferta;
    }

    public String getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(String duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public boolean isEsEvento() {
        return esEvento;
    }

    public void setEsEvento(boolean esEvento) {
        this.esEvento = esEvento;
    }
}
