package com.shimaa.dagger.modules;

import com.shimaa.dagger.MainActivity;
import com.shimaa.dagger.adaptors.AdapterRepos;
import com.shimaa.dagger.annotations.MainActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
@Module
public class MainActivityModule {
    private final MainActivity homeActivity;
    @MainActivityScope
    public MainActivityModule(MainActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity provideHomeActivity(){
        return homeActivity;
    }

    // Replaced by @Inject in the AdapterRepos's constructor
    // and probideHomeActivity();
    /*
    @Provides
    @HomeActivityScope
    public AdapterRepos provideAdapterRepos(Picasso picasso){
        return new AdapterRepos(homeActivity, picasso);
    }
    */
}
