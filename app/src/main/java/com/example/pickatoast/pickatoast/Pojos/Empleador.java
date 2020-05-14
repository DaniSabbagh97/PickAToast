package com.example.pickatoast.pickatoast.Pojos;

import java.util.ArrayList;

public class Empleador {
    String id;
    String nombreEmpleado;
    String nombreEmpresa;
    String correoEmpleador;
    String ciudadEmpleador;
    String direccionEmpleador;
    String imagenLogoEmpresaURL;
    String descripcionEmpleador;
    ArrayList<String> coleccionImagenesEmpresa;
    boolean buscaEmpleado;
    ArrayList<OfertaEmpleador> ofertaEmpleador;

    public Empleador(String id, String nombreEmpleado, String nombreEmpresa, String correoEmpleador, String ciudadEmpleador, String direccionEmpleador,
                     String imagenLogoEmpresaURL, String descripcionEmpleador, ArrayList<String> coleccionImagenesEmpresa, boolean buscaEmpleado, ArrayList<OfertaEmpleador> ofertaEmpleador) {
        this.id = id;
        this.nombreEmpleado = nombreEmpleado;
        this.nombreEmpresa = nombreEmpresa;
        this.correoEmpleador = correoEmpleador;
        this.ciudadEmpleador = ciudadEmpleador;
        this.direccionEmpleador = direccionEmpleador;
        this.imagenLogoEmpresaURL = imagenLogoEmpresaURL;
        this.descripcionEmpleador = descripcionEmpleador;
        this.coleccionImagenesEmpresa = coleccionImagenesEmpresa;
        this.buscaEmpleado = buscaEmpleado;
        this.ofertaEmpleador = ofertaEmpleador;
    }

    public Empleador() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCorreoEmpleador() {
        return correoEmpleador;
    }

    public void setCorreoEmpleador(String correoEmpleador) {
        this.correoEmpleador = correoEmpleador;
    }

    public String getCiudadEmpleador() {
        return ciudadEmpleador;
    }

    public void setCiudadEmpleador(String ciudadEmpleador) {
        this.ciudadEmpleador = ciudadEmpleador;
    }

    public String getDireccionEmpleador() {
        return direccionEmpleador;
    }

    public void setDireccionEmpleador(String direccionEmpleador) {
        this.direccionEmpleador = direccionEmpleador;
    }

    public String getImagenLogoEmpresaURL() {
        return imagenLogoEmpresaURL;
    }

    public void setImagenLogoEmpresaURL(String imagenLogoEmpresaURL) {
        this.imagenLogoEmpresaURL = imagenLogoEmpresaURL;
    }

    public String getDescripcionEmpleador() {
        return descripcionEmpleador;
    }

    public void setDescripcionEmpleador(String descripcionEmpleador) {
        this.descripcionEmpleador = descripcionEmpleador;
    }

    public ArrayList<String> getColeccionImagenesEmpresa() {
        return coleccionImagenesEmpresa;
    }

    public void setColeccionImagenesEmpresa(ArrayList<String> coleccionImagenesEmpresa) {
        this.coleccionImagenesEmpresa = coleccionImagenesEmpresa;
    }

    public boolean isBuscaEmpleado() {
        return buscaEmpleado;
    }

    public void setBuscaEmpleado(boolean buscaEmpleado) {
        this.buscaEmpleado = buscaEmpleado;
    }

    public ArrayList<OfertaEmpleador> getOfertaEmpleador() {
        return ofertaEmpleador;
    }

    public void setOfertaEmpleador(ArrayList<OfertaEmpleador> ofertaEmpleador) {
        this.ofertaEmpleador = ofertaEmpleador;
    }
}
