package com.andnet.gazeta.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.andnet.gazeta.Activityies.MainActivity;
import com.andnet.gazeta.Adapters.RecyclerViewAdapter.NewsListRecycleViewAdapter;
import com.andnet.gazeta.Databases.GazetaDao;
import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Models.Body;
import com.andnet.gazeta.Models.News;
import com.andnet.gazeta.Preference.SettingActivity;
import com.andnet.gazeta.R;
import com.andnet.gazeta.ui.CustomToolbar;
import com.andnet.gazeta.ui.MenuDrawable;

import java.util.Collections;
import java.util.List;


public class ReadLaterFragment extends Fragment{
    private View mainView;
    private SharedPreferences sharedPreferences;
    private CustomToolbar toolbar;
    private static final int LIBRARY_LOADER = 1;
    private NewsListRecycleViewAdapter newsListRecycleViewAdapter;
    private RecyclerView recyclerView;
    private RelativeLayout no_read_later_layout;
    private DrawerLayout drawerLayout;
    private AppBarLayout appBarLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.read_later_fragment,container,false);
        no_read_later_layout=mainView.findViewById(R.id.no_saved_relative_layout);
        toolbar=mainView.findViewById(R.id.toolbar);
        recyclerView=mainView.findViewById(R.id.rv);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListner);
        toolbar.setTitle(getString(R.string.bookmark));
        Drawable menuDrawable=new MenuDrawable();
        toolbar.setNavigationIcon(menuDrawable);
        toolbar.setTitle(getString(R.string.bookmark));
        appBarLayout=mainView.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> ViewCompat.setElevation(appBarLayout, 4));
        int orientation=getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        }else if(orientation== Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        MainActivity activity=(MainActivity)getActivity();
        drawerLayout=activity.getDrawerLayout();

//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.ok, R.string.cancel){
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//
//            }
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//        };
//        //set the listener to the drawer and sync the state
//        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

        newsListRecycleViewAdapter =new NewsListRecycleViewAdapter(getContext(),"Read Later");
        newsListRecycleViewAdapter.setReadLaterFragment(this);
        recyclerView.setAdapter(newsListRecycleViewAdapter);

        if(GazetaDatabase.getDatabase(getContext()).dao().getAllSavedNews().isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            no_read_later_layout.setVisibility(View.VISIBLE);
        }else {
           List<News> newsList= GazetaDatabase.getDatabase(getContext()).dao().getAllSavedNews();
            Collections.reverse(newsList);
            newsListRecycleViewAdapter.setObjectList(newsList);
            newsListRecycleViewAdapter.notifyDataSetChanged();
        }
        return mainView;
    }



    private Toolbar.OnMenuItemClickListener onMenuItemClickListner= item -> {
        switch (item.getItemId()){
            case R.id.clear_all:
                clearAll();
                break;
            case R.id.text_size:
//                TextSizeChooserFragment simpleNewsTextSizeChooserFragment=new TextSizeChooserFragment();
//                simpleNewsTextSizeChooserFragment.show(getFragmentManager(),"TEXT_SIZE_CHOOSER");
                return true;
            case R.id.setting:
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                getContext().startActivity(intent);
                return true;

        }
        return false;
    };

    private void clearAll() {
        GazetaDao gazetaDao=GazetaDatabase.getDatabase(getContext()).dao();
        if(gazetaDao.getAllSavedNews().isEmpty()){
            changeVisibility();
            return;
        }
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage(("Delete all saved news"));
            builder.setTitle("Delete");
            builder.setNegativeButton(R.string.cancel, (dialogInterface, k) -> dialogInterface.cancel());
            builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                List<News> newsList=gazetaDao.getAllSavedNews();
                List<Body> bodyList=gazetaDao.getAllBody();
                gazetaDao.clearBody(bodyList);
                gazetaDao.clearNews(newsList);
                changeVisibility();
                Snackbar snackbar = Snackbar.make(mainView.findViewById(R.id.home_layout_coordinate), R.string.succ_delete_all_news_msg, Snackbar.LENGTH_SHORT);
                snackbar.show();
                dialogInterface.cancel();
            });
            builder.create().show();
        }

    public void changeVisibility(){
        no_read_later_layout.setAlpha(0f);
        no_read_later_layout.setVisibility(View.VISIBLE);
        no_read_later_layout.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null);

        recyclerView.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        recyclerView.setVisibility(View.GONE);
                    }
                });

    }


}
