package com.android1.shoplarity.network;

import com.android1.shoplarity.Apifolder.Apiresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("businesses/search")
    Call<Apiresponse>getCategory(
            @Query("term") String term,
            @Query("location") String location
    );
}
