package com.andnet.gazeta.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.andnet.gazeta.R;

import java.lang.reflect.Field;


public class CustomToolbar extends Toolbar{

    public CustomToolbar(Context context) {
        super(context);
         updateTheme();
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        updateTheme();
    }


    public void updateTheme(){

       Menu menu= getMenu();
       if(menu!=null){
           int size= menu.size();
           for(int i=0;i<size;i++){
               MenuItem menuItem=menu.getItem(i);
               if(menuItem.getIcon()!=null){
                   Drawable menuDrawable=menuItem.getIcon();
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       menuDrawable.setTint(Theme.toolbar_title_text_color);
                   }else {
                       menuDrawable.setColorFilter(Theme.toolbar_title_text_color, PorterDuff.Mode.SRC_IN);
                   }
               }
           }
       }

      Drawable navIcon=getNavigationIcon();
       if(navIcon!=null){
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               navIcon.setTint(Theme.toolbar_title_text_color);
           }else {
               navIcon.setColorFilter(Theme.toolbar_title_text_color, PorterDuff.Mode.SRC_IN);
           }
       }

      setBackgroundColor(Theme.toolbar_background_color);
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
           if(getChildAt(i) instanceof TextView){
               TextView textView=(TextView) getChildAt(i);
               textView.setTextColor(Theme.toolbar_title_text_color);
           }else if(getChildAt(i) instanceof ImageView){
               ImageView imageView=(ImageView)getChildAt(i);
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                   imageView.setImageTintList(ColorStateList.valueOf(Theme.toolbar_title_text_color));
               }else{
                   Drawable drawable=imageView.getDrawable();
                   if(drawable!=null){
                       drawable.setColorFilter(Theme.toolbar_title_text_color, PorterDuff.Mode.ADD);
                   }
               }
           }
        }



    }

}
