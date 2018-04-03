package com.andnet.gazeta.ui.Componenet;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.andnet.gazeta.ui.Theme;


public class CustomRadioButton extends AppCompatRadioButton {
    public CustomRadioButton(Context context) {
        super(context);
        updateTheme();
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTheme();
    }

    public void updateTheme(){

        setTextColor(Theme.side_nav_item_text_color);
        setHighlightColor(Theme.side_nav_switch_enabled_color);

    }
}
