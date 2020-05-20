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

import com.example.pickatoast.pickatoast.Pojos.Empleador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePasswordEmpleador extends AppCompatActivity {

    EditText currentPassword;
    EditText newPassword;
    EditText newPassword2;
    Button btnUpdate;

    DatabaseReference databaseRef;
    FirebaseUser user;
    String uid;

    Empleador firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPassword=findViewById(R.id.etCurrentPasswordEmpleador);
        newPassword=findViewById(R.id.etNuevaPasswordEmpleador);
        newPassword2=findViewById(R.id.etNuevaPasswordEmpleador2);
        btnUpdate=findViewById(R.id.btnUpdatePassword);

        user= FirebaseAuth.getInstance().getCurrentUser();
        databaseRef= FirebaseDatabase.getInstance().getReference();



        if (user != null) {

            uid = user.getUid();

        } else {}

        databaseRef.child("Empleador").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot snapshot: dataSnapshot.getChildren()){
                    databaseRef.child("Empleador").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            firebase=snapshot.getValue(Empleador.class);
                            Log.e("Uid: ", ""+uid);
                            Log.e("Firebase: ", ""+firebase.getId());
                            if(uid.equals(firebase.getId())){

                               btnUpdate.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       if(currentPassword.getText().toString().equals(firebase.getContraseñaEmpleador())){
                                           if(newPassword.getText().toString().equals(newPassword2.getText().toString())){
                                               user.updatePassword(newPassword.getText().toString());
                                               Intent i= new Intent(ChangePasswordEmpleador.this, ChangePasswordEmpleador.class);
                                               startActivity(i);
                                               Toast.makeText(ChangePasswordEmpleador.this,"Contraseña Guardada",Toast.LENGTH_LONG).show();
                                           }else{
                                               Toast.makeText(ChangePasswordEmpleador.this,"La nueva contraseña debe coincidir",Toast.LENGTH_LONG).show();
                                           }
                                       }else{
                                           Toast.makeText(ChangePasswordEmpleador.this,"La contraseña actual es incorrecta",Toast.LENGTH_LONG).show();
                                       }
                                   }
                               });
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
}
