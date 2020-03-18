package com.example.pickatoast.pickatoast.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Oferta implements Parcelable {

    String nombreOferta;
    String restauranteOferta;
    String localizacionOferta;
    String duracionContrato;
    String linkImagen;

    public Oferta(String nombreOferta, String restauranteOferta, String localizacionOferta, String duraciónContrato, String linkImagen) {
        this.nombreOferta = nombreOferta;
        this.restauranteOferta = restauranteOferta;
        this.localizacionOferta = localizacionOferta;
        this.duracionContrato = duraciónContrato;
        this.linkImagen= linkImagen;
    }

    public Oferta() {
    }

    protected Oferta(Parcel in){
        nombreOferta=in.readString();
        restauranteOferta=in.readString();
        localizacionOferta=in.readString();
        duracionContrato=in.readString();
        linkImagen=in.readString();
    }

    public static final Creator<Oferta> CREATOR=new Creator<Oferta>() {
        @Override
        public Oferta createFromParcel(Parcel in) {return new Oferta(in);}

        @Override
        public Oferta[] newArray(int size) {return new Oferta[size];}
    };

    public String getNombreOferta() {
        return nombreOferta;
    }

    public String getRestauranteOferta() {
        return restauranteOferta;
    }

    public String getLocalizacionOferta() {
        return localizacionOferta;
    }

    public String getDuracionContrato() {
        return duracionContrato;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreOferta);
        dest.writeString(restauranteOferta);
        dest.writeString(localizacionOferta);
        dest.writeString(duracionContrato);
        dest.writeString(linkImagen);
    }
}
