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
    public void menu(int buttonClicked,String fragmentName) {
        Toast.makeText(this,""+fragmentName+"El boton pulsado es el: "+buttonClicked,Toast.LENGTH_SHORT).show();
        switch (buttonClicked){
            case 0:
                leftMenu.setEnabled(!leftMenu.isEnabled());
                if(leftMenu.getVisibility()==View.VISIBLE)
                {
                    leftMenu.setVisibility(View.INVISIBLE);
                    leftMenuAnimation(R.anim.left_slice);
                }
                else{
                    leftMenuAnimation(R.anim.right_slice);
                    leftMenu.setVisibility(View.VISIBLE);

                }

                break;
        }
    }

    public void leftMenuAnimation(int Animation){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(this);
        xmlAnimationService.runAnimation(leftMenu,Animation);
    }

}
