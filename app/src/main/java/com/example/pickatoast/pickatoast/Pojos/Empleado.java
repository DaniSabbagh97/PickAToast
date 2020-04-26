package com.example.pickatoast.pickatoast.Pojos;

public class Empleado {


    String idEmpleado;
    String nombreEmpleado;
    String ciudadEmpleado;
    String direccionEmpleado;
    boolean trabaja;
    String puntuacionEmpleado;
    String imagenPerfilEmpleadoURL;
    String oficioEmpleado;
    String subidaCurriculumURL;
    String descipcionEmpleado;

    public Empleado(String idEmpleado, String nombreEmpleado, String ciudadEmpleado, String direccionEmpleado, boolean trabaja, String puntuacionEmpleado,
                    String imagenPerfilEmpleadoURL, String oficioEmpleado, String subidaCurriculumURL, String descipcionEmpleado) {
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
    }


}