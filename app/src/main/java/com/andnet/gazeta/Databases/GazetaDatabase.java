package com.andnet.gazeta.Databases;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.andnet.gazeta.Models.Body;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.Models.Keys;
import com.andnet.gazeta.Models.News;
import com.andnet.gazeta.Models.Source;

@Database(entities = {Body.class, Keys.class, News.class,Source.class, Category.class}, version =1)
public abstract class GazetaDatabase extends RoomDatabase{

    private static GazetaDatabase INSTANCE;

    public abstract GazetaDao dao();

    public static GazetaDatabase getDatabase(Context context) {
        if(INSTANCE==null) {
            INSTANCE= Room.databaseBuilder(context,GazetaDatabase.class,"gazeta").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


}
