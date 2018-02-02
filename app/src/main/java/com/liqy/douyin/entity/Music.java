package com.liqy.douyin.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liqy on 2018/2/2.
 */

@Entity
public class Music {

    @Id
    @SerializedName("id")
    public long pid;

    public String author;
    public String title;
    @Generated(hash = 294002375)
    public Music(long pid, String author, String title) {
        this.pid = pid;
        this.author = author;
        this.title = title;
    }
    @Generated(hash = 1263212761)
    public Music() {
    }
    public long getPid() {
        return this.pid;
    }
    public void setPid(long pid) {
        this.pid = pid;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
