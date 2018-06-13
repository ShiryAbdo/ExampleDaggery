package com.shimaa.dagger.modules;

import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shimaa.dagger.annotations.ApplicationScope;
import com.shimaa.dagger.network.GithubService;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class GithubServiceModule {
    @Provides
    @ApplicationScope
    public GithubService getGithubService(Retrofit gitHubRetrofit){
        return gitHubRetrofit.create(GithubService.class);
    }

    @Provides
    @ApplicationScope
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient) // tell retrofit to use our OkHttpClient
                .baseUrl("https://api.github.com/")
                .build();
    }

}
