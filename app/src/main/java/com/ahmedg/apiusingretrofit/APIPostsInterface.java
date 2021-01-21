package com.ahmedg.apiusingretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIPostsInterface {
    @GET("posts")
    Call<PostsModelPojo []> getPost();
}
