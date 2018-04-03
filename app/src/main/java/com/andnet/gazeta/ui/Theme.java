package com.andnet.gazeta.ui;


import android.content.Context;
import android.content.SharedPreferences;

import com.andnet.gazeta.FileLog;
import com.andnet.gazeta.MainApplication;

public class Theme {

      //bottom nav
      public static final String key_bottom_nav_background="key_bottom_nav_background_color";
      public static final String key_bottom_nav_item_color="key_bottom_nav_item_color";
      public static final String key_bottom_nav_item_selected_color="key_bottom_nav_item_selected_color";






      public static final String key_toolbar_background_color="key_toolbar_background_color";
      public static final String key_toolbar_title_text_color="key_toolbar_title_text_color";
      public static final String key_progress_circle="key_progress_circle";
      public static final String key_tab_layout_item_text_color="key_tab_layout_text_color";
      public static final String key_tab_indicator_color="key_tab_indicator_color";
      public static final String key_tab_layout_background_color="key_tab_layout_background_color";
      public static final String key_tab_layout_item_icon_color="key_tab_layout_item_icon_color";
      public static final String key_tab_item_selected_text_color="key_tab_item_selected_text_color";
      public static final String key_tab_item_selected_icon_color="key_tab_item_selected_icon_color";
      public static final String key_side_nav_background_color="key_side_nav_background_color";

      public static final String key_side_nav_item_icon_color="key_side_nav_item_icon_color";
      public static final String key_side_nav_item_text_color="key_side_nav_item_text_color";
      public static final String key_side_nav_switch_disable_color="key_side_nav_switch_disable_color";
      public static final String key_side_nav_switch_enabled_color="key_side_nav_switch_enabled_color";
      public static final String key_side_nav_item_divider="key_side_nav_item_divider";
      public static final String key_side_nav_item_header_text_color="key_side_nav_item_header_text_color";
      public static final String key_ripple_color="key_ripple_color";
      public static final String key_avatar_disable_color="key_avatar_disable_color";
      public static final String key_avatar_enale_color="key_avatar_enale_color";


      //bottom nav default value
      public static int  bottom_nav_background_color=0xffffffff;
      public static int  bottom_nav_item_color=0xff000000;
      public static int  bottom_nav_item_selected_color=0xffD50000;



      public static int  toolbar_background_color=0xffD50000;
      public static int  toolbar_title_text_color=0xffffffff;
      public static int  progress_view_circle=0xffD50000;
      public static int  tab_layout_item_text_color=0x85ffffff;
      public static int  tab_indictor_color=0xffffffff;
      public static int  tab_layout_background_color=0xffD50000;
      public static int  tab_layout_item_icon_color=0x85ffffff;
      public static int  tab_layout_item_selected_text_color=0xffffffff;
      public static int  tab_layout_item_selected_icon_color=0xffffffff;
      public static int  side_nav_background_color=0xffffffff;
      public static int side_nav_item_icon_color=0xff000000;
      public static int side_nav_item_text_color=0xff000000;
      public static int side_nav_switch_disable_color=0x80000000;
      public static int side_nav_switch_enabled_color=0xffD50000;
      public static int side_nav_item_divider=0x10000000;
      public static int side_nav_item_header_text_color=0x80000000;
      public static int ripple_color=0x10000000;
      public static int avatar_enale_color=0xFF03A9F4;


    public static void updateAppTheme(){
            SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE);
            //bottom nav init
            bottom_nav_background_color=sharedPreferences.getInt(key_bottom_nav_background,bottom_nav_background_color);
            bottom_nav_item_color=sharedPreferences.getInt(key_bottom_nav_item_color,bottom_nav_item_color);
            bottom_nav_item_selected_color=sharedPreferences.getInt(key_bottom_nav_item_selected_color,bottom_nav_item_selected_color);

            toolbar_background_color=sharedPreferences.getInt(key_toolbar_background_color,toolbar_background_color);
            progress_view_circle=sharedPreferences.getInt(key_progress_circle,progress_view_circle);
            toolbar_title_text_color=sharedPreferences.getInt(key_toolbar_title_text_color,toolbar_title_text_color);
            tab_layout_item_text_color=sharedPreferences.getInt(key_tab_layout_item_text_color,tab_layout_item_text_color);
            tab_indictor_color=sharedPreferences.getInt(key_tab_indicator_color,tab_indictor_color);
            tab_layout_background_color=sharedPreferences.getInt(key_tab_layout_background_color,tab_layout_background_color);
            tab_layout_item_icon_color=sharedPreferences.getInt(key_tab_layout_item_icon_color,tab_layout_item_icon_color);
            tab_layout_item_selected_text_color=sharedPreferences.getInt(key_tab_item_selected_text_color,tab_layout_item_selected_text_color);
            tab_layout_item_selected_icon_color=sharedPreferences.getInt(key_tab_item_selected_icon_color,tab_layout_item_selected_icon_color);
            side_nav_background_color=sharedPreferences.getInt(key_side_nav_background_color,side_nav_background_color);
            side_nav_item_icon_color=sharedPreferences.getInt(key_side_nav_item_icon_color,side_nav_item_icon_color);;
            side_nav_item_text_color=sharedPreferences.getInt(key_side_nav_item_text_color,side_nav_item_text_color);;
            side_nav_switch_disable_color=sharedPreferences.getInt(key_side_nav_switch_disable_color,side_nav_switch_disable_color);;
            side_nav_switch_enabled_color=sharedPreferences.getInt(key_side_nav_switch_enabled_color,side_nav_switch_enabled_color);;
            side_nav_item_divider=sharedPreferences.getInt(key_side_nav_item_divider,side_nav_item_divider);
            side_nav_item_header_text_color=sharedPreferences.getInt(key_side_nav_item_header_text_color,side_nav_item_header_text_color);
            ripple_color=sharedPreferences.getInt(key_ripple_color,ripple_color);
      }

      public static void writeThemeInfoToFile(){
          SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor=sharedPreferences.edit();
          //bottom nav
          editor.putInt(key_bottom_nav_background,bottom_nav_background_color);
          editor.putInt(key_bottom_nav_item_color,bottom_nav_item_color);
          editor.putInt(key_bottom_nav_item_selected_color,bottom_nav_item_selected_color);


          editor.putInt(key_toolbar_background_color,toolbar_background_color);
          editor.putInt(key_progress_circle,progress_view_circle);
          editor.putInt(key_toolbar_title_text_color,toolbar_title_text_color);
          editor.putInt(key_tab_layout_item_text_color,tab_layout_item_text_color);
          editor.putInt(key_tab_indicator_color,tab_indictor_color);
          editor.putInt(key_tab_layout_background_color,tab_layout_background_color);
          editor.putInt(key_tab_layout_item_icon_color,tab_layout_item_icon_color);
          editor.putInt(key_tab_item_selected_text_color,tab_layout_item_selected_text_color);
          editor.putInt(key_tab_item_selected_icon_color,tab_layout_item_selected_icon_color);
          editor.putInt(key_side_nav_background_color,side_nav_background_color);
          editor.putInt(key_side_nav_item_icon_color,side_nav_item_icon_color);
          editor.putInt(key_side_nav_item_text_color,side_nav_item_text_color);
          editor.putInt(key_side_nav_switch_disable_color,side_nav_switch_disable_color);
          editor.putInt(key_side_nav_switch_enabled_color,side_nav_switch_enabled_color);
          editor.putInt(key_side_nav_item_divider,side_nav_item_divider);
          editor.putInt(key_side_nav_item_header_text_color,side_nav_item_header_text_color);
          editor.putInt(key_ripple_color,ripple_color);
          editor.commit();
      }

      public static void setSingleTheme(String key,int value){
          FileLog.write(key + " = " + value);
          SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor=sharedPreferences.edit();
          editor.putInt(key,value);
          editor.commit();
      }





}
