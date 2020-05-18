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
             case 0:
                 frameMenu.setEnabled(!frameMenu.isEnabled());
                 if(frameMenu.getVisibility()== View.VISIBLE)
                 {
                     frameMenu.setVisibility(View.INVISIBLE);
                     leftMenuAnimation(R.anim.left_slice,frameMenu);
                 }
                 else{
                     leftMenuAnimation(R.anim.right_slice,frameMenu);
                     frameMenu.setVisibility(View.VISIBLE);

                 }

                 break;
         }
     }
    public void onTopMenuButtonClicked(int buttonClicked,FrameLayout leftMenu, String fragmentName){
        switch (buttonClicked){
            case 0:

                break;
        }
    }
    private void leftMenuAnimation(int Animation, FrameLayout frameMenu){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(context);
        xmlAnimationService.runAnimation(frameMenu,Animation);
    }
}
