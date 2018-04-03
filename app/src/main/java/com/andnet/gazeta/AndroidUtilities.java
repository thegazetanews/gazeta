package com.andnet.gazeta;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class AndroidUtilities {

    public static float density=1;
    public static int statusBarHeight = 0;
    public static Point displaySize = new Point();
    private static Boolean isTablet = null;



    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static float dpf2(float value) {
        density = MainApplication.applicationContext.getResources().getDisplayMetrics().density;
        if (value == 0) {
            return 0;
        }
        return density * value;
    }


    public static int getGridSpanCount(Context context){
        DisplayMetrics displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return  (int) (dpWidth / 140);
    }

    public static int dp(float value) {
        density = MainApplication.applicationContext.getResources().getDisplayMetrics().density;
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }
    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0) {
            MainApplication.applicationHandler.post(runnable);
        } else {
            MainApplication.applicationHandler.postDelayed(runnable, delay);
        }
    }

    public static void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        try {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (!imm.isActive()) {
                return;
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public static int[] fromStringToArray(String str){
       String[] val=str.replace("[","").replace("]","").split(",");
       int[] i=new int[val.length];
       for(int k=0;k<i.length;k++){
           i[k]=Integer.parseInt(val[k].trim());
       }
       return i;
    }








}
