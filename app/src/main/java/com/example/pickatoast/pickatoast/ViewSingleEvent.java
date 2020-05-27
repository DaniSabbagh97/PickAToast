package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Pojos.Empleado;
import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.example.pickatoast.pickatoast.Services.ChangeWindowService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;



public class ViewSingleEvent extends AppCompatActivity {

    private TextView tvNombreOferta, tvLocalizacionOferta, tvDuracionOferta, tvRestauranteOferta;
    private ImageView ivOferta;
    private Button btnApuntarseOferta;

    String nombre, restaurante, foto, id, duracion, localizacion;

    static OfertaEmpleador[] currentOferta = new OfertaEmpleador[1];
    static Empleado[] currentEmpleado = new Empleado[1];
    Empleado e;
    OfertaEmpleador oe = new OfertaEmpleador();
    private FirebaseUser fb = FirebaseAuth.getInstance().getCurrentUser();
    private String idUserConnected = fb.getUid();
    private int numOfertas;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_event);


        tvNombreOferta = (TextView) findViewById(R.id.tvNombreOferta);
        tvLocalizacionOferta = (TextView) findViewById(R.id.tvLocalizacionOferta);
        tvDuracionOferta = (TextView) findViewById(R.id.tvDuracionOferta);
        tvRestauranteOferta = (TextView) findViewById(R.id.tvRestauranteOferta);

        ivOferta = (ImageView) findViewById(R.id.ivImagenOfertaIndividual);

        btnApuntarseOferta = (Button) findViewById(R.id.btnApuntarseOferta);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }else {

            id = bundle.getString("idUser");



        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("Ofertas").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                currentOferta[0] = dataSnapshot.getValue(OfertaEmpleador.class);

                oe = currentOferta[0];
                nombre = currentOferta[0].getNombreOferta();
                restaurante = currentOferta[0].getRestaurante();
                duracion = currentOferta[0].getDuracionContrato();
                localizacion = currentOferta[0].getLocalizacionOferta();
                foto = currentOferta[0].getLinkImagen();


                tvNombreOferta.setText(nombre);
                tvRestauranteOferta.setText(restaurante);
                tvDuracionOferta.setText(duracion);
                tvLocalizacionOferta.setText(localizacion);
                Picasso.get().load(foto).into(ivOferta);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        btnApuntarseOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO APUNTARSE A EVENTO
                popup();
                Toast.makeText(ViewSingleEvent.this, "Te Has Apuntado a Este Evento, Gracias!!", Toast.LENGTH_LONG).show();

            }


        });
    }

    private void popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleEvent.this);
        View view;    view = LayoutInflater.from( ViewSingleEvent.this).inflate(R.layout.customdialogapuntarse, null);

        builder.setPositiveButton("¡Apuntarse!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                apuntarse();

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();


                       }
        });
        builder.setView(view);    builder.show();

    }
    private void apuntarse(){


        FirebaseDatabase db = FirebaseDatabase.getInstance();

        db.getReference().child("Empleados").child(idUserConnected).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                currentEmpleado[0] = dataSnapshot.getValue(Empleado.class);

                //Empleado listEm = [0];

                numOfertas = currentEmpleado[0].getnumeroOfertas();
                System.out.println("HIJODEPUTA");
                System.out.println(numOfertas);


                if (numOfertas < 5){//TODO LO HACE MUCHAS VECES HASTA QUE EL RESULTADO ES 5 Y SE TERMINA SIN SER UN FOR!!!
                    int resultado;
                    resultado = numOfertas +1;
                    System.out.println(resultado);
                    String ciudadEmpleado = currentEmpleado[0].getCiudadEmpleado();
                    String pswEmpleado = currentEmpleado[0].getContraseñaEmpleado();
                    String correoEmpleado = currentEmpleado[0].getCorreoEmpleado();
                    String direccionEmpleado = currentEmpleado[0].getDireccionEmpleado();
                    String idEmpleado = currentEmpleado[0].getIdEmpleado();
                    String imagenPerfilEmpleado = currentEmpleado[0].getImagenPerfilEmpleadoURL();
                    String nombreEmpleado = currentEmpleado[0].getNombreEmpleado();
                    String oficioEmpleado = currentEmpleado[0].getOficioEmpleado();
                    String subidaCV = currentEmpleado[0].getSubidaCurriculumURL();
                    e = new Empleado(idEmpleado, nombreEmpleado, ciudadEmpleado, direccionEmpleado, true, null, imagenPerfilEmpleado, oficioEmpleado, subidaCV, null, pswEmpleado, correoEmpleado, resultado);
                    dataSnapshot.getRef().setValue(e);

                }else if(numOfertas == 5){
                    String msg = "Ya estas suscrito a 5 ofertas!";
                    Toast.makeText(ViewSingleEvent.this, msg, Toast.LENGTH_LONG).show();
                }

            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ChangeWindowService.jumpTo(ViewSingleEvent.this, EventsList.class);

    }


}