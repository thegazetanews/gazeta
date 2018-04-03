package com.andnet.gazeta.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "keys")
public class Keys {

    @PrimaryKey(autoGenerate = true)
    private Long rowid;

    @ColumnInfo(name ="keys")
    private String keys;


    @ColumnInfo(name = "category")
    private String category;


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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
