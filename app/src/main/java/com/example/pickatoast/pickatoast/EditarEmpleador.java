package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pickatoast.pickatoast.Pojos.Empleador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditarEmpleador extends AppCompatActivity {

    EditText nombreEmpleadorEd;
    EditText direccionEmpleadorEd;
    EditText localidadEmpleadorEd;

    Button btnCambiarImagenEmpelador;
    Button btnEditar;
    Button btnGuardar;
    Button btnCambiarContraseña;

    ImageView imagenPerfilEmpelador;

    DatabaseReference databaseRef;
    FirebaseUser user;
    String uid;
    Empleador firebase;
    Empleador empleador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleador);

        nombreEmpleadorEd=findViewById(R.id.etNombreEmpleadorEd);
        direccionEmpleadorEd=findViewById(R.id.etDireccionEmpleadorEd);
        localidadEmpleadorEd=findViewById(R.id.etDLocalidadEmpleadorEd);

        btnCambiarImagenEmpelador=findViewById(R.id.btnCambiarImagenEmpleador);
        btnEditar=findViewById(R.id.btnEditarEmpleador);
        btnGuardar=findViewById(R.id.btnGuardarEmpelador);
        btnCambiarContraseña=findViewById(R.id.btnBorrarPerfil);

        imagenPerfilEmpelador=findViewById(R.id.imgPerfilEmpleadorED);

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
                                nombreEmpleadorEd.setText(firebase.getNombreEmpresa());
                                direccionEmpleadorEd.setText(firebase.getDireccionEmpleador());
                                localidadEmpleadorEd.setText(firebase.getCiudadEmpleador());
                                Log.e("Uid: ", ""+firebase.getNombreEmpresa());
                                Log.e("direccion: ", ""+firebase.getDireccionEmpleador());
                                Log.e("Uid: ", ""+uid);

                                empleador=firebase;
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

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> datosEmpresa= new HashMap<>();


                datosEmpresa.put("nombreEmpresa",nombreEmpleadorEd.getText().toString());
                datosEmpresa.put("correoEmpleador", empleador.getCorreoEmpleador());
                datosEmpresa.put("ciudadEmpleador", localidadEmpleadorEd.getText().toString());
                datosEmpresa.put("direccionEmpleador", direccionEmpleadorEd.getText().toString());
                datosEmpresa.put("id",uid);

                databaseRef.child("Empleador").child(uid).setValue(datosEmpresa);
            }
        });




    }
}
