package com.andnet.gazeta.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Adapter;


public class CustomSideNavView extends NavigationView {
    public CustomSideNavView(Context context) {
        super(context);
    }

    public CustomSideNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void updateTheme(){
//        setBackgroundColor(Theme.side_nav_background_color);

//        int[][] states = new int[][]{
//                new int[]{android.R.attr.state_checked},
//                new int[]{-android.R.attr.state_checked},
//        };
//        int[] colors = new int[]{
//
//                getResources().getColor(R.color.colorAccent),
//                getResources().getColor(R.color.black)
//        };
//        ColorStateList colorStateList = new ColorStateList(states, colors);
//        setItemTextColor(ColorStateList.valueOf(Theme.side_nav_item_text_color));
//        setItemIconTintList(ColorStateList.valueOf(Theme.side_nav_item_icon_color));


        Menu menu=getMenu();
        for(int i=0;i<menu.size();i++){
            MenuItem menuItem=menu.getItem(i);
            if(menuItem!=null){

             View view=menuItem.getActionView();
             if(view!=null){
                 Log.i("BERHAN",view.getClass().getSimpleName());
             }

            }
        }







    }
}
