package com.shimaa.dagger.components;

import com.shimaa.dagger.annotations.ApplicationScope;
import com.shimaa.dagger.modules.ActivityModule;
import com.shimaa.dagger.modules.GithubServiceModule;
import com.shimaa.dagger.modules.PicassoModule;
import com.shimaa.dagger.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;
@ApplicationScope
@Component(modules = {GithubServiceModule.class, PicassoModule.class , ActivityModule.class})
public interface ComponentInterFace {
    Picasso getPicasso();
    GithubService getGithubService();
}
