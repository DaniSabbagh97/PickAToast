package com.example.pickatoast.pickatoast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pickatoast.pickatoast.Adapters.AdaptadorOfertas;
import com.example.pickatoast.pickatoast.Pojos.Oferta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Contratos extends AppCompatActivity {


    AdaptadorOfertas adapter;

    ArrayList<Oferta> datos;
    ArrayList<Oferta> todosContratos;
    private ChildEventListener cel;
    private DatabaseReference dbr;
    RecyclerView rvContratos;
    LinearLayoutManager llm;

    Oferta oferta;

    FirebaseUser user;

    String uid;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratos);

        //email=getIntent().getExtras().getString("CLAVE CORREO");

        todosContratos= new ArrayList<>();
        datos=new ArrayList<>();
        adapter= new AdaptadorOfertas(datos,this);

        rvContratos=findViewById(R.id.rvContratos);

        user= FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            uid=user.getUid();
        }

        llm= new LinearLayoutManager(this);

        rvContratos.setLayoutManager(llm);
        rvContratos.setAdapter(adapter);
        rvContratos.setItemAnimator(new DefaultItemAnimator());
        dbr= FirebaseDatabase.getInstance().getReference().child("contratos");

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oferta o=datos.get(rvContratos.getChildAdapterPosition(v));
                /*TODO hace falta la siguiente ventana para acarbarlo
                Intent intent= new Intent(Contratos.this, Siguiente.class)
                stratActivity(intent);*/
            }
        });

    }

    private void addChildEventListener(){
        if(cel==null){
            if(cel == null){
                cel= new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        oferta = (Oferta) dataSnapshot.getValue(Oferta.class);

                        if (oferta.getId().equals(uid)) {
                            datos.add(oferta);
                        }

                        adapter.notifyItemInserted(datos.size()-1);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                dbr.addChildEventListener(cel);
            }
        }

    }
    protected void onResume(){
        super.onResume();
        addChildEventListener();
    }
}