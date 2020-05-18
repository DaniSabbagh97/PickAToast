package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Registro extends AppCompatActivity {


    ImageButton imgEmpelador;
    ImageButton imgEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        imgEmpleado=findViewById(R.id.ibRegistroEmpleado);
        imgEmpelador=findViewById(R.id.ibRegistroEmpleador);

        imgEmpelador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this,RegistroEmpleador1.class));
            }
        });

        imgEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this, RegristroEmpleado.class));
            }
        });
    }
}
