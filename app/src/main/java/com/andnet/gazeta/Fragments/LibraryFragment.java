package com.andnet.gazeta.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.andnet.gazeta.Adapters.RecyclerViewAdapter.LibraryAdapter;
import com.andnet.gazeta.Helper.Connection;
import com.andnet.gazeta.Activityies.MainActivity;
import com.andnet.gazeta.Models.Library;
import com.andnet.gazeta.Preference.SettingActivity;
import com.andnet.gazeta.R;
import com.andnet.gazeta.Helper.ColumnQty;
import com.andnet.gazeta.ui.CustomToolbar;
import com.andnet.gazeta.ui.MenuDrawable;
import com.andnet.gazeta.ui.RadialProgressView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class LibraryFragment extends Fragment{

    private View mainView;
    private CustomToolbar toolbar;
    private RecyclerView recyclerView;
    private RadialProgressView progressLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Library> libraryList=new ArrayList<>();
    private LibraryAdapter libraryAdapter;
    private DrawerLayout drawerLayout;
    private FirebaseDatabase firebaseDatabase;
    private AppBarLayout appBarLayout;
    private RelativeLayout relativeLayout;
    private boolean search=false;

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  final Bundle savedInstanceState) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        mainView=inflater.inflate(R.layout.my_news_layout,container,false);
        progressLayout=mainView.findViewById(R.id.item_progress_bar);
        recyclerView=mainView.findViewById(R.id.rv);
        toolbar=mainView.findViewById(R.id.toolbar);
        appBarLayout=mainView.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> ViewCompat.setElevation(appBarLayout, 4));
        swipeRefreshLayout=mainView.findViewById(R.id.swipeToRefresh);
        relativeLayout=mainView.findViewById(R.id.coud_not_frame);
        swipeRefreshLayout.setOnRefreshListener(this::getLibrariesFromFiresBase);
        ColumnQty columnQty=new ColumnQty(getContext(),R.layout.lib_layout);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),columnQty.calculateNoOfColumns());
        recyclerView.setLayoutManager(gridLayoutManager);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListner);
        Drawable menuDrawable=new MenuDrawable();
        toolbar.setNavigationIcon(menuDrawable);
        toolbar.setTitle(getString(R.string.my_news));
        toolbar.updateTheme();
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
            }
        });

        MainActivity activity=(MainActivity)getActivity();
        drawerLayout=activity.getDrawerLayout();

//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.ok, R.string.cancel){
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//        };
//        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        libraryAdapter=new LibraryAdapter(getContext());
//        recyclerView.setAdapter(libraryAdapter);
//            if(!Connection.isConnected(getContext())){
//                Snackbar snackbar = Snackbar
//                        .make(mainView.findViewById(R.id.library_cordinate), getString(R.string.no_connation), Snackbar.LENGTH_LONG);
//                snackbar.show();
//            }
//        getLibrariesFromFiresBase();
        return mainView;
    }

    private void getLibrariesFromFiresBase() {
        DatabaseReference databaseReference= firebaseDatabase.getReference("source");
        databaseReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    showNosourceFound();
                    return;
                }
                libraryList.clear();
                Iterable<DataSnapshot> snapshotList=dataSnapshot.getChildren();
                for(DataSnapshot data: snapshotList){
                    Library myLib=data.getValue(Library.class);
                    if(myLib!=null){
                        myLib.setSearch_term(data.getKey());
                        libraryList.add(myLib);
                    }
                }
                libraryAdapter.setLibraryList(libraryList);
                libraryAdapter.notifyDataSetChanged();
                if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
                crossFade();
            }
            public void onCancelled(DatabaseError databaseError) {
                showNosourceFound();
                if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void showNosourceFound(){
        relativeLayout.setAlpha(0f);
        relativeLayout.setVisibility(View.VISIBLE);
        relativeLayout.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);
        progressLayout.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressLayout.setVisibility(View.GONE);

                    }
                });
    }


    private void crossFade(){

        recyclerView.setAlpha(0f);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);
        progressLayout.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressLayout.setVisibility(View.GONE);

                    }
                });
    }

//    private void setOrientation(){
//
//        int screenSize=getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
//        int orientation=getResources().getConfiguration().orientation;
//
//        if(orientation==Configuration.ORIENTATION_PORTRAIT){
//
//            if(screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
//            {
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),5));
//
//
//            }else if(screenSize==Configuration.SCREENLAYOUT_SIZE_NORMAL){
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
//
//
//            }else if(screenSize==Configuration.SCREENLAYOUT_SIZE_SMALL){
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//
//
//            }else if(screenSize==Configuration.SCREENLAYOUT_SIZE_LARGE){
//
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),5));
//
//
//            }
//
//        }else {
//
//            if(screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
//            {
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),6));
//
//
//            }else if(screenSize==Configuration.SCREENLAYOUT_SIZE_NORMAL){
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
//
//
//            }else if(screenSize==Configuration.SCREENLAYOUT_SIZE_SMALL){
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
//
//
//            }else if(screenSize==Configuration.SCREENLAYOUT_SIZE_LARGE){
//
//                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),5));
//
//
//            }
//        }
//
//
//    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListner= item -> {
        switch (item.getItemId()){
            case R.id.setting:
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                getContext().startActivity(intent);
                return true;
            case R.id.refresh:
                getLibrariesFromFiresBase();
                return true;
        }
        return false;
    };


    public void onPause() {
        super.onPause();

    }
}
