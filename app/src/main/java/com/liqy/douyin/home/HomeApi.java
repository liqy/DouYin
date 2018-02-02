package com.liqy.douyin.home;

import com.liqy.douyin.common.HttpResult;
import com.liqy.douyin.entity.User;
import com.liqy.douyin.entity.UserResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 主页
 * Created by liqy on 2018/1/27.
 */

public interface HomeApi {

    /**
     * 用户信息
     *
     * @param uid
     * @return
     */
    @GET("aweme/v1/user/")
    Observable<UserResult<User>> userInfo(@Query("user_id") String uid);

    /**
     * 自己的信息
     * @return
     */
    @GET("aweme/v1/user/")
    Observable<HttpResult> userInfo();

    /**
     * 播放列表
     *
     * @param type 0 ，1
     * @param max_cursor 0
     * @param min_cursor 0
     * @param count 6
     * @return
     */
    @GET("aweme/v1/feed/")
    Observable<HttpResult> feed(@Query("type") int type,
                                @Query("max_cursor") int max_cursor,
                                @Query("min_cursor") int min_cursor,
                                @Query("count") int count);

    /**
     * 附近
     * @param max_cursor 0
     * @param min_cursor 0
     * @param count 20
     * @param feed_style 1
     * @return
     */
    @GET("aweme/v1/nearby/feed/")
    Observable<HttpResult> feedNearby(@Query("max_cursor") int max_cursor,
                                      @Query("min_cursor") int min_cursor,
                                      @Query("count") int count,
                                      @Query("feed_style") int feed_style);

    /**
     * 评论列表
     * @param aweme_id
     * @param cursor
     * @param count
     * @param comment_style
     * @return
     */
    @GET("aweme/v1/comment/list/")
    Observable<HttpResult> commentList(@Query("aweme_id") String aweme_id,
                                       @Query("cursor") int cursor,
                                       @Query("count") int count,
                                       @Query("comment_style") int comment_style);

}
