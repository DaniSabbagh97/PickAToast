package com.example.pickatoast.pickatoast.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.example.pickatoast.pickatoast.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorOfertas extends RecyclerView.Adapter<AdaptadorOfertas.InicioViewHolder> implements View.OnClickListener{

    private ArrayList<OfertaEmpleador> lista;
    private View.OnClickListener listener;
    private Context contexto;

    public AdaptadorOfertas(ArrayList<OfertaEmpleador> lista, Context context){
        this.contexto=context;
        this.lista=lista;
    }

    @NonNull
    @Override
    public AdaptadorOfertas.InicioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ofertas, viewGroup, false);
        v.setOnClickListener(this);
        AdaptadorOfertas.InicioViewHolder vh = new AdaptadorOfertas.InicioViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorOfertas.InicioViewHolder inicioViewHolder, int i) {
        inicioViewHolder.bindMensaje(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class InicioViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre;
        TextView tvRestaurante;
        TextView tvLocalidad;
        TextView tvDuracion;
        ImageView imgOferta;

        public InicioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreOferta);
            tvRestaurante = itemView.findViewById(R.id.tvNombreRestauranteOfer);
            tvLocalidad = itemView.findViewById(R.id.tvLocalizacionofer);
            tvDuracion = itemView.findViewById(R.id.tvDuracionOferta);
            imgOferta= itemView.findViewById(R.id.imgOferta);

        }

        public void bindMensaje(OfertaEmpleador oferta){
            tvNombre.setText(oferta.getNombreOferta());
            tvRestaurante.setText(oferta.getRestauranteOferta());
            tvLocalidad.setText(oferta.getLocalizacionOferta());
            tvDuracion.setText(oferta.getDuracionContrato());
            Glide.with(contexto).load(oferta.getLinkImagen());
        }
    }

    public void clear(){
        lista.clear();
    }
}

