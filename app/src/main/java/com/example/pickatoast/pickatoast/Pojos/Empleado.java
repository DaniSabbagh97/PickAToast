package com.example.pickatoast.pickatoast.Pojos;

public class Empleado {
    private String nombre;
    private String apellidos;
    private String localidad;
    private String estado;
    private String puntuacion;
    private String imagen;

    public Empleado(String nombre, String apellidos, String localidad, String estado, String puntuacion, String imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
        this.estado = estado;
        this.puntuacion = puntuacion;
        this.imagen = imagen;
    }
    //getters
    public Empleado() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getEstado() {
        return estado;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public String getImagen() {
        return imagen;
    }
    //fin getters
    //<--------------------------------------->
    //setter


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    //fin setters
}



