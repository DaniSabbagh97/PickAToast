package com.example.pickatoast.pickatoast.Pojos;

public class Empleado {
    private String nombre;

    private String correo;
    private String localidad;
    private String estado;
    private String puntuacion;
    private String imagen;
    private String tipo;
    private String oficio;

    public Empleado(String nombre, String correo, String localidad, String estado, String puntuacion, String imagen, String oficio) {
        this.nombre = nombre;
        this.correo = correo;
        this.localidad = localidad;
        this.estado = estado;
        this.puntuacion = puntuacion;
        this.imagen = imagen;
        this.oficio= oficio;
    }

    public Empleado() {
    }


    //<---------Getters y Setters-------->


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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }
}



