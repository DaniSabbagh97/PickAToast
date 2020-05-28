package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Pojos.Empleado;
import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.example.pickatoast.pickatoast.Services.ChangeWindowService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeProfile extends AppCompatActivity {

    EditText etNombreEmpleado, etciudadEmpleado, etDireccionEmpleado, etOficioEmpleado;
    Button btnCambiarPSW, btnGuardar, btnModificarFoto;
    //Button btnCV;
    CircleImageView cvPerfilEmpleado;

    private FirebaseUser fb = FirebaseAuth.getInstance().getCurrentUser();
    private String idUserConnected = fb.getUid();

    private StorageReference mStorageRef;

    static Empleado[] currentEmpleado = new Empleado[1];
    static Empleado[] currentEmpleadoModificacion = new Empleado[1];
    Empleado e;

    private String nombre, correo, idNoUso, contrasenia, ciudad, direccion, oficio, foto;
    private String nombreCambiado, ciudadCambiado, direccionCambiado, oficioCambiado, fotoCambiado;

    int SELEC_IMAGEN = 200;
    //int SELEC_CV = 100;
    Uri imagenUri;
    //Uri cvUri;
    private String url;
    //private String urlCV;
    
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleado);
        
        etNombreEmpleado = (EditText) findViewById(R.id.etNombreEmpleadoPerfil);
        etciudadEmpleado = (EditText) findViewById(R.id.etciudadEmpleadoPerfil);
        etDireccionEmpleado = (EditText) findViewById(R.id.etDireccionEmpleadoPerfil);
        etOficioEmpleado = (EditText) findViewById(R.id.etOficioEmpleadoPerfil);
        
        cvPerfilEmpleado = (CircleImageView) findViewById(R.id.imgImagenPerfilEmpleado); 
        
        //btnCV = (Button) findViewById(R.id.btnCurriculumVitae);
        btnCambiarPSW = (Button) findViewById(R.id.btnCambiarContraseñaEmpleado);
        btnGuardar = (Button) findViewById(R.id.btnGuardarEmpleado);//todo ???
        btnModificarFoto = (Button) findViewById(R.id.btnCambiarImagen);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarDatosPerfil();
            }
        });

        /*btnCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/pdf");
                startActivityForResult(intent, SELEC_CV);


            }
        });*/

        btnModificarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catchImage();
            }
        });
        btnCambiarPSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeWindowService.jumpTo(EmployeeProfile.this, ChangePasswordEmpleado.class);
            }
        });






        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("Empleados").child(idUserConnected).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                currentEmpleado[0] = dataSnapshot.getValue(Empleado.class);


                nombre = currentEmpleado[0].getNombreEmpleado();
                ciudad = currentEmpleado[0].getCiudadEmpleado();
                direccion = currentEmpleado[0].getDireccionEmpleado();
                oficio = currentEmpleado[0].getOficioEmpleado();
                foto = currentEmpleado[0].getImagenPerfilEmpleadoURL();

                if(foto == null ){
                    Toast.makeText(EmployeeProfile.this, "Es indispensable que cambie su foto de perfil para modificar sus datos", Toast.LENGTH_LONG).show();
                    //btnGuardar.setEnabled(false);
                }else {
                    foto = currentEmpleado[0].getImagenPerfilEmpleadoURL();
                }

                etNombreEmpleado.setText(nombre);
                etciudadEmpleado.setText(ciudad);
                etDireccionEmpleado.setText(direccion);
                etOficioEmpleado.setText(oficio);
                if(foto == null || foto.equals("")){
                    Toast.makeText(EmployeeProfile.this, "Es indispensable que cambie su foto de perfil para modificar sus datos", Toast.LENGTH_LONG).show();
                    btnGuardar.setEnabled(false);
                }else{
                    Picasso.get().load(foto).into(cvPerfilEmpleado);
                    //btnGuardar.setEnabled(true);
                }

               


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        
        
        
        
        
    }

    private void editarDatosPerfil() {

        nombreCambiado = etNombreEmpleado.getText().toString();
        ciudadCambiado = etciudadEmpleado.getText().toString();
        direccionCambiado = etDireccionEmpleado.getText().toString();
        oficioCambiado = etOficioEmpleado.getText().toString();



        FirebaseDatabase db = FirebaseDatabase.getInstance();

        db.getReference().child("Empleados").child(idUserConnected).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                currentEmpleadoModificacion[0] = dataSnapshot.getValue(Empleado.class);


                contrasenia = currentEmpleadoModificacion[0].getContraseñaEmpleado();
                correo = currentEmpleadoModificacion[0].getCorreoEmpleado();
                idNoUso = currentEmpleadoModificacion[0].getIdEmpleado();

                e = new Empleado(nombreCambiado, ciudadCambiado, direccionCambiado, oficioCambiado, url, idNoUso, contrasenia, correo);



                dataSnapshot.getRef().setValue(e);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void catchImage() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, SELEC_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SELEC_IMAGEN) {
            imagenUri = data.getData();
            cvPerfilEmpleado.setImageURI(imagenUri);

            final StorageReference filepath = mStorageRef.child("Photos").child(imagenUri.getLastPathSegment());

            filepath.putFile(imagenUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {


                            url = String.valueOf(uri);
                            btnGuardar.setEnabled(true);

                        }
                    });

                }
            });
        } /*else if(resultCode == RESULT_OK && requestCode == SELEC_CV) {
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

        }*/
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ChangeWindowService.jumpTo(this,EventsList.class);
    }
}
