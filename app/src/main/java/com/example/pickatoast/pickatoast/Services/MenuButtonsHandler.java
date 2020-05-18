package com.example.pickatoast.pickatoast.Services;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.pickatoast.pickatoast.MenuTests2;
import com.example.pickatoast.pickatoast.R;

public class MenuButtonsHandler {
    private Context context;
    public MenuButtonsHandler(Context context) {
        this.context=context;
    }

     public void onLeftMenuButtonClicked(int buttonClicked, FrameLayout frameMenu, String fragmentName){
         switch (buttonClicked){
             case 0:
                 jumpToProfile();

                 break;
             case 1:
                 notifications();
                 break;
             case 2:

                 break;
         }
     }
    public void onTopMenuButtonClicked(int buttonClicked,FrameLayout leftMenu, String fragmentName){
        switch (buttonClicked){
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
            case 1:
                //TODO funcionalidad de notificaciones
                notifications();
                break;
            case 2:
                //TODO intent para ir al perfil
             jumpToProfile();
        }
    }
    private void leftMenuAnimation(int Animation, FrameLayout frameMenu){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(context);
        xmlAnimationService.runAnimation(frameMenu,Animation);
    }

    private void jumpToProfile(){
          /* Intent guestActivity = new Intent(context, MenuTests2.class);
                startActivity(guestActivity);
                finish();
                break;*/
    }
    private void jumpToSettings(){
        /* Intent guestActivity = new Intent(context, MenuTests2.class);
                startActivity(guestActivity);
                finish();
                break;*/
    }
    //TODO llamada a las notificaciones
    private void notifications(){

    }
}
