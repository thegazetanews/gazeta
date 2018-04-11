package com.andnet.gazeta.ui;


import android.content.Context;
import android.content.SharedPreferences;

import com.andnet.gazeta.MainApplication;

import java.util.ArrayList;
import java.util.List;

public class Theme {

    public static class ThemeInfo{

        public static List<ThemeInfo> themeInfoList=new ArrayList<>();

         public String name;
         public int color;

        public ThemeInfo() {
        }

        public ThemeInfo(String name, int color) {
            this.name = name;
            this.color = color;

        }

        public static void addThemesInfo(){
            ThemeInfo themeInfo=new ThemeInfo();
            themeInfo.name="Blue";
            themeInfo.color=0xff2196F3;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Red";
            themeInfo.color=0xffF44336;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Orange";
            themeInfo.color=0xffFF9800;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Green";
            themeInfo.color=0xff4CAF50;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Brown";
            themeInfo.color=0xff795548;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Blue Gray";
            themeInfo.color=0xff607D8B;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Yellow";
            themeInfo.color=0xffFFEB3B;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Pink";
            themeInfo.color=0xffE91E63;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Purple";
            themeInfo.color=0xff9C27B0;
            themeInfoList.add(themeInfo);

            themeInfo=new ThemeInfo();
            themeInfo.name="Indigo";
            themeInfo.color=0xff3F51B5;
            themeInfoList.add(themeInfo);


            themeInfo=new ThemeInfo();
            themeInfo.name="Cyan";
            themeInfo.color=0xff00BCD4;
            themeInfoList.add(themeInfo);


            themeInfo=new ThemeInfo();
            themeInfo.name="Teal";
            themeInfo.color=0xff009688;
            themeInfoList.add(themeInfo);

//
//            themeInfo=new ThemeInfo();
//            themeInfo.name="Dark";
//            themeInfo.color=0xff000000;
//            themeInfoList.add(themeInfo);
//
//            themeInfo=new ThemeInfo();
//            themeInfo.name="Blue Dark";
//            themeInfo.color=0xff212121;
//            themeInfoList.add(themeInfo);


        }
    }

//    public static ArrayList<String> getMainThemeKeys(){
//        ArrayList<String> mainThemeKeys=new ArrayList<>();
//        mainThemeKeys.add("key_bottom_nav_item_selected_color");
//        mainThemeKeys.add("key_toolbar_background_color");
//        mainThemeKeys.add("key_progress_circle");
//        mainThemeKeys.add("key_tab_background_color");
//        mainThemeKeys.add("key_tab_background_color");
//        return mainThemeKeys;
//    }
//
//
//     /*bottom nav key*/
//     private static final String key_bottom_nav_background="key_bottom_nav_background_color";
//     private static final String key_bottom_nav_item_color="key_bottom_nav_item_color";
//     private static final String key_bottom_nav_item_selected_color="key_bottom_nav_item_selected_color";
//
//     public static int  bottom_nav_background_color=0xffffffff;
//     public static int  bottom_nav_item_color=0xff000000;
//     public static int  bottom_nav_item_selected_color=0xffD50000;
//
//
//     /*toolbar key*/
//     private static final String key_toolbar_background_color="key_toolbar_background_color";
//     private static final String key_toolbar_title_text_color="key_toolbar_title_text_color";
//     private static final String key_toolbar_icon_color="key_toolbar_icon_color";
//     public static int  toolbar_background_color=0xffD50000;
//     public static int  toolbar_title_text_color=0xffffffff;
//     public static int  toolbar_icon_color=0xffffffff;
//
//    /*radial progress*/
//     private static final String key_progress_circle="key_progress_circle";
//     public static int progress_circle=0xffD50000;
//
//     /*tab*/
//     private static final String key_tab_item_color="key_tab_layout_text_color";
//     private static final String key_tab_item_selected_color="key_tab_item_selected_color";
//     private static final String key_tab_indicator_color="key_tab_indicator_color";
//     private static final String key_tab_background_color="key_tab_background_color";
//     public static  int tab_item_color=0x85ffffff;
//     public static  int tab_item_selected_color=0xffffffff;
//     public static  int tab_indicator_color=0xffffffff;
//     public static  int tab_background_color=0xffD50000;
//
//     /*divider*/
//     private static final String  key_divider_color="key_divider_color";
//     public static  int  divider_color=0x10000000;
//
//    /*side nav*/
//     private static final String key_side_nav_background_color="key_side_nav_background_color";
//     private static final String key_side_nav_item_icon_color="key_side_nav_item_icon_color";
//     private static final String key_side_nav_item_text_color="key_side_nav_item_text_color";
//     private static final String key_side_nav_item_header_text_color="key_side_nav_item_header_text_color";
//     public static  int side_nav_background_color=0xffffffff;
//     public static  int side_nav_item_icon_color=0x80000000;
//     public static  int side_nav_item_text_color=0x80000000;
//     public static  int side_nav_item_header_text_color=0x60000000;
//
//     /*switch,check box,radio button*/
//     private static final String key_state_enabled_color="key_state_enabled_color";
//     private static final String key_state_disable_color="key_state_disable_color";
//     public static  int state_enabled_color=0xffD50000;
//     public static  int state_disable_color=0x80000000;
//
//      /*settings header*/
//      private static final String key_setting_header_background_color="key_setting_header_background_color";
//      private static final String key_settings_header_text_color="key_settings_header_text_color";
//      public static int  setting_header_background_color=0xffebebeb;
//      public static int  settings_header_text_color=0x80000000;
//
//      /*bottom sheet*/
//      private static final String key_bottom_sheet_background_color="key_bottom_sheet_background_color";
//      private static final String key_bottom_sheet_text_color="key_bottom_sheet_text_color";
//      private static final String key_bottom_sheet_icon_color="key_bottom_sheet_icon_color";
//      public static int bottom_sheet_background_color=0xffffffff;
//      public static int bottom_sheet_text_color=0x80000000;
//      public static int bottom_sheet_icon_color=0x80000000;
//
//      public static void updateAppTheme(){
//            SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE);
//            toolbar_background_color=sharedPreferences.getInt(key_toolbar_background_color,toolbar_background_color);
//            toolbar_title_text_color=sharedPreferences.getInt(key_toolbar_title_text_color,toolbar_title_text_color);
//            toolbar_icon_color=sharedPreferences.getInt(key_toolbar_icon_color,toolbar_icon_color);
//            progress_circle=sharedPreferences.getInt(key_progress_circle,progress_circle);
//            tab_item_color=sharedPreferences.getInt(key_tab_item_color,tab_item_color);
//            tab_item_selected_color=sharedPreferences.getInt(key_tab_item_selected_color,tab_item_selected_color);
//            tab_indicator_color=sharedPreferences.getInt(key_tab_indicator_color,tab_indicator_color);
//            tab_background_color=sharedPreferences.getInt(key_tab_background_color,tab_background_color);
//            divider_color=sharedPreferences.getInt(key_divider_color,divider_color);
//            side_nav_background_color=sharedPreferences.getInt(key_side_nav_background_color,side_nav_background_color);
//            side_nav_item_header_text_color=sharedPreferences.getInt(key_side_nav_item_header_text_color,side_nav_item_header_text_color);
//            side_nav_item_icon_color=sharedPreferences.getInt(key_side_nav_item_icon_color,side_nav_item_icon_color);
//            side_nav_item_text_color=sharedPreferences.getInt(key_side_nav_item_text_color,side_nav_item_text_color);
//            bottom_nav_background_color=sharedPreferences.getInt(key_bottom_nav_background,bottom_nav_background_color);
//            bottom_nav_item_color=sharedPreferences.getInt(key_bottom_nav_item_color,bottom_nav_item_color);
//            bottom_nav_item_selected_color=sharedPreferences.getInt(key_bottom_nav_item_selected_color,bottom_nav_item_selected_color);
//            setting_header_background_color=sharedPreferences.getInt(key_setting_header_background_color,setting_header_background_color);
//            settings_header_text_color=sharedPreferences.getInt(key_settings_header_text_color,settings_header_text_color);
//            bottom_sheet_background_color=sharedPreferences.getInt(key_bottom_sheet_background_color,bottom_sheet_background_color);
//            state_enabled_color=sharedPreferences.getInt(key_state_enabled_color,state_enabled_color);
//            state_disable_color=sharedPreferences.getInt(key_state_disable_color,state_disable_color);
//            bottom_sheet_text_color=sharedPreferences.getInt(key_bottom_sheet_text_color,bottom_sheet_text_color);
//            bottom_sheet_icon_color=sharedPreferences.getInt(key_bottom_sheet_icon_color,bottom_sheet_icon_color);
//      }
//
//      public static void applyThemeColor(String key, int value){
//          SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE);
//          SharedPreferences.Editor editor=sharedPreferences.edit();
//          editor.putInt(key,value);
//          editor.apply();
      }


