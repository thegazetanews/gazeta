package com.andnet.gazeta.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news")
public class News {

    @PrimaryKey(autoGenerate = true)
    private Long rowid;

    @ColumnInfo(name = "keys")
    private String keys;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "thumbnail")
    private String thumbnail;

    @ColumnInfo(name = "synop")
    private String  synop;

    @ColumnInfo(name = "title")
    private String  title;

    @ColumnInfo(name = "link")
    private String  link;

    @ColumnInfo(name = "source")
    private String  source;

    @ColumnInfo(name = "sourceName")
    private String sourceName;

    @ColumnInfo(name = "sourceLink")
    private String sourceLink;

    @ColumnInfo(name = "sourceLogo")
    private String sourceLogo;

    @ColumnInfo(name = "sourceImage")
    private String sourceImage;

    @ColumnInfo(name = "allowedSource")
    private boolean allowedSource;


    @ColumnInfo(name = "author")
    private String  author;

    @ColumnInfo(name = "date")
    private String  date;

    @ColumnInfo(name = "cover_image")
    private String  cover_image;

    @ColumnInfo(name = "cover_audio")
    private String  cover_audio;

    @ColumnInfo(name = "cover_video")
    private String  cover_video;

    @ColumnInfo(name = "o_cover_a_prev")
    private String  o_cover_a_prev;

    @ColumnInfo(name = "original_image")
    private String  original_image;

    @ColumnInfo(name = "cover_y_embed")
    private String  cover_y_embed;

    @ColumnInfo(name = "o_cover_v_prev")
    private String  o_cover_v_prev;

    @ColumnInfo(name = "cover_caption")
    private String  cover_caption;


    public News() {
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSynop() {
        return synop;
    }

    public void setSynop(String synop) {
        this.synop = synop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getSourceLogo() {
        return sourceLogo;
    }

    public void setSourceLogo(String sourceLogo) {
        this.sourceLogo = sourceLogo;
    }

    public String getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }

    public boolean isAllowedSource() {
        return allowedSource;
    }

    public void setAllowedSource(boolean allowedSource) {
        this.allowedSource = allowedSource;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getCover_audio() {
        return cover_audio;
    }

    public void setCover_audio(String cover_audio) {
        this.cover_audio = cover_audio;
    }

    public String getCover_video() {
        return cover_video;
    }

    public void setCover_video(String cover_video) {
        this.cover_video = cover_video;
    }

    public String getO_cover_a_prev() {
        return o_cover_a_prev;
    }

    public void setO_cover_a_prev(String o_cover_a_prev) {
        this.o_cover_a_prev = o_cover_a_prev;
    }

    public String getOriginal_image() {
        return original_image;
    }

    public void setOriginal_image(String original_image) {
        this.original_image = original_image;
    }

    public String getCover_y_embed() {
        return cover_y_embed;
    }

    public void setCover_y_embed(String cover_y_embed) {
        this.cover_y_embed = cover_y_embed;
    }

    public String getO_cover_v_prev() {
        return o_cover_v_prev;
    }

    public void setO_cover_v_prev(String o_cover_v_prev) {
        this.o_cover_v_prev = o_cover_v_prev;
    }

    public String getCover_caption() {
        return cover_caption;
    }

    public void setCover_caption(String cover_caption) {
        this.cover_caption = cover_caption;
    }
}