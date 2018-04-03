package com.andnet.gazeta.ui.Componenet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.andnet.gazeta.ui.Theme;


public class CustomSwitch extends SwitchCompat {
    public CustomSwitch(Context context) {
        super(context);
    }

    public CustomSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTheme();
    }

    public void updateTheme(){
        setTextColor(ColorStateList.valueOf(Theme.side_nav_item_text_color));


        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{-android.R.attr.state_checked},
        };
        int[] colors = new int[]{

               Theme.side_nav_switch_enabled_color,
               Theme.side_nav_switch_disable_color
        };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        setThumbTintList(colorStateList);
    }
}
