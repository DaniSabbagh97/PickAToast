package com.example.pickatoast.pickatoast.Services;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.example.pickatoast.pickatoast.ChangePasswordEmpleado;

import com.example.pickatoast.pickatoast.EmployeeProfile;
import com.example.pickatoast.pickatoast.EventsList;
import com.example.pickatoast.pickatoast.Login;
import com.example.pickatoast.pickatoast.Pojos.OfertaEmpleador;
import com.example.pickatoast.pickatoast.R;
import com.example.pickatoast.pickatoast.info;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuButtonsHandler {
    private Context context;
    public MenuButtonsHandler(Context context) {
        this.context=context;
    }

     public void onLeftMenuButtonClicked(int buttonClicked, FrameLayout frameMenu, String fragmentName){
         switch (buttonClicked){
             //BOTON INICIO
             case 0:
                // ChangeWindowService.jumpTo(context, SplashActivity.class);


                 break;
             //BOTON PERFIL
             case 1:
                 ChangeWindowService.jumpTo(context, EmployeeProfile.class);
                 break;
             //BOTON AJUSTES
             case 2:
                 //TODO AJUSTES
                 break;
             //BOTON InFO
             case 3:
                 ChangeWindowService.jumpTo(context, info.class);
                 break;
             //BOTON CERRAR SESION
             case 4:
                 FirebaseAuth.getInstance().signOut();
                 ChangeWindowService.jumpTo(context, Login.class);

                 break;
         }
     }
    public void onTopMenuButtonClicked(int buttonClicked,FrameLayout leftMenu, String fragmentName){
        switch (buttonClicked){
            //BOTON MENU LATERAL
            case 0:
                leftMenu.setEnabled(!leftMenu.isEnabled());
                if(leftMenu.getVisibility()== View.VISIBLE)
                {
                    leftMenu.setVisibility(View.INVISIBLE);
                    leftMenuAnimation(R.anim.left_slice,leftMenu);
                }
                else{
                    leftMenuAnimation(R.anim.right_slice,leftMenu);
                    leftMenu.setVisibility(View.VISIBLE);

                }
                break;
            //BOTON INICIO
            case 1:
                if(!context.getClass().equals(EventsList.class)){
                    ChangeWindowService.jumpTo(context, EventsList.class);
                }else{
                    System.out.println("You are already in the main menu");

                }

                break;
            //BOTON PERFIL
            case 2:
                ChangeWindowService.jumpTo(context, EmployeeProfile.class);

                break;



        }
    }


    private void leftMenuAnimation(int Animation, FrameLayout frameMenu){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(context);
        xmlAnimationService.runAnimation(frameMenu,Animation);
    }


}
