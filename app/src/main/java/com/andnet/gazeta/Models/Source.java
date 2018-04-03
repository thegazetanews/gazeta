package com.andnet.gazeta.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "source")
public class Source{


    @PrimaryKey(autoGenerate = true)
    private Long rowid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "link")
    private String link;

    @ColumnInfo(name = "logo")
    private String logo;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "allowed")
    private boolean allowed;

    @ColumnInfo(name = "enabled")
    private boolean enabled;

    @ColumnInfo(name ="visibility")
    private boolean visibility;

    @ColumnInfo(name = "pri")
    private int pri;

    @Ignore
    public Source(String name,  String link, String logo, String image, boolean allowed,boolean enabled) {
        this.name = name;
        this.link = link;
        this.logo = logo;
        this.image = image;
        this.allowed = allowed;
        this.enabled=enabled;
    }

    public Source() {}

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public void setPri(int pri) {
        this.pri = pri;
    }

    public int getPri() {
        return pri;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
