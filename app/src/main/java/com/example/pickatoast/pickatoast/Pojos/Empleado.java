package com.example.pickatoast.pickatoast.Pojos;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleado implements Serializable {


    private String idEmpleado;
    private String nombreEmpleado;
    private String ciudadEmpleado;
    private String direccionEmpleado;
    private boolean trabaja;
    private String puntuacionEmpleado;
    private String imagenPerfilEmpleadoURL;
    private String oficioEmpleado;
    private String subidaCurriculumURL;
    private String descipcionEmpleado;
    private String contraseñaEmpleado;
    private String correoEmpleado;
    private int numeroOfertas;
    private ArrayList<OfertaEmpleador> ofertasEmpleados;

    public Empleado(ArrayList<OfertaEmpleador> ofertasEmpleados) {
        this.ofertasEmpleados = ofertasEmpleados;
    }

    public Empleado(String idEmpleado, String nombreEmpleado, String ciudadEmpleado, String direccionEmpleado, boolean trabaja, String puntuacionEmpleado,
                    String imagenPerfilEmpleadoURL, String oficioEmpleado, String subidaCurriculumURL, String descipcionEmpleado, String contraseñaEmpleado, String correoEmpleado, int numeroOfertas) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.ciudadEmpleado = ciudadEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.trabaja = trabaja;
        this.puntuacionEmpleado = puntuacionEmpleado;
        this.imagenPerfilEmpleadoURL = imagenPerfilEmpleadoURL;
        this.oficioEmpleado = oficioEmpleado;
        this.subidaCurriculumURL = subidaCurriculumURL;
        this.descipcionEmpleado = descipcionEmpleado;
        this.contraseñaEmpleado = contraseñaEmpleado;
        this.correoEmpleado = correoEmpleado;
        this.numeroOfertas = numeroOfertas;
    }

    public Empleado() {
    }

    public Empleado(int numeroOfertas) {
    }

    public int getnumeroOfertas() {
        return numeroOfertas;
    }

    public void setnumeroOfertas(int numeroOfertas) {
        this.numeroOfertas = numeroOfertas;
    }

    public ArrayList<OfertaEmpleador> getOfertasEmpleados() {
        return ofertasEmpleados;
    }

    public void setOfertasEmpleados(ArrayList<OfertaEmpleador> ofertasEmpleados) {
        this.ofertasEmpleados = ofertasEmpleados;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getCiudadEmpleado() {
        return ciudadEmpleado;
    }

    public void setCiudadEmpleado(String ciudadEmpleado) {
        this.ciudadEmpleado = ciudadEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public boolean isTrabaja() {
        return trabaja;
    }

    public void setTrabaja(boolean trabaja) {
        this.trabaja = trabaja;
    }

    public String getPuntuacionEmpleado() {
        return puntuacionEmpleado;
    }

    public void setPuntuacionEmpleado(String puntuacionEmpleado) {
        this.puntuacionEmpleado = puntuacionEmpleado;
    }

    public String getImagenPerfilEmpleadoURL() {
        return imagenPerfilEmpleadoURL;
    }

    public void setImagenPerfilEmpleadoURL(String imagenPerfilEmpleadoURL) {
        this.imagenPerfilEmpleadoURL = imagenPerfilEmpleadoURL;
    }

    public String getOficioEmpleado() {
        return oficioEmpleado;
    }

    public void setOficioEmpleado(String oficioEmpleado) {
        this.oficioEmpleado = oficioEmpleado;
    }

    public String getSubidaCurriculumURL() {
        return subidaCurriculumURL;
    }

    public void setSubidaCurriculumURL(String subidaCurriculumURL) {
        this.subidaCurriculumURL = subidaCurriculumURL;
    }

    public String getDescipcionEmpleado() {
        return descipcionEmpleado;
    }

    public void setDescipcionEmpleado(String descipcionEmpleado) {
        this.descipcionEmpleado = descipcionEmpleado;
    }

    public String getContraseñaEmpleado() {
        return contraseñaEmpleado;
    }

    public void setContraseñaEmpleado(String contraseñaEmpleado) {
        this.contraseñaEmpleado = contraseñaEmpleado;
    }
}