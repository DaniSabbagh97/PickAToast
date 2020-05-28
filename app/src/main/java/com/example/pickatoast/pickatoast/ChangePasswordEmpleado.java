package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Pojos.Empleado;
import com.example.pickatoast.pickatoast.Services.ChangeWindowService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePasswordEmpleado extends AppCompatActivity {

    EditText newPassword,newPassword2,currentPassword;
    Button guardar;

    DatabaseReference databaseRef;
    FirebaseUser user;
    String uid;

    Empleado firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_empleado);

        newPassword=findViewById(R.id.etNuevaContraseñaEmpelado);
        newPassword2=findViewById(R.id.etNuevaContraseñaEmpelado2);
        currentPassword=findViewById(R.id.etCurrentPasswordEmpleado);
        guardar=findViewById(R.id.btnChangePasswordEmpleado);

        user= FirebaseAuth.getInstance().getCurrentUser();
        databaseRef= FirebaseDatabase.getInstance().getReference();


        if (user != null) {

            uid = user.getUid();

        } else {}

        databaseRef.child("Empleados").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot snapshot: dataSnapshot.getChildren()){
                    databaseRef.child("Empleados").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            firebase=snapshot.getValue(Empleado.class);
                            Log.e("Uid: ", ""+uid);
                            Log.e("Firebase: ", ""+firebase.getIdEmpleado());
                            if(uid.equals(firebase.getIdEmpleado())){

                                guardar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(currentPassword.getText().toString().equals(firebase.getContraseñaEmpleado())){
                                            if(newPassword.getText().toString().equals(newPassword2.getText().toString())){
                                                user.updatePassword(newPassword.getText().toString());
                                                Intent i= new Intent(ChangePasswordEmpleado.this, EmployeeProfile.class);
                                                startActivity(i);
                                                Toast.makeText(ChangePasswordEmpleado.this,"Contraseña Guardada",Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(ChangePasswordEmpleado.this,"La nueva contraseña debe coincidir",Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(ChangePasswordEmpleado.this,"La contraseña actual es incorrecta",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                //Toast.makeText(ChangePasswordEmpleado.this,"Mal wey",Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ChangeWindowService.jumpTo(this,EmployeeProfile.class);
    }
}
