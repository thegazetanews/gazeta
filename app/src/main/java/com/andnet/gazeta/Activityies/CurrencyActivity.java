package com.andnet.gazeta.Activityies;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.andnet.gazeta.Adapters.ViewPagerAdapters.CurrencyPagerAdapter;
import com.andnet.gazeta.Helper.Constants;
import com.andnet.gazeta.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CurrencyActivity extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase;
    private ViewPager viewPager;
    private CurrencyPagerAdapter bankPagerAdapter;
    private TabLayout tabLayout;
    private FrameLayout progressLayout;
    private List<String> banksNameList=new ArrayList<>();
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        progressLayout=findViewById(R.id.progressLayout);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> CurrencyActivity.super.onBackPressed());
        reference();
    }

    private void reference() {
        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference reference=firebaseDatabase.getReference().child(Constants.ETHIOPIA).child("banks");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reference.removeEventListener(this);
                dataSnapshot.getChildren();
                if(dataSnapshot.exists()){
                Iterable<DataSnapshot> dataSnapshots=dataSnapshot.getChildren();
                for(DataSnapshot data:dataSnapshots){
                    banksNameList.add(data.getKey());
                }
                bankPagerAdapter=new CurrencyPagerAdapter(getSupportFragmentManager(),banksNameList);
                viewPager.setAdapter(bankPagerAdapter);
                if(viewPager.getVisibility()!=View.VISIBLE)
                crossFade();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void crossFade(){

        progressLayout.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressLayout.setVisibility(View.GONE);
                    }
                });
        viewPager.setAlpha(0f);
        viewPager.setVisibility(View.VISIBLE);
        viewPager.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null);
    }


}
