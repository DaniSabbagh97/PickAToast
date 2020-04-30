package com.example.pickatoast.pickatoast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pickatoast.pickatoast.Interfaces.TopMainMenuImpl;
import com.example.pickatoast.pickatoast.Services.XmlAnimationService;


public class MainActivity extends AppCompatActivity implements TopMainMenuImpl {
    FrameLayout leftMenu;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        leftMenu=findViewById(R.id.leftMenu);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,RegristroEmpleado.class);
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
