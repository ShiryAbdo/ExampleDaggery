package com.shimaa.dagger.components;

import com.shimaa.dagger.MainActivity;
import com.shimaa.dagger.adaptors.AdapterRepos;
import com.shimaa.dagger.annotations.MainActivityScope;
 import com.shimaa.dagger.modules.MainActivityModule;
import com.shimaa.dagger.network.GithubService;

import dagger.Component;

 @Component(modules = MainActivityModule.class,
        dependencies = ComponentInterFace.class ) // Tell Dagger to use GithubApplicationComponent as a source for our modules
@MainActivityScope
public interface MainActivityComponent {

    AdapterRepos getAdapterRepos();

    GithubService getGithubService();

    // if return type is void, Dagger assumes that he has to inject something somewhere
    // so he will look into method parameters and try to inject there (here - HomeActivity) to @Inject
    void injectHomeActivity(MainActivity homeActivity);


}
