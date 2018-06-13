package com.shimaa.dagger.network;


import com.shimaa.dagger.models.GithubRepo;
import com.shimaa.dagger.models.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    @GET("users/{username}/repos")
    Call<List<GithubRepo>> getReposForUser(@Path("username") String username);

    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);
}
