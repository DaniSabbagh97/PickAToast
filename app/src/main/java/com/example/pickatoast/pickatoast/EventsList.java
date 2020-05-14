package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pickatoast.pickatoast.Adapters.CardListEventsAdapter;
import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;

import java.util.ArrayList;

public class EventsList extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<OfertaEmpleador> _ofertas;
    CardListEventsAdapter adapter;

    private String xxx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_events_list );

        rv = findViewById( R.id.rvEvents );
        rv.setLayoutManager( new LinearLayoutManager( this ) );

        _ofertas = new ArrayList<>(  );

        adapter = new CardListEventsAdapter( _ofertas, this );

        rv.setAdapter( adapter );

        adapter.notifyDataSetChanged();

        rv.setHasFixedSize( true );
    }
}
