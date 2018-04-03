package com.andnet.gazeta;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.andnet.gazeta.Helper.LocaleHelper;
import com.andnet.gazeta.ui.Theme;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.database.FirebaseDatabase;


public class MainApplication extends Application {

    public static volatile Context applicationContext;
    public static volatile Handler applicationHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        Theme.updateAppTheme();
        applicationHandler = new Handler(applicationContext.getMainLooper());
        Fresco.initialize(applicationContext);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        SharedPreferences firsttime=applicationContext.getSharedPreferences("first_time_load",MODE_PRIVATE);
        if(firsttime.getBoolean("first_time",true)){
            Theme.writeThemeInfoToFile();
            firsttime.edit().putBoolean("first_time",false).commit();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
