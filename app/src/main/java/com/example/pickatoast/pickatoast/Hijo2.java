package com.example.pickatoast.pickatoast;

public class Hijo2 extends Padre {
    private String gato="michi";

    public Hijo2(String gato) {
        this.gato = gato;
    }

    public void llamarAlPerro(){
        System.out.println(gato);
    }
}
