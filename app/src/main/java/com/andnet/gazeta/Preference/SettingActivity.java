package com.andnet.gazeta.Preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.andnet.gazeta.Helper.LocaleHelper;
import com.andnet.gazeta.R;


public class SettingActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{


    SharedPreferences appPreference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPreference= PreferenceManager.getDefaultSharedPreferences(this);

        ActionBar actionBar=getSupportActionBar();

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.setting);
        }

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();
    }

    protected void onResume() {
        super.onResume();
        appPreference.registerOnSharedPreferenceChangeListener(this);
    }


    protected void onStop() {
        super.onStop();
        appPreference.unregisterOnSharedPreferenceChangeListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if("pref_key_app_language".equals(s)){
            LocaleHelper.setLocale(this,sharedPreferences.getString("pref_key_app_language","en"));
            recreate();
        }else if("pref_key_about".equals(s)){
//            Intent intent=new Intent(SettingActivity.this,AboutActivity.class);
//            startActivity(intent);
        }

    }
}
