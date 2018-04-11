package com.andnet.gazeta.ui.Componenet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;

/**
 * Created by developer on 4/10/18.e
 */

public class CustomSwitchCompat extends SwitchCompat {


    public CustomSwitchCompat(Context context) {
        super(context);
        updateTheme();
    }

    public CustomSwitchCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTheme();
    }

    public void updateTheme() {

        int color= PreferenceUtility.getMainAppThemeColor();
        int alphColor=Color.argb(127,Color.red(Color.GRAY), Color.green(Color.GRAY), Color.blue(Color.GRAY));

        if(PreferenceUtility.getAppTheme().equals("dark")){
            color=ContextCompat.getColor(getContext(),R.color.colorAccent);
        }

        DrawableCompat.setTintList(this.getThumbDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        color,
                        Color.GRAY,
                }));

        DrawableCompat.setTintList(this.getTrackDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        alphColor,
                        alphColor
                }));
    }
}

