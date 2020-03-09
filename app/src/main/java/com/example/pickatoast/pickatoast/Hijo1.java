package com.example.pickatoast.pickatoast;

public class Hijo1 extends Padre {
    private String perro="rex";

    public Hijo1(String perro) {
        this.perro = perro;
    }

    public void llamarAlPerro(){
        System.out.println(perro);
    }
}
