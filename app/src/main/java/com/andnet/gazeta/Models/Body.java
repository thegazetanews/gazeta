package com.andnet.gazeta.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "body")
public class Body {

    @PrimaryKey(autoGenerate = true)
    private Long rowid;

    @ColumnInfo(name ="keys")
    private String keys;

    @ColumnInfo(name = "body")
    private String body;




    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public Body() {
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
