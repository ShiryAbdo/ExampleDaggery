package com.shimaa.dagger.controls;

import android.app.Activity;
import android.app.Application;
import com.shimaa.dagger.components.ComponentInterFace;
import com.shimaa.dagger.components.DaggerComponentInterFace;
import com.shimaa.dagger.modules.ContextModule;
import com.shimaa.dagger.network.GithubService;
import com.squareup.picasso.Picasso;

import timber.log.Timber;


public class MyApplicationClass extends Application {

   private ComponentInterFace  componentInterFace ;
    private GithubService githubService;
    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        componentInterFace = DaggerComponentInterFace.builder()
                .contextModule(new ContextModule(this))
                  .build();


        // access these componentInterFace
        githubService = componentInterFace.getGithubService();
        picasso = componentInterFace.getPicasso();

        // Will have different memory addresses! => not a Singleton (yet)
        // UPDATED: we added GithubApplicationScope => only one instance of each module method per Component
        GithubService githubService2 = componentInterFace.getGithubService();
        Picasso picasso2 = componentInterFace.getPicasso();
        GithubService githubService3 = componentInterFace.getGithubService();
        Picasso picasso3 = componentInterFace.getPicasso();
        // print memory addresses
        Timber.i("Dagger 2: githubService "+githubService);
        Timber.i("Dagger 2: githubService2 "+githubService2);
        Timber.i("Dagger 2: githubService3 "+githubService3);
        Timber.i("Dagger 2: picasso "+picasso);
        Timber.i("Dagger 2: picasso2 "+picasso2);
        Timber.i("Dagger 2: picasso3 "+picasso3);




    }
    public ComponentInterFace getComponent() {
        return componentInterFace;
    }


     public static MyApplicationClass get(Activity activity) {
        return (MyApplicationClass) activity.getApplication();
    }

}
