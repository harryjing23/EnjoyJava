package com.harry.myapplication.rxjava.wanandroid;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created on 2022/3/14.
 *
 * @author harry
 */
public interface WanAndroidApi {

    // 总数据
    @GET("tree/json")
    // 返回类型是Observable，作为RxJava的起点
    Observable<ProjectBean> getProject();


    // item数据。project/list/1/json?cid=294
    @GET("project/list/{pageIndex}/json")
    Observable<ItemBean> getProjectItem(@Path("pathIndex") int pageIndex, @Query("cid") int cid);
}
