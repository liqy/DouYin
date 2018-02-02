package com.liqy.douyin.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liqy on 2018/2/2.
 */

@Entity
public class Comment {
    @Id
    public String cid;

    @SerializedName("text")
    public String textDesc;

    @Generated(hash = 510860273)
    public Comment(String cid, String textDesc) {
        this.cid = cid;
        this.textDesc = textDesc;
    }

    @Generated(hash = 1669165771)
    public Comment() {
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTextDesc() {
        return this.textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }
}
