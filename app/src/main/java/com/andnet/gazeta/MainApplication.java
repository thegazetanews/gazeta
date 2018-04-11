package com.andnet.gazeta;
import android.app.Application;
import android.content.Context;
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
        Theme.ThemeInfo.addThemesInfo();
        applicationHandler = new Handler(applicationContext.getMainLooper());
        Fresco.initialize(applicationContext);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
