package com.jlp.mvvm_jlp_project.di;/*
 * Created by Sandeep(Techno Learning) on 17,June,2022
 */

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

//    @Singleton
//    @Provides
//    Context provideContext(Application application){
//        return application.getApplicationContext();
//    }
}
