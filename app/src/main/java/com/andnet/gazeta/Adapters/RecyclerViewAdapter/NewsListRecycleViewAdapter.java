package com.andnet.gazeta.Adapters.RecyclerViewAdapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andnet.gazeta.Activityies.NewsDetailActivity;
import com.andnet.gazeta.Databases.GazetaDao;
import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Fragments.ReadLaterFragment;
import com.andnet.gazeta.Helper.ChromeBrowser;
import com.andnet.gazeta.Helper.Connection;
import com.andnet.gazeta.Helper.Constants;
import com.andnet.gazeta.Helper.TimeUtil;
import com.andnet.gazeta.Models.Body;
import com.andnet.gazeta.Models.News;
import com.andnet.gazeta.R;
import com.facebook.cache.common.CacheKey;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class NewsListRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //constants
    public static final String DETIAL_ARGS="DETAIL_ARGS";
    public static final String YOUTUBE_API_KEY = "AIzaSyBpKQhNoay5m0WUg5lc4jf0s03SDf8meYk";
    public static final String YOUTUBE_IMAGE_URL = "http://img.youtube.com/vi/";
    public static final String YOUTUBE_BASE_LINK = "https://www.youtube.com/embed/";
    public static final String DETAIL_VIEW_SETTING_KEY = "pref_key_open_news_in_web_browser";
    public static final String IMAGE_QUALITY_SETTING="pref_key_image_quality";
    public static final String KEY_LIST = "EXTRA_LIST";
    public static final String PAGE_TITLE = "PAGE_TITLE";

    //view types
    private static final int TEXT_TYPE = 1;
    private static final int IMAGE_TYPE = 2;
    private static final int AV_TYPE = 3;
    private static final int YOUTUBE_TYPE = 4;
    private static final int PROGRESS_TYPE = 5;

    //preferences
//    private SharedPreferences textSizePreference;
    private SharedPreferences defaultSharedPref;

    //preference values
    private boolean imageLoading = true;
    private String image_quality;

    //fonts
    private Typeface nayala;
    private Typeface rLight;
    private Typeface rRegular;

    //data list
    private List<Object> objectList = new ArrayList<>();

    //text view list for sizing
    private List<TextView> textViewForSize = new ArrayList<>();
    private Context context;
    private String pageTitle;
    private FirebaseDatabase firebaseDatabase;
    private ReadLaterFragment readLaterFragment;

    public void setReadLaterFragment(ReadLaterFragment readLaterFragment) {
        this.readLaterFragment = readLaterFragment;
    }

    public NewsListRecycleViewAdapter(Context context, String pageTitle) {
        this.context = context;
        this.pageTitle = pageTitle;
        nayala = Typeface.createFromAsset(context.getAssets(), "NYALA.TTF");
        rLight = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        rRegular = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        firebaseDatabase = FirebaseDatabase.getInstance();
        defaultSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        image_quality=defaultSharedPref.getString(IMAGE_QUALITY_SETTING,context.getString(R.string.defual_video_loading));
//        textSizePreference.registerOnSharedPreferenceChangeListener(textSizePreferenceListener);
    }

    public void setObjectList(List<News> list) {
        objectList.addAll(list);
    }

    private void goToDetailActivity(int pos){
        News news=getNewsModel(pos);
        if("Read Later".equals(pageTitle)){
            if(news==null)return;
            if (news.isAllowedSource()){
                if (defaultSharedPref.getBoolean(DETAIL_VIEW_SETTING_KEY, false)) {
                    startChromeBrowser(pos);
                } else {
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    intent.putExtra(DETIAL_ARGS, pos);
                    intent.putExtra(KEY_LIST, getKeyList());
                    intent.putExtra(PAGE_TITLE, pageTitle);
                    context.startActivity(intent);
                }
            }else {
                startChromeBrowser(pos);
            }
        }else {
            if(news==null)return;
            if (news.isAllowedSource()){
                if (defaultSharedPref.getBoolean(DETAIL_VIEW_SETTING_KEY, false)) {
                    startChromeBrowser(pos);
                } else {
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    intent.putExtra(DETIAL_ARGS, pos);
                    intent.putExtra(KEY_LIST, getKeyList());
                    intent.putExtra(PAGE_TITLE, pageTitle);
                    context.startActivity(intent);
                }
            }else {
                startChromeBrowser(pos);
            }
        }
 }

    private void shareNews(int pos) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, getNewsModel(pos).getTitle() + "-> " + getNewsModel(pos).getLink());
        context.startActivity(shareIntent);

    }

    private void saveNews(int pos,ImageView saveImageView){
        GazetaDao dao=GazetaDatabase.getDatabase(context).dao();
        if("Read Later".equals(pageTitle)){
            News news=getNewsModel(pos);
            if(news==null)return;
            saveImageView.setImageResource(R.drawable.ic_bookmark_outline_white);
            dao.deleteNews(news);
            List<Body> bodyList=dao.getBodyId(news.getKeys());
            if(!bodyList.isEmpty()){
                dao.deleteBody(bodyList.get(0));
            }
            objectList.remove(pos);
            notifyItemRemoved(pos);
            if(dao.getAllSavedNews().isEmpty()){
                readLaterFragment.changeVisibility();
            }
        }else {
            News news=getNewsModel(pos);
            if(news==null)return;
            if(dao.isNewsSaved(news.getKeys()).isEmpty()){
                dao.insertNews(news);
                saveImageView.setImageResource(R.drawable.ic_bookmark);
                Toast.makeText(context, R.string.added_to_read_later, Toast.LENGTH_SHORT).show();

                firebaseDatabase.getReference(Constants.ETHIOPIA).child(Constants.NEWS_B).child(news.getKeys()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        JSONArray jsonArray = new JSONArray();
                        for (DataSnapshot dataSnapshot1 : children) {
                            jsonArray.put(dataSnapshot1.getValue());
                        }
                        Body body=new Body();
                        body.setKeys(news.getKeys());
                        body.setBody(jsonArray.toString());
                        dao.insertBody(body);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }});
            }else {
                News news_1=getNewsModel(pos);
                if(news_1==null)return;
                dao.deleteNews(news_1);
                List<Body> bodyList=dao.getBodyId(news_1.getKeys());
                if(!bodyList.isEmpty()){
                    dao.deleteBody(bodyList.get(0));
                }
                saveImageView.setImageResource(R.drawable.ic_bookmark_outline_white);
                Toast.makeText(context, R.string.removed_from_read_later, Toast.LENGTH_SHORT).show();
               }}}

    public int getItemViewType(int position) {
        Object object = getObjectList().get(position);
        if (object instanceof News) {
            News news = (News) object;
            if(news.getCover_y_embed()!=null){
                Log.i("YOUTUBE",news.getCover_y_embed());
                if (news.getCover_y_embed().contains(YOUTUBE_BASE_LINK)) {
                    return YOUTUBE_TYPE;
                } else {
                    return AV_TYPE;
                }
            }
            if (news.getCover_video() != null)return AV_TYPE;
            if (news.getCover_audio() != null) return AV_TYPE;
            if(imageLoading) {
                if (news.getThumbnail() != null || news.getCover_image() != null){
                    return IMAGE_TYPE;
                }else {
                    return TEXT_TYPE;
                }
            }else {
                return TEXT_TYPE;
            }
        } else if (object == null) {
            return PROGRESS_TYPE;
        }
        return -1;
    }

    public int getItemCount() {
        return objectList.size();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        switch (viewType) {
            case TEXT_TYPE:
                view = inflater.inflate(R.layout.news_list_item_no_image_view, parent, false);
                return new NewsNoImageViewHolder(view);
            case IMAGE_TYPE:
                    view = inflater.inflate(R.layout.news_list_item_normal_image_view, parent, false);
                return new NewsNormalImageViewHolder(view);
            case AV_TYPE:
                view = inflater.inflate(R.layout.news_list_item_audio_video, parent, false);
                return new AVviewHolder(view);
            case YOUTUBE_TYPE:
                view = inflater.inflate(R.layout.youtube_video_player_frame, parent, false);
                return new NewsYoutubeViewHolder(view);
            case PROGRESS_TYPE:
                view = inflater.inflate(R.layout.progress_list_item, parent, false);
                return new ProgressViewHolder(view);
            default:
                return new EmptyViewHolder(new View(context));
        }

    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        News news = null;
        if (getObjectList().get(position) instanceof News) {
            news = (News) getObjectList().get(position);
        }
        if (news == null) return;

        switch (getItemViewType(position)) {
            case TEXT_TYPE:
                NewsNoImageViewHolder noImageViewHolder = (NewsNoImageViewHolder) holder;
                setTypeFace(holder, news.getTitle());
                noImageViewHolder.titleTextView.setText(news.getTitle().trim());
                if(news.getSynop() != null){
                    noImageViewHolder.synopTextView.setVisibility(View.VISIBLE);
                    noImageViewHolder.synopTextView.setText(news.getSynop().trim().replace("\u00a0", ""));
                }else {
                    noImageViewHolder.synopTextView.setVisibility(View.INVISIBLE);
                }
                noImageViewHolder.sourceTextView.setText((news.getSourceName()));
                noImageViewHolder.sourceImageView.setImageURI(news.getSourceLogo());
                noImageViewHolder.dateTextView.setText( TimeUtil.getDate(news.getTimestamp()));

                if (isSaved(news.getKeys())) {
                    noImageViewHolder.saveImageView.setImageResource(R.drawable.ic_bookmark);
                } else {
                    noImageViewHolder.saveImageView.setImageResource(R.drawable.ic_bookmark_outline_white);
                }
                break;
            case IMAGE_TYPE:
                NewsNormalImageViewHolder normalImageView = (NewsNormalImageViewHolder) holder;
                setTypeFace(normalImageView, news.getTitle());
                normalImageView.titleTextView.setText(news.getTitle().trim());
                normalImageView.sourceTextView.setText(news.getSourceName());
                normalImageView.dateTextView.setText( TimeUtil.getDate(news.getTimestamp()));
                normalImageView.sourceImageView.setImageURI(news.getSourceLogo());
                if (isSaved(news.getKeys())) {
                    normalImageView.saveImageView.setImageResource(R.drawable.ic_bookmark);
                } else {
                    normalImageView.saveImageView.setImageResource(R.drawable.ic_bookmark_outline_white);
                }
                if(image_quality.equals(context.getString(R.string.defual_video_loading))){
                    String conn=Connection.getCurrentNetwork(context);
                    if(conn==null){

                        if(news.getThumbnail()!=null){
                            normalImageView.newsImageView.setImageURI(news.getThumbnail().trim());
                        }else {
                            normalImageView.newsImageView.setImageURI(news.getCover_image().trim());
                        }
                    }else {

                        if(conn.equals(Connection.MOBILE)){
                            if(news.getThumbnail()!=null){
                                normalImageView.newsImageView.setImageURI(news.getThumbnail().trim());
                            }else {
                                normalImageView.newsImageView.setImageURI(news.getCover_image().trim());
                            }

                        }else if(conn.equals(Connection.WIFI)){
                            if(news.getCover_image()!=null){
                                normalImageView.newsImageView.setImageURI(news.getCover_image().trim());
                            }else {
                                normalImageView.newsImageView.setImageURI(news.getThumbnail().trim());
                            }
                        }

                    }
                }else if(image_quality.equals("disabled")){
                    if(news.getThumbnail()!=null){
                        normalImageView.newsImageView.setImageURI(news.getThumbnail().trim());
                    }else {
                        normalImageView.newsImageView.setImageURI(news.getCover_image().trim());
                    }
                    break;
                }else if(image_quality.equals("enabled")){
                    if(news.getCover_image()!=null){
                        normalImageView.newsImageView.setImageURI(news.getCover_image().trim());
                    }else {
                        normalImageView.newsImageView.setImageURI(news.getThumbnail().trim());
                    }
                }
                break;
            case AV_TYPE:
                AVviewHolder AVviewHolder = (AVviewHolder) holder;
                setTypeFace(AVviewHolder, news.getTitle());
                AVviewHolder.titleTextView.setText(news.getTitle().trim());
                AVviewHolder.sourceTextView.setText(news.getSourceName());
                AVviewHolder.sourceImageView.setImageURI(news.getSourceLogo());
                AVviewHolder.dateTextView.setText( TimeUtil.getDate(news.getTimestamp()));
                if (isSaved(news.getKeys())) {
                    AVviewHolder.saveImageView.setImageResource(R.drawable.ic_bookmark);
                } else {
                    AVviewHolder.saveImageView.setImageResource(R.drawable.ic_bookmark_outline_white);
                }
                if(imageLoading){
                    if (news.getO_cover_a_prev() != null)
                        AVviewHolder.youTubeThumbnailView.setImageURI(news.getO_cover_a_prev());
                    else if (news.getThumbnail() != null) {
                        AVviewHolder.youTubeThumbnailView.setImageURI(news.getThumbnail());
                    } else if (news.getCover_image() != null) {
                        AVviewHolder.youTubeThumbnailView.setImageURI(news.getCover_image());
                    } else if(news.getO_cover_v_prev()!=null){
                        AVviewHolder.youTubeThumbnailView.setImageURI(news.getCover_image());
                    }
                }
                break;
            case YOUTUBE_TYPE:
                NewsYoutubeViewHolder youtubeViewHolder = (NewsYoutubeViewHolder) holder;
                setTypeFace(youtubeViewHolder, news.getTitle());
                youtubeViewHolder.titleTextView.setText(news.getTitle());
                youtubeViewHolder.dateTextView.setText( TimeUtil.getDate(news.getTimestamp()));
                youtubeViewHolder.sourceTextView.setText(news.getSourceName());
                youtubeViewHolder.sourceImageView.setImageURI(news.getSourceLogo());
                if (isSaved(news.getKeys())) {
                    youtubeViewHolder.saveImageView.setImageResource(R.drawable.ic_bookmark);
                } else {
                    youtubeViewHolder.saveImageView.setImageResource(R.drawable.ic_bookmark_outline_white);
                }
                String key = news.getCover_y_embed().replace(YOUTUBE_BASE_LINK, "");
                if(key.contains("?")){
                    int i=key.indexOf("?");
                    key=key.substring(0,i);
                    if(key.contains("?")){
                        key=key.replace("?","");
                    }
                }
                if(imageLoading){
                    Uri imageUri;
                    if(image_quality.equals(context.getString(R.string.defual_video_loading))){
                        String conn=Connection.getCurrentNetwork(context);
                        if(conn==null){

                            //not connected to the internet so to load image check image pipe from fresco
                            //if the image pipe is empety for the defulat image then usethe hquality image


                            imageUri=Uri.parse(YOUTUBE_IMAGE_URL).buildUpon()
                                    .appendPath(key)
                                    .appendPath("default.jpg")
                                    .build();
                            youtubeViewHolder.youTubeThumbnailView.setImageURI(imageUri);


                            if(isDownloaded(imageUri)){
                                Log.i("DOWN","yea it is");
                            }else {
                                Log.i("DOWN","not it is not");

                            }

                        }else {
                            if(conn.equals(Connection.MOBILE)){
                                //the connection is on mobile network so cehck the image pipe line if it is already exist
                                //it if doesnet exist use the defailt imag
                                imageUri=Uri.parse(YOUTUBE_IMAGE_URL).
                                        buildUpon()
                                        .appendPath(key)
                                        .appendPath("default.jpg")
                                        .build();
                                youtubeViewHolder.youTubeThumbnailView.setImageURI(imageUri);
                            }else if(conn.equals(Connection.WIFI)){
                                imageUri=Uri.parse(YOUTUBE_IMAGE_URL).buildUpon()
                                        .appendPath(key)
                                        .appendPath("hqdefault.jpg")
                                        .build();
                                youtubeViewHolder.youTubeThumbnailView.setImageURI(imageUri);
                            }

                        }

                    }else if(image_quality.equals("disabled")){
                        imageUri=Uri.parse(YOUTUBE_IMAGE_URL).buildUpon()
                                .appendPath(key)
                                .appendPath("default.jpg")
                                .build();
                        youtubeViewHolder.youTubeThumbnailView.setImageURI(imageUri);
                    }else if(image_quality.equals("enabled")){
                        imageUri=Uri.parse(YOUTUBE_IMAGE_URL).buildUpon()
                                .appendPath(key)
                                .appendPath("hqdefault.jpg")
                                .build();
                        youtubeViewHolder.youTubeThumbnailView.setImageURI(imageUri);
                    }
                }
                break;
        }
    }

    private SharedPreferences.OnSharedPreferenceChangeListener textSizePreferenceListener = (sharedPreferences, s) -> setTexSize();

    private ArrayList<String> getKeyList() {
        ArrayList<String> keyList = new ArrayList<>();
        try {
            for (Object o : objectList) {
                if (o != null)
                    if (o instanceof News) {
                        keyList.add(((News) o).getKeys());
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyList;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void add(Object o) {
       objectList.add(o);
       notifyItemInserted(objectList.size()-1);
    }

    public void addProgress() {
        this.objectList.add(null);
        this.notifyItemInserted(getObjectList().size() - 1);
    }

    public void removeProgress() {
         if(objectList.isEmpty()){
             return;
         }
        if(objectList.get(objectList.size()-1)==null){
            objectList.remove(objectList.size()-1);
            notifyItemRemoved(objectList.size()-1);
        }
    }

    private News getNewsModel(int pos) {
        Object object=objectList.get(pos);
        if(object instanceof News){
            return (News)object;
        }
        return null;
    }

    private void setTexSize() {


    }

    private void setNayala(TextView textView) {
        textView.setTypeface(nayala);
    }

    private void setrRegular(TextView textView) {
        textView.setTypeface(rRegular);
    }

    private void setrLight(TextView textView){
        textView.setTypeface(rLight);
    }

    private boolean isEn(String title) {
        try {
            int  k = title.charAt(title.length()-1);
            return k < 200;
        }catch (Exception e){
            e.printStackTrace();
        }
      return true;
    }

    private boolean isSaved(String key) {
        return !GazetaDatabase.getDatabase(context).dao().isNewsSaved(key).isEmpty();

    }

    private void startChromeBrowser(int pos) {
        String link = ((News) getObjectList().get(pos)).getLink();
        ChromeBrowser.openUrl(context, link);
    }

    private void setTypeFace(RecyclerView.ViewHolder holder, String title) {
        if (holder instanceof NewsNoImageViewHolder) {
            NewsNoImageViewHolder noImageViewHolder = (NewsNoImageViewHolder) holder;
            if (isEn(title)) {
                setrRegular(noImageViewHolder.titleTextView);
                setrLight(noImageViewHolder.synopTextView);
                setrRegular(noImageViewHolder.sourceTextView);
                setrRegular(noImageViewHolder.dateTextView);
            } else {
                setNayala(noImageViewHolder.titleTextView);
                setNayala(noImageViewHolder.synopTextView);
                setrRegular(noImageViewHolder.sourceTextView);
                setrRegular(noImageViewHolder.dateTextView);
            }
        } else if (holder instanceof NewsNormalImageViewHolder) {
            NewsNormalImageViewHolder newsNormalImageViewHolder = (NewsNormalImageViewHolder) holder;
            if (isEn(title)) {
                setrRegular(newsNormalImageViewHolder.titleTextView);
                setrRegular(newsNormalImageViewHolder.sourceTextView);
                setrRegular(newsNormalImageViewHolder.dateTextView);
            } else {
                setNayala(newsNormalImageViewHolder.titleTextView);
                setrRegular(newsNormalImageViewHolder.sourceTextView);
                setrRegular(newsNormalImageViewHolder.dateTextView);
            }

        } else if (holder instanceof AVviewHolder) {
            AVviewHolder AVviewHolder = (AVviewHolder) holder;
            if (isEn(title)) {
                setrRegular(AVviewHolder.titleTextView);
                setrRegular(AVviewHolder.sourceTextView);
                setrRegular(AVviewHolder.dateTextView);
            } else {
                setNayala(AVviewHolder.titleTextView);
                setrRegular(AVviewHolder.sourceTextView);
                setrRegular(AVviewHolder.dateTextView);
            }
        } else if (holder instanceof NewsYoutubeViewHolder) {
            NewsYoutubeViewHolder newsYoutubeViewHolder = (NewsYoutubeViewHolder) holder;
            if (isEn(title)) {
                setrRegular(newsYoutubeViewHolder.titleTextView);
                setrRegular(newsYoutubeViewHolder.sourceTextView);
                setrRegular(newsYoutubeViewHolder.dateTextView);
            } else {
                setNayala(newsYoutubeViewHolder.titleTextView);
                setrRegular(newsYoutubeViewHolder.sourceTextView);
                setrRegular(newsYoutubeViewHolder.dateTextView);
            }

        }


    }

    private class NewsNoImageViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sourceImageView;
        ImageView shareImageView;
        ImageView saveImageView;
        TextView titleTextView;
        TextView synopTextView;
        TextView dateTextView;
        TextView sourceTextView;

        public NewsNoImageViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            synopTextView = itemView.findViewById(R.id.synop_textView);
            dateTextView = itemView.findViewById(R.id.date_textView);
            sourceTextView = itemView.findViewById(R.id.source_name_textView);
            sourceImageView = itemView.findViewById(R.id.source_imageView);
            shareImageView = itemView.findViewById(R.id.share_imageView);
            saveImageView = itemView.findViewById(R.id.save_imageView);
            List<TextView> textViewList = new ArrayList<>();
            textViewList.add(titleTextView);
            textViewList.add(synopTextView);
            textViewList.add(dateTextView);
            textViewList.add(sourceTextView);
            textViewForSize.add(titleTextView);
            textViewForSize.add(synopTextView);
            setTexSize();
            itemView.setOnClickListener(view -> goToDetailActivity(getAdapterPosition()));

            shareImageView.setOnClickListener(view -> shareNews(getAdapterPosition()));
            saveImageView.setOnClickListener(view -> saveNews(getAdapterPosition(), saveImageView));
        }
    }

    private class NewsNormalImageViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView newsImageView;
        SimpleDraweeView sourceImageView;
        ImageView shareImageView;
        ImageView saveImageView;
        TextView titleTextView;
        TextView synopTextView;
        TextView dateTextView;
        TextView sourceTextView;

        public NewsNormalImageViewHolder(final View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.news_imageView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            synopTextView = itemView.findViewById(R.id.synop_textView);
            dateTextView = itemView.findViewById(R.id.date_textView);
            sourceTextView = itemView.findViewById(R.id.source_name_textView);
            sourceImageView = itemView.findViewById(R.id.source_imageView);
            shareImageView = itemView.findViewById(R.id.share_imageView);
            saveImageView = itemView.findViewById(R.id.save_imageView);

            List<TextView> textViewList = new ArrayList<>();
            textViewList.add(titleTextView);
            textViewList.add(synopTextView);
            textViewList.add(dateTextView);
            textViewList.add(sourceTextView);
            textViewForSize.add(titleTextView);
            textViewForSize.add(synopTextView);
            setTexSize();
            itemView.setOnClickListener(view -> goToDetailActivity(getAdapterPosition()));
            shareImageView.setOnClickListener(view -> shareNews(getAdapterPosition()));
            saveImageView.setOnClickListener(view -> saveNews(getAdapterPosition(), saveImageView));
        }
    }

    private class NewsYoutubeViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        SimpleDraweeView youTubeThumbnailView;
        ImageView playButton;
        SimpleDraweeView sourceImageView;
        ImageView shareImageView;
        ImageView saveImageView;
        TextView titleTextView;
        TextView synopTextView;
        TextView dateTextView;
        TextView sourceTextView;


        public NewsYoutubeViewHolder(final View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            synopTextView = itemView.findViewById(R.id.synop_textView);
            dateTextView = itemView.findViewById(R.id.date_textView);
            sourceTextView = itemView.findViewById(R.id.source_name_textView);
            sourceImageView = itemView.findViewById(R.id.source_imageView);
            shareImageView = itemView.findViewById(R.id.share_imageView);
            saveImageView = itemView.findViewById(R.id.save_imageView);
            playButton = itemView.findViewById(R.id.btnYoutube_player);
            relativeLayoutOverYouTubeThumbnailView =itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = itemView.findViewById(R.id.youtube_thumbnail_view);

            textViewForSize.add(titleTextView);
            textViewForSize.add(synopTextView);
            setTexSize();

            itemView.setOnClickListener(view -> goToDetailActivity(getAdapterPosition()));
            itemView.setOnClickListener(view -> {
                News news =getNewsModel(getAdapterPosition());
                if(news ==null)return;
                if(news.getCover_y_embed()==null)return;
                String key = news.getCover_y_embed().replace(YOUTUBE_BASE_LINK, "");
                if(key.contains("?")){
                    int i=key.indexOf("?");
                    key=key.substring(0,i);
                    if(key.contains("?")){
                        key=key.replace("?","");
                    }
                }
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, YOUTUBE_API_KEY,key);
                context.startActivity(intent);
            });
            shareImageView.setOnClickListener(view -> shareNews(getAdapterPosition()));
            saveImageView.setOnClickListener(view -> saveNews(getAdapterPosition(), saveImageView));


        }
    }


    private class AVviewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        SimpleDraweeView youTubeThumbnailView;
        ImageView playButton;
        SimpleDraweeView sourceImageView;
        ImageView shareImageView;
        ImageView saveImageView;
        TextView titleTextView;
        TextView synopTextView;
        TextView dateTextView;
        TextView sourceTextView;

        public AVviewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            synopTextView = itemView.findViewById(R.id.synop_textView);
            dateTextView = itemView.findViewById(R.id.date_textView);
            sourceTextView = itemView.findViewById(R.id.source_name_textView);
            sourceImageView = itemView.findViewById(R.id.source_imageView);
            shareImageView = itemView.findViewById(R.id.share_imageView);
            saveImageView = itemView.findViewById(R.id.save_imageView);

            List<TextView> textViewList = new ArrayList<>();
            textViewList.add(titleTextView);
            textViewList.add(synopTextView);
            textViewList.add(dateTextView);
            textViewList.add(sourceTextView);

            textViewForSize.add(titleTextView);
            textViewForSize.add(synopTextView);
            setTexSize();

            playButton = itemView.findViewById(R.id.btnYoutube_player);
            relativeLayoutOverYouTubeThumbnailView = itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = itemView.findViewById(R.id.youtube_thumbnail_view);

            playButton.setOnClickListener(v -> {
                ChromeBrowser.openUrl(context,getNewsModel(getAdapterPosition()).getLink());
            });
            itemView.setOnClickListener(view -> goToDetailActivity(getAdapterPosition()));
            shareImageView.setOnClickListener(view -> shareNews(getAdapterPosition()));
            saveImageView.setOnClickListener(view -> saveNews(getAdapterPosition(), saveImageView));

        }
    }

    private class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private boolean isDownloaded(Uri loadUri) {
        if (loadUri == null) {
            return false;
        }
        ImageRequest imageRequest = ImageRequest.fromUri(loadUri);
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance()
                .getEncodedCacheKey(imageRequest,loadUri);
        DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(imageRequest,loadUri);
        return ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey);
    }

}
