package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Registro extends AppCompatActivity {

    ImageButton imgbEmpleado;
    ImageButton imgbEmplador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        imgbEmpleado=findViewById(R.id.imgbEmpleado);
        imgbEmplador=findViewById(R.id.imgbEmpelador);

        imgbEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Registro.this,RegristroEmpleado.class);
                startActivity(i);
            }
        });

        imgbEmplador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Registro.this,RegistroEmpleador1.class);
                startActivity(i);
            }
        });

    }
}
