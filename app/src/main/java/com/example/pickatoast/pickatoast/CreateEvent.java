package com.example.pickatoast.pickatoast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class CreateEvent extends AppCompatActivity {
    private EditText etNombre, etRestaurante, etLocalizacion, etDuracion;
    private Button btnCrearOferta;
    private ImageView imvFotoOferta;

    private String nombreOferta;
    private String descripcionOferta;
    private String nombreRestaurante;
    private String localizacionRestaurante;
    private String duracionOferta;
    private String ImageOferta;

    private OfertaEmpleador oferta;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;
   //
    private FirebaseUser user;

    //private String clave;

    Uri imagenUri;

    int TOMAR_FOTO = 100;
    int SELEC_IMAGEN = 200;

    String CARPETA_RAIZ = "MisFotosApp";
    String CARPETAS_IMAGENES = "imagenes";
    String RUTA_IMAGEN = CARPETA_RAIZ + CARPETAS_IMAGENES;
    String path;

    int photoHeigth, photoWidth;
    private String url;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        /*Bundle bun = getIntent().getExtras();
        if (bun == null) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }else{
            clave = bun.getString("clave");
        }*/

        etNombre = (EditText) findViewById(R.id.etNombreOferta);
        etRestaurante = (EditText) findViewById(R.id.etRestaurante);
        etLocalizacion = (EditText) findViewById(R.id.etLocalizacionOferta);
        etDuracion = (EditText) findViewById(R.id.etDuracion);

        btnCrearOferta = (Button) findViewById(R.id.btnCrearOferta);

        imvFotoOferta = (ImageView) findViewById(R.id.imvOferta);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Ofertas");
        mStorageRef = FirebaseStorage.getInstance().getReference();

        btnCrearOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFields();
            }
        });

        imvFotoOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup();
            }
        });


        if (ContextCompat.checkSelfPermission(CreateEvent.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreateEvent.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }


    }

    private void saveData() {

        FirebaseUser fb = FirebaseAuth.getInstance().getCurrentUser();
        String clave = fb.getUid();
        System.out.println(fb+"hollllllllaaaaaa");
        System.out.println(clave+" eeeooooo");

        //todo inicilizar el get instance

        oferta = new OfertaEmpleador(clave, nombreOferta, descripcionOferta, nombreRestaurante, localizacionRestaurante, duracionOferta, url, true);
        mDatabaseRef.child(clave).setValue(oferta);

        Intent i = new Intent(CreateEvent.this, EventsList.class);
        startActivity(i);
        Toast.makeText(CreateEvent.this, "Oferta Creada con Éxito", Toast.LENGTH_LONG).show();


    }

    private void checkFields() {

        nombreOferta = etNombre.getText().toString().trim();
        nombreRestaurante = etRestaurante.getText().toString().trim();
        localizacionRestaurante = etLocalizacion.getText().toString().trim();
        duracionOferta = etRestaurante.getText().toString().trim();

        //TODO RECOGER IMAGEN

        if (nombreOferta.isEmpty() || nombreRestaurante.isEmpty()
                || localizacionRestaurante.isEmpty() || duracionOferta.isEmpty()) {
            Toast.makeText(CreateEvent.this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        } else {
            saveData();
        }


    }

    private void catchImage() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, SELEC_IMAGEN);
    }


    private void takePhoto() {

        String nombreImagen = "";
        File fileImagen = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada = fileImagen.exists();

        if (isCreada == false) {
            isCreada = fileImagen.mkdirs();
        }

        if (isCreada == true) {
            nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
        }

        path = Environment.getExternalStorageDirectory() + File.separator + RUTA_IMAGEN + File.separator + nombreImagen;
        File imagen = new File(path);

        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authorities = this.getPackageName() + ".provider";
            Uri imageUri = FileProvider.getUriForFile(this, authorities, imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }

        startActivityForResult(intent, TOMAR_FOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SELEC_IMAGEN) {
            imagenUri = data.getData();
            imvFotoOferta.setImageURI(imagenUri);

            final StorageReference filepath = mStorageRef.child("Photos").child(imagenUri.getLastPathSegment());

            filepath.putFile(imagenUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {


                            url = String.valueOf(uri);
                            btnCrearOferta.setEnabled(true);

                        }
                    });

                }
            });
        } else if (resultCode == RESULT_OK && requestCode == TOMAR_FOTO) {
            MediaScannerConnection.scanFile(CreateEvent.this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) {

                }
            });

            Bitmap bitmap = BitmapFactory.decodeFile(path);
            imvFotoOferta.setImageBitmap(bitmap);
        }
    }

    public void popup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateEvent.this);
        View view;
        view = LayoutInflater.from(CreateEvent.this).inflate(R.layout.customdialog, null);
        TextView title = (TextView) view.findViewById(R.id.title);

        title.setText("Seleccione la imagen");
        builder.setPositiveButton("Galería", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                catchImage();

            }
        });
        builder.setNegativeButton("Cámara", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                takePhoto();
            }
        });
        builder.setView(view);
        builder.show();


    }
}

