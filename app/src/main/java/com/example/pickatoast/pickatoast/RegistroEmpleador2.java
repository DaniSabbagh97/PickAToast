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

public class RegistroEmpleador2 extends AppCompatActivity {


    EditText etNombreEmpresa;
    EditText etLocalidadEmpresa;
    EditText etDireccionEmpresa;
    Button btnSubirDatosEmpleador;

    String email;
    String nombre;
    String clave;
    String password;
    FirebaseUser user;
    String uid;

    DatabaseReference databaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empleador2);

        etNombreEmpresa=findViewById(R.id.etNombreEmpresa);
        etLocalidadEmpresa=findViewById(R.id.etLocalidadEmpresa);
        etDireccionEmpresa=findViewById(R.id.etDireccionEmpresa);
        btnSubirDatosEmpleador=findViewById(R.id.btnSubidaEmpleadorDatos);

        email=getIntent().getExtras().getString("CLAVE CORREO EMPLEADOR");
        clave=getIntent().getExtras().getString("CLAVE ID");
        password=getIntent().getExtras().getString("CLAVE CONTRASEÑA");
        user= FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            uid = user.getUid();

        } else {
            //Aqui ira algo
        }

        databaseRef= FirebaseDatabase.getInstance().getReference().child("Empleador");

        btnSubirDatosEmpleador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre= etNombreEmpresa.getText().toString();
                String localidad= etLocalidadEmpresa.getText().toString();
                String direccion=etDireccionEmpresa.getText().toString();

                Map<String, Object> datosEmpresa= new HashMap<>();

                datosEmpresa.put("nombreEmpresa",nombre);
                datosEmpresa.put("correoEmpleador", email);
                datosEmpresa.put("ciudadEmpleador", localidad);
                datosEmpresa.put("direccionEmpleador", direccion);
                datosEmpresa.put("id",uid);
                datosEmpresa.put("contraseñaEmpleador", password);
                datosEmpresa.put("esEmpleador", true);
                datosEmpresa.put("imagenLogoEmpresaURL","");

                databaseRef.child(uid).setValue(datosEmpresa);
                //VQuwuF9uPxQDQDSPSBDBSDMRAXv2
                Toast.makeText(RegistroEmpleador2.this,"Su usuario ha sido registrado",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(RegistroEmpleador2.this, EditarEmpleador.class);
                startActivity(intent2);
            }
        });
    }
}
