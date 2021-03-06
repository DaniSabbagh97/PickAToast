package com.example.pickatoast.pickatoast.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pickatoast.pickatoast.Interfaces.TopMainMenuImpl;
import com.example.pickatoast.pickatoast.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftMainMenu extends Fragment {
    //TODO esta mierda esta copiada del menu de arriba, hay que adaptarlo bien

    private String fragmentName = this.getClass().getSimpleName();
    //Botones del menu
    private final int[] MENUICONBUTTONS={R.id.topMainMenuHomeButtonIcon,
            R.id.topMainMenuProfileButtonIcon,
            R.id.topMainMenuSettingsButtonIcon,
            R.id.topMainMenuInfoButtonIcon,
            R.id.topMainMenuCloseSessionButtonIcon};
    private final int[] MENUBUTTONS={R.id.topMainMenuHomeButton,
            R.id.topMainMenuProfileButton,
            R.id.topMainMenuSettingsButton,
            R.id.topMainMenuInfoButton,
            R.id.topMainMenuCloseSessionButton};

    public LeftMainMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu = inflater.inflate(R.layout.fragment_left_main_menu, container, false);

        menu.setVisibility(View.INVISIBLE);



        //Recorremos todos los botones de icono y le añadimos un onClickListener
        for(int i =0;i<MENUICONBUTTONS.length;i++)
        {
            ImageButton menuButton=menu.findViewById(MENUICONBUTTONS[i]);

            //Numero del boton pulsado segun el array recorrido de todos los botones
            final int numberOfButtonPressed=i;

           setOnClickOfButtons(menuButton,numberOfButtonPressed);
        }
        //Recorremos todos los botones y le añadimos un onClickListener
        for(int i =0;i<MENUBUTTONS.length;i++)
        {
            Button menuButton=menu.findViewById(MENUBUTTONS[i]);

            //Numero del boton pulsado segun el array recorrido de todos los botones
            final int numberOfButtonPressed=i;

            setOnClickOfButtons(menuButton,numberOfButtonPressed);
        }
        return menu;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    //Esto permite identificar automaticamente la actividad en la que nos encontramos al pulsar el boton
    private void setOnClickOfButtons(View menuButton, final int numberOfButtonPressed){
        menuButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Recojo la actividad del boton
                Activity currentButtonActivity= getActivity();

                //Casteo de la actividad al tipo de la interfaz, para poder llamar al metodo
                ((TopMainMenuImpl)currentButtonActivity).menu(numberOfButtonPressed, fragmentName);
            }
        });
    }
}
