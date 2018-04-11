package com.andnet.gazeta.ui.Componenet;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.CheckBox;


public class CustomCheckBox extends android.support.v7.widget.AppCompatCheckBox{


    public CustomCheckBox(Context context) {
        super(context);
        updateTheme();

    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTheme();
    }

    @SuppressLint("RestrictedApi")
    public void updateTheme() {
//        setTextColor(Theme.side_nav_item_text_color);
        int states[][] = {{android.R.attr.state_checked}, {-android.R.attr.state_checked}};
//        int colors[] = {Theme.state_enabled_color, Theme.state_disable_color};
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            setButtonTintList(new ColorStateList(states,colors));
//        }else {
//            setSupportButtonTintList(new ColorStateList(states,colors));
//
//        }
    }
}