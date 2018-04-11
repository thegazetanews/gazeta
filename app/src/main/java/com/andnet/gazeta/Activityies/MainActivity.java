package com.andnet.gazeta.Activityies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import com.andnet.gazeta.Fragments.FavoriteFragment;
import com.andnet.gazeta.Fragments.HomeFragment;
import com.andnet.gazeta.Fragments.LibraryFragment;
import com.andnet.gazeta.Fragments.ReadLaterFragment;
import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.Componenet.CustomSwitchCompat;
import com.andnet.gazeta.ui.CustomNavigationView;
import com.andnet.gazeta.ui.Dialogs.ArticleViewModeBottomSheetDialog;
import com.andnet.gazeta.ui.Dialogs.CategoryChooserBottomSheet;
import com.andnet.gazeta.ui.Dialogs.ColorPickerBottomSheetDialog;
import com.andnet.gazeta.ui.Dialogs.FontChooserBottomSheetDialog;
import com.andnet.gazeta.ui.Dialogs.SourceDialogFragment;
import com.andnet.gazeta.ui.Dialogs.TabControlBottomSheet;
import com.andnet.gazeta.ui.Dialogs.TextsizeChooserBottomSheetDialog;


public class MainActivity extends AppCompatActivity implements CategoryChooserBottomSheet.OnTabItemChanged,ColorPickerBottomSheetDialog.OnMainAppThemeChanged{

    public static final String HOME_TAG = "home";
    public static final String MY_NEWS_TAG = "my_news_tag";
    public static final String READ_LATER_TAG = "read_later";
    public static final String FAV_TAG = "fav";

    private int baseContentId = R.id.content;

    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private ReadLaterFragment readLaterFragment;
    private FavoriteFragment favoriteFragment;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CustomNavigationView bottomNavigation;

    private void init() {
        navigationView = findViewById(R.id.sideNav);
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener);
        bottomNavigation.updateTheme();
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        Menu menu=navigationView.getMenu();
        SwitchCompat switchCompat=(SwitchCompat)menu.findItem(R.id.setting_dark_theme).getActionView();
        if( PreferenceUtility.getAppTheme().equals("dark")){
            switchCompat.setChecked(true);
        }else{
            switchCompat.setChecked(false);
        }
        if(PreferenceUtility.getAppTheme().equals("dark")){
            menu.findItem(R.id.setting_theme).setEnabled(false);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(PreferenceUtility.getAppTheme().equals("dark")){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        PreferenceUtility.loadDefulatSettings();
        setContentView(R.layout.activity_main);
        init();
        placeFragment();
        LocalBroadcastManager.getInstance(this).registerReceiver(tabChangeReceiver, new IntentFilter("something_on_tab_has_changed"));
    }

    private BroadcastReceiver tabChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(getSupportFragmentManager().findFragmentById(baseContentId) instanceof HomeFragment){
                homeFragment=(HomeFragment)getSupportFragmentManager().findFragmentById(baseContentId);
                homeFragment.realodTabItems();
            }
        }

    };


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(baseContentId) instanceof HomeFragment){
            homeFragment=(HomeFragment) getSupportFragmentManager().findFragmentById(baseContentId);
            if(homeFragment.isSearch()){
                homeFragment.backPressed();
                return;
            }
        }else if(getSupportFragmentManager().findFragmentById(baseContentId) instanceof LibraryFragment){
            libraryFragment=(LibraryFragment) getSupportFragmentManager().findFragmentById(baseContentId);
            if(libraryFragment.isSearch()){
                libraryFragment.backPressed();
                return;
            }
        }
        if(getSupportFragmentManager().getFragments().size()==1)getSupportFragmentManager().popBackStackImmediate();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(tabChangeReceiver);
        super.onDestroy();
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }


      private BottomNavigationView.OnNavigationItemSelectedListener onBottomNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.bottom_nav_home:
                    homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_TAG);
                    if (homeFragment != null) {
                        //replace whatever on the @id=R.id.content with the home fragment
                        ft.replace(baseContentId, homeFragment);
                    } else {
                        //create a new home fragment and replace it with thi new fragment
                        homeFragment = new HomeFragment();
                        ft.replace(baseContentId, homeFragment, HOME_TAG);
                    }
                    break;
                case R.id.bottom_nav_my_news:
                    libraryFragment = (LibraryFragment) getSupportFragmentManager().findFragmentByTag(MY_NEWS_TAG);
                    if (libraryFragment != null) {
                        ft.replace(baseContentId, libraryFragment);
                    } else {
                        libraryFragment = new LibraryFragment();
                        ft.replace(baseContentId, libraryFragment, MY_NEWS_TAG);
                    }
                    break;
                case R.id.bottom_nav_read_later:
                    readLaterFragment = (ReadLaterFragment) getSupportFragmentManager().findFragmentByTag(READ_LATER_TAG);
                    if (readLaterFragment != null) {
                        ft.replace(baseContentId, readLaterFragment);
                    } else {
                        readLaterFragment = new ReadLaterFragment();
                        ft.replace(baseContentId, readLaterFragment, READ_LATER_TAG);

                    }
                    break;
                case R.id.bottom_nav_fav:
                    favoriteFragment = (FavoriteFragment) getSupportFragmentManager().findFragmentByTag(FAV_TAG);
                    if (favoriteFragment != null) {
                        ft.replace(baseContentId, favoriteFragment);
                    } else {
                        favoriteFragment = new FavoriteFragment();
                        ft.replace(baseContentId, favoriteFragment, FAV_TAG);

                    }
                    break;
            }
            ft.addToBackStack(null);
            ft.commit();
            return true;

        }
    };
      private void placeFragment(){
         FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof HomeFragment) {
             homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_TAG);
             if (homeFragment != null) {
                 //replace whatever on the @id=R.id.content with the home fragment
                 ft.replace(baseContentId, homeFragment);
             } else {
                 //create a new home fragment and replace it with thi new fragment
                 homeFragment = new HomeFragment();
                 ft.replace(baseContentId, homeFragment, HOME_TAG);
             }

         } else if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof LibraryFragment) {
             //the content frame holds the LibraryFragment
             //get the my_news_fragment from the fragment manager if it exist else return null
             libraryFragment = (LibraryFragment) getSupportFragmentManager().findFragmentByTag(MY_NEWS_TAG);
             if (libraryFragment != null) {
                 ft.replace(baseContentId, libraryFragment);
             } else {
                 libraryFragment = new LibraryFragment();
                 ft.replace(baseContentId, libraryFragment, MY_NEWS_TAG);
             }
         }  else if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof FavoriteFragment) {
             //the content frame holds the LibraryFragment
             //get the my_news_fragment from the fragment manager if it exist else return null
             favoriteFragment = (FavoriteFragment) getSupportFragmentManager().findFragmentByTag(FAV_TAG);
             if (favoriteFragment != null) {
                 ft.replace(baseContentId, favoriteFragment);
             } else {
                 favoriteFragment = new FavoriteFragment();
                 ft.replace(baseContentId, favoriteFragment, FAV_TAG);
             }
         }
         else if (getSupportFragmentManager().findFragmentById(R.id.content) instanceof ReadLaterFragment) {
             //the content frame holds the ReadLaterFragment
             readLaterFragment = (ReadLaterFragment) getSupportFragmentManager().findFragmentByTag(READ_LATER_TAG);
             if (readLaterFragment != null) {
                 ft.replace(baseContentId, readLaterFragment);
             } else {
                 readLaterFragment = new ReadLaterFragment();
                 ft.replace(baseContentId, readLaterFragment, READ_LATER_TAG);
             }

         } else {
             //the content frame does'nt hold any thing
             homeFragment = new HomeFragment();
             ft.add(baseContentId, homeFragment, HOME_TAG);
         }
         ft.addToBackStack(null);
         ft.commit();
     }

      private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener= item -> {
         switch (item.getItemId()){
             case R.id.setting_category:
                 CategoryChooserBottomSheet categoryChooserBottomSheet=new CategoryChooserBottomSheet();
                 categoryChooserBottomSheet.show(getSupportFragmentManager(),categoryChooserBottomSheet.getTag());
                 break;
             case R.id.setting_source:
                 SourceDialogFragment sourceDialogFragment=new SourceDialogFragment();
                 sourceDialogFragment.show(getSupportFragmentManager(),sourceDialogFragment.getTag());
                 break;
             case R.id.setting_open_news_in_browser:
                 break;
             case R.id.setting_dark_theme:
                 Menu menu=navigationView.getMenu();
                 CustomSwitchCompat switchCompat=(CustomSwitchCompat) menu.findItem(R.id.setting_dark_theme).getActionView();
                 switchCompat.updateTheme();
                 if( PreferenceUtility.getAppTheme().equals("dark")){
                     switchCompat.setChecked(false);
                     PreferenceUtility.setAppTheme("default");
                     PreferenceUtility.setAppMainThemeColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
                     recreate();
                 }else{
                     switchCompat.setChecked(true);
                     PreferenceUtility.setAppTheme("dark");
                     PreferenceUtility.setAppMainThemeColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
                     recreate();
                 }
                 break;
             case R.id.setting_app_language:
                 break;
             case R.id.setting_font:
                 FontChooserBottomSheetDialog fontChooserBottomSheetDialog=new FontChooserBottomSheetDialog();
                 fontChooserBottomSheetDialog.show(getSupportFragmentManager(),fontChooserBottomSheetDialog.getTag());
                 break;
             case R.id.setting_notification:
                 break;
             case R.id.setting_image_loading:
                 break;
             case R.id.setting_theme:
                 ColorPickerBottomSheetDialog colorPickerBottomSheetDialog=new ColorPickerBottomSheetDialog();
                 colorPickerBottomSheetDialog.show(getSupportFragmentManager(),colorPickerBottomSheetDialog.getTag());
                 break;
             case R.id.setting_aritcle_view_mode:
                 ArticleViewModeBottomSheetDialog articleViewModeBottomSheetDialog=new ArticleViewModeBottomSheetDialog();
                 articleViewModeBottomSheetDialog.show(getSupportFragmentManager(),articleViewModeBottomSheetDialog.getTag());
                 break;
             case R.id.settings_article_text_size:
                 TextsizeChooserBottomSheetDialog textsizeChooserBottomSheetDialog=new TextsizeChooserBottomSheetDialog();
                 textsizeChooserBottomSheetDialog.show(getSupportFragmentManager(),textsizeChooserBottomSheetDialog.getTag());                 break;
             case R.id.setting_tab_contorl:
                 TabControlBottomSheet tabControlBottomSheet=new TabControlBottomSheet();
                 tabControlBottomSheet.show(getSupportFragmentManager(),tabControlBottomSheet.getTag());
                 break;
         }
         drawerLayout.closeDrawer(Gravity.START);
         return false;
     };



    @Override
    public void tabchanged() {
         if(getSupportFragmentManager().findFragmentById(baseContentId) instanceof HomeFragment){
             homeFragment=(HomeFragment)getSupportFragmentManager().findFragmentById(baseContentId);
             homeFragment.updateTabItems();
         }
    }

    @Override
    public void appThemeChanged() {
        Fragment fragment=getSupportFragmentManager().findFragmentById(baseContentId);
        if(fragment instanceof HomeFragment){
            ((HomeFragment) fragment).updateThemeColor();
            if(bottomNavigation!=null)bottomNavigation.updateTheme();
        }else if(fragment instanceof LibraryFragment){


        }else if(fragment instanceof FavoriteFragment){



        }else if(fragment instanceof ReadLaterFragment){

        }
    }
}


