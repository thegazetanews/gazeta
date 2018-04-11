package com.andnet.gazeta.Activityies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private boolean firsTime=true;
    private SharedPreferences firtTimePref;
    public static final String FIRST_TIME_PREF_FILE_NAME="fitst_time_file_name";
    public static final String FIRST_TIME_PREF_KEY="pref_first_time_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firtTimePref=getSharedPreferences(FIRST_TIME_PREF_FILE_NAME,MODE_PRIVATE);
        firsTime=firtTimePref.getBoolean(FIRST_TIME_PREF_KEY,firsTime);
        if(firsTime){
            startActivity(new Intent(this,SourceCatChooser.class));
        }else{
            start();
        }

    }
    private void start(){
        Intent intent=new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
