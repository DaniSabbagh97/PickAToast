package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pickatoast.pickatoast.Pojos.Empleador;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EditarEmpleador extends AppCompatActivity {

    EditText nombreEmpleadorEd;
    EditText direccionEmpleadorEd;
    EditText localidadEmpleadorEd;

    Button btnCambiarImagenEmpelador;
    Button btnBorrarPerfil;
    Button btnGuardar;
    Button btnCambiarContraseña;
    Button btnHacerFoto;

    ImageView imagenPerfilEmpelador;

    DatabaseReference databaseRef;
    FirebaseUser user;
    String uid;
    Empleador firebase;
    Empleador empleador;
    FirebaseAuth auth;
    private StorageReference mStorageRef;

    Uri imagenUri;
    private String url;

    int TOMAR_FOTO = 100;
    int SELEC_IMAGEN = 200;

    String CARPETA_RAIZ = "MisFotosApp";
    String CARPETAS_IMAGENES = "imagenes";
    String RUTA_IMAGEN = CARPETA_RAIZ + CARPETAS_IMAGENES;
    String path;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleador);

        nombreEmpleadorEd=findViewById(R.id.etNombreEmpleadorEd);
        direccionEmpleadorEd=findViewById(R.id.etDireccionEmpleadorEd);
        localidadEmpleadorEd=findViewById(R.id.etDLocalidadEmpleadorEd);

        btnCambiarImagenEmpelador=findViewById(R.id.btnCambiarImagenEmpleador);
        btnCambiarContraseña=findViewById(R.id.btnContraseñaEmpleador);
        btnGuardar=findViewById(R.id.btnGuardarEmpelador);
        btnBorrarPerfil=findViewById(R.id.btnBorrarPerfil);
        btnHacerFoto=findViewById(R.id.btnCambiarImagenEmpleador2);

        imagenPerfilEmpelador=findViewById(R.id.imgPerfilEmpleadorED);

        user= FirebaseAuth.getInstance().getCurrentUser();
        databaseRef= FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        url="";

        if(ContextCompat.checkSelfPermission(EditarEmpleador.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditarEmpleador.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }



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
                                image=firebase.getImagenLogoEmpresaURL();
                                if(firebase.getImagenLogoEmpresaURL().equals("")){

                                }else{
                                    Picasso.get().load(image).into(imagenPerfilEmpelador);
                                }




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



        btnCambiarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(EditarEmpleador.this, ChangePasswordEmpleador.class);
                startActivity(i);

            }
        });

        btnCambiarImagenEmpelador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(galeria, SELEC_IMAGEN);

            }
        });

        btnHacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreImagen = "";
                File fileImagen = new File( Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
                boolean isCreada = fileImagen.exists();

                if(isCreada == false) {
                    isCreada = fileImagen.mkdirs();
                }

                if(isCreada == true) {
                    nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
                }

                path = Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;
                File imagen = new File(path);

                Intent intent = null;
                intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    String authorities = EditarEmpleador.this.getPackageName()+".provider";
                    Uri imageUri = FileProvider.getUriForFile(EditarEmpleador.this, authorities, imagen);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                } else {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
                }

                startActivityForResult(intent, TOMAR_FOTO);
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
                datosEmpresa.put("imagenLogoEmpresaURL",url);
                datosEmpresa.put("esEmpleador",true);
                datosEmpresa.put("contraseñaEmpleador",empleador.getContraseñaEmpleador());


                databaseRef.child("Empleador").child(uid).setValue(datosEmpresa);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == SELEC_IMAGEN) {
            imagenUri = data.getData();
            imagenPerfilEmpelador.setImageURI(imagenUri);

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
        } else if(resultCode == RESULT_OK && requestCode == TOMAR_FOTO) {
            MediaScannerConnection.scanFile(EditarEmpleador.this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) {

                }
            });

            Bitmap bitmap = BitmapFactory.decodeFile(path);
            imagenPerfilEmpelador.setImageBitmap(bitmap);
        }
    }
}
