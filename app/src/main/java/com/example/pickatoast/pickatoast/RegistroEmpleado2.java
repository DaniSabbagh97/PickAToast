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
    EditText direccion;
    Button btnRegistrar;
    String email;
    String nombre;
    String clave;
    String contraseña;
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
        direccion=findViewById(R.id.etDireccionEmpeladoReg);
        btnRegistrar=findViewById(R.id.btnRegistrarEmpleado2);
        email=getIntent().getExtras().getString("CLAVE CORREO EMP");
        clave=getIntent().getExtras().getString("CLAVE ID EMP");
        contraseña=getIntent().getExtras().getString("CLAVE CONTRASEÑA EMP");
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

                datosUsuario.put("nombreEmpleado",nombre);
                datosUsuario.put("correoEmpleado", email);
                datosUsuario.put("ciudadEmpleado", localidad);
                datosUsuario.put("direccionEmpleado",direccion.getText().toString());
                datosUsuario.put("oficioEmpleado", oficio);
                datosUsuario.put("idEmpleado",clave);
                datosUsuario.put("contraseñaEmpleado", contraseña);
                datosUsuario.put("imagenPerfilEmpleadoURL","");
                datosUsuario.put("subidaCurriculumURL","");
                datosUsuario.put("numeroOfertas", 0);

                databaseRef.child(clave).setValue(datosUsuario);
                //VQuwuF9uPxQDQDSPSBDBSDMRAXv2
                Toast.makeText(RegistroEmpleado2.this,"Su usuario ha sido registrado",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(RegistroEmpleado2.this, EventsList.class);
                intent2.putExtra("clave",uid);
                intent2.putExtra("correo",email);
                intent2.putExtra("password", contraseña);
                startActivity(intent2);
            }
        });




    }
}
