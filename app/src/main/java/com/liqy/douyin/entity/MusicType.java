package com.liqy.douyin.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liqy on 2018/2/2.
 */

@Entity
public class MusicType {

    @Id
    public String id_str;

    public String mc_name;

    @Generated(hash = 328558331)
    public MusicType(String id_str, String mc_name) {
        this.id_str = id_str;
        this.mc_name = mc_name;
    }

    @Generated(hash = 1846957130)
    public MusicType() {
    }

    public String getId_str() {
        return this.id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getMc_name() {
        return this.mc_name;
    }

    public void setMc_name(String mc_name) {
        this.mc_name = mc_name;
    }


}
