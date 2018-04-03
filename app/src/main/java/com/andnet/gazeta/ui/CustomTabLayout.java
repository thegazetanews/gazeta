package com.andnet.gazeta.ui;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.PreferenceUtility;

import java.util.List;


public class CustomTabLayout extends TabLayout {
    public CustomTabLayout(Context context) {
        super(context);
        updateTheme();
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTheme();
    }

    public void updateTheme(){
        setSelectedTabIndicatorColor(Theme.tab_indictor_color);
        setTabTextColors( Theme.tab_layout_item_text_color,  Theme.tab_layout_item_selected_text_color);
        setBackgroundColor(Theme.toolbar_background_color);

        switch (PreferenceUtility.getTabGravity()){
            case "center":
                setTabGravity(TabLayout.GRAVITY_CENTER);
                break;
            case "fill":
                setTabGravity(TabLayout.GRAVITY_FILL);
                break;
        }

        switch (PreferenceUtility.getTabMode()){
            case "scrollable":
                setTabMode(TabLayout.MODE_SCROLLABLE);
                break;
            case "fixed":
                setTabMode(TabLayout.MODE_FIXED);
                break;
        }

        List<Category> categoryList= GazetaDatabase.getDatabase(getContext()).dao().getAllVisibleCategories();
        if(PreferenceUtility.getTabDisplay().equals("icon") && categoryList.size()==getTabCount()){
            for(int i=0;i<getTabCount();i++){
                TabLayout.Tab tab=getTabAt(i);
                if(tab!=null){
                    tab.setIcon(categoryList.get(i).getRes());
                    tab.setText("");

                }
            }
        }else  if(PreferenceUtility.getTabDisplay().equals("both") && categoryList.size()==getTabCount()){
            for(int i=0;i<getTabCount();i++){
                TabLayout.Tab tab=getTabAt(i);
                if(tab!=null){
                    tab.setIcon(categoryList.get(i).getRes());
                    tab.setText(categoryList.get(i).getName());
                }
            }
        }else if(PreferenceUtility.getTabDisplay().equals("title")){
            for(int i=0;i<getTabCount();i++){
                TabLayout.Tab tab=getTabAt(i);
                if(tab!=null){
                    tab.setIcon(null);
                    tab.setText(categoryList.get(i).getName());
                }
            }
        }

    }}
