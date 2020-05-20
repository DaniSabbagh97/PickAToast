package com.example.pickatoast.pickatoast.Adapters;

import android.app.Activity;
import android.content.Intent;
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
import com.example.pickatoast.pickatoast.ViewSingleEvent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardListEventsAdapter extends RecyclerView.Adapter<CardListEventsAdapter.CardEventsViewHolder> {

    List<OfertaEmpleador> mOferta;
    Intent intent;
    Activity activity;




    String nombre, restaurante, foto, id, duracion, localizacion;

    public CardListEventsAdapter(List<OfertaEmpleador> mOfertas, Activity activity) {
        this.mOferta = mOfertas;
        this.activity = activity;
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
        restaurante = ofertaEmpleador.getRestaurante();
        foto = ofertaEmpleador.getLinkImagen();
        id = ofertaEmpleador.getId();
        localizacion = ofertaEmpleador.getLocalizacionOferta();
        duracion = ofertaEmpleador.getDuracionContrato();

        holder.tvNombre.setText( nombre );
        holder.tvDescripcion.setText( restaurante );
        Picasso.get().load( foto ).into(holder.ivOferta);
        //TODO Colocar aqui cada oferta para que se recoja de firebase

        holder.btnApuntarse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              changeWindowTo(ViewSingleEvent.class, id, foto, nombre, duracion, localizacion, restaurante);
            }
        } );

        holder.layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO por si se quiere poner funcionalidad al pulsar cada oferta
            }
        } );


    }

    void changeWindowTo(Class goTo, String idUser, String foto, String nombre, String duracion, String localizacion, String restaurante){
        intent = new Intent(activity,goTo);
        intent.putExtra("idOferta", idUser);
        intent.putExtra("foto", foto);
        intent.putExtra("nombre", nombre);
        intent.putExtra("duracion", duracion);
        intent.putExtra("localizacion", localizacion);
        intent.putExtra("restaurante", restaurante);
        activity.startActivity(intent);

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

            ivOferta = itemView.findViewById( R.id.ivOferta);

            btnApuntarse = itemView.findViewById( R.id.btnApuntarse );

            layout = itemView.findViewById( R.id.linearLayoutButtonCardItineraryData );
        }
    }

}
