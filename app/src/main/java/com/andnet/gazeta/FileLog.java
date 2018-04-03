package com.andnet.gazeta;


import android.util.Log;

public class FileLog {

    public static final String TAG="FILE_LOG";
    public static  void write(String message){
        Log.i(TAG,message);
    }

}
