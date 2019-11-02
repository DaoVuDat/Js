package com.example.js.Service;

import com.example.js.Model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("posts/")
    Call<ArrayList<Post>> getPosts();

}
