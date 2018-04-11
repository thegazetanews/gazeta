package com.andnet.gazeta.ui.Componenet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.andnet.gazeta.ui.ThemeInterface;


public class SettingHeaderView extends android.support.v7.widget.AppCompatTextView  implements ThemeInterface{

    public SettingHeaderView(Context context) {
        super(context);
        updateTheme();
    }

    public SettingHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        updateTheme();
    }

    @Override
    public void updateTheme() {
//        setTextColor(Theme.settings_header_text_color);
//        setBackgroundColor(Theme.setting_header_background_color);
    }
}
