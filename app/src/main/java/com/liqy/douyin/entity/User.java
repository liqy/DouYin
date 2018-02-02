package com.liqy.douyin.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liqy on 2018/2/1.
 */

@Entity
public class User {
    @Id(autoincrement = true)
    public Long id;

    @SerializedName("nickname")
    public String name;

    public String signature;

    @Generated(hash = 914631923)
    public User(Long id, String name, String signature) {
        this.id = id;
        this.name = name;
        this.signature = signature;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
