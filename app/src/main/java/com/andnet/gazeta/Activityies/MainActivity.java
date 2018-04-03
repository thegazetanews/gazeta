package com.andnet.gazeta.Activityies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.andnet.gazeta.Adapters.RecyclerViewAdapter.DrawerLayoutAdapter;
import com.andnet.gazeta.FileLog;
import com.andnet.gazeta.Fragments.FavoriteFragment;
import com.andnet.gazeta.Fragments.HomeFragment;
import com.andnet.gazeta.Fragments.LibraryFragment;
import com.andnet.gazeta.Fragments.ReadLaterFragment;
import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.CustomNavigationView;
import com.andnet.gazeta.ui.Dialogs.CategoryChooserBottomSheet;
import com.andnet.gazeta.ui.Dialogs.SourceDialogFragment;
import com.andnet.gazeta.ui.Dialogs.TabControlBottomSheet;
import com.andnet.gazeta.ui.Theme;

public class MainActivity extends AppCompatActivity implements DrawerLayoutAdapter.OnDrawerItemClickListner,CategoryChooserBottomSheet.OnTabItemChanged{

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
    private RecyclerView sideNavview;
    private FrameLayout frameLayout;
    private CustomNavigationView bottomNavigation;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceUtility.loadDefulatSettings();
        setContentView(R.layout.activity_main);
        init();
        placeFragment();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("something_on_tab_has_changed"));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            FileLog.write("on Recive otuside called");

            if(getSupportFragmentManager().findFragmentById(baseContentId) instanceof HomeFragment){
                homeFragment=(HomeFragment)getSupportFragmentManager().findFragmentById(baseContentId);
                homeFragment.realodTabItems();

            }
        }

    };

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }




    private void init() {
        frameLayout = findViewById(baseContentId);
        sideNavview = findViewById(R.id.siderv);
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.setItemBackgroundResource(R.drawable.item_background);
        bottomNavigation.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener);
        bottomNavigation.updateTheme();
        sideNavview.setBackgroundColor(Theme.side_nav_background_color);

        sideNavview.setLayoutManager(new LinearLayoutManager(this));
        DrawerLayoutAdapter adapter=new DrawerLayoutAdapter(this);
        sideNavview.setAdapter(adapter);
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

            }
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
         ft.commit();
     }

    @Override
    public void onItemClicked(int id, boolean on) {
        if(id==2){
            CategoryChooserBottomSheet categoryChooserBottomSheet=new CategoryChooserBottomSheet();
            categoryChooserBottomSheet.show(getSupportFragmentManager(),categoryChooserBottomSheet.getTag());
            drawerLayout.closeDrawer(Gravity.START);
        }else if(id==22){
                TabControlBottomSheet categoryChooserBottomSheet=new TabControlBottomSheet();
                categoryChooserBottomSheet.show(getSupportFragmentManager(),categoryChooserBottomSheet.getTag());
                drawerLayout.closeDrawer(Gravity.START);
        }else if(id==3){
                SourceDialogFragment sourceDialogFragment=new SourceDialogFragment();
                sourceDialogFragment.show(getSupportFragmentManager(),sourceDialogFragment.getTag());
                drawerLayout.closeDrawer(Gravity.START);
        }}



    @Override
    public void tabchanged() {
         if(getSupportFragmentManager().findFragmentById(baseContentId) instanceof HomeFragment){
             homeFragment=(HomeFragment)getSupportFragmentManager().findFragmentById(baseContentId);
             homeFragment.updateTabItems();
         }
    }
}


