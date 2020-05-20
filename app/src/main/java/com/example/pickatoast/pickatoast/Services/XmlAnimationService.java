package com.example.pickatoast.pickatoast.Services;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pickatoast.pickatoast.R;

public class XmlAnimationService implements Animation.AnimationListener {

    private Animation currentAnimation; //Animacion actual
    private int[] animationQueueArray; //Cola de animaciones
    private Context context;    //Contexto de la actividad
    private View targetView;    //Vista  a animar
    private int animationState=0;   //Estado del array de animaciones
    private int animationStateMax=0;

    public XmlAnimationService(Context context) {
        this.context = context;
    }

    //Para ejecutar una animación individual
   public void runAnimation(View view, int currentAnimation){
        setCurrentAnimation(currentAnimation);
        targetView=view;
        animationState=0;
        animationStateMax=0;
        view.startAnimation(this.currentAnimation);
   }
   //Para ejecutar varias animaciones seguidas
   public void runAnimationArrayInOrder(View view, int[] currentAnimationArray){
        animationQueueArray= currentAnimationArray;
        targetView=view;
        animationStateMax = (animationQueueArray.length-1);

        setNextAnimationOfTheArray();

        view.startAnimation(currentAnimation);
   }
    // Pasa a la siguiente animacion del array
   private void setNextAnimationOfTheArray(){
        setCurrentAnimation(animationQueueArray[animationState]);
   }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animationState<animationStateMax)
        {
            animationState++;
            setNextAnimationOfTheArray();
            targetView.startAnimation(currentAnimation);
        }
        else{
            animationStateMax=0;
            animationState=0;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    //Sobrecarga para permitir ints o animaciones
    public void setCurrentAnimation(Animation currentAnimation) {
        this.currentAnimation = currentAnimation;
        this.currentAnimation.setAnimationListener(this);
    }
    public void setCurrentAnimation(int currentAnimation) {
        this.currentAnimation = AnimationUtils.loadAnimation(context, currentAnimation);
        this.currentAnimation.setAnimationListener(this);
    }

}
