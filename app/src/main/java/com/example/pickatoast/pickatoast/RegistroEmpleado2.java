package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroEmpleado2 extends AppCompatActivity {


    EditText nombreEmpleado;
    EditText localidadEmpleado;
    EditText oficioEmpleado;
    Button btnRegistrar;
    String email;
    String nombre;
    FirebaseUser user;
    String uid;

    DatabaseReference databaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empleado2);

        nombreEmpleado=findViewById(R.id.etNombreEmpleado);
        localidadEmpleado=findViewById(R.id.etLocalidadRegistro);
        oficioEmpleado=findViewById(R.id.etOficioRegistro);
        btnRegistrar=findViewById(R.id.btnRegistrarEmpleado2);
        email=getIntent().getExtras().getString("CLAVE CORREO");
        user=FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            uid = user.getUid();

        } else {
            //Aqui ira algo
        }
        databaseRef=FirebaseDatabase.getInstance().getReference().child("Empleados");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre= nombreEmpleado.getText().toString();
                String localidad= localidadEmpleado.getText().toString();
                String oficio=oficioEmpleado.getText().toString();

                Map<String, Object> datosUsuario= new HashMap<>();
                datosUsuario.put("nombre",nombre);
                datosUsuario.put("correo", email);
                datosUsuario.put("localidad", localidad);
                datosUsuario.put("oficio", oficio);
                datosUsuario.put("tipo","empleado");

                databaseRef.child(uid).setValue(datosUsuario);
                //VQuwuF9uPxQDQDSPSBDBSDMRAXv2
                Toast.makeText(RegistroEmpleado2.this,"Su usuario ha sido registrado",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(RegistroEmpleado2.this, Contratos.class);
                startActivity(intent2);
            }
        });




    }
}
