package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ViewSingleEvent extends AppCompatActivity {

    private TextView tvNombreOferta, tvLocalizacionOferta, tvDuracionOferta, tvRestauranteOferta;
    private ImageView ivOferta;
    private Button btnApuntarseOferta;

    String nombre, restaurante, foto, id, duracion, localizacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_event);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }else {
            id = bundle.getString("idOferta");
            nombre = bundle.getString("nombre");
            restaurante = bundle.getString("restaurante");
            foto = bundle.getString("foto");
            duracion = bundle.getString("duracion");
            localizacion = bundle.getString("localizacion");

        }
        tvNombreOferta = (TextView) findViewById(R.id.tvNombreOferta);
        tvLocalizacionOferta = (TextView) findViewById(R.id.tvLocalizacionOferta);
        tvDuracionOferta = (TextView) findViewById(R.id.tvDuracionOferta);
        tvRestauranteOferta = (TextView) findViewById(R.id.tvRestauranteOferta);

        ivOferta = (ImageView) findViewById(R.id.ivImageOferta);

        btnApuntarseOferta = (Button) findViewById(R.id.btnApuntarseOferta);

        cargarDatos();

        btnApuntarseOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO APUNTARSE A EVENTO
                Toast.makeText(ViewSingleEvent.this, "Te Has Apuntado a Este Evento, Gracias!!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void cargarDatos() {

        tvNombreOferta.setText(nombre);
        tvRestauranteOferta.setText(restaurante);
        tvDuracionOferta.setText(duracion);
        tvLocalizacionOferta.setText(localizacion);
        Picasso.get().load(foto).into(ivOferta);

    }

}
