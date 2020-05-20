package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

import com.example.pickatoast.pickatoast.Adapters.CardListEventsAdapter;
import com.example.pickatoast.pickatoast.Fragments.LeftMainMenu;
import com.example.pickatoast.pickatoast.Fragments.TopMainMenu;
import com.example.pickatoast.pickatoast.Interfaces.TopMainMenuImpl;
import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.example.pickatoast.pickatoast.Services.MenuButtonsHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsList extends AppCompatActivity implements TopMainMenuImpl {
    //------Variables de los fragments menu------
    FrameLayout leftMenu;
    String leftFragmentName = LeftMainMenu.class.getSimpleName();
    String topFragmentName = TopMainMenu.class.getSimpleName();
    MenuButtonsHandler buttonsHandler;

    RecyclerView rv;
    List<OfertaEmpleador> _ofertas;
    CardListEventsAdapter adapter;
    Button btnCrearEvento;
    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_events_list );

        btnCrearEvento = (Button) findViewById(R.id.btnCrearEvento);

        btnCrearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EventsList.this, CreateEvent.class);
                startActivity(i);
            }
        });

        //fab = findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //TODO ACCTION FLOAT BUTTON
            }
        });*/

        //------Configuración de los Fragments menu------
        buttonsHandler= new MenuButtonsHandler(this);
        leftMenu=findViewById(R.id.leftMenu);

        //------Codigo y funcionalidad------
        rv = findViewById( R.id.rvEvents );
        rv.setLayoutManager( new LinearLayoutManager( this ) );

        _ofertas = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new CardListEventsAdapter(_ofertas, this);

        rv.setAdapter( adapter );

        database.getReference().child("Ofertas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                _ofertas.removeAll(_ofertas);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    OfertaEmpleador ofertaEmpleador = snapshot.getValue(OfertaEmpleador.class);
                    _ofertas.add(ofertaEmpleador);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        adapter.notifyDataSetChanged();

        rv.setHasFixedSize( true );
    }

    //Gestión de los botones de los fragments
    @Override
    public void menu(int buttonClicked,String fragmentName) {

        if(fragmentName.equals(leftFragmentName)){
            buttonsHandler.onLeftMenuButtonClicked(buttonClicked,leftMenu,leftFragmentName);
        }
        else if(fragmentName.equals(topFragmentName)){
            buttonsHandler.onTopMenuButtonClicked(buttonClicked,leftMenu,topFragmentName);
        }
    }
}
