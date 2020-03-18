package com.example.pickatoast.pickatoast.Services;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pickatoast.pickatoast.R;

public class XmlAnimationService implements Animation.AnimationListener {

    private Animation currentAnimation;
    private int[] animationQueueArray;
    private Context context;
    private View targetView;
    private int animationState=0;
    private int animationStateMax=0;

    public XmlAnimationService(Context context) {
        this.context = context;
    }

   public void runAnimation(View view, int currentAnimation){
        setCurrentAnimation(currentAnimation);
        targetView=view;
        animationState=0;
        animationStateMax=0;
        view.startAnimation(this.currentAnimation);
   }
   public void runAnimationArrayInOrder(View view, int[] currentAnimationArray){
        animationQueueArray= currentAnimationArray;
        targetView=view;
        animationStateMax = (animationQueueArray.length-1);

        setNextAnimationOfTheArray();

        view.startAnimation(currentAnimation);
   }

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
    public void setCurrentAnimation(Animation currentAnimation) {
        this.currentAnimation = currentAnimation;
        this.currentAnimation.setAnimationListener(this);
    }
    public void setCurrentAnimation(int currentAnimation) {
        this.currentAnimation = AnimationUtils.loadAnimation(context, currentAnimation);
        this.currentAnimation.setAnimationListener(this);
    }

}
