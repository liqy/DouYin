package com.liqy.douyin.entity;

import com.liqy.douyin.common.HttpResult;

/**
 * Created by liqy on 2018/2/2.
 */

public class UserResult<T> extends HttpResult{
  public   T user;

    @Override
    public String toString() {
        return "UserResult{" +
                "user=" + user +
                '}';
    }
}
