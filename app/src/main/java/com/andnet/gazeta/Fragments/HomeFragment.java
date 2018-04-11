package com.andnet.gazeta.Fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.andnet.gazeta.Activityies.MainActivity;
import com.andnet.gazeta.AndroidUtilities;
import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.CustomTabLayout;
import com.andnet.gazeta.ui.CustomToolbar;
import com.andnet.gazeta.ui.MenuDrawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment{

    private ViewPager viewPager;
    private CustomTabLayout tabLayout;
    private CustomToolbar toolbar;
    private View mainView;
    private EditText searchEditText;
    private RecyclerView searchRv;
    private AppBarLayout appBarLayout;

    private HomeFragmentPagerAdapter homeFragmentPagerAdapter;
    private MenuDrawable menuDrawable;
    private boolean search=false;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.home_fragment, container, false);
        init();
        return mainView;
    }

    private void init(){
        appBarLayout=mainView.findViewById(R.id.appBar);
        if(PreferenceUtility.getAppTheme().equals("dark"))
            PreferenceUtility.setAppMainThemeColor(0xff212121);
        appBarLayout.setBackgroundColor(PreferenceUtility.getMainAppThemeColor());
        viewPager =  mainView.findViewById(R.id.viewpager);
        tabLayout= mainView.findViewById(R.id.tabLayout);
        toolbar= mainView.findViewById(R.id.toolbar);
        searchEditText=mainView.findViewById(R.id.searchEdiText);
        searchRv=mainView.findViewById(R.id.searchRv);
        toolbar.inflateMenu(R.menu.main_menu);
        menuDrawable=new MenuDrawable();
        toolbar.setNavigationIcon(menuDrawable);
        toolbar.setTitleTextColor(Color.WHITE);
        tabLayout.updateTheme();

        toolbar.setOnMenuItemClickListener(item -> {
            searchEditText.setVisibility(View.VISIBLE);
            menuDrawable.setRotation(1f,true);
            toolbar.getMenu().getItem(0).setVisible(false);
            searchEditText.requestFocus();
            showSearchView(true);
            AndroidUtilities.showKeyBoared(searchEditText);
            search=true;
            return false;
        });

        toolbar.setNavigationOnClickListener(v -> {
            if(search){
                searchEditText.setVisibility(View.GONE);
                menuDrawable.setRotation(0f,true);
                toolbar.getMenu().getItem(0).setVisible(true);
                search=false;
                showSearchView(false);
                AndroidUtilities.hideKeyboard(searchEditText);
            }else{
                MainActivity mainActivity=(MainActivity)getActivity();
                DrawerLayout drawerLayout=mainActivity.getDrawerLayout();
                if(drawerLayout.isDrawerOpen(Gravity.START)){
                    drawerLayout.closeDrawer(Gravity.START);
                }else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });

        homeFragmentPagerAdapter=new HomeFragmentPagerAdapter(getChildFragmentManager(),getActivity());
        viewPager.setAdapter(homeFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        realodTabItems();
    }

    private void showSearchView(boolean b) {

        if(b){
            viewPager.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            viewPager.setVisibility(View.GONE);
                        }
                    });
            tabLayout.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            tabLayout.setVisibility(View.GONE);
                        }
                    });
            searchRv.setAlpha(0f);
            searchRv.setVisibility(View.VISIBLE);
            searchRv.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);

        }else {

            searchRv.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            searchRv.setVisibility(View.GONE);
                        }
                    });

            tabLayout.setAlpha(0f);
            tabLayout.setVisibility(View.VISIBLE);
            tabLayout.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);

            viewPager.setAlpha(0f);
            viewPager.setVisibility(View.VISIBLE);
            viewPager.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);


        }

    }

    public void realodTabItems(){
        if(tabLayout==null)return;
        if(homeFragmentPagerAdapter==null)return;
        if(toolbar==null)return;
        updateTheme();
    }

    public void updateThemeColor(){
        if(appBarLayout!=null){
            if(PreferenceUtility.getAppTheme().equals("dark"))
                PreferenceUtility.setAppMainThemeColor(0xff212121);
            appBarLayout.setBackgroundColor(PreferenceUtility.getMainAppThemeColor());
        }
    }

    public void updateTheme(){
        switch (PreferenceUtility.getTabGravity()){
            case "center":
                tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
                break;
            case "fill":
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                break;
        }

        switch (PreferenceUtility.getTabMode()){
            case "scrollable":
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                break;
            case "fixed":
                tabLayout.setTabMode(TabLayout.MODE_FIXED);
                break;
        }

        List<Category> categoryList= GazetaDatabase.getDatabase(getContext()).dao().getAllVisibleCategories();
        if(PreferenceUtility.getTabDisplay().equals("icon") && categoryList.size()==tabLayout.getTabCount()){
            for(int i=0;i<tabLayout.getTabCount();i++){
                TabLayout.Tab tab=tabLayout.getTabAt(i);
                if(tab!=null){
                    tab.setIcon(categoryList.get(i).getRes());
                    tab.setText("");

                }
            }
        }else  if(PreferenceUtility.getTabDisplay().equals("both") && categoryList.size()==tabLayout.getTabCount()){
            for(int i=0;i<tabLayout.getTabCount();i++){
                TabLayout.Tab tab=tabLayout.getTabAt(i);
                if(tab!=null){
                    tab.setIcon(categoryList.get(i).getRes());
                    tab.setText(categoryList.get(i).getName());
                }
            }
        }else if(PreferenceUtility.getTabDisplay().equals("title")){
            for(int i=0;i<tabLayout.getTabCount();i++){
                TabLayout.Tab tab=tabLayout.getTabAt(i);
                if(tab!=null){
                    tab.setIcon(null);
                    tab.setText(categoryList.get(i).getName());
                }
            }
        }

    }

    public void updateTabItems(){
        if(homeFragmentPagerAdapter!=null){
            homeFragmentPagerAdapter.notifyDataSetChanged();
        }
    }

    public void backPressed() {
        if(search){
            searchEditText.setVisibility(View.GONE);
            menuDrawable.setRotation(0f,true);
            toolbar.getMenu().getItem(0).setVisible(true);
            search=false;
            showSearchView(false);
            AndroidUtilities.hideKeyboard(searchEditText);
        }
    }

     public boolean isSearch() {
        return search;
     }

    public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private List<Category> tabItems =new ArrayList<>();
        private Context context;

        HomeFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context=context;
            init();
        }

        private void init(){
            List<Category> tempItems= GazetaDatabase.getDatabase(context).dao().getAllCatagory();
            tabItems.clear();
            Collections.sort(tempItems, (cat1, cat2) -> {
                if(cat1.getPriority()>cat2.getPriority()){
                    return 1;
                }else{
                    return -1;
                }
            });
            for(Category item:tempItems){
                if(item.isVisibility())tabItems.add(item);
            }
        }

        @Override
        public void notifyDataSetChanged() {
            init();
            super.notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return HomeListFragment.getInstance(tabItems.get(position).getName());
        }

        @Override
        public int getCount() {
            return tabItems!=null?tabItems.size():0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(PreferenceUtility.getTabDisplay().equals("icon")){
                return null;
            }
            return tabItems.get(position).getName();
        }
    }
}
