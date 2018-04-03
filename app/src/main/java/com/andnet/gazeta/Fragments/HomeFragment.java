package com.andnet.gazeta.Fragments;


import android.content.Context;
import android.graphics.Typeface;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andnet.gazeta.Activityies.MainActivity;
import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.PreferenceUtility;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.CustomTabLayout;
import com.andnet.gazeta.ui.CustomToolbar;
import com.andnet.gazeta.ui.MenuDrawable;
import com.andnet.gazeta.ui.Theme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment{
    private ViewPager viewPager;
    private CustomTabLayout tabLayout;
    private CustomToolbar toolbar;
    private View mainView;
    private AppBarLayout appBarLayout;

    private HomeFragmentPagerAdapter homeFragmentPagerAdapter;
    private MenuDrawable menuDrawable;
    private boolean search=false;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.home_fragment, container, false);
        init();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return mainView;
    }

    private void init(){
        appBarLayout=mainView.findViewById(R.id.appBar);
        viewPager =  mainView.findViewById(R.id.viewpager);
        tabLayout= mainView.findViewById(R.id.tabLayout);
        toolbar= mainView.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.updateTheme();
        menuDrawable=new MenuDrawable();
        toolbar.setNavigationIcon(menuDrawable);
        toolbar.setNavigationOnClickListener(v -> {
            if(!search){
                if(getActivity() instanceof  MainActivity){
                    MainActivity mainActivity=(MainActivity)getActivity();
                    DrawerLayout drawerLayout=mainActivity.getDrawerLayout();
                    if(drawerLayout.isDrawerOpen(Gravity.START)){
                        drawerLayout.closeDrawer(Gravity.START);
                    }else {
                        drawerLayout.openDrawer(Gravity.START);
                    }
                }
            }else {
            }
        });

        homeFragmentPagerAdapter=new HomeFragmentPagerAdapter(getChildFragmentManager(),getActivity());
        ((TextView)mainView.findViewById(R.id.gazetaTextView)).setTypeface(Typeface.createFromAsset(getResources().getAssets(),"geez.ttf"));
        viewPager.setAdapter(homeFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        realodTabItems();
    }

    public void realodTabItems(){
        if(tabLayout==null)return;
        if(homeFragmentPagerAdapter==null)return;
        if(toolbar==null)return;
        updateTheme();
    }


    public void updateTheme(){
        toolbar.updateTheme();
        tabLayout.setSelectedTabIndicatorColor(Theme.tab_indictor_color);
        tabLayout.setTabTextColors( Theme.tab_layout_item_text_color,  Theme.tab_layout_item_selected_text_color);
        tabLayout.setBackgroundColor(Theme.toolbar_background_color);

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
