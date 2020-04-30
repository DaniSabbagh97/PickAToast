package com.example.pickatoast.pickatoast.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class OfertaEmpleador {

    String id;
    String nombreOferta;
    String Restaurante;
    String localizacionOferta;
    String duracionContrato;
    String linkImagen;
    boolean esEvento;

    public OfertaEmpleador(String id, String nombreOferta, String restaurante, String localizacionOferta, String duracionContrato, String linkImagen, boolean esEvento) {
        this.id = id;
        this.nombreOferta = nombreOferta;
        Restaurante = restaurante;
        this.localizacionOferta = localizacionOferta;
        this.duracionContrato = duracionContrato;
        this.linkImagen = linkImagen;
        this.esEvento = esEvento;
    }
}
