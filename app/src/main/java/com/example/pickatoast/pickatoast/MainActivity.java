package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Interfaces.TopMainMenuImpl;


public class MainActivity extends AppCompatActivity implements TopMainMenuImpl {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,RegristroEmpleado.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void menu(int buttonClicked) {
        Toast.makeText(this,"El boton pulsado es el: "+buttonClicked,Toast.LENGTH_SHORT).show();
    }
}
