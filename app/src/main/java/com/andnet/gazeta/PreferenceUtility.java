package com.andnet.gazeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

import com.andnet.gazeta.ui.Dialogs.CategoryChooserBottomSheet;

import java.util.ArrayList;
import java.util.List;


public class PreferenceUtility {


    //listers
//
//    public OnTabItemChangedListeners onTabItemChangedListeners;
//    public interface OnTabItemChangedListeners{
//        void tabItemChanged();
//    }

    public static final String mainConfig="mainconfig";


    //tab settings
    public static final String key_tab_item_display="key_tab_item_display";
    public static final String key_tab_item_gravity="key_tab_item_gravity";
    public static final String key_tab_item_mode="key_tab_item_mode";

    public static  String tab_item_display="title";/*title,icon,both*/
    public static  String tab_item_gravity="center";/*center,fill*/
    public static String  tab_item_mode="scrollable";/*scrollable,fixed*/

    //theme
    public static final String key_app_theme="key_app_theme";
    public static  String app_theme="default";/*default,dark*/



    public static void loadDefulatSettings(){
        SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE);
        tab_item_display=sharedPreferences.getString(key_tab_item_display,tab_item_display);
        tab_item_gravity=sharedPreferences.getString(key_tab_item_gravity,tab_item_gravity);
        tab_item_mode=sharedPreferences.getString(key_tab_item_mode,tab_item_mode);
        app_theme=sharedPreferences.getString(key_app_theme,app_theme);

        sharedPreferences.registerOnSharedPreferenceChangeListener((sharedPreferences1, key) -> {
            if(key.equals(key_tab_item_display) || key.equals(key_tab_item_gravity) || key.equals(key_tab_item_mode)){
                Intent intent = new Intent("something_on_tab_has_changed");
                LocalBroadcastManager.getInstance(MainApplication.applicationContext).sendBroadcast(intent);
                FileLog.write("wah the fauck is wrong ith you");
            }
        });

    }

//    public static void saveDefualtSettings() {
//        SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString(key_tab_item_display,tab_item_display).apply();
//        editor.putString(key_tab_item_gravity,key_tab_item_gravity).apply();
//        editor.putString(key_tab_item_mode,tab_item_mode).apply();
//
//    }

    //tab setter and getter from shared pref file
    public static String getTabGravity(){
        return MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE).getString(key_tab_item_gravity,tab_item_gravity);
    }
    public static String getTabMode(){
        return MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE).getString(key_tab_item_mode,tab_item_mode);
    }
    public static String getTabDisplay(){
        return MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE).getString(key_tab_item_display,tab_item_display);
    }
    public static void setTabGravity(String gravity){
        SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key_tab_item_gravity,gravity).apply();
    }
    public static void setTabMode(String mode){
        SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key_tab_item_mode,mode).apply();

    }
    public static void setTabDisplay(String display){
        SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key_tab_item_display,display).apply();
    }
    public static String getAppTheme(){
        return MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE).getString(key_app_theme,app_theme);
    }

    public static void setAppTheme(String theme){
        SharedPreferences sharedPreferences=MainApplication.applicationContext.getSharedPreferences(mainConfig, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key_app_theme,theme).commit();
    }



    public static List<CategoryChooserBottomSheet.TabItem> getTabIconList(Context context){
        List<CategoryChooserBottomSheet.TabItem> tabItems =new ArrayList<>();
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.top_stories),R.drawable.ic_top_stories,true,1));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.technology),R.drawable.ic_technology,false,2));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.world),R.drawable.ic_world,false,3));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.sport),R.drawable.ic_sport,false,4));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.politics),R.drawable.ic_politics,false,5));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.business),R.drawable.ic_business,false,6));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.entertianment),R.drawable.ic_entertianment,false,7));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.social),R.drawable.ic_social,false,8));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.art_and_culture),R.drawable.ic_art_culuture,false,9));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.health),R.drawable.ic_health,false,10));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.videos),R.drawable.ic_video,false,11));
        tabItems.add(new CategoryChooserBottomSheet.TabItem(context.getString(R.string.audio),R.drawable.ic_sound,false,12));
         return tabItems;
    }



}
