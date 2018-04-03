package com.andnet.gazeta.Helper;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil{

    public static String getDate(long timeStamp){
        Date date=new Date(timeStamp);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        return df2.format(date);
    }

}