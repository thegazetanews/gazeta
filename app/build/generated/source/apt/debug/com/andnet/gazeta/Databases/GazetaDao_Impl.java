package com.andnet.gazeta.Databases;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.andnet.gazeta.Models.Body;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.Models.Keys;
import com.andnet.gazeta.Models.News;
import com.andnet.gazeta.Models.Source;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("android.arch.persistence.room.RoomProcessor")
public class GazetaDao_Impl implements GazetaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSource;

  private final EntityInsertionAdapter __insertionAdapterOfNews;

  private final EntityInsertionAdapter __insertionAdapterOfBody;

  private final EntityInsertionAdapter __insertionAdapterOfKeys;

  private final EntityInsertionAdapter __insertionAdapterOfCategory;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfNews;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfBody;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfKeys;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfSource;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfCategory;

  public GazetaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSource = new EntityInsertionAdapter<Source>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `source`(`rowid`,`name`,`link`,`logo`,`image`,`allowed`,`enabled`,`visibility`,`pri`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Source value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getLink() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLink());
        }
        if (value.getLogo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLogo());
        }
        if (value.getImage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getImage());
        }
        final int _tmp;
        _tmp = value.isAllowed() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isEnabled() ? 1 : 0;
        stmt.bindLong(7, _tmp_1);
        final int _tmp_2;
        _tmp_2 = value.isVisibility() ? 1 : 0;
        stmt.bindLong(8, _tmp_2);
        stmt.bindLong(9, value.getPri());
      }
    };
    this.__insertionAdapterOfNews = new EntityInsertionAdapter<News>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `news`(`rowid`,`keys`,`timestamp`,`thumbnail`,`synop`,`title`,`link`,`source`,`sourceName`,`sourceLink`,`sourceLogo`,`sourceImage`,`allowedSource`,`author`,`date`,`cover_image`,`cover_audio`,`cover_video`,`o_cover_a_prev`,`original_image`,`cover_y_embed`,`o_cover_v_prev`,`cover_caption`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, News value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
        if (value.getKeys() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getKeys());
        }
        stmt.bindLong(3, value.getTimestamp());
        if (value.getThumbnail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getThumbnail());
        }
        if (value.getSynop() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSynop());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTitle());
        }
        if (value.getLink() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLink());
        }
        if (value.getSource() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getSource());
        }
        if (value.getSourceName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getSourceName());
        }
        if (value.getSourceLink() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getSourceLink());
        }
        if (value.getSourceLogo() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getSourceLogo());
        }
        if (value.getSourceImage() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getSourceImage());
        }
        final int _tmp;
        _tmp = value.isAllowedSource() ? 1 : 0;
        stmt.bindLong(13, _tmp);
        if (value.getAuthor() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getAuthor());
        }
        if (value.getDate() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getDate());
        }
        if (value.getCover_image() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getCover_image());
        }
        if (value.getCover_audio() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getCover_audio());
        }
        if (value.getCover_video() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getCover_video());
        }
        if (value.getO_cover_a_prev() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getO_cover_a_prev());
        }
        if (value.getOriginal_image() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getOriginal_image());
        }
        if (value.getCover_y_embed() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getCover_y_embed());
        }
        if (value.getO_cover_v_prev() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getO_cover_v_prev());
        }
        if (value.getCover_caption() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindString(23, value.getCover_caption());
        }
      }
    };
    this.__insertionAdapterOfBody = new EntityInsertionAdapter<Body>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `body`(`rowid`,`keys`,`body`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Body value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
        if (value.getKeys() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getKeys());
        }
        if (value.getBody() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBody());
        }
      }
    };
    this.__insertionAdapterOfKeys = new EntityInsertionAdapter<Keys>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `keys`(`rowid`,`keys`,`category`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Keys value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
        if (value.getKeys() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getKeys());
        }
        if (value.getCategory() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCategory());
        }
      }
    };
    this.__insertionAdapterOfCategory = new EntityInsertionAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `category`(`rowid`,`name`,`res`,`enabled`,`priority`,`visibility`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getRes());
        final int _tmp;
        _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.getPriority());
        final int _tmp_1;
        _tmp_1 = value.isVisibility() ? 1 : 0;
        stmt.bindLong(6, _tmp_1);
      }
    };
    this.__deletionAdapterOfNews = new EntityDeletionOrUpdateAdapter<News>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `news` WHERE `rowid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, News value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
      }
    };
    this.__deletionAdapterOfBody = new EntityDeletionOrUpdateAdapter<Body>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `body` WHERE `rowid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Body value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
      }
    };
    this.__deletionAdapterOfKeys = new EntityDeletionOrUpdateAdapter<Keys>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `keys` WHERE `rowid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Keys value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
      }
    };
    this.__updateAdapterOfSource = new EntityDeletionOrUpdateAdapter<Source>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `source` SET `rowid` = ?,`name` = ?,`link` = ?,`logo` = ?,`image` = ?,`allowed` = ?,`enabled` = ?,`visibility` = ?,`pri` = ? WHERE `rowid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Source value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getLink() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLink());
        }
        if (value.getLogo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLogo());
        }
        if (value.getImage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getImage());
        }
        final int _tmp;
        _tmp = value.isAllowed() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isEnabled() ? 1 : 0;
        stmt.bindLong(7, _tmp_1);
        final int _tmp_2;
        _tmp_2 = value.isVisibility() ? 1 : 0;
        stmt.bindLong(8, _tmp_2);
        stmt.bindLong(9, value.getPri());
        if (value.getRowid() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindLong(10, value.getRowid());
        }
      }
    };
    this.__updateAdapterOfCategory = new EntityDeletionOrUpdateAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `category` SET `rowid` = ?,`name` = ?,`res` = ?,`enabled` = ?,`priority` = ?,`visibility` = ? WHERE `rowid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        if (value.getRowid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getRowid());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getRes());
        final int _tmp;
        _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.getPriority());
        final int _tmp_1;
        _tmp_1 = value.isVisibility() ? 1 : 0;
        stmt.bindLong(6, _tmp_1);
        if (value.getRowid() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getRowid());
        }
      }
    };
  }

  @Override
  public void insertSource(Source... sources) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSource.insert(sources);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertNews(News... news) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNews.insert(news);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertBody(Body... bodies) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfBody.insert(bodies);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertKey(Keys... keys) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfKeys.insert(keys);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertCategory(Category category) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCategory.insert(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNews(News news) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfNews.handle(news);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteBody(Body news) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfBody.handle(news);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearNews(List<News> newsList) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfNews.handleMultiple(newsList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearBody(List<Body> bodyList) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfBody.handleMultiple(bodyList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearKeys(List<Keys> keysList) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfKeys.handleMultiple(keysList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateSource(Source source) {
    __db.beginTransaction();
    try {
      __updateAdapterOfSource.handle(source);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateCategory(Category category) {
    __db.beginTransaction();
    try {
      __updateAdapterOfCategory.handle(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<News> getAllSavedNews() {
    final String _sql = "SELECT * FROM news";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfKeys = _cursor.getColumnIndexOrThrow("keys");
      final int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow("timestamp");
      final int _cursorIndexOfThumbnail = _cursor.getColumnIndexOrThrow("thumbnail");
      final int _cursorIndexOfSynop = _cursor.getColumnIndexOrThrow("synop");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfLink = _cursor.getColumnIndexOrThrow("link");
      final int _cursorIndexOfSource = _cursor.getColumnIndexOrThrow("source");
      final int _cursorIndexOfSourceName = _cursor.getColumnIndexOrThrow("sourceName");
      final int _cursorIndexOfSourceLink = _cursor.getColumnIndexOrThrow("sourceLink");
      final int _cursorIndexOfSourceLogo = _cursor.getColumnIndexOrThrow("sourceLogo");
      final int _cursorIndexOfSourceImage = _cursor.getColumnIndexOrThrow("sourceImage");
      final int _cursorIndexOfAllowedSource = _cursor.getColumnIndexOrThrow("allowedSource");
      final int _cursorIndexOfAuthor = _cursor.getColumnIndexOrThrow("author");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfCoverImage = _cursor.getColumnIndexOrThrow("cover_image");
      final int _cursorIndexOfCoverAudio = _cursor.getColumnIndexOrThrow("cover_audio");
      final int _cursorIndexOfCoverVideo = _cursor.getColumnIndexOrThrow("cover_video");
      final int _cursorIndexOfOCoverAPrev = _cursor.getColumnIndexOrThrow("o_cover_a_prev");
      final int _cursorIndexOfOriginalImage = _cursor.getColumnIndexOrThrow("original_image");
      final int _cursorIndexOfCoverYEmbed = _cursor.getColumnIndexOrThrow("cover_y_embed");
      final int _cursorIndexOfOCoverVPrev = _cursor.getColumnIndexOrThrow("o_cover_v_prev");
      final int _cursorIndexOfCoverCaption = _cursor.getColumnIndexOrThrow("cover_caption");
      final List<News> _result = new ArrayList<News>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final News _item;
        _item = new News();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpKeys;
        _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        _item.setKeys(_tmpKeys);
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _item.setTimestamp(_tmpTimestamp);
        final String _tmpThumbnail;
        _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
        _item.setThumbnail(_tmpThumbnail);
        final String _tmpSynop;
        _tmpSynop = _cursor.getString(_cursorIndexOfSynop);
        _item.setSynop(_tmpSynop);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpLink;
        _tmpLink = _cursor.getString(_cursorIndexOfLink);
        _item.setLink(_tmpLink);
        final String _tmpSource;
        _tmpSource = _cursor.getString(_cursorIndexOfSource);
        _item.setSource(_tmpSource);
        final String _tmpSourceName;
        _tmpSourceName = _cursor.getString(_cursorIndexOfSourceName);
        _item.setSourceName(_tmpSourceName);
        final String _tmpSourceLink;
        _tmpSourceLink = _cursor.getString(_cursorIndexOfSourceLink);
        _item.setSourceLink(_tmpSourceLink);
        final String _tmpSourceLogo;
        _tmpSourceLogo = _cursor.getString(_cursorIndexOfSourceLogo);
        _item.setSourceLogo(_tmpSourceLogo);
        final String _tmpSourceImage;
        _tmpSourceImage = _cursor.getString(_cursorIndexOfSourceImage);
        _item.setSourceImage(_tmpSourceImage);
        final boolean _tmpAllowedSource;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowedSource);
        _tmpAllowedSource = _tmp != 0;
        _item.setAllowedSource(_tmpAllowedSource);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        _item.setAuthor(_tmpAuthor);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _item.setDate(_tmpDate);
        final String _tmpCover_image;
        _tmpCover_image = _cursor.getString(_cursorIndexOfCoverImage);
        _item.setCover_image(_tmpCover_image);
        final String _tmpCover_audio;
        _tmpCover_audio = _cursor.getString(_cursorIndexOfCoverAudio);
        _item.setCover_audio(_tmpCover_audio);
        final String _tmpCover_video;
        _tmpCover_video = _cursor.getString(_cursorIndexOfCoverVideo);
        _item.setCover_video(_tmpCover_video);
        final String _tmpO_cover_a_prev;
        _tmpO_cover_a_prev = _cursor.getString(_cursorIndexOfOCoverAPrev);
        _item.setO_cover_a_prev(_tmpO_cover_a_prev);
        final String _tmpOriginal_image;
        _tmpOriginal_image = _cursor.getString(_cursorIndexOfOriginalImage);
        _item.setOriginal_image(_tmpOriginal_image);
        final String _tmpCover_y_embed;
        _tmpCover_y_embed = _cursor.getString(_cursorIndexOfCoverYEmbed);
        _item.setCover_y_embed(_tmpCover_y_embed);
        final String _tmpO_cover_v_prev;
        _tmpO_cover_v_prev = _cursor.getString(_cursorIndexOfOCoverVPrev);
        _item.setO_cover_v_prev(_tmpO_cover_v_prev);
        final String _tmpCover_caption;
        _tmpCover_caption = _cursor.getString(_cursorIndexOfCoverCaption);
        _item.setCover_caption(_tmpCover_caption);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Body> getAllBody() {
    final String _sql = "SELECT * FROM body";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfKeys = _cursor.getColumnIndexOrThrow("keys");
      final int _cursorIndexOfBody = _cursor.getColumnIndexOrThrow("body");
      final List<Body> _result = new ArrayList<Body>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Body _item;
        _item = new Body();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpKeys;
        _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        _item.setKeys(_tmpKeys);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        _item.setBody(_tmpBody);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Keys> getAllKeys() {
    final String _sql = "SELECT * FROM keys";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfKeys = _cursor.getColumnIndexOrThrow("keys");
      final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
      final List<Keys> _result = new ArrayList<Keys>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Keys _item;
        _item = new Keys();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpKeys;
        _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        _item.setKeys(_tmpKeys);
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        _item.setCategory(_tmpCategory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Source> getAllSource() {
    final String _sql = "SELECT * FROM source";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLink = _cursor.getColumnIndexOrThrow("link");
      final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfAllowed = _cursor.getColumnIndexOrThrow("allowed");
      final int _cursorIndexOfEnabled = _cursor.getColumnIndexOrThrow("enabled");
      final int _cursorIndexOfVisibility = _cursor.getColumnIndexOrThrow("visibility");
      final int _cursorIndexOfPri = _cursor.getColumnIndexOrThrow("pri");
      final List<Source> _result = new ArrayList<Source>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Source _item;
        _item = new Source();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpLink;
        _tmpLink = _cursor.getString(_cursorIndexOfLink);
        _item.setLink(_tmpLink);
        final String _tmpLogo;
        _tmpLogo = _cursor.getString(_cursorIndexOfLogo);
        _item.setLogo(_tmpLogo);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item.setImage(_tmpImage);
        final boolean _tmpAllowed;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowed);
        _tmpAllowed = _tmp != 0;
        _item.setAllowed(_tmpAllowed);
        final boolean _tmpEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp_1 != 0;
        _item.setEnabled(_tmpEnabled);
        final boolean _tmpVisibility;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfVisibility);
        _tmpVisibility = _tmp_2 != 0;
        _item.setVisibility(_tmpVisibility);
        final int _tmpPri;
        _tmpPri = _cursor.getInt(_cursorIndexOfPri);
        _item.setPri(_tmpPri);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Category> getAllCatagory() {
    final String _sql = "SELECT * FROM Category";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfRes = _cursor.getColumnIndexOrThrow("res");
      final int _cursorIndexOfEnabled = _cursor.getColumnIndexOrThrow("enabled");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfVisibility = _cursor.getColumnIndexOrThrow("visibility");
      final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Category _item;
        _item = new Category();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final int _tmpRes;
        _tmpRes = _cursor.getInt(_cursorIndexOfRes);
        _item.setRes(_tmpRes);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _item.setEnabled(_tmpEnabled);
        final int _tmpPriority;
        _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
        _item.setPriority(_tmpPriority);
        final boolean _tmpVisibility;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVisibility);
        _tmpVisibility = _tmp_1 != 0;
        _item.setVisibility(_tmpVisibility);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<News> getNews(String newsId) {
    final String _sql = "SELECT * FROM news WHERE keys  = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (newsId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, newsId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfKeys = _cursor.getColumnIndexOrThrow("keys");
      final int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow("timestamp");
      final int _cursorIndexOfThumbnail = _cursor.getColumnIndexOrThrow("thumbnail");
      final int _cursorIndexOfSynop = _cursor.getColumnIndexOrThrow("synop");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfLink = _cursor.getColumnIndexOrThrow("link");
      final int _cursorIndexOfSource = _cursor.getColumnIndexOrThrow("source");
      final int _cursorIndexOfSourceName = _cursor.getColumnIndexOrThrow("sourceName");
      final int _cursorIndexOfSourceLink = _cursor.getColumnIndexOrThrow("sourceLink");
      final int _cursorIndexOfSourceLogo = _cursor.getColumnIndexOrThrow("sourceLogo");
      final int _cursorIndexOfSourceImage = _cursor.getColumnIndexOrThrow("sourceImage");
      final int _cursorIndexOfAllowedSource = _cursor.getColumnIndexOrThrow("allowedSource");
      final int _cursorIndexOfAuthor = _cursor.getColumnIndexOrThrow("author");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfCoverImage = _cursor.getColumnIndexOrThrow("cover_image");
      final int _cursorIndexOfCoverAudio = _cursor.getColumnIndexOrThrow("cover_audio");
      final int _cursorIndexOfCoverVideo = _cursor.getColumnIndexOrThrow("cover_video");
      final int _cursorIndexOfOCoverAPrev = _cursor.getColumnIndexOrThrow("o_cover_a_prev");
      final int _cursorIndexOfOriginalImage = _cursor.getColumnIndexOrThrow("original_image");
      final int _cursorIndexOfCoverYEmbed = _cursor.getColumnIndexOrThrow("cover_y_embed");
      final int _cursorIndexOfOCoverVPrev = _cursor.getColumnIndexOrThrow("o_cover_v_prev");
      final int _cursorIndexOfCoverCaption = _cursor.getColumnIndexOrThrow("cover_caption");
      final List<News> _result = new ArrayList<News>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final News _item;
        _item = new News();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpKeys;
        _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        _item.setKeys(_tmpKeys);
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _item.setTimestamp(_tmpTimestamp);
        final String _tmpThumbnail;
        _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
        _item.setThumbnail(_tmpThumbnail);
        final String _tmpSynop;
        _tmpSynop = _cursor.getString(_cursorIndexOfSynop);
        _item.setSynop(_tmpSynop);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpLink;
        _tmpLink = _cursor.getString(_cursorIndexOfLink);
        _item.setLink(_tmpLink);
        final String _tmpSource;
        _tmpSource = _cursor.getString(_cursorIndexOfSource);
        _item.setSource(_tmpSource);
        final String _tmpSourceName;
        _tmpSourceName = _cursor.getString(_cursorIndexOfSourceName);
        _item.setSourceName(_tmpSourceName);
        final String _tmpSourceLink;
        _tmpSourceLink = _cursor.getString(_cursorIndexOfSourceLink);
        _item.setSourceLink(_tmpSourceLink);
        final String _tmpSourceLogo;
        _tmpSourceLogo = _cursor.getString(_cursorIndexOfSourceLogo);
        _item.setSourceLogo(_tmpSourceLogo);
        final String _tmpSourceImage;
        _tmpSourceImage = _cursor.getString(_cursorIndexOfSourceImage);
        _item.setSourceImage(_tmpSourceImage);
        final boolean _tmpAllowedSource;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowedSource);
        _tmpAllowedSource = _tmp != 0;
        _item.setAllowedSource(_tmpAllowedSource);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        _item.setAuthor(_tmpAuthor);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _item.setDate(_tmpDate);
        final String _tmpCover_image;
        _tmpCover_image = _cursor.getString(_cursorIndexOfCoverImage);
        _item.setCover_image(_tmpCover_image);
        final String _tmpCover_audio;
        _tmpCover_audio = _cursor.getString(_cursorIndexOfCoverAudio);
        _item.setCover_audio(_tmpCover_audio);
        final String _tmpCover_video;
        _tmpCover_video = _cursor.getString(_cursorIndexOfCoverVideo);
        _item.setCover_video(_tmpCover_video);
        final String _tmpO_cover_a_prev;
        _tmpO_cover_a_prev = _cursor.getString(_cursorIndexOfOCoverAPrev);
        _item.setO_cover_a_prev(_tmpO_cover_a_prev);
        final String _tmpOriginal_image;
        _tmpOriginal_image = _cursor.getString(_cursorIndexOfOriginalImage);
        _item.setOriginal_image(_tmpOriginal_image);
        final String _tmpCover_y_embed;
        _tmpCover_y_embed = _cursor.getString(_cursorIndexOfCoverYEmbed);
        _item.setCover_y_embed(_tmpCover_y_embed);
        final String _tmpO_cover_v_prev;
        _tmpO_cover_v_prev = _cursor.getString(_cursorIndexOfOCoverVPrev);
        _item.setO_cover_v_prev(_tmpO_cover_v_prev);
        final String _tmpCover_caption;
        _tmpCover_caption = _cursor.getString(_cursorIndexOfCoverCaption);
        _item.setCover_caption(_tmpCover_caption);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Body> getBodyId(String bodyId) {
    final String _sql = "SELECT * FROM body WHERE keys = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bodyId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bodyId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfKeys = _cursor.getColumnIndexOrThrow("keys");
      final int _cursorIndexOfBody = _cursor.getColumnIndexOrThrow("body");
      final List<Body> _result = new ArrayList<Body>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Body _item;
        _item = new Body();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpKeys;
        _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        _item.setKeys(_tmpKeys);
        final String _tmpBody;
        _tmpBody = _cursor.getString(_cursorIndexOfBody);
        _item.setBody(_tmpBody);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<News> isNewsSaved(String newsId) {
    final String _sql = "SELECT * FROM news WHERE keys = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (newsId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, newsId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfKeys = _cursor.getColumnIndexOrThrow("keys");
      final int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow("timestamp");
      final int _cursorIndexOfThumbnail = _cursor.getColumnIndexOrThrow("thumbnail");
      final int _cursorIndexOfSynop = _cursor.getColumnIndexOrThrow("synop");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfLink = _cursor.getColumnIndexOrThrow("link");
      final int _cursorIndexOfSource = _cursor.getColumnIndexOrThrow("source");
      final int _cursorIndexOfSourceName = _cursor.getColumnIndexOrThrow("sourceName");
      final int _cursorIndexOfSourceLink = _cursor.getColumnIndexOrThrow("sourceLink");
      final int _cursorIndexOfSourceLogo = _cursor.getColumnIndexOrThrow("sourceLogo");
      final int _cursorIndexOfSourceImage = _cursor.getColumnIndexOrThrow("sourceImage");
      final int _cursorIndexOfAllowedSource = _cursor.getColumnIndexOrThrow("allowedSource");
      final int _cursorIndexOfAuthor = _cursor.getColumnIndexOrThrow("author");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfCoverImage = _cursor.getColumnIndexOrThrow("cover_image");
      final int _cursorIndexOfCoverAudio = _cursor.getColumnIndexOrThrow("cover_audio");
      final int _cursorIndexOfCoverVideo = _cursor.getColumnIndexOrThrow("cover_video");
      final int _cursorIndexOfOCoverAPrev = _cursor.getColumnIndexOrThrow("o_cover_a_prev");
      final int _cursorIndexOfOriginalImage = _cursor.getColumnIndexOrThrow("original_image");
      final int _cursorIndexOfCoverYEmbed = _cursor.getColumnIndexOrThrow("cover_y_embed");
      final int _cursorIndexOfOCoverVPrev = _cursor.getColumnIndexOrThrow("o_cover_v_prev");
      final int _cursorIndexOfCoverCaption = _cursor.getColumnIndexOrThrow("cover_caption");
      final List<News> _result = new ArrayList<News>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final News _item;
        _item = new News();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpKeys;
        _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        _item.setKeys(_tmpKeys);
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _item.setTimestamp(_tmpTimestamp);
        final String _tmpThumbnail;
        _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
        _item.setThumbnail(_tmpThumbnail);
        final String _tmpSynop;
        _tmpSynop = _cursor.getString(_cursorIndexOfSynop);
        _item.setSynop(_tmpSynop);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpLink;
        _tmpLink = _cursor.getString(_cursorIndexOfLink);
        _item.setLink(_tmpLink);
        final String _tmpSource;
        _tmpSource = _cursor.getString(_cursorIndexOfSource);
        _item.setSource(_tmpSource);
        final String _tmpSourceName;
        _tmpSourceName = _cursor.getString(_cursorIndexOfSourceName);
        _item.setSourceName(_tmpSourceName);
        final String _tmpSourceLink;
        _tmpSourceLink = _cursor.getString(_cursorIndexOfSourceLink);
        _item.setSourceLink(_tmpSourceLink);
        final String _tmpSourceLogo;
        _tmpSourceLogo = _cursor.getString(_cursorIndexOfSourceLogo);
        _item.setSourceLogo(_tmpSourceLogo);
        final String _tmpSourceImage;
        _tmpSourceImage = _cursor.getString(_cursorIndexOfSourceImage);
        _item.setSourceImage(_tmpSourceImage);
        final boolean _tmpAllowedSource;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowedSource);
        _tmpAllowedSource = _tmp != 0;
        _item.setAllowedSource(_tmpAllowedSource);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        _item.setAuthor(_tmpAuthor);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _item.setDate(_tmpDate);
        final String _tmpCover_image;
        _tmpCover_image = _cursor.getString(_cursorIndexOfCoverImage);
        _item.setCover_image(_tmpCover_image);
        final String _tmpCover_audio;
        _tmpCover_audio = _cursor.getString(_cursorIndexOfCoverAudio);
        _item.setCover_audio(_tmpCover_audio);
        final String _tmpCover_video;
        _tmpCover_video = _cursor.getString(_cursorIndexOfCoverVideo);
        _item.setCover_video(_tmpCover_video);
        final String _tmpO_cover_a_prev;
        _tmpO_cover_a_prev = _cursor.getString(_cursorIndexOfOCoverAPrev);
        _item.setO_cover_a_prev(_tmpO_cover_a_prev);
        final String _tmpOriginal_image;
        _tmpOriginal_image = _cursor.getString(_cursorIndexOfOriginalImage);
        _item.setOriginal_image(_tmpOriginal_image);
        final String _tmpCover_y_embed;
        _tmpCover_y_embed = _cursor.getString(_cursorIndexOfCoverYEmbed);
        _item.setCover_y_embed(_tmpCover_y_embed);
        final String _tmpO_cover_v_prev;
        _tmpO_cover_v_prev = _cursor.getString(_cursorIndexOfOCoverVPrev);
        _item.setO_cover_v_prev(_tmpO_cover_v_prev);
        final String _tmpCover_caption;
        _tmpCover_caption = _cursor.getString(_cursorIndexOfCoverCaption);
        _item.setCover_caption(_tmpCover_caption);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Keys> isKeyExist(String val) {
    final String _sql = "SELECT * FROM keys WHERE keys = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (val == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, val);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfKeys = _cursor.getColumnIndexOrThrow("keys");
      final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
      final List<Keys> _result = new ArrayList<Keys>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Keys _item;
        _item = new Keys();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpKeys;
        _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        _item.setKeys(_tmpKeys);
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        _item.setCategory(_tmpCategory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Long isSoureExist(String url) {
    final String _sql = "SELECT `rowid` FROM source WHERE link  = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final Long _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getLong(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Long isCatExist(String name) {
    final String _sql = "SELECT `rowid` FROM category WHERE name  = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final Long _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getLong(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Source> getAllenabledSource() {
    final String _sql = "SELECT * FROM source WHERE visibility = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLink = _cursor.getColumnIndexOrThrow("link");
      final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfAllowed = _cursor.getColumnIndexOrThrow("allowed");
      final int _cursorIndexOfEnabled = _cursor.getColumnIndexOrThrow("enabled");
      final int _cursorIndexOfVisibility = _cursor.getColumnIndexOrThrow("visibility");
      final int _cursorIndexOfPri = _cursor.getColumnIndexOrThrow("pri");
      final List<Source> _result = new ArrayList<Source>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Source _item;
        _item = new Source();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpLink;
        _tmpLink = _cursor.getString(_cursorIndexOfLink);
        _item.setLink(_tmpLink);
        final String _tmpLogo;
        _tmpLogo = _cursor.getString(_cursorIndexOfLogo);
        _item.setLogo(_tmpLogo);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item.setImage(_tmpImage);
        final boolean _tmpAllowed;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowed);
        _tmpAllowed = _tmp != 0;
        _item.setAllowed(_tmpAllowed);
        final boolean _tmpEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp_1 != 0;
        _item.setEnabled(_tmpEnabled);
        final boolean _tmpVisibility;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfVisibility);
        _tmpVisibility = _tmp_2 != 0;
        _item.setVisibility(_tmpVisibility);
        final int _tmpPri;
        _tmpPri = _cursor.getInt(_cursorIndexOfPri);
        _item.setPri(_tmpPri);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Category> getAllVisibleCategories() {
    final String _sql = "SELECT * FROM category WHERE visibility = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfRes = _cursor.getColumnIndexOrThrow("res");
      final int _cursorIndexOfEnabled = _cursor.getColumnIndexOrThrow("enabled");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfVisibility = _cursor.getColumnIndexOrThrow("visibility");
      final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Category _item;
        _item = new Category();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final int _tmpRes;
        _tmpRes = _cursor.getInt(_cursorIndexOfRes);
        _item.setRes(_tmpRes);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _item.setEnabled(_tmpEnabled);
        final int _tmpPriority;
        _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
        _item.setPriority(_tmpPriority);
        final boolean _tmpVisibility;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVisibility);
        _tmpVisibility = _tmp_1 != 0;
        _item.setVisibility(_tmpVisibility);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Source> getSource(String name) {
    final String _sql = "SELECT * FROM source WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowid = _cursor.getColumnIndexOrThrow("rowid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfLink = _cursor.getColumnIndexOrThrow("link");
      final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfAllowed = _cursor.getColumnIndexOrThrow("allowed");
      final int _cursorIndexOfEnabled = _cursor.getColumnIndexOrThrow("enabled");
      final int _cursorIndexOfVisibility = _cursor.getColumnIndexOrThrow("visibility");
      final int _cursorIndexOfPri = _cursor.getColumnIndexOrThrow("pri");
      final List<Source> _result = new ArrayList<Source>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Source _item;
        _item = new Source();
        final Long _tmpRowid;
        if (_cursor.isNull(_cursorIndexOfRowid)) {
          _tmpRowid = null;
        } else {
          _tmpRowid = _cursor.getLong(_cursorIndexOfRowid);
        }
        _item.setRowid(_tmpRowid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpLink;
        _tmpLink = _cursor.getString(_cursorIndexOfLink);
        _item.setLink(_tmpLink);
        final String _tmpLogo;
        _tmpLogo = _cursor.getString(_cursorIndexOfLogo);
        _item.setLogo(_tmpLogo);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item.setImage(_tmpImage);
        final boolean _tmpAllowed;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowed);
        _tmpAllowed = _tmp != 0;
        _item.setAllowed(_tmpAllowed);
        final boolean _tmpEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp_1 != 0;
        _item.setEnabled(_tmpEnabled);
        final boolean _tmpVisibility;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfVisibility);
        _tmpVisibility = _tmp_2 != 0;
        _item.setVisibility(_tmpVisibility);
        final int _tmpPri;
        _tmpPri = _cursor.getInt(_cursorIndexOfPri);
        _item.setPri(_tmpPri);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
