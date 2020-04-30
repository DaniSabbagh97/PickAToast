package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Pojos.Empleado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegristroEmpleado extends AppCompatActivity {


    TextView tvCorreo;
    EditText etContraseña;
    EditText etVerificarContraseña;
    Button btnRegistrarEmpleado;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DatabaseReference mDatabaseRef;

    String email;
    String email2;
    String password;
    String password2;
    String nombre;

    Empleado empleado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regristro_empleado);


        tvCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        etVerificarContraseña = findViewById(R.id.etVerificarContraseña);
        btnRegistrarEmpleado = findViewById(R.id.btnRegistrarEmpleado);




        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child( "Users" );

        progressDialog = new ProgressDialog( this );

        firebaseAuth = FirebaseAuth.getInstance();
        btnRegistrarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = tvCorreo.getText().toString().trim();

                password = etContraseña.getText().toString().trim();
                password2 = etVerificarContraseña.getText().toString().trim();


                if(email.isEmpty()) {
                    Toast.makeText( RegristroEmpleado.this, "Debe introducirse el email y la password", Toast.LENGTH_SHORT ).show();
                }else if (password.length() < 6) {
                    Toast.makeText( RegristroEmpleado.this, "La password debe contener al menos 6 caracteres", Toast.LENGTH_SHORT ).show();
                } else if (!password.equals( password2 )) {
                    Toast.makeText( RegristroEmpleado.this, "Las contraseñas deben de coincidir", Toast.LENGTH_SHORT ).show();
                } /*else if(!email.equals(email2)){
                    Toast.makeText( RegristroEmpleado.this, "Los correos deben de coincidir", Toast.LENGTH_SHORT ).show();
                }*/
                else{
                    progressDialog.setMessage( "Realizando registro en linea. . ." );
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegristroEmpleado.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //checking if success
                                    if(task.isSuccessful()){

                                        Toast.makeText(RegristroEmpleado.this,"Se ha registrado el usuario",Toast.LENGTH_LONG).show();
                                    }else{

                                        Toast.makeText(RegristroEmpleado.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                                        System.out.println(task.getException());
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                    Intent intent=new Intent(RegristroEmpleado.this, RegistroEmpleado2.class);
                    intent.putExtra("CLAVE CORREO", email);
                    startActivity(intent);

                }


            }
        });


    }


    }

