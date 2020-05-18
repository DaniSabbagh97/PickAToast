package com.example.pickatoast.pickatoast.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.example.pickatoast.pickatoast.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardListEventsAdapter extends RecyclerView.Adapter<CardListEventsAdapter.CardEventsViewHolder> {

    List<OfertaEmpleador> mOferta;




    String nombre, descripcion, foto;

    public CardListEventsAdapter(List<OfertaEmpleador> mOfertas) {
        this.mOferta = mOfertas;
    }


    @NonNull
    @Override
    public CardEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.card_events_data ,parent, false );
        CardEventsViewHolder holder = new CardEventsViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardListEventsAdapter.CardEventsViewHolder holder, int position) {

        OfertaEmpleador ofertaEmpleador = mOferta.get( position );

        nombre = ofertaEmpleador.getNombreOferta();
        descripcion = ofertaEmpleador.getDescripcionOferta();
        foto = ofertaEmpleador.getLinkImagen();

        holder.tvNombre.setText( nombre );
        holder.tvDescripcion.setText( descripcion );
        Picasso.get().load( foto ).into(holder.ivOferta);
        //TODO Colocar aqui cada oferta para que se recoja de firebase

        holder.btnApuntarse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO boton apuntarse
            }
        } );

        holder.layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO por si se quiere poner funcionalidad al pulsar cada oferta
            }
        } );


    }

    @Override
    public int getItemCount() {
        return mOferta.size();
    }

    public static class CardEventsViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre;
        TextView tvDescripcion;
        ImageView ivOferta;
        Button btnApuntarse;
        LinearLayout layout;

        public CardEventsViewHolder(@NonNull View itemView) {
            super( itemView );

            tvNombre = itemView.findViewById( R.id.tvNombre );
            tvDescripcion = itemView.findViewById( R.id.tvDescripcion );

            ivOferta = itemView.findViewById( R.id.ivOferta );

            btnApuntarse = itemView.findViewById( R.id.btnApuntarse );

            layout = itemView.findViewById( R.id.linearLayoutButtonCardItineraryData );
        }
    }

    /*public void obtenerDatosOfertas() {
        //user = FirebaseAuth.getInstance().getCurrentUser();


        reference = FirebaseDatabase.getInstance().getReference("Ofertas").child( "clave");
        final Query qq = reference;
        qq.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    OfertaEmpleador of = dataSnapshot.getValue(OfertaEmpleador.class);
                    //Comprobar si esta el itinerario
                    mOferta.add(of);

                //adapter.notifyDataSetChanged();
                //TODO recuperar la imagen tambien


                qq.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}
