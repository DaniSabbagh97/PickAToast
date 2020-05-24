package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Pojos.Empleado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.example.pickatoast.pickatoast.Services.ChangeWindowService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditarEmpleado extends AppCompatActivity {
    EditText ciudadEmpleado;
    EditText direccionEmpleado;
    EditText trabajoEmpleado;

    Button btnGuardar;
    Button btnCurriculum;
    Button btnCambiarContraseña;
    Button btnCambiarImagen;

    ImageView imgPerfil;

    DatabaseReference databaseRef;
    FirebaseUser user;
    String uid;
    Empleado firebase;
    Empleado empleado;
    FirebaseAuth auth;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;

    Uri imagenUri;
    Uri cvUri;
    private String url;
    private String urlCV;

    int SELEC_CV = 100;
    int SELEC_IMAGEN = 200;

    String CARPETA_RAIZ = "MisFotosApp";
    String CARPETAS_IMAGENES = "imagenes";
    String RUTA_IMAGEN = CARPETA_RAIZ + CARPETAS_IMAGENES;
    String path;
    String image;
    String clave;
    String correo;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleado);

      /*  correo=getIntent().getExtras().getString("correo");
        password=getIntent().getExtras().getString("password");
        System.out.println(correo);
        System.out.println(password);
        mAuth=FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(EditarEmpleado.this,"Iniciando Sesión. . .", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(EditarEmpleado.this,"Usuario y/o Contraseña Incorrectos. . .", Toast.LENGTH_LONG).show();
                }
            }
        });*/


        ciudadEmpleado=findViewById(R.id.etCiudadEmpleado);
        direccionEmpleado=findViewById(R.id.etDireccionEmpleado);
        trabajoEmpleado=findViewById(R.id.etTrabajoEmpelado);
        btnCambiarContraseña=findViewById(R.id.btnCambiarContraseñaEmpleado);
        btnCurriculum=findViewById(R.id.btnCurriculumVitae);
        btnGuardar=findViewById(R.id.btnGuardarEmpleado);
        btnCambiarImagen=findViewById(R.id.btnCambiarImagen);

        imgPerfil=findViewById(R.id.imgImagenPerfilEmpleado);



        user= FirebaseAuth.getInstance().getCurrentUser();
        databaseRef= FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        if (user != null) {

            uid = user.getUid();

        } else {
            Toast.makeText(EditarEmpleado.this,"Su usuario ha sido registrado",Toast.LENGTH_LONG).show();
        }
        System.out.println("--------------------------------"+uid);

        url="";
        urlCV="";

        if(ContextCompat.checkSelfPermission(EditarEmpleado.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditarEmpleado.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }



        databaseRef.child("Empleados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("MMMMMMMMMMMMMMMMMMMM");
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println("NNNNNNNNNNNNNN");
                    firebase = snapshot.getValue(Empleado.class);
                    if(uid.equals(firebase.getIdEmpleado())){
                        System.out.println("BBBBBBBBBBBBBBBBB");
                        empleado=firebase;
                        ciudadEmpleado.setText(firebase.getCiudadEmpleado());
                        direccionEmpleado.setText(firebase.getDireccionEmpleado());
                        trabajoEmpleado.setText(firebase.getOficioEmpleado());
                        if(firebase.getImagenPerfilEmpleadoURL().equals("")){

                        }else{
                            Picasso.get().load(firebase.getImagenPerfilEmpleadoURL()).into(imgPerfil);
                        }
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnCambiarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EditarEmpleado.this,ChangePasswordEmpleado.class);
                startActivity(i);
            }
        });
        btnCambiarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(galeria, SELEC_IMAGEN);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> datosEmpresa= new HashMap<>();


                datosEmpresa.put("nombreEmpresa",empleado.getNombreEmpleado());
                datosEmpresa.put("correoEmpleado", empleado.getCorreoEmpleado());
                datosEmpresa.put("ciudadEmpleado", ciudadEmpleado.getText().toString());
                datosEmpresa.put("direccionEmpleado", direccionEmpleado.getText().toString());
                datosEmpresa.put("id",uid);
                datosEmpresa.put("imagenPerfilEmpleadoURL",url);
                datosEmpresa.put("esEmpleador",false);
                datosEmpresa.put("contraseñaEmpleado",empleado.getContraseñaEmpleado());
                datosEmpresa.put("subidaCurriculumURL",urlCV);
                datosEmpresa.put("oficioEmpleado",trabajoEmpleado.getText().toString());


                databaseRef.child("Empleados").child(uid).setValue(datosEmpresa);

                ChangeWindowService.jumpTo(EditarEmpleado.this,EventsList.class);
            }
        });
        btnCurriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/pdf");
                startActivityForResult(intent, SELEC_CV);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == SELEC_IMAGEN) {
            imagenUri = data.getData();
            imgPerfil.setImageURI(imagenUri);

            final StorageReference filepath = mStorageRef.child("Photos").child(imagenUri.getLastPathSegment());

            filepath.putFile(imagenUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            url = String.valueOf(uri);

                        }
                    });

                }
            });
        } else if(resultCode == RESULT_OK && requestCode == SELEC_CV) {
            cvUri=data.getData();
            final StorageReference filepathCV = mStorageRef.child("CV").child(cvUri.getLastPathSegment());
            filepathCV.putFile(cvUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepathCV.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            urlCV = String.valueOf(uri);

                        }
                    });

                }
            });

        }
    }
}
