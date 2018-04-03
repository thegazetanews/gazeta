package com.andnet.gazeta.Background;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.andnet.gazeta.Databases.GazetaDatabase;
import com.andnet.gazeta.Helper.Constants;
import com.andnet.gazeta.Activityies.MainActivity;
import com.andnet.gazeta.Models.Keys;
import com.andnet.gazeta.Models.News;
import com.andnet.gazeta.Models.Source;
import com.andnet.gazeta.R;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

;

/**
 * service for feaching data in the background
 */

public class NewsReaderService extends Service {

    //constant for notification
    private static final String NOTIFICATION_ENABLED_KEY = "pref_key_enable_notification";
    private static final String RINGTONE_PREF_KEY = "pref_key_ringtone";
    private static final String CONTENT_LANG_PREF = "pref_key_content_language";

    private int NOT_ID = 0;


    private static final int MAX_CATCH_KEY = 1;

    private Timer timer;
    private SharedPreferences sharedPreferences;
    private News notNews;
    private Intent intent;
    private FirebaseDatabase firebaseDatabase;
    private String lang;

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseDatabase = FirebaseDatabase.getInstance();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lang = sharedPreferences.getString(CONTENT_LANG_PREF, "am");
        intent = new Intent(this, MainActivity.class);
        startTimer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        intent = new Intent(this, MainActivity.class);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        stopTimer();
    }

    private void startTimer() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (sharedPreferences.getBoolean(NOTIFICATION_ENABLED_KEY, true)) {
                    String lang = sharedPreferences.getString(CONTENT_LANG_PREF, "am");
                    reference(lang);
                }
            }
        };

        timer = new Timer(true);
        int delay = 1000 * 10;  //ten sec
        int interval = 1000 * 3600;// 1 hour interval
        timer.schedule(timerTask, delay, interval);
    }

    private void reference(String lang) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(Constants.ETHIOPIA).child(lang).child("Headline");
        Query news_query = databaseReference.orderByValue().limitToLast(1);
        createValueEventListener(news_query);
    }

    private void getNewsByKey(final String key) {
        if(isKexExist(key)){
            return;
        }

        DatabaseReference databaseReference = firebaseDatabase.getReference(Constants.ETHIOPIA).child("newsL").child(key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot firstSnapShot) {
                final News newsModel = firstSnapShot.getValue(News.class);
                if (newsModel == null) return;
                if (newsModel.getTitle() == null) return;
                if (newsModel.getSource() == null) return;
                newsModel.setKeys(firstSnapShot.getKey());
                DatabaseReference secReference = firebaseDatabase.getReference(Constants.SOURCE).child(newsModel.getSource());
                secReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot secSnapShot) {
                        Source source = secSnapShot.getValue(Source.class);
                        if (source == null) return;
                        newsModel.setSourceName(source.getName());
                        newsModel.setSourceLogo(source.getLogo());
                        newsModel.setSourceLink(source.getLink());
                        newsModel.setAllowedSource(source.isAllowed());
                        notNews = newsModel;
                       getImageBitmap();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void createValueEventListener(Query query) {
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return;
                }
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                for (DataSnapshot keySnapShot : dataSnapshots) {
                    String key = keySnapShot.getKey();
                    if (!isKexExist(key)) {
                        getNewsByKey(key);
                        writeKeyToDatabase(key);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private boolean isKexExist(String key) {
        return !GazetaDatabase.getDatabase(getApplicationContext()).dao().isKeyExist(key).isEmpty();
    }


    private void writeKeyToDatabase(String key) {
        Keys mykey = new Keys();
        mykey.setKeys(key);
        mykey.setCategory("Headline");
        GazetaDatabase.getDatabase(getApplicationContext()).dao().insertKey(mykey);
    }

    private void getImageBitmap(){
        if(notNews.getThumbnail()!=null) {
             imageReqest(notNews.getThumbnail());
        }else if(notNews.getCover_image()!=null) {
            imageReqest(notNews.getCover_image());
        }else if(notNews.getSourceImage()!=null){
            imageReqest(notNews.getSourceImage());
        }else {
            displayNotification(null,"what");
        }
    }

    private void imageReqest(String link){

        ImageRequest imageRequest = ImageRequest.fromUri(link);
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, null);
        dataSource.subscribe(
                new BaseBitmapDataSubscriber() {

                    @Override
                    protected void onNewResultImpl(Bitmap bitmap) {
                        displayNotification(bitmap,"i");
                    }

                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        // In general, failing to fetch the image should not keep us from displaying the
                        // notification. We proceed without the bitmap.
                        displayNotification(null,"j");
                    }
                },
                UiThreadImmediateExecutorService.getInstance());
    }



    private void displayNotification(Bitmap bitmap,String from) {
        Uri ringtone_uri = Uri.parse(sharedPreferences.getString(RINGTONE_PREF_KEY, Settings.System.DEFAULT_NOTIFICATION_URI.toString()));
        PendingIntent pIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setTicker(notNews.getTitle())
                .setAutoCancel(true)
                .setSound(ringtone_uri)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent);
        RemoteViews notificationView = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification_layout);

        if("i".equals(from)){
            notificationView.setImageViewBitmap(R.id.logo_image_view, bitmap);
        }else {
            notificationView.setImageViewResource(R.id.logo_image_view, R.mipmap.ic_launcher);

        }
        notificationView.setTextViewText(R.id.source_name, notNews.getTitle());
        notificationView.setTextViewText(R.id.title, notNews.getSourceName());
        builder = builder.setContent(notificationView);
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (mNotifyMgr != null) {
            if(NOT_ID<=3){
                mNotifyMgr.notify(NOT_ID,builder.build());
                NOT_ID++;
            }

        }
    }
}






