package com.harry.myapplication.rxjava;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created on 2021/9/27.
 *
 * @author harry
 */
public interface WanAndroidApi {

    @GET("project/tree/json")
    Observable<ProjectBean> getProject();

    @GET("project/list/{pageIndex}/json")
//project/list/1/json?cid=294
    Observable<ItemBean> getProjectItem( @Path("pageIndex") int pageIndex, @Query("cid") int cid);

}
