package com.example.pickatoast.pickatoast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pickatoast.pickatoast.Fragments.LeftMainMenu;
import com.example.pickatoast.pickatoast.Fragments.TopMainMenu;
import com.example.pickatoast.pickatoast.Interfaces.TopMainMenuImpl;
import com.example.pickatoast.pickatoast.Services.MenuButtonsHandler;
import com.example.pickatoast.pickatoast.Services.XmlAnimationService;


public class MenuTests2 extends AppCompatActivity implements TopMainMenuImpl {
    FrameLayout leftMenu;
    String leftFragmentName = LeftMainMenu.class.getSimpleName();
    String topFragmentName = TopMainMenu.class.getSimpleName();

    MenuButtonsHandler buttonsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.menu_test2);

        buttonsHandler= new MenuButtonsHandler(this);

        leftMenu=findViewById(R.id.leftMenu);


    }

    @Override
    public void menu(int buttonClicked,String fragmentName) {
        Toast.makeText(this,"El boton pulsado es el: "+buttonClicked,Toast.LENGTH_SHORT).show();
        if(fragmentName.equals(leftFragmentName)){
            buttonsHandler.onLeftMenuButtonClicked(buttonClicked,leftMenu,leftFragmentName);
        }
       else if(fragmentName.equals(topFragmentName)){
            buttonsHandler.onTopMenuButtonClicked(buttonClicked,leftMenu,topFragmentName);
        }
    }



}
