package com.shimaa.dagger.modules;

import android.app.Activity;
import android.content.Context;

import com.shimaa.dagger.annotations.ApplicationScope;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity){
        this.activity=activity;
    }

    @Provides
    @ApplicationScope
    @Named("activity_context")
    public Context context(){
        return activity;
    }

}
