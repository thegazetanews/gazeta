package com.andnet.gazeta.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "category")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private Long rowid;

    @ColumnInfo(name ="name")
    private String name;

    @ColumnInfo(name ="res")
    private int res;

    @ColumnInfo(name ="enabled")
    private boolean enabled;

    @ColumnInfo(name="priority")
    private int priority;

    @ColumnInfo(name ="visibility")
    private boolean visibility;


    @Ignore
    public Category(String name, int res, boolean enabled, int priority) {
        this.name = name;
        this.res = res;
        this.enabled = enabled;
        this.priority=priority;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Category() {
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
