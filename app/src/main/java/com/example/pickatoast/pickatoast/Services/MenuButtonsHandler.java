package com.example.pickatoast.pickatoast.Services;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.example.pickatoast.pickatoast.R;

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

                 // ChangeWindowService.jumpTo(context, SplashActivity.class);
                 break;
             //BOTON AJUSTES
             case 2:
                 break;
             //BOTON InFO
             case 3:
                 break;
             //BOTON CERRAR SESION
             case 4:
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

               // ChangeWindowService.jumpTo(context, SplashActivity.class);
                break;
            //BOTON PERFIL
            case 2:
                break;



        }
    }


    private void leftMenuAnimation(int Animation, FrameLayout frameMenu){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(context);
        xmlAnimationService.runAnimation(frameMenu,Animation);
    }


}
