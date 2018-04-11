package com.andnet.gazeta.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andnet.gazeta.Adapters.RecyclerViewAdapter.LibraryAdapter;
import com.andnet.gazeta.Activityies.MainActivity;
import com.andnet.gazeta.AndroidUtilities;
import com.andnet.gazeta.Models.Library;
import com.andnet.gazeta.Preference.SettingActivity;
import com.andnet.gazeta.R;
import com.andnet.gazeta.Helper.ColumnQty;
import com.andnet.gazeta.ui.CustomTabLayout;
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


    private CustomToolbar toolbar;
    private View mainView;
    private EditText searchEditText;
    private RecyclerView searchRv;
    private RecyclerView sourceRv;
    private DrawerLayout drawerLayout;
    private LibraryAdapter libraryAdapter;
    private List<Library> libraryList=new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;

    private MenuDrawable menuDrawable;
    private boolean search=false;

    private RelativeLayout infoLayout;
    private ImageView infoImageImageView;
    private TextView infoTextView;
    private RelativeLayout progressLayout;

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  final Bundle savedInstanceState) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        mainView=inflater.inflate(R.layout.fragment_library,container,false);
        init();
        libraryAdapter=new LibraryAdapter(getContext());
        sourceRv.setLayoutManager(new GridLayoutManager(getContext(),4));
        sourceRv.setAdapter(libraryAdapter);
        getLibrariesFromFiresBase();
        return mainView;
    }

    private void getLibrariesFromFiresBase() {
        DatabaseReference databaseReference= firebaseDatabase.getReference("source");
        databaseReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    setInfo(R.drawable.ic_empty_error,getString(R.string.no_news_in_this_catagory),true);
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
                crossFade();
            }
            public void onCancelled(DatabaseError databaseError) {
                setInfo(R.drawable.ic_empty_error,databaseError.getMessage(),true);
            }
        });

    }

    private void crossFade(){

        if(sourceRv.getVisibility()==View.VISIBLE)return;
        progressLayout.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressLayout.setVisibility(View.GONE);
                    }
                });
        sourceRv.setAlpha(0f);
        sourceRv.setVisibility(View.VISIBLE);
        sourceRv.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);

    }


    private void setInfo(int res,String info,boolean needImage){
        if(progressLayout.getVisibility()==View.VISIBLE){
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
        if(infoLayout.getVisibility()!=View.VISIBLE){
            infoLayout.setAlpha(0f);
            infoLayout.setVisibility(View.VISIBLE);
            infoLayout.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);
        }
        if(needImage) {
            infoImageImageView.setImageResource(res);
        }
        infoTextView.setText(info);
    }
    private void init(){
        sourceRv =  mainView.findViewById(R.id.sourceRv);
        progressLayout= mainView.findViewById(R.id.progressLayout);
        toolbar= mainView.findViewById(R.id.toolbar);
        searchEditText=mainView.findViewById(R.id.searchEdiText);
        searchRv=mainView.findViewById(R.id.searchRv);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setTitle(R.string.my_news);
        menuDrawable=new MenuDrawable();
        toolbar.setNavigationIcon(menuDrawable);


        infoLayout=mainView.findViewById(R.id.infoView);
        infoImageImageView=mainView.findViewById(R.id.info_image_view);
        infoTextView=mainView.findViewById(R.id.infoTextView);

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
    }

    private void showSearchView(boolean b) {

        if(b){
            sourceRv.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            sourceRv.setVisibility(View.GONE);
                        }
                    });

            searchRv.setAlpha(0f);
            searchRv.setVisibility(View.VISIBLE);
            searchRv.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);

            if(search){
                progressLayout.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                progressLayout.setVisibility(View.GONE);
                            }
                        });

                searchRv.setAlpha(0f);
                searchRv.setVisibility(View.VISIBLE);
                searchRv.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .setListener(null);

            }

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


            sourceRv.setAlpha(0f);
            sourceRv.setVisibility(View.VISIBLE);
            sourceRv.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);
        }

    }

    public boolean isSearch() {
        return search;
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
}
