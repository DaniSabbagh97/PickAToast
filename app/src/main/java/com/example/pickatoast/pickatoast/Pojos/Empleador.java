package com.example.pickatoast.pickatoast.Pojos;

public class Empleador {

    private String nombre;
    private String correo;
    private String localidad;
    private String puntuacion;
    private String tipo;
    private String imagen;

    public Empleador(String nombre, String correo, String localidad, String puntuacion, String tipo, String imagen) {
        this.nombre = nombre;
        this.correo = correo;
        this.localidad = localidad;
        this.puntuacion = puntuacion;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
