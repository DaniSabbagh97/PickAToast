package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroEmpleador1 extends AppCompatActivity {

    EditText etCorreo;
    EditText etContraseña;
    EditText etVerificarContraseña;
    Button btnRegistrarEmpleado;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    String email;
    String password;
    String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empleador1);

        etCorreo=findViewById(R.id.etCorreoEmpleador);
        etContraseña=findViewById(R.id.etContraseñaEmpleador);
        etVerificarContraseña=findViewById(R.id.etVerContraseñaEmpelador);
        btnRegistrarEmpleado=findViewById(R.id.btnSubirEmpelador);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child( "Users" );
        mAuth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog( this );

        firebaseAuth = FirebaseAuth.getInstance();
        btnRegistrarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etCorreo.getText().toString().trim();

                password = etContraseña.getText().toString().trim();
                password2 = etVerificarContraseña.getText().toString().trim();


                if(email.isEmpty()) {
                    Toast.makeText( RegistroEmpleador1.this, "Debe introducirse el email y la password", Toast.LENGTH_SHORT ).show();
                }else if (password.length() < 6) {
                    Toast.makeText( RegistroEmpleador1.this, "La password debe contener al menos 6 caracteres", Toast.LENGTH_SHORT ).show();
                } else if (!password.equals( password2 )) {
                    Toast.makeText( RegistroEmpleador1.this, "Las contraseñas deben de coincidir", Toast.LENGTH_SHORT ).show();
                } /*else if(!email.equals(email2)){
                    Toast.makeText( RegristroEmpleado.this, "Los correos deben de coincidir", Toast.LENGTH_SHORT ).show();
                }*/
                else{
                    progressDialog.setMessage( "Realizando registro en linea. . ." );
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegistroEmpleador1.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //checking if success
                                    if(task.isSuccessful()){
                                        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()){
                                                    user=mAuth.getCurrentUser();
                                                    String clave=user.getUid();
                                                    Intent intent2=new Intent(RegistroEmpleador1.this, RegistroEmpleador2.class);
                                                    intent2.putExtra("CLAVE CORREO EMPLEADOR", email);
                                                    intent2.putExtra("CLAVE ID", clave);
                                                    intent2.putExtra("CLAVE CONTRASEÑA", password);
                                                    startActivity(intent2);
                                                        Toast.makeText(RegistroEmpleador1.this,"Todo good", Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(RegistroEmpleador1.this,"Fallo wey", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                        Toast.makeText(RegistroEmpleador1.this,"Se ha registrado el usuario",Toast.LENGTH_LONG).show();
                                    }else{

                                        Toast.makeText(RegistroEmpleador1.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                                        System.out.println(task.getException());
                                    }
                                    progressDialog.dismiss();
                                }
                            });


                }


            }
        });
    }
}
