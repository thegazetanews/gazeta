package com.andnet.gazeta.ui.Componenet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;



public class CustomSwitch extends SwitchCompat {
    public CustomSwitch(Context context) {
        super(context);
    }

    public CustomSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    public void updateTheme(){
////        setTextColor(ColorStateList.valueOf(Theme.side_nav_item_text_color));
//        int[][] states = new int[][]{
//                new int[]{android.R.attr.state_checked},
//                new int[]{-android.R.attr.state_checked},
//        };
//        int[] colors = new int[]{
//
//               Theme.state_enabled_color,
//               Theme.state_disable_color
//        };
//        ColorStateList colorStateList = new ColorStateList(states, colors);
//        setThumbTintList(colorStateList);
//    }
}
