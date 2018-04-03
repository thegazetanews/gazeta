package com.andnet.gazeta.Activityies;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.andnet.gazeta.Adapters.ViewPagerAdapters.SourceDetailPagerAdapter;
import com.andnet.gazeta.Helper.Constants;
import com.andnet.gazeta.Models.Library;
import com.andnet.gazeta.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class LibDetailActivity extends AppCompatActivity{

    public static final String LIBRARY_CONST="library";
    private Toolbar toolbar;
    private ViewPager viewpager;
    private Library library;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> keyList=new ArrayList<>();
    private TabLayout tabLayout;
    private SourceDetailPagerAdapter sourceDetailPagerAdapter;
    private FirebaseDatabase firebaseDatabase;
    private AppBarLayout appBarLayout;

    private List<String> sourceKeyList=new ArrayList<>();
    private List<String> sourceCatNameList=new ArrayList<>();


    private FrameLayout progresFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        setContentView(R.layout.activity_lib_detial);
        progresFrame=findViewById(R.id.progresFrame);
        firebaseDatabase=FirebaseDatabase.getInstance();
        toolbar =findViewById(R.id.toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        viewpager =  findViewById(R.id.viewpager);
        library = (Library) getIntent().getSerializableExtra(LibDetailActivity.LIBRARY_CONST);
        this.context = this;
        toolbar.setOnMenuItemClickListener(menuItemClickListener);
        toolbar.setNavigationOnClickListener(view -> LibDetailActivity.this.onBackPressed());
        toolbar.setTitle(library.getName());
        toolbar.inflateMenu(R.menu.source_menu);
        tabLayout=findViewById(R.id.tabLayout);
        appBarLayout=findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> ViewCompat.setElevation(appBarLayout, 20));
        getSourceCatFromFirebase();

 }

    private void crossFade(){

        progresFrame.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progresFrame.setVisibility(View.GONE);
                    }
                });
        viewpager.setAlpha(0f);
        viewpager.setVisibility(View.VISIBLE);
        viewpager.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);

    }



    public void getSourceCatFromFirebase(){
        DatabaseReference databaseReference=firebaseDatabase.getReference(Constants.ETHIOPIA).child("sourses").child(library.getSearch_term());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot shots: dataSnapshots){
                        sourceKeyList.add(shots.getKey());
                        sourceCatNameList.add(shots.getValue() + "");
                }
                if(sourceCatNameList.isEmpty()){
                    addSource();
                }else {
                    addTabs();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void addSource() {

        toolbar.setTitle(library.getName());
        sourceDetailPagerAdapter =new SourceDetailPagerAdapter(getSupportFragmentManager(),sourceKeyList,sourceCatNameList,false);
        viewpager.setAdapter(sourceDetailPagerAdapter);
        tabLayout.setupWithViewPager(viewpager);
        crossFade();

    }

    public void addTabs(){
     sourceDetailPagerAdapter =new SourceDetailPagerAdapter(getSupportFragmentManager(),sourceKeyList,sourceCatNameList,true);
     viewpager.setAdapter(sourceDetailPagerAdapter);
     tabLayout.setupWithViewPager(viewpager);
     crossFade();

 }

    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferencesListner=new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if("pref_key_enable_image_loading".equals(s)){

            }


        }
    };

    Toolbar.OnMenuItemClickListener menuItemClickListener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
//            switch (item.getItemId()){
//                case R.id.go_to_orginal_website:
//                    ChromeBrowser.openUrl(LibDetailActivity.this,library.getLink());
//                    break;
//                case R.id.text_size:
//                    TextSizeChooserFragment simpleNewsTextSizeChooserFragment=new TextSizeChooserFragment();
//                    simpleNewsTextSizeChooserFragment.show(getSupportFragmentManager(),"TEXT_SIZE_CHOOSER");
//                    break;
//                case R.id.setting:
//                    Intent intent=new Intent(LibDetailActivity.this, SettingActivity.class);
//                     startActivity(intent);
//                    break;
//
//            }
            return true;
        }
    };



    public Library getLibrary(){
        return library;
    }

}
