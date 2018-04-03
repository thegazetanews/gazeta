package com.andnet.gazeta.Databases;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
import javax.annotation.Generated;

@Generated("android.arch.persistence.room.RoomProcessor")
public class GazetaDatabase_Impl extends GazetaDatabase {
  private volatile GazetaDao _gazetaDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `body` (`rowid` INTEGER PRIMARY KEY AUTOINCREMENT, `keys` TEXT, `body` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `keys` (`rowid` INTEGER PRIMARY KEY AUTOINCREMENT, `keys` TEXT, `category` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `news` (`rowid` INTEGER PRIMARY KEY AUTOINCREMENT, `keys` TEXT, `timestamp` INTEGER NOT NULL, `thumbnail` TEXT, `synop` TEXT, `title` TEXT, `link` TEXT, `source` TEXT, `sourceName` TEXT, `sourceLink` TEXT, `sourceLogo` TEXT, `sourceImage` TEXT, `allowedSource` INTEGER NOT NULL, `author` TEXT, `date` TEXT, `cover_image` TEXT, `cover_audio` TEXT, `cover_video` TEXT, `o_cover_a_prev` TEXT, `original_image` TEXT, `cover_y_embed` TEXT, `o_cover_v_prev` TEXT, `cover_caption` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `source` (`rowid` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `link` TEXT, `logo` TEXT, `image` TEXT, `allowed` INTEGER NOT NULL, `enabled` INTEGER NOT NULL, `visibility` INTEGER NOT NULL, `pri` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `category` (`rowid` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `res` INTEGER NOT NULL, `enabled` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `visibility` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"4ae663cb3d7526d0ca20127ec22ce107\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `body`");
        _db.execSQL("DROP TABLE IF EXISTS `keys`");
        _db.execSQL("DROP TABLE IF EXISTS `news`");
        _db.execSQL("DROP TABLE IF EXISTS `source`");
        _db.execSQL("DROP TABLE IF EXISTS `category`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsBody = new HashMap<String, TableInfo.Column>(3);
        _columnsBody.put("rowid", new TableInfo.Column("rowid", "INTEGER", false, 1));
        _columnsBody.put("keys", new TableInfo.Column("keys", "TEXT", false, 0));
        _columnsBody.put("body", new TableInfo.Column("body", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBody = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBody = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBody = new TableInfo("body", _columnsBody, _foreignKeysBody, _indicesBody);
        final TableInfo _existingBody = TableInfo.read(_db, "body");
        if (! _infoBody.equals(_existingBody)) {
          throw new IllegalStateException("Migration didn't properly handle body(com.andnet.gazeta.Models.Body).\n"
                  + " Expected:\n" + _infoBody + "\n"
                  + " Found:\n" + _existingBody);
        }
        final HashMap<String, TableInfo.Column> _columnsKeys = new HashMap<String, TableInfo.Column>(3);
        _columnsKeys.put("rowid", new TableInfo.Column("rowid", "INTEGER", false, 1));
        _columnsKeys.put("keys", new TableInfo.Column("keys", "TEXT", false, 0));
        _columnsKeys.put("category", new TableInfo.Column("category", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysKeys = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesKeys = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoKeys = new TableInfo("keys", _columnsKeys, _foreignKeysKeys, _indicesKeys);
        final TableInfo _existingKeys = TableInfo.read(_db, "keys");
        if (! _infoKeys.equals(_existingKeys)) {
          throw new IllegalStateException("Migration didn't properly handle keys(com.andnet.gazeta.Models.Keys).\n"
                  + " Expected:\n" + _infoKeys + "\n"
                  + " Found:\n" + _existingKeys);
        }
        final HashMap<String, TableInfo.Column> _columnsNews = new HashMap<String, TableInfo.Column>(23);
        _columnsNews.put("rowid", new TableInfo.Column("rowid", "INTEGER", false, 1));
        _columnsNews.put("keys", new TableInfo.Column("keys", "TEXT", false, 0));
        _columnsNews.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0));
        _columnsNews.put("thumbnail", new TableInfo.Column("thumbnail", "TEXT", false, 0));
        _columnsNews.put("synop", new TableInfo.Column("synop", "TEXT", false, 0));
        _columnsNews.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsNews.put("link", new TableInfo.Column("link", "TEXT", false, 0));
        _columnsNews.put("source", new TableInfo.Column("source", "TEXT", false, 0));
        _columnsNews.put("sourceName", new TableInfo.Column("sourceName", "TEXT", false, 0));
        _columnsNews.put("sourceLink", new TableInfo.Column("sourceLink", "TEXT", false, 0));
        _columnsNews.put("sourceLogo", new TableInfo.Column("sourceLogo", "TEXT", false, 0));
        _columnsNews.put("sourceImage", new TableInfo.Column("sourceImage", "TEXT", false, 0));
        _columnsNews.put("allowedSource", new TableInfo.Column("allowedSource", "INTEGER", true, 0));
        _columnsNews.put("author", new TableInfo.Column("author", "TEXT", false, 0));
        _columnsNews.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsNews.put("cover_image", new TableInfo.Column("cover_image", "TEXT", false, 0));
        _columnsNews.put("cover_audio", new TableInfo.Column("cover_audio", "TEXT", false, 0));
        _columnsNews.put("cover_video", new TableInfo.Column("cover_video", "TEXT", false, 0));
        _columnsNews.put("o_cover_a_prev", new TableInfo.Column("o_cover_a_prev", "TEXT", false, 0));
        _columnsNews.put("original_image", new TableInfo.Column("original_image", "TEXT", false, 0));
        _columnsNews.put("cover_y_embed", new TableInfo.Column("cover_y_embed", "TEXT", false, 0));
        _columnsNews.put("o_cover_v_prev", new TableInfo.Column("o_cover_v_prev", "TEXT", false, 0));
        _columnsNews.put("cover_caption", new TableInfo.Column("cover_caption", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNews = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNews = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNews = new TableInfo("news", _columnsNews, _foreignKeysNews, _indicesNews);
        final TableInfo _existingNews = TableInfo.read(_db, "news");
        if (! _infoNews.equals(_existingNews)) {
          throw new IllegalStateException("Migration didn't properly handle news(com.andnet.gazeta.Models.News).\n"
                  + " Expected:\n" + _infoNews + "\n"
                  + " Found:\n" + _existingNews);
        }
        final HashMap<String, TableInfo.Column> _columnsSource = new HashMap<String, TableInfo.Column>(9);
        _columnsSource.put("rowid", new TableInfo.Column("rowid", "INTEGER", false, 1));
        _columnsSource.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsSource.put("link", new TableInfo.Column("link", "TEXT", false, 0));
        _columnsSource.put("logo", new TableInfo.Column("logo", "TEXT", false, 0));
        _columnsSource.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        _columnsSource.put("allowed", new TableInfo.Column("allowed", "INTEGER", true, 0));
        _columnsSource.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0));
        _columnsSource.put("visibility", new TableInfo.Column("visibility", "INTEGER", true, 0));
        _columnsSource.put("pri", new TableInfo.Column("pri", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSource = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSource = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSource = new TableInfo("source", _columnsSource, _foreignKeysSource, _indicesSource);
        final TableInfo _existingSource = TableInfo.read(_db, "source");
        if (! _infoSource.equals(_existingSource)) {
          throw new IllegalStateException("Migration didn't properly handle source(com.andnet.gazeta.Models.Source).\n"
                  + " Expected:\n" + _infoSource + "\n"
                  + " Found:\n" + _existingSource);
        }
        final HashMap<String, TableInfo.Column> _columnsCategory = new HashMap<String, TableInfo.Column>(6);
        _columnsCategory.put("rowid", new TableInfo.Column("rowid", "INTEGER", false, 1));
        _columnsCategory.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCategory.put("res", new TableInfo.Column("res", "INTEGER", true, 0));
        _columnsCategory.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0));
        _columnsCategory.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0));
        _columnsCategory.put("visibility", new TableInfo.Column("visibility", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategory = new TableInfo("category", _columnsCategory, _foreignKeysCategory, _indicesCategory);
        final TableInfo _existingCategory = TableInfo.read(_db, "category");
        if (! _infoCategory.equals(_existingCategory)) {
          throw new IllegalStateException("Migration didn't properly handle category(com.andnet.gazeta.Models.Category).\n"
                  + " Expected:\n" + _infoCategory + "\n"
                  + " Found:\n" + _existingCategory);
        }
      }
    }, "4ae663cb3d7526d0ca20127ec22ce107");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "body","keys","news","source","category");
  }

  @Override
  public GazetaDao dao() {
    if (_gazetaDao != null) {
      return _gazetaDao;
    } else {
      synchronized(this) {
        if(_gazetaDao == null) {
          _gazetaDao = new GazetaDao_Impl(this);
        }
        return _gazetaDao;
      }
    }
  }
}
