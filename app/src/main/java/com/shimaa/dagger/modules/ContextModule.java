package com.shimaa.dagger.modules;

import android.content.Context;

import com.shimaa.dagger.annotations.ApplicationContextQualifier;
import com.shimaa.dagger.annotations.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context){
        this.context=context.getApplicationContext();
    }
    @Provides
    @ApplicationScope
    @ApplicationContextQualifier  /* replace @Named("application_context") */// add a qualifier  with that annotation do the same of@named
    public Context context(){
        return context;
    }
}
