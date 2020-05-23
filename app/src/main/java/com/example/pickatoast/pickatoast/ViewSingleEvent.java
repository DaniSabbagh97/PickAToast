package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
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
                Toast.makeText(ViewSingleEvent.this, "Te Has Apuntado a Este Evento, Gracias!!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }



}