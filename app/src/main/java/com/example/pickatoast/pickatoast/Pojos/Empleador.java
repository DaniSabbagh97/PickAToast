package com.example.pickatoast.pickatoast.Pojos;

import java.util.ArrayList;

public class Empleador {

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

    public Empleador(String nombreEmpleado, String nombreEmpresa, String correoEmpleador, String ciudadEmpleador, String direccionEmpleador, String imagenLogoEmpresaURL, String descripcionEmpleador,
                     ArrayList<String> coleccionImagenesEmpresa, boolean buscaEmpleado, ArrayList<OfertaEmpleador> ofertaEmpleador) {
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
}
