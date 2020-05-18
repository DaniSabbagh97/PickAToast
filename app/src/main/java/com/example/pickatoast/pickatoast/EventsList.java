package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Adapters.CardListEventsAdapter;
import com.example.pickatoast.pickatoast.Fragments.LeftMainMenu;
import com.example.pickatoast.pickatoast.Fragments.TopMainMenu;
import com.example.pickatoast.pickatoast.Interfaces.TopMainMenuImpl;
import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.example.pickatoast.pickatoast.Services.MenuButtonsHandler;

import java.util.ArrayList;

public class EventsList extends AppCompatActivity implements TopMainMenuImpl {
    //------Variables de los fragments menu------
    FrameLayout leftMenu;
    String leftFragmentName = LeftMainMenu.class.getSimpleName();
    String topFragmentName = TopMainMenu.class.getSimpleName();
    MenuButtonsHandler buttonsHandler;

    RecyclerView rv;
    ArrayList<OfertaEmpleador> _ofertas;
    CardListEventsAdapter adapter;

    private String xxx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_events_list );

        //------Configuración de los Fragments menu------
        buttonsHandler= new MenuButtonsHandler(this);
        leftMenu=findViewById(R.id.leftMenu);

        //------Codigo y funcionalidad------
        rv = findViewById( R.id.rvEvents );
        rv.setLayoutManager( new LinearLayoutManager( this ) );

        _ofertas = new ArrayList<>(  );

        adapter = new CardListEventsAdapter( _ofertas, this );

        rv.setAdapter( adapter );

        adapter.notifyDataSetChanged();

        rv.setHasFixedSize( true );
    }

    //Gestión de los botones de los fragments
    @Override
    public void menu(int buttonClicked,String fragmentName) {
        Toast.makeText(this,"El boton pulsado es el: "+buttonClicked,Toast.LENGTH_SHORT).show();
        if(fragmentName.equals(leftFragmentName)){
            buttonsHandler.onLeftMenuButtonClicked(buttonClicked,leftMenu,leftFragmentName);
        }
        else if(fragmentName.equals(topFragmentName)){
            buttonsHandler.onTopMenuButtonClicked(buttonClicked,leftMenu,topFragmentName);
        }
    }
}
