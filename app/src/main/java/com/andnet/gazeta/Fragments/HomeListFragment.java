package com.andnet.gazeta.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andnet.gazeta.Adapters.RecyclerViewAdapter.NewsListRecycleViewAdapter;
import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.FileLog;
import com.andnet.gazeta.Helper.Constants;
import com.andnet.gazeta.Helper.EndlessRecyclerViewScrollListener;
import com.andnet.gazeta.Helper.NoAnimationItemAnimator;
import com.andnet.gazeta.Models.Keys;
import com.andnet.gazeta.Models.News;
import com.andnet.gazeta.Models.Source;
import com.andnet.gazeta.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class HomeListFragment extends Fragment {

    public static final String PREF_CONTENT_LANG="pref_key_content_language";
    public static final String PAGE_ARGS = "PAGE_ARGS";
    private String pageTitle;
    private String content_lang="am";
    private View mainView;
    private RecyclerView recyclerView;
    private NewsListRecycleViewAdapter newsListRecycleViewAdapter;
    private RelativeLayout progressLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase firebaseDatabase;
    public static final int MAX_CATCH_KEY=300;
    private int startPos=0;
    private int pageSize=2;
    private int trackPos=0;
    private  ArrayList<String> keyList=new ArrayList<>();

    private RelativeLayout infoLayout;
    private ImageView infoImageImageView;
    private TextView infoTextView;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    public static HomeListFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(PAGE_ARGS, title);
        HomeListFragment listFragment = new HomeListFragment();
        listFragment.setArguments(bundle);
        return listFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
           pageTitle=getArguments().getString(PAGE_ARGS);

    }

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.home_list_fragment, container, false);
        progressLayout=mainView.findViewById(R.id.progressLayout);
        recyclerView = mainView.findViewById(R.id.rv);

        infoLayout=mainView.findViewById(R.id.infoView);
        infoImageImageView=mainView.findViewById(R.id.info_image_view);
        infoTextView=mainView.findViewById(R.id.infoTextView);

        progressLayout=mainView.findViewById(R.id.progressLayout);
        swipeRefreshLayout=mainView.findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(this::refreshRec);
        newsListRecycleViewAdapter = new NewsListRecycleViewAdapter(getContext(),pageTitle);
        recyclerView.setAdapter(newsListRecycleViewAdapter);
        recyclerView.setItemAnimator(new NoAnimationItemAnimator());
        init();
        setOrientation();
        reference();
        return mainView;
    }

    private void init(){
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        content_lang = sharedPreferences.getString(PREF_CONTENT_LANG, content_lang);
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener);
        firebaseDatabase=FirebaseDatabase.getInstance();
    }

    private void setOrientation(){
            int orientation=getResources().getConfiguration().orientation;
            int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
            if(orientation==Configuration.ORIENTATION_LANDSCAPE) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        getNews();
                    }
                });
            } else if(orientation== Configuration.ORIENTATION_PORTRAIT) {
                if(screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE){
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                    recyclerView.setLayoutManager(staggeredGridLayoutManager);
                    recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                            getNews();

                        }
                    });

                }else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                            getNews();


                        }});}}}

    private void reference(){
        DatabaseReference databaseReference=firebaseDatabase.getReference(Constants.ETHIOPIA).child(content_lang).child(pageTitle);
        Query news_query=databaseReference.orderByValue().limitToLast(10);
        createValueEventListener(news_query);
    }




    private void createValueEventListener(Query query){
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
                    setInfo(R.drawable.in_news,getString(R.string.no_news_in_this_catagory),true);
                    return;
                }
                keyList.clear();
                Iterable<DataSnapshot> dataSnapshots=dataSnapshot.getChildren();
                for (DataSnapshot keySnapShot: dataSnapshots){
                    keyList.add(keySnapShot.getKey());
                }
                Collections.reverse(keyList);
                getNews();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
            }
        });
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



    private void getNews() {
        if(trackPos>=keyList.size()){
            newsListRecycleViewAdapter.removeProgress();
            return;
        }
        for(int i=startPos;i<pageSize;i++){
            if(keyList.size()>i)
                getNewsByKey(keyList.get(i));
            trackPos++;
        }
        startPos=pageSize;
        pageSize+=2;
    }


    private boolean isSourceAllowed(String source){
        List<Source> sourceList=GazetaDatabase.getDatabase(getContext()).dao().getSource(source);
        if(sourceList!=null && !source.isEmpty()){
           return  sourceList.get(0).isVisibility();
        }
        return false;
    }


    private void getSource(String key){
        DatabaseReference databaseReference=firebaseDatabase.getReference(Constants.ETHIOPIA).child("newsL").child(key).child("source");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    FileLog.write(dataSnapshot.getValue().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void getNewsByKey(final String key) {
        DatabaseReference databaseReference=firebaseDatabase.getReference(Constants.ETHIOPIA).child("newsL").child(key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot firstSnapShot){
                final News newsModel =firstSnapShot.getValue(News.class);
                if(newsModel ==null)return;
                if(newsModel.getTitle()==null)return;
                if(newsModel.getSource()==null)return;
                   newsModel.setKeys(firstSnapShot.getKey());
                DatabaseReference secReference=firebaseDatabase.getReference(Constants.SOURCE).child(newsModel.getSource());
                secReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot secSnapShot) {
                        Source source=secSnapShot.getValue(Source.class);
                        if(source==null)return;
                        newsModel.setSourceImage(source.getImage());
                        newsModel.setSourceLink(source.getLink());
                        newsModel.setSourceLogo(source.getLogo());
                        newsModel.setSourceName(source.getName());
                        newsModel.setAllowedSource(source.isAllowed());



                        newsListRecycleViewAdapter.removeProgress();
                        newsListRecycleViewAdapter.add(newsModel);
                        newsListRecycleViewAdapter.addProgress();
                        crossFade();
                        if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
                        Log.i("KEY", newsModel.getKeys() + ": " + newsModel.getTitle() + pageTitle);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
                           Toast.makeText(getContext(),"Error: Try again!",Toast.LENGTH_SHORT).show();
                    }});}
            public void onCancelled(DatabaseError databaseError) {
                if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(),"Error: Try again!",Toast.LENGTH_SHORT).show();
            }});
    }

    private void writeKeyToDatabase(String key){
        Keys keys=new Keys();
        keys.setKeys(key);
        keys.setCategory("Headline");
        if(GazetaDatabase.getDatabase(getContext()).dao().isKeyExist(key).isEmpty()){
            GazetaDatabase.getDatabase(getContext()).dao().insertKey(keys);
        }
        if(GazetaDatabase.getDatabase(getContext()).dao().getAllKeys().size()>=10){
            List<Keys> keysList=GazetaDatabase.getDatabase(getContext()).dao().getAllKeys();
            keysList.remove(0);
            GazetaDatabase.getDatabase(getContext()).dao().clearKeys(keysList);
        }
    }

    private void crossFade(){

        if(recyclerView.getVisibility()==View.VISIBLE)return;
        progressLayout.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressLayout.setVisibility(View.GONE);
                    }
                });
                recyclerView.setAlpha(0f);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .setListener(null);

      }

    private SharedPreferences.OnSharedPreferenceChangeListener sharedPreferencesListener = (sharedPreferences, s) -> {
        if("pref_key_enable_image_loading".equals(s) || "pref_key_enable_video_loading".equals(s)) {
            refreshRec();
        }
    };


    public void  refreshRec(){
        startPos=0;
        newsListRecycleViewAdapter.getObjectList().clear();
        newsListRecycleViewAdapter = null;
        newsListRecycleViewAdapter = new NewsListRecycleViewAdapter(context,pageTitle);
        recyclerView.setAdapter(newsListRecycleViewAdapter);
        reference();
    }

}



