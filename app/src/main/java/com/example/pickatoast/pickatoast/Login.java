package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Services.ChangeWindowService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText etContraseña;
    EditText etCorreo;
    Button btnLogin;
    TextView tvRegister;

    private String password="";
    private String correo="";
    private FirebaseUser user;



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        etContraseña=findViewById(R.id.etPasswordLogin);
        etCorreo=findViewById(R.id.etCorreoLogin);
        btnLogin=findViewById(R.id.btnLogin);

        tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeWindowService.jumpTo(Login.this,EmployeeProfile.class);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password=etContraseña.getText().toString();
                correo=etCorreo.getText().toString();

                if(!correo.isEmpty()&& !password.isEmpty()){
                    logIn();
                }
                else{
                    Toast.makeText(Login.this,"Comprueba que los campos no esten vacios", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void logIn(){
        mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    user = mAuth.getCurrentUser();
                    String clave = user.getUid();
                    Intent intent2=new Intent(Login.this, EventsList.class);
                    intent2.putExtra("clave", clave);
                    intent2.putExtra("correo", correo);
                    intent2.putExtra("password", password);
                    startActivity(intent2);
                    Toast.makeText(Login.this,"Iniciando Sesión. . .", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Login.this,"Usuario y/o Contraseña Incorrectos. . .", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
