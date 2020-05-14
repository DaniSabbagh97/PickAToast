package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText etContraseña;
    EditText etCorreo;
    Button btnLogin;

    private String password="";
    private String correo="";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        etContraseña=findViewById(R.id.etPasswordLogin);
        etCorreo=findViewById(R.id.etCorreoLogin);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password=etContraseña.getText().toString();
                correo=etCorreo.getText().toString();

                if(!correo.isEmpty()&& !password.isEmpty()){
                    logIn();
                }
                else{
                    Toast.makeText(Login.this,"Comprueba que todos los campos esten rellenados", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void logIn(){
        mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent2=new Intent(Login.this, EditarEmpleador.class);
                    startActivity(intent2);
                    Toast.makeText(Login.this,"Todo good", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Login.this,"Fallo wey", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
