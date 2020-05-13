package com.example.pickatoast.pickatoast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pickatoast.pickatoast.Interfaces.TopMainMenuImpl;
import com.example.pickatoast.pickatoast.Services.XmlAnimationService;


public class MenuTests2 extends AppCompatActivity implements TopMainMenuImpl {
    FrameLayout leftMenu;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.menu_test2);

        leftMenu=findViewById(R.id.leftMenu);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MenuTests2.this,RegristroEmpleado.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void menu(int buttonClicked) {
        Toast.makeText(this,"El boton pulsado es el: "+buttonClicked,Toast.LENGTH_SHORT).show();
        switch (buttonClicked){
            case 0:
                leftMenu.setEnabled(!leftMenu.isEnabled());
                if(leftMenu.getVisibility()==View.VISIBLE)
                {
                    leftMenu.setVisibility(View.INVISIBLE);
                    leftMenuAnimation2();
                }
                else{
                    leftMenuAnimation();
                    leftMenu.setVisibility(View.VISIBLE);

                }

                break;
        }
    }

    public void leftMenuAnimation(){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(this);
        int[] animationArray ={R.anim.right_slice};
        xmlAnimationService.runAnimationArrayInOrder(leftMenu,animationArray);
    }
    public void leftMenuAnimation2(){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(this);
        int[] animationArray ={R.anim.left_slice};
        xmlAnimationService.runAnimationArrayInOrder(leftMenu,animationArray);
    }
}
