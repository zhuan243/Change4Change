package com.example.hw6;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Calls {

    @GET("accounts/{id}/purchases")
    Call<List<purchases>> listpurchases(@Path("id") String id, @Query("key") String key);

}
