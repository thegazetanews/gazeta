package com.andnet.gazeta;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;


public class AnimationUtilities {


//    public static void slideInFromLeft(Context context, View view) {
//        Animation animation = new TranslateAnimation(0, 500,0, 0);
//
//        runSimpleAnimation(context, view, R.anim.slide_from_left);
//    }
//

    private static void runSimpleAnimation(Context context, View view, int animationId) {
//        view.startAnimation(AnimationUtils.loadAnimation(
//                context, animationId
//        ));
//
//        Animation animation = new TranslateAnimation(0, 500,0, 0);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
//        view.startAnimation(animation);
//        view.setVisibility(View.INVISIBLE);

//        ObjectAnimator transAnimation= ObjectAnimator.ofFloat(view, "x", fromX, toX);
//        transAnimation.setDuration(3000);
//        transAnimation.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void enterReavel(View myView){
        int cx = myView.getMeasuredWidth() / 2;
        int cy = myView.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void exitReavel(View myView){
        int cx = myView.getMeasuredWidth() / 2;
        int cy = myView.getMeasuredHeight() / 2;
        int initialRadius = myView.getWidth() / 2;
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }
}
