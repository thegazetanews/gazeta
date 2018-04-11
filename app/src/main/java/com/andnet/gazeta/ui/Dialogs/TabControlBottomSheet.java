package com.andnet.gazeta.ui.Dialogs;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;

public class TabControlBottomSheet extends BottomSheetDialogFragment{

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.tab_layout_setting,null);

        if(PreferenceUtility.getAppTheme().equals("dark")){
            view.setBackgroundColor(Color.parseColor("#212121"));
        }

        RadioGroup tabDisplayRadioGroup=view.findViewById(R.id.tabDisplayRadioGroup);
        RadioGroup tabModeRadioGroup=view.findViewById(R.id.tabModeRadioGroup);
        RadioGroup tabGravityRadioGroup=view.findViewById(R.id.tabGravityRadioGroup);


        if(PreferenceUtility.getTabDisplay().equals("title")){
            ((com.andnet.gazeta.ui.Componenet.CustomRadioButton)tabDisplayRadioGroup.findViewById(R.id.tabtitle)).setChecked(true);
        }else  if(PreferenceUtility.getTabDisplay().equals("both")){
            ((com.andnet.gazeta.ui.Componenet.CustomRadioButton)tabDisplayRadioGroup.findViewById(R.id.tabboth)).setChecked(true);
        }else  if(PreferenceUtility.getTabDisplay().equals("icon")){
            ((com.andnet.gazeta.ui.Componenet.CustomRadioButton)tabDisplayRadioGroup.findViewById(R.id.tabicon)).setChecked(true);

        }

        if(PreferenceUtility.getTabGravity().equals("center")){
            ((com.andnet.gazeta.ui.Componenet.CustomRadioButton)tabGravityRadioGroup.findViewById(R.id.center)).setChecked(true);
        }else  if(PreferenceUtility.getTabGravity().equals("fill")){
            ((com.andnet.gazeta.ui.Componenet.CustomRadioButton)tabGravityRadioGroup.findViewById(R.id.fill)).setChecked(true);

        }

        if(PreferenceUtility.getTabMode().equals("scrollable")){
            ((com.andnet.gazeta.ui.Componenet.CustomRadioButton)tabModeRadioGroup.findViewById(R.id.scrollable)).setChecked(true);
        }else  if(PreferenceUtility.getTabMode().equals("fixed")){
            ((com.andnet.gazeta.ui.Componenet.CustomRadioButton)tabModeRadioGroup.findViewById(R.id.fixed)).setChecked(true);

        }

        tabGravityRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case  R.id.center:
                    PreferenceUtility.setTabGravity("center");
                    break;
                case  R.id.fill:
                    PreferenceUtility.setTabGravity("fill");
                    break;
            }
        });

        tabModeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case  R.id.scrollable:
                    PreferenceUtility.setTabMode("scrollable");
                    break;
                case  R.id.fixed:
                    PreferenceUtility.setTabMode("fixed");
                    break;
            }
        });


        tabDisplayRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
               case  R.id.tabboth:
                   PreferenceUtility.setTabDisplay("both");
                    break;
                case  R.id.tabtitle:
                    PreferenceUtility.setTabDisplay("title");
                    break;
                case  R.id.tabicon:
                    PreferenceUtility.setTabDisplay("icon");
                    break;
            }

        });
        dialog.setContentView(view);
    }







}
