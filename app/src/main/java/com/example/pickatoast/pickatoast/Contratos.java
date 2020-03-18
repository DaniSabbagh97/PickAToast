package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pickatoast.pickatoast.Adapters.AdaptadorOfertas;
import com.example.pickatoast.pickatoast.Pojos.Oferta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratos);

        todosContratos= new ArrayList<>();
        datos=new ArrayList<>();
        adapter= new AdaptadorOfertas(datos,this);

        rvContratos=findViewById(R.id.rvContratos);

        user= FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            uid=user.getUid();
        }

        llm= new LinearLayoutManager(this);

    }
}
